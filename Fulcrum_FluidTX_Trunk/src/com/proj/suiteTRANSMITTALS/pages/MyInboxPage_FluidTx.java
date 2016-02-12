package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.proj.Constants.Constants_Workflow;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;

public class MyInboxPage_FluidTx extends TestSuiteBase{
	private static final String page="My Inbox";

	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page
	 * @author shaikka
	 * @param driver
	 * @param workflow
	 * @param returnData
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>returnData,Hashtable<String,String>data) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		if(returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)){
			status="Closed";
			TxComplete_Status="Completed";
			subject=returnData.get("Tramsmittals-Subject");
		}
		else if(returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)|| returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)||returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)){
			status="Open";
			TxComplete_Status="Outstanding";
			subject=returnData.get("Tramsmittals-Subject");			
		}

		Navigations_FluidTX.Transmittals.navigateToMyinbox(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,page, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,page, workflow, subject, status);
		if(!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("Closed")){
			TransmittalsGridUtil.searchSubjectAndOpenRecord(driver,page, workflow, subject);
			Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		}
		return status;
	}


	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>data,String action) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)){
			status="Completed";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval))&& action.equals("Approved")){
			status="Approved";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval))&& action.equals("Rejected")){
			status="Rejected";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)) && (action.equals("Forward"))){
			status="Outstanding";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");
		}			
		Navigations_FluidTX.Transmittals.navigateToMyinbox(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,page, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,page, workflow, subject, status);

		return status;
	}
}
