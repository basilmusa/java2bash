package java2bash.java2bash.common;

public class BashString {
	
	private final String str;
	private final EscapeMethod escapeMethod;

	private BashString(String str, EscapeMethod escapeMethod) {
		this.str = str;
		this.escapeMethod = escapeMethod;
	}
	
	/**
	 * Static factory methods for creating BashString's
	 * @param str
	 * @param escapeMethod
	 * @return
	 */
	public static BashString create(String str, EscapeMethod escapeMethod) {
		return new BashString(str, escapeMethod);
	}
	
	public static BashString createRaw(String str) {
		return new BashString(str, EscapeMethod.RAW);
	}
	
	public static BashString createEscaped(String str) {
		return new BashString(str, EscapeMethod.DOUBLE_QUOTES);
	}

	public static BashString createEscapedSingleQuotes(String str) {
		return new BashString(str, EscapeMethod.SINGLE_QUOTES);
	}
	
	/**
	 * To get the internal string
	 */
	public String toString() {
		return escapeMethod.escapeString(str);
	}
}
