package java2bash.java2bash.commands.feature;

import java.util.Arrays;
import java.util.List;
import java2bash.java2bash.commands.AbstractSnippet;

public class ChangeToScriptDirectoryBeforeExecution extends AbstractSnippet {

	@Override
	public String getCleanupCode() {
		return "cd ${__CURRENT_DIR__}";
	}

	@Override
	public String getCode() {
		// TODO Auto-generated method stub
		return "cd ${__SCRIPT_DIR__}";
	}

	@Override
	public List<String> getIncludesList() {
		String str = "__SCRIPT_DIR__=\"$( cd \"$( dirname \"${BASH_SOURCE[0]}\" )\" && pwd )\"\n"
				+ "__CURRENT_DIR__=`pwd`";
		return Arrays.asList(str);
	}
	
	/**
	 * Returns '${__CURRENT_DIR__}' as a string
	 * @return
	 */
	public String getCurrentDirVarName() {
		return "${__CURRENT_DIR__}";
	}
	
	/**
	 *  Returns ${__SCRIPT_DIR__} as a string
	 * @return
	 */
	public String getScriptDirVarName() {
		return "${__SCRIPT_DIR__}";
	}
}