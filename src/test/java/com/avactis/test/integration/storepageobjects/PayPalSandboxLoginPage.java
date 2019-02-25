package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;





import com.avactis.test.integration.storepageobjects.Browser;

//import pageObjectScriptver3.Search;
import static org.testng.Assert.*;

public class PayPalSandboxLoginPage extends LoadableComponent<PayPalSandboxLoginPage> {
	//static String url = "http://sandbox.avactis.net/ketan473/avactis-system/admin";
	//url will not be invoked directly. The page will appear based on action
	//taken on store
	private static String title = "Pay with a PayPal account";
	
	@FindBy (id = "login_email")
	@CacheLookup
	WebElement payPalBuyerEmailID;
	
	@FindBy (id = "login_password")
	@CacheLookup
	WebElement payPalBuyerPassword;
	
	@FindBy (id = "submitLogin")
	@CacheLookup
	WebElement payPalBuyerLogInButton;
	
	
	public PayPalSandboxLoginPage(){
		PageFactory.initElements(Browser.driver(), this);
	 }
	
	@Override
    public void load() {
//        Browser.open(url);
	}
	
	@Override
    public void isLoaded() {
        assertTrue(Browser.driver().getTitle().equals(title));
    }
	
	public void close() {
        Browser.close();
    }
	
	public PayPalConfirmationPage LogInAsBuyer(String email, String password) {
		payPalBuyerEmailID.clear();
		payPalBuyerEmailID.sendKeys(email);
		payPalBuyerPassword.clear();
		payPalBuyerPassword.sendKeys(password);
		payPalBuyerLogInButton.click();
		PayPalConfirmationPage payPalConfirmation = new PayPalConfirmationPage();
	    return payPalConfirmation;
    }
	
	
}
