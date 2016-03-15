package java2bash.java2bash.commands.simple;

import java2bash.java2bash.commands.AbstractCodeSnippet;

public class SimpleCodeSnippet extends AbstractCodeSnippet 
{
	private String code;
	
	public SimpleCodeSnippet(String code) {
		this.code = code;
	}

	@Override
	public String getCode() {
		return code;
	}
}
