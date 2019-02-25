package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utility.WaitTool;

public class PayPalThanksPage extends LoadableComponent<PayPalThanksPage>{

	@FindBy (name = "merchant_return_link")
	@CacheLookup
	WebElement linkToReturnBackToAvactis;
	
	@FindBy (className = "total")
	@CacheLookup
	WebElement paymentMade;
	
	@FindBy (css = "#header > h1")
	@CacheLookup
	WebElement facilitatorEmailID;
	
	private static String title = "Thanks for your order - PayPal";
	
	public PayPalThanksPage(){
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
	public AvactisOrderConfirmationPage returnToAvactisStore() 
	{
		WaitTool.waitForElementPresent(Browser.driver(), linkToReturnBackToAvactis, 30);
		linkToReturnBackToAvactis.click();
		AvactisOrderConfirmationPage avactisOrderConfirmationPage = new AvactisOrderConfirmationPage();
		return avactisOrderConfirmationPage;
	}
	
	public String getPaymentMadeAmount()
	{
		WaitTool.waitForElementPresent(Browser.driver(), paymentMade, 30);
		String paymentAmount = paymentMade.getText();
		return paymentAmount;
	}
	
	public String getFacilitatorEmailID()
	{
		WaitTool.waitForElementPresent(Browser.driver(), facilitatorEmailID, 30);
		String facilitatorEmail = facilitatorEmailID.getText();
		return facilitatorEmail;
	}
	
}
