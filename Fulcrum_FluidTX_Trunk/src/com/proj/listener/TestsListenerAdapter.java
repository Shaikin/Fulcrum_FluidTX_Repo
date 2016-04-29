package com.proj.listener;



import java.io.IOException;
import java.util.Hashtable;

import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.frw.Constants.Constants_FRMWRK;
import com.proj.base.TestBase;
import com.proj.util.TestExecutionUtil;

public class TestsListenerAdapter implements ITestListener, ISuiteListener, IInvokedMethodListener {
	private String beforeMethodName="befMethod";
	private String afterMethodName="aftMethod";
	public static boolean isScenarionPass;

	// This belongs to ISuiteListener and will execute before the Suite start
	@Override

	public void onStart(ISuite arg0) {
		try {
			TestBase.initialize();
		} catch (IOException e) {
			e.printStackTrace();
		}
		TestBase.logsObj.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		TestBase.logsObj.log("About to begin executing Suite " + arg0.getName());
		TestBase.logsObj.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Reporter.log("About to begin executing Suite " + arg0.getName(), true);

	}

	// This belongs to ISuiteListener and will execute, once the Suite is finished

	@Override

	public void onFinish(ISuite arg0) {
		TestBase.logsObj.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		TestBase.logsObj.log("About to end executing Suite " + arg0.getName());
		TestBase.logsObj.log("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		Reporter.log("About to end executing Suite " + arg0.getName(), true);

	}

	// This belongs to ITestListener and will execute before starting of Test set/batch 

	public void onStart(ITestContext arg0) {
		TestBase.logsObj.log("================================================");
		TestBase.logsObj.log("About to begin executing of the test :- " + arg0.getName());
		TestBase.logsObj.log("================================================");
		Reporter.log("About to begin executing of the test :-" + arg0.getName(), true);
		TestBase.scenarioName=arg0.getName();
		isScenarionPass=Constants_FRMWRK.TrueB;
	}

	// This belongs to ITestListener and will execute, once the Test set/batch is finished

	public void onFinish(ITestContext arg0) {

			
		TestBase.logsObj.log("================================================");
		TestBase.logsObj.log("Completed executing of the test:- " + arg0.getName());
		TestBase.logsObj.log("================================================");
		Reporter.log("Completed executing test:- " + arg0.getName(), true);	

	}

	// This belongs to ITestListener and will execute only when the test is pass

	public void onTestSuccess(ITestResult arg0) {

		// This is calling the printTestResults method

		//printTestResults(arg0);

	}

	// This belongs to ITestListener and will execute only on the event of fail test

	public void onTestFailure(ITestResult arg0) {

		// This is calling the printTestResults method

		//printTestResults(arg0);

	}

	// This belongs to ITestListener and will execute before the main test start (@Test)

	@SuppressWarnings("unchecked")
	public void onTestStart(ITestResult arg0) {
		String tc="";
		System.out.println("Test method:-"+arg0.getName()+" of the test is started..");
		TestBase.logsObj.log("Test method:-"+arg0.getName()+" of the test is started..");
		Object[] parms=arg0.getParameters();
		Hashtable<String,String>data=(Hashtable<String, String>) parms[0];
		TestBase.refID=data.get("RefID");
		if(data.get("IssueReason")!=null){
			tc=data.get("IssueReason");			
		}else{
			tc=TestBase.scenarioName;
		}

		if(data.get("AttachDocumentName")!=null){
			if(!data.get("AttachDocumentName").equalsIgnoreCase("")){
				tc=tc+" with Attachment";
			}else{
				tc=tc+" without Attachment";
			}
		}

		if(data.get("Action-Level2")!=null){
			if(!data.get("Action-Level2").equalsIgnoreCase("")){
				tc=tc+"-"+data.get("Action-Level2");
			}
		}

		if(data.get("Action-Level3")!=null){
			if(!data.get("Action-Level3").equalsIgnoreCase("")){
				tc=tc+"-"+data.get("Action-Level3");
			}
		}

		TestBase.testcaseName=tc;
		//TestBase.testcaseName=data.get("IssueReason")+"-"+data.get("Action-Level2");
	}

	// This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped

	public void onTestSkipped(ITestResult arg0) {

		printTestResults(arg0);

	}

	// This is just a piece of shit, ignore this

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	// This is the method which will be executed in case of test pass or fail

	// This will provide the information on the test

	private void printTestResults(ITestResult result) {

		Reporter.log("Test Method resides in " + result.getTestClass().getName(), true);

		if (result.getParameters().length != 0) {

			String params = null;

			for (Object parameter : result.getParameters()) {

				params += parameter.toString() + ",";

			}

			Reporter.log("Test Method had the following parameters : " + params, true);

		}

		String status = null;

		switch (result.getStatus()) {

		case ITestResult.SUCCESS:

			status = "Pass";

			break;

		case ITestResult.FAILURE:

			status = "Failed";

			break;

		case ITestResult.SKIP:

			status = "Skipped";

		}

		Reporter.log("Test Status: " + status, true);

	}

	// This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test

	public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "About to begin executing following method : " + returnMethodName(arg0.getTestMethod());

		if(returnMethodName(arg0.getTestMethod()).contains(beforeMethodName)){
			TestBase.isTestPass=Constants_FRMWRK.TrueB;
			TestExecutionUtil.initialiseTestFlags(TestBase.testcaseName);
		}

		if(returnMethodName(arg0.getTestMethod()).contains(afterMethodName)){
			if(TestBase.isTestPass==Constants_FRMWRK.FalseB && isScenarionPass==Constants_FRMWRK.TrueB){
				isScenarionPass=Constants_FRMWRK.FalseB;
			}	
		}

		Reporter.log(textMsg, true);

	}

	// This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test

	public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {

		String textMsg = "Completed executing following method : " + returnMethodName(arg0.getTestMethod());

		Reporter.log(textMsg, true);

	}

	// This will return method names to the calling function

	private String returnMethodName(ITestNGMethod method) {

		return method.getRealClass().getSimpleName() + "." + method.getMethodName();

	}

}