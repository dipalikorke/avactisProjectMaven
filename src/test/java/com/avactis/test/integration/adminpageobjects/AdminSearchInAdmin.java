package com.avactis.test.integration.adminpageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.avactis.test.integration.storepageobjects.Browser;

public class AdminSearchInAdmin {
	
	@FindBy(id = "search_text_box")
	@CacheLookup
	private WebElement searchField;
	
	@FindBy(css="a.btn.submit")
	@CacheLookup
	private WebElement searchButton;
	
	
	public AdminSearchInAdmin() {
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public AdminSearchResults searchInAdmin(String query) {
		searchField.clear();
		searchField.sendKeys(query);
		searchButton.click();
		return new AdminSearchResults(query);
	}
}
