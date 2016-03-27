package java2bash.java2bash.commands.conditions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.common.TemplateEngine;

public class IfCommandExists extends AbstractSnippet 
{
	private SnippetCombo snippetCombo;
	
	/*
	 * Provides the true action if command exists, or false action
	 * if command does not exist.
	 */
	public IfCommandExists(String commandName, Snippet trueAction,
			Snippet falseAction) {
		super();
		this.commandName = commandName;
		this.trueAction = trueAction;
		this.falseAction = falseAction;
		
		snippetCombo = new SnippetCombo();
		snippetCombo.add(this.trueAction);
		snippetCombo.add(this.falseAction);
	}

	final String commandName;
	final Snippet trueAction;
	final Snippet falseAction;

	@Override
	public String getCode() {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("commandName", this.commandName);
		context.put("TRUE_ACTION", this.trueAction.getCode());
		context.put("FALSE_ACTION", this.falseAction.getCode());
		return TemplateEngine.getInstance().render(IfCommandExists.class, context);
	}

	@Override
	public List<String> getIncludesList() {
		List<String> includes = new ArrayList<String>();
		includes.addAll(snippetCombo.getIncludesList());
		includes.add(TemplateEngine.getInstance().render("templates/includes/command-exists.bash"));
		return includes;
	}
}