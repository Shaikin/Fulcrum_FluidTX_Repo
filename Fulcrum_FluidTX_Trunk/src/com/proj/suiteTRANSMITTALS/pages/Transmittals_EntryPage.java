package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DateUtil;
import com.frw.util.FetchElementBackUp;
import com.frw.util.FetchWebElement;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.KeyMethods;
import com.proj.library.KeysUtil;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.proj.utilFulcrum.ApplicationMethods;
import com.proj.utilFulcrum.WebTableUtil;
import com.proj.utilFulcrum.WorkArounds;
import com.report.reporter.Reporting;
@SuppressWarnings("unused")
public class Transmittals_EntryPage extends TestSuiteBase{

	static String res="";
	static String input="";
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

	public static void switchToTramsmittalEditFrame(WebDriver driver,String refID,String testcaseName,String workFlow) throws Throwable{
		//KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, objects_step_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), objects_locatorType_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), objects_objectType_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), objects_objectLocator_Transmittals.get("Tramsmittals-Switch to Tramsmittal Edit Frame"), input);
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
	}
	public static void switchToTramsmittalViewFrame(WebDriver driver,String refID,String testcaseName,String workFlow) throws Throwable{
		//KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, objects_step_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), objects_locatorType_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), objects_objectType_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), objects_objectLocator_Transmittals.get("Tramsmittals-Switch to Tramsmittal View Frame"), input);
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
	}
	
