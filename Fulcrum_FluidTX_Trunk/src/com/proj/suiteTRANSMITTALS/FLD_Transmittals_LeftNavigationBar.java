package com.proj.suiteTRANSMITTALS;
import java.util.Hashtable;

import org.testng.annotations.Test;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_ConfigProperties;
import com.proj.navigations.Navigations_FluidTX;
import com.proj.util.CustomExceptions;
import com.proj.util.TestExecutionUtil;
import com.proj.utilFulcrum.ApplicationMethods;

public class FLD_Transmittals_LeftNavigationBar extends TestSuiteBase{

	@Test(dataProviderClass=com.proj.util.DataProviders.class,dataProvider="getData_Global")
	public static void TestFLD_Transmittals_LeftNavigationBar(Hashtable<String,String>data
			) throws Throwable{

		System.out.println("In test");
		logsObj.log("******************************************************");		
		logsObj.log("Executing the test case: "+testcaseName);
		try{
			String appName=ApplicationMethods.getSiteName(Constants_ConfigProperties.testSiteName);
			if(isBeforeMethodPass_trans==Constants_FRMWRK.FalseB){
				CustomExceptions.Exit(testcaseName, "Before Method-Failure", "Due to above error in the Before Method cannot execute the test..");
			}

			logsObj.log("******************************************************");		
			logsObj.log("Executing the test case: "+testcaseName);
			Navigations_FluidTX.verify_menu(driver_TRANS, appName, data);
			WaitUtil.pause(2);

		}catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_TRANS,refID,t);			
		}

		TestExecutionUtil.AssertTestStatus(isTestPass);

	}
}
