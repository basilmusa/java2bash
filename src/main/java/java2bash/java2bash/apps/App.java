package java2bash.java2bash.apps;

import java.util.EnumSet;
import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.conditions.IfCommandExists;
import java2bash.java2bash.commands.feature.HereDocument;
import java2bash.java2bash.commands.feature.InstallJavaJDK7UsingAptGet;
import java2bash.java2bash.commands.feature.InstallMaven3;
import java2bash.java2bash.commands.feature.InstallXvfb;
import java2bash.java2bash.commands.feature.UserMustBeRoot;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.core.BashScript;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();

    	// Must be root
    	bashScript.add(new UserMustBeRoot());
    	
    	bashScript.add(new HereDocument("something.txt", "HELLO THERE\nIS THIS WORKING\n"));
    	
    	// R2D2 Package
    	bashScript.add(Commands.changeColor(EnumSet.of(TextStyle.WHITE, TextStyle.RED_BG, TextStyle.BOLD)));
    	bashScript.add(Commands.text(
    			"R2D2 - Installation Of Required Packages"));

    	// Install Xvfb - Ubuntu
    	IfCommandExists guaranteeInstallXvfb = new IfCommandExists(
    			"Xvfb", 
    			Commands.text(TextStyle.GREEN, "Xvfb package already installed."),
    			new InstallXvfb());
    	bashScript.add(guaranteeInstallXvfb);

    	// Install Java JDK 7 - Ubuntu using AptGet
    	IfCommandExists guaranteeInstallJava = new IfCommandExists(
    			"javac", 
    			Commands.text(TextStyle.GREEN, "Java JDK package already installed."), 
    			new InstallJavaJDK7UsingAptGet());
    	bashScript.add(guaranteeInstallJava);
    	
    	// Install Maven
    	IfCommandExists guaranteeInstallMaven3 = new IfCommandExists(
    			"mvn", 
    			Commands.text(TextStyle.GREEN, "Maven already installed and available on the system"), 
    			new InstallMaven3());
    	bashScript.add(guaranteeInstallMaven3);
    	
		System.out.println(bashScript.render());
    }
}
