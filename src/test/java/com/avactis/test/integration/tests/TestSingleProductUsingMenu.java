package com.avactis.test.integration.tests;

import static org.testng.Assert.*;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avactis.test.integration.model.Address;
import com.avactis.test.integration.model.NewRegistrationDetails;
import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.storepageobjects.Checkout;
import com.avactis.test.integration.storepageobjects.CheckoutStepthree;
import com.avactis.test.integration.storepageobjects.CheckoutSteptwo;
import com.avactis.test.integration.storepageobjects.HomePage;
import com.avactis.test.integration.storepageobjects.MyCart;
import com.avactis.test.integration.storepageobjects.RegistrationFormPage;
import com.avactis.test.integration.storepageobjects.ShoppingCart;
import com.avactis.test.integration.storepageobjects.ProductList;
import com.avactis.test.integration.storepageobjects.SignInPage;
import com.avactis.test.integration.utility.AvactisPaymentMethods;
import com.avactis.test.integration.utility.ConfigProperties;
import com.avactis.test.integration.utility.ExcelData;


public class TestSingleProductUsingMenu {
	HomePage homePage;
	
	@Parameters("Browser")
	
	@BeforeClass (alwaysRun = true)
	public void setUp(String browser){	    
	System.out.println("The Test will run on browser -" + browser);
		Browser.setBrowser(browser);
		ConfigProperties.loadProperties();
		homePage = new HomePage();
		homePage.get();
	 }
	
	@AfterMethod
	public void tearDown(){
	//	homePage.close();
	  }

	@DataProvider(name = "DP1")
    public Object[][] createData1() {
       String file = ConfigProperties.getProperty("DATA_PATH")+ConfigProperties.getProperty("DATA_FILE");
		System.out.println(file);
       Object[][] retObjArr=ExcelData.getTableArray(file,
        		ConfigProperties.getProperty("DATA_SHEET"), ConfigProperties.getProperty("DATA_START_N_END_POINT"));	
        return(retObjArr);
    }
	
	@Test(groups = "Register")
	public void register()
	{
		homePage.get();
		RegistrationFormPage registrationFormPage = homePage.goToRegistrationPageUsingUrl();
		NewRegistrationDetails newRegistrationDetails = new NewRegistrationDetails();
		newRegistrationDetails.setEmail(ConfigProperties.getProperty("REG_EMAIL_ID"));
		newRegistrationDetails.setPassword(ConfigProperties.getProperty("REG_PASSWORD"));
		newRegistrationDetails.setRePassword(ConfigProperties.getProperty("REG_REPASSWORD"));
		newRegistrationDetails.setFirstName(ConfigProperties.getProperty("REG_FIRST_NAME"));
		newRegistrationDetails.setLastName(ConfigProperties.getProperty("REG_LAST_NAME"));
		newRegistrationDetails.setCountry(ConfigProperties.getProperty("REG_COUNTRY"));
		newRegistrationDetails.setState(ConfigProperties.getProperty("REG_STATE"));
		newRegistrationDetails.setZipcode(ConfigProperties.getProperty("REG_ZIP"));
		newRegistrationDetails.setCity(ConfigProperties.getProperty("REG_CITY"));
		newRegistrationDetails.setStreetLine1(ConfigProperties.getProperty("REG_STREET_LINE1"));
		newRegistrationDetails.setstreetLine2(ConfigProperties.getProperty("REG_STREET_LINE2"));
		newRegistrationDetails.setPhone(ConfigProperties.getProperty("REG_PHONE"));
		
		if (registrationFormPage.FillRegistrationDetailsAndRegister(newRegistrationDetails))
		{
			assertTrue(true, "Registration is Successful");
		}
		else
		{
			fail("Registration failed for given data, check if the account already exists");
		}
	}
	
	@Test(groups = "Login", priority=1)
	public void signIn()
	{
		SignInPage signInPage = homePage.doSignOut();
		
		if (signInPage == null)
		{
			signInPage = homePage.goToSignInPage();
		}
		signInPage.doSignIn("ketanvj@yahoo.com", "avactis123");		
	}
	
