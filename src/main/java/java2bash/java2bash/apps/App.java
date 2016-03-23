package java2bash.java2bash.apps;

import java.util.EnumSet;

import java2bash.java2bash.BashScript;
import java2bash.java2bash.commands.conditions.IfCommandExists;
import java2bash.java2bash.commands.feature.Description;
import java2bash.java2bash.commands.feature.InstallJavaJDK7UsingAptGet;
import java2bash.java2bash.commands.feature.InstallMaven3;
import java2bash.java2bash.commands.feature.InstallXvfb;
import java2bash.java2bash.commands.simple.TextStyle;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();

    	// R2D2 Package
    	bashScript.add(new Description(
    			"R2D2 - Installation Of Required Packages", EnumSet.of(TextStyle.WHITE, TextStyle.RED_BG, TextStyle.BOLD)));

    	// Install Xvfb - Ubuntu
    	IfCommandExists guaranteeInstallXvfb = new IfCommandExists(
    			"Xvfb", 
    			new Description("Xvfb package already installed.", TextStyle.GREEN), 
    			new InstallXvfb());
    	bashScript.add(guaranteeInstallXvfb);

    	// Install Java 7 - Ubuntu
    	IfCommandExists guaranteeInstallJava = new IfCommandExists(
    			"javac", 
    			new Description("Java JDK package already installed.", TextStyle.GREEN), 
    			new InstallJavaJDK7UsingAptGet());
    	bashScript.add(guaranteeInstallJava);
    	
    	// Install Maven
    	IfCommandExists guaranteeInstallMaven3 = new IfCommandExists(
    			"mvn", 
    			new Description("Maven already installed and available on the system", TextStyle.GREEN), 
    			new InstallMaven3());
    	bashScript.add(guaranteeInstallMaven3);
    	
    	
		System.out.println(bashScript.render());
    }
}
