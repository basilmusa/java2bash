package java2bash.java2bash.commands.feature;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.simple.TextStyle;

/**
 * Script must be run as root
 *
 */
public class UserMustBeRoot extends SnippetCombo {
	
	public UserMustBeRoot() {
		this.addLine("if ! [ $(id -u) = 0 ]; then");
		this.add(Commands.changeColor(TextStyle.RED));
		this.add(Commands.text("This script should be run as 'root' user."));
		this.add(Commands.changeColorReset());
		this.addLine("exit 1");
		this.addLine("fi");
	}
}
