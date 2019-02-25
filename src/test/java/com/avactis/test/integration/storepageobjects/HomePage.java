package com.avactis.test.integration.storepageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.avactis.test.integration.utility.ConfigProperties;
//import com.avactis.test.integration.utility.Constant;
import com.avactis.test.integration.utility.JSFunctions;
import com.avactis.test.integration.utility.WaitTool;
import com.avactis.test.integration.utility.WebElementExtender;







//import pageObjectScriptver3.Search;
import static org.testng.Assert.*;

public class HomePage extends LoadableComponent<HomePage> {
	//static String url = Constant.URL;
	
	String url;
	//private static String title = Constant.HomePageTitle;
	private static String title = ConfigProperties.getProperty("HOMEPAGE_TITLE");
	@FindBy(how= How.XPATH, using="//a[contains(@href,'sign-in.php')]")	
	WebElement signInLink;
	
	@FindBy(how= How.XPATH, using="//a[contains(@href,'sign-in.php?')]")	
	WebElement signOutLink;
	
	
	@FindBy(how= How.XPATH, using="//a[contains(@href,'cart.php')]")	
	WebElement myCartLink;
	
	public HomePage(){
		url = ConfigProperties.getProperty("STORE_URL");
		System.out.println("URL is " + url);
		PageFactory.initElements(Browser.driver(), this);
	 }
		
	@Override
    public void load() {
        Browser.open(url);
	}
	
	@Override
    public void isLoaded() {
        WaitTool.waitForTitle(title);
		assertEquals(title, Browser.driver().getTitle());
		//assertTrue(Browser.driver().getTitle().equals(title));
    }	
	
	public void close() {
        Browser.close();
    }
	
	public SignInPage goToSignInPage()
	{
		signInLink.click();
		SignInPage signInPage = new SignInPage();
		return signInPage;
		
	}
	
	public RegistrationFormPage goToRegistrationPageUsingUrl()
	{
		String constructedUrl = url + "/register.php";
		Browser.driver().get(constructedUrl);
		RegistrationFormPage registrationFormPage = new RegistrationFormPage();
		return registrationFormPage;
		
	}
	
	public ProductInfo showProductCategory(String CategoryName) {
		//clickProductCategory(CategoryName);
		return new ProductInfo();
	}
   
	public SearchProduct search() {
	    	SearchProduct search = new SearchProduct();
	    	return search;
    }
	
