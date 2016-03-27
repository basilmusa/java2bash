package java2bash.java2bash.commands.conditions;

import java.util.HashMap;
import java.util.Map;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.common.TemplateEngine;

public class IsProcessNameRunning extends AbstractSnippet 
{
	private final String processName;
	private final String rawExecuteIfNotRunning;
	
	public IsProcessNameRunning(String processName, String rawExecuteIfNotRunning) {
		super();
		this.processName = processName;
		this.rawExecuteIfNotRunning = rawExecuteIfNotRunning;
	}

	@Override
	public String getCode() {
		
		Map<String, Object> context = new HashMap<>();
		context.put("processName", this.processName);
		context.put("PROCESS_NOT_RUNNING", rawExecuteIfNotRunning);
		return TemplateEngine.getInstance().render(IsProcessNameRunning.class, context);
	}
}
