package java2bash.java2bash.commands.yum;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.common.BashString;
import java2bash.java2bash.common.Strtr;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

public class YumIsInstalled implements Snippet {
	
	final static String FUNCTION_NAME_IS_INSTALLED = "_java2bash_yum_isinstalled_";

	final BashString packageName;
	final Snippet actionInstalled;
	final Snippet actionNotInstalled;

	/**
	 * 
	 * @param packageName
	 * @param actionInstalled
	 * @param actionNotInstalled
	 */
	public YumIsInstalled(BashString packageName, Snippet actionInstalled, Snippet actionNotInstalled) {
		super();
		this.packageName = packageName;
		this.actionInstalled = actionInstalled == null ? Snippet.NOOP : actionInstalled;
		this.actionNotInstalled = actionNotInstalled == null ? Snippet.NOOP : actionNotInstalled;
	}

	@Override
	public String getCode() {
		final String TEMPLATE = "if " + FUNCTION_NAME_IS_INSTALLED + " {{PACKAGE_NAME}}; then \n"
				+ "{{ACTION_INSTALLED}}\n"
				+ "else\n"
				+ "{{ACTION_NOT_INSTALLED}}\n"
				+ "fi";
		
		// Maps.immutableEntry(, packageName.toString()).
		Map<String, String> replacements = ImmutableMap.<String, String>builder()
			.put("{{PACKAGE_NAME}}", packageName.toString())
			.put("{{ACTION_INSTALLED}}", actionInstalled.getCode())
			.put("{{ACTION_NOT_INSTALLED}}", actionNotInstalled.getCode())
			.build();
		
		return Strtr.replaceStringUsingMap(TEMPLATE, replacements);
	}

	@Override
	public String getCleanupCode() {
		StringBuilder cleanupCode = new StringBuilder();
		prepareCleanupCode(cleanupCode, actionInstalled);
		prepareCleanupCode(cleanupCode, actionNotInstalled);
		
		return Strings.emptyToNull(cleanupCode.toString());
	}

	private void prepareCleanupCode(StringBuilder cleanupCode, Snippet snippet) {
		if (null != snippet.getCleanupCode()) {
			cleanupCode.append(snippet.getCleanupCode());
			cleanupCode.append('\n');
		}
	}

	final static String isInstalledFunction = "function " + FUNCTION_NAME_IS_INSTALLED + " {\n" + 
			"  if yum list installed \"$@\" >/dev/null 2>&1; then\n" + 
			"    true\n" + 
			"  else\n" + 
			"    false\n" + 
			"  fi\n" + 
			"}";

	@Override
	public List<String> getIncludesList() {

		List<String> includes = new ArrayList<String>();
		
		includes.addAll(actionInstalled.getIncludesList());
		includes.addAll(actionNotInstalled.getIncludesList());
		includes.add(isInstalledFunction);
		
		return includes;
	}
}
