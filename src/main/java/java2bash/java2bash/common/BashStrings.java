package java2bash.java2bash.common;

import java.util.HashMap;
import java.util.Map;


/**
 * This class is used to escape bash strings with either single quotes or double quotes
 * and applying the needed escape characters.
 * 
 * The methods that escape and surround fully are:
 * 
 *     {@link #escapeAndSurroundDoubleQuotes(String)}
 *     {@link #escapeAndSurroundSingleQuotes(String)}
 *
 * The methods that escape WITHOUT surrounding are:
 *     
 *     {@link #escapeForDoubleQuoting(String)}
 *     {@link #escapeForSingleQuoting(String)}
 */
public class BashStrings 
{
	/**
	 * This will escape the string and surround it with double quotes
	 * 
	 * @param toEscape
	 * @return
	 */
	public static String escapeAndSurroundDoubleQuotes(String toEscape) {
		String escaped = escapeForDoubleQuoting(toEscape);
		return "\"" + escaped + "\"";
	}
	
	/**
	 * Surrounds string with single quotes after escaping any needed characters in between.
	 * 
	 * @param toEscape
	 * @return
	 */
	public static String escapeAndSurroundSingleQuotes(String toEscape) {
		String escaped = escapeForSingleQuoting(toEscape);
		return "'" + escaped + "'";
	}
	
	/**
	 * This method escapes for single quotes but without surrounding the string with single quotes.
	 * Use this method inside a substring of a single quoted string.
	 * 
	 * @param toEscape
	 * @return
	 */
	public static String escapeForSingleQuoting(String toEscape) {
		Map<String, String> singleQuoteReplacements = new HashMap<String, String>();
		singleQuoteReplacements.put("'", "'\"'\"'");
		
		return Strtr.replaceStringUsingMap(toEscape, singleQuoteReplacements);
	}
	
	/**
	 * This methods escapes for double quotes but without surrounding the string with double quotes.
	 * Use this method inside a substring of a double quoted string.
	 * @param toEscape
	 * @return
	 */
	public static String escapeForDoubleQuoting(String toEscape) {
		Map<String, String> doubleQuoteReplacements = new HashMap<String, String>();
		doubleQuoteReplacements.put("\"", "\\\"");
		doubleQuoteReplacements.put("$", "\\$");
		doubleQuoteReplacements.put("\\", "\\\\");
		doubleQuoteReplacements.put("\\", "\\\\");
		
		return Strtr.replaceStringUsingMap(toEscape, doubleQuoteReplacements);		
	}
	
	public static void main(String[] args) {
		String toEscape = "Basil is \" good boy and $ not really \\ good.";
		System.out.println(toEscape);
		System.out.println(BashStrings.escapeAndSurroundDoubleQuotes(toEscape));
	}
}
