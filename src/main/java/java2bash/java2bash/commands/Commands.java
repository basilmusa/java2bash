package java2bash.java2bash.commands;

import java.util.EnumSet;

import java2bash.java2bash.commands.feature.ChangeToScriptDirectoryBeforeExecution;
import java2bash.java2bash.commands.feature.HereDocument;
import java2bash.java2bash.commands.feature.UserMustBeRoot;
import java2bash.java2bash.commands.simple.ChangeColor;
import java2bash.java2bash.commands.simple.ContinueYesNo;
import java2bash.java2bash.commands.simple.Echo;
import java2bash.java2bash.commands.simple.PromptInput;
import java2bash.java2bash.commands.simple.SimpleCodeSnippet;
import java2bash.java2bash.commands.simple.Text;
import java2bash.java2bash.commands.simple.Echo.Options;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.common.BashString;
import java2bash.java2bash.common.BashStrings;
import java2bash.java2bash.common.EscapeMethod;

public class Commands {
	
	/**
	 * Simply write bash code in raw format as passed.
	 * 
	 * @param commandLine
	 * @return
	 */
	public static Snippet code(String commandLine) {
		return new SimpleCodeSnippet(commandLine);
	}
	
	/**
	 * Feature to make the script change to script directory.
	 * 
	 * @return
	 */
	public static ChangeToScriptDirectoryBeforeExecution changeToScriptDirectoy() {
		return new ChangeToScriptDirectoryBeforeExecution();
	}
	
	/**
	 * Used to create a here document in the script and save it into a file
	 * {@inheritDoc HereDocument}
	 * 
	 * @return HereDocument
	 */
	public static HereDocument hereDocument(String outputFile, String fileContents) {
		return new HereDocument(outputFile, fileContents);
	}
	
	/**
	 * Adds a check that a user must be root
	 * @return
	 */
	public static UserMustBeRoot userMustBeRoot() {
		return new UserMustBeRoot();
	}
	
	/**
	 * Color changing 
	 * 
	 * @param textStyle
	 * @return
	 */
	public static ChangeColor changeColor(TextStyle textStyle) {
		return new ChangeColor(textStyle);
	}
	public static ChangeColor changeColor(TextStyle textStyle1, TextStyle textStyle2) {
		return new ChangeColor(textStyle1, textStyle2);
	}
	public static ChangeColor changeColor(EnumSet<TextStyle> enumSet) {
		return new ChangeColor(enumSet);
	}
	public static ChangeColor changeColorReset() {
		return new ChangeColor(EnumSet.of(TextStyle.BLACK_BG, TextStyle.RESET, TextStyle.CLEAR_TO_EOL));
	}
	
	/**
	 * Echo methods raw here
	 * 
	 * @param rawText
	 * @return
	 */
	public static Echo echo(String rawText) {
		return new Echo(rawText);
	}
	public static Echo echo(String rawText, Options option) {
		return new Echo(rawText, option);
	}
	public static Echo echo(String rawText, EnumSet<Options> options) {
		return new Echo(rawText, options);
	}

	/**
	 * This will simply ask with the string "Proceed?"
	 * @return
	 */
	public static ContinueYesNo continueYesNo() {
		return new ContinueYesNo();
	}

	/**
	 * This will simply ask with the string "Proceed?" with the color specified
	 * 
	 * @return
	 */
	public static Snippet continueYesNo(TextStyle textStyle) {
		return SnippetCombo.create(
				Commands.changeColor(textStyle),
				new ContinueYesNo(),
				Commands.changeColorReset());
	}
	
	/**
	 * The string will be escaped internally using {@link BashStrings#escapeAndSurroundDoubleQuotes(String)}
	 * If you need a raw string use the over-rided method.
	 * @param question
	 * @return
	 */
	public static ContinueYesNo continueYesNo(String question) {
		return new ContinueYesNo(question, EscapeMethod.DOUBLE_QUOTES);
	}
	
	public static Snippet continueYesNo(TextStyle textStyle, String question) {
		return SnippetCombo.create(
			Commands.changeColor(textStyle),
			new ContinueYesNo(question, EscapeMethod.DOUBLE_QUOTES),
			Commands.changeColorReset());
	}
	
	public static ContinueYesNo continueYesNo(String question, EscapeMethod escapeMethod) {
		return new ContinueYesNo(question);
	}

	/**
	 * Raw text to echo
	 * 
	 * @param rawString
	 * @return
	 */
	public static Text textRaw(String rawString) {
		return Text.createRaw(rawString);
	}
	
	/**
	 * This will print text that gets escaped with double quotes, if you need raw text
	 * use {@link #textRaw(String)}
	 * 
	 * @param toEscape
	 * @return
	 */
	public static Text text(String toEscape) {
		return Text.createEscaped(toEscape);
	}
	
	public static Text text(BashString bashString) {
		return Text.create(bashString);
	}
	
	public static Snippet text(TextStyle textStyle, BashString toEscape) {
		return text(EnumSet.of(textStyle), toEscape);
	}

	/**
	 * Text with color
	 * @param toEscape
	 * @param textStyle
	 * @return
	 */
	public static Snippet text(TextStyle textStyle, String toEscape) {
		return text(EnumSet.of(textStyle), toEscape);
	}

	public static Snippet text(EnumSet<TextStyle> styles, String toEscape) {
		return text(styles, BashString.createEscaped(toEscape));
	}
	
	public static Snippet text(EnumSet<TextStyle> styles, BashString bashString) {
		return SnippetCombo.create(
				Commands.changeColor(styles),
				Text.create(bashString),
				Commands.changeColorReset());
	}


	/**
	 * Ask a question here please
	 * 
	 * @param question
	 * @param variableName
	 * @return
	 */
	public static PromptInput promptInput(String question, String variableName) {
		return new PromptInput(question, variableName);
	}
	
	public static PromptInput promptInput(String question, String variableName, String defaultValue) {
		return new PromptInput(question, variableName, defaultValue);
	}
	
}
