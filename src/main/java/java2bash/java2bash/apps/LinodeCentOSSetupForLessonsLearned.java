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
import java2bash.java2bash.common.BashString;
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

//    	bashScript.add(new UserMustBeRoot());
    	bashScript.add(new ChangeToScriptDirectoryBeforeExecution());
    	
    	// Provide a label for the linode
    	bashScript.add(Commands.text(TextStyle.GREEN, "Please go to Linode > Settings > Linode Label and set the name of your server (eg. lessonslearned_3), then click Save Changes."));
    	bashScript.add(Commands.continueYesNo(TextStyle.CYAN));
    	
    	// Deploy CentOS 7 Image
    	bashScript.add(Commands.text(TextStyle.GREEN, "Go to Linode > Dashboard > Deploy an Image, then select 'CentOS 7' and provide a root password. Then click 'Deploy Image'"));
    	bashScript.add(Commands.continueYesNo(TextStyle.CYAN));
    	
    	// Please provide the IP Address 
    	bashScript.add(Commands.text(TextStyle.GREEN, "Provide the IP Address, Hostname and FQDN for your linode from Linodes > Remote Access > Copy IP Address "));
    	bashScript.add(Commands.promptInput("Provide IP Address:", "IP_ADDRESS", "X.X.X.X"));
    	bashScript.add(Commands.promptInput("Provide Hostname:", "HOSTNAME"));
    	bashScript.add(Commands.promptInput("Provide FQDN:", "FQDN"));
    	
    	
    	bashScript.add(Commands.text(TextStyle.GREEN, 
    		BashString.builder()
    			.literal("The IP Address you provided is")
    			.var("IP_ADDRESS")
    			.literal("And how are you doing today?")
    			.buildBashString()));
    	
    	
//    	// yum -y update
//    	bashScript.add(Commands.text(TextStyle.CYAN, "Performing a yum update using 'yum -y update'"));
//    	bashScript.addLine("yum -y update");
//    	
//    	// TODO - Ask for the machine name
//
//     	// TODO - Ask for the FQDN 
    	
		System.out.println(bashScript.render());
    }
}
