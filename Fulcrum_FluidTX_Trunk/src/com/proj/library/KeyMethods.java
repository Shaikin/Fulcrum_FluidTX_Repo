package com.proj.library;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.WaitUtil;
import com.frw.wait.ImplicitWaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.util.ObjectType;
import com.proj.utilFulcrum.KeyMethodsUtil;
import com.report.reporter.Reporting;

public class KeyMethods extends TestBase{

	/**
	 * Performs actions on any element present on the application with any objectType locator(Strings parameters)	 
	 * @author sahamed
	 * @param testcaseName
	 * @param Step
	 * @param identifyBy
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @Date 8 Jan 2016
	 * @return returns entered value for successful entry otherwise Fail for failure
	 */

	@SuppressWarnings({"incomplete-switch" })
	public static String f_performAction(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String identifyBy, String objectType, String objectLocator,String input) 	
	{
		String generic_Step;
		WebElement element = null;
		String flag=Constants_FRMWRK.False;

		try 
		{
			WaitUtil.pause(100L);
			if(!objectType.equalsIgnoreCase("SWITCHTOFRAMEFROMDEFAULT")){
				element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;

				if(element==null){
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, 2);
				}

			}

			WaitUtil.pause(300L);
			ObjectType str=ObjectType.valueOf(objectType.toUpperCase());


			switch (str){

			case TEXTBOX:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				return flag; 

			case TEXTBOX_AUTOSUGGEST:	
				flag=KeysUtil.enter_autoSuggest(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				return flag; 

			case TEXTBOX_AUTOSUGGEST_SELECT:
				flag=KeysUtil.enter_autoSuggestAndSelect(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_AUTOSUGGEST_CHOICE:
				flag=KeyMethodsUtil.enter_autoSuggestAndSelectChoice(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_AUTOSUGGEST_BROWSE:			
				flag=KeyMethodsUtil.enter_autosuggest_browse(driver,testcaseName, workFlow, Step, input, element);
				break;
			case TEXTBOX_DECIMALS:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_MONEY:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_GETTEXT:
				flag=KeysUtil.getText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_GETVALUE:
				flag=KeysUtil.getValue(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case BUTTON:	
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case BROWSE:
				flag=KeysUtil.browse(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case IMAGE:					
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 
			case LINK:					
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TAB:					
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TITLE:	
				flag=KeysUtil.getText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);				
				break;
			case DATE:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case WEBELEMENT:
				flag=KeysUtil.getText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case DROPDOWN:
				flag=KeysUtil.dropDown(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case DROPDOWN_JQX:
				flag=KeysUtil.dropDown_js(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 
			case CHECKBOX:
				flag=KeysUtil.checkBoxORradiobutton(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case RADIOBUTTON:				
				flag=KeysUtil.checkBoxORradiobutton(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 
			case RADIOGROUP:
				flag=KeysUtil.radioGroup(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case SWITCHTOFRAMEFROMDEFAULT :							
				generic_Step="Switch to Frame";
				Step=workFlow+generic_Step+Step;

				commonMethods.switchToFrameFromDefault(driver, testcaseName, identifyBy, objectLocator);				
				flag=Constants_FRMWRK.Pass;				
				break;

			case SELECTITEMBYTEXTFROMLIST:
				flag=KeysUtil.selectItemByTextFromList(driver,refID,testcaseName,workFlow, Step, identifyBy, objectLocator, input);				
				break;			
			case ELEMENT_ENABLE:
				flag=KeysUtil.isEnabled(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case ELEMENT_DISABLE:
				flag=KeysUtil.isDisabled(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case ELEMENT_DISABLE_ATTRIBUTE:
				flag=KeyMethodsUtil.isDisabledWithAttr(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
			}



		}

		catch (Throwable e)//General exception

		{

			isTestPass=Constants_FRMWRK.FalseB;
			System.out.println("Unable to locate the element "+objectLocator+" due to -->"+commonMethods.getStackTrace(e));
			logsObj.log(testcaseName+":-Unable to locate the element "+objectLocator+" due to -->"+commonMethods.getStackTrace(e));

			Reporting.logStep(driver, refID, workFlow+Step,   objectType+": "+objectLocator+" ,Unable to locate the element due to -->"+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;


		}
		return flag;


	}

	/**
	 * Performs actions on any element present on the application with any objectType locator(Hashtable parameters)
	 * 
	 * @author sahamed
	 * @param testcaseName
	 * @param Step
	 * @param identifyBy
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @Date 10 Jun 2013
	 * @return returns entered value for successful entry otherwise Fail for failure
	 */

	@SuppressWarnings({"incomplete-switch" })
	public static String f_performAction(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,Hashtable<String,String>f_performAction_objects_locatorType, Hashtable<String,String>f_performAction_objects_objectType, Hashtable<String,String>f_performAction_objects_objectLocator,String input) 	
	{
		String generic_Step;
		String identifyBy=f_performAction_objects_locatorType.get(Step);
		String objectType=f_performAction_objects_objectType.get(Step);
		String objectLocator=f_performAction_objects_objectLocator.get(Step);

		WebElement element = null;
		String flag=Constants_FRMWRK.False;

		try 
		{
			WaitUtil.pause(100L);
			if(!objectType.equalsIgnoreCase("SWITCHTOFRAMEFROMDEFAULT")){
				element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;

				if(element==null){
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, 2);
				}

			}

			WaitUtil.pause(300L);
			ObjectType str=ObjectType.valueOf(objectType.toUpperCase());


			switch (str){

			case TEXTBOX:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				return flag; 

			case TEXTBOX_AUTOSUGGEST:	
				flag=KeysUtil.enter_autoSuggest(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				return flag; 

			case TEXTBOX_AUTOSUGGEST_SELECT:
				flag=KeysUtil.enter_autoSuggestAndSelect(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_AUTOSUGGEST_CHOICE:
				flag=KeyMethodsUtil.enter_autoSuggestAndSelectChoice(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_AUTOSUGGEST_BROWSE:			
				flag=KeyMethodsUtil.enter_autosuggest_browse(driver,testcaseName, workFlow, Step, input, element);
				break;
			case TEXTBOX_DECIMALS:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_MONEY:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_GETTEXT:
				flag=KeysUtil.getText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TEXTBOX_GETVALUE:
				flag=KeysUtil.getValue(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case BUTTON:	
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case BROWSE:
				flag=KeysUtil.browse(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case IMAGE:					
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 
			case LINK:					
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TAB:					
				flag=KeysUtil.click(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case TITLE:	
				flag=KeysUtil.getText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);				
				break;
			case DATE:
				flag=KeysUtil.enter(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case WEBELEMENT:
				flag=KeysUtil.getText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case DROPDOWN:
				flag=KeysUtil.dropDown(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case DROPDOWN_JQX:
				flag=KeysUtil.dropDown_js(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 
			case CHECKBOX:
				flag=KeysUtil.checkBoxORradiobutton(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case RADIOBUTTON:				
				flag=KeysUtil.checkBoxORradiobutton(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 
			case RADIOGROUP:
				flag=KeysUtil.radioGroup(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case SWITCHTOFRAMEFROMDEFAULT :							
				generic_Step="Switch to Frame";
				Step=workFlow+generic_Step+Step;

				commonMethods.switchToFrameFromDefault(driver, testcaseName, identifyBy, objectLocator);				
				flag=Constants_FRMWRK.Pass;				
				break;

			case SELECTITEMBYTEXTFROMLIST:
				flag=KeysUtil.selectItemByTextFromList(driver,refID,testcaseName,workFlow, Step, identifyBy, objectLocator, input);				
				break;
			case ELEMENT_ENABLE:
				flag=KeysUtil.isEnabled(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case ELEMENT_DISABLE:
				flag=KeysUtil.isDisabled(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case ELEMENT_DISABLE_ATTRIBUTE:
				flag=KeyMethodsUtil.isDisabledWithAttr(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
			
			}


		}

		catch (Throwable e)//General exception

		{

			isTestPass=Constants_FRMWRK.FalseB;
			System.out.println("Unable to locate the element "+objectLocator+" due to -->"+commonMethods.getStackTrace(e));
			logsObj.log(testcaseName+":-Unable to locate the element "+objectLocator+" due to -->"+commonMethods.getStackTrace(e));

			Reporting.logStep(driver, refID, workFlow+Step,   objectType+": "+objectLocator+" ,Unable to locate the element due to -->"+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;


		}

		return flag;

	}



	/**
	 * fetches the value/Select value of an element under test and validates it with input (Strings parameters)
	 * @author sahamed
	 * @Date 10 Jun 2013
	 * @SuppressWarnings({ "unchecked", "rawtypes", "incomplete-switch" })
	 * @param testcaseName
	 * @param Step
	 * @param identifyBy
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @return return a default value/Selected value for success ,Fail for failure
	 */
	@SuppressWarnings({ "incomplete-switch" })
	public static String f_fetchElementDetails(WebDriver driver,String testcaseName,String workFlow,String Step,String identifyBy, String objectType, String objectLocator,String input) 	 
	{
		WebElement element = null;
		String flag=Constants_FRMWRK.False;
		try 
		{
			WaitUtil.pause(100L);
			element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;

			if(element==null){
				element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, 2);
			}

			WaitUtil.pause(300L);
			ObjectType str= ObjectType.valueOf(objectType.toUpperCase());

			switch (str){

			case TEXTBOX:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break; 

			case TEXTBOX_AUTOSUGGEST:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break; 

			case TEXTBOX_AUTOSUGGEST_SELECT:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_DECIMALS:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_MONEY:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_GETTEXT:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_GETVALUE:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;
			case DATE:		
				element.click();
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");				
				break;
			case DROPDOWN :
				flag=KeysUtil.validate_dropdownOptionSelected(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case WEBELEMENT:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Text");				
				break;
			case CHECKBOX:
				flag=KeysUtil.validate_checkBox(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;  
			case CHECKBOX_READONLY:
				flag=KeysUtil.validate_checkBoxReadOnly(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 

			case LINK:
				KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Text");

			}	
			return flag;

		}
		catch (Exception genException)//General exception

		{
			isTestPass=Constants_FRMWRK.FalseB;
			System.out.println("Unable to locate the element "+objectLocator+" due to -->"+genException);
			logsObj.logError(testcaseName+"Unable to locate the element "+objectLocator+" due to -->",genException);

			Reporting.logStep(driver, Step,  objectType+": "+objectLocator+" ,Unable to locate the element due to -->"+genException, Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
			return flag;

		}


	}


	/**
	 * fetches the value/Select value of an element under test and validates it with input (Hashtable parameters)
	 * @author sahamed
	 * @Date 10 Jun 2013
	 * @SuppressWarnings({ "unchecked", "rawtypes", "incomplete-switch" })
	 * @param testcaseName
	 * @param Step
	 * @param identifyBy
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @return return a default value/Selected value for success ,Fail for failure
	 */
	@SuppressWarnings({ "incomplete-switch" })
	public static String f_fetchElementDetails(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,Hashtable<String,String>f_performAction_objects_locatorType, Hashtable<String,String>f_performAction_objects_objectType, Hashtable<String,String>f_performAction_objects_objectLocator,String input) 	 
	{

		String identifyBy=f_performAction_objects_locatorType.get(Step);
		String objectType=f_performAction_objects_objectType.get(Step);
		String objectLocator=f_performAction_objects_objectLocator.get(Step);

		WebElement element = null;
		String flag=Constants_FRMWRK.False;
		try 
		{
			WaitUtil.pause(100L);
			element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;

			if(element==null){
				element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, 2);
			}

			WaitUtil.pause(300L);
			ObjectType str= ObjectType.valueOf(objectType.toUpperCase());

			switch (str){

			case TEXTBOX:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break; 

			case TEXTBOX_AUTOSUGGEST:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break; 

			case TEXTBOX_AUTOSUGGEST_SELECT:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_DECIMALS:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_MONEY:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_GETTEXT:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;

			case TEXTBOX_GETVALUE:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");
				break;
			case DATE:		
				element.click();
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Value");				
				break;
			case DROPDOWN :
				flag=KeysUtil.validate_dropdownOptionSelected(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;
			case WEBELEMENT:
				flag=KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Text");				
				break;
			case CHECKBOX:
				flag=KeysUtil.validate_checkBox(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break;  
			case CHECKBOX_READONLY:
				flag=KeysUtil.validate_checkBoxReadOnly(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element);
				break; 

			case LINK:
				KeysUtil.validate_ValueORText(driver, refID, testcaseName, workFlow, Step, identifyBy, objectType, objectLocator, input, element,"Text");

			}	
			return flag;

		}

		catch (Exception genException)//General exception

		{
			isTestPass=Constants_FRMWRK.FalseB;
			System.out.println("Unable to locate the element "+objectLocator+" due to -->"+genException);
			logsObj.logError(testcaseName+"Unable to locate the element "+objectLocator+" due to -->",genException);

			Reporting.logStep(driver, Step,  objectType+": "+objectLocator+" ,Unable to locate the element due to -->"+genException, Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
			return flag;

		}


	}


	/**
	 * Click on stale element
	 * @author khshaik
	 * @date Feb 12 2015
	 * @param driver
	 * @param Step
	 * @param identifyBy
	 * @param objectLocator
	 * @param timeOut
	 * @return
	 */
	static String staleRecovery_Click(WebDriver driver,String Step,String identifyBy,String objectLocator,int timeOut){
		String staleFlag=Constants_FRMWRK.False;
		WebElement staleElement=null;
		try{
			ImplicitWaitUtil.turnOffImplicitWait(driver);
			staleElement=FetchWebElement.waitForElementTobeActionable(driver, identifyBy, objectLocator, Constants_TimeOuts.StaleElement_TimeOut);
			if(staleElement!=null){
				staleElement.click();
				Reporting.logStep(driver, refID, Step,  identifyBy+": "+objectLocator+" exists and clicked after recovering stale exception ", Constants_FRMWRK.Warning);
				staleFlag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, refID, Step,  identifyBy+": "+objectLocator+" couldnt recovered from stale exception ", Constants_FRMWRK.Fail);
			}
		}catch (Throwable t){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID, Step,  identifyBy+": "+objectLocator+" exception  while recovering stale and exception is "+t, Constants_FRMWRK.Fail);
		}finally{
			ImplicitWaitUtil.turnOnImplicitWait(driver);
		}
		return staleFlag;
	}



}
