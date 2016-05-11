package com.proj.library;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.WaitUtil;
import com.frw.wait.ImplicitWaitUtil;
import com.proj.Constants.Constants;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;

public class Driver extends TestBase{
	private static WebDriver driver=null;
	static WebDriver bak_firefox;
	static WebDriver bak_chrome;
	static WebDriver bak_ie;
	
	
	/**
	 * launches a specified browser
	 * @author khshaik
	 * @param browserType
	 * @return
	 */
	public static WebDriver launchBrowser(String browserType){
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		
		if (browserType.equalsIgnoreCase(Constants.browserfirefox)){
			isRemoteDriver=Constants_FRMWRK.False;
			isMobile=Constants_FRMWRK.False;
			logsObj.log("Browser to launch is firefox..");


			/*FirefoxProfile profile = new FirefoxProfile();
			profile.setEnableNativeEvents(true);
			profile.setPreference("plugins.update.notifyUser", false);
			//profile.setPreference("plugins.newAddons", false);
			
		    profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "atprojects.leapthought.co.nz");*/
			
			ProfilesIni allProfiles = new ProfilesIni();
			FirefoxProfile profile = allProfiles.getProfile("Selenium");
			

			driver = new FirefoxDriver(profile);			


			bak_firefox=driver;	
			driver.manage().window().maximize();
			logsObj.log("Browser launched is firefox and maximized..");

			logsObj.log("launching the "+browserType+" browser");

		}
		else if(browserType.equalsIgnoreCase(Constants.browserie)) {
			isRemoteDriver=Constants_FRMWRK.False;
			isMobile=Constants_FRMWRK.False;		
			logsObj.log("Browser to launch is ie..");
			System.setProperty("webdriver.ie.driver", (System.getProperty("user.dir")+"\\src\\com\\proj\\drivers\\IEDriverServer.exe"));
			logsObj.log("ie webdriver set for browser..");
			driver=new InternetExplorerDriver(cap);
			logsObj.log("ie driver initialized..");
			bak_ie=driver;
			driver.manage().window().maximize();					
			logsObj.log("launching the "+browserType+" browser");

		}

		else{
			isRemoteDriver=Constants_FRMWRK.False;
			isMobile=Constants_FRMWRK.False;
			System.setProperty("webdriver.chrome.driver", (System.getProperty("user.dir")+"\\src\\com\\proj\\drivers\\chromedriver.exe"));
			driver=new ChromeDriver(cap);
			bak_chrome=driver;
			driver.manage().window().maximize();
			//maximise();
			logsObj.log("launching the "+browserType+" browser");
		}				
		commonMethods.getBrowserVersion(driver);
		ImplicitWaitUtil.setImplicitWait(driver, Constants_TimeOuts.Implicit_Element_TimeOut);
		driver.manage().timeouts().pageLoadTimeout(Constants_TimeOuts.Page_MAX_TimeOut, TimeUnit.SECONDS);

		return driver;
	}
	
	
	
	/**
	 * Closes the browser
	 * @author khshaik 
	 * @param browserType
	 */
	public static void closeBrowser(WebDriver driver,String browserType){
		if (driver !=null){
			driver.close();
			logsObj.log("Closing the browser ");
		}


		if (browserType.equalsIgnoreCase(Constants.browserfirefox) && bak_firefox!=null){
			bak_firefox=null;
			driver.close();
			logsObj.log("Closing the launched "+browserType+" browser");
			return;
		}else if(browserType.equalsIgnoreCase(Constants.browserchrome) && bak_chrome!=null){
			bak_chrome=null;
			driver.close();
			logsObj.log("Closing the launched "+browserType+" browser");
			return;
		}else if(browserType.equalsIgnoreCase(Constants.browserie) && bak_ie!=null){
			bak_ie=null;
			logsObj.log("Closing the launched "+browserType+" browser");
			return;
		}

	}
	
	/**
	 * Closes the browser & webdriver instance
	 * @author Shaik
	 * @param driver
	 */
	public static void close(WebDriver driver,String browserType){
		if (!driver.toString().contains("null")){
			WaitUtil.pause(2);
			driver.quit();
			logsObj.log("Closing the browser ");

			WaitUtil.pause(2);

			if (!driver.toString().contains("null")){
				driver.quit();
				logsObj.log("Closing the browser for second attempt");
			}			
		}
		commonMethods.cleanProcess(driver, browserType);

	}

}
