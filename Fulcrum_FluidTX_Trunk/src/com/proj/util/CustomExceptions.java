package com.proj.util;

import java.util.Arrays;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.base.TestBase;
import com.report.reporter.Reporting;



public class CustomExceptions extends TestBase {

/**
 * Throws an exception 
 * @author khshaik
 * @param testcaseName
 * @param Step
 * @param Errormessage
 * @throws Exception
 */
	public static void Exit(String testcaseName,String Step,String Errormessage) throws Exception{
		isTestPass=Constants_FRMWRK.FalseB;
		if(error_Step.equalsIgnoreCase("")){
			error_Step=Step;
		}

		if(error_Description.equalsIgnoreCase("")){
			error_Description=Errormessage;
		}
		
		throw new Exception(Errormessage+"\n");

	}


	
	/**
	 * this method will be located in each test of catch block and this method will report the failure(exception) details of the test
	 * @author khshaik 
	 * @param driver
	 * @param refID
	 * @param t
	 */
	public static void final_catch_Reporting(WebDriver driver,String refID,Throwable t){


		if (!isTestSkip==true){
			isTestPass=false;
			if (error_Step.equalsIgnoreCase("")){
				Reporting.logStep(driver, refID, "Exception Block for the test:-Unexpected Failure ", "Error is due to "+t+" and stack is "+Arrays.toString(t.getStackTrace()), Constants_FRMWRK.Fail);
			}else{
				Reporting.logStep(driver, refID, "Exception-Catch Block for the test:-"+error_Step, error_Description+" and stack is "+Arrays.toString(t.getStackTrace()), Constants_FRMWRK.Fail);
			}

			error_Step="";
			error_Description="";
		}
	}





}
