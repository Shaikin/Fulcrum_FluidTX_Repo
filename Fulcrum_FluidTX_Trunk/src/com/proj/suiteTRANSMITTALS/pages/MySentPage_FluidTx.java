package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.Constants.Constants_Workflow;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;

public class MySentPage_FluidTx extends TestSuiteBase{
	private static final String page="My Sent";
	
	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Sent Page
	 * @author shaikka
	 * @param driver
	 * @param workflow
	 * @param data
	 * @return
	 * @throws Throwable 
	 */
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>data) throws Throwable{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		
		if(data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation)){
			status="Completed";
			TxComplete_Status="Closed";
			subject=data.get("Tramsmittals-Subject");			
		}
		else if((!data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForInformation))&& data.get("Action-Level2").equalsIgnoreCase("Overdue")){
			status="Overdue";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");
		}
		else if (data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForReview)||data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_RequestForInformation)|| data.get(Constants_Workflow.FluidTX_WorkFlow_Condition).equalsIgnoreCase(Constants_Workflow.FluidTX_WorkFlow_IssuedForApproval)){
			status="Outstanding";
			TxComplete_Status="Open";
			subject=data.get("Tramsmittals-Subject");			
		}
		WaitUtil.pause(Constants_TimeOuts.processToComplete);
		Navigations_FluidTX.Transmittals.navigateToMysent(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,page, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver, workflow,page, subject, status);
		return TxComplete_Status;
	}

	
}
