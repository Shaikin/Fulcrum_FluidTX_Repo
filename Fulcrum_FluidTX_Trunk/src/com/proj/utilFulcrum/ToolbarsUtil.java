package com.proj.utilFulcrum;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.Xls_Reader;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.util.fetchObjectRepository;
import com.report.reporter.Reporting;

public class ToolbarsUtil extends TestBase{
	
	static String res="";
	static String input="";	
	static String className=ToolbarsUtil.class.getSimpleName();
	
	private static Xls_Reader xlsReader_objects_toolbar=new Xls_Reader(Constants.OR_Toolbar_Path);

	@SuppressWarnings("unused")
	private static Hashtable<String,String>objects_step_Toolbar=null;
	private static Hashtable<String,String>objects_locatorType_Toolbar=null; 
	private static Hashtable<String,String>objects_objectType_Toolbar=null;
	private static Hashtable<String,String>objects_objectLocator_Toolbar=null;
	
	
	static {		
		logsObj.log("fetchExcelobjects method triggred for "+className+" Class");
		try {
			fetchObjectRepository.getObjects(ToolbarsUtil.class,xlsReader_objects_toolbar, "Objects_Toolbar", "Toolbar");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}
	
		public static void clickItems(WebDriver driver,String workFlow) throws Throwable{
			commonMethods.switchToDefaultPage(driver);
			ExplicitWaitUtil.waitForElementTobeActionable(driver, objects_locatorType_Toolbar.get("Toolbar - Items"), objects_objectLocator_Toolbar.get("Toolbar - Items"), Constants_TimeOuts.Element_TimeOut);
			KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Toolbar - Items", objects_locatorType_Toolbar, objects_objectType_Toolbar, objects_objectLocator_Toolbar, input);
		}
	public static class Items {
		public static void clickSendTranmittals(WebDriver driver,String workFlow) throws Throwable{
			commonMethods.switchToDefaultPage(driver);
			ExplicitWaitUtil.waitForElementTobeActionable(driver, objects_locatorType_Toolbar.get("Toolbar - Items - Start New Tranmittal"), objects_objectLocator_Toolbar.get("Toolbar - Items - Start New Tranmittal"), Constants_TimeOuts.Element_TimeOut);
			KeyMethods.f_performAction(driver, refID, testcaseName, workFlow, "Toolbar - Items - Start New Tranmittal", objects_locatorType_Toolbar, objects_objectType_Toolbar, objects_objectLocator_Toolbar, input);
		}
	}
	
	
	
	

}
