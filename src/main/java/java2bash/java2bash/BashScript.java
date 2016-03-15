package java2bash.java2bash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java2bash.java2bash.commands.Snippet;

public class BashScript 
{
	private List<Snippet> snippets = new ArrayList<>();
	
	public BashScript() {
	}
	
	public void add(final Snippet snippet) {
		this.snippets.add(snippet);
	}
	
	public String render() 
	{
		StringBuilder codeBuilder = new StringBuilder(4096);
		StringBuilder cleanupBuilder = new StringBuilder(4096);
		
		// Run through all snippets
		for (Snippet snippet : snippets) {
			if (snippet.getCleanupCode() != null) {
				cleanupBuilder.append(snippet.getCleanupCode());
			}
			if (snippet.getCode() != null) {
				codeBuilder.append(snippet.getCode());
			}
		}
		
		// Context object
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("cleanupCode", cleanupBuilder.toString());
		context.put("code", codeBuilder.toString());
		context.put("uniqueCode", "# INCLUDES HERE");
		
		// Rendering here
		return TemplateEngine.getInstance().render("templates/bashscript.bash", context);
	}
	
}
