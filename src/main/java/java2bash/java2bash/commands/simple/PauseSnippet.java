package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.List;

import java2bash.java2bash.TemplateEngine;
import java2bash.java2bash.commands.Snippet;

public class PauseSnippet implements Snippet {

	@Override
	public String getCode() {
		return "pause";
	}

	@Override
	public String getCleanupCode() {
		return null;
	}

	@Override
	public List<String> getUniqueCode() {
		return Arrays.asList(TemplateEngine.getInstance().render("templates/functions/pause.bash"));
	}
}
