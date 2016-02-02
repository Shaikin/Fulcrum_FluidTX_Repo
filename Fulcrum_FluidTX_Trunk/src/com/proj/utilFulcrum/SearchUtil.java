package com.proj.utilFulcrum;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.proj.base.TestBase;
import com.proj.library.KeyMethods;
import com.proj.util.CustomExceptions;

public class SearchUtil extends TestBase{
	
	
	/**
	 * Enters the search criteria 
	 * @author khshaik 
	 * @param driver
	 * @param RefID
	 * @param workFlow
	 * @param objects_step
	 * @param objects_locatorType
	 * @param objects_objectType
	 * @param objects_objectLocator
	 * @param scanCode
	 * @return
	 * @throws Throwable
	 */
	public static String SearchCriteria(WebDriver driver,String RefID,String workFlow,String step,Hashtable<String,String>objects_step,Hashtable<String,String>objects_locatorType,Hashtable<String,String>objects_objectType,Hashtable<String,String>objects_objectLocator,String criteria) throws Throwable{
		String res;
		res=KeyMethods.f_performAction(driver, RefID, testcaseName,workFlow, objects_step.get(step), objects_locatorType, objects_objectType, objects_objectLocator, criteria);
		if(res.equalsIgnoreCase(Constants_FRMWRK.False)){
			logsObj.log(" the test case"+testcaseName+" failed during "+objects_step.get(step)+" "+objects_objectType.get(step)+" hence quiting ,cannot continue further steps of the testcase");
			CustomExceptions.Exit(testcaseName, objects_step.get(step)+"-failure", "Please check the customized results for more details");

		}
		WaitUtil.pause(2);
		return res;
	}

}
