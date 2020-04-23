package java2bash.java2bash.commands.feature;

import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.simple.ContinueYesNo;
import java2bash.java2bash.commands.simple.TextStyle;

public class InstallXvfb extends SnippetCombo 
{
	public InstallXvfb() {
    	this.add(Commands.text(TextStyle.BOLD, "This script will now install 'Xvfb' which is required to be able to run firefox and chrome browsers headlessly in memory."));
    	this.add(Commands.continueYesNo(TextStyle.CYAN));
    	this.addLine("apt-get -qq -y update");
    	this.addLine("apt-get -qq -y xvfb");
	}
}
