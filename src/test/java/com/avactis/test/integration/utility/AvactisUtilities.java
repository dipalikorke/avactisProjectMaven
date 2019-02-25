package com.avactis.test.integration.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.storepageobjects.Browser;

public class AvactisUtilities {

	  public static String getCartNoOfItems()
	  {
		  WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
		  WebElement noOfItemsInCartElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@class='top-cart-info-count']")));
		  //WebElement noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[@class='top-cart-info-count']"));
		  //WebElement noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[contains(@class,'top-cart-info-count')]"));
		  System.out.println("No. of items in the cart " + noOfItemsInCartElement.getText());
		  return noOfItemsInCartElement.getText();
		  
	  }
	
	
}
