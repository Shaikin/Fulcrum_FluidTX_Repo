package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DateUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.library.ApplicationMethods_Falcrum;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.proj.utilFulcrum.WorkArounds;
import com.report.reporter.Reporting;

public class Transmittals_EntryPage extends TestSuiteBase{
	
	static String res="";
	static String input="";
	//static WebDriver dr; //Remove this after getting new logStep method
	static String className=Transmittals_EntryPage.class.getSimpleName();
	private static Xls_Reader xlsReader_objects_Transmittals=new Xls_Reader(Constants.OR_TRANS_Path);
	
	private static Hashtable<String,String>objects_step_Transmittals=null;
	private static Hashtable<String,String>objects_locatorType_Transmittals=null; 
	private static Hashtable<String,String>objects_objectType_Transmittals=null;
	private static Hashtable<String,String>objects_objectLocator_Transmittals=null;
	
	private static Hashtable<String,String>objects_step_Transmittals_toolbar=null;
	private static Hashtable<String,String>objects_locatorType_Transmittals_toolbar=null; 
	private static Hashtable<String,String>objects_objectType_Transmittals_toolbar=null;
	private static Hashtable<String,String>objects_objectLocator_Transmittals_toolbar=null;
	
	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(Transmittals_EntryPage.class,  xlsReader_objects_Transmittals, "Objects_Transmittals", "Transmittals");
			fetchObjectRepository.getObjects(Transmittals_EntryPage.class,  xlsReader_objects_Transmittals, "Objects_Transmittals_Toolbar", "Transmittals_toolbar");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);
			
		}
	}
	
	public static void switchToTramsmittalEditFrame(WebDriver driver,String refID,String testcaseName,String workFlow){
		KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, objects_step_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), objects_locatorType_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), objects_objectType_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), objects_objectLocator_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), input);
	}
	public static void switchToTramsmittalViewFrame(WebDriver driver,String refID,String testcaseName,String workFlow){
		KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, objects_step_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), objects_locatorType_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), objects_objectType_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), objects_objectLocator_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), input);
	}
	
	public static void clickSend(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Send", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}
	
	public static void clickCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Cancel", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
	}
	public static void clickSubmit(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Submit", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}
	public static void clickViewCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
	}
	
	public static void clickCompleteAction(WebDriver driver,String workFlow) throws Exception{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver_TRANS, refID, testcaseName, workFlow);
		WaitUtil.pause(3);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Complete Action", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Complete Action -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	
	public static void clickForward(WebDriver driver,String workFlow) throws Exception{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver_TRANS, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Forward", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Forward -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	
	public static void waitInvisiblilityofWorkingTitle(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		boolean tt=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, com.proj.objectRepository.ObjRepository.heading_working, 20);
		System.out.println("Working.. invisibility.."+tt);
	}
	public static Hashtable<String, String> newItem(String appName,WebDriver driver,String refID,String testcaseName,String workFlow,Hashtable<String,String> data){
		Hashtable<String,String>returnData = new Hashtable<String,String>();
		ApplicationMethods_Falcrum.waitForOverlayToDisappear(driver);
		switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		if(appName.equals(Constants.App_Fluid)){
			WorkArounds.deFocusCursor(driver);
		}
		
		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-To", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("To"));
		returnData.put("Tramsmittals-To", res);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-CC", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("CC"));
		returnData.put("Tramsmittals-CC", res);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Subject", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("Subject")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy hh:mm:ss"));
		returnData.put("Tramsmittals-Subject", res);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-IsConfidential", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("IsConfidential"));
		returnData.put("Tramsmittals-IsConfidential", res);
		if(!returnData.get("Tramsmittals-IsConfidential").equalsIgnoreCase(Constants_FRMWRK.Tick)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-TxType", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("TxType"));
			returnData.put("Tramsmittals-TxType", res);
		}else{			
			returnData.put("Tramsmittals-TxType", data.get("TxType"));
		}
		if(appName.equals(Constants.App_Fulcrum)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-ReasonForIssue", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("ReasonForIssue"));
			returnData.put("Tramsmittals-ReasonForIssue", res);
		}else{
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Issue Reason", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("IssueReason"));
			returnData.put("Tramsmittals-Issue Reason", res);
		}
		String dueDate = null;
		if(appName.equals(Constants.App_Fulcrum)){
			dueDate=DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy");
		}else{
			dueDate=DateUtil.getCurrentDateInRequiredDateFormat("MM/dd/yyyy");
		}
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-DueDate", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, dueDate);
		returnData.put("Tramsmittals-DueDate", res);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Message", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("Message"));
		returnData.put("Tramsmittals-Message", res);
		
		clickSend(driver, workFlow);
		WaitUtil.pause(5);
		return returnData;
	}

	
	public static void editItem(WebDriver driver,String refID,String testcaseName,String workFlow,Hashtable<String,String> data,String action){
		switchToTramsmittalViewFrame(driver, refID, testcaseName, workFlow);
		if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note")){
			if(action.equalsIgnoreCase("APPROVED") || action.equalsIgnoreCase("REJECTED")){
				res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Approve/Reject", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
			}
		}
		else if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Consultant Advice")){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Comments", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
		}
		
		
		clickSubmit(driver, workFlow);
		ApplicationMethods_Falcrum.closeAllDialogs(driver, refID, testcaseName);
	}
	
	public static void forward(WebDriver driver,String workFlow,Hashtable<String,String>data) throws Exception{
		
		clickForward(driver, workFlow);
		Hashtable<String,String>returnData = new Hashtable<String,String>();
		switchToTramsmittalViewFrame(driver, refID, testcaseName, workFlow);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-To", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("ForwardTo"));
		returnData.put("Tramsmittals-ForwardTo", res);
		if(!data.get("IsConfidential").equalsIgnoreCase(Constants_FRMWRK.Tick)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-TxType", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("TxType"));
			returnData.put("Tramsmittals-TxType", res);
		}else{			
			returnData.put("Tramsmittals-TxType", data.get("TxType"));
		}
		clickSend(driver, workFlow);
		WorkArounds.getViewPortOfPage(driver);
		//((JavascriptExecutor)driver).executeScript("window.scrollTo(500, 0);");
	/*	commonMethods.switchToDefaultPage(driver);
		WaitUtil.pause(2);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.F12);
		WaitUtil.pause(1);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.F12);*/
		/*driver.switchTo().activeElement().sendKeys(Keys.F12);
		driver.switchTo().activeElement().sendKeys(Keys.F12);*/
		ApplicationMethods_Falcrum.closeAllDialogs(driver, refID, testcaseName);
	}
}
