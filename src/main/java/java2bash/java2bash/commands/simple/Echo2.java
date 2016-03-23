package java2bash.java2bash.commands.simple;

import java.util.EnumSet;


public class Echo2 extends AbstractEcho {
	
	private EnumSet<Color> colors;
	
	public Echo2(String description, Color color) {
		this(description, EnumSet.of(color));
	}
	
	public Echo2(String description, EnumSet<Color> colors) {
		this.colors = colors;
		this.description = description;
	}

	@Override
	public String getCode() 
	{
		String bashColor = "";
		for (Color color : this.colors) {
			bashColor += color.getBashVariable();
		}
		
		return "echo "+bashColor+"\"" + description + "\"" + Color.RESET.getBashVariable() + "";
	}
}
