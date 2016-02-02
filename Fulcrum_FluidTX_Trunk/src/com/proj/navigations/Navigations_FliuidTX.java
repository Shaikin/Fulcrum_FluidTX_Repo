package com.proj.navigations;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.report.reporter.Reporting;

public class Navigations_FliuidTX extends TestSuiteBase{


	static String res="";
	static String input="";	
	static String className=Navigations_USERSITE.class.getSimpleName();
	private static String locator_menu_pattern=".//*[@class='menu-item-text' and contains(text(),'objectlocator')]";
	private static String locator_submenu_pattern="//*[contains(@class,'ui popup inverted')]/descendant :: * [text()='objectlocator']";

	private static Xls_Reader xlsReader_objects_Navigation=new Xls_Reader(Constants.OR_Nav_Path);

	private static Hashtable<String,String>objects_step_Navigation=null;
	private static Hashtable<String,String>objects_locatorType_Navigation=null; 
	private static Hashtable<String,String>objects_objectType_Navigation=null;
	private static Hashtable<String,String>objects_objectLocator_Navigation=null;

	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Navigation Class");
		try {
			fetchObjectRepository.getObjects(Navigations_FliuidTX.class,xlsReader_objects_Navigation, "Objects_Navigation", "Navigation");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}
	public static void navigateToTramsmittals(WebDriver driver) throws Exception{
			commonMethods.switchToDefaultPage(driver);

			String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite Menu - Transmittals"));
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite Menu - Transmittals"), objects_locatorType_Navigation.get("Usersite Menu - Transmittals"), objects_objectType_Navigation.get("Usersite Menu - Transmittals"),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - Transmittals", "Please refer above details for more details");
			}
		}
	public static class Transmittals{
		public static void navigateToNewTransmittal(WebDriver driver) throws Exception{
			navigateToTramsmittals(driver);
			WaitUtil.pause(1);
			String Step="Usersite SubMenu - new Transmittal";
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", Step, objects_locatorType_Navigation, objects_objectType_Navigation,objects_objectLocator_Navigation , input);
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(Step, " Navigate Failure - New Transmittal", "Please refer above details for more details");
			}
			//ApplicationMethods_Falcrum.getApplicationFrameCount(driver);
		}
	}


}
