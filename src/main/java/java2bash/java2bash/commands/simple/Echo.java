package java2bash.java2bash.commands.simple;

import java.util.EnumSet;


public class Echo extends AbstractEcho {
	
	private EnumSet<EchoColor> colors;
	
	public Echo(String description, EchoColor color) {
		this(description, EnumSet.of(color));
	}
	
	public Echo(String description, EnumSet<EchoColor> colors) {
		this.colors = colors;
		this.description = description;
	}

	@Override
	public String getCode() 
	{
		String bashColor = "";
		for (EchoColor color : this.colors) {
			bashColor += color.getBashVariable();
		}
		
		return "echo "+bashColor+"\"" + description + "\"" + EchoColor.RESET.getBashVariable() + "";
	}
}
