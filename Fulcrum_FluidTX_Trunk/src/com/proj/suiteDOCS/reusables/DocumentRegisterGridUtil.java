package com.proj.suiteDOCS.reusables;

import org.openqa.selenium.WebDriver;

import com.proj.objectRepository.ObjRepository;
import com.proj.suiteTRANSMITTALS.TestSuiteBase;
import com.proj.utilFulcrum.WebTableUtil;

public class DocumentRegisterGridUtil extends TestSuiteBase{


	static String res="";
	static String input="";
	static WebDriver dr; //Remove this after getting new logStep method


	/**
	 * Searches for the subject record in the grid and tick the record 
	 * @author Shaik
	 * @param driver
	 * @param workflow
	 * @param subject
	 * @throws Throwable 
	 */
	public static String searchDocumentAndTickRecord(WebDriver driver,String workflow,String docName) throws Throwable{
		return WebTableUtil.searchforDataInsearchColumnAndTickInactionableColumn(driver, testcaseName, workflow+" - Name", ObjRepository.container_documentRegister, docName, 4, 1);

	}
	public static String validateUploadedDocument(WebDriver driver,String workflow,String testcasename,String docName)throws Throwable {
		return WebTableUtil.searchforDataInsearchColumnAndTickInactionableColumn(driver, testcaseName, workflow+" - Name", ObjRepository.container_uploadedOrNewDocuments, docName, 3, 1);
		
	}
	
	public static String validateVersion(WebDriver driver,String workflow,String testcasename,String docName , String version) throws Throwable {
	
	
		return WebTableUtil.searchforDataInsearchColumnAndValidateDataInactionableColumn(driver,testcaseName,workflow+" - Major version",ObjRepository.container_uploadedOrNewDocuments,docName ,version,3,7);	
		
	}
		

}
