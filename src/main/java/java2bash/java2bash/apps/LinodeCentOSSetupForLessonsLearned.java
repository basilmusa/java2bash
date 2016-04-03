package java2bash.java2bash.apps;

import java.util.EnumSet;

import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.conditions.IfCommandExists;
import java2bash.java2bash.commands.feature.ChangeToScriptDirectoryBeforeExecution;
import java2bash.java2bash.commands.feature.HereDocument;
import java2bash.java2bash.commands.feature.InstallJavaJDK7UsingAptGet;
import java2bash.java2bash.commands.feature.InstallMaven3;
import java2bash.java2bash.commands.feature.InstallXvfb;
import java2bash.java2bash.commands.feature.UserMustBeRoot;
import java2bash.java2bash.commands.simple.Text;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.core.BashScript;

/**
 * Hello world!
 *
 */
public class LinodeCentOSSetupForLessonsLearned 
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();

    	// Must be root
    	bashScript.add(new UserMustBeRoot());

    	// Change to script directory before execution
    	bashScript.add(new ChangeToScriptDirectoryBeforeExecution());
    	
    	// yum -y update
    	bashScript.add(Commands.text(TextStyle.CYAN, "Performing a yum update using 'yum -y update'"));
    	bashScript.addLine("yum -y update");
    	
    	// TODO - Ask for the machine name

     	// TODO - Ask for the FQDN 
    	
		System.out.println(bashScript.render());
    }
}
