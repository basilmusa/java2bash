package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.List;

import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.common.BashStrings;
import java2bash.java2bash.common.EscapeMethod;
import java2bash.java2bash.common.TemplateEngine;

public class ContinueYesNo implements Snippet 
{
	private static final String DEFAULT_QUESTION = "Proceed?";
	
	private final String question;
	private final EscapeMethod escapeMethod;

	public ContinueYesNo() {
		this(DEFAULT_QUESTION, EscapeMethod.DOUBLE_QUOTES);
	}
			
	public ContinueYesNo(String question) {
		this(question, EscapeMethod.DOUBLE_QUOTES);
	}

	public ContinueYesNo(String question, EscapeMethod escapeMethod) {
		this.question = question;
		this.escapeMethod = escapeMethod;
	}

	
	@Override
	public String getCode() {
		return "pause " + this.escapeMethod.escapeString(this.question);
	}

	@Override
	public String getCleanupCode() {
		return null;
	}

	@Override
	public List<String> getIncludesList() {
		return Arrays.asList(TemplateEngine.getInstance().render("templates/functions/pause.bash"));
	}
}
