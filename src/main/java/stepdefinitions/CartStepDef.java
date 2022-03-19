package stepdefinitions;

import cucumber.api.java.en.Then;
import pageobjects.CartPage;
import pageobjects.ShopPage;
import utilities.Log;

public class CartStepDef {
	
	ShopPage shop = new ShopPage();
	CartPage cart = new CartPage();

	@Then("I can view the added products ([^\"]*) in my cart$")
	public void i_can_view_the_added_products(String products) {
		//Log this step on the report
		Log.stepDefinitionName();
		shop.goToCart();
		cart.verifyCartItems(products);
	    
	}
	
	@Then("I can verify the subtotal of each added products ([^\"]*) in my cart$")
	public void i_can_verify_the_subtotal_of_each_added_products(String products) {
		//Log this step on the report
		Log.stepDefinitionName();
		shop.goToCart();
		cart.verifyCartSubtotal(products);
	}

}
