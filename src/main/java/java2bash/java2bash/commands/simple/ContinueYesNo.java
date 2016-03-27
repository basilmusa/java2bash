package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.List;

import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.common.BashStrings;
import java2bash.java2bash.common.TemplateEngine;

public class ContinueYesNo implements Snippet {

	private static final String DEFAULT_QUESTION = "Would you like to continue?";

	public ContinueYesNo() {
		super();
		this.question = DEFAULT_QUESTION;
	}
			
	public ContinueYesNo(String question) {
		super();
		this.question = question;
	}

	final String question;
	
	@Override
	public String getCode() {
		return "pause " + BashStrings.escapeWithDoubleQuotes(this.question);
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
