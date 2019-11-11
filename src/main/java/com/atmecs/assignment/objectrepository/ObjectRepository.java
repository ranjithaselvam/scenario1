package com.atmecs.assignment.objectrepository;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
/**
 * Purpose:A common storage location for all objects and the locators used to
 * uniquely identify web elements(segregation of objects from test cases)
 * 
 * @author ranjitha.selvam
 *
 */

public class ObjectRepository {
	

		public List<WebElement> values;
		public WebElement locator = null;

		/**
		 * uniquely identify a (one) web element within the web page(findElement)
		 * 
		 * @param driver
		 * @param locatorName
		 * @return
		 */

		public WebElement findObject(WebDriver driver, String locatorName) {

			try {
				String[] locatorType = locatorName.split(":");
				switch (locatorType[0]) {
				case "Id":
					locator = driver.findElement(By.id(locatorType[1]));
					break;
				case "Name":
					locator = driver.findElement(By.name(locatorType[1]));
					break;
				case "CssSelector":
					locator = driver.findElement(By.cssSelector(locatorType[1]));
					break;
				case "LinkText":
					locator = driver.findElement(By.linkText(locatorType[1]));
					break;
				case "PartialLinkText":
					locator = driver.findElement(By.partialLinkText(locatorType[1]));
					break;
				case "TagName":
					locator = driver.findElement(By.tagName(locatorType[1]));
					break;
				case "Xpath":
					locator = driver.findElement(By.xpath(locatorType[1]));
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return locator;
		}

	}

	


