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

public class Navigations_FluidTX extends TestSuiteBase{


	static String res="";
	static String input="";	
	static String className=Navigations_FluidTX.class.getSimpleName();
	private static String locator_menu_pattern=".//*[@class='menu-item-text' and contains(text(),'objectlocator')]";
	private static String locator_submenu_pattern=".//*[contains(@id,'SelectorMenu_Container')]/ul/li/a[text()='objectlocator']";

	private static Xls_Reader xlsReader_objects_Navigation=new Xls_Reader(Constants.OR_Nav_Path);

	private static Hashtable<String,String>objects_step_Navigation=null;
	private static Hashtable<String,String>objects_locatorType_Navigation=null; 
	private static Hashtable<String,String>objects_objectType_Navigation=null;
	private static Hashtable<String,String>objects_objectLocator_Navigation=null;

	static {		
		System.out.println("Static ...............Initializeeeeeeeeeee");
		logsObj.log("fetchExcelobjects method triggred for Navigation Class");
		try {
			fetchObjectRepository.getObjects(Navigations_FluidTX.class,xlsReader_objects_Navigation, "Objects_Navigation", "Navigation");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}
	/**
	 * Navigates to Transmittals Menu
	 * @author shaikka
	 * @param driver
	 * @throws Throwable 
	 */
	public static void navigateToTramsmittals(WebDriver driver) throws Throwable{
		commonMethods.switchToDefaultPage(driver);
		commonMethods.pageLoadWait(driver);
		String key_step="Usersite Menu - Transmittals";
		String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+key_step, "Please refer above details for more details");
		}
	}

	public static void navigateToDocumentRegister(WebDriver driver) throws Throwable{
		String Step="Usersite Menu - Document Register";
		commonMethods.switchToDefaultPage(driver);
		commonMethods.pageLoadWait(driver);
		String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(Step));
		res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(Step), objects_locatorType_Navigation.get(Step), objects_objectType_Navigation.get(Step),locator , input);
		if(res.equals(Constants_FRMWRK.False)){
			CustomExceptions.Exit(locator, " Navigate Failure - "+Step, "Please refer above details for more details");
		}
	}

	public static class Transmittals{
		/**
		 * Navigates to New Transmittals sub menu
		 * @author shaikka
		 * @param driver
		 * @throws Throwable 
		 */
		public static void navigateToNewTransmittal(WebDriver driver) throws Throwable{
			navigateToTramsmittals(driver);
			WaitUtil.pause(1);
			String Step="Usersite SubMenu - new Transmittal";
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", Step, objects_locatorType_Navigation, objects_objectType_Navigation,objects_objectLocator_Navigation , input);
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(Step, " Navigate Failure - "+Step, "Please refer above details for more details");
			}
			//ApplicationMethods_Falcrum.getApplicationFrameCount(driver);
		}

		/**
		 * Navigates to My Sent Sub menu
		 * @param driver
		 * @throws Throwable 
		 */
		public static void navigateToMysent(WebDriver driver) throws Throwable{
			navigateToTramsmittals(driver);
			String key_step="Usersite SubMenu - My Sent";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - "+key_step, "Please refer above details for more details");
			}
		}
		/**
		 * Navigates to My Inbox
		 * @param driver
		 * @throws Throwable 
		 */
		public static void navigateToMyinbox(WebDriver driver) throws Throwable{
			navigateToTramsmittals(driver);
			String key_step="Usersite SubMenu - My Inbox";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - "+key_step, "Please refer above details for more details");
			}
		}

		/**
		 * Navigates to My Inbox
		 * @param driver
		 * @throws Throwable 
		 */
		public static void navigateToActionRequired(WebDriver driver) throws Throwable{
			navigateToTramsmittals(driver);
			String key_step="Usersite Menu - Action Required";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - "+key_step, "Please refer above details for more details");
			}
		}

		/**
		 * Navigates to Actions Overdue
		 * @param driver
		 * @throws Throwable 
		 */

		public static void navigateToActionsOverdue(WebDriver driver) throws Throwable{
			navigateToTramsmittals(driver);
			String key_step="Usersite Menu - Actions Overdue";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));

			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , input);
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - "+key_step, "Please refer above details for more details");
			}
		}


	}
	public static void verify_menu(WebDriver driver,String ApplicationName, Hashtable<String,String>data){

		int j = Integer.valueOf(data.get(ApplicationName+"_MenuCount"));		
		for(int i=1;i<=j; i++){
			String MenuName = ApplicationName+"_Menu"+String.valueOf(i);				
			commonMethods.switchToDefaultPage(driver);				
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "","Fluid Menu List",objects_locatorType_Navigation ,objects_objectType_Navigation,objects_objectLocator_Navigation, data.get(MenuName));
		}		
	}
}




