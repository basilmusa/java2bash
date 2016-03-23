package java2bash.java2bash;

import java.util.EnumSet;

import com.google.common.base.CharMatcher;
import com.google.common.base.Strings;

import java2bash.java2bash.commands.feature.Description;
import java2bash.java2bash.commands.simple.TextStyle;
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
    	
    	bashScript.add(new Description(
    			"Deployment Automation Checklist", EnumSet.of(TextStyle.WHITE, TextStyle.RED_BG, TextStyle.BOLD)));
    	bashScript.add(new Description(
   			"Will now pull the latest git using 'git checkout' and pull them well, then I will try my best to create a repository synchronized. Thats all there is to have this working correctly or not. Hope this works.", TextStyle.CYAN));
    	bashScript.add(new PauseSnippet());
    	
		System.out.println(bashScript.render());
    }
}
