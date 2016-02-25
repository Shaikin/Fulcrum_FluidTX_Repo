package com.proj.suiteTRANSMITTALS;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_Workflow;
import com.proj.library.LocalDriverManager;
import com.proj.suiteTRANSMITTALS.workflows.Workflows;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;


public class FLD_Transmittals_New_IssuedForReview extends TestSuiteBase{

	private String local_testcaseName=FLD_Transmittals_New_IssuedForReview.class.getSimpleName();	
	String input="";
	static String getResult="";

	private static boolean isBeforeTestPass=Constants_FRMWRK.TrueB;
	private static boolean isBeforeMethodPass=Constants_FRMWRK.TrueB;

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();
	private String local_refID="FLU-TRAN-02";
	private static String workflow_l1="Level-1:-Initiation of Transmittal";
	private static String workflow_l2="Level-2:-Recieve Transmittal and ";
	private static String workflow_end=" || ";
	private static String url;
	private static String username1;
	private static String password1;
	private static String username2;
	private static String password2;


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
		username2=CONFIG.getProperty("userUserName2");
		password2=CONFIG.getProperty("userpassword2");
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
	public static void TestFLD_Transmittals_New_IssuedForReview(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");



		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{
			
			if(isBeforeMethodPass==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			String siteName=ApplicationMethods.getSiteName(url);
			String condition="";
			condition=" ["+data.get("IssueReason")+"]";

			//************************************** LEVEL 1 *****************************************************************************
			workflow_l1=workflow_l1+condition+workflow_end;		
			
			transmittalData=Workflows.Level1_Initaite_Transmittal(driver_TRANS, url, workflow_l1, data);

			//************************************** LEVEL 2 *****************************************************************************		
			driver_TRANS=Workflows.Level2_Validate_OR_Submit_OR_ApproveOrReject_OR_Forward_OR_ReplyAll_Transmittal(siteName,Constants_Workflow.page_myInbox,driver_TRANS,refID,testcaseName, workflow_l2, condition, workflow_end, url, browserName, username2, password2, transmittalData, data);
			
			
			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);

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
				//WaitUtil.pause(2);
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
