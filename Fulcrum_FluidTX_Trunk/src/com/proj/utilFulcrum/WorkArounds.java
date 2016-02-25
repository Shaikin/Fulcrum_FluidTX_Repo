package com.proj.utilFulcrum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.commonMethods;

public class WorkArounds extends TestBase{

	public static void deFocusCursor(WebDriver driver){
		Actions action =new Actions (driver);
		action.keyDown(Keys.SHIFT).sendKeys(Keys.TAB).build().perform();
		action.keyUp(Keys.SHIFT).build().perform();
		
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		logsObj.log("WorkArounds:- Defocussed with Shift & Tab");
		System.out.println("Defocussed....");
	}

	public static void getViewPortOfPage(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		WaitUtil.pause(2);
		driver.findElement(By.tagName("body")).sendKeys(Keys.F12);
		logsObj.log("WorkArounds:- getViewPortOfPage with F12 .. 1 st");
		WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);
		driver.findElement(By.tagName("body")).sendKeys(Keys.F12);
		logsObj.log("WorkArounds:- getViewPortOfPage with F12 .. 2 nd");
	}


	public static void deFocusCursor_shiftAndTab(WebDriver driver){

		driver.findElement(By.tagName("body")).click();
		System.out.println("Defocussed....");
	}
	public static void deFocusCursor_old(WebDriver driver){
		driver.findElement(By.xpath("//body")).click();
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
	}

	public static void getViewPortOfPage_old(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		WaitUtil.pause(2);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.F12);
		WaitUtil.pause(1);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.F12);
	}
}
