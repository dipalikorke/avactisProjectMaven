package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.avactis.test.integration.utility.ConfigProperties;
import com.avactis.test.integration.utility.WaitTool;
import com.avactis.test.integration.utility.WebElementExtender;

public class PayUMoneyPaymentOptionsPage extends LoadableComponent<PayUMoneyPaymentOptionsPage> {
	private static String title = "PayUbiz";
	
	@FindBy(how = How.ID, using="credit-card")
	WebElement 	payUMoneyCreditCards;
	
	@FindBy(how = How.ID, using="amex-cards")
	WebElement 	payUMoneyAmexCards;
	
	@FindBy(how = How.ID, using="diners")
	WebElement 	payUMoneyDiners;
	
	@FindBy(how = How.ID, using="ccard_number")
	WebElement 	payUMoneyCCardNumber;
	
	@FindBy(how = How.ID, using="cname_on_card")
	WebElement 	payUMoneyCNameOnCard;
	
	@FindBy(how = How.ID, using="ccvv_number")
	WebElement 	payUMoneyCCVVNumber;
	
	@FindBy(how = How.ID, using="cexpiry_date_month")
	WebElement 	payUMoneyCexpiryDateMonth;
	
	@FindBy(how = How.ID, using="cexpiry_date_year")
	WebElement 	payUMoneyCexpiryDateYear;
	
	@FindBy(how = How.NAME, using="pay_button")
	WebElement 	payUMoneyPayButton;
	
	public PayUMoneyPaymentOptionsPage(){
		PageFactory.initElements(Browser.driver(), this);
		get();
	 }
	
	@Override
    public void load() {
//        Browser.open(url);
	}
	
	@Override
    public void isLoaded() {
		
    		WaitTool.waitForElementPresent(payUMoneyPayButton, 60);
            assertTrue(Browser.driver().getTitle().equals(title));
    }
	
	
	public boolean payByCreditCard() {
		payUMoneyCreditCards.click();
		//payUMoneyCCardNumber.sendKeys(ConfigProperties.getProperty("PAYUMONEY_CARD_NO"));
		payUMoneyCCardNumber.sendKeys(ConfigProperties.getProperty("PAYUMONEY_CARD_NO").split(" ")[0]);
		payUMoneyCCardNumber.sendKeys(ConfigProperties.getProperty("PAYUMONEY_CARD_NO").split(" ")[1]);
		payUMoneyCCardNumber.sendKeys(ConfigProperties.getProperty("PAYUMONEY_CARD_NO").split(" ")[2]);
		payUMoneyCCardNumber.sendKeys(ConfigProperties.getProperty("PAYUMONEY_CARD_NO").split(" ")[3]);
		System.out.println(ConfigProperties.getProperty("PAYUMONEY_CARD_NO"));
		payUMoneyCNameOnCard.sendKeys(ConfigProperties.getProperty("PAYUMONEY_NAME_ON_CARD"));
		payUMoneyCCVVNumber.sendKeys(ConfigProperties.getProperty("PAYUMONEY_CARD_VERI_NO"));
		Select expiratioMonth = new Select(payUMoneyCexpiryDateMonth);
		expiratioMonth.selectByValue(ConfigProperties.getProperty("PAYUMONEY_CARD_EXP_MONTH"));
		Select expiratioYear = new Select(payUMoneyCexpiryDateYear);
		expiratioYear.selectByValue(ConfigProperties.getProperty("PAYUMONEY_CARD_EXP_YEAR"));
		payUMoneyPayButton.click();
		//title - Send PARes to TermUrl
		WaitTool.waitForTitle("Send PARes to TermUrl");
		WaitTool.waitForElementPresent(Browser.driver().findElement(By.xpath("//input[@type='submit']")), 120);		
		System.out.println("Current URL - " + Browser.driver().getCurrentUrl() + " and Page title - " + Browser.driver().getTitle());
		Browser.driver().findElement(By.xpath("//input[@type='submit']")).click();
		return true;
		
	}
	
	
}
