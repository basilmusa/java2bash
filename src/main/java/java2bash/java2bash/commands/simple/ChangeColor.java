package java2bash.java2bash.commands.simple;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.Snippet;
import java2bash.java2bash.common.TemplateEngine;

public class ChangeColor extends AbstractSnippet {

	private final EnumSet<TextStyle> enumSet;
	
	public ChangeColor(TextStyle enumSet) {
		this(EnumSet.of(enumSet));
	}
	
	public ChangeColor(TextStyle enumSet1, TextStyle enumSet2) {
		this(EnumSet.of(enumSet1, enumSet2));
	}
	
	public ChangeColor(EnumSet<TextStyle> enumSet) {
		super();
		this.enumSet = enumSet;
	}

	@Override
	public String getCode() {
		String str = "echo -n ";
		for (TextStyle textStyle : enumSet) {
			str += textStyle.getBashVariable();
		}
		return str;
	}

	@Override
	public List<String> getIncludesList() {
		return Arrays.asList(TemplateEngine.getInstance().render("templates/includes/bold-and-normal.bash"));
	}

}
