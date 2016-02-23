package com.proj.library;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.objectRepository.ObjRepository;
import com.proj.utilFulcrum.KeyMethodsUtil;
import com.proj.utilFulcrum.WebTableUtil;
import com.report.reporter.Reporting;

public class KeysUtil extends KeyMethods{
	/**
	 * Enters the data in the required text box object of the page
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String enter(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;
		String generic_Step="";

		if(objectType.contains("textbox")){
			generic_Step="Enter the ";
		}
		else if (objectType.equalsIgnoreCase("date")){
			generic_Step="Enter Date for ";
		}
		Step=workFlow+generic_Step+Step;

		try{
			element.clear();					    					
			element.sendKeys(input);					    					
			logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
			Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
			flag=element.getAttribute("value");

		}catch(StaleElementReferenceException st){
			logsObj.logError("Stale exits for the element-"+objectLocator,st);
			element=FetchWebElement.waitForPresenceOfElement(driver,locatorType, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
			element.clear();	
			element.sendKeys(input);
			logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value entered is "+input);
			Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists after stale and the value entered is "+input, Constants_FRMWRK.Pass);
			flag=element.getAttribute("value");

		}
		if (flag == null){					
			flag=input;
		}
		return flag;
	}

	/**
	 * Enters the data in the required text box with auto suggestion object of the page 
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 * @throws Throwable
	 */
	protected static String enter_autoSuggest(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_Step="Enter Suggestion for ";
		Step=workFlow+generic_Step+Step;

		if(!input.equalsIgnoreCase("")){
			element.click();

		}
		if(input.equalsIgnoreCase("Any")){
			element.sendKeys(Keys.SPACE);
			element.sendKeys(Keys.BACK_SPACE);
			Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
		}else{
			element.sendKeys(input);
		}									    					
		logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
		Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
		flag=element.getText();
		PageLoadWaitUtil.waitForAjax(driver);
		PageLoadWaitUtil.waitForPageToLoad(driver);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);


