package com.avactis.test.integration.adminpageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;



import com.avactis.test.integration.storepageobjects.Browser;


//import pageObjectScriptver3.Search;
import static org.testng.Assert.*;

public class AdminHomePage extends LoadableComponent<AdminHomePage> {
	static String adminHomPageUrl = "http://sandbox.avactis.net/ketan471/avactis-system/admin/index.php?";
	private static String adminHomePagetitle = "Home - Avactis Shopping Cart";

	@FindBy (xpath = "//li[@id='menu-orders']/a[@href='orders.php']")
	@CacheLookup
	WebElement orderLink;
	
	
	public AdminHomePage(){
		PageFactory.initElements(Browser.driver(), this);
	 }
	
	@Override
    public void load() {
      //  Browser.open(adminHomPageUrl);
	}
	
	@Override
    public void isLoaded() {
        assertTrue(Browser.driver().getTitle().equals(adminHomePagetitle));
    }
	
	public void close() {
        Browser.close();
    }
	
	public AdminSearchInAdmin search() {
		AdminSearchInAdmin search = new AdminSearchInAdmin();
	    	return search;
    }
	
	public AdminOrdersPage goToOrders() {
		orderLink.click();
		AdminOrdersPage ordersPage = new AdminOrdersPage();
	    	return ordersPage;
    }
	
}