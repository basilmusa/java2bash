package java2bash.java2bash.apps;

import java.util.EnumSet;

import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.conditions.IfCommandExists;
import java2bash.java2bash.commands.feature.HereDocument;
import java2bash.java2bash.commands.feature.InstallJavaJDK7UsingAptGet;
import java2bash.java2bash.commands.feature.InstallMaven3;
import java2bash.java2bash.commands.feature.InstallXvfb;
import java2bash.java2bash.commands.feature.UserMustBeRoot;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.commands.yum.Yum;
import java2bash.java2bash.common.BashString;
import java2bash.java2bash.core.BashScript;

/**
 * Hello world!
 *
 */
public class InstallR2D2OnAmazonLinux
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();

    	bashScript.add(new UserMustBeRoot());
    	bashScript.add(Commands.changeToScriptDirectoy());
    	bashScript.add(Commands.text(EnumSet.of(TextStyle.WHITE, TextStyle.RED_BG, TextStyle.BOLD), 
    			"R2D2 - Installation Of Required Packages"));
    	
    	
    	final String XVFB_PACKAGE_NAME = "xorg-x11-server-Xvfb";
		bashScript.add(Yum.isInstalled(
    			XVFB_PACKAGE_NAME, 
    			Commands.text(TextStyle.GREEN, "Package [" + XVFB_PACKAGE_NAME + "] is already installed on this system"), 
    			SnippetCombo.create(
					Commands.text("Package [" + XVFB_PACKAGE_NAME + "] needs to be installed on the system."),
					Commands.continueYesNo(TextStyle.CYAN, "Proceed?"),
					Commands.code("set -x"),
					Commands.code("yum -y install " + XVFB_PACKAGE_NAME),
					Commands.code("set +x")
   					)));
    	
    	// Check java is installed
    	IfCommandExists checkJavaJDKInstalled = new IfCommandExists(
    			"javac", 
    			Commands.text(TextStyle.GREEN, "Java JDK package already installed."),
    			SnippetCombo.create(
    					Commands.text(TextStyle.RED, "Please install Java JDK 7 then run this script again"),
    					Commands.code("exit 1")));
    	bashScript.add(checkJavaJDKInstalled);
    	
    	// Install Maven
    	IfCommandExists guaranteeInstallMaven3 = new IfCommandExists(
    			"mvn", 
    			Commands.text(TextStyle.GREEN, "Maven already installed and available on the system"), 
    			new InstallMaven3());
    	bashScript.add(guaranteeInstallMaven3);
    	
		System.out.println(bashScript.render());
    }
}
