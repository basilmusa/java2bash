package java2bash.java2bash.commands.conditions;

import java.util.List;
import java.util.Map;
import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.common.Strtr;

import com.google.common.collect.ImmutableMap;

public class IfCondition implements Snippet {
	
	final String condition;
	final Snippet trueAction;
	final Snippet falseAction;
	
	// This is used just to evaluate clean up and includes
	final SnippetCombo combo;

	/**
	 * 
	 * @param condition
	 * @param trueAction
	 * @param falseAction
	 */
	public IfCondition(String condition, Snippet trueAction, Snippet falseAction) {
		super();
		this.condition = condition;
		this.trueAction = trueAction == null ? Snippet.NOOP : trueAction;
		this.falseAction = falseAction == null ? Snippet.NOOP : falseAction;
		combo = SnippetCombo.create(trueAction, falseAction);
	}

	@Override
	public String getCode() {
		final String TEMPLATE = "if {{CONDITION}}; then \n"
				+ "{{ACTION_INSTALLED}}\n"
				+ "else\n"
				+ "{{ACTION_NOT_INSTALLED}}\n"
				+ "fi";
		
		// Maps.immutableEntry(, packageName.toString()).
		Map<String, String> replacements = ImmutableMap.<String, String>builder()
			.put("{{CONDITION}}", condition.toString())
			.put("{{ACTION_INSTALLED}}", trueAction.getCode())
			.put("{{ACTION_NOT_INSTALLED}}", falseAction.getCode())
			.build();
		
		return Strtr.replaceStringUsingMap(TEMPLATE, replacements);
	}

	@Override
	public String getCleanupCode() {
		return combo.getCleanupCode();
	}

	@Override
	public List<String> getIncludesList() {
		return combo.getIncludesList();
	}
}
