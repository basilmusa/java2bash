package java2bash.java2bash.commands.simple;

import java2bash.java2bash.commands.AbstractSnippet;

public class SimpleCodeSnippet extends AbstractSnippet 
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
