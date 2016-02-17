package com.proj.suiteDOCS;

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

public class FLD_DocumentRegistry_New_Transmittals extends TestSuiteBase{

	private String local_testcaseName=FLD_DocumentRegistry_New_Transmittals.class.getSimpleName();
	private String local_refID="LATFLD-13";
	String input="";
	static String getResult="";
	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();	
	private static String workflow_l1="Level-1:-Initiation of Transmittal From Document Register ";
	private static String workflow_l2="Level-2:-Recieve Transmittal with document attached from Document Register ";
	private static String workflow_end=" || ";
	private static String url;
	private static String username1;
	private static String password1;
	private static String username2;
	private static String password2;

	private static boolean isBeforeTestPass=Constants_FRMWRK.TrueB;
	private static boolean isBeforeMethodPass=Constants_FRMWRK.TrueB;
	


	@BeforeTest

	public void testPrerequisite() throws Throwable{
		refID=local_refID;
		logsObj.log("starting before test of "+local_testcaseName);
		isTestPass=Constants_FRMWRK.TrueB;
		testcaseName=TestExecutionUtil.getTestcaseName(local_testcaseName);		
		scenarioName=testcaseName;
		moduleName=Constants.Module_DOCS;
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
				driver_DOCS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, url, username1, password1, refID);
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
	public static void TestFLD_DocumentRegistry_New_Transmittals(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");
		if(isBeforeMethodPass==Constants_FRMWRK.FalseB){
			CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
		}
		String siteName=ApplicationMethods.getSiteName(url);
		String condition="";
		condition=" ["+data.get("IssueReason")+"]";
			
		//************************************** LEVEL 1 *****************************************************************************
		workflow_l1=workflow_l1+condition+workflow_end;		
		
		transmittalData=Workflows.Level1_Initaite_Transmittal_FromDocumentRegister(driver_DOCS, url, workflow_l1, data);
		//************************************** LEVEL 2 *****************************************************************************		
		driver_DOCS=Workflows.Level2_Validate_OR_Submit_OR_ApproveOrReject_OR_Forward_Transmittal(siteName,Constants_Workflow.page_actionRequired,driver_DOCS, workflow_l2, condition, workflow_end, url, browserName, username2, password2, transmittalData, data);
		
		
		logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);
	}



	@AfterMethod
	public static void aftMethod(){
		logsObj.log("Aft method "+testcaseName);
		try {
			if (!isBeforeMethodPass==Constants_FRMWRK.FalseB){
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(LocalDriverManager.getDriver());
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
