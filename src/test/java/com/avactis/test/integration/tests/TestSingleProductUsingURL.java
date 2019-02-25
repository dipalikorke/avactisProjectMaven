package com.avactis.test.integration.tests;

import static org.testng.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

//import mx4j.log.Log;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
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


public class TestSingleProductUsingURL {
	
	HomePage homePage;
	
	Logger Test= Logger.getLogger(TestSingleProductUsingURL.class.getName());
	@Parameters("browser")
	@BeforeClass (alwaysRun = true)
	public void setUp(String browser){	    
	System.out.println("The Test will run on browser -" + browser);
		DOMConfigurator.configure("log4j.xml");
	//	Test = Logger.getLogger(TestSingleProductUsingURL.class.getName());
		Browser.setBrowser(browser);
		Test.info("Browser value set");
		ConfigProperties.loadProperties();
		homePage = new HomePage();		
		homePage.get();
		Test.info("Test into HomePage");
	 }
	
	/*@Parameters({ "platform","browser","version"})
	@BeforeClass (alwaysRun = true)*/
	public void setUpForRemoteWebDriver(String platform, String browser, String version) {	    
	System.out.println("The Test will run on browser -" + browser);
		DOMConfigurator.configure("log4j.xml");
	//	Test = Logger.getLogger(TestSingleProductUsingURL.class.getName());
		DesiredCapabilities caps = new DesiredCapabilities();
		  System.out.println("Platform - "+ platform); 
		  System.out.println("Browser - "+ browser);
		  System.out.println("Browser Version - "+ version); 
		  //Platforms
		  if(platform.equalsIgnoreCase("Windows"))
			 caps.setPlatform(org.openqa.selenium.Platform.WINDOWS);
		  
		  if(platform.equalsIgnoreCase("MAC"))
		  	 caps.setPlatform(org.openqa.selenium.Platform.MAC);
		
		  if(platform.equalsIgnoreCase("Andorid"))
				 caps.setPlatform(org.openqa.selenium.Platform.ANDROID);
		  
		  //Browsers
		  if(browser.equalsIgnoreCase("Internet Explorer"))
			  caps = DesiredCapabilities.internetExplorer();
		  	 
		  if(browser.equalsIgnoreCase("Firefox"))
			  caps = DesiredCapabilities.firefox();
		  
		  if(browser.equalsIgnoreCase("chrome"))
			  caps = DesiredCapabilities.chrome();
		  
		  if(browser.equalsIgnoreCase("iPad"))
			  caps = DesiredCapabilities.ipad();
		  
		  if(browser.equalsIgnoreCase("Android"))
			  caps = DesiredCapabilities.android();
		  
		  //Version

		//	  caps.setVersion(version);
		  WebDriver driver; 
		  try {	   
			  driver = new RemoteWebDriver(new URL("http://192.168.0.109:4444/wd/hub"), caps);
			  Browser.setBrowser(driver);
			  System.out.println("Remote Web Driver initialized");
		  }catch(MalformedURLException e) {
			  System.out.println("Error executing Remote web Driver");
		  }
		

		Test.info("Browser value set");
		ConfigProperties.loadProperties();
		homePage = new HomePage();		
		homePage.get();
		Test.info("Test into HomePage");
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
		//homePage.get();
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
			Test.info("Registration is Successful");
			assertTrue(true, "Registration is Successful");
		}
		else
		{
			Test.debug("Test Failed as probably account already exists");
			fail("Registration failed for given data, check if the account already exists");
		}
	}
	
	@Test(groups = "Login", priority=1)
	public void signIn()
	{
		
		Test.info("Registration is Successful");
		SignInPage signInPage = homePage.doSignOut();
		
		if (!homePage.isUserLoggedIn())
		{
			signInPage = homePage.goToSignInPage();
			signInPage.doSignIn(ConfigProperties.getProperty("CLIENT_EMAIL_ID"), ConfigProperties.getProperty("CLIENT_PASSWORD"));
		}
		else
		{
			Test.debug("User already sign-in so no attempt was made to resign-in");
		}
				
	}
	
	@Test (groups = {"testOrder2"}, dataProvider="DP1",dependsOnGroups="Login")
	public void checkOutUsingUrls(String productPageContent, String categoryID,String categoryName, String subCategoryID, String subCategoryName, String productID, String paymentMethod, String shipmentMethod, String moreFlag)
	{
		//homePage.get();
		ProductList productList = homePage.goToProductPageUsingUrl(productPageContent, subCategoryName,subCategoryID);
		if (productList != null)
        {
			System.out.println("placeOrder() called");
			placeOrder(productList,productID,paymentMethod,shipmentMethod, moreFlag );	        
			
        }
        else
        {
        	fail("Could not go to required product List. Menu or submenu not available or not visible");       
        }
    }
	
	
	
	public void placeOrder(ProductList productList, String productID, String paymentMethod, String shipmentMethod,  String moreFlag) {
		if (productList.clickAddToCartButton(productID))
	        	System.out.println("Add To Cart Is Successful");
	    else 
        {
        	System.out.println("Add To Cart Is Not Successful");
        	assertTrue(false,"Add To Cart Failed");        	
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
		        
		        if (paymentMethod.equals("PayUbiz Payment gateway"))
		        {
		        	AvactisPaymentMethods.payUMoneyPaymentMethod();
		        }
		        
		        System.out.println("End of Place order Method");
        	}
	        
        } 
        else
        	System.out.println("There are still items to be added in the cart");
	}
}
