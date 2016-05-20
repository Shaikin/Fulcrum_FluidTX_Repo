package com.proj.Constants;

import com.proj.suiteTRANSMITTALS.TestSuiteBase;

public class Constants_ConfigProperties extends TestSuiteBase{ 
	
	final public static String testSiteName = CONFIG.getProperty("testSiteName");
	
	   //#R for Regression,p for Partial Regression,S for sanity
		
			final public static String SuiteMode = CONFIG.getProperty("SuiteMode");
			
			final public static String captureTool = CONFIG.getProperty("captureTool");
		//# ie, firefox,chrome
		
			final public static String browserType_DOCS = CONFIG.getProperty("browserType_DOCS");
		
			final public static String browserType_TRANS = CONFIG.getProperty("browserType_TRANS");
			
			
		
		//# User Credentials  
			
			final public static String user_Domain = CONFIG.getProperty("userDomain");
			final public static String username_SuperUser = CONFIG.getProperty("userUserName");
			final public static String password_SuperUser = CONFIG.getProperty("userpassword");
			
			final public static String username_AutoTestAdmin = CONFIG.getProperty("userUserName1");
			final public static String password_AutoTestAdmin = CONFIG.getProperty("userpassword1");
			
			final public static String username_AutoTestUser = CONFIG.getProperty("userUserName2");
			final public static String password_AutoTestUser = CONFIG.getProperty("userpassword2");
			
			final public static String username_SubSiteUser = CONFIG.getProperty("userUserName3");
			final public static String password_SubSiteUser = CONFIG.getProperty("userpassword3");

		//	Database details (optional has to be fetched from DB)
			
			final public static String DB_FLAG = CONFIG.getProperty("DB_FLAG");
			final public static String IS_REMOTEDRIVER = CONFIG.getProperty("IS_REMOTEDRIVER");
			final public static String DB_PROVIDER = CONFIG.getProperty("DB_PROVIDER");
			final public static String DB_DATASOURCE = CONFIG.getProperty("DB_DATASOURCE");
			final public static String DB_DATASOURCEPORT = CONFIG.getProperty("DB_DATASOURCEPORT");
			final public static String DB_DATABASE = CONFIG.getProperty("DB_DATABASE");
			final public static String DB_USERID = CONFIG.getProperty("DB_USERID");
			final public static String DB_PSWD = CONFIG.getProperty("DB_PSWD");
			
		//	test repository
			
			final public static String testRepository_UR = CONFIG.getProperty("testRepository_UR");
			
		//	customized reporting
			
			final public static String htmlReporting = CONFIG.getProperty("htmlReporting");
			final public static String excelReporting = CONFIG.getProperty("excelReporting");
			
	   // Email Results		
			
			final public static String email_Flag = CONFIG.getProperty("email");
			final public static String email_From = CONFIG.getProperty("emailFrom");
			final public static String email_User = CONFIG.getProperty("emailUser");
			final public static String email_Password = CONFIG.getProperty("emailPassword");
			final public static String email_Receipients = CONFIG.getProperty("emailReceipients");
			final public static String email_Subject = CONFIG.getProperty("emailSubject");
			final public static String email_Message = CONFIG.getProperty("emailMessage");
			final public static String email_Signature= CONFIG.getProperty("emailSignature");
			final public static String published_Results_OpenLocation = CONFIG.getProperty("publishedResultsOpenLocation");
			final public static String published_Results_Location = CONFIG.getProperty("publishedResultsLocation=");
}
