package com.proj.suiteTRANSMITTALS;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.Constants.Constants_Workflow;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.suiteTRANSMITTALS.workflows.Workflows;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;


public class FLD_Transmittals_New_IssuedForReview extends TestSuiteBase{	

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();

	private static String workflow_l1="Level-1:-Initiation of Transmittal";
	private static String workflow_l2="Level-2:-Recieve Transmittal and ";
	private static String workflow_l3="Level-3:-Recieve Transmittal and ";
	private static String workflow_end=" || ";

	private static String username2;
	private static String password2;


	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFLD_Transmittals_New_IssuedForReview(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");



		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{

			if(isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			String siteName=ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName);
			String condition="";
			if(data.get("To").contains(Constants.delimiter_data)){
				condition=" ["+data.get("IssueReason")+"-"+data.get("Action-Level2")+"-[Multi User]]";				
			}else{
				condition=" ["+data.get("IssueReason")+"-"+data.get("Action-Level2")+"]";
			}
			//************************************** LEVEL 1 *****************************************************************************
			String workflow_lvl1=workflow_l1+condition+workflow_end;		

			transmittalData=Workflows.Level1_Initaite_Transmittal(driver_TRANS, Constants_ConfigProperties.testSiteName, workflow_lvl1, data);

			//************************************** LEVEL 2 *****************************************************************************		
			for (int userIteration=1 ;userIteration<= Transmittals_EntryPage.getRecieverUserCount(data);userIteration++){
				System.out.println("user count" +Transmittals_EntryPage.getRecieverUserCount(data));

				String username = "";
				String password = "";

				if (userIteration ==1)
				{
					username = Constants_ConfigProperties.username_AutoTestAdmin;
					password = Constants_ConfigProperties.password_AutoTestAdmin;

				}
				else if (userIteration ==2)
				{
					username = Constants_ConfigProperties.username_AutoTestUser;
					password = Constants_ConfigProperties.password_AutoTestUser;

				}

				driver_TRANS=Workflows.Level2_Validate_OR_Submit_OR_ApproveOrReject_OR_Forward_OR_ReplyAll_Transmittal(siteName,Constants_Workflow.page_myInbox,driver_TRANS,refID,testcaseName, workflow_l2, condition, workflow_end, Constants_ConfigProperties.testSiteName, browserName, username, password, transmittalData, data,userIteration);
			}

			//************************************** LEVEL 3 *****************************************************************************
			if (data.get("Action-Level2").equalsIgnoreCase("Forward")&& data.get("To").contains(Constants.delimiter_data)|| data.get("Action-Level2").equalsIgnoreCase("ReplyAll")){
				username2=Constants_ConfigProperties.username_SuperUser;
				password2=Constants_ConfigProperties.password_SuperUser;
			}

			else{
				username2=Constants_ConfigProperties.username_AutoTestUser;
				password2=Constants_ConfigProperties.password_AutoTestUser;

			}
			driver_TRANS=Workflows.Level3_ValidateForwarded_OR_ValidateReplyAll_And_ApproveOrReject_Transmittal(siteName,Constants_Workflow.page_myInbox,driver_TRANS,refID,testcaseName, workflow_l3, condition, workflow_end, Constants_ConfigProperties.testSiteName, browserName, username2, password2, transmittalData, data);

			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);
	}

}
