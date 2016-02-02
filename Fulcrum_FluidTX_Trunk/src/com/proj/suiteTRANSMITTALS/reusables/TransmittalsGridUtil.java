package com.proj.suiteTRANSMITTALS.reusables;

import org.openqa.selenium.WebDriver;

import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.suiteTRANSMITTALS.pages.Transmittals_EntryPage;
import com.proj.utilFulcrum.WebTableUtil;

public class TransmittalsGridUtil extends TestSuiteBase{
	
	
	static String res="";
	static String input="";
	static WebDriver dr; //Remove this after getting new logStep method
	
	
	/**
	 * Searches for the subject record in the grid and tick the record 
	 * @author Shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @throws Exception
	 */
	public static void searchSubjectAndTickRecord(WebDriver driver,String workflow,String subject) throws Exception{
		WebTableUtil.searchforDataInsearchColumnAndTickInactionableColumn(driver, testcaseName, workflow+" My Sent - Subject", ObjRepository.container_transmittals, subject, 4, 1);

	}
	public static void searchSubjectAndOpenRecord(WebDriver driver,String workflow,String subject) throws Exception{
		WebTableUtil.searchforDataInsearchColumnAndClickInactionableLinkColumn(driver, testcaseName, workflow+" My Sent - Subject", ObjRepository.container_transmittals, subject,subject, 4, 4);
		Transmittals_EntryPage.waitInvisiblilityofWorkingTitle(driver);
	}
	/**
	 * Searches for the subject record in the grid and validates the  TxComplete_Status of the record 
	 * @author shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @param TxComplete_Status
	 * @throws Exception
	 */
	public static void searchSubjectAndCheck_TxComplete_Status(WebDriver driver,String workflow,String subject,String TxComplete_Status) throws Exception{
		WebTableUtil.searchforDataInsearchColumnAndValidateDataInactionableColumn(driver, testcaseName, workflow+" My Sent - TxComplete_Status", ObjRepository.container_transmittals, subject, TxComplete_Status, 4, 12);
	}
	/**
	 * Searches for the subject record in the grid and validates the status of the record 
	 * @author shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @param status
	 * @throws Exception
	 */
	public static void searchSubjectAndCheck_Status(WebDriver driver,String workflow,String subject,String status) throws Exception{
		WebTableUtil.searchforDataInsearchColumnAndValidateDataInactionableColumn(driver, testcaseName, workflow+" My Sent - Status", ObjRepository.container_transmittals, subject, status, 4, 13);
	}
	

}
