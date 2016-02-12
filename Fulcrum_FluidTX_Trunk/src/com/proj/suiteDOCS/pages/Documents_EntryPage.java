package com.proj.suiteDOCS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DateUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.suiteDOCS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.proj.utilFulcrum.ApplicationMethods;
import com.report.reporter.Reporting;

public class Documents_EntryPage extends TestSuiteBase{
	
	static String res="";
	static String input="";
	//static WebDriver dr; //Remove this after getting new logStep method
	static String className=Documents_EntryPage.class.getSimpleName();
	private static Xls_Reader xlsReader_objects_Documents=new Xls_Reader(Constants.OR_DOCS_Path);
	
	
	private static Hashtable<String,String>objects_step_Documents=null;
	private static Hashtable<String,String>objects_locatorType_Documents=null; 
	private static Hashtable<String,String>objects_objectType_Documents=null;
	private static Hashtable<String,String>objects_objectLocator_Documents=null;
	
	
	
	static {		
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(Documents_EntryPage.class,  xlsReader_objects_Documents, "Objects_Documents", "Documents");
			
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);
			
		}
	}
	
	public static void clickNewDocument(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "New Document", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("New Document")+" - Failure", "Please refer above details for more information");
		}
	}
	public static void clickOK(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "OK", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("OK")+" - Failure", "Please refer above details for more information");
		}
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
	}
	public static void clickSave(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Save", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("Save")+" - Failure", "Please refer above details for more information");
		}
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
	}
	public static void browseAFile(WebDriver driver,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver,testcasename);
		String filename=Constants.DataFileLocation_Documents+data.get("Choose a file");
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Choose a file", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, filename);
	}
	public static void destinationFolder(){
		//Need to implement after POC and when sprint starts.
	}
	public static String title(WebDriver driver,String testcasename,String workflow,Hashtable<String,String>data){
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Title", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Title")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy hh:mm:ss"));
		return res;
	}
	public static void versionComments(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data){
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Version Comments", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Version Comments"));
		
	}
	public static void uploadADocumentIntoApplication(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		clickNewDocument(driver, refID, testcasename, workflow);
		browseAFile(driver, testcasename, workflow, data);
		versionComments(driver, refID, testcasename, workflow, data);
		clickOK(driver, refID, testcasename, workflow);
		title(driver, testcasename, workflow, data);
		clickSave(driver, refID, testcasename, workflow);
		
	}
}
