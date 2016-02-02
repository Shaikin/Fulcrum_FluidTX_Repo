package com.proj.library;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.proj.base.TestBase;
import com.proj.util.LocatorType;

public class ElementMethods extends TestBase{

	/**
	 * Fetches the webelement
	 * @param driver
	 * @param locatorType
	 * @param locator
	 * @return
	 */
	public static WebElement fetchElement(WebDriver driver,String locatorType, String locator){
		WebElement element=null;
		try{

			LocatorType str= LocatorType.valueOf(locatorType.toUpperCase());

			switch (str){

			case XPATH:
				element=driver.findElement(By.xpath(locator));
				break;
			case ID:
				element=driver.findElement(By.id(locator));
				break;
			case NAME:
				element= driver.findElement(By.name(locator));						 
				break;
			}

		}				 

		catch (ElementNotVisibleException env) 
		{
			logsObj.logError("fetchElement-Element "+locator+" is not visible..due to -->",env);
			throw env;
		}

		catch (Exception genException)
		{
			logsObj.logError("fetchElement: General Exception error .."+"while fecth element "+locator+" due to -->  ",genException);
		}

		logsObj.log("fetchElement-Successfully returned the Object "+locator);
		return element; //return the webelement

	}
	/**
	 * Fetches the weblements
	 * @author khshaik
	 * @param driver
	 * @param locatorType
	 * @param locator
	 * @return
	 */

	public static List<WebElement> fetchElements(WebDriver driver,String locatorType, String locator){
		List<WebElement> listElement = null;
		try{

			LocatorType str= LocatorType.valueOf(locatorType.toUpperCase());

			switch (str){

			case XPATH:
				listElement=driver.findElements(By.xpath(locator));
				break;
			case ID:
				listElement=driver.findElements(By.id(locator));
				break;
			case NAME:
				listElement= driver.findElements(By.name(locator));						 
				break;
			}

		}				 

		catch (ElementNotVisibleException env) 
		{
			logsObj.logError("fetchElements:Element "+locator+" is not visible..due to -->",env);
			throw env;
		}

		catch (Exception genException)
		{
			logsObj.logError("fetchElement: General Exception error .."+"while fecth element "+locator+" due to -->  ",genException);
		}

		logsObj.log("Successfully returned the Object "+locator);
		return listElement; //return the webelement

	}

	/**
	 * Find a Radio button from the Radio Group
	 * @author sahamed
	 * @Date Feb 18 2014
	 * @param identifyBy
	 * @param value
	 * @param radioButton
	 * @return input webelement for success and null for failure
	 */

	public static WebElement fetchRadiobuttonFromGroup(WebDriver driver,String identifyBy, String locator,String radioButton)
	{

		WebElement element=null;

		try 
		{

			List<WebElement> listElement= fetchElements(driver,identifyBy, locator); 

			int size=listElement.size();
			if(size==0){
				return element;
			}
			else{

				for (WebElement ele :listElement){
					String str=ele.getAttribute("value");
					if(str.equalsIgnoreCase(radioButton)){
						element=ele;
						break;
					}

				}
			}

		}


		catch (ElementNotVisibleException env) 
		{
			logsObj.logError("FindRadiobutton:Element "+locator+" is not visible..due to -->",env);

		}

		catch (Exception genException)
		{
			logsObj.logError("FindRadiobutton: General Exception error .."+"while fecth element "+locator+" due to -->  ",genException);				        	
		}


		return element;
	}	

	
	
	
	
	
	
	
}
