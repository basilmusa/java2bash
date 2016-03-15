package java2bash.java2bash;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java2bash.java2bash.commands.SimpleCodeSnippet;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

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
    	
		System.out.println(bashScript.render());
    }
}
