package com.avactis.test.integration.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;

import com.avactis.test.integration.storepageobjects.Browser;

public class JSFunctions {
	
	public static boolean onMouseOver(WebElement element)
	{
		boolean result = false;
		try
		{
			String mouseOverScript = "if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false); arguments[0].dispatchEvent(evObj);} else if(document.createEventObject) { arguments[0].fireEvent('onmouseover');}";
			JavascriptExecutor js = (JavascriptExecutor) Browser.driver();
			js.executeScript(mouseOverScript, element);
			System.out.println("Line post execution of JS command");
			result = true;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("Inside exception of OnMousdeOver command");
			result = false;
		}
		return result;
	}
	
	public static void moveToElement(WebElement element, By locator) {
		if (!(Browser.driver() instanceof FirefoxDriver) || !(Browser.driver() instanceof SafariDriver)) {
			Actions builder = new Actions(Browser.driver());
			builder.moveToElement(element).perform();
		}
		else {
			JavascriptExecutor js = (JavascriptExecutor) Browser.driver();
			String locatorType = locator.toString().substring(3);
			String elem = "var elem = document;";
			if (locatorType.startsWith("id")) {
				elem = "var elem = document.getElementById(\""+locatorType.substring(4)+"\");";
			}
			else if (locatorType.startsWith("xpath")) {
				String snippet = "document.getElementByXPath = function(sValue) { var a = this.evaluate(sValue, this, null, XPathResult.ORDERED_NODE_SNAPSHOT_TYPE, null); if (a.snapshotLength > 0) { return a.snapshotItem(0); } }; ";
				js.executeScript(snippet);
				elem = "var elem = document.getElementByXPath(\""+locatorType.substring(7)+"\");";
			}
			else if (locatorType.startsWith("className")) {
				elem = "var elem = document.getElementsByClassName(\""+locatorType.substring(14)+"\")[0];";
			}
			String mouseOverScript = elem + " if(document.createEvent){var evObj = document.createEvent('MouseEvents');evObj.initEvent('mouseover', true, false);" +
					" elem.dispatchEvent(evObj);} else if(document.createEventObject) { elem.fireEvent('onmouseover');}";
			js.executeScript(mouseOverScript);
		}
	}
	
	
}
