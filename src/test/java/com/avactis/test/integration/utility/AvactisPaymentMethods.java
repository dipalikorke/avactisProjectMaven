package com.avactis.test.integration.utility;

import org.openqa.selenium.By;

import com.avactis.test.integration.storepageobjects.AvactisOrderConfirmationPage;
import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.storepageobjects.CheckoutSteptwo;
import com.avactis.test.integration.storepageobjects.PayPalConfirmationPage;
import com.avactis.test.integration.storepageobjects.PayPalSandboxLoginPage;
import com.avactis.test.integration.storepageobjects.PayPalThanksPage;
import com.avactis.test.integration.storepageobjects.PayUMoneyPaymentOptionsPage;

public class AvactisPaymentMethods {

	public static boolean payPalWebsitePaymentsStandard()
	{
		PayPalSandboxLoginPage payPalLogin = new PayPalSandboxLoginPage();
    	if (!WebElementExtender.isElementPresent(By.id("login_email")))
    	{
    		WaitTool.waitForElementPresentBy(Browser.driver(), By.id("loadLogin"), 5);
    		Browser.driver().findElement(By.id("loadLogin")).click();
    		
    	}
    	/*
    	WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
    	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("login_email")));
    	*/
    	
    	WaitTool.waitForElementPresentBy(Browser.driver(), By.id("login_email"), 5);
    	
    	payPalLogin = null;
    	payPalLogin = new PayPalSandboxLoginPage();
    	PayPalConfirmationPage confirmPayment = payPalLogin.LogInAsBuyer("ketanvj-buyer@yahoo.com", "12345678");
    	PayPalThanksPage payPalThanks = confirmPayment.clickOnPayNow();
    	String paymentMade = payPalThanks.getPaymentMadeAmount();
    	String facilitatorEmailID = payPalThanks.getFacilitatorEmailID();
    	System.out.println("PayPal Payment Amount - " + paymentMade);
    	System.out.println("Facilitator EmailID - " + facilitatorEmailID);
    	AvactisOrderConfirmationPage avactisOrderConfirmationPage = payPalThanks.returnToAvactisStore();
    	avactisOrderConfirmationPage.verifyOrderAmountWithPayPalAmount();
		return true;
	}
	
	public static boolean payUMoneyPaymentMethod() {
		PayUMoneyPaymentOptionsPage payUMoney = new PayUMoneyPaymentOptionsPage();
		payUMoney.payByCreditCard();		
		return true;
		
	}
	
	
}
