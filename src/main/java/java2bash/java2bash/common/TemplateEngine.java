package java2bash.java2bash.common;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import java2bash.java2bash.commands.feature.HereDocument;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.ExtensionRegistry;
import com.mitchellbosecke.pebble.extension.escaper.EscapingStrategy;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

/**
 * This will be used in specific commands that will need templating.
 * Uses the Pebble engine. 
 *
 */
public class TemplateEngine 
{
	private PebbleEngine resourcesEngine;
	
	private static TemplateEngine INSTANCE = null;
	
	public static TemplateEngine getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new TemplateEngine();
		}
		return INSTANCE;
	}

	private TemplateEngine() {
		
		resourcesEngine = new PebbleEngine.Builder()
			.addEscapingStrategy("bashDoubleQuotes", new EscapingStrategy() {
				@Override
				public String escape(String input) {
					return BashStrings.escapeAndSurroundDoubleQuotes(input);
				}
			})
			.addEscapingStrategy("bashSingleQuotes", new EscapingStrategy() {
				@Override
				public String escape(String input) {
					return BashStrings.escapeAndSurroundSingleQuotes(input);
				}
			})
			.autoEscaping(false)
			.build();
	}

	/**
	 * Convenient method that gets passed a class and returns the resource for that class
	 * This will by default search for the class name appended to it ".bash"
	 * For example, {@link HereDocument} will have a getName() of
	 * 
	 *     java2bash.java2bash.commands.feature.HereDocument
	 *  
	 * The corresponding resource will be:
	 * 
	 *     'java2bash/java2bash/commands/feature/HereDocument.bash'
	 *     
	 * @param clazz
	 * @param context
	 * @return
	 */
	public String render(Class clazz, Map<String, Object> context) {
		return render(clazz, "bash", context);
	}
	
	/**
	 * 
	 * @param clazz
	 * @param extension Example "bash" will append to the class name's path the string ".bash"
	 * @param context
	 * @return
	 */
	public String render(Class clazz, String extension, Map<String, Object> context) {
		return render(clazz.getName().replace('.', '/') + "." + extension, context);
	}
	
	/**
	 * This method takes a templatePath which is the name of the resource in the resources directory
	 * and uses Pebble engine to fill in the variables
	 * 
	 * @param templatePath
	 * @param context
	 * @return
	 */
	public String render(String templatePath, Map<String, Object> context)
	{
		try 
		{
			PebbleTemplate compiledTemplate = resourcesEngine.getTemplate(templatePath);
			
			Writer writer = new StringWriter();
			compiledTemplate.evaluate(writer, context);
			return writer.toString();
			
		} catch (PebbleException e) {
			throw new RuntimeException(e);
		} catch (IOException e) { 
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Just to load a resource file without any evaluation of variables.
	 * 
	 * @param templatePath
	 * @return
	 */
	public String render(String templatePath)
	{
		try 
		{
			PebbleTemplate compiledTemplate = resourcesEngine.getTemplate(templatePath);
			Writer writer = new StringWriter();
			compiledTemplate.evaluate(writer);
			return writer.toString();
			
		} catch (PebbleException e) {
			throw new RuntimeException(e);
		} catch (IOException e) { 
			throw new RuntimeException(e);
		}
	}
}
