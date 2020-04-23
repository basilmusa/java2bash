package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.List;

import com.google.common.base.Preconditions;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.common.BashString;
import java2bash.java2bash.common.BashStrings;
import java2bash.java2bash.common.EscapeMethod;
import java2bash.java2bash.common.TemplateEngine;

/**
 * Asks the user for input
 */
public class PromptInput extends AbstractSnippet 
{
	private static final String DEFAULT_VALUE = "";
	
	private final BashString question;

	private final String variableName;
	private final BashString defaultValue;
	
	public PromptInput(BashString question, String variableName, BashString defaultValue) {
		super();
		Preconditions.checkArgument(isAlphaNumeric(variableName), 
			"Variable name should be alphanumeric [" + variableName + "]");
		this.question = question;
		this.variableName = variableName;
		this.defaultValue = defaultValue;
	}
	public PromptInput(String question, String variableName, String defaultValue) {
		this(BashString.createEscaped(question), variableName, BashString.createEscaped(defaultValue));
	}
	
	public PromptInput(BashString question, String variableName) {
		this(question, variableName, BashString.createEscaped(DEFAULT_VALUE));
	}
	public PromptInput(String question, String variableName) {
		this(BashString.createEscaped(question), variableName);
	}
	
	private boolean isAlphaNumeric(String s) {
	    String pattern= "^[a-zA-Z_]{1}[a-zA-Z0-9_]*$";
	        if(s.matches(pattern)){
	            return true;
	        }
	        return false;   
	}
	/**
	 * Use this method to get the variable name, the variable name should be without any dollar sign $ or braces { ... }
	 * 
	 * @return
	 */
	public String getVariableName() {
		return variableName;
	}

	@Override
	public String getCode() {
		return "read -e -p " + BashString.builder().literal(question).literal(" ").buildRawString() + " -i " + defaultValue.toString() + " " + variableName;
	}
}
