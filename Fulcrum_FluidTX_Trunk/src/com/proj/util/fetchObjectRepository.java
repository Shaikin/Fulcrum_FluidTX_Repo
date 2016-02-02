package com.proj.util;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.TestUtil;
import com.frw.util.Xls_Reader;
import com.proj.base.TestBase;
import com.report.reporter.Reporting;

public class fetchObjectRepository extends TestBase{
	
	/**
	 * Fetches all objects from the sheet given
	 * @param className
	 * @param driver
	 * @param readerofExcelFile
	 * @param sheetNameTobeFetch
	 * @param patternofVariable
	 * @return
	 * True for Success , and for failure details of not fetched.
	 * @throws Throwable 
	 */
	public static String getObjects(Class<?> className,Xls_Reader readerofExcelFile,String sheetNameTobeFetch,String patternofVariable) throws Throwable{
		String flag="";
		try{
			flag=TestUtil.getAllObjectsFromExcelSheet(className, readerofExcelFile, sheetNameTobeFetch, patternofVariable);
			if(flag.equalsIgnoreCase(Constants_FRMWRK.True)){
				//Reporting.logStep(driver, "Object Repository -Objects Collection", "All objects are fetched from the sheet-"+sheetNameTobeFetch, Constants_FRMWRK.Pass);
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				Reporting.logStep("Object Repository -Objects Collection-Failure", "All objects are not fetched from the sheet-"+sheetNameTobeFetch+" and error details are "+flag, Constants_FRMWRK.Fail);
				CustomExceptions.Exit(testcaseName, "Object Repository -Objects Collection-Failure", flag);
			}
		}catch(Throwable t){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep("Object Repository -Objects Collection-Exception", "All objects are not fetched from the sheet-"+sheetNameTobeFetch+" and due to exception-->"+t, Constants_FRMWRK.Fail);
			CustomExceptions.Exit(testcaseName, "Object Repository -Objects Collection-Exception", "All objects are not fetched from the sheet-"+sheetNameTobeFetch+" and due to exception-->"+t);
		}
		return flag;
	}

}
