package pageobjects;

import org.openqa.selenium.By;

import utilities.Log;

import elements.Element;
import utilities.RandomStringGenerator;
import utilities.WebControl;

public class ContactPage {
	
	WebControl control = new WebControl();
	
	public static String successMsg = null;
	
	public Element forenameText = new Element("Forename TextBox", By.id("forename"));
	public Element surnameText = new Element("Surname TextBox", By.id("surname"));
	public Element emailText = new Element("Email TextBox", By.id("email"));
	public Element telephoneText = new Element("Telephone TextBox", By.id("telephone"));
	public Element messageText = new Element("Message TextBox", By.id("message"));
	public Element submitButton = new Element("Submit Button", By.xpath("//a[text()='Submit']"));
	public Element verifyMessage = new Element("Successful Message", By.xpath("//*[@class='alert alert-success']"));
	
	public void populateAllFields (String forename, String surname, String email, String telephone, String message) {
		
		String forenameRand = forename + RandomStringGenerator.alphabet(3);
		String surnameRand = surname + RandomStringGenerator.alphabet(3);
		String emailRand = forenameRand + surnameRand + email;
		
		forenameText.waitForExist(60);
		forenameText.sendKeys(forenameRand);
		surnameText.sendKeys(surnameRand);
		emailText.sendKeys(emailRand);
		telephoneText.sendKeys(telephone);
		messageText.sendKeys(message);
		control.wait(2);
		control.takeScreenshot();
		submitButton.click();
		control.wait(20);
	}
	
	public void verifySucessMessage() {
		control.waitForPageToLoad(60);
		if (verifyMessage.isExist(120)==true) {
			successMsg = verifyMessage.getText();
			Log.setTestStepPassed("Feedback message '" + successMsg + "' is displayed.");
			control.takeScreenshot();
		} else {
			Log.setTestStepFailed("Feedback message '" + successMsg + "' is not displayed.");
		}
		
	}
	

}
