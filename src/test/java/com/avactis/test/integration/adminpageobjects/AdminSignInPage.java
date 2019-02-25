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

public class AdminSignInPage extends LoadableComponent<AdminSignInPage> {
	static String url = "http://sandbox.avactis.net/ketan473/avactis-system/admin";
	private static String title = "Avactis Shopping Cart";
	
	@FindBy (name = "AdminEmail")
	@CacheLookup
	WebElement adminEmail;
	
	@FindBy (name = "Password")
	@CacheLookup
	WebElement adminPassword;
	
	@FindBy (xpath = "//button[@type='submit']")
	@CacheLookup
	WebElement adminSignInButton;
	
	
	public AdminSignInPage(){
		PageFactory.initElements(Browser.driver(), this);
	 }
	
	@Override
    public void load() {
        Browser.open(url);
	}
	
	@Override
    public void isLoaded() {
        assertTrue(Browser.driver().getTitle().equals(title));
    }
	
	public void close() {
        Browser.close();
    }
	
	public AdminHomePage SignInAsAdmin(String email, String password) {
	    adminEmail.clear();
	    adminEmail.sendKeys(email);
	    adminPassword.clear();
	    adminPassword.sendKeys(password);
	    adminSignInButton.click();
		AdminHomePage adminHomePage = new AdminHomePage();
	    return adminHomePage;
    }
	
	
}