	public ProductInfo ProductInfo(String ProductName) {
	
	//	PageFactory.initElements(Browser.driver(), this);
	    WebDriverWait waitForProductText = new WebDriverWait(Browser.driver(),30);
	    waitForProductText.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(ProductName)));
		Browser.driver().findElement(By.partialLinkText(ProductName)).click();
		ProductInfo productinfo = new ProductInfo();
		return productinfo;
	}
	
	/* public ProductList goToProductPageUsingMenuAndSubMenu(String categoryID, String subCategoryID) {
		 //For juicehub theme use the following class
		 //String className = "nav-container visible-desktop";
		 //WebElement headerNavigation = Browser.driver().findElement(By.xpath("//div[@class='nav-container visible-desktop']"));
		 //WebElementExtender.highlightElement(headerNavigation);
		 
		  WebElement headerNavigation = Browser.driver().findElement(By.className("header-navigation"));
		  WebElementExtender.highlightElement(headerNavigation);
		  Actions builder=new Actions(Browser.driver());
		  String xpathForMenu = "//a[contains(@href,'" + categoryID + "')]";
		  System.out.println("Xpath for main menu item - " + xpathForMenu);
	      WebElement category=headerNavigation.findElement(By.xpath(xpathForMenu));		
	      //WebElementExtender.highlightElement(category);
	      builder.moveToElement(category).build().perform();
		  String xpathForSubMenu = "//a[contains(@href,'" + subCategoryID + ".html')]";
		  System.out.println("Xpath for Sub menu is - " + xpathForSubMenu);
		  WebDriverWait wait = new WebDriverWait(Browser.driver(),30);
		  wait.until(ExpectedConditions.visibilityOf(headerNavigation.findElement(By.xpath(xpathForSubMenu))));
		  WebElement subCategoryElement = headerNavigation.findElement(By.xpath(xpathForSubMenu));
		  
		  String subMenuText = subCategoryElement.getText();
		  System.out.println("Sub Category is - " + subMenuText);
		  subCategoryElement.click();
		  //Browser.driver().findElement(By.xpath("//input[@value='Add To Cart']/preceding::a[contains(@href,'pid8.html')]")).click();
		  ProductList productList = new ProductList(subMenuText);
		  return productList;
	  }
*/
	
	 public ProductList goToProductPageUsingUrl(String productPageContent,String subcategoryName, String subCategoryID) {
		//String currentUrl;
		//currentUrl = Browser.driver().getCurrentUrl();
		String constructedUrl = url + "/product-list.php?"+ subcategoryName + productPageContent.trim() + subCategoryID + ".html" ;
		System.out.println(constructedUrl);
		Browser.driver().get(constructedUrl); 		
		ProductList productList = new ProductList(subcategoryName);
		return productList;
	 }
	 
	 public ProductList goToProductPageUsingMenuAndSubMenu(String categoryID, String subCategoryID) {
		 //For juicehub theme use the following class
		 //String className = "nav-container visible-desktop";
		 //WebElement headerNavigation = Browser.driver().findElement(By.xpath("//div[@class='nav-container visible-desktop']"));
		 //WebElementExtender.highlightElement(headerNavigation);
		  if (WaitTool.waitForElementPresentBy(By.className("header-navigation"), 30))
		  {
			  WebElement headerNavigation = Browser.driver().findElement(By.className("header-navigation"));
		//	  WebElementExtender.highlightElement(headerNavigation);
			  Actions builder=new Actions(Browser.driver());
			  String xpathForMenu = "//a[contains(@href,'" + categoryID + "')]";
			  System.out.println("Xpath for main menu item - " + xpathForMenu);
		      WebElement category=headerNavigation.findElement(By.xpath(xpathForMenu));		
		      //WebElementExtender.highlightElement(category);
			  String xpathForSubMenu = "//a[contains(@href,'" + subCategoryID + ".html')]";
			  
			  //
			  System.out.println("Xpath for Sub menu is - " + xpathForSubMenu);
			 builder.moveToElement(category).build().perform();
			  //JSFunctions.onMouseOver(category);
		//	  JSFunctions.moveToElement(category,By.xpath(xpathForMenu));
			  int i=0;
			  while (i<5)
			  {
				  //WebElement subProduct=driver.findElement(By.xpath(xpathForSubMenu));
				 // subProduct.isdisplayed
				  if (WaitTool.isElementPresentAndDisplay(Browser.driver(),By.xpath(xpathForSubMenu)))
				  {
					  WebElement subCategoryElement = headerNavigation.findElement(By.xpath(xpathForSubMenu));
					  String subMenuText = subCategoryElement.getText();
					  System.out.println("Sub Category is - " + subMenuText);
					  
					  builder.moveToElement(subCategoryElement).build().perform();
					//  JSFunctions.onMouseOver(subCategoryElement);
					  subCategoryElement.click();
			//		  WebElement subCategoryElement = headerNavigation.findElement(By.xpath(xpathForSubMenu));
					  
			//		  
			//		  subCategoryElement.click();
					  //Browser.driver().findElement(By.xpath("//input[@value='Add To Cart']/preceding::a[contains(@href,'pid8.html')]")).click();
					  ProductList productList = new ProductList(subMenuText);
					  return productList;
					  
				  }
				  else
				  {
					  //go to menu item again
					  builder.moveToElement(category).build().perform();
					//  JSFunctions.onMouseOver(category);
					  i=i+1;
					  continue;
				  }
			  }
			  System.out.println("Submenu was not visible or available");
			  return null;
				
		  }
		  else
		  {
			  System.out.println("Main Menu Display timed out");
			  return null;
		  }
	  }
	 
	 public MyCart goToMyCart()
	 {
		 myCartLink.click();
		 MyCart myCart = new MyCart();
		 return myCart;
	 }
	 public MyCart goToMyCartUsingUrl()
	 {
		// myCartLink.click();
		 Browser.driver().get(url +"/cart.php");
		 MyCart myCart = new MyCart();
		 return myCart;
	 }

	 public boolean isUserLoggedIn()
		{			
		 	if ( WaitTool.waitForElementPresent(signOutLink, 5))
		 	{		 	
		 		return true;
		 	}
		 	else
		 	{		 		
		 		return false;
		 	}
		}
	 
	 public SignInPage doSignOut()
	 {
		 if (isUserLoggedIn())
		 {
			signOutLink.click();
			SignInPage signInPage = new SignInPage();
			return signInPage;
		 }
		 else
		 {
			 System.out.println("Nobody is singed in so cannot signOut");
			 return null;
		 }
	 }
}
