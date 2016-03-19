package java2bash.java2bash.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class BashStrings {
	
	/**
	 * 
	 * @param haystack
	 * @param from
	 * @param to
	 * @return
	 */
	public static String replaceStringUsingChars(String haystack, String from, String to) {
		if (from.length() != to.length()) {
			throw new IllegalArgumentException("from and to should have same number of characters.");
		}
		Map<String, String> replacements = new HashMap<String, String>();
		int length = from.length();
		for (int i = 0; i < length; i++) {
			replacements.put(String.valueOf(from.charAt(i)), String.valueOf(to.charAt(i)));
		}
		return replaceStringUsingMap(haystack, replacements);
	}
	
	/**
	 * 
	 * @param haystack
	 * @param needle2replacement
	 * @return
	 */
	public static String replaceStringUsingMap(String haystack, Map<String, String> needle2replacement)
	{
		// To optimize, just return the haystack if the map has no items
		if (needle2replacement.isEmpty()) {
			return haystack;
		}
		
		// Sort map by length of keys please by longest string first
		Map<String, String> sortedNeedle2replacement = sortKeysByLengthDescending(needle2replacement);
		
		Set<String> arrNeedles = sortedNeedle2replacement.keySet();
		List<Range> ranges = new ArrayList<>();
		
		int lengthDifference = 0;
		for (String needle : arrNeedles) {
			
			int indexOf = -1;
			
			// Ignore needles whom are empty strings
			if (needle.equals("")) {
				continue;
			}
			
			while(-1 != (indexOf = haystack.indexOf(needle, indexOf + 1))) {
				String replacement = sortedNeedle2replacement.get(needle);
				if (replacement == null) {
					throw new IllegalArgumentException("Map value of null found in key ['" + needle + "']");
				}
				
				Range range = new Range(indexOf, indexOf + needle.length() - 1, needle, replacement);
				
				// Check for any overlaps
				boolean addRange = true;
				for (Range target : ranges) {
					if (range.isOverlappingWith(target)) {
						addRange = false;
						break;
					}
				}
				if (addRange) {
					ranges.add(range);
					lengthDifference += range.replacement.length() - range.needle.length();
				}
			}
		}
		
		// Sort ranges please
		Collections.sort(ranges, new Comparator<Range>() {
			@Override
			public int compare(Range o1, Range o2) {
				return o1.begin - o2.begin;
			}
		});
		
		// type them
		StringBuilder stringBuilder = new StringBuilder(haystack.length() + lengthDifference);
		System.out.println(haystack.length());
		System.out.println(stringBuilder.capacity());
		
		int startAt = 0;
		for (Range range : ranges) {
			System.out.println(range.toString());
			stringBuilder.append(haystack.substring(startAt, range.begin));
			stringBuilder.append(range.replacement);
			startAt = range.end + 1;
		}
		stringBuilder.append(haystack.substring(startAt));
		
		System.out.println(stringBuilder.capacity());
		return stringBuilder.toString();
	}
	public static String escapeWithDoubleQuotes(String toEscape) {
		
		return toEscape;
	}
	
	public static String escapeWithSingleQuotes(String toEscape) {
		return toEscape;
	}
	
	private static class Range
	{
		public Range(int begin, int end, String needle, String replacement) {
			super();
			if (begin > end) {
				throw new RuntimeException("begin [" + begin + "] should be smaller or equal to end [" + end + "]");
			}
			this.begin = begin;
			this.end = end;
			this.needle = needle;
			this.replacement = replacement;
		}
		
		public final int begin;
		public final int end;
		public final String needle;
		public final String replacement;
		
		public String toString() {
			return String.format("{%d,%d,%s,%s}", begin, end, needle, replacement);
		}
		
		/**
		 * @param target
		 * @return
		 */
		public boolean isOverlappingWith(Range target) {
			final boolean liesBefore = this.end < target.begin;
			final boolean liesAfter = this.begin > target.end;
			if (liesBefore || liesAfter) {
				return false;
			}
			return true;
		}
		
		/**
		 * +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		 *             XXXXXX
		 *          YYYYYYYYYYYYYYYY
		 *          
		 * Y is surround X as shown above
		 * 
		 * @param target
		 * @return
		 */
		public boolean isSurrounding(Range target) {
			if (this.begin < target.begin && this.end > target.end) {
				return true;
			}
			return false;
		}
	}
	

	private static Map<String, String> sortKeysByLengthDescending(
			Map<String, String> needle2replacement) {
		Map<String, String> sortedNeedle2replacement = new TreeMap<String, String>(new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				if (s1.length() > s2.length()) {
	                return -1;
	            } else if (s1.length() < s2.length()) {
	                return 1;
	            } else {
	                return s1.compareTo(s2);
	            }
			}
		});
		sortedNeedle2replacement.putAll(needle2replacement);
		
		Set<String> keySet = sortedNeedle2replacement.keySet();
		for (String key : keySet) {
			System.out.println(key + ":" + sortedNeedle2replacement.get(key));
		}
			
		return sortedNeedle2replacement;
	}
	
	public static void main(String[] args) {
		try {
			Map<String, String> replacements = new TreeMap<String, String>();
			//maps.put("a", "x");
			replacements.put("this", "that");
			replacements.put("that", "this");
			replacements.put("Nothing much", "Everything");
			
			String example = Strtr.replaceStringUsingMap("Whether this or that, whats the difference? Nothing much.", replacements);
			System.out.println(example);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
