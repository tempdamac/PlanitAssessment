package utilities;

import java.io.IOException;

import org.testng.annotations.Parameters;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.Log.Console;

public class Hooks {

	public static String featureName;
	public static String scenarioName; 
	Console console = new Console();
	
	
	@Before
	@Parameters({ "BrowserType", "Url" })
	public void beforeScenario(Scenario scenario) throws InterruptedException, IOException  {
		console.setInfo("Getting feature and scenario name...");
		
		String rawFeatureName;
		int index;
		int size;
		StringBuilder str;
		
		
		try {
			//read feature file name under "features" folder
			rawFeatureName = scenario.getId().split("/")[2];
			//Remove character starting at '.feature'
			index = rawFeatureName.indexOf(".feature:");
			size = rawFeatureName.length();
			str  = new StringBuilder(rawFeatureName);
			rawFeatureName = str.replace(index, size, "").toString();
		} catch (Exception e) {
			//read feature file name under "features/{module}" folder
			rawFeatureName = scenario.getId().split("/")[3];
			//Remove character starting at '.feature'
			index = rawFeatureName.indexOf(".feature:");
			size = rawFeatureName.length();
			str  = new StringBuilder(rawFeatureName);
			rawFeatureName = str.replace(index, size, "").toString();
		}
		
		
		featureName=rawFeatureName;
		//Get scenario name		
		scenarioName= scenario.getName();
		
		Log.featureName();
		
		console.setInfo("Opening browser...");
		//deleteFiles();
		DriverUtils.openBrowser();
	}

	@After
	public void afterScenario(Scenario scenario) {
		//Close Browser
		console.setInfo("Closing browser...");
		if (DriverUtils.isDriverClose==false) {
			DriverUtils.endDriver();
			
			DriverUtils.isDriverClose=false;
		}
	}
}
