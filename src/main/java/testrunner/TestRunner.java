package testrunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import utilities.Log.Console;

@CucumberOptions(
		features="./features/",
		glue= {"stepdefinitions","utilities"},
 		tags = {"@CHJOB"},
		plugin= {"pretty", "html:target/site/cucumber-pretty","json:target/cucumber.json","junit:target/cucumber-reports/Cucumber.xml"},
		monochrome=true
		)

	
public class TestRunner {
	private TestNGCucumberRunner testNGCucumberRunner;
	Console console = new Console();
	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}
	
	@Test (dataProvider = "features")
	public void feature(PickleEventWrapper pickle,CucumberFeatureWrapper cucumberFeature) throws Throwable {
		try {
			testNGCucumberRunner.runScenario(pickle.getPickleEvent());
		}catch (Exception e) {
			console.setInfo(e.getMessage());
		}
	}
	
	//Reads all feature file and check what feature file to be executed based on Cucumber Options
	@DataProvider
	public Object[][] features(){
		return testNGCucumberRunner.provideScenarios();
	}
	
	//Close TestNGCucumberRunner
	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception{
		console.setInfo("Ending TestNGCucumber runner...");
		testNGCucumberRunner.finish();
	}
}
