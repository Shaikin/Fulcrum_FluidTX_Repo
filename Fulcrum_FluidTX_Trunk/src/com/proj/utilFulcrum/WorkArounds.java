package com.proj.utilFulcrum;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.library.commonMethods;

public class WorkArounds {
	
	public static void deFocusCursor(WebDriver driver){
		driver.findElement(By.xpath("//body")).click();
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
	}
	
	public static void getViewPortOfPage(WebDriver driver){
		commonMethods.switchToDefaultPage(driver);
		WaitUtil.pause(2);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.F12);
		WaitUtil.pause(1);
		driver.findElement(By.xpath("//body")).sendKeys(Keys.F12);
	}

}
