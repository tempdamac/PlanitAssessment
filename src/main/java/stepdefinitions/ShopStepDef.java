package stepdefinitions;

import cucumber.api.java.en.When;
import pageobjects.ShopPage;
import utilities.Log;

public class ShopStepDef {
	
	ShopPage shop = new ShopPage();
	
	@When("add to cart the following products ([^\"]*)$")
	public void add_to_cart(String product) {
		//Log this step on the report
		Log.stepDefinitionName();
		shop.addProductToCart(product);
	    
	}


}
