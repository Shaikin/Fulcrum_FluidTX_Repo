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
import com.proj.Constants.Constants;
import com.proj.base.TestBase;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.report.reporter.Reporting;

public class PopupUtil extends TestBase{

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
	@SuppressWarnings("unused")
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


