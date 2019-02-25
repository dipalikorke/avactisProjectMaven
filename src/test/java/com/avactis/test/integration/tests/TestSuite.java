package com.avactis.test.integration.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avactis.test.integration.model.Address;
import com.avactis.test.integration.storepageobjects.Checkout;
import com.avactis.test.integration.storepageobjects.CheckoutStepthree;
import com.avactis.test.integration.storepageobjects.CheckoutSteptwo;
import com.avactis.test.integration.storepageobjects.HomePage;
import com.avactis.test.integration.storepageobjects.ProductInfo;
import com.avactis.test.integration.storepageobjects.SearchResults;
import com.avactis.test.integration.storepageobjects.ShoppingCart;
import com.avactis.test.integration.storepageobjects.Browser;
public class TestSuite {
	//WebDriver selenium;
	HomePage homePage;
	
	@Parameters("Browser")
	@BeforeClass (alwaysRun = true)
	public void setUp(String browser){	    
	System.out.println("Value of Browser parameter" + browser);
		Browser.setBrowser(browser);
		homePage = new HomePage();
	 }
	
	@AfterMethod
	public void tearDown(){
	//	homePage.close();
	  }
	
	@Test
	public void testProductCheckout()
	{
		
		//Navigate to the Home page
        homePage.get();
        
      //Search for 'sony', the searchInStore method will return
      //SearchResults class
        System.out.println("TestSuite-Before searching in store");
        SearchResults searchResult = homePage.search().searchInStore("Asus");
        System.out.println("TestSuite-Before clicking a particular product");
        ProductInfo productinfo = homePage.ProductInfo("ASUS");  
        System.out.println("TestSuite-Before adding to cart");
        ShoppingCart shoppingcart = productinfo.doAddToCart();
        if (shoppingcart!=null)
        {
        System.out.println("TestSuite-Before doing first level check out");
        if (shoppingcart.goToCartImage(1))
        {
           
	        Checkout checkout = shoppingcart.doCheckout();
	        Address billingAddress = new Address();
	        billingAddress.setFirstName("Ketan");
	        billingAddress.setLastName("J");
	        billingAddress.setEmail("ketanvj@gmail.com");
	        billingAddress.setCountry("United States");
	        billingAddress.setZipcode("10118");
	        billingAddress.setState("New York");
	        billingAddress.setCity("New York");
	        billingAddress.setAddLine1("350 Fifth Avenue");
	        billingAddress.setAddLine2("34th floor New York");
	        billingAddress.setContactPhone("9112131313");
	        checkout.fillBillingAddress();
	        Address shippingAddress = new Address();
	        checkout.fillShippingAddress();
	        System.out.println("TestSuite-Before doing second level checkout ");
	        CheckoutSteptwo checkoutsteptwo = checkout.continueCheckout(true);
	        System.out.println("TestSuite-Before doing third level checkout");
	        String paymentMethod = "PayPal Website Payments Standard";
	       //The below call is a must and if needed, need to be refactored this function to support without parameter
	        // CheckoutStepthree checkoutstepthree = checkoutsteptwo.continueNextCheckout(paymentMethod, "Ground Shipping");
	        System.out.println("TestSuite-Before placing the order");
//Below function is required to make the test run
	        //	        checkoutstepthree.placeOrder();
	        if (paymentMethod.equals("PayPal Website Payments Standard"))
	        {
	        	//Do paypal payment processing by clicking on sign-in and then login 
	        }
        } 
        else
        	System.out.println("No items found in the cart");
        }
        else
        {
        	System.out.println("Add to Cart Failed");
        }
        }
        
	
	
	/*@Test
	 public void ShouldLoadTheHomePageAndThenCheckButtonOnProductDisplay(){
	    selenium.get("http://localhost/shopcart/");
	    HomePage hp = new HomePage(selenium);
	    hp.showProductCategory("Apparel");
	    ProductInfo pl = hp.showProduct("Avactis T-Shirt");
	    pl.addToShoppingCart(selenium);
	    //assertTrue(pl.isButtonPresent("el buttons button_add_to_cart input_submit"));
	    assertTrue(pl.isButtonPresent("button_add_to_cart"));
	  }*/
	}


