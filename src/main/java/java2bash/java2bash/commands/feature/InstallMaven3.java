package java2bash.java2bash.commands.feature;

import java.util.List;

import java2bash.java2bash.commands.AbstractSnippet;
import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.SnippetCombo;
import java2bash.java2bash.commands.simple.ContinueYesNo;
import java2bash.java2bash.commands.simple.TextStyle;

public class InstallMaven3 extends AbstractSnippet 
{
	private SnippetCombo snippetCombo;

	public InstallMaven3() {
		snippetCombo = new SnippetCombo();
		snippetCombo.add(Commands.changeColor(TextStyle.CYAN));
    	snippetCombo.add(Commands.text("Installing Maven3 at /opt/maven"));
    	snippetCombo.add(Commands.changeColorReset());
    	
    	snippetCombo.add(new ContinueYesNo());
    	snippetCombo.addLine("cd /opt");
    	snippetCombo.addLine("FILE_URL=http://www-us.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz");
    	snippetCombo.addLine("FILE_NAME=$(basename \"$FILE_URL\")");
    	snippetCombo.addLine("rm -f \"$FILE_NAME\"");
    	snippetCombo.addLine("wget \"$FILE_URL\"");
    	snippetCombo.addLine("mkdir maven");
    	snippetCombo.addLine("tar -zxf \"$FILE_NAME\" -C maven --strip-components=1");
    	snippetCombo.addLine("rm -f \"$FILE_NAME\"");
    	snippetCombo.add(new HereDocument("/etc/profile.d/maven.sh", 
    			"export MAVEN_HOME=/opt/maven\n"
    			+ "export PATH=$PATH:$MAVEN_HOME/bin\n"));
    	
    	snippetCombo.add(Commands.changeColor(TextStyle.GREEN));
    	snippetCombo.add(
    		Commands.text("Please reload your /etc/profile file to load /etc/profile.d/maven.sh by running the command:\n\n\t. /etc/profile"));
    	snippetCombo.add(Commands.changeColorReset());
	}
	
	@Override
	public String getCleanupCode() {
		return snippetCombo.getCleanupCode();
	}

	@Override
	public String getCode() {
		return snippetCombo.getCode();
	}

	@Override
	public List<String> getIncludesList() {
		return snippetCombo.getIncludesList();
	}
}
