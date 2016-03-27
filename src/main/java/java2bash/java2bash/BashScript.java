package java2bash.java2bash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.simple.SimpleCodeSnippet;

public class BashScript 
{
	private SnippetCombo snippetCombo = new SnippetCombo();
	private List<String> includes = new ArrayList<>();
	
	public BashScript() {
	}
	
	public void add(final Snippet snippet) {
		this.snippetCombo.add(snippet);
	}
	
	public void addLine(final String line) {
		this.snippetCombo.addLine(line);
	}
	
	public String render() 
	{
		// Context object
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("cleanupCode", snippetCombo.getCleanupCode());
		context.put("code", snippetCombo.getCode());
		context.put("uniqueCode", snippetCombo.getIncludesCode());
		
		// Rendering here
		return TemplateEngine.getInstance().render(BashScript.class, context);
	}
}
