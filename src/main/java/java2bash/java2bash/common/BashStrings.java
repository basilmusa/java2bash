package java2bash.java2bash.common;

import java.util.HashMap;
import java.util.Map;


/**
 * This class is used to escape bash strings with either single quotes or double quotes
 * and applying the needed escape characters.
 * 
 * The methods are:
 * 
 *     {@link #escapeWithDoubleQuotes(String)}
 *     {@link #escapeWithSingleQuotes(String)}
 *
 */
public class BashStrings 
{
	/**
	 * Escape with double quotes
	 * 
	 * @param toEscape
	 * @return
	 */
	public static String escapeWithDoubleQuotes(String toEscape) {
		Map<String, String> doubleQuoteReplacements = new HashMap<String, String>();
		doubleQuoteReplacements.put("\"", "\\\"");
		doubleQuoteReplacements.put("$", "\\$");
		doubleQuoteReplacements.put("\\", "\\\\");
		doubleQuoteReplacements.put("\\", "\\\\");
		
		String escaped = Strtr.replaceStringUsingMap(toEscape, doubleQuoteReplacements);
		return "\"" + escaped + "\"";
	}
	
	/**
	 * Escaping with single quotes as follows:
	 * 
	 * Replaces occurrences of ' with '"'"'
	 * Surrounds the string with ' .. ' single quotes
	 * 
	 * @param toEscape
	 * @return
	 */
	public static String escapeWithSingleQuotes(String toEscape) {
		
		Map<String, String> singleQuoteReplacements = new HashMap<String, String>();
		singleQuoteReplacements.put("'", "'\"'\"'");
		
		String escaped = Strtr.replaceStringUsingMap(toEscape, singleQuoteReplacements);
		return "'" + escaped + "'";
	}
	
	public static void main(String[] args) {
		String toEscape = "Basil is \" good boy and $ not really \\ good.";
		System.out.println(toEscape);
		System.out.println(BashStrings.escapeWithDoubleQuotes(toEscape));
	}
}
