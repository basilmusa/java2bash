package java2bash.java2bash.commands.simple;

public enum Color {
	BLACK("text_black"),
	RED("text_red"),
	GREEN("text_green"),
	YELLOW("text_yellow"),
	BLUE("text_blue"),
	PURPLE("text_purple"),
	CYAN("text_cyan"),
	WHITE("text_white"),

	BLACK_BG("bg_black"),
	RED_BG("bg_red"),
	GREEN_BG("bg_green"),
	YELLOW_BG("bg_yellow"),
	BLUE_BG("bg_blue"),
	PURPLE_BG("bg_purple"),
	CYAN_BG("bg_cyan"),
	WHITE_BG("bg_white"),
	
	BOLD("bold"),
	UNDERLINE("underline"),
	
	RESET("normal");
	
	private String bashVariable;

	private Color(String bashVariable) {
		this.bashVariable = bashVariable;
	}
	
	public String getBashVariable() {
		return "${" + bashVariable + "}";
	}
}
