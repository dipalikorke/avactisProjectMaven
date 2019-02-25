package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.*;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.utility.WaitTool;

public class PayPalConfirmationPage extends LoadableComponent<PayPalConfirmationPage> {

	@FindBy (id = "continue_abovefold")
	@CacheLookup
	WebElement payPalBuyerPayNowButton;
	
	private static String title = "Review your information";
	
	public PayPalConfirmationPage(){
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
	
	public PayPalThanksPage clickOnPayNow()
	{
		System.out.println("Insider ClickOnPayMethod");
		WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
		wait.until(ExpectedConditions.elementToBeClickable(payPalBuyerPayNowButton));
		payPalBuyerPayNowButton.click();		
		PayPalThanksPage payPalThanks = new PayPalThanksPage();
		return payPalThanks;
	}

	
}
