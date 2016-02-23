package com.proj.utilFulcrum;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.report.reporter.Reporting;

public class KeyMethodsUtil extends TestBase{
	/**
	 * selects an js item from the list
	 * @author khshaik
	 * @date Nov 28 2014
	 * @param driver
	 * @param workFlow
	 * @param Step
	 * @param itemsXpath
	 * @param input
	 * @return input for success , error details for error and false for no item
	 */
	
	public static String js_selectItem(WebDriver driver,String workFlow,String step,String itemsXpath,String input){
		String flag=Constants_FRMWRK.False;
		step=workFlow+" Select Item from dropdown list for "+step;
		String rowNumber="item-";
		int rowCount=0;
		Hashtable<String,String> dropdownItems=new Hashtable<String,String>();

		List<WebElement>elements=FetchWebElement.visibleElementsList(driver, Constants_FRMWRK.FindElementByXPATH, itemsXpath);

		if(elements.size()!=0){
			for (WebElement element:elements){

				String actualtext=element.getText();
				dropdownItems.put(rowNumber+Integer.toString(rowCount), actualtext);
				if(actualtext.equals(input)){
					try{
						logsObj.log("js_selectItem:- actual item text-"+actualtext+"matches with expected item text-"+input+" next is click..");
						try{
							WaitUtil.pause(1);
							element.click();
							flag=actualtext;
							Reporting.logStep(driver, refID, step, "js_selectItem:- Item "+actualtext+" is located and clicked", Constants_FRMWRK.Pass);
							break;
						}catch (StaleElementReferenceException st){
							logsObj.log("js_selectItem:- Item "+actualtext+" is located but unable to click due to state error, will rerun again");
							js_selectItem(driver, workFlow, step, itemsXpath, input);
						}
						
					}catch(Throwable t){
						isTestPass=Constants_FRMWRK.FalseB;
						logsObj.logError("js_selectItem:- Item "+actualtext+" is located but unable to click due to error", t);
						Reporting.logStep(driver, refID, step, "js_selectItem:- Item "+actualtext+" is located but unable to click due to error"+ t, Constants_FRMWRK.Fail);
					}

				}
			}
			if (flag.equalsIgnoreCase(Constants_FRMWRK.False)){
				Reporting.logStep(driver, refID, step, "js_selectItem:- Item "+input+" is not listed , the complete item list available is: "+dropdownItems, Constants_FRMWRK.Fail);
			}
		}else{
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID, step, "js_selectItem:- Item "+input+" is not listed as there are no items for the dropdown_jqx", Constants_FRMWRK.Fail);
		}
		return flag;
	}
	
	
	
	public static String js_selectChoice(WebDriver driver,String workFlow,String step,String itemsXpath,String input){
		String flag=Constants_FRMWRK.False;
		step=workFlow+" Select Item from dropdown list for "+step;
		String rowNumber="item-";
		int rowCount=0;
		String choiceItemsLocator="/div[1]/div/table/descendant :: tr";
		
		Hashtable<String,String> dropdownItems=new Hashtable<String,String>();
		itemsXpath=itemsXpath.replaceAll("containereditableRegion", "containersuggestionsContainer");
		itemsXpath=itemsXpath+choiceItemsLocator;
		
		
		List<WebElement>elements=FetchWebElement.visibleElementsList(driver, Constants_FRMWRK.FindElementByXPATH, itemsXpath);
		if(elements.size()!=0){
			for (WebElement element:elements){

				String actualtext=element.getAttribute("title");
				if(actualtext.contains("[")){
					String[] comp_title=actualtext.split("\\[");
					actualtext=comp_title[0];
				}
				
				dropdownItems.put(rowNumber+Integer.toString(rowCount), actualtext);
				if(actualtext.equals(input)){
					try{
						WaitUtil.pause(1);
						element.click();
						flag=actualtext;
						Reporting.logStep(driver, refID, step, "js_selectChoice:- Choice "+actualtext+" is located and clicked", Constants_FRMWRK.Pass);
						break;
					}catch(Throwable t){
						isTestPass=Constants_FRMWRK.FalseB;
						logsObj.logError("js_selectChoice:- Choice "+actualtext+" is located but unable to click due to error", t);
						Reporting.logStep(driver, refID, step, "js_selectChoice:- Item "+actualtext+" is located but unable to click due to error"+ t, Constants_FRMWRK.Fail);
					}

				}
			}
			if (flag.equalsIgnoreCase(Constants_FRMWRK.False)){
				Reporting.logStep(driver, refID, step, "js_selectChoice:- Choice "+input+" is not listed , the complete choice list available is: "+dropdownItems, Constants_FRMWRK.Fail);
			}
		}else{
			Reporting.logStep(driver, refID, step, "js_selectChoice:- Choice "+input+" is not listed as there are no choices for the dropdown_jqx", Constants_FRMWRK.Fail);
		}



		return flag;
	}
	
	public static String enter_autoSuggestAndSelectChoice(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;			
		String generic_autosuggest_step="Enter Suggestion for ";
		generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;

			//element.click();				
			if(input.equalsIgnoreCase("Any")){
				element.sendKeys(Keys.SPACE);
				element.sendKeys(Keys.BACK_SPACE);
				Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
			}else{
				element=FetchWebElement.waitForElement(driver,locatorType, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
				element.click();
				WaitUtil.pause(300L);
				element.clear();
				WaitUtil.pause(300L);
				element.sendKeys(new String[]{input});					
				WaitUtil.pause(3);
			}									    					
			logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
			Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
			if(!input.equalsIgnoreCase("") ){					
				PageLoadWaitUtil.waitForAjax(driver);
				PageLoadWaitUtil.waitForPageToLoad(driver);
				WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
				flag=KeyMethodsUtil.js_selectChoice(driver, workFlow, Step, objectLocator, input);
			}
			
			return flag;
	}
	public static String enter_autosuggest_browse(WebDriver driver,String testcasename,String workFlow,String step,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_autosuggest_step="Select Suggestion for ";
		generic_autosuggest_step=workFlow+generic_autosuggest_step+step;
		try{
			element.click();
			try{
				//commonMethods.switchToFrameFromDefault(driver, testcaseName, Constants_FRMWRK.FindElementByXPATH, ObjRepository.frame_double);
				ApplicationMethods.switchToLatestDLGframe(driver,testcasename);
				WebElement prvPage=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.choice_prvpage, Constants_TimeOuts.Element_TimeOut);
				if(prvPage!=null && prvPage.isDisplayed()==true){
					prvPage.click();
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
				}
				WebElement choice=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ".//*[text()='"+input+"']", Constants_TimeOuts.Element_TimeOut);
				choice.click();				
				try{
					
					WebElement select=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.choice_select, Constants_TimeOuts.Element_TimeOut);
					select.click();			
					WaitUtil.pause(500L);
					try{
						WebElement ok=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.choice_ok, Constants_TimeOuts.Element_TimeOut);
						ok.click();
						Reporting.logStep(driver, step, "Able to select a choice "+input, Constants_FRMWRK.Pass);
						flag=input;
					}catch(Throwable tttt){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, step, "Unable to click on the ok button due to error"+commonMethods.getStackTrace(tttt), Constants_FRMWRK.Fail);
					}
					
				}catch(Throwable ttt){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, step, "Unable to click on the select button due to error"+commonMethods.getStackTrace(ttt), Constants_FRMWRK.Fail);
				}
			}catch (Throwable tt){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, step, "Unable to click on the choice due to error"+commonMethods.getStackTrace(tt), Constants_FRMWRK.Fail);
			}			
		}catch (Throwable t){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, step, "Unable to click the browse for valid choice due to error"+commonMethods.getStackTrace(t), Constants_FRMWRK.Fail);
		}
		finally{
			WaitUtil.pause(500L);
			ApplicationMethods.switchToLatestDLGframe(driver,testcasename);
		}
		return flag;
	}
	
	public static String isDisabledWithAttr(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_Step="Check Element is disabled ";
		
		Step=workFlow+generic_Step+Step;

		try{
			String isEnabled=element.getAttribute("class");
			if(isEnabled.contains("disabled")){
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and disabled status is "+isEnabled);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and disabled, status is "+isEnabled, Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and but not disabled & status is "+isEnabled);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and but not disabled & status is  "+String.valueOf(isEnabled), Constants_FRMWRK.Fail);
			}
			
		}catch (StaleElementReferenceException st)	{
			logsObj.logError("Stale exits for the element-"+objectLocator,st);
			String isEnabled=element.getAttribute("class");
			if(String.valueOf(isEnabled).equalsIgnoreCase("disabled")){
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and disabled status is "+isEnabled);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and disabled, status is "+isEnabled, Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and but not disabled & status is "+isEnabled);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and but not disabled & status is  "+String.valueOf(isEnabled), Constants_FRMWRK.Fail);
			}
		}
		return flag;
	}
	

}
