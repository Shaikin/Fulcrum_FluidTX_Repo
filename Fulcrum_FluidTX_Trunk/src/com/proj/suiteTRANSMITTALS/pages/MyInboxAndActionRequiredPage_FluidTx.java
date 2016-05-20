package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_Workflow;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;
import com.report.reporter.Reporting;

public class MyInboxAndActionRequiredPage_FluidTx extends TestSuiteBase{



	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Inbox Page(Before Submission)
	 * @author shaikka
	 * @param driver
	 * @param workflow
	 * @param returnData
	 * @param data
	 * @return
	 * @throws Throwable 
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>returnData,Hashtable<String,String>data) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		if(returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)){
			status="Completed";
			TxComplete_Status="Closed";
			subject=returnData.get("Tramsmittals-Subject");
		}
		else if(!returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation) && (returnData.get("Action-Level2").equalsIgnoreCase("Overdue"))){
			status="Overdue";
			TxComplete_Status="Open";
			subject=returnData.get("Tramsmittals-Subject");
		}
		else if(returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)|| returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)||returnData.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)){
			//
			if(data.get("To").contains(Constants.delimiter_data)&& !returnData.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)&& data.get("Action-Level2").equalsIgnoreCase("Rejected")){
				status="Rejected";
				TxComplete_Status="Closed";
				subject=returnData.get("Tramsmittals-Subject");
			}else{
				status="Outstanding";
				TxComplete_Status="Open";
				subject=returnData.get("Tramsmittals-Subject");	
			}

		}
		if(validationPage.equalsIgnoreCase(Constants_Workflow.page_myInbox)){
			Navigations_FluidTX.Transmittals.navigateToMyinbox(driver);
		}
		else if(validationPage.equalsIgnoreCase(Constants_Workflow.page_actionRequired))
		{
			Navigations_FluidTX.Transmittals.navigateToActionRequired(driver);
		}else {
			Navigations_FluidTX.Transmittals.navigateToActionsOverdue(driver);
		}

		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,validationPage, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,validationPage, workflow, subject, status);
		if(!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("Closed")&& !status.equalsIgnoreCase("Rejected")){
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
	 * @throws Throwable 
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>data,String action) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;


		if((data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval))&& action.equals("Approved")){
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
		
			else if((!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)) && (action.equals("Forward")||(action.equals("ReplyAll")||(action.equals("Delegate"))))){
			status="Outstanding";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");

		}	
		else if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)){
			//if(data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)){
			if(!data.get("Tramsmittals-To").contains(Constants.delimiter_data)){
				status="Completed";
				TxComplete_Status="Closed";
				subject=data.get("Tramsmittals-Subject");
			}			
			else if(data.get("Tramsmittals-To").contains(Constants.delimiter_data)&& data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(Constants_Workflow.level2_reciever_first_count)){
				status="Outstanding";
				TxComplete_Status="Open";
				subject=data.get("Tramsmittals-Subject");
			}
			else if(data.get("Tramsmittals-To").contains(Constants.delimiter_data)&& data.get("Tramsmittals-Level2-Reciever").equalsIgnoreCase(data.get("Tramsmittals-ToCount"))){
				status="Completed";
				TxComplete_Status="Closed";
				subject=data.get("Tramsmittals-Subject");
			}
			else{
				status="";
				TxComplete_Status="";
				subject=data.get("Tramsmittals-Subject");

			}

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
	 * @throws Throwable 
	 */
	public static String validate_CaC_TxComplete_StatusAndStatus(WebDriver driver,String validationPage,String workflow,Hashtable<String,String>data,String action) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;

		//if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)&& action.equalsIgnoreCase("CANCEL")){
		if(!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)&& action.equalsIgnoreCase("CANCEL")){
			status="Cancelling from Initiator";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");
		}
		//else if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)&& action.equalsIgnoreCase("CLOSE")){
		else if(!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)&& action.equalsIgnoreCase("CLOSE")){
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
	
	public static void validate_TransmittalID(WebDriver driver,String page,String workflow,Hashtable<String,String> data) throws Throwable{
		String subject=data.get("Tramsmittals-Subject");
		String res=TransmittalsGridUtil.searchSubjectAndGetTransmittalID(driver, page, workflow, subject);
		if(!res.equalsIgnoreCase(Constants_FRMWRK.Error)||!res.equalsIgnoreCase(Constants_FRMWRK.False)){
			 if(!res.equalsIgnoreCase("")){
				 Reporting.logStep(driver, workflow+" "+page+" - Transmittal ID", "Transmittal ID :-"+res+" is displayed for the record "+subject, Constants_FRMWRK.Pass);
			 }else{
				 Reporting.logStep(driver, workflow+" "+page+" - Transmittal ID", "Transmittal ID :-"+res+" is not displayed for the record "+subject, Constants_FRMWRK.Fail);
			 }
		 }
	}
}
