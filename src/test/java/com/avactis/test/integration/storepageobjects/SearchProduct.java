package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchProduct {
	
	@FindBy(xpath = "//i[contains(@class, 'search')]")
	@CacheLookup
	private WebElement searchImage; 
	
	@FindBy(name = "search_pattern")
	@CacheLookup
	private WebElement searchField;
	
	@FindBy(css="button.btn.btn-primary")
	@CacheLookup
	private WebElement searchButton;
	
	
	public SearchProduct() {
		PageFactory.initElements(Browser.driver(), this);
	}
	
	public SearchResults searchInStore(String query) {
	  WebDriverWait waitForSearchImage = new WebDriverWait(Browser.driver(),30);
	  waitForSearchImage.until(ExpectedConditions.elementToBeClickable(searchImage)).click();
		searchImage.click();
		searchField.clear();
		searchField.sendKeys(query);
		searchButton.click();
		return new SearchResults(query);
	}
}
