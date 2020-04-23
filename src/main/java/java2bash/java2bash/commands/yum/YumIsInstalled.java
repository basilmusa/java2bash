package java2bash.java2bash.commands.yum;

import java.util.ArrayList;
import java.util.List;
import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.commands.conditions.IfCondition;
import java2bash.java2bash.common.BashString;

public class YumIsInstalled implements Snippet {

	/*
	 * Constants
	 */
	final static String FUNCTION_NAME_IS_INSTALLED = "_java2bash_yum_isinstalled_";
	final static String isInstalledFunction = "function " + FUNCTION_NAME_IS_INSTALLED + " {\n" + 
			"  if yum list installed \"$@\" >/dev/null 2>&1; then\n" + 
			"    true\n" + 
			"  else\n" + 
			"    false\n" + 
			"  fi\n" + 
			"}";
	
	/*
	 * Member variables
	 */
	final IfCondition ifCondition;

	/**
	 * 
	 * @param packageName
	 * @param actionInstalled
	 * @param actionNotInstalled
	 */
	public YumIsInstalled(BashString packageName, Snippet actionInstalled, Snippet actionNotInstalled) {
		ifCondition = new IfCondition(FUNCTION_NAME_IS_INSTALLED + " " + packageName.toString(), 
				actionInstalled == null ? Snippet.NOOP : actionInstalled, 
				actionNotInstalled == null ? Snippet.NOOP : actionNotInstalled);
	}

	@Override
	public List<String> getIncludesList() {
		List<String> includes = new ArrayList<String>();
		includes.addAll(ifCondition.getIncludesList());
		includes.add(isInstalledFunction);
		return includes;
	}

	@Override
	public String getCode() {
		return ifCondition.getCode();
	}

	@Override
	public String getCleanupCode() {
		return ifCondition.getCleanupCode();
	}
}
