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
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.pages.MyInboxPage;
import com.proj.suiteTRANSMITTALS.pages.MySentPage;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;


public class Transmittals_New_ChangeNote extends TestSuiteBase{

	private String local_testcaseName=Transmittals_New_ChangeNote.class.getSimpleName();	
	String input="";
	static String getResult="";

	private static boolean isBeforeTestPass=Constants_FRMWRK.TrueB;
	private static boolean isBeforeMethodPass=Constants_FRMWRK.TrueB;

	static Hashtable<String,String>returnData=new Hashtable<String,String>();
	private String local_refID="TRAN-03";
	/*private static String worflow_l1="Level-1:-Initiation of Transmittal";
	private static String worflow_l2="Level-2:-Recieve Transmittal and ";
	private static String worflow_l3="Level-3:-Recieve Transmittal and ";
	private static String worflow_end=" || ";*/
//	private static String url;
//	private static String username1;
//	private static String password1;


	@BeforeTest

	public void testPrerequisite() throws Throwable{
		refID=local_refID;
		logsObj.log("starting before test of "+local_testcaseName);
		isTestPass=Constants_FRMWRK.TrueB;
		testcaseName=TestExecutionUtil.getTestcaseName(local_testcaseName);		
		scenarioName=testcaseName;
		moduleName=Constants.Module_TRANS;

//		url=CONFIG.getProperty("testSiteName");
//		username1=CONFIG.getProperty("userUserName");
//		password1=CONFIG.getProperty("userpassword");
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
				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName, Constants_ConfigProperties.username_SuperUser, Constants_ConfigProperties.password_SuperUser, refID);
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
	public static void TestTransmittals_New_ChangeNote(Hashtable<String,String>data
			) throws Throwable{
		String worflow_l1="Level-1:-Initiation of Transmittal";
		String worflow_l2="Level-2:-Recieve Transmittal and ";
		String worflow_l3="Level-3:-Recieve Transmittal and ";
		String worflow_end=" || ";
		System.out.println("In test");

		String username2=Constants_ConfigProperties.username_AutoTestUser;
		String password2=Constants_ConfigProperties.password_AutoTestUser;

		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{
			if(isBeforeMethodPass==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			//************************************** LEVEL 1 *****************************************************************************
			worflow_l1=worflow_l1+" ["+data.get("TxType")+"]"+worflow_end;		
			Navigations_Fulcrum.Transmittals.navigateToNewTransmittal(driver_TRANS);
			//WaitUtil.pause(2);				

			returnData=Transmittals_EntryPage.createAndSendTransmittalRecord(ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName),driver_TRANS, refID,testcaseName,worflow_l1,  data);
			System.out.println("Done.."+returnData);

			if (returnData.size()==0 || returnData.get("Tramsmittals-Subject").equals(Constants_FRMWRK.False)){
				logsObj.log("Due to above error in the test case"+testcaseName+" hence quiting ,cannot continue further steps of the testcase");
				CustomExceptions.Exit(testcaseName, "Failure during New Transmittal entry for the test case"+testcaseName, "Please refer the above error details for more information");
			}

			getResult=MySentPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l1, returnData);

			if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
				CustomExceptions.Exit(testcaseName, worflow_l1+"- Failure", "Unable to continue the test due to above error ");
			}

