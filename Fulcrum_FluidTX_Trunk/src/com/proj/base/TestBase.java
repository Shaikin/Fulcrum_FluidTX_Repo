package com.proj.base;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.Properties;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.base.Base;
import com.frw.excelreporting.ExcelReporting;
import com.frw.htmlreporting.HTMLReporting;
import com.frw.log.Logs;
import com.frw.util.DateUtil;
import com.frw.util.OracleDBUtil;
import com.frw.util.TestUtil;
import com.frw.util.Xls_Reader;
import com.frw.util.baseUtil;
import com.proj.Constants.Constants;
import com.proj.util.DataProviders;
import com.report.reporter.Reporting;


public class TestBase extends Base{
	

	public static Xls_Reader suiteXls=null;		
	public static Xls_Reader suiteTRNSxls=null;
	public static Xls_Reader suiteDOCSxls=null;
	public static Xls_Reader configXls=null;
	public static boolean isInitiazed=false;
	
	public static String parentwindow;
		
	public static String local_testcaseName;
	public static String scenarioName="";
	public static String moduleName="";
	public static String browserName="";
	public static String browserVersion="";
	public static String testStartDateTime="";
	public static String testEndDateTime="";
	public static String AUT_URL="";
	
	public static String TestRRU_scenarioName="";
	public static String error_Step="";
	public static String error_Description="";
	public static String isRemoteDriver=Constants_FRMWRK.False;
	public static String isMobile=Constants_FRMWRK.False;
	public static DataProviders ref_dataProvider=null;
	
	public static boolean scenarioFlag=false;	

	public static boolean isTabExist=true;
	
	public static Hashtable<String,String>dataFetchParamFlag=null;
	public static Hashtable<String,String>dataFetchParamXL=null;
	public static Hashtable<String,String>dataFetchParamSQL=null;
	public static Hashtable<String,String>dataFetchParamMasterSheetName=null;
	
	public static String query=null;
	public static int resultStep=0;
	public static int summaryrowNumber=0;
	
	public static DateUtil dateObj=null;
	public static ExcelReporting excelReportingObj=null;	
	public static HTMLReporting htmlReportingObj=null;
	private static String resultsFileLoc=null;
	public static String excelresultsFileLoc=null;
	public static String htmlresultsFileLoc=null;
	public static String screenshotsFileLoc=null;
	public static OracleDBUtil DBObject =null;
	public static BufferedWriter htmlsummary_bfw=null;
	private static String summarySheetName="TestSummary";
	private static String summaryHTMLFileName="index.html";
	private static boolean isRemoteDriverExecution=false;
	public static String currentSuite_Sheetname="";
	public static BufferedWriter currentSuite_bfw=null;	
	public static String testSummaryFilePath="";
	public static String refID="";
	public static int suites_size;
	public static int current_suiteCounter=0;
	static String configFolder=System.getProperty("user.dir")+"\\src\\com\\proj\\config\\";
	static String environmentFile=configFolder+"Environment.properties";
	
	
	/**
	 * @author: Shaik Khaleel
	 * This is used the initiliazed all the required files 
	 * @throws IOException
	 */
	public void initialize() throws IOException{
	
	if (!isInitiazed)
	{
		System.out.println("Test Base Initialization starts");
	
		
		logsObj=Logs.getLogsObjAndInitialize();
		
		Properties environmentProperties=baseUtil.loadPropertiesFile(environmentFile);
		
		Base.baseInitialize(configFolder+environmentProperties.getProperty("Environment")+".properties", System.getProperty("user.dir")+"\\src\\com\\proj\\objectRepository\\OR.properties");
		
		configXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\proj\\config\\"+ Constants.CONFIG_FILE_NAME);
		logsObj.log("configXLSdone..");
		suiteXls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\proj\\suite\\"+Constants_FRMWRK.SUITE_FILE_NAME);
		suiteTRNSxls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\proj\\suite\\"+Constants.SUITE_TRANS_FILE_NAME);
		suiteDOCSxls=new Xls_Reader(System.getProperty("user.dir")+"\\src\\com\\proj\\suite\\"+Constants.SUITE_DOCS_FILE_NAME);
		
		
		//Logs load
		logsObj.log("Loaded Suite XL files..");
		
		// get instance of data provider class
		DataProviders.getConfigParams();
		AUT_URL=CONFIG.getProperty("testSiteName");		
			
		if (CONFIG.getProperty("IS_REMOTEDRIVER").equalsIgnoreCase("YES")){
			isRemoteDriverExecution=true;
		}
		resultsFileLoc=System.getProperty("user.dir")+"\\Results";
		screenshotsFileLoc=System.getProperty("user.dir")+"\\Screenshots\\";
		dateObj=DateUtil.getDateUtilObject();
		
		//**************  Excel reporting initialization  *********************************************
		initializeExcelReporting();
		//**************  HTML reporting initialization  *********************************************	
		initializeHTMLReporting();
		
		//*****************  DB initialization **********************************************************
		if (CONFIG.getProperty("DB_FLAG").equalsIgnoreCase("YES")){
			DBObject =OracleDBUtil.getDBUtilObj();
			DBObject.initializeDBParameters(CONFIG.getProperty("DB_PROVIDER"), CONFIG.getProperty("DB_DATASOURCE"), CONFIG.getProperty("DB_DATASOURCEPORT"), CONFIG.getProperty("DB_DATABASE"), CONFIG.getProperty("DB_USERID"), CONFIG.getProperty("DB_PSWD"));
			
			logsObj.log("DB parameters are initialized..");
		}
		
		
		isInitiazed=true;
	}
	
	}
	
