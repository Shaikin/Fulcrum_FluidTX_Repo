	package com.proj.library;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.frw.util.WorkaroundsSelenium;
import com.frw.wait.ImplicitWaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.objectRepository.ObjRepository;
import com.proj.util.ObjectType;
import com.proj.utilFulcrum.KeyMethodsUtil;
import com.report.reporter.Reporting;

public class KeyMethods extends TestBase{

	/**
	 * Performs actions on any element present on the application with any objectType locator(Strings parameters)
	 * 
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
				try{
					generic_Step="Enter the ";
					Step=workFlow+generic_Step+Step;

					element.clear();					    					
					element.sendKeys(input);					    					
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
					flag=element.getAttribute("value");
					
					if (flag == null){					
						flag=input;
					}
				}catch(StaleElementReferenceException st){
					logsObj.logError("Stale exits for the element-"+objectLocator,st);
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
					element.clear();	
					element.sendKeys(input);
					logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value entered is "+input);
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists after stale and the value entered is "+input, Constants_FRMWRK.Pass);
					flag=element.getAttribute("value");

				}


				return flag; 

			case TEXTBOX_AUTOSUGGEST:	
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.click();
				element.clear();
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

				return flag; 

			case TEXTBOX_AUTOSUGGEST_SELECT:

				String generic_autosuggest_step="Enter Suggestion for ";
				generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;
				
				element.click();
				
				if(input.equalsIgnoreCase("Any")){
					element.sendKeys(Keys.SPACE);
					//element.sendKeys(Keys.BACK_SPACE);
					Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
				}else{
					objectLocator=objectLocator+"/../input[2]";
					element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
					element.sendKeys(input);
				}									    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				
				objectLocator=objectLocator+"/../div/ul/li/a/div";				
				flag=KeyMethodsUtil.js_selectItem(driver, workFlow, generic_autosuggest_step, objectLocator, input);
				return flag; 
			case TEXTBOX_AUTOSUGGEST_CHOICE:

				generic_autosuggest_step="Enter Suggestion for ";
				generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;

					element.click();				
					if(input.equalsIgnoreCase("Any")){
						element.sendKeys(Keys.SPACE);
						element.sendKeys(Keys.BACK_SPACE);
						Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
					}else{
						element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
						element.click();
						element.clear();
						element.sendKeys(input);
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
			case TEXTBOX_DECIMALS:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag; 

			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag; 

			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();	
				element.sendKeys(input);	
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag;

			case TEXTBOX_MONEY:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag; 

			case TEXTBOX_GETTEXT:
				try{
					generic_Step="Enter the ";
					Step=workFlow+generic_Step+Step;

					element.sendKeys(input);
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
					flag=element.getAttribute("value");
				}catch (StaleElementReferenceException st)	{
					logsObj.logError("Stale exits for the element-"+objectLocator,st);
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, 20) ;
					element.sendKeys(input);
					logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value entered is "+input);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists after stale and the value entered is  "+input, Constants_FRMWRK.Pass);
					flag=element.getAttribute("value");
				}


				return flag; 
				
			case TEXTBOX_GETVALUE:
				try{
					generic_Step="Get the Value";
					Step=workFlow+generic_Step+Step;
					flag=element.getAttribute("value");
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value fetched is "+flag, Constants_FRMWRK.Pass);
					
				}catch (StaleElementReferenceException st)	{
					logsObj.logError("Stale exits for the element-"+objectLocator,st);
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, 20) ;
					flag=element.getAttribute("value");
					logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value fetched is "+input);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists after stale and the value fetched is  "+flag, Constants_FRMWRK.Pass);
					
				}


				return flag; 

			case BUTTON:	
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				try{
					WaitUtil.pause(2);
					element.click();				    					
					logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked  ", Constants_FRMWRK.Pass);
					flag=Constants_FRMWRK.Pass;
				}catch(StaleElementReferenceException ex){
					logsObj.log("Encountered stale exception for "+objectLocator+" So trying for recovery..");
					/*int count=StaleElementHandleAndClick(driver,identifyBy,objectLocator);
					logsObj.log(testcaseName+"--> Stale recovered count"+count);
					if(count<4){
						Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked after recovering stale exception ", Constants_FRMWRK.Warning);
					}else{
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and not recovering stale exception ", Constants_FRMWRK.Fail);
					}*/
					
