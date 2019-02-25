package com.avactis.test.integration.storepageobjects;


	import static org.testng.Assert.assertTrue;

	import java.util.ArrayList;
import java.util.List;

	import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.avactis.test.integration.utility.AvactisUtilities;
import com.avactis.test.integration.utility.ConfigProperties;
//import com.avactis.test.integration.utility.Constant;
import com.avactis.test.integration.utility.WaitTool;

	public class ProductList  extends LoadableComponent<SearchResults> {

		private String menuSelection;
		
		public ProductList(String menuSelection){
			//menuSelection is used to set the page title. The calling class should pass the menu item selected in their call to this constructor
			PageFactory.initElements(Browser.driver(),this);
			this.menuSelection = menuSelection;
			get();
		}
		
		@Override
	    public void isLoaded() {
			if (menuSelection != "")
				assertTrue(Browser.driver().getTitle().equals(menuSelection + " - " + ConfigProperties.getProperty("HOMEPAGE_TITLE")));
			else
				assertTrue(Browser.driver().getTitle().equals(ConfigProperties.getProperty("HOMEPAGE_TITLE")));
	    }

		@Override
		protected void load() {
			// TODO Auto-generated method stub
		}
		
		public List<String> getProducts() {
			//NOT YET IMPLEMENTED
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
		 
		 public ProductInfo goToProductDetailsUsingDetailsButton(String productID)
		 {
			 //NOT YET IMPLEMENTED. 
			 //Need to go to a product and click on detail
			 //button or click on product link to go to details.
			 ProductInfo productInfo = new ProductInfo();
			 return productInfo;
		 }
		 
		 public ProductInfo goToProductDetailsUsingProductLink(String productID)
		 {
			 //NOT YET IMPLEMENTED. 
			 //Need to go to a product and click on detail
			 //button or click on product link to go to details.
			 ProductInfo productInfo = new ProductInfo();
			 return productInfo;
		 }
		 
		 public boolean clickAddToCartButton(String productID) {
			  String noOfItemsBeforeAddingCurrentItem = AvactisUtilities.getCartNoOfItems(); 
			  String AddtoCartButtonForGivenProduct = "ProductForm_" + productID;
			 //Added for IE wait Element
			  ////*[@id='ProductForm_97']/descendant::input[contains(@value,'Add To Cart')]
			  WaitTool.waitForElementPresentBy(Browser.driver(), By.id(AddtoCartButtonForGivenProduct), 30);
			  WebElement formElementOfAddToCartButton;
			  WebElement formElementOfDetailsButton;
			  try {
				  formElementOfAddToCartButton = Browser.driver().findElement(By.xpath("//*[@id='"+ AddtoCartButtonForGivenProduct + "']/descendant::input[contains(@value,'Add To Cart')]"));
				  formElementOfAddToCartButton.click();
			  } catch (NoSuchElementException e)
			  {
				  try {
					  formElementOfDetailsButton = Browser.driver().findElement(By.xpath("//*[@id='"+ AddtoCartButtonForGivenProduct + "']/descendant::a[contains(text(),'Details')]"));
					  formElementOfDetailsButton.click();
					  //write more code to handle the details page
				  } catch (NoSuchElementException e1)
				  {
					  System.out.println("Problem with page loading");
					  return false;
				  }
			  }
			  //String xpathForProductIDAddToCartButton = "//a[contains(@href,'" + productID + ".html')]/preceding::input[contains(@value,'Add To Cart')]";
//			  WebElement addToCartButton = Browser.driver().findElement(By.xpath(xpathForProductIDAddToCartButton));
		//	  WebElement addToCartButton = formElementOfAddToCartButton.findElement(By.xpath("//input[contains(@value,'Add To Cart')]"));
		//	  formElementOfAddToCartButton.click();
			  /* Need to add some wait for jquery to get executed to populate the cart item
			  * the following statement may get executed before the cart no. of items gets
			  * refershed. Need to understand how to extract this status from the web page
			  */
			  /*
			   * Also need to check for alert which shows the add to cart was successful or failure
			   */
			 // Browser.driver().switchTo().alert();
			  WebDriverWait wait = new WebDriverWait(Browser.driver(),50);
		  wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ajax_message_box_text")));
			  String noOfItemsAfterAddingCurrentItem;
			  noOfItemsAfterAddingCurrentItem = AvactisUtilities.getCartNoOfItems();
/*
			  //put a wait to change the items and then call below function 
			  boolean isReady = WaitTool.waitForJQueryProcessing(Browser.driver(),5); 
//			  boolean isReady = WaitTool.waitForJavaScriptCondition(Browser.driver(),
		//			  "return (xmlhttp.readyState >= 2 && xmlhttp.status == 200)", 5); 
			  //
			  String noOfItemsAfterAddingCurrentItem;
			  if (isReady) 
			  {
				  noOfItemsAfterAddingCurrentItem = AvactisUtilities.getCartNoOfItems();
			  }
			  else
			  {
				  noOfItemsAfterAddingCurrentItem = noOfItemsBeforeAddingCurrentItem;
			  }
*/
			  System.out.print("Before add to cart no. of items " + noOfItemsBeforeAddingCurrentItem+" and ");
			  System.out.println("Before add to cart no. of items " + noOfItemsAfterAddingCurrentItem);
			  if (!noOfItemsBeforeAddingCurrentItem.equals(noOfItemsAfterAddingCurrentItem))
			  {
				 
				  return true;
			  }
			  else
			  {
				  return false;
			  }
		  }
		
		 
		 
	}
