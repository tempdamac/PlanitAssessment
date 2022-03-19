package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import pageobjects.HomePage;
import utilities.Log;


public class HomePageStepDef {
	
	HomePage homepage = new HomePage();

	@Given("^I navigate to Jupiter Cloud site$")
	public void navigate_to_jupiter_cloud_site() {
		//Log this step on the report
		Log.stepDefinitionName();
		homepage.goToLoginPage();
	}
	
	@When("^I go to tab ([^\"]*)$")
	public void i_go_to_tab(String tabName) {
		//Log this step on the report
		Log.stepDefinitionName();
		homepage.goToTab(tabName);
	}
	
}
