package pageobjects;

import org.openqa.selenium.By;

import elements.Element;
import utilities.Log.Console;
import utilities.Log;
import utilities.WebControl;

public class ShopPage {
	
	Console console = new Console();
	WebControl control = new WebControl();
	
	public static String productName = null;
	public static String quantity = null;
	
	public Element cartMenu = new Element("Cart Menu", By.xpath("//a[contains(@href,'cart')]"));
	public Element buyButton (String productName) {
		return new Element("Buy Button", By.xpath("//h4[contains(text(),'"+productName+"')]//following-sibling::p/a[text()='Buy']"));
	}
	
	
	
	public void addProductToCart (String productValue) {
		
		if (productValue.contains(";")) {
			String[] multiProduct = productValue.split(";");
			for (int i=0; i<multiProduct.length; i++) {
				if (multiProduct[i].contains(">")) {
					String[] product = multiProduct[i].split(">");
					productName = product[0];
					quantity = product[1];
					
					int count = Integer.valueOf(quantity);
					for(int j=0; j<count; j++) {
						buyButton(productName).click();
						Log.setTestStepPassed("Product "+productName+" has been added to cart.");
						control.wait(1);
					}
				}
			}
		} else {
			if (productValue.contains(">")) {
				String[] product = productValue.split(">");
				productName = product[0];
				quantity = product[1];
				
				int count = Integer.valueOf(quantity);
				for(int j=1; j<=count; j++) {
					buyButton(productName).click();
					Log.setTestStepPassed("Product "+productName+" has been added to cart.");
					control.wait(1);
				}
			}
		}
	}
	
	public void goToCart() {
		cartMenu.click();
	}

}