	/**
	 * initialize excel reporting according to the flag given in config file
	 * @author khshaik
	 * @date Nov 3 2014
	 * @throws IOException
	 */
	
	private void initializeExcelReporting(){
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES")){
			logsObj.log("Excel reporting is enabled..");
			//**************  Excel reporting initialization  *********************************************
			String resultsFile=resultsFileLoc+"\\ExcelResults";
			excelReportingObj=ExcelReporting.getExcelReportingObject();
			excelresultsFileLoc=excelReportingObj.CreateResultFile(resultsFile);
			excelReportingObj.createTestSummarySheet(excelresultsFileLoc, summarySheetName,isRemoteDriverExecution,CONFIG.getProperty("testRepository_URL"), CONFIG.getProperty("testSiteName"));
			logsObj.log("Excel reporting is initailized sucessfully..");
		}else{
			logsObj.log("Excel reporting is not enabled..");
		}
	}
	/**
	 * initialize html reporting according to the flag given in config file
	 * @author khshaik
	 * @date Nov 3 2014
	 * @throws IOException
	 */
	private void initializeHTMLReporting() throws IOException{
		if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			logsObj.log("HTML reporting is enabled..");
		//**************  HTML reporting initialization  *********************************************	
				htmlresultsFileLoc=resultsFileLoc+"\\HTMLReporting\\";
				testSummaryFilePath=htmlresultsFileLoc+summaryHTMLFileName;				
				
				htmlReportingObj=HTMLReporting.getHTMLReportingObject();				
				LinkedHashMap <String,String>runnableSuites=TestUtil.fetch_SuitesRunnable(suiteXls, CONFIG.getProperty("SuiteMode"));
				suites_size=runnableSuites.size();
				htmlsummary_bfw=htmlReportingObj.createHTMLForTestSummary(testSummaryFilePath, htmlresultsFileLoc, isRemoteDriverExecution,  CONFIG.getProperty("testRepository_URL"), "Selenium Automation Test Results", runnableSuites, CONFIG.getProperty("testSiteName"));
				logsObj.log("HTML reporting is initailized sucessfully..");
		}else{
			logsObj.log("HTML reporting is not enabled..");
		}
	}
	
	/**
	 * Initializes the Reporting templates
	 * @author khshaik
	 * @date Feb 19 2015
	 * @param suiteFilePath
	 * @throws Throwable 
	 */
	public static void initializeReports(String suiteFilePath) throws Throwable{
		Reporting.CreateExcelSummarySheet(currentSuite_Sheetname, screenshotsFileLoc);
		currentSuite_bfw=Reporting.CreateHTMLSummaryFile(suiteFilePath, currentSuite_Sheetname+" Execution Summary", screenshotsFileLoc);
		
	}
	
	
	
}
