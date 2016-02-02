package com.proj.util;

import org.testng.Assert;
import org.testng.SkipException;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.TestUtil;
import com.frw.util.WaitUtil;
import com.frw.util.Xls_Reader;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.report.reporter.Reporting;

public class TestExecutionUtil extends TestBase{
	
	static String SuiteMode;
	public static String SuiteStatus=Constants_FRMWRK.Pass;
/* ***************************Find if the suite is Executable or not....*****************************************************************/
	
		
	//****************  find if test is skip or not ****************************************************
	/**
	 * Checks the test is executable and initialises the required flags
	 * @author khshaik	 * 
	 * @param testcaseName
	 * @param xls
	 */
		public static void testSkip(String testcaseName,Xls_Reader xls){
			isTestSkip=false;
			if(isTestRRU.equalsIgnoreCase(Constants_FRMWRK.False)){
				//	System.out.println("test case name: "+testcaseName);				
				logsObj.log("Verifying the test case"+testcaseName+" run mode..");
				if (!TestUtil.isTestCaseRunnable(xls, testcaseName))
					
				{
					logsObj.log("Skipinng the test case "+testcaseName+" As the run mode is set to NO");
					TestExecutionUtil.reportDataSetResult(xls, Constants_FRMWRK.TC_SHEET_NAME, TestExecutionUtil.get_testcaseRowNum(xls, testcaseName), Constants_FRMWRK.Skip);
					isTestSkip=true;
					//Assume.assumeTrue("Skipping the test case"+testcaseName+" As the run mode is set to NO,hence cannot execute further steps of this test case..",false);
					throw new SkipException("Skipping the test case"+testcaseName+" As the run mode is set to NO,hence cannot execute further steps of this test case..");

				}
				//testStartDateTime=DateUtil.getDateStamp("GETAPPLICATIONDATETIME");	
				testStartDateTime=dateObj.getFormattedDate();
				logsObj.log("the run mode of Test case: "+ testcaseName+" is yes,hence executing further steps..");
			}else{
				scenarioName=TestRRU_scenarioName;
			}
		}

		/**
		 * Initialise the required flags for the test executable
		 * @author khshaik
		 * @param testcaseName
		 * @param xls
		 */
		public static void initialiseTestFlags(String testcaseName){
			isTestSkip=false;
			if(isTestRRU.equalsIgnoreCase(Constants_FRMWRK.False)){
				testStartDateTime=dateObj.getFormattedDate();
				logsObj.log("the run mode of Test case: "+ testcaseName+" is yes,hence executing further steps..");
			}
			else{
				scenarioName=TestRRU_scenarioName;
			}
		}
		
		
		//***********************   update the test case sheet with Pass/Fail ************************************************
		public static void resultTest(boolean isTestPass,Xls_Reader xls,String testcaseName){
			testEndDateTime=dateObj.getFormattedDate();				
			if (!isTestPass){
				TestExecutionUtil.reportDataSetResult(xls, Constants_FRMWRK.TC_SHEET_NAME, TestExecutionUtil.get_testcaseRowNum(xls, testcaseName), Constants_FRMWRK.Fail);
				logsObj.log(testcaseName+" result in the excel sheet is marked as Fail");
				SuiteStatus=Constants_FRMWRK.Fail;
			}else{
				TestExecutionUtil.reportDataSetResult(xls, Constants_FRMWRK.TC_SHEET_NAME, TestExecutionUtil.get_testcaseRowNum(xls, testcaseName), Constants_FRMWRK.Pass);
				logsObj.log(testcaseName+" result in the excel sheet is marked as Pass");
			}
			WaitUtil.pause(200L);
			Reporting.logTestSummaryStep(testStartDateTime, testEndDateTime);
			WaitUtil.pause(Constants_TimeOuts.AfterTest_TimeOut);
		}
			
		
		
		//updates the Results column of a particular Data set
		public static void reportDataSetResult(Xls_Reader xls,String TestSheetName,int rowNum,String result){
			xls.setCellData(TestSheetName, "Results", rowNum, result);
		}
		
