package com.proj.suiteTRANSMITTALS;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants;
import com.proj.library.LocalDriverManager;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;

public class FLD_Transmittals_LeftNavigationBar extends TestSuiteBase{
	private String local_testcaseName=FLD_Transmittals_LeftNavigationBar.class.getSimpleName();	
	String input="";
	static String getResult="";

	private static boolean isBeforeTestPass=Constants_FRMWRK.TrueB;
	private static boolean isBeforeMethodPass=Constants_FRMWRK.TrueB;
	private String local_refID="TRAN-00";
	private static String url;
	private static String username1;
	private static String password1;

	@BeforeTest

	public void testPrerequisite() throws Throwable{
		refID=local_refID;
		logsObj.log("starting before test of "+local_testcaseName);
		isTestPass=Constants_FRMWRK.TrueB;
		testcaseName=TestExecutionUtil.getTestcaseName(local_testcaseName);		
		scenarioName=testcaseName;
		moduleName=Constants.Module_TRANS;

		url=CONFIG.getProperty("testSiteName");
		username1=CONFIG.getProperty("userUserName");
		password1=CONFIG.getProperty("userpassword");
		try{

			TestExecutionUtil.initialiseTestFlags(testcaseName);

		}catch (Throwable t){
			isBeforeTestPass=Constants_FRMWRK.FalseB;
		}
		logsObj.log(" after test of "+testcaseName);
	}

	@BeforeMethod
	public static void befMethod() throws Throwable{
		try {

			if(!isBeforeTestPass ==Constants_FRMWRK.FalseB){				
				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, url, username1, password1, refID);
				logsObj.log("Before method success for "+testcaseName);
			}else{
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Test cannot execute the test..");
			}	

		} catch (Throwable t) {
			isTestPass=Constants_FRMWRK.FalseB;
			logsObj.log("Not able to login into the application due to error "+t+" hence cannot continue execution of "+testcaseName);
			try{
				CustomExceptions.Exit(testcaseName, "Log into the application", "Not able to login into the application due to error "+t+" hence cannot continue execution of "+testcaseName);
			} catch (Exception e) {
				isTestPass=Constants_FRMWRK.FalseB;
				isBeforeMethodPass=Constants_FRMWRK.FalseB;
			}
		}
	}
	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFLD_Transmittals_LeftNavigationBar(Hashtable<String,String>data
			) throws Throwable{

		System.out.println("In test");
		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);
		try{
			String appName=ApplicationMethods.getSiteName(url);
			if(isBeforeMethodPass==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}

			logsObj.log("******************************************************");		
			logsObj.log("Executing the test case: "+testcaseName);
			Navigations_FluidTX.verify_menu(driver_TRANS, appName, data);
			WaitUtil.pause(2);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);

	}


	@AfterMethod
	public static void aftMethod(){
		logsObj.log("Aft method "+testcaseName);
		try {

			if (!isBeforeMethodPass==Constants_FRMWRK.FalseB){
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(LocalDriverManager.getDriver(),refID,testcaseName);
				logsObj.log(" after test of "+testcaseName+"-AfterTest successful");			}
		} catch (Throwable t) {
			logsObj.log("Not able to logout to the application due to error "+t+" hence cannot continue execution of "+testcaseName);

		}
	}


	@AfterTest
	public void Report_TestResult() throws Throwable{
		TestExecutionUtil.resultTest(isTestPass,suiteTRNSxls, testcaseName);
	}
}