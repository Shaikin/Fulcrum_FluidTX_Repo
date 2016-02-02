package com.proj.util;



import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import com.jacob.com.LibraryLoader;

public class AutoITUtil {

	/**
	 * fetches the os version of the machine
	 * @return
	 */

	public	static String jvmBitVersion(){
		return System.getProperty("sun.arch.data.model");
	}

	/**
	 * Loads the required jacob dll
	 */
	public static void loadJocobDLL(){
		String jacobDllVersionToUse;

		if (jvmBitVersion().contains("32")){
			jacobDllVersionToUse = "jacob-1.18-M2-x86.dll";
		}
		else {
			jacobDllVersionToUse = "jacob-1.18-M2-x64.dll";
		}

		File file = new File("lib", jacobDllVersionToUse);
		System.setProperty("jacob.dll.path", file.getAbsolutePath());
		//System.setProperty("jacob.dll.path", "E:\\FalcrumWS\\ATU_Selenium_Utils_Sample_Project\\lib\\jacob-1.18-x64.dll");
		LibraryLoader.loadJacobLibrary(); 
	}



	/**
	 * Perform key actions to open download window of IE browser
	 * @throws AWTException
	 */
	public static void robo_openDownloadsWindow() throws AWTException{
		Robot r = new Robot();
		// Ctrl+P
		r.keyPress(KeyEvent.VK_CONTROL );          
		r.keyPress(KeyEvent.VK_J );          
		r.keyRelease(KeyEvent.VK_CONTROL);
		r.keyRelease(KeyEvent.VK_J );

	}

	public static void robo_click_saveAs() throws Exception{
		Robot r = new Robot();
		// Ctrl+P
		r.keyPress(KeyEvent.VK_DOWN);  
		r.keyRelease(KeyEvent.VK_DOWN);  

		r.keyPress(KeyEvent.VK_ENTER);  
		r.keyRelease(KeyEvent.VK_ENTER);
		Thread.sleep(3000);
	}


	public static void robo_click_backspace() throws Exception{
		Robot r = new Robot();
		// Ctrl+P
		r.keyPress(KeyEvent.VK_BACK_SPACE);  
		r.keyRelease(KeyEvent.VK_BACK_SPACE);  


	}
	
	public static void openDownloadsWindow(WebDriver driver) throws AWTException{
		driver.switchTo().activeElement().sendKeys(Keys.CONTROL+"j");

	}
}
