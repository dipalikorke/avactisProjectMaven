package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

import com.avactis.test.integration.model.Address;
import com.avactis.test.integration.model.NewRegistrationDetails;
import com.avactis.test.integration.utility.WaitTool;

public class RegistrationFormPage extends LoadableComponent<RegistrationFormPage> {

	@FindBy(how= How.NAME, using="customer_info[Customer][Email]")	
	WebElement email;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][Password]")	
	WebElement password;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][RePassword]")	
	WebElement rePassword;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][FirstName]")	
	WebElement firstName;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][LastName]")	
	WebElement lastName;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][Country]")	
	WebElement country;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][State]")	
	WebElement state;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][ZipCode]")	
	WebElement zipCode;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][City]")	
	WebElement city;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][Streetline1]")	
	WebElement streetline1;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][Streetline2]")	
	WebElement streetline2;
	
	@FindBy(how= How.NAME, using="customer_info[Customer][Phone]")	
	WebElement phone;
	
	@FindBy(how= How.XPATH, using="//input[contains(@value,'Register')]")	
	WebElement register;
	
	@Override
	protected void isLoaded() throws Error {
		if (!WaitTool.waitForElementPresent(register, 20))
			{
				System.out.println("Could not load Registration Page after waiting for few seconds");
			}
		// TODO Auto-generated method stub
		
	}

	public RegistrationFormPage() {
		PageFactory.initElements(Browser.driver(), this);
		get();
	}

	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean FillRegistrationDetailsAndRegister(NewRegistrationDetails registrationDetails) 
	{
		email.sendKeys(registrationDetails.getEmail());
		password.sendKeys(registrationDetails.getPassword());
		rePassword.sendKeys(registrationDetails.getRePassword());
		firstName.sendKeys(registrationDetails.getFirstName());
		lastName.sendKeys(registrationDetails.getLastName());
		Select country1 = new Select(country);
		country1.selectByVisibleText(registrationDetails.getCountry());
		Select state1 = new Select(state);
		state1.selectByVisibleText(registrationDetails.getState());
		zipCode.sendKeys(registrationDetails.getZipcode());
		city.sendKeys(registrationDetails.getCity());
		streetline1.sendKeys(registrationDetails.getStreetLine1());
		streetline2.sendKeys(registrationDetails.getStreetLine2());
		phone.sendKeys(registrationDetails.getPhone());
		register.click();
		if (Browser.driver().getCurrentUrl().contains("home"))
			return true;
		else
			return false;
		
	}

}
