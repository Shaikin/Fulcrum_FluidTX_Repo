package com.report.reporter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.frw.util.FileUtil;
import com.frw.util.WaitUtil;
import com.proj.base.TestBase;
import com.proj.library.commonMethods;
import com.proj.util.EmailResults;

public class Reporting extends TestBase{
	static int current_suite=0;
	
	private static void excelStep(WebDriver driver,String suiteSheetName,String module,String scenarioName,String refID,String testcaseName,String browser,String browVersion,String environ,String Step, String stepDescription,String status){
		excelReportingObj.Reporter(driver, suiteSheetName, module, scenarioName, refID, testcaseName,browser,browVersion,environ, Step, stepDescription, status);
	}
	private static void excelStep(String suiteSheetName,String module,String scenarioName,String refID,String testcaseName,String browser,String browVersion,String environ,String Step, String stepDescription,String status){
		excelReportingObj.Reporter(suiteSheetName, module, scenarioName, refID, testcaseName,browser,browVersion,environ, Step, stepDescription, status);
	}
	
	private static void htmlStep(WebDriver driver,BufferedWriter suiteFileName,String module,String scenarioName,String refID,String testcaseName,String browser,String browVersion,String environ,String Step, String stepDescription,String status){
		htmlReportingObj.logTestStepDetails(driver,suiteFileName,  module, scenarioName, refID, testcaseName,browser, browVersion, environ, Step, stepDescription, status);
	}
	private static void htmlStep(BufferedWriter suiteFileName,String module,String scenarioName,String refID,String testcaseName,String browser,String browVersion,String environ,String Step, String stepDescription,String status){
		htmlReportingObj.logTestStepDetails(suiteFileName,  module, scenarioName, refID, testcaseName,browser, browVersion, environ, Step, stepDescription, status);
	}
	
	
	public static void CreateExcelSummarySheet(String suiteSheetName,String screenShotLocation){
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES")){
			excelReportingObj.createSuiteSummarySheet(excelresultsFileLoc, suiteSheetName, screenShotLocation);
		}
	}
	
	public static BufferedWriter CreateHTMLSummaryFile(String pathOfFileToStore,String Title,String screenShotLocation ) throws Throwable{
		BufferedWriter suite_bfw=null;
		if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			suite_bfw=htmlReportingObj.createHTMLForSuiteExecution(pathOfFileToStore, Title, screenShotLocation);
			
		}
		return suite_bfw;
	}
	
	/*public static void suiteIncrementer(){
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES") && CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			current_suite++;
		}
	}*/
	
	
	
	/**
	 * Logs the step action details of a test under suite into the relevant suite summary sheet/page
	 * @author khshaik
	 * @date Nov 05 2014
	 * @param driver
	 * @param refID
	 * @param Step
	 * @param stepDescription
	 * @param status
	 */
	public static void logStep(WebDriver driver,String refID,String Step,String stepDescription,String status){
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES")){
			excelStep(driver, currentSuite_Sheetname, moduleName, scenarioName, refID, testcaseName, browserName, browserVersion, System.getProperty("os.name").toLowerCase(), Step, stepDescription, status);
		}
		
		if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			htmlStep(driver, currentSuite_bfw, moduleName, scenarioName, refID, testcaseName, browserName, browserVersion, System.getProperty("os.name").toLowerCase(), Step, stepDescription, status);
		}
		
	}
	/**
	 * Logs the step action details of a test under suite into the relevant suite summary sheet/page
	 * @author shaik
	 * @date Jan 17 2016
	 * @param driver
	 * @param refID
	 * @param Step
	 * @param stepDescription
	 * @param status
	 */
	public static void logStep(String Step,String stepDescription,String status){
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES")){
			excelStep( currentSuite_Sheetname, moduleName, scenarioName, refID, testcaseName, browserName, browserVersion, System.getProperty("os.name").toLowerCase(), Step, stepDescription, status);
		}
		
		if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			htmlStep( currentSuite_bfw, moduleName, scenarioName, refID, testcaseName, browserName, browserVersion, System.getProperty("os.name").toLowerCase(), Step, stepDescription, status);
		}
		
	}
	
	/**
	 * Logs the step action details of a test under suite into the relevant suite summary sheet/page
	 * @author khshaik
	 * @date Nov 05 2014
	 * @param driver
	 * @param Step
	 * @param stepDescription
	 * @param status
	 */
	public static void logStep(WebDriver driver,String Step,String stepDescription,String status){
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES")){
			excelStep(driver, currentSuite_Sheetname, moduleName, scenarioName, refID, testcaseName, browserName, browserVersion, System.getProperty("os.name").toLowerCase(), Step, stepDescription, status);
		}
		
		if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			htmlStep(driver, currentSuite_bfw, moduleName, scenarioName, refID, testcaseName, browserName, browserVersion, System.getProperty("os.name").toLowerCase(), Step, stepDescription, status);
		}
		
	}
	
	/**
	 * Logs the test status details into the test summary sheet/page
	 * @author khshaik
	 * @date Nov 5 2014
	 * @param testSrtTime
	 * @param testEndTime
	 */
	public static void logTestSummaryStep(String testSrtTime,String testEndTime){
		
		if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES")){
			excelReportingObj.ReporterTestSummary(scenarioName, testcaseName, isTestPass, testSrtTime, testEndTime);
		}
		
		if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES")){
			String sts="Pass";
			if(isTestPass==false){
				sts="Fail";
			}
			htmlReportingObj.logTestStatus(htmlsummary_bfw,scenarioName, testcaseName, sts, testSrtTime, testEndTime);
		}
		
		
	}
	
	
	public static void  closeTagsForHTMLReporting(BufferedWriter currentSuite_bfw) throws Throwable{
		htmlReportingObj.closingTags(currentSuite_bfw);	
		
		if(current_suiteCounter==suites_size){
			htmlReportingObj.closingTags(htmlsummary_bfw);					
			htmlReportingObj.UpdateHTMLTestSummary(testSummaryFilePath);
			
		}
		
		
	}
	
	public static void  closeTagsForHTMLReportingEmail(BufferedWriter currentSuite_bfw,String resultsFolder,String zipFileName,String From,String User,String Password,String Recepients,String Subject,String Mailmessage,String attachmentPath ) throws Throwable{
		htmlReportingObj.closingTags(currentSuite_bfw);	
		
		if(current_suiteCounter==suites_size){
			htmlReportingObj.closingTags(htmlsummary_bfw);					
			htmlReportingObj.UpdateHTMLTestSummary(testSummaryFilePath);
			EmailResults.ZipAndpublish(resultsFolder, zipFileName, From, User, Password, Recepients, Subject, Mailmessage, attachmentPath);
		}
		
		
	}
	
	public static void  closeTagsForHTMLReportingEmail(BufferedWriter currentSuite_bfw,String resultsFolder,String sourceFolder,String From,String User,String Password,String Recepients,String Subject,String Mailmessage ) throws Throwable{
		htmlReportingObj.closingTags(currentSuite_bfw);	
		String dropboxSourceLocation = "";
		if(current_suiteCounter==suites_size){
			htmlReportingObj.closingTags(htmlsummary_bfw);	
			htmlReportingObj.UpdateHTMLTestSummary(testSummaryFilePath);
			//Reporting.CopyExcelReports_DropBox(System.getProperty("user.dir")+"//Results", "C:/Users/"+System.getProperty("user.name")+"/Dropbox/Public/Results/");
			//Reporting.CopyHTMLReports_DropBox(System.getProperty("user.dir")+"//Results", "C:/Users/"+System.getProperty("user.name")+"/Dropbox/Public/Results/");
			//Reporting.CopyExcelReports_DropBox(resultsFolder, sourceFolder);
			//Reporting.CopyHTMLReports_DropBox(resultsFolder, sourceFolder);
			
			WaitUtil.pause(2);
			if(sourceFolder.contains("username")){
				dropboxSourceLocation=sourceFolder.replace("username", System.getProperty("user.name"));
			}else{
				dropboxSourceLocation=sourceFolder;
			}
			
			if (CONFIG.getProperty("excelReporting").equalsIgnoreCase("YES") && CONFIG.getProperty("email").equalsIgnoreCase("YES")){
				CopyHTMLReports_TomcatServer(resultsFolder, dropboxSourceLocation);
			}
			if (CONFIG.getProperty("htmlReporting").equalsIgnoreCase("YES") && CONFIG.getProperty("email").equalsIgnoreCase("YES")){
				String link=CopyHTMLReports_TomcatServer(resultsFolder, dropboxSourceLocation);
				String confLink=CONFIG.getProperty("publishedResultsOpenLocation");
				WaitUtil.pause(1);
				
					confLink=commonMethods.replaceString("fileName", confLink, link);
				
				
				EmailResults.publish(From, User, Password, Recepients, Subject, Mailmessage, confLink, CONFIG.getProperty("emailSignature"));
				
			}
			
			//EmailResults.publish(resultsFolder, sourceFolder, From, User, Password, Recepients, Subject, Mailmessage, confLink,CONFIG.getProperty("emailSignature"));
			
		}	
		
	}
	public static String CopyExcelReports_DropBox(String source,String dest){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = dateFormat.format(now);
		
		
		source=source+"\\ExcelResults\\";
		String destFile=strDate+"-ExcelResults/";
		dest=dest+destFile;
		FileUtil fileObj=FileUtil.getFileUtilObject();
		fileObj.copyFolders(source, dest);
		return destFile;
		
	}
	public static String CopyHTMLReports_DropBox(String source,String dest){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = dateFormat.format(now);
		
		
		source=source+"\\HTMLReporting\\";
		String destFile=strDate+"-HTMLReporting/";
		dest=dest+destFile;
		FileUtil fileObj=FileUtil.getFileUtilObject();
		fileObj.copyFolders(source, dest);
		return destFile;
	}
	
	public static String CopyExcelReports_TomcatServer(String source,String dest){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = dateFormat.format(now);
		
		
		source=source+"\\ExcelResults\\";
		String destFile=strDate+"-ExcelResults/";
		dest=dest+destFile;
		FileUtil fileObj=FileUtil.getFileUtilObject();
		fileObj.copyFolders(source, dest);
		return destFile;
		
	}
	public static String CopyHTMLReports_TomcatServer(String source,String dest){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date now = new Date();
		String strDate = dateFormat.format(now);
		
		
		source=source+"\\HTMLReporting\\";
		String destFile=strDate+"-HTMLReporting/";
		dest=dest.replaceAll("fileName", destFile);
		//dest=dest+destFile;
		FileUtil fileObj=FileUtil.getFileUtilObject();
		fileObj.copyFolders(source, dest);
		return destFile;
	}

}