		//Return test data from the Test Data Sheet in 2 dimensional array(object)
		public static Object[][] getData(Xls_Reader xls,String TestDataSheet){
			
			if (!xls.isSheetExist(TestDataSheet)){
				xls=null;
				return new Object[1][0];
			}
			
			
			int rows=xls.getRowCount(TestDataSheet);
			int cols=xls.getColumnCount(TestDataSheet);
			
		//	System.out.println("Rows---"+rows);
		//	System.out.println("Cols---"+cols);
			
			Object[][] data=new Object[rows-1][cols-3];
			for (int rowNum=2;rowNum<=rows;rowNum++){
				for (int colNum=0;colNum<cols-3;colNum++){
					//System.out.print(xls.getCellData(TestDataSheet, colNum, rowNum)+"--- --");
					
					data[rowNum-2][colNum]=xls.getCellData(TestDataSheet, colNum, rowNum);
				}
				System.out.println();
			}
			
			
			return data;
			
		}
		
		// get row number of a test case from the particular suite excel sheet
		
		public static int get_testcaseRowNum(Xls_Reader xls, String testcaseName){
			
			for(int i=2;i<=xls.getRowCount(Constants_FRMWRK.TC_SHEET_NAME);i++){
				
				String tcid=xls.getCellData(Constants_FRMWRK.TC_SHEET_NAME, Constants_FRMWRK.TC_SHEET_TCID_COLUMN, i);
								
				if (tcid.equalsIgnoreCase(testcaseName)){
					return i;
				}
			}
			
			return -1;
		}
		
		public static String getTestcaseName(String local_testcaseName){
			// updated on Jun 25 2013
			   if(scenarioFlag==true){
					//TestSuiteBase.scenarioName=scenarioName;
				   logsObj.log("testcasename:");
					return testcaseName=local_testcaseName;						
				}else{
					scenarioName="";
					return testcaseName=local_testcaseName;
				}
				
				
		}	
		
		
		
		public static void AssertTestStatus(boolean isTestPass) {
			//boolean test=true;
			if(isTestRRU==Constants_FRMWRK.False){
				logsObj.log("Verifying Assert as isTestRRU is-"+isTestRRU);
				if(isTestPass==Constants_FRMWRK.TrueB){
					logsObj.log(testcaseName+" -Assert Status is "+isTestPass);
					//test=true;
				}else{
					logsObj.log(testcaseName+" -Assert Status is "+isTestPass);
					//test=false;			
					Assert.fail("There are failure[s] in the testcase:"+testcaseName+" Refer the excel for more details..");			
				}
				
			}else{
				logsObj.log("Not Verifying Assert as isTestRRU is-"+isTestRRU);
					
				}
		}
				
		/**
		 * Initializes the required flags before invoking the RRU class
		 * @author khshaik
		 * @date Nov 18 2014
		 * @param caller_tcname
		 * @param calle_tcname
		 * @param calle_Refid
		 * @param isTestRRUflag
		 * @return
		 */
		public static String reusableFlags_Before(String caller_tcname,String calle_tcname,String calle_Refid,String isTestRRUflag){
			String old_isTestRRU=isTestRRUflag;
			isTestRRU=Constants_FRMWRK.True;
			TestRRU_scenarioName=scenarioName;
			String old_testcaseName=caller_tcname;
			testcaseName=calle_tcname;
			String old_RefID=refID;	
			refID=calle_Refid;		
			
			return old_testcaseName+"@"+old_RefID+"@"+old_isTestRRU;
			
		}
		
		/**
		 * Initialize the required flags to calle after invoking the RRU class
		 * @param isTestRRU
		 * @param tcname
		 * @param Matid
		 * @param flag
		 * @return caller testcaseName and MATID
		 */
		public static String reusableFlags_After(String tcname,String Refid,String revert_isTestRRU){
			if(revert_isTestRRU.equalsIgnoreCase(Constants_FRMWRK.False)){
				isTestRRU=Constants_FRMWRK.False;
			}else{
				isTestRRU=Constants_FRMWRK.True;
			}
			
				testcaseName=tcname;
				refID=Refid;
				return testcaseName+"-"+refID;
			
			
		}
		
		
		
}
