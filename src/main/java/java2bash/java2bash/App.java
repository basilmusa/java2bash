package java2bash.java2bash;

import java.util.EnumSet;

import java2bash.java2bash.commands.simple.Echo;
import java2bash.java2bash.commands.simple.EchoColor;
import java2bash.java2bash.commands.simple.PauseSnippet;
import java2bash.java2bash.commands.simple.SimpleCodeSnippet;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();
    	bashScript.add(new SimpleCodeSnippet("echo \"HELLO WORLD!\""));
    	
    	bashScript.add(new Echo(
    			"Deployment Automation Checklist", EnumSet.of(EchoColor.WHITE, EchoColor.RED_BG, EchoColor.BOLD)));
    	bashScript.add(new Echo(
   			"Will now pull the latest git using 'git checkout' and pull them well, then I will try my best to create a repository synchronized. Thats all there is to have this working correctly or not. Hope this works.", EchoColor.CYAN));
    	bashScript.add(new PauseSnippet());
    	
		System.out.println(bashScript.render());
    }
}
