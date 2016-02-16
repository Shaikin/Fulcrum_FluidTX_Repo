package com.proj.suiteDOCS.pages;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.proj.navigations.Navigations_FluidTX;
import com.proj.suiteDOCS.TestSuiteBase;
import com.proj.suiteDOCS.reusables.DocumentRegisterGridUtil;
import com.proj.utilFulcrum.ToolbarsUtil;

public class DocumentRegistryPage extends TestSuiteBase{
	
	public static void selectADocument(WebDriver driver,String workflow,Hashtable<String,String>data) throws Exception{
		Navigations_FluidTX.navigateToDocumentRegister(driver_DOCS);
		DocumentRegisterGridUtil.searchDocumentAndTickRecord(driver_DOCS, workflow, data.get("AttachDocumentName"));		
	}

	
	public static void sendTransmittal(WebDriver driver,String workFlow){
		ToolbarsUtil.clickItems(driver, workFlow);
		ToolbarsUtil.Items.clickSendTranmittals(driver, workFlow);
	}
}
