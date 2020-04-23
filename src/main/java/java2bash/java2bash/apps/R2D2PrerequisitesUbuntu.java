package java2bash.java2bash.apps;

import java.util.EnumSet;
import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.conditions.IfCommandExists;
import java2bash.java2bash.commands.feature.InstallMaven3;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.core.BashScript;

/**
 * Hello world!
 *
 */
public class R2D2PrerequisitesUbuntu 
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();

    	// Initial checks
    	bashScript.add(Commands.userMustBeRoot());
    	bashScript.add(Commands.changeToScriptDirectoy());
    	bashScript.add(Commands.text(EnumSet.of(TextStyle.WHITE, TextStyle.RED_BG, TextStyle.BOLD),
    			"R2D2 - Installation Of Required Packages"));

    	// Install Xvfb - Ubuntu
    	IfCommandExists guaranteeInstallXvfb = new IfCommandExists(
    			"Xvfb", 
    			Commands.text(TextStyle.GREEN, "Xvfb package already installed."),
    			SnippetCombo.create(
					Commands.text(TextStyle.BOLD, "This script will now install 'Xvfb' package."),
					Commands.continueYesNo(TextStyle.CYAN),
					Commands.code("set -x"),
					Commands.code("apt-get -qq -y update"),
					Commands.code("apt-get -qq -y install xvfb"),
					Commands.code("apt-get -qq -y install xorg"),
					Commands.code("apt-get -qq -y install dbus-x11"),
					Commands.code("apt-get -qq -y install xfonts-100dpi"),
					Commands.code("apt-get -qq -y install xfonts-75dpi"),
					Commands.code("apt-get -qq -y install xfonts-cyrillic"),
					Commands.code("set +x")
   					));
    	bashScript.add(guaranteeInstallXvfb);

    	// Install Java JDK 7 - Ubuntu using AptGet
    	IfCommandExists guaranteeInstallJava = new IfCommandExists(
    			"javac", 
    			Commands.text(TextStyle.GREEN, "[oracle-java7-installer] JDK package already installed."), 
    			SnippetCombo.create(
    					Commands.text(TextStyle.BOLD, "This script will now install 'oracle-java7-installer' package."),
    					Commands.continueYesNo(TextStyle.CYAN),
    					Commands.code("apt-get install python-software-properties"),
    					Commands.code("add-apt-repository ppa:webupd8team/java"),
    					Commands.code("apt-get update"),
    					Commands.code("apt-get install oracle-java7-installer")
    					));
    	bashScript.add(guaranteeInstallJava);
    	
    	// Install firefox
    	IfCommandExists guaranteeInstallFirefox = new IfCommandExists(
    			"firefox", 
    			Commands.text(TextStyle.GREEN, "[firefox] package already installed."),
    			SnippetCombo.create(
					Commands.text(TextStyle.BOLD, "This script will now install 'firefox' package."),
					Commands.continueYesNo(TextStyle.CYAN),
					Commands.code("apt-get -qq -y update"),
					Commands.code("apt-get -qq -y firefox")
   					));

    	bashScript.add(guaranteeInstallFirefox);
    	
    	// Install Maven
    	IfCommandExists guaranteeInstallMaven3 = new IfCommandExists(
    			"mvn", 
    			Commands.text(TextStyle.GREEN, "Maven already installed and available on the system"), 
    			new InstallMaven3());
    	bashScript.add(guaranteeInstallMaven3);
    	
    	// All well
    	bashScript.add(Commands.text(TextStyle.GREEN, "All packages have been setup successfully."));
    	
		System.out.println(bashScript.render());
    }
}
