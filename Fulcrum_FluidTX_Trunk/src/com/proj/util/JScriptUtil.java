package com.proj.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;

public class JScriptUtil extends TestBase{
	
	public static JavascriptExecutor get_JscriptObject(WebDriver driver){
		JavascriptExecutor js=null;
	
		
		for (int i=0;i<5;i++){
			
			try{
					WaitUtil.pause(1);
					js = (JavascriptExecutor) driver; 
					 WaitUtil.pause(1);
			
				}catch(Throwable t){
					System.out.println(t.getMessage());
				}
				
			if(js!=null){
				break;
			}else{
				WaitUtil.pause(3);
			}
		}
		
		if (js==null){
			logsObj.log("The returned JS is null ");
		}else{
			logsObj.log("The returned JS is not null");
		}
		
		return js;
	}
	
	
	public static void set_JSTimeOuts(WebDriver driver){
		driver.manage().timeouts().setScriptTimeout(Constants_TimeOuts.Jscript_TimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Constants_TimeOuts.Page_MAX_TimeOut, TimeUnit.SECONDS);
		logsObj.log("Jscript & Page time outs are set to "+Constants_TimeOuts.Jscript_TimeOut+" --"+Constants_TimeOuts.Page_MAX_TimeOut);
		
	}

	
	public static void api(WebDriver driver) throws InterruptedException{
		JavascriptExecutor js=JScriptUtil.get_JscriptObject(driver);
		js.executeScript("var jq = document.createElement('script');jq.src = 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js';document.getElementsByTagName('head')[0].appendChild(jq);");
	      Thread.sleep(300);
	  }
		/*String flag = (String)js.executeScript("return jQuery == 'undefined'");
		
		if (flag.equalsIgnoreCase("true"))
		  {
		      js.executeScript("var jq = document.createElement('script');jq.src = '//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js';document.getElementsByTagName('head')[0].appendChild(jq);");
		      Thread.sleep(300);
		  }*/
	//}
}
