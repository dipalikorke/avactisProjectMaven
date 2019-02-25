package com.avactis.test.integration.tests;

import static org.testng.Assert.*;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
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


public class VerifyNewRegistration {
	HomePage homePage;
	
	@Parameters("Browser")
	@BeforeClass (alwaysRun = true)
	public void setUp(String browser){	    
	System.out.println("Value of Browser parameter-" + browser);
		Browser.setBrowser(browser);
		homePage = new HomePage();
		homePage.get();
	 }
	
	@AfterMethod
	public void tearDown(){
	//	homePage.close();
	  }

	
	
	@Test(groups = "Register")
	public void register()
	{
		homePage.get();
		SignInPage signInPage = homePage.goToSignInPage();
		RegistrationFormPage registrationFormPage = signInPage.doRegistration();
		NewRegistrationDetails newRegistrationDetails = new NewRegistrationDetails();
		newRegistrationDetails.setEmail("ketanvj@yahoo.com");
		newRegistrationDetails.setPassword("avactis123");
		newRegistrationDetails.setRePassword("avactis123");
		newRegistrationDetails.setFirstName("Ketan");
		newRegistrationDetails.setLastName("Jobanputra");
		newRegistrationDetails.setCountry("United States");
		newRegistrationDetails.setState("Delaware");
		newRegistrationDetails.setZipcode("12345");
		newRegistrationDetails.setCity("Pune");
		newRegistrationDetails.setStreetLine1("A3-44, Royal Orchard");
		newRegistrationDetails.setstreetLine2("Near Wireless Colony");
		newRegistrationDetails.setPhone("9860196111");
		if (registrationFormPage.FillRegistrationDetailsAndRegister(newRegistrationDetails))
			System.out.println("Registration Successfull");
		else
			fail("Registration was not Successful");
		
	}
	
	@Test(groups = {"Login"})
	public void signIn()
	{
		//homePage.get();
		
		SignInPage signInPage = homePage.doSignOut();
		
		if (signInPage == null)
		{
			signInPage = homePage.goToSignInPage();
		}
		signInPage.doSignIn("ketan@paratussystems.com", "avactis123");		
	}

}
