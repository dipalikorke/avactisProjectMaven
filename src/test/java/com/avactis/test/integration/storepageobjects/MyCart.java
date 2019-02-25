//verify if the items to the cart are present in the cart
package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.By;

import static org.testng.Assert.*;
//import org.testng.Assert;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.avactis.test.integration.utility.ConfigProperties;
//import com.avactis.test.integration.utility.Constant;
import com.avactis.test.integration.utility.WaitTool;
import com.avactis.test.integration.utility.WebTable;



public class MyCart extends LoadableComponent<MyCart> {
	
	@FindBy(how= How.XPATH, using="//a[contains(@href,'product-list.php')]")	
	WebElement countinueShoppingButton;

	@FindBy(how= How.XPATH, using="//a[contains(@href,'checkout.php')]")	
	WebElement checkoutButton;
	
	
	private StringBuffer verificationErrors = new StringBuffer();
	  WebElement table =Browser.driver().findElement(By.xpath("//table"));
	  WebTable tableFunctions=new WebTable(table);
	  int rowCount=tableFunctions.getRowCount();
	  int colCount=tableFunctions.getColumnCountFromHeader();
	
	  public MyCart()
	  {
		  PageFactory.initElements(Browser.driver(), this);
		  get();
	  }
	  
	@Override
	protected void isLoaded() throws Error {
		//Write a code to check Continue shopping button is present
		// TODO Auto-generated method stub
		WaitTool.waitForElementPresent(countinueShoppingButton, 5);
	}
		
	@Override
	protected void load() {
		// TODO Auto-generated method stub
		
	}

	//veifying if the product which was added to the cart is present in My Cart
    public void verifyProductInTable() {
		String url=Browser.driver().getCurrentUrl();
		String source= Browser.driver().getPageSource();
		System.out.println("Current URL=" +url);
		System.out.println("Number of rows:" +rowCount);
		System.out.println("Number of columns:" +colCount);
	
		try {	
		  
		 		  
			for(int i=1;i<rowCount;i++){  //<=
			
			  WebElement text=tableFunctions.getElement(i,1);  //i,0
			  if(text.getText().contains("Apple MacBook Air")){
                 System.out.println("link:" +text.getAttribute("href"));	//not returning URL of the particular product		
				 System.out.println("Element is added to the cart");
				
			  }
			  else
			  {
				  System.out.println("link:" +text.getAttribute("href"));	
				  System.out.println("Element not added to the cart");
			  }
			}
		
		} catch (Error e) {
			//Capture and append Exceptions/Errors
			verificationErrors.append(e.toString());
		}
	  
	}

//verify number of products added to the cart
	public void verifyTotalNumberOfProducts(){
	int productCount=rowCount-1; //rowCount consider header row also so do -1
	System.out.println("Number of distinct products added to the cart:" +productCount);
	
	Select qty=new Select(Browser.driver().findElement(By.name("quantity_in_cart[0]")));
	
	for(int j=1;j<=productCount;j++){
		String quantityOfProuduct=qty.getFirstSelectedOption().getText();
		System.out.println("Quantity of the selected product is=" +quantityOfProuduct); //should include assert
	}
}
	

	//verifying if the total amount is correct
	public void totalAmount(){
	    WebElement total=Browser.driver().findElement(By.xpath("//li/strong[contains(@class,'shopping-total-price price')]"));
		System.out.println("Amount is:" +total.getText());
		assertEquals("$1,799.00", total.getText());
		
	}
	public Checkout doCheckout() {
	    checkoutButton.click();
	    System.out.println("Clicked on checkout button done");
	    Checkout checkout = new Checkout();
	    return checkout;
	}
	
	public Checkout doCheckoutUsingUrl() {
		Browser.driver().get(ConfigProperties.getProperty("STORE_URL")+"/checkout.php");
	    System.out.println("Navigating to checkout url");
	    Checkout checkout = new Checkout();
	    return checkout;
	}
	
	public boolean IsNotEmpty()
	{
		if (rowCount > 0)
			return true;
		else
			return false;
	}
	
  }

  
 

   
  
