package java2bash.java2bash.commands.conditions;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.feature.Description;
import java2bash.java2bash.commands.simple.TextStyle;

/**
 * Script must be run as root
 *
 */
public class UserMustBeRoot extends SnippetCombo {
	
	public UserMustBeRoot() {
		this.addLine("if ! [ $(id -u) = 0 ]; then");
		this.add(new Description("This script should be run as 'root' user.", TextStyle.RED));
		this.addLine("exit 1");
		this.addLine("fi");
	}
}
