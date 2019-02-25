package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.testng.Assert.*;

import com.avactis.test.integration.utility.WaitTool;

public class ShoppingCart {
	//need to identify the alternate strategy instead of class-name or create a mapping file of class names vs themes
//	@FindBy(how= How.CLASS_NAME, using="top-cart-block")	
//	WebElement cartImage;

	@FindBy(how= How.CLASS_NAME, using="top-cart-info-count")	
	WebElement cartImage;

	
	@FindBy(how= How.CSS, using=".btn.btn-primary")	
	WebElement proceedToCheckout;
		
	public ShoppingCart(){	    
		PageFactory.initElements(Browser.driver(), this);
	   
	  }
		
	public boolean goToCartImage(int noOfItemsOnCart) {
		
	//	System.out.println("Start of check-out function");
		Actions builder = new Actions(Browser.driver());
		WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
		WebElement noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[contains(@class,'top-cart-info-count')]"));
	//	String noOfItems = noOfItemsInCartElement.getText();
//		System.out.println("Cart items before wait " + noOfItems);
		//int i=0;
	//	wait.until(ExpectedConditions.textToBePresentInElement(noOfItemsInCartElement, "0 items"));
	//	wait.until(ExpectedConditions.textToBePresentInElement(noOfItemsInCartElement, noOfItemsOnCart + " items"));
	//	noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[contains(@class,'top-cart-info-count')]"));
	//	noOfItems = noOfItemsInCartElement.getText();
	//	System.out.println("Cart items after wait " + noOfItems);
/*
		while (noOfItems.equals("0 items") || i>100)
		{
			noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[contains(@class,'top-cart-info-count')]"));
			noOfItems = noOfItemsInCartElement.getText();
			i=i+1;
		}
		*/
//		wait.until(ExpectedConditions.textToBePresentInElement(locator, "0 items".contentEquals(sb))
				//Browser.driver().findElement(By.xpath("//a[contains(@class,'top-cart-info-count')]")).getText().equals("0 items")));
		////a[contains(@class,'top-cart-info-count')]
//	    WebElement cartImage = Browser.driver().findElement(By.className("fa fa-shopping-cart"));
	    Action mouseover = builder.moveToElement(noOfItemsInCartElement).build();
	    mouseover.perform();
	//    System.out.println("No. of cart items");
/*
	    if (!noOfItems.equals("0 items"))
		{
			return true;
		} else {
			return false;
		}
	*/
	    return true;
	}
		
	
	public Checkout doCheckout() {
			//WebDriverWait wait = new WebDriverWait(Browser.driver(),50);
		   // System.out.println("Mouse over top cart area done");
		    //WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
			if (!(WaitTool.waitForElementPresent(proceedToCheckout,30)))
				{
					goToCartImage(1);
				}
		//	wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
		 //   System.out.println("waiting for checkout button on top cart");
		    proceedToCheckout.click();
		    System.out.println("Clicked on checkout button done");
		    Checkout checkout = new Checkout();
		    return checkout;
		
	}
	
	/*public Checkout goToCartImageAndDoCheckout() {
		
		
		 * 	String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			WebElement someElem = driver.findElement(By.id("some Id")); //replace with your own WebElement call/code here
			js.executeScript(mouseOverScript, someElem);
		 
		
		
		Actions builder = new Actions(Browser.driver());
	    builder.moveToElement(cartImage).build().perform();
	    if (!(WaitTool.waitForElementPresent(proceedToCheckout,30)))
		{
			goToCartImage(1);
		}
	    builder.moveToElement(proceedToCheckout).build().perform();
//		mouseover.perform();
		proceedToCheckout.click();
	//	proceedToCheckout.click();
	    System.out.println("Clicked on checkout button done");
	    Checkout checkout = new Checkout();
	    return checkout;
	
}*/
	
public Checkout goToCartImageAndDoCheckout() {
		
		
		 	String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) Browser.driver();
		//	WebElement someElem = driver.findElement(By.id("some Id")); //replace with your own WebElement call/code here
			js.executeScript(mouseOverScript, cartImage);
		 
		
		
		Actions builder = new Actions(Browser.driver());
	    builder.moveToElement(cartImage).build().perform();
	    if (!(WaitTool.waitForElementPresent(proceedToCheckout,30)))
		{
			goToCartImage(1);
		}
//	    builder.moveToElement(proceedToCheckout).build().perform();
//		mouseover.perform();
	    js.executeScript(mouseOverScript, proceedToCheckout);
		proceedToCheckout.click();
	//	proceedToCheckout.click();
	    System.out.println("Clicked on checkout button done");
	    Checkout checkout = new Checkout();
	    return checkout;
	
}
	
	public  MyCart ViewCart(){
		//assertEquals("12","123");
		Actions builder = new Actions(Browser.driver());
        Action mouseover = builder.moveToElement(cartImage).build();
	    mouseover.perform();
	    WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
	 //   wait.until(ExpectedConditions.visibilityOf(proceedToCheckout));
	    //click on view cart button
	    Browser.driver().findElement(By.xpath("//a[contains(@href,'cart')]")).click();
	    MyCart cartview=new MyCart();
	    return cartview;
	  
	    
		
		
		
		
	}


}
