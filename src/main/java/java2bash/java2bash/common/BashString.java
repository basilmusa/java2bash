package java2bash.java2bash.common;

public class BashString {
	
	private final String rawString;
	private final EscapeMethod escapeMethod;

	private BashString(String rawString, EscapeMethod escapeMethod) {
		this.rawString = rawString;
		this.escapeMethod = escapeMethod;
	}

	public String getRawString() {
		return rawString;
	}

	public EscapeMethod getEscapeMethod() {
		return escapeMethod;
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
		return escapeMethod.escapeString(rawString);
	}
	
	public static Builder builder() {
		return new Builder();
	}
	public static class Builder {
		private StringBuilder sb = new StringBuilder();
		
		public Builder literal(String str) {
			sb.append(BashStrings.escapeForDoubleQuoting(str));
			return this;
		}
		public Builder literal(BashString bashString) {
			sb.append(BashStrings.escapeForDoubleQuoting(bashString.getRawString()));
			return this;
		}
		public Builder var(String varname) {
			sb.append("${" + varname + "}");
			return this;
		}
		public String buildRawString() {
			return '"' + sb.toString() + '"';
		}
		public BashString buildBashString() {
			return BashString.createRaw(sb.toString());
		}
	}
}