					flag=staleRecovery_Click(driver, Step, identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut);
				}

				PageLoadWaitUtil.waitForPageToLoad(driver);
				return flag;   

			case BROWSE:
				generic_Step="Upload/Browse a ";
				Step=workFlow+generic_Step+Step;

				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is  "+input, Constants_FRMWRK.Pass);
				flag=input;

				PageLoadWaitUtil.waitForPageToLoad(driver);
				return flag; 

			case IMAGE:					
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				element.click();			       							
				logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked ", Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.Pass;

				return flag; 

			case LINK:					
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				element.click();			       							
				logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked ", Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.Pass;

				return flag;

			case TAB:
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				element.click();
				logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked ", Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.Pass;

				return flag; 

			case TITLE:	
				generic_Step="Get Title of ";
				Step=workFlow+generic_Step+Step;

				flag=element.getText();
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and text displayed is "+flag, Constants_FRMWRK.Pass);
				return flag;

			case DATE:
				generic_Step="Enter Date for ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=input;

				return flag;
			
			case WEBELEMENT:
				generic_Step="Fetches the element text for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getText();
				
			return flag;

			case DROPDOWN:

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
						break;
					}catch (Exception e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but unable to select first item  due to -->"+e.getCause(), Constants_FRMWRK.Fail);
						break;
					}

				}else{


					try{
						dropdown.selectByVisibleText(input);						
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and an item "+input+" is selected", Constants_FRMWRK.Pass);
						flag=input;											       							       					
						break;
					}catch (Exception e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but unable to select item "+input+" due to error --> "+e.getMessage(), Constants_FRMWRK.Fail);
						break;
					}
				}

			case DROPDOWN_JQX:
				generic_Step="Select an Item from dropdown ";
				Step=workFlow+generic_Step+Step;

				String staleflag=Constants_FRMWRK.False;
				try{
					element.click();
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the dropdown_jqx is clicked");
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the dropdown_jqx is clicked ", Constants_FRMWRK.Pass);
					staleflag=Constants_FRMWRK.True;
				}catch (StaleElementReferenceException st){
					logsObj.log("dropdown-jqx-Encountered stale exception for "+objectLocator+" So trying for recovery..");
					staleflag=staleRecovery_Click(driver, Step, identifyBy, objectLocator, Constants_TimeOuts.StaleElement_TimeOut);
				}
				
				if(staleflag.equalsIgnoreCase(Constants_FRMWRK.False)){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" could not recover from the stale ", Constants_FRMWRK.Fail);
				}
				else if(!input.equalsIgnoreCase("") ){
					PageLoadWaitUtil.waitForAjax(driver);
					PageLoadWaitUtil.waitForPageToLoad(driver);
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
					flag=KeyMethodsUtil.js_selectItem(driver, workFlow, Step, ObjRepository.js_dropdown_items, input);
				}
				return flag; 

			case CHECKBOX:

				if (input.equalsIgnoreCase(Constants_FRMWRK.Tick)){
					boolean tick=Constants_FRMWRK.TrueB;
					tick=element.isSelected();
					if(!tick){
						element.click();
						tick=element.isSelected();
						flag=String.valueOf(tick);	
						if(!tick){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is not selected/ticked after click action", Constants_FRMWRK.Fail);
						}else{
							Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is selected/ticked", Constants_FRMWRK.Pass);
						}

					}else{
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is already selected/ticked", Constants_FRMWRK.Pass);
					}


				}else{

					boolean tick=Constants_FRMWRK.TrueB;
					tick=element.isSelected();
					if(!tick){
						Reporting.logStep(driver, refID, Step,     objectType+": "+objectLocator+" exists and checkbox is already unselected/unticked", Constants_FRMWRK.Pass);
						flag=input;
					}else{
						element.click();
						tick=element.isSelected();
						if(!tick){			       										
							Reporting.logStep(driver, refID, Step,     objectType+": "+objectLocator+" exists and checkbox is un-selected after performing action, which is as per input->"+input, Constants_FRMWRK.Pass);
							flag=input;
						}else{
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is not  un-selected after performing action, which is not as per input->"+input, Constants_FRMWRK.Fail);
						}			       							

					}

				}

				break;	


			case RADIOBUTTON:
				generic_Step="Click a Radiobutton for ";
				Step=workFlow+generic_Step+Step;

				if (input.equalsIgnoreCase("Select")){
					boolean Rtick=Constants_FRMWRK.TrueB;
					Rtick=element.isSelected();
					if(!Rtick){
						element.click();
						Rtick=element.isSelected();
						flag=String.valueOf(Rtick);	
						if(!Rtick){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,     objectType+": "+objectLocator+" exists and radiobutton is not selected after click action", Constants_FRMWRK.Fail);
						}else{
							flag=element.getAttribute("value");
							Reporting.logStep(driver, refID, Step,      objectType+": "+objectLocator+" exists and radiobutton is selected", Constants_FRMWRK.Pass);
							break;
						}

					}else{
						flag=element.getAttribute("value");
						Reporting.logStep(driver, refID, Step,      objectType+": "+objectLocator+" exists and radiobutton is already selected", Constants_FRMWRK.Pass);
						break;
					}

				}else{

					boolean Rtick=Constants_FRMWRK.TrueB;
					Rtick=element.isSelected();
					if(!Rtick){
						flag=element.getAttribute("value");
						Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and radiobutton is already unselected", Constants_FRMWRK.Pass);

					}else{
						element.click();			       								
						Rtick=element.isSelected();
						flag=String.valueOf(Rtick);
						if(!Rtick){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and radiobutton is not unselected after click action", Constants_FRMWRK.Fail);
						}else{
							flag=element.getAttribute("value");
							Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and radiobutton is unselected", Constants_FRMWRK.Pass);
							break;
						}

					}

				}
				break;				
			case RADIOGROUP:
				generic_Step="Click a Radiobutton from group for ";
				Step=workFlow+generic_Step+Step;

				if(element ==null){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" does not exists hence cannot select a radiobutton ", Constants_FRMWRK.Fail);
				}
				else{
					element=ElementMethods.fetchRadiobuttonFromGroup(driver,identifyBy, objectLocator, input);
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
							break;
						}

					}else{
						flag=element.getAttribute("value");
						Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and the required radiobutton "+input+" is already selected", Constants_FRMWRK.Pass);
					}
				}

				break;	
			case SWITCHTOFRAMEFROMDEFAULT:					
				generic_Step="Switch to Frame";
				Step=workFlow+generic_Step+Step;
				
				commonMethods.switchToFrameFromDefault(driver, testcaseName, identifyBy, objectLocator);				
				flag=Constants_FRMWRK.Pass;

				return flag; 

			}



			return flag;

		}

		catch (Throwable e)//General exception

		{


			isTestPass=Constants_FRMWRK.FalseB;
			System.out.println("Unable to locate the element "+objectLocator+" due to -->"+e);
			logsObj.logError(testcaseName+"Unable to locate the element "+objectLocator+" due to -->",e);

			Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" ,Unable to locate the element due to -->"+e, Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
			return flag;

		}


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
				try{
					generic_Step="Enter the ";
					Step=workFlow+generic_Step+Step;

					element.clear();					    					
					element.sendKeys(input);					    					
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
					flag=element.getAttribute("value");
					if (flag == null){					
						flag=input;
					}
				}catch(StaleElementReferenceException st){
					logsObj.logError("Stale exits for the element-"+objectLocator,st);
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
					element.clear();	
					element.sendKeys(input);
					logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value entered is "+input);
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists after stale and the value entered is "+input, Constants_FRMWRK.Pass);
					flag=element.getAttribute("value");

				}


				return flag; 

			case TEXTBOX_AUTOSUGGEST:	
				generic_Step="Enter Suggestion for ";
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

			case TEXTBOX_AUTOSUGGEST_SELECT:

				String generic_autosuggest_step="Enter Suggestion for ";
				generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;

				element.click();				
				if(input.equalsIgnoreCase("Any")){
					element.sendKeys(Keys.SPACE);
					element.sendKeys(Keys.BACK_SPACE);
					Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
				}else{
					objectLocator=objectLocator+"/../input[2]";
					element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
					element.click();
					element.sendKeys(input);
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);					
				}									    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				if(!input.equalsIgnoreCase("") ){
					objectLocator=objectLocator+"/../div/ul/li/a/div";
					PageLoadWaitUtil.waitForAjax(driver);
					PageLoadWaitUtil.waitForPageToLoad(driver);
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
					flag=KeyMethodsUtil.js_selectItem(driver, workFlow, Step, objectLocator, input);
				}
				return flag; 
		
		case TEXTBOX_AUTOSUGGEST_CHOICE:

			generic_autosuggest_step="Enter Suggestion for ";
			generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;

				//element.click();				
				if(input.equalsIgnoreCase("Any")){
					element.sendKeys(Keys.SPACE);
					element.sendKeys(Keys.BACK_SPACE);
					Reporting.logStep(driver, refID, generic_autosuggest_step, objectType+": "+objectLocator+" exists and the input value is Any hence entered Space & BackSpace to list all items available", Constants_FRMWRK.Pass);
				}else{
					element=FetchWebElement.waitForElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
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
		case TEXTBOX_AUTOSUGGEST_BROWSE:

			generic_autosuggest_step="Select Suggestion for ";
			generic_autosuggest_step=workFlow+generic_autosuggest_step+Step;
			flag=KeyMethodsUtil.textbox_autosuggest_browse(driver,testcaseName, workFlow, generic_autosuggest_step, input, element);
			//commonMethods.switchToFrameFromDefault(driver, testcaseName, Constants_FRMWRK.FindElementByXPATH, ObjRepository.frame_single);
		return flag;
			case TEXTBOX_DECIMALS:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag; 

			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag; 

			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();	
				element.sendKeys(input);	
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag;

			case TEXTBOX_MONEY:
				generic_Step="Enter the ";
				Step=workFlow+generic_Step+Step;

				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=element.getAttribute("value");

				return flag; 

			case TEXTBOX_GETTEXT:
				generic_Step="Get the Text ";
				Step=workFlow+generic_Step+Step;

				try{
					flag=element.getText();
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the text fetched is "+flag);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the text fetched is "+flag, Constants_FRMWRK.Pass);
				}catch (StaleElementReferenceException st)	{
					logsObj.logError("Stale exits for the element-"+objectLocator,st);
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
					flag=element.getText();
					logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the text fetched is "+flag);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists after stale and the text fetched is  "+flag, Constants_FRMWRK.Pass);
					//flag=element.getAttribute("value");
				}

				return flag; 
			case TEXTBOX_GETVALUE:
				generic_Step="Get the Text ";
				Step=workFlow+generic_Step+Step;

				try{
					flag=element.getAttribute("value");
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value fetched is "+input);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value fetched is "+flag, Constants_FRMWRK.Pass);
				}catch (StaleElementReferenceException st)	{
					logsObj.logError("Stale exits for the element-"+objectLocator,st);
					element=FetchWebElement.waitForPresenceOfElement(driver,identifyBy, objectLocator, Constants_TimeOuts.Element_TimeOut) ;
					flag=element.getAttribute("value");
					logsObj.log(testcaseName+"--> "+objectLocator+" exists after stale and the value fetched is "+flag);
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists after stale and the value fetched is  "+flag, Constants_FRMWRK.Pass);
					
				}

				return flag; 

			case BUTTON:	
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				try{
					element.click();				    					
					logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked  ", Constants_FRMWRK.Pass);
					flag=Constants_FRMWRK.Pass;
				}catch(StaleElementReferenceException ex){
					logsObj.log("Button-Encountered stale exception for "+objectLocator+" So trying for recovery..");
					/*int count=StaleElementHandleAndClick(driver,identifyBy,objectLocator);
					if(count<4){
						Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked after recovering stale exception ", Constants_FRMWRK.Warning);
					}*/
					flag=staleRecovery_Click(driver, Step, identifyBy, objectLocator, Constants_TimeOuts.StaleElement_TimeOut);
				}

				PageLoadWaitUtil.waitForPageToLoad(driver);
				return flag;   

			case BROWSE:
				generic_Step="Upload/Browse a ";
				Step=workFlow+generic_Step+Step;

				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is  "+input, Constants_FRMWRK.Pass);
				flag=input;

				PageLoadWaitUtil.waitForPageToLoad(driver);
				return flag; 

			case IMAGE:					
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;


				element.click();			       							
				logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked ", Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.Pass;

				return flag; 

			case LINK:					
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				WorkaroundsSelenium.getElementintoVisible(element);
				element.click();			       							
				logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked ", Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.Pass;

				return flag;

			case TAB:					
				generic_Step="Click on ";
				Step=workFlow+generic_Step+Step;

				element.click();
				logsObj.log(testcaseName+"-->"+objectLocator+" exists and clicked ");
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and clicked ", Constants_FRMWRK.Pass);
				flag=Constants_FRMWRK.Pass;

				return flag; 

			case TITLE:	
				generic_Step="Get Title of ";
				Step=workFlow+generic_Step+Step;

				flag=element.getText();
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and text displayed is "+flag, Constants_FRMWRK.Pass);
				return flag;

			case DATE:
				//Step="Enter Date for "+Step;
				generic_Step="Enter Date for ";
				Step=workFlow+generic_Step+Step;
				element.clear();					    					
				element.sendKeys(input);					    					
				logsObj.log(testcaseName+"--> "+objectLocator+" exists and the value entered is "+input);
				Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and the value entered is "+input, Constants_FRMWRK.Pass);
				flag=input;

				return flag;
			
			case WEBELEMENT:
				generic_Step="Fetches the element text for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getText();
				
			return flag;

			case DROPDOWN:
				generic_Step="Select an Item from dropdown ";
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
						break;
					}catch (Exception e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but unable to select first item  due to -->"+e.getCause(), Constants_FRMWRK.Fail);
						break;
					}

				}else if(input.equalsIgnoreCase("")){
					Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and there is no item to select as per input", Constants_FRMWRK.Info);
					break;
				}

				else{


					try{
						dropdown.selectByVisibleText(input);						
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and an item "+input+" is selected", Constants_FRMWRK.Pass);
						flag=input;											       							       					
						break;
					}catch (Exception e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists but unable to select item "+input+" due to error --> "+e.getMessage(), Constants_FRMWRK.Fail);
						break;
					}
				}

			case DROPDOWN_JQX:
				generic_Step="Select an Item from dropdown ";
				Step=workFlow+generic_Step+Step;
				String staleflag=Constants_FRMWRK.False;
				try{
					element.click();
					logsObj.log(testcaseName+"--> "+objectLocator+" exists and the dropdown_jqx is clicked");
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and the dropdown_jqx is clicked ", Constants_FRMWRK.Pass);
					staleflag=Constants_FRMWRK.True;
				}catch (StaleElementReferenceException st){
					logsObj.log("dropdown-jqx-Encountered stale exception for "+objectLocator+" So trying for recovery..");
					staleflag=staleRecovery_Click(driver, Step, identifyBy, objectLocator, Constants_TimeOuts.StaleElement_TimeOut);
				}
				
				if(staleflag.equalsIgnoreCase(Constants_FRMWRK.False)){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" could not recover from the stale ", Constants_FRMWRK.Fail);
				}
				else if(!input.equalsIgnoreCase("") ){
					PageLoadWaitUtil.waitForAjax(driver);
					PageLoadWaitUtil.waitForPageToLoad(driver);
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
					flag=KeyMethodsUtil.js_selectItem(driver, workFlow, generic_Step, ObjRepository.js_dropdown_items, input);
				}
				return flag; 
			case CHECKBOX:
				generic_Step="Tick an Item(s) from Checkbox ";
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
							Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is not selected/ticked after click action", Constants_FRMWRK.Fail);
						}else{
							Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is selected/ticked", Constants_FRMWRK.Pass);
						}

					}else{
						Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is already selected/ticked", Constants_FRMWRK.Pass);
					}


				}else{

					boolean tick=Constants_FRMWRK.TrueB;
					tick=element.isSelected();
					if(!tick){
						Reporting.logStep(driver, refID, Step,     objectType+": "+objectLocator+" exists and checkbox is already unselected/unticked", Constants_FRMWRK.Pass);
						flag=input;
					}else{
						element.click();
						tick=element.isSelected();
						if(!tick){			       										
							Reporting.logStep(driver, refID, Step,     objectType+": "+objectLocator+" exists and checkbox is un-selected after performing action, which is as per input->"+input, Constants_FRMWRK.Pass);
							flag=input;
						}else{
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,    objectType+": "+objectLocator+" exists and checkbox is not  un-selected after performing action, which is not as per input->"+input, Constants_FRMWRK.Fail);
						}			       							

					}

				}

				break;	


			case RADIOBUTTON:
				generic_Step="Click a Radiobutton for ";
				Step=workFlow+generic_Step+Step;

				if (input.equalsIgnoreCase("Select")){
					boolean Rtick=Constants_FRMWRK.TrueB;
					Rtick=element.isSelected();
					if(!Rtick){
						element.click();
						Rtick=element.isSelected();
						flag=String.valueOf(Rtick);	
						if(!Rtick){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,     objectType+": "+objectLocator+" exists and radiobutton is not selected after click action", Constants_FRMWRK.Fail);
						}else{
							flag=element.getAttribute("value");
							Reporting.logStep(driver, refID, Step,      objectType+": "+objectLocator+" exists and radiobutton is selected", Constants_FRMWRK.Pass);
							break;
						}

					}else{
						flag=element.getAttribute("value");
						Reporting.logStep(driver, refID, Step,      objectType+": "+objectLocator+" exists and radiobutton is already selected", Constants_FRMWRK.Pass);
						break;
					}

				}else{

					boolean Rtick=Constants_FRMWRK.TrueB;
					Rtick=element.isSelected();
					if(!Rtick){
						flag=element.getAttribute("value");
						Reporting.logStep(driver, refID, Step, objectType+": "+objectLocator+" exists and radiobutton is already unselected", Constants_FRMWRK.Pass);

					}else{
						element.click();			       								
						Rtick=element.isSelected();
						flag=String.valueOf(Rtick);
						if(!Rtick){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and radiobutton is not unselected after click action", Constants_FRMWRK.Fail);
						}else{
							flag=element.getAttribute("value");
							Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" exists and radiobutton is unselected", Constants_FRMWRK.Pass);
							break;
						}

					}

				}
				break;				

			case RADIOGROUP:
				//Step="Select  "+Step;
				generic_Step="Select a Radiobutton from group for ";
				Step=workFlow+generic_Step+Step;

				if(element ==null){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, refID, Step,  objectType+": "+objectLocator+" does not exists hence cannot select a radiobutton ", Constants_FRMWRK.Fail);
				}
				else{
					element=ElementMethods.fetchRadiobuttonFromGroup(driver,identifyBy, objectLocator, input);
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
							break;
						}

					}else{
						flag=element.getAttribute("value");
						Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" exists and the required radiobutton "+input+" is already selected", Constants_FRMWRK.Pass);
					}
				}

				break;	
				
			case SWITCHTOFRAMEFROMDEFAULT :							
				generic_Step="Switch to Frame";
				Step=workFlow+generic_Step+Step;
				
				commonMethods.switchToFrameFromDefault(driver, testcaseName, identifyBy, objectLocator);				
				flag=Constants_FRMWRK.Pass;				
				break;

			}



			return flag;

		}

		catch (Throwable e)//General exception

		{


			isTestPass=Constants_FRMWRK.FalseB;
			System.out.println("Unable to locate the element "+objectLocator+" due to -->"+commonMethods.getStackTrace(e));
			logsObj.log(testcaseName+":-Unable to locate the element "+objectLocator+" due to -->"+commonMethods.getStackTrace(e));

			Reporting.logStep(driver, refID, Step,   objectType+": "+objectLocator+" ,Unable to locate the element due to -->"+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
			return flag;

		}


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
	public static String f_fetchElementDetails(WebDriver driver,String testcaseName,String Step,String identifyBy, String objectType, String objectLocator,String input) 	 
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

				flag=element.getAttribute("value");

				if (flag.equals(input)){
					//	ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants.Pass);
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
					//break;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					//ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants.Fail);
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
					//break;
				} 
				break; 



			case TEXTBOX_DECIMALS:

				flag=element.getAttribute("value");
				//String[] sinput=commonFunctions.splitString(input, ".");
				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
					//break;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
					//break;
				}
				break;

			case TEXTBOX_APPDECIMALS_DBNODECIMALS:

				flag=element.getAttribute("value");
				//String[] sinput=commonFunctions.splitString(input, ".");
				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;

			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:

				flag=element.getAttribute("value");

				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;

			case TEXTBOX_MONEY:

				flag=element.getAttribute("value");
				//String[] sinput=commonFunctions.splitString(input, ".");
				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;	

			case TEXTBOX_GETTEXT:

				flag=element.getText();

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);	
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;
				
			case TEXTBOX_GETVALUE:

				flag=element.getAttribute("value");

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);	
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;
			case DATE:		

				element.click();
				WaitUtil.pause(1);
				flag=element.getAttribute("value");


				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;
			case DROPDOWN :

				//Select dropdown=FetchWebElement.waitForDropdownItems(driver, element, Constants_TimeOuts.Element_TimeOut);
				Select dropdown = new Select(element);
				flag=dropdown.getFirstSelectedOption().getText();     

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}

				break;


			case WEBELEMENT:

				flag=element.getText();

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}	

				break;

			case CHECKBOX:

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

				break;  

			case CHECKBOX_READONLY:
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
				break; 



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
		String generic_Step;
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
				generic_Step="Validate the displayed value with entered for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getAttribute("value");

				if (flag.equals(input)){
					//	ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants.Pass);
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
					//break;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					//ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants.Fail);
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
					//break;
				} 
				break; 

			case TEXTBOX_AUTOSUGGEST:
				generic_Step="Validate the displayed value with entered suggested for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getAttribute("value");

				if (flag.equals(input)){
					//	ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants.Pass);
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
					//break;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					//ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants.Fail);
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
					//break;
				} 
				break; 

			case TEXTBOX_AUTOSUGGEST_SELECT:

				generic_Step="Validate the displayed value with entered & selected suggested for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getAttribute("value");

				if (flag.equals(input)){
					//	ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants.Pass);
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
					//break;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					//ReportExcel.Reporter(testcaseName, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants.Fail);
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
					//break;
				} 
				break;

			case TEXTBOX_DECIMALS:
				generic_Step="Validate the displayed value with entered for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getAttribute("value");
				//String[] sinput=commonFunctions.splitString(input, ".");
				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
					//break;
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);
					//break;
				}
				break;

			case TEXTBOX_APPDECIMALS_DBNODECIMALS:
				generic_Step="Validate the displayed value with entered for ";
				Step=workFlow+generic_Step+Step;


				flag=element.getAttribute("value");
				//String[] sinput=commonFunctions.splitString(input, ".");
				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;

			case TEXTBOX_APPDECIMALS_DBSINGLEDECIMAL:
				generic_Step="Validate the displayed value with entered for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getAttribute("value");

				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;

			case TEXTBOX_MONEY:
				generic_Step="Validate the displayed value with entered for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getAttribute("value");
				//String[] sinput=commonFunctions.splitString(input, ".");
				if (flag.matches(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;	

			case TEXTBOX_GETTEXT:
				generic_Step="Validate the displayed value with entered for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getText();

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);	
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;
			
			case TEXTBOX_GETVALUE:

				flag=element.getAttribute("value");

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);	
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;
			case DATE:		
				generic_Step="Validate the displayed value with entered date for ";
				Step=workFlow+generic_Step+Step;

				element.click();
				WaitUtil.pause(1);
				flag=element.getAttribute("value");


				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}
				break;
			case DROPDOWN :
				generic_Step="Validate the displayed value with selected item for ";
				Step=workFlow+generic_Step+Step;

				Select dropdown = new Select(element);
				flag=dropdown.getFirstSelectedOption().getText();	       				

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);
				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}

				break;


			case WEBELEMENT:
				generic_Step="Validate the displayed value with for ";
				Step=workFlow+generic_Step+Step;

				flag=element.getText();

				if (flag.equals(input)){
					Reporting.logStep(driver, Step, objectType+":- "+objectLocator+" Actual Value: "+flag+" matches with the Expected Value:- "+input, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, Step,  objectType+":- "+objectLocator+" Actual Value: "+flag+" does not match with the Expected Value:- "+input, Constants_FRMWRK.Fail);

				}	

				break;



			case CHECKBOX:
				generic_Step="Validate the item value is ticked with selected item for ";
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

				break;  

			case CHECKBOX_READONLY:
				generic_Step="Validate the item value is ticked for ";
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
				break; 



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
	private static String staleRecovery_Click(WebDriver driver,String Step,String identifyBy,String objectLocator,int timeOut){
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
