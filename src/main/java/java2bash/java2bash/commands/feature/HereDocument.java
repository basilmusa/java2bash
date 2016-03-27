package java2bash.java2bash.commands.feature;

import java.util.HashMap;
import java.util.Map;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.common.TemplateEngine;

public class HereDocument extends AbstractSnippet {
	
	public HereDocument(String outputFile, String fileContents) {
		super();
		this.outputFile = outputFile;
		this.fileContents = fileContents;
	}

	private final String outputFile;
	private final String fileContents;
	
	@Override
	public String getCode() {
		Map<String, Object> context = new HashMap<String, Object>();
		context.put("outputFile", outputFile);
		context.put("fileContents", fileContents);
		return TemplateEngine.getInstance().render(HereDocument.class, context);
	}
}
