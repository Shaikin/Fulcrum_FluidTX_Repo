package com.proj.runner;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Properties;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.base.Base;
import com.frw.log.Logs;
import com.frw.util.FileUtil;
import com.frw.util.TestNGRunnerUtil;
import com.frw.util.XMLUtil;
import com.frw.util.baseUtil;

public class TestNGRunner extends Base{
	
	static String suitessetupFolder= System.getProperty("user.dir")+"\\conf\\TestNG";
	static String suitesetupSuitesFolder=suitessetupFolder+"//suites";	
	static LinkedHashMap<String,String> runnableSuites=new LinkedHashMap<String,String>();
	final static String xmlFilePath =suitesetupSuitesFolder+"\\testNG.xml";
	
	static String configFolder=System.getProperty("user.dir")+"\\src\\com\\proj\\config\\";
	static String environmentFile=configFolder+"Environment.properties";
	
	@Test
	public static void triggertestNGXMLrunner() throws InterruptedException{
		System.out.println("*********Trigger Test ***********");
		String packagePattern="com.proj.suite";



		try {
			FileUtil fileObj=FileUtil.getFileUtilObject();
			logsObj=Logs.getLogsObjAndInitialize();
			fileObj.deleteFilesinFolder(suitesetupSuitesFolder);
			System.out.println("*********Suite Folder Files are Deleted ***********");
			fileObj.deleteFilesinFolder(System.getProperty("user.dir")+"\\test-output");
			System.out.println("*********Testoutput Folder Files are Deleted ***********");
			
			Properties environmentProperties=baseUtil.loadPropertiesFile(environmentFile);
			Properties Config=baseUtil.loadPropertiesFile(configFolder+environmentProperties.getProperty("Environment")+".properties");
			
			//Properties Config=baseUtil.loadPropertiesFile(System.getProperty("user.dir")+"\\src\\com\\proj\\config\\config.properties");
			runnableSuites=TestNGRunnerUtil.testNGXMLrunner(System.getProperty("user.dir")+"\\src\\com\\proj\\suite","xlsx", Constants_FRMWRK.SUITE_FILE_NAME, suitesetupSuitesFolder+"\\suites_lists.txt", packagePattern,suitesetupSuitesFolder,Config.getProperty("SuiteMode"));

			if(TestNGRunnerUtil.isAllTestsParsed==false){
				System.out.println("All Classes are not generated..please refer the logs");
			}else{
				System.out.println("All Classes under suites are generated..");
			}
				XMLUtil.createTestNGXML(xmlFilePath,"conf/TestNG/suites",runnableSuites,"Selenium Automation Suite-MAT-1.0");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}


}
