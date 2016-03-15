package java2bash.java2bash;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

/**
 * This will be used in specific commands that will need templating. 
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
		resourcesEngine = new PebbleEngine.Builder().autoEscaping(false).build();
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
}
