package com.avactis.test.integration.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;

import static org.testng.AssertJUnit.assertTrue;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.avactis.test.integration.adminpageobjects.AdminHomePage;
import com.avactis.test.integration.adminpageobjects.AdminSearchResults;
import com.avactis.test.integration.adminpageobjects.AdminSignInPage;
import com.avactis.test.integration.storepageobjects.Browser;


public class TestAdminSearchFeature {
	AdminSignInPage adminSignInPage;
	
	@Parameters("Browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser){	    
		Browser.setBrowser(browser);
		adminSignInPage = new AdminSignInPage();
	 }
	
	@AfterMethod
	public void tearDown(){
		System.out.println("In TearDown");
		adminSignInPage.close();
	  }
	
	@Test
	public void testAdminSearch()
	{
		
		//Navigate to the Home page
        adminSignInPage.get();
        AdminHomePage adminHomePage = adminSignInPage.SignInAsAdmin("sandeep.raman@hbwsl.com", "avactis#123");
        AdminSearchResults searchResultInAdmin = adminHomePage.search().searchInAdmin("orders");
  //      AdminSearchResults searchResultInAdmin = adminHomePage.search().searchInAdmin("Orders");
        searchResultInAdmin.search().searchInAdmin("Product");        
	}
}


