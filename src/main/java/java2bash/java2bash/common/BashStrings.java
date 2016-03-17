package java2bash.java2bash.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

public class BashStrings {
	
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
		 * 012345678901234567890123456789
		 *            XXXXXXXXXXXX
		 *        AAAAA
		 *                       CCCCC
		 *            BBBBBBBBBBBB
		 *      YYYYYYYYYY
		 *                   ZZZZZZZZZZZZ
		 *  WWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWWW
		 *             
		 * A, B, C, Y, W and Z are all overlapping with XXXXX
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
	
	private static String replaceStringUsingMap(String haystack, Map<String, String> needle2replacement) 
	{
		StringBuilder stringBuilder = new StringBuilder(haystack.length() * 2);
		
		Set<String> arrNeedles = needle2replacement.keySet();
		List<Range> ranges = new ArrayList<>();
		
		for (String needle : arrNeedles) {
			
			int indexOf = -1;
			while(-1 != (indexOf = haystack.indexOf(needle, indexOf + 1))) {
				Range range = new Range(indexOf, indexOf + needle.length() - 1, needle, needle2replacement.get(needle));
				
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
		int startAt = 0;
		for (Range range : ranges) {
			System.out.println(range.toString());
			stringBuilder.append(haystack.substring(startAt, range.begin));
			stringBuilder.append(range.replacement);
			startAt = range.end + 1;
		}
		stringBuilder.append(haystack.substring(startAt));
		
		return stringBuilder.toString();
	}
	
	public static void main(String[] args) {
		Map<String, String> maps = new TreeMap<String, String>();
		maps.put("abbas", "musa");
		maps.put("bba", "zift");
		maps.put("basil", "tarek");
		maps.put("basil", "tarek");
		String example = BashStrings.replaceStringUsingMap("basil abbasabbas is bba a good boy.abbas bba", maps);
		System.out.println(example);
	}
}
