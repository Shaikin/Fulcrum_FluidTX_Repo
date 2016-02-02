package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.proj.navigations.Navigations_USERSITE;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;

public class MySentPage extends TestSuiteBase{
	
	
	/*
	public static void validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,String subject,String TxComplete_Status,String status) throws Exception{
		Navigations_USERSITE.navigateToMysent(driver);
		searchSubjectAndCheck_TxComplete_Status(driver, workflow, subject, TxComplete_Status);
		searchSubjectAndCheck_Status(driver, workflow, subject, status);
	}*/
	
	public static String validate_TxComplete_StatusAndStatus(WebDriver driver,String workflow,Hashtable<String,String>data) throws Exception{
		String subject = null;
		String status = null;
		String TxComplete_Status = null;
		if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Correspondence")){
			status="Closed";
			TxComplete_Status="Completed";
			subject=data.get("Tramsmittals-Subject");
		}
		else if(data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note")){
			status="Sending…";
			TxComplete_Status="Outstanding";
			subject=data.get("Tramsmittals-Subject");
		}
		else if (data.get("Tramsmittals-TxType").equalsIgnoreCase("Consultant Advice")){
			status="Open";
			TxComplete_Status="Outstanding";
			subject=data.get("Tramsmittals-Subject");
		}
		
		else if((data.get("Tramsmittals-TxType").equalsIgnoreCase("Change Note")||data.get("Tramsmittals-TxType").equalsIgnoreCase("Consultant Advice"))&& data.get("Action-Level2").equalsIgnoreCase("Forward")){
			status="Open";
			TxComplete_Status="Outstanding";
			subject="FW:"+data.get("Tramsmittals-Subject");
		}
		Navigations_USERSITE.Transmittals.navigateToMysent(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver, workflow, subject, status);
		return TxComplete_Status;
	}

	
}
