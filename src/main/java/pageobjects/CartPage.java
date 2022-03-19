package pageobjects;

import org.openqa.selenium.By;

import elements.Element;
import utilities.Log;
import utilities.WebControl;
import utilities.Log.Console;

public class CartPage {
	
	Console console = new Console();
	WebControl control = new WebControl();
	
	public static String productName = null;
	public static String quantity = null;
	
	public Element cartProductName (int row) {
		return new Element("Cart Product Name", By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+row+"]/td[1]"));
	}
	
	public Element cartProductPrice (int row) {
		return new Element("Cart Product Price", By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+row+"]/td[2]"));
	}
	
	public Element cartProductQty (int row) {
		return new Element("Cart Product Quantity", By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+row+"]/td[3]/input"));
	}
	
	public Element cartProductSubTotal (int row) {
		return new Element("Cart Product SubTotal", By.xpath("//table[contains(@class,'cart-items')]/tbody/tr["+row+"]/td[4]"));
	}
	
	public void verifyCartItems (String productValue) {
		
		if (productValue.contains(";")) {
			String[] multiProduct = productValue.split(";");
			for (int i=0; i<multiProduct.length; i++) {
				if (multiProduct[i].contains(">")) {
					String[] product = multiProduct[i].split(">");
					productName = product[0];
					quantity = product[1];
					
					int row = i+1;
					// Verify product name
					if (productName.equals(cartProductName(row).getText())) {
						Log.setTestStepPassed("Added product '"+productName+"' is the same "
								+ "from cart product '"+cartProductName(row).getText()+"'");
						control.takeScreenshot();
					} else {
						Log.setTestStepFailed("Added product '"+productName+"' is not the same "
								+ "from cart product '"+cartProductName(row).getText()+"'");
					}
					String cartQty = cartProductQty(row).getAttribute("value");
					// Verify product quantity
					if (quantity.equals(cartQty)) {
						Log.setTestStepPassed("Added product quantity '"+quantity+"' is the same "
								+ "from cart product quantity '"+cartQty+"'");
						control.takeScreenshot();
					} else {
						Log.setTestStepFailed("Added product quantity '"+quantity+"' is not the same "
								+ "from cart product quantity '"+cartQty+"'");
					}
				}
			}
		} else {
			if (productValue.contains(">")) {
				String[] product = productValue.split(">");
				productName = product[0];
				quantity = product[1];
				
				// Verify product name
				if (productName.equals(cartProductName(0).getText())) {
					Log.setTestStepPassed("Added product "+productName+" is the same from cart product "+cartProductName(0).getText());
					control.takeScreenshot();
				} else {
					Log.setTestStepFailed("Added product "+productName+" is not the same from cart product "+cartProductName(0).getText());
				}
				
				// Verify product quantity
				if (quantity.equals(cartProductQty(1).getValue())) {
					Log.setTestStepPassed("Added product quantity "+quantity+" is the same "
							+ "from cart product quantity "+cartProductName(1).getValue());
					control.takeScreenshot();
				} else {
					Log.setTestStepFailed("Added product quantity "+quantity+" is not the same "
							+ "from cart product quantity "+cartProductName(1).getValue());
				}
			}
		}
	}
	
	public void verifyCartSubtotal(String productValue) {
			
		if (productValue.contains(";")) {
			String[] multiProduct = productValue.split(";");
			for (int i=0; i<multiProduct.length; i++) {
				if (multiProduct[i].contains(">")) {
					String[] product = multiProduct[i].split(">");
					productName = product[0];
					quantity = product[1];
					
					int row = i+1;
					String actualPrice = cartProductPrice(row).getText();
					String actualSubTotal = cartProductSubTotal(row).getText();
					String priceOnly = actualPrice.replaceAll("[^0-9\\.]","");
					String subTotalOnly = actualSubTotal.replaceAll("[^0-9\\.]","");
					
					int intQty = Integer.valueOf(quantity);
					double intPrice = Double.parseDouble(priceOnly);
					double intSubTotal = Double.parseDouble(subTotalOnly);
					
					if (intQty*intPrice == intSubTotal) {
						Log.setTestStepPassed("Product '"+productName+"' with quantity '"+intQty+"' "
								+ "each price at '"+intPrice+"' has a subtotal of '"+intSubTotal+"'");
						control.takeScreenshot();
					} else {
						Log.setTestStepFailed("Product '"+productName+"' with quantity '"+intQty+"' "
								+ "each price at '"+intPrice+"' has a mismatch on subtotal of '"+intSubTotal+"'");
					}
				}
			}
		} else {
			if (productValue.contains(">")) {
				String[] product = productValue.split(">");
				productName = product[0];
				quantity = product[1];
				
				String actualPrice = cartProductPrice(1).getText();
				String actualSubTotal = cartProductSubTotal(1).getText();
				String priceOnly = actualPrice.replaceAll("[^0-9\\.]","");
				String subTotalOnly = actualSubTotal.replaceAll("[^0-9\\.]","");
				
				int intQty = Integer.valueOf(quantity);
				double intPrice = Double.parseDouble(priceOnly);
				double intSubTotal = Double.parseDouble(subTotalOnly);
				
				if (intQty*intPrice == intSubTotal) {
					Log.setTestStepPassed("Product '"+productName+"' with quantity '"+intQty+"' "
							+ "each price at '"+intPrice+"' has a subtotal of '"+intSubTotal+"'");
					control.takeScreenshot();
				} else {
					Log.setTestStepFailed("Product '"+productName+"' with quantity '"+intQty+"' "
							+ "each price at '"+intPrice+"' has a mismatch on subtotal of '"+intSubTotal+"'");
				}
			}
		}
	}
}
