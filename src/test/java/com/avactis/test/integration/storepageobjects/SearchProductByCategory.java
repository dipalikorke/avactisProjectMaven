//selection category as "Computers" and sub category as "Notebooks" and clicking on "Add to cart" button.
package com.avactis.test.integration.storepageobjects;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.annotations.Test;

public class SearchProductByCategory {
  @Test
  public void SearchUsingCategory(String categoryID, String subCategoryID, String productID) {
	  WebElement headerNavigation = Browser.driver().findElement(By.className("header-navigation"));
	  Actions builder=new Actions(Browser.driver());
	  String xpathForMenu = "//a[contains(@href,'" + categoryID + "')]";
	  System.out.println("Xpath for main menu item - " + xpathForMenu);
      WebElement category=headerNavigation.findElement(By.xpath(xpathForMenu));		
	  builder.moveToElement(category).build().perform();
	  String xpathForSubMenu = "//a[contains(@href,'" + subCategoryID + ".html')]";
	  System.out.println("Xpath for Sub menu is - " + xpathForSubMenu);
	  headerNavigation.findElement(By.xpath(xpathForSubMenu)).click();
	  //Browser.driver().findElement(By.xpath("//input[@value='Add To Cart']/preceding::a[contains(@href,'pid8.html')]")).click();
	  WebDriverWait wait = new WebDriverWait(Browser.driver(),20);
	  String xpathForProductID = "//a[contains(@href,'" + productID + ".html')]/preceding::input[contains(@value,'Add To Cart')]";
	  WebElement addToCartButton = null;//wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathForProductID)));
	  addToCartButton.click();
	//  Browser.driver().findElement(By.xpath("//a[contains(@href,'" + productID + ".html')]/preceding::input[contains(@value,'Add To Cart')]")).click();
	  
  }
  
  /*
   *  public void SearchUsingCategory(String categoryID, String subCategoryID, String productID) {
	  WebElement headerNavigation = driver.findElement(By.className("header-navigation"));
	  Actions builder=new Actions(Browser.driver());
	  String xpathForMenu = "//a[contains(@href,'" + categoryID + "')]";
	  System.out.println("Xpath for main menu item - " + xpathForMenu);
      WebElement category=Browser.driver().findElement(By.xpath(xpathForMenu));		
	  builder.moveToElement(category).build().perform();
	  String xpathForSubMenu = "//a[contains(@href,'" + categoryID + "')]";
	  Browser.driver().findElement(By.xpath("//a[contains(@href,'" + subCategoryID + ".html')]")).click();
	  //Browser.driver().findElement(By.xpath("//input[@value='Add To Cart']/preceding::a[contains(@href,'pid8.html')]")).click();
	  Browser.driver().findElement(By.xpath("//a[contains(@href,'" + productID + ".html')]/preceding::input[@value='Add To Cart']")).click();
	  
  }

   */
  
}
