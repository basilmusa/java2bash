package java2bash.java2bash.commands;

import java.util.List;

public interface Snippet {
	/**
	 * The command to execute in the bash script is returned here.
	 * Returns null if no code is to be placed in the script
	 * 
	 * @return 
	 */
	public String getCode();
	
	/**
	 * The cleanup comman if any is returned here
	 * Returns null if no code is to be executed in the trap function.
	 * 
	 * @return
	 */
	public String getCleanupCode();
	
	/**
	 * The unique code, for example a function is returned here.
	 * Or a set of functions to include. Not necessarily a function, it could
	 * be an include variable or something like that.
	 * 
	 * But this guarantees that it will be at the top of the file.
	 * 
	 * Returns empty array if no code is to be included at the top of the file.
	 * 
	 * @return
	 */
	public List<String> getIncludesList();
}
