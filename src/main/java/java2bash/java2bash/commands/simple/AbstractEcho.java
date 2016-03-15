package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.List;

import java2bash.java2bash.TemplateEngine;
import java2bash.java2bash.commands.Snippet;

public abstract class AbstractEcho implements Snippet 
{
	protected String description;

	public AbstractEcho() {
		super();
	}

	@Override
	public String getCleanupCode() {
		return null;
	}

	@Override
	public List<String> getUniqueCode() {
		return Arrays.asList(TemplateEngine.getInstance().render("templates/includes/bold-and-normal.bash"));
	}

}