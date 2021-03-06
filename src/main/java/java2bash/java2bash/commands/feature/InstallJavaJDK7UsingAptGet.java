package java2bash.java2bash.commands.feature;

import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.simple.ContinueYesNo;
import java2bash.java2bash.commands.simple.TextStyle;

public class InstallJavaJDK7UsingAptGet extends SnippetCombo {
	public InstallJavaJDK7UsingAptGet() {
		this.add(Commands.changeColor(TextStyle.CYAN));
    	this.add(Commands.text("Proceeding will install Java JDK 7 Oracle."));
    	this.add(Commands.changeColorReset());
    	
    	this.add(new ContinueYesNo());
    	this.addLine("apt-get install python-software-properties");
    	this.addLine("add-apt-repository ppa:webupd8team/java");
    	this.addLine("apt-get update");
    	this.addLine("apt-get install oracle-this-installer");
	}
}
