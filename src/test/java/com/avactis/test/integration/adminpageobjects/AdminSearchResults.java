package com.avactis.test.integration.adminpageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.storepageobjects.Browser;

public class AdminSearchResults extends LoadableComponent<AdminSearchResults> {

	private String query;
	
	public AdminSearchResults(String query){
		PageFactory.initElements(Browser.driver(),this);
	}
	
	@Override
    public void isLoaded() {
    	assertTrue(Browser.driver().getTitle().contains(" - Avactis Shopping Cart"));
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	
	public void close() {
    	Browser.close();
    }
	
	 public AdminSearchInAdmin search() {
		 AdminSearchInAdmin search = new AdminSearchInAdmin();
	    	return search;
	 }
}
