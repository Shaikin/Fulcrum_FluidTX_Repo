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
import com.proj.library.LocalDriverManager;
import com.proj.library.commonMethods;
import com.proj.suiteDOCS.TestSuiteBase;
import com.proj.suiteDOCS.reusables.DocumentRegisterGridUtil;
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
	private static String locator_document_pattern =".//*[@id='js-newdocWOPI-divMain-WPQ2']/descendant::a[contains(@id,'js-newdocWOPI-divobjectlocator')]";
    

	private static Hashtable<String,String>objects_step_Documents=null;
	private static Hashtable<String,String>objects_locatorType_Documents=null; 
	private static Hashtable<String,String>objects_objectType_Documents=null;
	private static Hashtable<String,String>objects_objectLocator_Documents=null;
	
	@SuppressWarnings("unused")
	private static Hashtable<String,String>objects_step_Documents_Toolbar=null;
	private static Hashtable<String,String>objects_locatorType_Documents_Toolbar=null; 
	private static Hashtable<String,String>objects_objectType_Documents_Toolbar=null;
	private static Hashtable<String,String>objects_objectLocator_Documents_Toolbar=null;
	
	
	static {		
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(Documents_EntryPage.class,  xlsReader_objects_Documents, "Objects_Documents", "Documents");
			fetchObjectRepository.getObjects(Documents_EntryPage.class,  xlsReader_objects_Documents, "Objects_Documents_Toolbar", "Documents_Toolbar");
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
	public static void browseAFile(WebDriver driver,String refID, String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver,testcasename);
		String filename=Constants.DataFileLocation_Documents+data.get("Choose a file");
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Choose a file", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, filename);
	}
	public static void destinationFolder(){
		//Need to implement after POC and when sprint starts.
	}
	public static String name(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data){
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Name", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Name")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("ddMMyyyy hhmmss"));
		return res;
	}
	public static String title(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data){
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Title", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Title")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd/MM/yyyy hh:mm:ss"));
		return res;
	}
	public static void versionComments(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data){
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Version Comments", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Version Comments"));
		
	}
	
		public static void clickUploadExistingFile(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
			res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Click Upload Existing file", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, input);
			if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
				CustomExceptions.Exit(testcasename, objects_step_Documents.get("Click Upload Existing file")+" - Failure", "Please refer above details for more information");
			}
			ApplicationMethods.switchToLatestDLGframe(driver, testcasename);
		}
	public static String uploadADocumentIntoApplication(WebDriver driver,String refid,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		clickNewDocument(driver, refid, testcasename, workflow);
		clickUploadExistingFile(driver, refid, testcasename, workflow); 
		browseAFile(driver, refid, testcasename, workflow, data);
		versionComments(driver, refid, testcasename, workflow, data);
		clickOK(driver, refid, testcasename, workflow);
		String getName = name(driver,refid, testcasename, workflow, data);
		title(driver, refid,testcasename, workflow, data);
		clickSave(driver, refid, testcasename, workflow);
		return getName;
	}
	public static void clickOnlineWord(WebDriver driver,String workflow) throws Throwable{
		String Step="Create a new file -Word document";
		String locator=locator_document_pattern.replaceAll("objectlocator", objects_objectLocator_Documents.get(Step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Documents.get(Step), objects_locatorType_Documents.get(Step), objects_objectType_Documents.get(Step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+Step, "Please refer above details for more details");
		}
	}
	public static void clickOnlineExcel(WebDriver driver,String workflow) throws Throwable{
		String Step="Create a new file -Excel workbook";
		String locator=locator_document_pattern.replaceAll("objectlocator", objects_objectLocator_Documents.get(Step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Documents.get(Step), objects_locatorType_Documents.get(Step), objects_objectType_Documents.get(Step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+Step, "Please refer above details for more details");
		}
	}
	public static void clickOnlinePowerPoint(WebDriver driver,String workflow) throws Throwable{
		String Step="Create a new file -PowerPoint presentation";
		String locator=locator_document_pattern.replaceAll("objectlocator", objects_objectLocator_Documents.get(Step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Documents.get(Step), objects_locatorType_Documents.get(Step), objects_objectType_Documents.get(Step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+Step, "Please refer above details for more details");
		}
	}
	public static void clickOnlineOneNote(WebDriver driver,String workflow) throws Throwable{
		String Step="Create a new file -OneNote notebook";
		String locator=locator_document_pattern.replaceAll("objectlocator", objects_objectLocator_Documents.get(Step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Documents.get(Step), objects_locatorType_Documents.get(Step), objects_objectType_Documents.get(Step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+Step, "Please refer above details for more details");
		}
	}
	public static void clickOnlineNewFolder(WebDriver driver,String workflow) throws Throwable{
		String Step="Create a new file -New folder";
		String locator=locator_document_pattern.replaceAll("objectlocator", objects_objectLocator_Documents.get(Step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, workflow, objects_step_Documents.get(Step), objects_locatorType_Documents.get(Step), objects_objectType_Documents.get(Step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+Step, "Please refer above details for more details");
		}
	}
	public static String enterOnlineDocumentName(WebDriver driver,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Online Document name", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Online Document name")+"-"+DateUtil.getCurrentDateInRequiredDateFormat("dd-MM-yyyy hhmmss"));
		return res;
	}
	public static String enterContentInDocument(WebDriver driver,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		WaitUtil.pause(10);
		commonMethods.switchToFrameFromDefault(driver, testcasename, Constants_FRMWRK.FindElementByNAME, "WebApplicationFrame");
		
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Document Content", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Document Content"));
		return res;
	}
	public static void clickSaveDocument(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Save Document", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("Save Document")+" - Failure", "Please refer above details for more information");
		}
		
	}
	public static void clickCloseDocument(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Close document", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("Close document")+" - Failure", "Please refer above details for more information");
		}
		
	}
	static String condition="";
	
	public static String uploadOrCreateOnlineDocument(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data)throws Throwable{
		String getName = "";
		WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);
		if(data.get("Document type").contains("Upload Document"))
		{
			 getName = uploadADocumentIntoApplication(driver, refID, testcasename, workflow, data); 
		}
		else 
			createOnlineDocument(driver,refID,testcasename,workflow,data);
		
		return getName;
		}
	
	public static void createOnlineDocument(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data)throws Throwable{
		clickNewDocument(LocalDriverManager.getDriver(), condition, testcaseName, workflow);
		createNewFileDocumentType(LocalDriverManager.getDriver(), condition, testcaseName, workflow, data);
		enterOnlineDocumentName(LocalDriverManager.getDriver(), testcaseName, workflow, data);
		clickOK(LocalDriverManager.getDriver(), condition, testcaseName, workflow);
		enterContentInDocument(LocalDriverManager.getDriver(), testcaseName, workflow, data);
		clickSaveDocument(LocalDriverManager.getDriver(), condition, testcaseName, workflow);
		clickCloseDocument(LocalDriverManager.getDriver(), condition, testcaseName, workflow);
	}
	
	public static void createNewFileDocumentType(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data)throws Throwable{
		WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);
		if(data.get("Document type").contains("Word document")){
			clickOnlineWord(driver,workflow);
			}
		else if	(data.get("Document type").contains("Excel workbook")){
			clickOnlineExcel(driver,workflow);
			}
		else if	(data.get("Document type").contains("PowerPoint presentation")){
			clickOnlinePowerPoint(driver,workflow);
			}		
		else if	(data.get("Document type").contains("OneNote notebook")){
			clickOnlineOneNote(driver,workflow);
			}
		else if	(data.get("Document type").contains("New folder")){
			clickOnlineNewFolder(driver,workflow);
			}
		}
	
	public static void clickFileRibbon(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Documens_FileRibbon", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("Documens_FileRibbon")+" - Failure", "Please refer above details for more information");
	}
	
	}
	public static String validateCheckIn(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "ValidateCheckIn", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		return res;
	}
	
	public static String validateCheckOut(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "ValidateCheckOut", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		return res;
	}
	
	public static void checkIn(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "CheckIn", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("CheckIn")+" - Failure", "Please refer above details for more information");
	}
	}
	
	public static void checkOut(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "CheckOut", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("CheckOut")+" - Failure", "Please refer above details for more information");
	}
	}
	
	public static String validatePublish(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "VaidatePublish", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		return res;
	}
	
	public static void publish(WebDriver driver,String refID,String testcasename,String workflow) throws Throwable{
		KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Publish", objects_locatorType_Documents_Toolbar, objects_objectType_Documents_Toolbar, objects_objectLocator_Documents_Toolbar, input);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			CustomExceptions.Exit(testcasename, objects_step_Documents.get("Publish")+" - Failure", "Please refer above details for more information");
	}
	}
	public static String publishComments(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data) throws Throwable{
		ApplicationMethods.switchToLatestDLGframe(driver, testcaseName);
		res=KeyMethods.f_performAction(driver, refID, testcasename, workflow, "Publish Comments", objects_locatorType_Documents, objects_objectType_Documents, objects_objectLocator_Documents, data.get("Publish Comments"));
		return res;
	}
	
	public static void publishMajorVersion(WebDriver driver,String refID,String testcasename,String workflow,Hashtable<String,String>data,String getName, String version)throws Throwable{
		
		clickFileRibbon(driver,refID,testcaseName,workflow);
		WaitUtil.pause(10);
		validatePublish(driver, refID,testcaseName,workflow);
		
		if (res.equalsIgnoreCase(Constants_FRMWRK.True) )
		{
			
			publish(driver,refID, testcaseName, workflow);
		}
		else if(res.equalsIgnoreCase(Constants_FRMWRK.False) ){
			validateCheckIn(driver, refID,testcaseName,workflow);
			if (res.equalsIgnoreCase(Constants_FRMWRK.True))
			{
				checkIn(driver,refID, testcaseName, workflow);
			}
			else
			{
				CustomExceptions.Exit(testcasename, objects_step_Documents.get("checkIn")+" - Failure", "Please refer above details for more information");
			}
		}
		
		publishComments(driver,refID,testcasename,workflow,data);
		clickOK(LocalDriverManager.getDriver(), refID, testcaseName, workflow);
		WaitUtil.pause(10);
		DocumentRegisterGridUtil.validateVersion(driver, refID,workflow, testcaseName, getName, version);
}
}
	
	
	

