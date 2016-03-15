package java2bash.java2bash.commands;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractCodeSnippet implements Snippet {
	
	private List<String> EMPTY_LIST = new ArrayList<>(0);

	@Override
	public String getCleanupCode() {
		return null;
	}

	@Override
	public List<String> getUniqueCode() {
		return EMPTY_LIST;
	}
}
