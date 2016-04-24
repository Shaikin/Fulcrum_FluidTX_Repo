package com.proj.suiteDOCS;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.Constants.Constants_Workflow;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.suiteTRANSMITTALS.workflows.Workflows;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;

public class FLD_DocumentRegistry_New_Transmittals extends TestSuiteBase{

	static Hashtable<String,String>transmittalData=new Hashtable<String,String>();	
	private static String workflow_l1="Level-1:-Initiation of Transmittal From Document Register ";
	private static String workflow_l2="Level-2:-Recieve Transmittal with document attached from Document Register ";
	private static String workflow_end=" || ";

	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFLD_DocumentRegistry_New_Transmittals(Hashtable<String,String>data
			) throws Throwable{
		System.out.println("In test");
		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);

		try{
			if(isBeforeMethodPass_docs==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}
			String siteName=ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName);
			String condition="";
			condition=" ["+data.get("IssueReason")+"]";

			//************************************** LEVEL 1 *****************************************************************************
			String workflow_lvl1=workflow_l1+condition+workflow_end;		

			transmittalData=Workflows.Level1_Initaite_Transmittal_FromDocumentRegister(driver_DOCS, Constants_ConfigProperties.testSiteName, workflow_lvl1, data);
			//************************************** LEVEL 2 *****************************************************************************		
			for (int userIteration=1 ;userIteration<= Transmittals_EntryPage.getRecieverUserCount(data);userIteration++){
				String username = "";
				String password = "";

				//			String username=CONFIG.getProperty("userUserName"+String.valueOf(userIteration));
				//			String password=CONFIG.getProperty("userpassword"+String.valueOf(userIteration));

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


				driver_DOCS=Workflows.Level2_Validate_OR_Submit_OR_ApproveOrReject_OR_Forward_OR_ReplyAll_Transmittal(siteName,Constants_Workflow.page_myInbox,driver_DOCS,refID,testcaseName, workflow_l2, condition, workflow_end, Constants_ConfigProperties.testSiteName, browserName, username, password, transmittalData, data,userIteration);
			}
			logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);
		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_DOCS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);
	}

}
