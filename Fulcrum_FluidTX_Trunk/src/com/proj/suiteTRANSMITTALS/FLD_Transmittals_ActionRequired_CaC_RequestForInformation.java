package com.proj.suiteTRANSMITTALS;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.Constants.Constants_Workflow;
import com.proj.suiteTRANSMITTALS.workflows.Workflows;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;


public class FLD_Transmittals_ActionRequired_CaC_RequestForInformation extends TestSuiteBase{

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();
	private static String workflow_l1="Level-1:-Initiation of Transmittal";
	private static String workflow_l2="Level-2:- ";	
	private static String workflow_end=" || ";

	private static String username2;
	private static String password2;


	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFLD_Transmittals_ActionRequired_CaC_RequestForInformation(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");
		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);		

		try{
			if(data.get("RecieverRole").equalsIgnoreCase(Constants_Workflow.role_admin)){
				username2=Constants_ConfigProperties.username_AutoTestAdmin;
				password2=Constants_ConfigProperties.password_AutoTestAdmin;				
			}else{
				username2=Constants_ConfigProperties.username_AutoTestUser;
				password2=Constants_ConfigProperties.password_AutoTestUser;
			}

			if(isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			String siteName=ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName);
			String condition="";
			condition=" ["+data.get("IssueReason")+"-"+data.get("RecieverRole")+"-"+data.get("Action-Level2")+"]";

			//************************************** LEVEL 1 *****************************************************************************
			String workflow_lvl1=workflow_l1+condition+workflow_end;	

			transmittalData=Workflows.Level1_Initaite_Transmittal(driver_TRANS, Constants_ConfigProperties.testSiteName, workflow_lvl1, data);

			//************************************** LEVEL 2 *****************************************************************************		
			driver_TRANS=Workflows.Level2_Close_Cancel_Transmittal(siteName,Constants_Workflow.page_actionRequired,driver_TRANS,refID,testcaseName,workflow_l2,condition,workflow_end,Constants_ConfigProperties.testSiteName,browserName,username2, password2, transmittalData, data);
			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);
	}
}