	@Test (groups = "testOrder", dataProvider="DP1")
	public void testSingleProductUsingMenu(String productPageContent, String categoryID,String categoryName, String subCategoryID, String subCategoryName, String productID, String paymentMethod, String shipmentMethod, String creditCardType, String creditCardNumber, String creditCardVVV, String expiryMonth, String expiryYear)
	{
	    ProductList productList = homePage.goToProductPageUsingMenuAndSubMenu(categoryID, subCategoryID);
        if (productList != null)
        {
	        if (productList.clickAddToCartButton(productID))   //if this method returns true
	        	System.out.println("Add To Cart Is Successful");
	        else 
	        {
	        	System.out.println("Add To Cart Is Not Successful");
	        	assertTrue(false);
	        	fail("Add To Cart Failed");
	        }
	        ShoppingCart shoppingcart = new ShoppingCart();
	        if (shoppingcart.goToCartImage(1))   //no need if and call method 
	        {
		        Checkout checkout = shoppingcart.goToCartImageAndDoCheckout();     
		        System.out.println("TestSuite-Before doing second level checkout ");
		        //if not signed-in then this below function should be passed with ture
		        //which will call corresponding functions to fill the billing
		        //and shipping details
		        CheckoutSteptwo checkoutsteptwo = checkout.continueCheckout(true);
		        System.out.println("TestSuite-Before doing third level checkout");
		        checkoutsteptwo.selectPaymentMethod(paymentMethod);
		        if (paymentMethod.equals("Authorize.Net (Credit Card)"))
		        {
		        	//checkoutsteptwo.authorizedDotNetCreditCardFillDetails("Visa", "4007000000027", "123", "12", "2016");
		        	checkoutsteptwo.authorizedDotNetCreditCardFillDetails();
		        }
		        checkoutsteptwo.selectShippingMethod(shipmentMethod);
		        CheckoutStepthree checkoutstepthree = checkoutsteptwo.continueNextCheckout();
		        System.out.println("TestSuite-Before placing the order");
		        checkoutstepthree.placeOrder();
		        if (paymentMethod.equals("PayPal Website Payments Standard"))
		        {
		        	AvactisPaymentMethods.payPalWebsitePaymentsStandard();    	
		        	//Do paypal payment processing by clicking on sign-in and then login
		        }
	        } 
	        else
	        	System.out.println("No items found in the cart");
        }
        else
        {
        	fail("Could not go to required product List. Menu or submenu not available or not visible");    
        }
    }
	
	@Test (groups = {"testOrder1"}, dataProvider="DP1", dependsOnGroups = "Login")  //14 col sheet2
	public void checkOutUsingMyCartMenu(String productPageContent, String categoryID,String categoryName, String subCategoryID, String subCategoryName, String productID, String paymentMethod, String shipmentMethod, String creditCardType, String creditCardNumber, String creditCardVVV, String expiryMonth, String expiryYear, String moreFlag)
	{
		homePage.get();
		ProductList productList = homePage.goToProductPageUsingMenuAndSubMenu(categoryID, subCategoryID);
		if (productList != null)
        {
			System.out.println("placeOrder() called");
			placeOrder(productList,productID,paymentMethod,shipmentMethod,creditCardType,creditCardNumber,creditCardVVV,expiryMonth,expiryYear, moreFlag );	        
			
        }
        else
        {
        	fail("Could not go to required product List. Menu or submenu not available or not visible");
       // 	System.out.println("Could not go to required product List. Menu or submenu not available or not visible");
        }
    }
	
	public void placeOrder(ProductList productList, String productID, String paymentMethod, String shipmentMethod, String creditCardType,String creditCardNumber, String creditCardVVV, String expiryMonth, String expiryYear, String moreFlag) {
		if (productList.clickAddToCartButton(productID))
	        	System.out.println("Add To Cart Is Successful");
	    else 
        {
        	System.out.println("Add To Cart Is Not Successful");
        	assertTrue(false);
        	fail("Add To Cart Failed");
        }
	    if (!moreFlag.contains("Y"))
	    {
	        MyCart myCart = homePage.goToMyCartUsingUrl();
	        if (myCart.IsNotEmpty())
	        {
	        	Checkout checkout = myCart.doCheckoutUsingUrl();
	        	CheckoutSteptwo checkoutsteptwo;
	        	if ( !homePage.isUserLoggedIn())
	        	{
	        		checkoutsteptwo = checkout.continueCheckout(true);
	        	} else
	        	{
	        		checkoutsteptwo = checkout.continueCheckout(false);
	        	}
		        checkoutsteptwo.selectPaymentMethod(paymentMethod);
		        if (paymentMethod.equals("Authorize.Net (Credit Card)"))
		        {		        	
		        	checkoutsteptwo.authorizedDotNetCreditCardFillDetails();
		        } 
		        else if (paymentMethod.equals("Stripe Gateway"))
		        {
		        	checkoutsteptwo.stripeCreditCardFillDetails();
		        }
		        checkoutsteptwo.selectShippingMethod(shipmentMethod);
		        CheckoutStepthree checkoutstepthree = checkoutsteptwo.continueNextCheckout();
		        System.out.println("TestSuite-Before placing the order");
		        
		        checkoutstepthree.placeOrder();
		        if (paymentMethod.equals("PayPal Website Payments Standard"))
		        {
		        	AvactisPaymentMethods.payPalWebsitePaymentsStandard();
		        }
		        System.out.println("End of Place order Method");
        	}
	        
        } 
        else
        	System.out.println("No items found in the cart");
	}
}
