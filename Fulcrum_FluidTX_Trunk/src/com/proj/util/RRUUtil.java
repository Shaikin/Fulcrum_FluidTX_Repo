package com.proj.util;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;

import com.frw.base.Base;
import com.frw.util.DBUtil;
import com.frw.util.RefUtil;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.library.commonMethods;

public class RRUUtil extends Base{
	
	
	/**
	 * retrieves the sheet data for required for test
	 * @author khshaik	
	 * @Date  Feb 02 2015
	 * @return
	 *//*
	private static Hashtable<String,String> getData(String fullPathOfDataFile,String sheetName,int rowNumber){
		Xls_Reader xlsReader_Data = new Xls_Reader(fullPathOfDataFile);
		Hashtable<String,String> xlData_Asset=ExcelUtil.getRowdataFromExcel(xlsReader_Data, sheetName, 0);
		return xlData_Asset;
	}*/
	
	
/*	
	public static void RRU_Callee(WebDriver driver_caller,WebDriver driver_calle,String moduleName_caller,String moduleName_calle,String testcaseName_caller,String testcaseName_calle,String referenceID_calle,String fullPathOfDataFile,String sheetName,int rowNumber) throws Throwable{
		boolean isInitialized=false;
		try{

			String calle_flags=TestExecutionUtil.reusableFlags_Before(testcaseName_caller,testcaseName_calle, referenceID_calle, isTestRRU);
			String [] flags=commonMethods.splitString(calle_flags, "@");
			Hashtable <String,String> Data=getData(fullPathOfDataFile,sheetName,rowNumber);
			if(moduleName_caller!=moduleName_calle && driver_calle==null){
				driver_calle=driver_caller;
				isInitialized=true;
			}
			AddConsumable_WithoutTemplate.befMethod();
			AddConsumable_WithoutTemplate.TestAddConsumable_WithoutTemplate(Data);
			AddConsumable_WithoutTemplate.aftMethod();
			TestExecutionUtil.reusableFlags_After(flags[0], flags[1], flags[2]);
			if(isInitialized==true && driver_calle!=null){
				driver_calle=null;
			}
		}
		catch(Throwable t){
			CustomExceptions.final_catch_Reporting(driver_calle, referenceID_calle, t);
		}  

	}*/
	
	public static void generic_RRU(Class<?> callerClass,Class<?> calleClass,WebDriver driver_caller,WebDriver driver_calle,String isTestRRU){
		boolean isInitialized=false;
		boolean isBeforeFlagsInitialized=false;
		String referenceID_calle="";
		String [] flags = null;
		try{
			Hashtable<String,String> testData=new Hashtable<String,String>();
			
			String testcaseName_caller=RefUtil.getClassName(callerClass);
			String testcaseName_calle=RefUtil.getClassName(calleClass);
			referenceID_calle=(String) RefUtil.getPrivateFieldValueofClass(calleClass, "local_refID");
						
			String calle_flags=TestExecutionUtil.reusableFlags_Before(testcaseName_caller,testcaseName_calle, referenceID_calle, isTestRRU);
			isBeforeFlagsInitialized=true;
			flags=commonMethods.splitString(calle_flags, "@");
			
			if(driver_calle==null){			
				driver_calle=driver_caller;
				RefUtil.initPublicVariableofClass(calleClass, "driver_", driver_caller);
				isInitialized=true;
			}
			
			Object[][] objData=DataProviders.execute_dataTable(testcaseName_calle);	
			for (int i=0;i<objData.length;i++){
				testData=DBUtil.getdataFromObject(objData, i);
				
				RefUtil.invokeDeclaredMethod(calleClass, "befMethod");
				WaitUtil.pause(Constants_TimeOuts.AfterTest_TimeOut);
				RefUtil.invokeDeclaredMethod(calleClass,"Test"+testcaseName_calle,new Class[]{Hashtable.class},testData);
				RefUtil.invokeDeclaredMethod(calleClass, "aftMethod");
			}
			
			
			if(isInitialized==true && driver_calle!=null){
				driver_calle=null;
				RefUtil.initPublicVariableofClass(calleClass, "driver_", null);
			}
			
			TestExecutionUtil.reusableFlags_After(flags[0], flags[1], flags[2]);
			
		}catch(Throwable t){
			if(isBeforeFlagsInitialized==true){
				TestExecutionUtil.reusableFlags_After(flags[0], flags[1], flags[2]);
			}
			CustomExceptions.final_catch_Reporting(driver_calle, referenceID_calle, t);
		}
		
	}

}
