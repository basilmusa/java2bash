package java2bash.java2bash.common;


public enum EscapeMethod {
	DOUBLE_QUOTES {
		@Override
		public String escapeString(String toEscape) {
			return BashStrings.escapeAndSurroundDoubleQuotes(toEscape);
		}
	},
	SINGLE_QUOTES {
		@Override
		public String escapeString(String toEscape) {
			return BashStrings.escapeAndSurroundSingleQuotes(toEscape);
		}
	},
	RAW {
		@Override
		public String escapeString(String toEscape) {
			return toEscape;
		}
	};
	
	private EscapeMethod() {
	}
	
	public abstract String escapeString(final String toEscape);
}
