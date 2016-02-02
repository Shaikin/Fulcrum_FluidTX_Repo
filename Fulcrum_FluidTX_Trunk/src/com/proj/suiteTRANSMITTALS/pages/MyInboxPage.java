package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.proj.navigations.Navigations_USERSITE;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;

public class MyInboxPage extends TestSuiteBase{
	
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>returnData,Hashtable<String,String>data) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		if(returnData.get("Tramsmittals-TxType").equalsIgnoreCase("Correspondence")){
			status="Closed";
			TxComplete_Status="Completed";
			subject=returnData.get("Tramsmittals-Subject");
		}
		else if(returnData.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note")|| returnData.get("Tramsmittals-TxType").equalsIgnoreCase("Consultant Advice")){
			status="Open";
			TxComplete_Status="Outstanding";
			subject=returnData.get("Tramsmittals-Subject");			
		}
		
		Navigations_USERSITE.Transmittals.navigateToMyinbox(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver, workflow, subject, status);
		if(!status.equalsIgnoreCase("Completed") && !status.equalsIgnoreCase("Closed")){
			TransmittalsGridUtil.searchSubjectAndOpenRecord(driver, workflow, subject);
			Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
		}
		return status;
	}

	
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>data,String action) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Correspondence")||data.get("Tramsmittals-TxType").equalsIgnoreCase("Consultant Advice")){
			status="Closed";
			TxComplete_Status="Completed";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note"))&& action.equals("Approved")){
			status="Closed";
			TxComplete_Status="Approved";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note"))&& action.equals("Rejected")){
			status="Closed";
			TxComplete_Status="Rejected";
			subject=data.get("Tramsmittals-Subject");
		}
		else if((data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note"))&& action.equals("Forward")){
			status="Open";
			TxComplete_Status="Outstanding";
			subject=data.get("Tramsmittals-Subject");
		}
		Navigations_USERSITE.Transmittals.navigateToMyinbox(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver, workflow, subject, status);
		
		if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Correspondence") && !status.equalsIgnoreCase("Completed")){
			TransmittalsGridUtil.searchSubjectAndOpenRecord(driver, workflow, subject);
		}
		else if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note") && !status.equalsIgnoreCase("Closed")&& !action.equals("Forward")){
			TransmittalsGridUtil.searchSubjectAndOpenRecord(driver, workflow, subject);
		}
		return status;
	}
}