		return flag;
	}
	/**
	 * Enters the data in the required text box with auto suggestion object of the page and selects an item from the list
	 * @author shaikka 
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 * @throws Throwable
	 */
	protected static String enter_autoSuggestAndSelect(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;		
		String generic_autosuggest_step="Enter Suggestion for ";
		generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;
		if(!input.equalsIgnoreCase("") ){
			element.click();				
			if(input.equalsIgnoreCase("Any")){
				element.sendKeys(Keys.SPACE);
				element.sendKeys(Keys.BACK_SPACE);
				Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
			}else{
				objectLocator=objectLocator+ObjRepository.js_autosuggest_input;
				element=FetchWebElement.waitForElement(driver,locatorType, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
				element.click();
				element.sendKeys(input);
				WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);					
			}									    					
			logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
			Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);

			objectLocator=objectLocator+ObjRepository.js_autosuggest_items;
			PageLoadWaitUtil.waitForAjax(driver);
			PageLoadWaitUtil.waitForPageToLoad(driver);
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
			flag=KeyMethodsUtil.js_selectItem(driver, workFlow, Step, objectLocator, input);
		}else{
			flag=Constants_FRMWRK.True;	
		}
		return flag;
	}
	/**
	 * Retrieves the text of an object on a page
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 * @throws Throwable
	 */
	protected static String getText(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_Step="";
		if(objectType.contains("textbox")){
			generic_Step="Get the Text ";
		}
		else if (objectType.equalsIgnoreCase("title")){
			generic_Step="Get Title of ";
		}
		else if (objectType.equalsIgnoreCase("WebElement")){
			generic_Step="Fetches the element text for ";
		}

		Step=workFlow+generic_Step+Step;

		try{
			flag=element.getText();
			logsObj.log(testcaseName+"--> "+objectLocator+" exists and the text fetched is "+flag);
			Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the text fetched is "+flag, Constants_FRMWRK.Pass);
		}catch (StaleElementReferenceException st)	{
			logsObj.logError("Stale exits for the element-"+objectLocator,st);
			element=FetchWebElement.waitForPresenceOfElement(driver,locatorType, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
			flag=element.getText();
			logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the text fetched is "+flag);
			Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists after stale and the text fetched is  "+flag, Constants_FRMWRK.Pass);
		}
		return flag;
	}
	/**
	 * Retrieves the value of an object on a page
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 * @throws Throwable
	 */
	protected static String getValue(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_Step="Get the Text ";
		Step=workFlow+generic_Step+Step;

		try{
			flag=element.getAttribute("value");
			logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value fetched is "+input);
			Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value fetched is "+flag, Constants_FRMWRK.Pass);
		}catch (StaleElementReferenceException st)	{
			logsObj.logError("Stale exits for the element-"+objectLocator,st);
			element=FetchWebElement.waitForPresenceOfElement(driver,locatorType, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
			flag=element.getAttribute("value");
			logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value fetched is "+flag);
			Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists after stale and the value fetched is  "+flag, Constants_FRMWRK.Pass);

		}
		return flag;
	}

	/**
	 * Clicks the element on the page
	 * @author shaikka
	 * @date Feb 12 2016
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String click(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;
		String generic_Step="Click on ";
		Step=workFlow+generic_Step+Step;

		try{
			element.click();				    					
			logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
			Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked  ", Constants_FRMWRK.Pass);
			flag=Constants_FRMWRK.True;
		}catch(StaleElementReferenceException ex){
			logsObj.log("Button-Encountered stale exception for "+objectLocator+" So trying for recovery..");
			/*int count=StaleElementHandleAndClick(driver,identifyBy,objectLocator);
			if(count<4){
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked after recovering stale exception ", Constants_FRMWRK.Warning);
			}*/
			flag=KeyMethods.staleRecovery_Click(driver, Step, locatorType, objectLocator, Constants_TimeOuts.StaleElement_TimeOut);
		}

		try {
			PageLoadWaitUtil.waitForPageToLoad(driver);
		} catch (Throwable e) {				
			//e.printStackTrace();
		}
		return flag;
	}
	/**
	 * Browse the required file on the page
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String browse(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;
		String generic_Step="Upload/Browse a ";
		Step=workFlow+generic_Step+Step;

		element.sendKeys(input);					    					
		logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
		Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is  "+input, Constants_FRMWRK.Pass);
		flag=input;

		try {
			PageLoadWaitUtil.waitForPageToLoad(driver);
		} catch (Throwable e) {			
			e.printStackTrace();
		}

		return flag;

	}

	protected static String dropDown(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;		
		String generic_Step="Select an Item from dropdown ";
		Step=workFlow+generic_Step+Step;

		Select dropdown=FetchWebElement.waitForDropdownItems(driver, element, Constants_TimeOuts.Element_TimeOut);
		List<WebElement>dropdownList=dropdown.getOptions();

		if (dropdownList.size()==0) {			       									
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but no items are listed to select ", Constants_FRMWRK.Fail);
			return flag;
		}	

		if (input.equalsIgnoreCase("ANY")){
			try{
				dropdown.selectByIndex(1);
				flag=dropdown.getFirstSelectedOption().getText();
				Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and an item "+flag+" is selected", Constants_FRMWRK.Pass);
				return flag;
			}catch (Exception e){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but unable to select first item  due to -->"+e.getCause(), Constants_FRMWRK.Fail);
				return flag;
			}

		}else if(input.equalsIgnoreCase("")){
			Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and there is no item to select as per input", Constants_FRMWRK.Info);
			return flag;
		}

		else
		{		
			try{
				dropdown.selectByVisibleText(input);						
				Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and an item "+input+" is selected", Constants_FRMWRK.Pass);
				flag=input;											       							       					
				return flag;
			}catch (Exception e){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but unable to select item "+input+" due to error --> "+e.getMessage(), Constants_FRMWRK.Fail);
				return flag;
			}
		}


	}
	/**
	 * Selects a item from the list (drop down type JS)
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String dropDown_js(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;		
		String generic_Step="Select an Item from dropdown ";
		Step=workFlow+generic_Step+Step;
		String staleflag=Constants_FRMWRK.False;
		try{
			element.click();
			logsObj.log(testcaseName+"--> "+objectLocator+" exists and the dropdown_jqx is clicked");
			Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the dropdown_jqx is clicked ", Constants_FRMWRK.Pass);
			staleflag=Constants_FRMWRK.True;
		}catch (StaleElementReferenceException st){
			logsObj.log("dropdown-jqx-Encountered stale exception for "+objectLocator+" So trying for recovery..");
			staleflag=KeyMethods.staleRecovery_Click(driver, Step,locatorType , objectLocator, Constants_TimeOuts.StaleElement_TimeOut);
		}

		if(staleflag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" could not recover from the stale ", Constants_FRMWRK.Fail);
		}
		else if(!input.equalsIgnoreCase("") ){
			PageLoadWaitUtil.waitForAjax(driver);
			try {
				PageLoadWaitUtil.waitForPageToLoad(driver);
			} catch (Throwable e) {				
				e.printStackTrace();
			}
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
			flag=KeyMethodsUtil.js_selectItem(driver, workFlow, generic_Step, ObjRepository.js_dropdown_items, input);
		}
		return flag;
	}
	/**
	 * Performs required action on an element (Element Type: Check box or Radio button)
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String checkBoxORradiobutton(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;			
		String generic_Step="";
		if(objectType.equalsIgnoreCase("Checkbox")){
			generic_Step="Tick an Item(s) from Checkbox ";
		}
		else if (objectType.equalsIgnoreCase("Radiobutton")){
			generic_Step="Select a Radiobutton for ";
		}
		Step=workFlow+generic_Step+Step;

		if (input.equalsIgnoreCase(Constants_FRMWRK.Tick)){
			boolean tick=Constants_FRMWRK.TrueB;
			tick=element.isSelected();
			if(!tick){
				element.click();
				tick=element.isSelected();
				flag=String.valueOf(tick);	
				if(!tick){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and "+objectType+" is not selected/ticked after click action", Constants_FRMWRK.Fail);
				}else{
					Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and "+objectType+" is selected/ticked", Constants_FRMWRK.Pass);
				}

			}else{
				Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and "+objectType+" is already selected/ticked", Constants_FRMWRK.Pass);
			}


		}else{

			boolean tick=Constants_FRMWRK.TrueB;
			tick=element.isSelected();
			if(!tick){
				Reporting.logStep(driver, refID, Step,objectType+": "+objectLocator+" exists and "+objectType+" is already unselected/unticked", Constants_FRMWRK.Pass);
				flag=input;
			}else{
				element.click();
				tick=element.isSelected();
				if(!tick){			       										
					Reporting.logStep(driver, refID, Step,objectType+": "+objectLocator+" exists and "+objectType+" is un-selected after performing action, which is as per input->"+input, Constants_FRMWRK.Pass);
					flag=input;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step,objectType+": "+objectLocator+" exists and "+objectType+" is not  un-selected after performing action, which is not as per input->"+input, Constants_FRMWRK.Fail);
				}			       							

			}

		}
		return flag;

	}
	/**
	 * Selects a required radio button from the group 
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String radioGroup(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;				
		String generic_Step="Select a Radiobutton from group for ";
		Step=workFlow+generic_Step+Step;

		if(element ==null){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" does not exists hence cannot select a radiobutton ", Constants_FRMWRK.Fail);
		}
		else{
			element=ElementMethods.fetchRadiobuttonFromGroup(driver,locatorType, objectLocator, input);
			boolean Rtick=Constants_FRMWRK.TrueB;
			Rtick=element.isSelected();
			if(!Rtick){
				element.click();
				Rtick=element.isSelected();
				flag=String.valueOf(Rtick);	
				if(!Rtick){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and the required radiobutton "+input+" is not selected after click action", Constants_FRMWRK.Fail);
				}else{
					flag=element.getAttribute("value");
					Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and the required radiobutton "+input+" is selected", Constants_FRMWRK.Pass);
					return flag;
				}

			}else{
				flag=element.getAttribute("value");
				Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and the required radiobutton "+input+" is already selected", Constants_FRMWRK.Pass);
			}
		}
		return flag;

	}

	/**
	 * Select an item displayed from the list
	 * @author shaikka
	 * @param driver
	 * @param Step
	 * @param listLocatorType
	 * @param listLocator
	 * @param data
	 * @return
	 */
	protected static String selectItemByTextFromList(WebDriver driver,String Step,String listLocatorType,String listLocator,String data){
		String flag=Constants_FRMWRK.False;
		String actual_actionData = "";
		String rowNumber="row";
		int rowCount=0;

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();		

		try{
			logsObj.log("Get Grid row count for column under search");
			List<WebElement> listOfItems=WebTableUtil.waitUntilAllVisible(driver,listLocator);

			WaitUtil.pause(100L);

			for (WebElement rowSearch : listOfItems) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("List- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+data.trim());
				actual_actionData=rowSearch.getText().trim();
				if(actual_actionData.equals(data.trim())){
					WaitUtil.pause(100L);				
					int counter=0;

					@SuppressWarnings("unused")
					boolean clicked;
					try{							
						while (counter< 5){
							try{
								rowSearch.click();
								WaitUtil.pause(1);
								clicked=Constants_FRMWRK.TrueB;

								logsObj.log("selectItemByTextFromList:Successfully clicked the record from the List") ;

								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(100L);

						}


					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to click the record from the List after successfully matching the expected data "+listLocator+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

					} 	
					if(clicked=Constants_FRMWRK.TrueB){
						flag=Constants_FRMWRK.True;
						Reporting.logStep(driver, Step, "Successfully clicked the record  from the List after matching the expected data:-"+data+" with actual data:-"+actual_actionData+" in the List "+listLocator, Constants_FRMWRK.Pass);
					}else{
						Reporting.logStep(driver, Step, "Unable to click the record from the List after matching the expected data:-"+data+" with actual data:-"+actual_actionData+" in the List "+listLocator, Constants_FRMWRK.Fail);
						flag=Constants_FRMWRK.False;
					}

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required search data "+data+" in the table "+listLocator+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			Reporting.logStep(driver, refID, "Menu Verification", "Not able to Locate the menu "+data, Constants_FRMWRK.Fail);
		}

		return flag;
	}

	/**
	 * Validates the value displayed for the element with input
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @param validationType
	 * @return
	 */
	protected static String validate_ValueORText(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element,String validationType){
		String flag=Constants_FRMWRK.False;
		String generic_Step="Validation of ";
		Step=workFlow+generic_Step+Step;
		if(validationType.equalsIgnoreCase("VALUE")){
			flag=element.getAttribute("value");
		}
		else{
			flag=element.getText();
		}


		if (flag.equals(input)){
			Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" equals with the Expected Value:- "+input, Constants_FRMWRK.Pass);			
		}
		else if (flag.contains(input)){
			Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);			
		}
		else{
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
		} 

		return flag;
	}
	/**
	 * Validates the item selected for dropdown with the input
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String validate_dropdownOptionSelected(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;
		String generic_Step="Validation of ";
		Step=workFlow+generic_Step+Step;

		flag=commonMethods.getSelectedOptionFromDropdown(element);  

		if (flag.equals(input)){
			Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" equals with the Expected Value:- "+input, Constants_FRMWRK.Pass);			
		}
		else if (flag.contains(input)){
			Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);			
		}
		else{
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
		} 

		return flag;
	}
	/**
	 * Validates the Checkbox option Ticked with input
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String validate_checkBox(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;
		String generic_Step="Validate the item value is ticked as per selected item for ";
		Step=workFlow+generic_Step+Step;

		if (input.equalsIgnoreCase(Constants_FRMWRK.Tick)){
			boolean tick=Constants_FRMWRK.TrueB;
			tick=element.isSelected();       						

			if(tick==Constants_FRMWRK.FalseB){      							
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, Step, objectType+": "+objectLocator+" exists and checkbox is not selected/ticked as Per Input:-"+input, Constants_FRMWRK.Fail);
			}else{      							
				Reporting.logStep(driver, Step,objectType+": "+objectLocator+" exists and checkbox is selected/ticked as Per Input:-"+input, Constants_FRMWRK.Pass);
			}

		}else{
			boolean tick=Constants_FRMWRK.FalseB;
			tick=element.isSelected();     						

			if(tick==Constants_FRMWRK.TrueB){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, Step, objectType+": "+objectLocator+" exists and checkbox is not un-selected/un-ticked as Per Input:-"+input, Constants_FRMWRK.Fail);
			}else{
				Reporting.logStep(driver, Step,   objectType+": "+objectLocator+" exists and checkbox is un-selected/un-ticked as Per Input:-"+input, Constants_FRMWRK.Pass);
			}

		}
		return flag;
	}

	/**
	 * Validates the Check box option (read only) is Ticked with input
	 * @author shaikka
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 */
	protected static String validate_checkBoxReadOnly(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element){
		String flag=Constants_FRMWRK.False;
		String generic_Step="Validate the item value is ticked for ";
		Step=workFlow+generic_Step+Step;

		String cinput=null;

		if(input.equalsIgnoreCase(Constants_FRMWRK.Tick)){
			cinput=Constants_FRMWRK.Yes;
		}else{
			cinput=Constants_FRMWRK.No;
		}

		flag=element.getAttribute("value");

		if (flag.equals(cinput)){
			Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Displayed Value:- "+cinput+" /Expected Input Value given:-"+input, Constants_FRMWRK.Pass);
		}else{
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Displayed Value:- "+cinput+" /Expected Input Value given"+input, Constants_FRMWRK.Fail);

		} 
		return flag;
	}
	
	/**
	 * Verifies the given element is enabled
	 * @author Khaleel
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param locatorType
	 * @param objectType
	 * @param objectLocator
	 * @param input
	 * @param element
	 * @return
	 * @throws Throwable
	 */
	protected static String isEnabled(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_Step="Check Element is enabled ";
		
		Step=workFlow+generic_Step+Step;

		try{
			boolean isEnabled=element.isEnabled();
			if(String.valueOf(isEnabled).equalsIgnoreCase(Constants_FRMWRK.True)){
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and enabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and enable status is "+String.valueOf(isEnabled), Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and enabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and enable status is "+String.valueOf(isEnabled), Constants_FRMWRK.Fail);
			}
			
		}catch (StaleElementReferenceException st)	{
			logsObj.logError("Stale exits for the element-"+objectLocator,st);
			boolean isEnabled=element.isEnabled();
			if(String.valueOf(isEnabled).equalsIgnoreCase(Constants_FRMWRK.True)){
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and enabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and enable status is "+String.valueOf(isEnabled), Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and enabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and enable status is "+String.valueOf(isEnabled), Constants_FRMWRK.Fail);
			}
		}
		return flag;
	}
	
	
	protected static String isDisabled(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String locatorType, String objectType, String objectLocator,String input,WebElement element) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String generic_Step="Check Element is disabled ";
		
		Step=workFlow+generic_Step+Step;

		try{
			boolean isEnabled=element.isDisplayed();
			if(String.valueOf(isEnabled).equalsIgnoreCase(Constants_FRMWRK.False)){
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and disabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists & disabled , status is "+String.valueOf(isEnabled), Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and disabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists & is not disable , status is "+String.valueOf(isEnabled), Constants_FRMWRK.Fail);
			}
			
		}catch (StaleElementReferenceException st)	{
			logsObj.logError("Stale exits for the element-"+objectLocator,st);
			boolean isEnabled=element.isEnabled();
			if(String.valueOf(isEnabled).equalsIgnoreCase(Constants_FRMWRK.False)){
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and disabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and disabled status is "+String.valueOf(isEnabled), Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.True;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and disabled status is "+String.valueOf(isEnabled));
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and disabled status is "+String.valueOf(isEnabled), Constants_FRMWRK.Fail);
			}
		}
		return flag;
	}
}
