package com.avactis.test.integration.storepageobjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.utility.WaitTool;

public class ProductInfo {
	
	@FindBy(how= How.XPATH, using="//input[contains(@class,'button_add_to_cart')]")	
	WebElement addToCart;
	
	  public ProductInfo(){
		  PageFactory.initElements(Browser.driver(), this);    
	    
	  }
	  
	  public ShoppingCart addToShoppingCart(){		  
		  ShoppingCart shoppingcart = new ShoppingCart();
		  return shoppingcart;
	  }
	  
	  public boolean isButtonPresent(String button){
/*		String xpath="//input[contains(@class,\'"+button+"\')]";
	    return selenium.findElements(By.xpath(xpath)).size()>0;*/
		  return false;
	  }
	  
	  public ShoppingCart doAddToCart() {
		  /*Pending changes
		   * - This function should not return the ShoppingCart object but should be void
		   * Once this change is made make sure calling class explicitly creates ShoppingCart object
		   * Also call AvactisUtilities.getCartNoOfItems instead of local method. 
		   * Delete the local copy of the same function from the current class
		   */
		  String noOfItemsBeforeAddingCurrentItem = getCartNoOfItems(); 
		  WebDriverWait waitForAddToCartButton = new WebDriverWait(Browser.driver(),30);
		  waitForAddToCartButton.until(ExpectedConditions.elementToBeClickable(addToCart));
		  System.out.println("After waiting for add to cart element");
		  addToCart.click();
		  //put a wait to change the items and then call below function 
		  boolean isReady = WaitTool.waitForJQueryProcessing(Browser.driver(),5); 
//		  boolean isReady = WaitTool.waitForJavaScriptCondition(Browser.driver(),
	//			  "return (xmlhttp.readyState >= 2 && xmlhttp.status == 200)", 5); 
		  //
		  String noOfItemsAfterAddingCurrentItem;
		  if (isReady) 
		  {
			  noOfItemsAfterAddingCurrentItem = getCartNoOfItems();
		  }
		  else
		  {
			  noOfItemsAfterAddingCurrentItem = noOfItemsBeforeAddingCurrentItem;
		  }
		  System.out.print("Before add to cart no. of items" + noOfItemsBeforeAddingCurrentItem+" and ");
		  System.out.println("Before add to cart no. of items" + noOfItemsAfterAddingCurrentItem);
		  if (noOfItemsBeforeAddingCurrentItem!=noOfItemsAfterAddingCurrentItem)
		  {
			  ShoppingCart shoppingcart = new ShoppingCart();
			  return shoppingcart;
		  }
		  else
		  {
			  ShoppingCart shoppingcart = null;
			  return shoppingcart;
		  }
	  }
	  /*
	   * Delete the function getCartNoOfItems as it is available in common utility. 
	   * Change the local references from this class as well.
	   */
	  public String getCartNoOfItems()
	  {
		  WebElement noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[@class='top-cart-info-count']"));
		  //WebElement noOfItemsInCartElement = Browser.driver().findElement(By.xpath("//a[contains(@class,'top-cart-info-count')]"));
		  System.out.println("No. of items in the cart " + noOfItemsInCartElement.getText());
		  return noOfItemsInCartElement.getText();
		  
	  }
	  
	  
	  
}
