package java2bash.java2bash.commands.simple;

import java.util.EnumSet;

import java2bash.java2bash.commands.AbstractCodeSnippet;
import java2bash.java2bash.common.BashStrings;


/**
 * Resembles the 'echo' command to use in the bash script
 * 
 */
public class Echo extends AbstractCodeSnippet {
	
	public static enum Options {
		/**
		 * Add the -n option for the echo which simply means do not write on a new line
		 */
		NO_NEW_LINE("-n"),
		/**
		 * Interpret backslashs escapes such as \n, \r, \t by passing the '-e' option to echo
		 */
		INTERPRET_BACKSLASH_ESCAPES("-e"),
		
		/**
		 * Don't interpret backslashes
		 */
		DISABLE_INTERPRET_BACKSLASH_ESCAPES("-E");
		
		private Options(String option) {
			this.option = option;
		}

		private final String option;

		public String getOption() {
			return option;
		}
	}
	
	private final String rawText;
	private final EnumSet<Options> options;
	
	/**
	 * Gets passed what you need to echo as a raw (unescaped) string.
	 * To escape your string you can use {@link BashStrings} using
	 * the methods {@link BashStrings#escapeWithDoubleQuotes(String)} or
	 * {@link BashStrings#escapeWithSingleQuotes(String)}.
	 * 
	 */
	public Echo(String rawText) {
		this(rawText, EnumSet.noneOf(Options.class));
	}
	
	/**
	 * Syntatic sugar constructor to pass only one option
	 * @param rawText
	 * @param option
	 */
	public Echo(String rawText, Options option) {
		this(rawText, EnumSet.of(option));
	}
	
	/**
	 * Echo passed several options here in addition to rawText unescaped string 
	 * 
	 * @param rawText
	 * @param options
	 */
	public Echo(String rawText, EnumSet<Options> options) {
		this.rawText = rawText;
		this.options = options;
	}

	@Override
	public String getCode() 
	{
		String commandLineOptions = "";
		for (Options option : options) {
			commandLineOptions += option.getOption() + " ";
		}
		
		return "echo " + commandLineOptions + this.rawText;
	}
}
