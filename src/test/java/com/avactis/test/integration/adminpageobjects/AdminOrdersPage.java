package com.avactis.test.integration.adminpageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;

import com.avactis.test.integration.storepageobjects.Browser;
import com.avactis.test.integration.utility.WebTable;

public class AdminOrdersPage extends LoadableComponent<AdminOrdersPage>{

	@FindBy (id = "datatable_orders")
	@CacheLookup
	WebElement orderTable;
	
	private static String title = "Orders - Avactis Shopping Cart";
	private WebTable ordersTable;
	
	public AdminOrdersPage(){
		PageFactory.initElements(Browser.driver(), this);
		ordersTable = new WebTable(orderTable);
	 }
	
	@Override
    public void load() {
//        Browser.open(url);
	}
	
	@Override
    public void isLoaded() {
        assertTrue(Browser.driver().getTitle().equals(title));
    }
	
	public void searchByOrderID(String orderID)
	{
		ordersTable.searchElementInARowBasedOnColumnValue(1,orderID,6);
		
	}
	
	
	
	public void getAllOrders()
	{
		int noOfOrders = ordersTable.getRowCount();
		String OrderNo = ordersTable.getCellData(7, 2);
		System.out.println("No. of Orders - " + noOfOrders);
		System.out.println("Order ID at Row No 7 and Column No 2 is - " + OrderNo);
	}
}
