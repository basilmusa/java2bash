package java2bash.java2bash.apps;

import java.util.EnumSet;
import java2bash.java2bash.commands.Commands;
import java2bash.java2bash.commands.feature.ChangeToScriptDirectoryBeforeExecution;
import java2bash.java2bash.commands.feature.UserMustBeRoot;
import java2bash.java2bash.commands.simple.ChangeColor;
import java2bash.java2bash.commands.simple.ContinueYesNo;
import java2bash.java2bash.commands.simple.TextStyle;
import java2bash.java2bash.core.BashScript;

/**
 * Hello world!
 *
 */
public class DeploySolrProduction 
{
    public static void main( String[] args )
    {
    	BashScript bashScript = new BashScript();

    	// Change to script directory before execution
    	bashScript.add(new ChangeToScriptDirectoryBeforeExecution());
    	
    	// Must be root
    	bashScript.add(new UserMustBeRoot());
    	
    	// Solr 
    	bashScript.add(Commands.text(
    			EnumSet.of(TextStyle.WHITE, TextStyle.RED_BG, TextStyle.BOLD),
    			"This Script Will Deploy The Latest Solr Changes"));
    	bashScript.add(new ChangeColor(TextStyle.RESET));
    	
    	// Get the latest changes from git
    	bashScript.add(Commands.text(TextStyle.CYAN, 
    			"The following step will checkout the latest master branch and merge it here by running the following commands:\n"
    			+ "\tgit checkout master\n"
    			+ "\tgit fetch origin\n"
    			+ "\tgit merge origin/master\n"
    			+ ""));
    	bashScript.add(new ContinueYesNo("Would you like to proceed?"));
    	
    	bashScript.addLine("git checkout master");
    	bashScript.addLine("git fetch origin");
    	bashScript.addLine("git merge origin/master");
    	
    	// Check that the database is pointing to the correct server
    	bashScript.add(Commands.text(TextStyle.CYAN, "Please confirm that the MySQL Database URL provided in the configuration file:\n\n"
				+ "\t[server/solr/vanilla/conf/data-config.xml]\n"
				+ "is correct. The MySQL Database URL is:\n"));
    	
    	bashScript.add(new ChangeColor(TextStyle.YELLOW, TextStyle.BOLD));
    	bashScript.addLine("more server/solr/vanilla/conf/data-config.xml | grep 'url=' -A 3 -B 2");
    	bashScript.add(new ChangeColor(TextStyle.RESET));
    	
    	bashScript.add(new ContinueYesNo("Is the database connection URL provided correct?"));
    	
    	//  Restart Solr Service
    	bashScript.add(Commands.text(TextStyle.CYAN, "Will restart Solr service by issuing the following command:\n\n"
    			+ "\tservice solr restart\n"
    			+ ""));
    	bashScript.add(new ContinueYesNo("Press y to restart solr service"));
    	bashScript.addLine("service solr restart");
    	
    	/**
			sleep .5 # Waits 0.5 second.
			sleep 5  # Waits 5 seconds.
			sleep 5s # Waits 5 seconds.
			sleep 5m # Waits 5 minutes.
			sleep 5h # Waits 5 hours.
			sleep 5d # Waits 5 days.
    	 */
    	bashScript.addLine("sleep 5s");
    	bashScript.addLine("echo \"\""); // Just a new line
    	
    	// Perform a full re-index
    	bashScript.add(Commands.text(TextStyle.CYAN, "\nWill perform full reindexing for all Vanilla product information by Solr from the database by issuing the following command:\n\n"
    			+ "\tcurl -v http://localhost:8983/solr/vanilla_core/dataimport?command=full-import\n"
    			+ ""));
    	bashScript.add(new ContinueYesNo("Press y to reindex all products."));
    	bashScript.addLine("curl -v http://localhost:8983/solr/vanilla_core/dataimport?command=full-import");
    	// 
    	bashScript.add(Commands.text(TextStyle.GREEN, "Deployment has been performed successfully. Please wait 5 to 10 minutes for Solr indexes to fully re-index."));
    	
		System.out.println(bashScript.render());
    }
}
