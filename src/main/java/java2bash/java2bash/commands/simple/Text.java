package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.common.BashString;
import java2bash.java2bash.common.BashStrings;
import java2bash.java2bash.common.TemplateEngine;

public class Text extends AbstractSnippet {

	public static Text create(BashString bashString) {
		return new Text(bashString);
	}
	
	public static Text createRaw(String rawString) {
		return new Text(BashString.createRaw(rawString));
	}
	
	public static Text createEscaped(String toEscape) {
		return new Text(BashString.createEscaped(toEscape));
	}
	
	private BashString description;
	
	public Text(BashString bashString) {
		this.description = bashString;
	}
	
	@Override
	public String getCode() 
	{
		return "echo " + this.description.toString();
	}
}
