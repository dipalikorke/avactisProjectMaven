package com.avactis.test.integration.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.storepageobjects.HomePage;
import com.avactis.test.integration.storepageobjects.MyCart;
import com.avactis.test.integration.storepageobjects.SearchProductByCategory;
import com.avactis.test.integration.storepageobjects.ShoppingCart;

public class SingleProductSelection {
	//WebDriver selenium;
	HomePage homePage;

	SearchProductByCategory searchproduct=new SearchProductByCategory();
	ShoppingCart viewOfCart=new ShoppingCart();
	MyCart cartPage;
	
	@Parameters("Browser")
	@BeforeMethod (alwaysRun = true)
	public void setUp(String browser){	    
		Browser.setBrowser(browser);
		homePage = new HomePage();
		
		
	 }
	
	@AfterMethod
	public void tearDown(){
	//	homePage.close();
		System.out.println("Hi use log file instead");
	  }
	
	public  void testVul() {
		try {
		Thread thread=new Thread();
		thread.run();
		int i =+ 1;
		}catch (ArithmeticException e) {
			throw e;
		}
	}
	
	@Test
	public void productSelection(){
		homePage.get();
		searchproduct.SearchUsingCategory("cid8", "cid20", "pid8");
		//on clicking on cart image->click on View Cart->so view page returns object which in turn is used by MyCart page.
		cartPage = viewOfCart.ViewCart();
		cartPage.verifyProductInTable();
		cartPage.verifyTotalNumberOfProducts();
		cartPage.totalAmount();
		
		
		
		
	}
	
}
