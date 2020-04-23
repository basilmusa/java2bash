package java2bash.java2bash.apps;

import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.common.BashString;
import java2bash.java2bash.core.BashScript;

import java.util.EnumSet;

public class SakaniCreateUser {
	public static void main(String[] args) {
		BashScript bashScript = new BashScript();

		bashScript.add(Commands.text(EnumSet.of(TextStyle.RED_BG, TextStyle.WHITE),
				"\n=======================================" +
						"\nSCRIPT TO CREATE JUMP NODE USER ACCOUNT" +
						"\n======================================="));


		bashScript.add(Commands.text(TextStyle.GREEN,
				"The username to create that will be able to SSH into the system."));
		bashScript.add(Commands.promptInput("Username: ", "NEW_USERNAME"));

		bashScript.add(Commands.text(TextStyle.GREEN,
				"Please provide the public key to create."));
		bashScript.add(Commands.promptInput("Public Key: ", "PUBLICKEY"));

		System.out.println(bashScript.render());
	}
}
