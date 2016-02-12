package com.proj.suiteDOCS;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;

public class Documents_New extends TestSuiteBase{

	private String local_testcaseName=Documents_New.class.getSimpleName();
	private String local_refID="DOCS-01";
	String input="";
	static String getResult="";

	private static boolean isBeforeTestPass=Constants_FRMWRK.TrueB;
	private static boolean isBeforeMethodPass=Constants_FRMWRK.TrueB;
	private static String worflow_upload="Uploading Document -";
	@BeforeTest

	public void testPrerequisite() throws Throwable{
		refID=local_refID;
		logsObj.log("starting before test of "+local_testcaseName);
		isTestPass=Constants_FRMWRK.TrueB;
		testcaseName=TestExecutionUtil.getTestcaseName(local_testcaseName);		
		scenarioName=testcaseName;
		moduleName=Constants.Module_DOCS;

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
				driver_DOCS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, CONFIG.getProperty("testSiteName"), CONFIG.getProperty("userUserName"), CONFIG.getProperty("userpassword"), refID);
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
	public static void TestDocuments_New(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");
		Navigations_Fulcrum.DocumentsAndFileStorage.navigateToBusinessCenter(driver_DOCS);
		//Documents_EntryPage.uploadADocumentIntoApplication(driver_DOCS, refID, testcaseName, worflow_upload, data);
	}


	@AfterMethod
	public static void aftMethod(){
		logsObj.log("Aft method "+testcaseName);
		try {

			if (!isBeforeMethodPass==Constants_FRMWRK.FalseB){
				
				//ApplicationMethods_Falcrum.logOutFromApplication(driver_TRANS);
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_DOCS);

				logsObj.log(" after test of "+testcaseName+"-AfterTest successful");			}
		} catch (Throwable t) {
			logsObj.log("Not able to logout to the application due to error "+t+" hence cannot continue execution of "+testcaseName);

		}
	}


	@AfterTest
	public void Report_TestResult() throws Throwable{
		TestExecutionUtil.resultTest(isTestPass,suiteDOCSxls, testcaseName);
	}

}
