package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.utility.WaitTool;

public class SignInPage extends LoadableComponent<SignInPage> {

	@FindBy(how= How.XPATH, using="//a[contains(@href,'register.php')]")	
	WebElement registerLink;
	
	@FindBy(how= How.XPATH, using="//input[contains(@value,'Sign In')]")	
	WebElement signInButton;
	
	@FindBy(how= How.ID, using="account_sign_in_form_email_id")	
	WebElement emailID;
	
	@FindBy(how= How.ID, using="account_sign_in_form_passwd_id")	
	WebElement password;
	
	
	@Override
	protected void isLoaded() throws Error {
		
		WaitTool.waitForElementPresent(registerLink, 20);
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	
	public SignInPage()
	{
		PageFactory.initElements(Browser.driver(), this);
		get();
		
	}

	public RegistrationFormPage doRegistration()
	{
		registerLink.click();
		RegistrationFormPage registrationFormPage = new RegistrationFormPage();
		return registrationFormPage;
	}
	
	public MyAccount doSignIn(String emailID, String password)
	{
		this.emailID.sendKeys(emailID);
		this.password.sendKeys(password);
		signInButton.click();
		MyAccount myAccount = new MyAccount();
		return myAccount;
	}
	
	
}
