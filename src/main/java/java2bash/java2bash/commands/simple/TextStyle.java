package java2bash.java2bash.commands.simple;

/**
 * Color Codes use tput:
 * http://www.tldp.org/HOWTO/Bash-Prompt-HOWTO/x405.html
 *
 */
public enum TextStyle {
	BLACK("$(tput setaf 0)"),
	RED("$(tput setaf 1)"),
	GREEN("$(tput setaf 2)"),
	YELLOW("$(tput setaf 3)"),
	BLUE("$(tput setaf 4)"),
	PURPLE("$(tput setaf 5)"),
	CYAN("$(tput setaf 6)"),
	WHITE("$(tput setaf 7)"),

	BLACK_BG("$(tput setab 0)"),
	RED_BG("$(tput setab 1)"),
	GREEN_BG("$(tput setab 2)"),
	YELLOW_BG("$(tput setab 3)"),
	BLUE_BG("$(tput setab 4)"),
	PURPLE_BG("$(tput setab 5)"),
	CYAN_BG("$(tput setab 6)"),
	WHITE_BG("$(tput setab 7)"),
	
	DIM("$(tput dim)"),
	REVERSE("$(tput rev)"),
	CLEAR_SCREEN("$(tput clear)"),
	
	UP_ONE_LINE("$(tput cuu1)"),
	
	BOLD("$(tput bold)"),
	UNDERLINE("$(tput smul)"),
	UNDERLINE_OFF("$(tput rmul)"),
	CLEAR_TO_EOL("$(tput el)"),
	CLEAR_TILL_EOL("$'\\x1B[K'"), // To handle trailing background colors in new lines
	RESET("$(tput sgr0)");
	
	private String bashVariable;

	private TextStyle(String bashVariable) {
		this.bashVariable = bashVariable;
	}
	
	public String getBashVariable() {
		return bashVariable;
	}
}
