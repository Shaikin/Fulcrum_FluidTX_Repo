package com.proj.suiteDOCS;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.proj.Constants.Constants;
import com.proj.base.TestBase;
import com.proj.library.Driver;
import com.report.reporter.Reporting;

public class TestSuiteBase extends TestBase {
	
	public static WebDriver driver_DOCS=null;
	
	@BeforeSuite
	public void checkSuiteSkip() throws Throwable{
		moduleName=Constants.Module_DOCS;
		currentSuite_Sheetname=moduleName+" Suite";
		current_suiteCounter++;
		
		initialize();
		String SuiteFilePath=htmlresultsFileLoc+moduleName+" Suite"+".html";
		
		initializeReports(SuiteFilePath);
		
		logsObj.log("the run mode of Suite "+ Constants.SUITE_DOCS_SUITENAME+" is yes..");
		
		browserName=CONFIG.getProperty("browserType_DOCS");
	
		//driver_TRANS=Driver.launchBrowser(browserName);
		//ApplicationMethodsOnTrack.navigateURLAndClickCookiesPopup(driver_TRANS, CONFIG.getProperty("testSiteName"));
		
		
	}

	
	

	@AfterSuite(alwaysRun = true)  

	public void aftSuite() throws Throwable{
		
		Driver.close(driver_DOCS);
		driver_DOCS=null;
		Reporting.closeTagsForHTMLReportingEmail(currentSuite_bfw, System.getProperty("user.dir")+"//Results", CONFIG.getProperty("publishedResultsLocation"), CONFIG.getProperty("emailFrom"), CONFIG.getProperty("emailUser"), CONFIG.getProperty("emailPassword"), CONFIG.getProperty("emailReceipients"), CONFIG.getProperty("emailSubject"), CONFIG.getProperty("emailMessage"));		
	}
}
