package stepdefinitions;

import utilities.Log;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobjects.HomePage;
import pageobjects.ContactPage;
import utilities.WebControl;

public class ContactStepDef {
	
	WebControl control = new WebControl();
	HomePage homepage = new HomePage();
	ContactPage contact = new ContactPage();
	
	@When("populate all mandatory fields ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*), ([^\"]*)")
	public void populate_all_mandatory_fields(String foreName, String surName, String email, String telephone, String message) {
		//Log this step on the report
		Log.stepDefinitionName();
		contact.populateAllFields(foreName, surName, email, telephone, message);
	}

	@Then("successful submission message is displayed")
	public void successful_submission_message_is_displayed() {
		//Log this step on the report
		Log.stepDefinitionName();
		contact.verifySucessMessage();
	}

}
