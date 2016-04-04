package java2bash.java2bash.commands.yum;

import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.common.BashString;

public final class Yum {
	
	/**
	 * Incase you dont need to escape the pacakge, this version will handle a packageName with
	 * bash variables (as an example).
	 * 
	 * @param packageName
	 * @param actionInstalled
	 * @param actionNotInstalled
	 * @return
	 */
	public static Snippet isInstalled(BashString packageName, Snippet actionInstalled, Snippet actionNotInstalled) {
		return new YumIsInstalled(packageName, actionInstalled, actionNotInstalled);
	}
	
	/**
	 * packageName here will be automatically escaped
	 * 
	 * @param packageName
	 * @param actionInstalled
	 * @param actionNotInstalled
	 * @return
	 */
	public static Snippet isInstalled(String packageName, Snippet actionInstalled, Snippet actionNotInstalled) {
		return isInstalled(BashString.createEscaped(packageName), actionInstalled, actionNotInstalled);
	}
}
