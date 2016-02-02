package com.proj.util;


import java.lang.reflect.Method;

import org.testng.SkipException;
import org.testng.annotations.DataProvider;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.ExcelUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;


//@SuppressWarnings("unused")
public class DataProviders extends TestBase {
	public static DataProviders ref_cmd;
	public static Method meth[];
	public static Xls_Reader testcase_excelFile;
	
	
	
	
	public static DataProviders dp_getInstance(){
		if (ref_cmd == null){
			
			ref_cmd=new DataProviders();
			logsObj.log("Created Data Provider class instance...");
			
			meth=ref_cmd.getClass().getMethods();	
			logsObj.log("retrieve all methods from DataProviders class..");
		}
		
		return ref_cmd;	
		
		
	}
	
	public static void getConfigParams(){
		dataFetchParamFlag=ExcelUtil.getParameters(configXls, "DataFetchFlag", "TestCaseName","DataFetchFlag");
		dataFetchParamXL=ExcelUtil.getParameters(configXls, "DataFetchXL", "TestCaseName","ExcelDataSheetPath");
		dataFetchParamMasterSheetName=ExcelUtil.getParameters(configXls, "DataFetchXL", "TestCaseName","FirstSheetName");
		
	}
	
	
	@DataProvider
	public static Object[][] getData_Global(Method m) throws Throwable{
		testcaseName=m.getName();
		String[] splittc = testcaseName.split("Test");
		testcaseName=splittc[1];
		logsObj.log("DataProvider Fetch start for testcase:-"+testcaseName);
		Object[][] data=null;
		
		if ( testcaseName !=null && isTestSkip==false && isSuiteSkip==false){
			logsObj.log("the testcase-"+testcaseName+ " is fetchable so execute_dataTable now");
			data=DataProviders.execute_dataTable(testcaseName);
			
			if (data==null && isTestSkip==true){
				throw new SkipException("Skipping the test case: "+testcaseName+" As the return data is null,hence cannot execute further steps of this test case..");
				
			}else{
				logsObj.log("Returned Data from Execute_dataTable : DataProvider for the test case:-"+testcaseName+" return data is "+data);
				return data;
			}
		}else if (isTestSkip==true){
			return new Object[][] {{"skipTest"}};
					
		}else if (isSuiteSkip=true){
			return new Object[][] {{"skipSuite"}};
		}
		else{
			logsObj.log("Skipping the data provider as the test case-"+testcaseName+" is marked as skipp..");
			throw new SkipException("Skipping the data provider as the above test case is skipp..");
			
		}
		
		
	}
	
	
	// need to move to test base class 
	public static Object[][] execute_dataTable(String testcaseName) throws Throwable{
			Object[][] data=null;
			//System.out.println(dataFetchParamFlag.get(testcaseName));
			if(dataFetchParamFlag.get(testcaseName)==null){
				CustomExceptions.Exit(testcaseName, "Fetch the test case from Config.xlsx file", "Unable to fetch the test case-"+testcaseName);
				
			}
			if (dataFetchParamFlag.get(testcaseName).equalsIgnoreCase(Constants_FRMWRK.DP_XL)){
				logsObj.log("Fetching data flag of test case "+testcaseName+" is XL");
				String excelPath=dataFetchParamXL.get(testcaseName);
				//System.out.println(excelPath);
				logsObj.log(testcaseName+" DataProvider Xls path-->"+excelPath);
				excelPath=System.getProperty("user.dir")+excelPath;
				
				testcase_excelFile=new Xls_Reader(excelPath);
				
				data=ExcelUtil.fetchSingleSheetData(dataFetchParamMasterSheetName.get(testcaseName), testcase_excelFile);
				WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
				logsObj.log(testcaseName+"-XL Data Provider data-"+data.toString());
				return data;
			}else if (dataFetchParamFlag.get(testcaseName).equalsIgnoreCase(Constants_FRMWRK.DP_DB)){
				logsObj.log("DataProvider-execute_dataTable:Fetching data flag of test case "+testcaseName+" is DB");
				if(query==null){
					isTestPass=Constants_FRMWRK.FalseB;
					CustomExceptions.Exit(testcaseName, "Dataprovider -Fetch query for the test case", "Unable to fetch the query for the test-"+testcaseName);
				}
				data=DBObject.fetchOracleData(query);
				logsObj.log(testcaseName+"-DB Data Provider data-"+data);
				return data;
			}else{
				isTestPass=Constants_FRMWRK.FalseB;
				logsObj.log("DataProvider-execute_dataTable:Fail--- unable to Fetch data of test case "+testcaseName+" due to invalid flag to fetch");
				data= null;
				return data;
			}
					
				
	}

	
	

}
