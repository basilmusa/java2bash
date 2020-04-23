package java2bash.java2bash.commands.simple;

import java.util.EnumSet;
import java2bash.java2bash.commands.AbstractSnippet;

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
}