			//************************************** LEVEL 2 *****************************************************************************		
			if(data.get("TxType").equalsIgnoreCase("Correspondence")){
				worflow_l2=worflow_l2+" validate"+worflow_end;
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_TRANS,refID,testcaseName);

				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName, username2, password2,refID);

				getResult=MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data);
				if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
					CustomExceptions.Exit(testcaseName, worflow_l2+"- Failure", "Unable to continue the test due to above error ");
				}

			}

			else if(data.get("TxType").equalsIgnoreCase("Change Note") && (data.get("Action-Level2").equals("Approved")|| data.get("Action-Level2").equals("Rejected"))){
				worflow_l2=worflow_l2+data.get("Action-Level2")+worflow_end;
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_TRANS,refID,testcaseName);

				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName, username2, password2,refID);

				MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data);

				//Transmittals_EntryPage.switchToTramsmittalEditFrame(driver_TRANS, refID, testcaseName, workFlow_ValidateNewTransmittal);
				Transmittals_EntryPage.clickCompleteAction(driver_TRANS, worflow_l2);

				Transmittals_EntryPage.editAndSubmitTransmittalRecord(ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName),driver_TRANS, refID, testcaseName, worflow_l2, returnData,data.get("Action-Level2"));
				getResult=MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data.get("Action-Level2"));
				if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
					CustomExceptions.Exit(testcaseName, worflow_l2+"- Failure", "Unable to continue the test due to above error ");
				}

			}
			else if((data.get("TxType").equalsIgnoreCase("Change Note")||data.get("TxType").equalsIgnoreCase("Consultant Advice") )&& (data.get("Action-Level2").equals("Forward"))){
				worflow_l2=worflow_l2+data.get("Action-Level2")+worflow_end;
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_TRANS,refID,testcaseName);

				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName, username2, password2,refID);

				MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data);

				Transmittals_EntryPage.forwardAndSendTransmittalRecord(ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName),driver_TRANS,testcaseName, worflow_l2, data);
				getResult=MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data.get("Action-Level2"));
				if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
					CustomExceptions.Exit(testcaseName, worflow_l2+"- Failure", "Unable to continue the test due to above error ");
				}
			}
			else if(data.get("TxType").equalsIgnoreCase("Consultant Advice")){
				worflow_l2=worflow_l2+" Submit"+worflow_end;
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_TRANS,refID,testcaseName);

				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName, username2, password2,refID);

				MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data);

				Transmittals_EntryPage.switchToTramsmittalEditFrame(driver_TRANS, refID, testcaseName, worflow_l2);
				Transmittals_EntryPage.clickCompleteAction(driver_TRANS, worflow_l2);
				//WaitUtil.pause(3);
				Transmittals_EntryPage.editAndSubmitTransmittalRecord(ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName),driver_TRANS, refID, testcaseName, worflow_l2, returnData,data.get("Action-Level2"));
				getResult=MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l2, returnData,data.get("Action-Level2"));
				if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
					CustomExceptions.Exit(testcaseName, worflow_l2+"- Failure", "Unable to continue the test due to above error ");
				}

			}
			//************************************** LEVEL 3 *****************************************************************************
			if(!data.get("Action-Level2").isEmpty()&& data.get("Action-Level2").equalsIgnoreCase("Forward")){
				worflow_l3=worflow_l3+" Submit for "+data.get("Action-Level3")+worflow_end;
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_TRANS,refID,testcaseName);
				driver_TRANS=ApplicationMethods.launchBrowserAndlogIntoApplication(browserName, Constants_ConfigProperties.testSiteName, Constants_ConfigProperties.username_SuperUser, Constants_ConfigProperties.password_SuperUser,refID);
				String subj=returnData.get("Tramsmittals-Subject");
				subj="FW: "+subj;
				returnData.put("Tramsmittals-Subject", subj);
				MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l3, returnData,data);

				Transmittals_EntryPage.switchToTramsmittalEditFrame(driver_TRANS, refID, testcaseName, worflow_l3);
				Transmittals_EntryPage.clickCompleteAction(driver_TRANS, worflow_l3);
				//WaitUtil.pause(3);
				Transmittals_EntryPage.editAndSubmitTransmittalRecord(ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName),driver_TRANS, refID, testcaseName, worflow_l3, returnData,data.get("Action-Level3"));
				getResult=MyInboxPage.validate_TxComplete_StatusAndStatus(driver_TRANS, worflow_l3, returnData,data.get("Action-Level3"));
				if(getResult.equalsIgnoreCase(Constants_FRMWRK.False)){
					CustomExceptions.Exit(testcaseName, worflow_l3+"- Failure", "Unable to continue the test due to above error ");
				}

			}
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
				WaitUtil.pause(2);
				//ApplicationMethods_Falcrum.logOutFromApplication(driver_TRANS);
				ApplicationMethods.logOutFromApplicationAndcloseBrowser(driver_TRANS,refID,testcaseName);

				logsObj.log(" after test of "+testcaseName+"-AfterTest successful");			}
		} catch (Throwable t) {
			logsObj.log("Not able to logout to the application due to error "+t+" hence cannot continue execution of "+testcaseName);

		}
	}


	@AfterTest
	public void Report_TestResult() throws Throwable{
		TestExecutionUtil.resultTest(isTestPass,suiteTRNSxls,scenarioName, testcaseName);
	}

}
