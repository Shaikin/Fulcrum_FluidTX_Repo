package com.proj.navigations;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.KeyMethods;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.proj.util.CustomExceptions;
import com.proj.util.fetchObjectRepository;
import com.report.reporter.Reporting;

public class Navigations_Fulcrum extends TestBase{
	static String res="";
	static String input="";	
	static String className=Navigations_Fulcrum.class.getSimpleName();
	private static String locator_menu_pattern=".//*[@class='expanded-item' and contains(text(),'objectlocator')]";
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
			fetchObjectRepository.getObjects(Navigations_Fulcrum.class,xlsReader_objects_Navigation, "Objects_Navigation", "Navigation");
		} catch (Throwable e) {
			e.printStackTrace();
			Reporting.logStep("Excel Object Initialization - "+className, "Required Objects for "+className+" are not  initialized due to error-"+e.getStackTrace(), Constants_FRMWRK.Fail);

		}
	}

	public static String checkSubMenusDisplayed(WebDriver driver){
		String flag=Constants_FRMWRK.False;

		WebElement element=ExplicitWaitUtil.waitForElement(driver,Constants_FRMWRK.FindElementByXPATH, ObjRepository.container_subMenu, Constants_TimeOuts.generic_TimeOut) ;
		if(element!=null){
			if(element.isDisplayed()==true){
				WaitUtil.pause(1);
				flag=Constants_FRMWRK.True;
			}			
		}
		return flag;
	}
	public static void navigateToTramsmittals(WebDriver driver) throws Exception{
		commonMethods.switchToDefaultPage(driver);
		if(checkSubMenusDisplayed(driver).equalsIgnoreCase(Constants_FRMWRK.False)){
			String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite Menu - Transmittals"));
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite Menu - Transmittals"), objects_locatorType_Navigation.get("Usersite Menu - Transmittals"), objects_objectType_Navigation.get("Usersite Menu - Transmittals"),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - Transmittals", "Please refer above details for more details");
			}
		}

	}
	
	public static void navigateToDocumentsAndFileStorage(WebDriver driver) throws Exception{
		commonMethods.switchToDefaultPage(driver);
		if(checkSubMenusDisplayed(driver).equalsIgnoreCase(Constants_FRMWRK.False)){
			String locator=locator_menu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite Menu - Document & File Storage"));
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite Menu - Document & File Storage"), objects_locatorType_Navigation.get("Usersite Menu - Document & File Storage"), objects_objectType_Navigation.get("Usersite Menu - Document & File Storage"),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - Usersite Menu - Document & File Storage", "Please refer above details for more details");
			}
		}

	}
	
	
	
	public static class Transmittals{
		public static void navigateToNewTransmittal(WebDriver driver) throws Exception{
			navigateToTramsmittals(driver);
			WaitUtil.pause(1);
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get("Usersite SubMenu - New Transmittal"));
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get("Usersite SubMenu - New Transmittal"), objects_locatorType_Navigation.get("Usersite SubMenu - New Transmittal"), objects_objectType_Navigation.get("Usersite SubMenu - New Transmittal"),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - New Transmittal", "Please refer above details for more details");
			}
			//ApplicationMethods_Falcrum.getApplicationFrameCount(driver);
		}

		public static void navigateToMysent(WebDriver driver) throws Exception{
			navigateToTramsmittals(driver);
			String key_step="Usersite SubMenu - My Sent";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));
			
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - My Sent", "Please refer above details for more details");
			}
		}

		public static void navigateToMyinbox(WebDriver driver) throws Exception{
			navigateToTramsmittals(driver);
			String key_step="Usersite SubMenu - My Inbox";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));
			
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - My Inbox", "Please refer above details for more details");
			}
		}

	}
	
	public static class DocumentsAndFileStorage{
		public static void navigateToBusinessCenter(WebDriver driver) throws Exception{
			navigateToDocumentsAndFileStorage(driver);
			String key_step="Usersite SubMenu - Business Case";
			String locator=locator_submenu_pattern.replaceAll("objectlocator", objects_objectLocator_Navigation.get(key_step));
			
			res=KeyMethods.f_performAction(driver, refID, testcaseName, "", objects_step_Navigation.get(key_step), objects_locatorType_Navigation.get(key_step), objects_objectType_Navigation.get(key_step),locator , "");
			if(res.equals(Constants_FRMWRK.False)){
				CustomExceptions.Exit(locator, " Navigate Failure - Business Center", "Please refer above details for more details");
			}
		}
	}

}