	public static void switchToDocumentsListFrame(WebDriver driver) throws Throwable{
		commonMethods.switchToSingleFrame(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.frame_documentList);
	}
	/**
	 * Clicks on Send button of a Transmittal
	 * @author shaikka
	 * @param driver
	 * @param workFlow
	 */
	public static void clickSend(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Send", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}
	/**
	 * Clicks on Cancel button of a Transmittal
	 * @param driver
	 * @param workFlow
	 */
	public static void clickCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Cancel", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
	}
	public static void clickToolbarCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Cancel", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
	}
	/**
	 * Clicks on Submit button of a Transmittal
	 * @author shaikka
	 * @param driver
	 * @param workFlow
	 */
	public static void clickSubmit(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Submit", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}
	
	/**
	 * Clicks on Delegate button of a Transmittal
	 * @author Anupama
	 * @param driver
	 * @param workFlow
	 * @throws Throwable 
	 */
	public static void clickDelegate(WebDriver driver,String testcasename, String workFlow) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Delegate", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		WaitUtil.pause(6);
	}
	
	
	/**
	 * Clicks on Cancel button of a View Transmittal
	 * @author shaikka
	 * @param driver
	 * @param workFlow
	 */
	public static void clickViewCancel(WebDriver driver,String workFlow){
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
	}
	/**
	 * Clicks on Complete Action button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable
	 */
	public static void clickCompleteAction(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);

		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Complete Action", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Complete Action -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	/**
	 * Clicks on Forward button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable
	 */
	public static void clickForward(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Forward", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Forward -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	/**
	 * Clicks on ReplyAll button of a Transmittal
	 * @param driver
	 * @param workFlow
	 * @throws Throwable
	 */
	
	public static void clickReplyAll(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-ReplyAll", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-ReplyAll -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	
	
	
	public static void clickCloseTransmittal(WebDriver driver,String workFlow) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Close Transmittal", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcaseName, "Tramsmittals-Close Transmittal -Failure", "Please refer above details for more details");
		}
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	public static String checkCancelIsEnabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel - IsEnabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;
	
	}
	public static String checkCancelIsDisabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Cancel - IsDisabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;
	
	}
	
	public static String checkCloseTransmittalIsEnabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Close Transmittal - IsEnabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;
	
	}
	public static String checkCloseTransmittalIsDisabled(WebDriver driver,String workFlow,String refID,String testcaseName) throws Throwable{
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		WaitUtil.pause(3);		
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-View -Close Transmittal - IsDisabled", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		return res;
	
	}
	/**
	 * Waits until the Working On it Tile disappears from page
	 * @author shaikka
	 * @param driver
	 */
	public static void waitInvisiblilityofWorkingTitle(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		boolean tt=ExplicitWaitUtil.waitUntilInvisibilityOfElement(driver, Constants_FRMWRK.FindElementByXPATH, com.proj.objectRepository.ObjRepository.heading_working, 20);
		System.out.println("Working.. invisibility.."+tt);
	}
	
	public static int getRecieverUserCount(Hashtable<String,String>data){
		int flag=1;
		if(data.get("To").contains(Constants.delimiter_data)){
			String[] receivers=commonMethods.splitString(data.get("To"), Constants.delimiter_data);
			flag=receivers.length;			
		}
		logsObj.log("Receiver count is "+flag);
		return flag;
	}
	/**
	 * Enter the require details in To field for a Transmittal
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @return
	 * @throws Throwable
	 */
	private static String enterTo(String appName,WebDriver driver,String testcasename,String workFlow,String data) throws Throwable{
		ApplicationMethods.waitForOverlayToDisappear(driver);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		if(appName.equals(Constants.App_Fluid)){
			WorkArounds.deFocusCursor(driver);
		}
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-To", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, workFlow+"To-Failure", "Due to above failure could not continue execution ,Please refer above details for more details");
		}
		return res;
	}
	/**
	 * Enter the required details to create the Transmittal record.
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param data
	 * @return
	 * @throws Throwable
	 */
	public static Hashtable<String, String> createAndSendTransmittalRecord(String appName,WebDriver driver,String refID,String testcaseName,String workFlow,Hashtable<String,String> data) throws Throwable{
		Hashtable<String,String>returnData = new Hashtable<String,String>();


		res=enterTo(appName, driver,testcaseName, workFlow, data.get("To"));		
		returnData.put("Tramsmittals-To", res);
		returnData.put("Tramsmittals-ToCount",String.valueOf(getRecieverUserCount(data)));
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
			dueDate=DateUtil.dateIncremter(dueDate, 2);
		}else{
			dueDate=DateUtil.getCurrentDateInRequiredDateFormat("MM/dd/yyyy");
			dueDate=DateUtil.dateIncremter(dueDate, 2);
		}
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-DueDate", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, dueDate);
		returnData.put("Tramsmittals-DueDate", res);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Message", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("Message"));
		returnData.put("Tramsmittals-Message", res);
		
		Transmittals_EntryPage.attachdocument(appName,driver, refID, testcaseName, workFlow, data);

		clickSend(driver, workFlow);
		WaitUtil.pause(5);
		return returnData;
	}

	/**
	 * Edit the Transmittal record with required details and Submit
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param refID
	 * @param testcaseName
	 * @param workFlow
	 * @param data
	 * @param action
	 * @throws Throwable
	 */
	public static void editAndSubmitTransmittalRecord(String appName,WebDriver driver,String refID,String testcaseName,String workFlow,Hashtable<String,String> data,String action) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		if(appName.equals(Constants.App_Fulcrum)){
			if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note")){
				if(action.equalsIgnoreCase("APPROVED") || action.equalsIgnoreCase("REJECTED")){
					res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Approve/Reject", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
				}
			}
			else if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Consultant Advice")||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)){
				res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Comments", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
			}			
		}
		else if (appName.equals(Constants.App_Fluid)){
			if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)){
				res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Comments", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);	
			}
			else if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)){
				if(action.equalsIgnoreCase("APPROVED") || action.equalsIgnoreCase("REJECTED")){
					res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Approve/Reject", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, action);
				}
			}

		}


		clickSubmit(driver, workFlow);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
	}
	/**
	 * Forward the tramsmittal with required details
	 * @author shaikka
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @throws Throwable
	 */
	public static void forwardAndSendTransmittalRecord(String appName,WebDriver driver,String testcasename,String workFlow,Hashtable<String,String>data) throws Throwable{		
		clickForward(driver, workFlow);
		Hashtable<String,String>returnData = new Hashtable<String,String>();
		res=enterTo(appName, driver,testcasename, workFlow, data.get("ForwardTo"));
		returnData.put("Tramsmittals-ForwardTo", res);
		if(appName.equals(Constants.App_Fluid)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Issue Reason", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("IssueReason"));
		}
		if(!data.get("IsConfidential").equalsIgnoreCase(Constants_FRMWRK.Tick)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-TxType", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("TxType"));
			returnData.put("Tramsmittals-TxType", res);
		}else{			
			returnData.put("Tramsmittals-TxType", data.get("TxType"));
		}
		clickSend(driver, workFlow);
		WorkArounds.getViewPortOfPage(driver);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
	}
	/**
	 * ReplyAll the transmittal with required details
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @throws Throwable
	 */
	public static void replyAllAndSendTransmittalRecord(String appName,WebDriver driver,String workFlow,Hashtable<String,String>data) throws Throwable{		
		clickReplyAll(driver, workFlow);
		ApplicationMethods.waitForOverlayToDisappear(driver);
		Transmittals_EntryPage.switchToTramsmittalEditFrame(driver, refID, testcaseName, workFlow);
		
		Hashtable<String,String>returnData = new Hashtable<String,String>();
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-ReplyAll", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
		if (res.isEmpty())
		{
			CustomExceptions.Exit(testcaseName, "Tramsmittals-ReplyAll -Failure", "Please refer above details for more details");
		}
	
		if(appName.equals(Constants.App_Fluid)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-Issue Reason", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("IssueReason"));
		}
		if(!data.get("IsConfidential").equalsIgnoreCase(Constants_FRMWRK.Tick)){
			res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-TxType", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("TxType"));
			returnData.put("Tramsmittals-TxType", res);
		}else{			
			returnData.put("Tramsmittals-TxType", data.get("TxType"));
		}
		clickSend(driver, workFlow);
		WorkArounds.getViewPortOfPage(driver);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
	}
	
	/**
	 * Delegate and send transmittal to a user
	 * @param appName
	 * @param driver
	 * @param workFlow
	 * @param data
	 * @throws Throwable
	 */
	public static void delegateAndSendTransmittalRecord(String appName,WebDriver driver,String testcasename,String workFlow,Hashtable<String,String>data) throws Throwable{		
		clickDelegate(driver, testcasename, workFlow);
		
		
		System.out.println(data.get("DelegateTo"));
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		WorkArounds.deFocusCursor(driver);
		WaitUtil.pause(3);
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Tramsmittals-DelegateTo", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("DelegateTo"));

		clickSubmit(driver, workFlow);
		ApplicationMethods.closeAllDialogs(driver, refID, testcaseName);
	}
	
	public static void attachdocument(String appName,WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		String condition="";
		String condition_workFlow="";
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Edit", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
		
		if(appName.equals(Constants.App_Fulcrum)){
			condition=Constants_Workflow.Fulcrum_WorkFlow_Data_Condition;
			condition_workFlow=Constants_Workflow.Fulcrim_WorkFlow_ConsultantAdvice;			
		}else{
			condition=Constants_Workflow.FluidTX_WorkFlow_Data_Condition;
			condition_workFlow=Constants_Workflow.FluidTX_WorkFlow_IssuedForReview;
		}
		if(data.get(condition).equalsIgnoreCase(condition_workFlow) && !data.get("ReviewDocument").isEmpty()){
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Attach Review Sheet", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
			ApplicationMethods.waitForOverlayToDisappear(driver);
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String fileName=Constants.DataFileLocation_Transmittal+data.get("ReviewDocument");
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Browse", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, fileName);
			if(!res.equalsIgnoreCase(Constants_FRMWRK.Fail)){
				res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Browse-OK", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, input);
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
				String locator=objects_objectLocator_Transmittals.get("Tramsmittals-Attached Review Document Files").replaceAll("docName", data.get("ReviewDocument"));
				objects_objectLocator_Transmittals.put("Tramsmittals-Attached Review Document Files", locator);
				KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-Attached Review Document Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("ReviewDocument"));
			}
			
		}
		if(!data.get("AttachDocuments").isEmpty()){		
			if(!data.get("AttachDocuments").isEmpty()){
				res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Attach Document", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
				ApplicationMethods.waitForOverlayToDisappear(driver);
				ApplicationMethods.switchToLatestDLGframe(driver, testcasename);

				res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Document Registry", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, data.get("AttachDocuments"));
				switchToDocumentsListFrame(driver);
				String page ="Attach Document";
				WebTableUtil.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcaseName, workflow+" "+page+" - Select a Document", ObjRepository.container_documentRegister, data.get("AttachDocumentName"),"Text", data.get("AttachDocumentName"), 2, 1);
				res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Attach", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
				ApplicationMethods.waitForOverlayToDisappear(driver);
			}			
			
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String locator=objects_objectLocator_Transmittals.get("Tramsmittals-Attached Transmittal Files").replaceAll("docName", data.get("AttachDocumentName"));
			objects_objectLocator_Transmittals.put("Tramsmittals-Attached Transmittal Files", locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-Attached Transmittal Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("AttachDocumentName"));
		}
		
		if(!data.get("AttachSupportDocuments").isEmpty()){
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Attach Support Document", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
			ApplicationMethods.waitForOverlayToDisappear(driver);
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Document Registry", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, data.get("AttachSupportDocuments"));
			switchToDocumentsListFrame(driver);
			String page ="Attach Support Document";
			WebTableUtil.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcaseName, workflow+" "+page+" - Select a Document", ObjRepository.container_documentRegister, data.get("AttachSupportDocumentName"),"Text", data.get("AttachSupportDocumentName"), 2, 1);
			res=KeyMethods.f_performAction(driver, refid, testcasename, workflow, "Tramsmittals-Attach", objects_locatorType_Transmittals_toolbar, objects_objectType_Transmittals_toolbar, objects_objectLocator_Transmittals_toolbar, input);
			ApplicationMethods.waitForOverlayToDisappear(driver);			
			
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String locator=objects_objectLocator_Transmittals.get("Tramsmittals-Attached Support Document Files").replaceAll("docName", data.get("AttachSupportDocumentName"));
			objects_objectLocator_Transmittals.put("Tramsmittals-Attached Support Document Files", locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-Attached Support Document Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("AttachSupportDocumentName"));
		}
		
	}
	
	public static void verifyAttachedFiles(String appName,WebDriver driver,String testcasename,String refid,String workflow,Hashtable<String,String>data) throws Throwable{
		if(data.get(Constants_Workflow.FluidTX_WorkFlow_Data_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview) && !data.get("ReviewDocument").isEmpty()){
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String locator=objects_objectLocator_Transmittals.get("Tramsmittals-View - Attached Review Document Files").replaceAll("docName", data.get("ReviewDocument"));
			objects_objectLocator_Transmittals.put("Tramsmittals-View - Attached Review Document Files", locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-View - Attached Review Document Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("ReviewDocument"));
			
		}
		if(!data.get("AttachDocuments").isEmpty()){	
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String locator=objects_objectLocator_Transmittals.get("Tramsmittals-View -Transmittal Files").replaceAll("docName", data.get("AttachDocumentName"));
			objects_objectLocator_Transmittals.put("Tramsmittals-View -Transmittal Files", locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-View -Transmittal Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("AttachDocumentName"));
			
		}		
		if(!data.get("AttachSupportDocuments").isEmpty()){
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
			String locator=objects_objectLocator_Transmittals.get("Tramsmittals-View -Supporting Document Files").replaceAll("docName", data.get("AttachSupportDocumentName"));
			objects_objectLocator_Transmittals.put("Tramsmittals-View -Supporting Document Files", locator);
			KeyMethods.f_fetchElementDetails(driver, refid, testcasename, workflow, "Tramsmittals-View -Supporting Document Files", objects_locatorType_Transmittals, objects_objectType_Transmittals, objects_objectLocator_Transmittals, data.get("AttachSupportDocumentName"));
		}
	}
}
