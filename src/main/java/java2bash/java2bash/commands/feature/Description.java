package java2bash.java2bash.commands.feature;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import java2bash.java2bash.TemplateEngine;
import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.common.BashStrings;

public class Description extends AbstractSnippet {
	
	private EnumSet<TextStyle> colors;
	private String description;
	
	public Description(String escapedDesc, TextStyle textStyle) {
		this(escapedDesc, EnumSet.of(textStyle));
	}
	
	public Description(String escapedDesc, EnumSet<TextStyle> textStyle) {
		this.colors = textStyle;
		this.description = escapedDesc;
	}

	@Override
	public String getCode() 
	{
		String bashColor = "";
		for (TextStyle color : this.colors) {
			bashColor += color.getBashVariable();
		}
		
		StringBuilder builder = new StringBuilder(100 + BashStrings.escapeWithDoubleQuotes(this.description).length());
		builder.append("echo -n " + bashColor);
		builder.append("\n");
		builder.append("echo " + BashStrings.escapeWithDoubleQuotes(this.description));
		builder.append("\n");
		builder.append("echo -n " + TextStyle.RESET.getBashVariable());
		builder.append("\n");
		
		return builder.toString();
	}
	
	@Override
	public List<String> getIncludesList() {
		return Arrays.asList(TemplateEngine.getInstance().render("templates/includes/bold-and-normal.bash"));
	}
}
