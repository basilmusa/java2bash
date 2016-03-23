package java2bash.java2bash.commands;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;

/**
 * This allows adding one or more snippets to collect them into one single snippet.
 * Thus merging snippets altogether.
 */
public class SnippetCombo implements Snippet {
	
	private static final char LINE_SEPARATOR = '\n';
	private List<Snippet> snippets = new ArrayList<Snippet>();
	private List<String> includes = new ArrayList<>();
	private boolean dirty = true;
	
	private String code;
	private String cleanupCode;
	private String includeCode;

	/**
	 * Add a snippet to this combo
	 * @param snippet
	 */
	public void add(final Snippet snippet) {
		Preconditions.checkNotNull(snippet);
		this.snippets.add(snippet);
		this.dirty = true;
	}
	
	/**
	 * 
	 * @return
	 */
	private void refreshIfDirty() 
	{
		if (dirty == true) {
			prepareSnippetFields();
			dirty = false;
		}
	}

	/**
	 * Do not call this directly. This method is called by {@link #refreshIfDirty()}.
	 * 
	 */
	private void prepareSnippetFields()
	{
		// Clear the includes array list, and re-add
		includes.clear();
		
		StringBuilder codeBuilder = new StringBuilder(4096);
		StringBuilder cleanupBuilder = new StringBuilder(4096);

		// Run through all snippets
		for (Snippet snippet : snippets) {
			processSnippet(snippet, codeBuilder, cleanupBuilder);
		}
		
		// Set the snippet fields please
		// Note: No need to set includes since it has already been done by processSnippet internally
		this.code = codeBuilder.toString();
		this.cleanupCode = cleanupBuilder.toString();
		
		// Generate include code
		StringBuilder includeBuilder = new StringBuilder(4096);
		for (String include : this.includes) {
			includeBuilder.append(include);
			includeBuilder.append(LINE_SEPARATOR);
		}
		this.includeCode = includeBuilder.toString();
	}

	private void processSnippet(Snippet snippet, StringBuilder codeBuilder, StringBuilder cleanupBuilder) {
		processSnippetCleanupCode(snippet, cleanupBuilder);
		processSnippetCode(snippet, codeBuilder);
		processSnippetIncludes(snippet);
	}

	private void processSnippetIncludes(Snippet snippet) {
		if (!snippet.getIncludesList().isEmpty())
		{
			List<String> uniqueCode = snippet.getIncludesList();
			
			for (String includeCode : uniqueCode) 
			{
				if (includes.contains(includeCode)) {
					continue;
				}
				includes.add(includeCode);
			}
		}
	}

	private void processSnippetCode(Snippet snippet, StringBuilder codeBuilder) {
		if (snippet.getCode() != null) {
			codeBuilder.append(snippet.getCode());
			codeBuilder.append(LINE_SEPARATOR);
		}
	}

	private void processSnippetCleanupCode(Snippet snippet,
			StringBuilder cleanupBuilder) {
		if (snippet.getCleanupCode() != null) {
			cleanupBuilder.append(snippet.getCleanupCode());
			cleanupBuilder.append(LINE_SEPARATOR);
		}
	}

	@Override
	public String getCode() {
		this.refreshIfDirty();
		return this.code;
	}

	@Override
	public String getCleanupCode() {
		this.refreshIfDirty();
		return this.cleanupCode;
	}

	@Override
	public List<String> getIncludesList() {
		this.refreshIfDirty();
		return this.includes;
	}
	
	public String getIncludesCode() {
		this.refreshIfDirty();
		return this.includeCode;
	}
}
