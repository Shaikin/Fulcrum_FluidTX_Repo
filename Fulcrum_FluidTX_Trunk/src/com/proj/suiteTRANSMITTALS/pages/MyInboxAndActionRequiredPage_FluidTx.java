package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.proj.Constants.Constants_Workflow;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;

public class MyInboxAndActionRequiredPage_FluidTx extends TestSuiteBase{



	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(Before Submission)
	 * @author shaikka
	 * @param driver
	 * @param workflow
	 * @param returnData
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>returnData,Hashtable<String,String>data) throws Exception{
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
		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_FluidTX.Transmittals.navigateToMyinbox(driver);
		}else{
			Navigations_FluidTX.Transmittals.navigateToActionRequired(driver);
		}

		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);
		if(!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("Closed")){
			TransmittalsGridUtil.searchSubjectAndOpenRecord(driver,validationPage, workflow, subject);
			Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		}
		return status;
	}

	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(After Submission)
	 * @author shaik
	 * @param driver
	 * @param validationPage
	 * @param workflow
	 * @param data
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>data,String action) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)){
			if(!data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(data.get("Tramsmittals-ToCount"))){
				status="Outstanding";
				TxComplete_Status="Open";
				subject=data.get("Tramsmittals-Subject");
			}else{
				status="Completed";
				TxComplete_Status="Closed";
				subject=data.get("Tramsmittals-Subject");

			}

		}
		else if((data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval))&& action.equals("Approved")){
			if(!data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(data.get("Tramsmittals-ToCount"))){
				status="Outstanding";
				TxComplete_Status="Open";
				subject=data.get("Tramsmittals-Subject");
			}else{
				status="Approved";
				TxComplete_Status="Closed";
				subject=data.get("Tramsmittals-Subject");
			}
		}
		else if((data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval))&& action.equals("Rejected")){
			status="Rejected";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)) && (action.equals("Forward")||(action.equals("ReplyAll")))){
			status="Outstanding";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");
		}			
		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_FluidTX.Transmittals.navigateToMyinbox(driver);
		}else{
			Navigations_FluidTX.Transmittals.navigateToActionRequired(driver);
		}
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);

		return status;
	}
	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(After Cancel/Close Transmittal)
	 * @author shaik
	 * @param driver
	 * @param validationPage
	 * @param workflow
	 * @param data
	 * @param action
	 * @return
	 * @throws Exception
	 */
	public static String validate_CaC_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>data,String action) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)&& action.equalsIgnoreCase("CANCEL")){
			status="Cancelling from Initiator";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");
		}
		else if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)&& action.equalsIgnoreCase("CLOSE")){
			status="Completed";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");
		}

		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_FluidTX.Transmittals.navigateToMyinbox(driver);
		}else{
			Navigations_FluidTX.Transmittals.navigateToActionRequired(driver);
		}
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);

		return status;
	}
}
