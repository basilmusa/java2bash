package java2bash.java2bash.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract snippet that returns default empty implementation of snippets.
 * To save time implementing the {@link Snippet} interface. Just
 * override the methods you want and leave what you dont want.
 * 
 */
public abstract class AbstractSnippet implements Snippet {
	
	private List<String> EMPTY_LIST = new ArrayList<>(0);
	
	@Override
	public String getCleanupCode() {
		return null;
	}
	
	@Override
	public String getCode() {
		return null;
	}

	@Override
	public List<String> getIncludesList() {
		return EMPTY_LIST;
	}
}
