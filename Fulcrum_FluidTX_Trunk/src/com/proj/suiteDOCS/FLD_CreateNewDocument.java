package com.proj.suiteDOCS;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.library.LocalDriverManager;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.suiteDOCS.pages.Documents_EntryPage;
import com.proj.suiteDOCS.reusables.DocumentRegisterGridUtil;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;

public class FLD_CreateNewDocument extends TestSuiteBase{
	private static String workflow_UploadDocument="Uploading a file";
	private static String workflow_createOnlineDocument="New file creation";
	
		@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
		public static void TestFLD_CreateNewDocument(Hashtable<String,String>data
				) throws Throwable{
			System.out.println("In test");
			logsObj.log("******************************************************");		
			System.out.println(data);
			logsObj.log("Executing the test case: "+testcaseName);
			workflow_createOnlineDocument = workflow_createOnlineDocument+data.get("Document type");
			String workflow;
			 if(data.get("Document type").contains("Upload Document")){
				 workflow = workflow_UploadDocument;
				 }
			 else
			 {
				 workflow = workflow_createOnlineDocument;
			 }
			try{
				if(isBeforeMethodPass_docs==Constants_FRMWRK.FalseB){
					CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
				}
				String siteName=ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName);
				String condition="";
				
				Navigations_FluidTX.navigateToDocuments(LocalDriverManager.getDriver());
				
				String getName = Documents_EntryPage.uploadOrCreateOnlineDocument(LocalDriverManager.getDriver(), condition, testcaseName, workflow, data);
				
				
				condition=" ["+data.get("IssueReason")+"]";
								
				DocumentRegisterGridUtil.validateUploadedDocument(LocalDriverManager.getDriver(), workflow, testcaseName, getName);
				Documents_EntryPage.publishMajorVersion(LocalDriverManager.getDriver(), refID, testcaseName, workflow, data,getName,data.get("Major version"));
				
				logsObj.log(" after test of "+testcaseName+"-testresult"+isTestPass);
				
			}catch(Throwable t){
				CustomExceptions.final_catch_Reporting(driver_DOCS,refID,t);			
			}

			TestExecutionUtil.AssertTestStatus(isTestPass);
			
		}
		
	}

