package com.proj.suiteTRANSMITTALS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.proj.navigations.Navigations_Fulcrum;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.reusables.TransmittalsGridUtil;

public class MySentPage extends TestSuiteBase{
	private static final String page="My Sent";
	/**
	 * Validates the TxComplete Status and Status of a record for a given transmittal in My Sent Page	
	 * @param driver
	 * @param workflow
	 * @param data
	 * @return
	 * @throws Exception
	 */
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
		Navigations_Fulcrum.Transmittals.navigateToMysent(driver);
		TransmittalsGridUtil.searchSubjectAndCheck_TxComplete_Status(driver,page, workflow, subject, TxComplete_Status);
		TransmittalsGridUtil.searchSubjectAndCheck_Status(driver,page, workflow, subject, status);
		return TxComplete_Status;
	}


}
