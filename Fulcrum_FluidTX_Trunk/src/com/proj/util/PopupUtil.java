package com.proj.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.WaitUtil;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.report.reporter.Reporting;

public class PopupUtil extends TestBase{

	/**
	 * Check for the message in the Popup (HTML) and Click required button
	 * @author sahamed
	 * @Date 18 Nov 2014
	 * @param testcaseName
	 * @param Step
	 * @param messageToSearch
	 * @param buttonToClick
	 * @return 
	 */

	public static String checkPopupAndClickButton(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String popupHeadObjectLocator,String messageToSearch,String buttonToClick){
		String flag=Constants_FRMWRK.False;

		WebElement element=FetchWebElement.waitForElement(driver,Constants_FRMWRK.FindElementByXPATH, popupHeadObjectLocator, Constants_TimeOuts.ModalWindowClosure_TimeOut);

		if(!(element== null)){
			try{
				flag=element.getText();
				if(flag.equalsIgnoreCase(messageToSearch)){
					Reporting.logStep(driver, workFlow+Step, "Popup Message under search matches with displayed message "+messageToSearch+"-"+flag, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver,workFlow+Step, "Popup Message under search does not matches with displayed message "+messageToSearch+"-"+flag, Constants_FRMWRK.Fail);
				}

				clickButton(driver, refID, testcaseName, workFlow, Step, buttonToClick);
				//boolean deleteWindow=FetchWebElement.waitForInvisibilityOfElement(driver,Constants_FRMWRK.FindElementByXPATH, ObjRepository.popup_head, Constants_TimeOuts.ModalWindowClosure_TimeOut);
				boolean deleteWindow=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver,Constants_FRMWRK.FindElementByXPATH, ObjRepository.popup_head, Constants_TimeOuts.ModalWindowClosure_TimeOut);
				if(!testcaseName.contains("DeleteEmployeeWithAssets")){
					if(deleteWindow==Constants_FRMWRK.FalseB){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, workFlow+Step, "Popup Message window still exists after clicking button "+messageToSearch+"-"+flag, Constants_FRMWRK.Fail);
					}
				}
			}catch(Throwable t){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, workFlow+Step, "Popup Message Unable to validate the message due to error "+t, Constants_FRMWRK.Fail);
				logsObj.logError("Popup Message Unable to validate the message due to error ", t);
			}
		}else{
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver,workFlow+ Step, "Popup window is not displayed ", Constants_FRMWRK.Fail);
		}

		return flag;
	}

	/**
	 * Click on the required button of the popup displayed
	 * @author khshaik
	 * @date Nov 18 2014
	 * @param driver
	 * @param Step
	 * @param clickButton
	 */
	private static void clickButton(WebDriver driver,String RefID,String testcaseName,String workFlow,String Step,String clickButton){
		if(clickButton.equalsIgnoreCase("OK") || clickButton.equalsIgnoreCase("YES")||clickButton.equalsIgnoreCase("CONFIRM")){
			//KeyMethods.f_performAction(driver, RefID, clickButton, Step+" Button Yes/OK", Constants_FRMWRK.FindElementByID, Constants.objectType_Button, ObjRepository.popup_success, "");
			KeyMethods.f_performAction(driver, RefID, testcaseName, workFlow, Step+" Button Yes/OK/Confirm", Constants_FRMWRK.FindElementByID, Constants.objectType_Button, ObjRepository.popup_success, "");
		}else if(clickButton.equalsIgnoreCase("SAVE")){
			KeyMethods.f_performAction(driver, RefID, testcaseName, workFlow, Step+" Button SAVE", Constants_FRMWRK.FindElementByXPATH, Constants.objectType_Button, ObjRepository.popup_save, "");
		}		
		else{
			//KeyMethods.f_performAction(driver, RefID, clickButton, Step+" Button No/Cancel", Constants_FRMWRK.FindElementByID, Constants.objectType_Button, ObjRepository.popup_cancel, "");
			KeyMethods.f_performAction(driver, RefID, testcaseName, workFlow, Step+" Button No/Cancel", Constants_FRMWRK.FindElementByID, Constants.objectType_Button, ObjRepository.popup_cancel, "");
		}
		WaitUtil.pause(Constants_TimeOuts.Overlay_disappear);// wait until toaster is get closed
	}

	/**
	 * Check for the message in the Popup (HTML) and Click required button if exists 
	 * @author khshaik
	 * @date Dec 23 2014
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param popupHeadObjectLocator
	 * @param messageToSearch
	 * @param buttonToClick
	 * @return
	 */

	public static String checkPopupAndButtonToClick(WebDriver driver,String refID,String testcaseName,String workFlow,String Step,String popupHeadObjectLocator,String messageToSearch,String buttonToClick){
		String flag=Constants_FRMWRK.False;

		WebElement element=FetchWebElement.waitForElement(driver,Constants_FRMWRK.FindElementByXPATH, popupHeadObjectLocator, Constants_TimeOuts.ModalWindowClosure_TimeOut);

		if(!(element== null)){
			try{
				flag=element.getText();
				if(flag.equalsIgnoreCase(messageToSearch)){
					Reporting.logStep(driver, workFlow+Step, "Popup Message under search matches with displayed message "+messageToSearch+"-"+flag, Constants_FRMWRK.Pass);

				}else{
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver,workFlow+Step, "Popup Message under search does not matches with displayed message "+messageToSearch+"-"+flag, Constants_FRMWRK.Fail);
				}

				ButtonToClick(driver, refID, testcaseName, workFlow, Step, buttonToClick);
				//boolean deleteWindow=FetchWebElement.waitForInvisibilityOfElement(driver,Constants_FRMWRK.FindElementByXPATH, ObjRepository.popup_head, Constants_TimeOuts.ModalWindowClosure_TimeOut);
				boolean deleteWindow=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver,Constants_FRMWRK.FindElementByXPATH, ObjRepository.popup_head, Constants_TimeOuts.ModalWindowClosure_TimeOut);
				if(deleteWindow==Constants_FRMWRK.FalseB){
					isTestPass=Constants_FRMWRK.FalseB;
					Reporting.logStep(driver, workFlow+Step, "Popup Message window still exists after clicking button "+messageToSearch+"-"+flag, Constants_FRMWRK.Fail);
				}
			}catch(Throwable t){
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, workFlow+Step, "Popup Message Unable to validate the message due to error "+t, Constants_FRMWRK.Fail);
				logsObj.logError("Popup Message Unable to validate the message due to error ", t);
			}
		}

		return flag;
	}
	/**
	 * Click given button with text 
	 * @author khshaik
	 * @date Dec 23 2014
	 * @param driver
	 * @param RefID
	 * @param testcaseName
	 * @param workFlow
	 * @param Step
	 * @param clickButton
	 */
	private static void ButtonToClick(WebDriver driver,String RefID,String testcaseName,String workFlow,String Step,String clickButton){
		String buttonxpath="//button[text()='buttonName']";
		buttonxpath=commonMethods.replaceString("buttonName", buttonxpath, clickButton);
		KeyMethods.f_performAction(driver, RefID, testcaseName, workFlow, Step+" Button to Click"+clickButton, Constants_FRMWRK.FindElementByXPATH, Constants.objectType_Button, buttonxpath, "");

	}

	/**
	 * Get the title of the Page/Dialog
	 * @author kashaik
	 * @date 13 Sep 2013
	 * @param driver
	 * @return
	 */
	public static boolean isDialogPresent(WebDriver driver) {
		try {
			driver.getTitle();
			return false;
		} catch (UnhandledAlertException e) {
			// Modal dialog showed
			return true;
		}
	}


	/**
	 * Verification of message in the validation popup 
	 * @author khshaik
	 * @date Mar 24 2015
	 * @param driver
	 * @param refID
	 * @param workFlow
	 * @param message
	 * @return
	 */
	public static String validation_popup(WebDriver driver,String refID,String workFlow,String message){
		String flag=Constants_FRMWRK.False;


		try{
			WebElement element_popup;
			String actual_message;

			element_popup=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.validation_popup_span, Constants_TimeOuts.Element_TimeOut);
			if(element_popup==null){
				Reporting.logStep(driver, refID,workFlow+"-Validation Popup message", "Validation popup is not displayed ", Constants_FRMWRK.Fail);
				return flag;
			}
			actual_message =element_popup.getText();
			if(actual_message.equalsIgnoreCase(message)){
				Reporting.logStep(driver, refID,workFlow+"-Validation Popup message", "Validation popup exists and message displayed "+actual_message+" matches with the expected "+message, Constants_FRMWRK.Pass);
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, refID,workFlow+"-Validation Popup message", "Validation popup exists and message displayed "+actual_message+" does not matches with the expected "+message, Constants_FRMWRK.Fail);
			}
		}catch(Throwable t){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID,workFlow+"-Validation Popup message-Failure", "Cannot Validate popup message due to error "+t, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Verification of message in the validation popup 
	 * @author khshaik
	 * @date Mar 24 2015
	 * @param driver
	 * @param refID
	 * @param workFlow
	 * @param message
	 * @return
	 */
	public static String validation_popup_2(WebDriver driver,String refID,String workFlow,String message){
		String flag=Constants_FRMWRK.False;


		try{
			WebElement element_popup;
			String actual_message;

			element_popup=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.validation_popup_div, Constants_TimeOuts.Element_TimeOut);

			if(element_popup==null){
				Reporting.logStep(driver, refID,workFlow+"-Validation Popup message", "Validation popup is not displayed ", Constants_FRMWRK.Fail);
				return flag;
			}
			actual_message =element_popup.getText();
			if(actual_message.equalsIgnoreCase(message)){
				Reporting.logStep(driver, refID,workFlow+"-Validation Popup message", "Validation popup exists and message displayed "+actual_message+" matches with the expected "+message, Constants_FRMWRK.Pass);
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver, refID,workFlow+"-Validation Popup message", "Validation popup exists and message displayed "+actual_message+" does not matches with the expected "+message, Constants_FRMWRK.Fail);
			}
		}catch(Throwable t){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID,workFlow+"-Validation Popup message-Failure", "Cannot Validate popup message due to error "+t, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Verification of Field Level Validation message(s) displayed in the form
	 * @author Monal
	 * @date Apr 2015
	 * @param driver
	 * @param refID
	 * @param workFlow
	 * @param messageLocator
	 * @param fld_len
	 */
	@SuppressWarnings("rawtypes")
	public static void validation_FieldLevel_popup(WebDriver driver,String refID,String workFlow,String messageLocator,LinkedHashMap<String,String> fld_len){	
		int userip = fld_len.size();
		if(userip!=0)
		{
			String maximum="* Maximum "+"";

			List<WebElement>msgs=FetchWebElement.FetchWebElements(driver, Constants_FRMWRK.FindElementByXPATH, messageLocator);
			ArrayList<String> errmsg = new ArrayList<String>();
			for(WebElement e: msgs)   
				if (e.getText().startsWith(maximum))
				{
					errmsg.add(e.getText());
				}       
			int msgcount = errmsg.size();//1
			int l=0,m=0;  

			Set entries  = fld_len.entrySet();        
			Iterator it = entries.iterator();
			if(msgcount!=0){
				while(it.hasNext()){

					Map.Entry pair = (Map.Entry)it.next();
					String messages = "* Maximum "+fld_len.values().toArray()[l]+" characters allowed for this field";
					if(messages.equals(errmsg.get(m)))
					{
						if(m<msgcount-1)  
							m++;

						Reporting.logStep(driver,refID, workFlow+" Verification of max field length msgs-"+pair.getKey(), "Able to compare the msgs listed", Constants_FRMWRK.Pass);
						l++;		


					}
					else 
					{
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver,refID, workFlow+" Verification of max field length msgs-"+pair.getKey(), "Unable to compare the msgs ", Constants_FRMWRK.Fail);
						
						l++;
						if(userip <= msgcount){
							m++;
						}

					}
				}
			} 
			else
			{
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep(driver,refID, workFlow+" Verification of max field length msgs", "No msgs displayed", Constants_FRMWRK.Fail);
			}      

		}
		else{
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, refID,workFlow+" Verification of max field length msgs", "No User Input", Constants_FRMWRK.Fail);

		}
	}
}


