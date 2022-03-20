package pageobjects;

import org.openqa.selenium.By;

import elements.Element;
import utilities.DriverUtils;
import utilities.PropertiesFileReader;
import utilities.WebControl;

public class HomePage {
	
	PropertiesFileReader propertiesFileReader = new PropertiesFileReader();
	
	String username = propertiesFileReader.getProperty().getProperty("pt.username");
	String password = propertiesFileReader.getProperty().getProperty("pt.password");
	WebControl control = new WebControl();
	
	public Element navigationTab (String tabName) {
		return new Element(tabName+ " Tab", By.xpath("//a[contains(text(),'"+tabName+"')]"));
	}
	
	public void goToLoginPage() {
		control.navigateTo(DriverUtils.url);
		control.waitForPageToLoad(120);
	}
	
	public void goToTab(String tabName) {
		navigationTab(tabName).waitForExist(60);
		navigationTab(tabName).click();
	}

}
