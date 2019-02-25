package com.avactis.test.integration.storepageobjects;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

public class SearchResults extends LoadableComponent<SearchResults> {

	private String query;
	
	public SearchResults(String query){
		PageFactory.initElements(Browser.driver(),this);
	}
	
	@Override
    public void isLoaded() {
    	assertTrue(Browser.driver().getTitle().equals("Avactis Demo Store"));
    }

	@Override
	protected void load() {
		// TODO Auto-generated method stub
	}
	
	public List<String> getProducts() {
		
		List<String> products = new ArrayList<String>();
		/*
		//List<WebElement> productList = Browser.driver().findElements(By.xpath("//*[@id='rso']/div[2]/li/div/h3/a"));
		List<WebElement> productList = Browser.driver().findElements(By.cssSelector("u1.products-grid > li"));
		int totalSearchItems = productList.size();
		System.out.println("No. of elements in product list - " + totalSearchItems);
	//	int i = 1;
		for(WebElement item : productList)
		{
		//	System.out.println("Iteration - "+i);
			products.add(item.findElement(By.cssSelector("h2 > a")).getText());
			System.out.println(item.findElement(By.cssSelector("li > a")).getText());
		//	i = i+1;
		//	if (i>totalSearchItems) {break;}
		}
		*/
		return products;
	}
	
	public void close() {
    	Browser.close();
    }
	
	 public SearchProduct search() {
	    	SearchProduct search = new SearchProduct();
	    	return search;
	 }
}
