package com.proj.utilFulcrum;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants;
import com.proj.base.TestBase;
import com.proj.library.KeyMethods;
import com.proj.util.fetchObjectRepository;
import com.report.reporter.Reporting;

public class PopupUtil extends TestBase{

	static String res="";
	static String input="";
	static String className=PopupUtil.class.getSimpleName();

	private static Xls_Reader xlsReader_objects_Popup=new Xls_Reader(Constants.OR_Popup_Path);

	@SuppressWarnings("unused")
	private static Hashtable<String,String>objects_step_Popup=null;
	private static Hashtable<String,String>objects_locatorType_Popup=null; 
	private static Hashtable<String,String>objects_objectType_Popup=null;
	private static Hashtable<String,String>objects_objectLocator_Popup=null;

	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Class "+className);
		try {
			fetchObjectRepository.getObjects(PopupUtil.class,  xlsReader_objects_Popup, "Objects_Popup", "Popup");

		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}
	private static String getMessage(WebDriver driver,String refid,String testcasename,String workFlow){
		String locator_step="Popup- Message";
		return KeyMethods.f_performAction(driver, refid, testcasename, workFlow, locator_step, objects_locatorType_Popup, objects_objectType_Popup, objects_objectLocator_Popup, input);

	}

	private static String clickOK(WebDriver driver,String refid,String testcasename,String workFlow){
		String locator_step="Popup- OK";
		return KeyMethods.f_performAction(driver, refid, testcasename, workFlow, locator_step, objects_locatorType_Popup, objects_objectType_Popup, objects_objectLocator_Popup, input);
	}

	private static String clickCancel(WebDriver driver,String refid,String testcasename,String workFlow){
		String locator_step="Popup- Cancel";
		return KeyMethods.f_performAction(driver, refid, testcasename, workFlow, locator_step, objects_locatorType_Popup, objects_objectType_Popup, objects_objectLocator_Popup, input);
	}

	public static String validatePopup(WebDriver driver,String refid,String testcasename,String workFlow,String message,String buttonToClick) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String flag_message=Constants_FRMWRK.False;
		String flag_button=Constants_FRMWRK.False;
		ApplicationMethods.switchToLatestDLGframe(driver, testcasename);

		String actual_message=getMessage(driver, refid, testcasename, workFlow);
		if (actual_message.contains(message)){
			Reporting.logStep(driver, refid, testcasename, workFlow+" - Popup message", "Popup message displayed "+actual_message+" matches with expected"+message, Constants_FRMWRK.Pass);
			flag_message=Constants_FRMWRK.True;
		}else{
			Reporting.logStep(driver, refid, testcasename, workFlow+" - Popup message", "Popup message displayed "+actual_message+" does not match with expected"+message, Constants_FRMWRK.Fail);			
		}

		if(buttonToClick.equalsIgnoreCase(Constants.ok)){
			flag_button=clickOK(driver, refid, testcasename, workFlow);
		}
		else{
			flag_button=clickCancel(driver, refid, testcasename, workFlow);
		}

		if(flag_message.equalsIgnoreCase(Constants_FRMWRK.True)&& flag_button.equalsIgnoreCase(Constants_FRMWRK.True)){
			flag=Constants_FRMWRK.True;
		}
		return flag;
	}


}
