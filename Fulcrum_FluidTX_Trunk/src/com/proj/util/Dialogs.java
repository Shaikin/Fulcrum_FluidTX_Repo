package com.proj.util;


import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import atu.utils.windows.handler.WindowElement;
import atu.utils.windows.handler.WindowHandler;
import atu.utils.windows.handler.exceptions.WindowsHandlerException;

import com.frw.Constants.Constants_FRMWRK;
import com.report.reporter.Reporting;

public class Dialogs {
	
	public static void userAuthentication(String browser,String url,String username,String password) throws WindowsHandlerException, InterruptedException{
		WindowElement windowElement;
		WindowElement authwindowElement = null;
		AutoITUtil.loadJocobDLL();
		Thread.sleep(3000);
		
		WindowHandler handle=AutoIT_ActionsUtil.getHandler();
		
		if(browser.equalsIgnoreCase("ie")){
			 windowElement=AutoIT_ActionsUtil.getDialog(handle,"WebDriver - Internet Explorer");
			 authwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Windows Security");
			 user_auth_ie(handle, authwindowElement, username, password);
		}
		else if(browser.equalsIgnoreCase("chrome")){
			 windowElement=AutoIT_ActionsUtil.getDialog(handle,url+" - Google Chrome");
			 authwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Authentication Required");
		}
		else if (browser.equalsIgnoreCase("firefox")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,"Mozilla Firefox Start Page - Mozilla Firefox");
			 authwindowElement=AutoIT_ActionsUtil.elementByName(handle, windowElement, "Authentication Required");
		}

	
	/*	List<WindowElement> editItems=AutoIT_ActionsUtil.elementsByLocalizedControlType(handle, authwindowElement, "edit");
		WindowElement usernameElement=editItems.get(0);
		AutoIT_ActionsUtil.type(handle, usernameElement, username);
		
		AutoIT_ActionsUtil.clear(handle, editItems.get(2));		
		AutoIT_ActionsUtil.type(handle, editItems.get(2), password);
	
		List<WindowElement> buttonItems=AutoIT_ActionsUtil.elementsByLocalizedControlType(handle, authwindowElement, "button");
		AutoIT_ActionsUtil.clickElement(handle, buttonItems.get(0));	
	*/
	
	}
	
	
	public static void userAuth_robo(String domain,String userName,String password) throws Exception {
        
        //wait - increase this wait period if required
        Thread.sleep(5000);
        
        //create robot for keyboard operations
        Robot rb = new Robot();

        //Enter user name by ctrl-v
        StringSelection username = new StringSelection(domain+"\\"+userName);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(username, null);    
        Reporting.logStep("Login - User Name", "Successfully entered "+userName, Constants_FRMWRK.Pass);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);

        //tab to password entry field
        rb.keyPress(KeyEvent.VK_TAB);
        rb.keyRelease(KeyEvent.VK_TAB);
        Thread.sleep(2000);

        //Enter password by ctrl-v
        StringSelection pwd = new StringSelection(password);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(pwd, null);
        Reporting.logStep( "Login - Password", "Successfully entered "+password, Constants_FRMWRK.Pass);
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_V);
        rb.keyRelease(KeyEvent.VK_CONTROL);
        
        //press enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);
        
        //wait
        Thread.sleep(5000);
    }   
	
	
	public static void browse(String browser,String filePath,String dialogName) throws WindowsHandlerException, InterruptedException{
		WindowElement windowElement = null;
		 WindowElement uploadElement=null;
		AutoITUtil.loadJocobDLL();
		Thread.sleep(3000);
		
		WindowHandler handle=AutoIT_ActionsUtil.getHandler();
		
		if(browser.equalsIgnoreCase("ie")){// dialogname reg expression for all browsers
			 windowElement=AutoIT_ActionsUtil.getDialog(handle,dialogName+" - Windows Internet Explorer");
			uploadElement = handle.findElementByName(windowElement,"Choose File to Upload");
		}else if (browser.equalsIgnoreCase("chrome")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,dialogName+" - Google Chrome"); 
			uploadElement = handle.findElementByName(windowElement,"Open");
		}
		else if(browser.equalsIgnoreCase("firefox")){
			windowElement=AutoIT_ActionsUtil.getDialog(handle,dialogName+" - Mozilla Firefox");
			uploadElement = handle.findElementByName(windowElement,"File Upload");
		}
		
		WindowElement fileNamePath = handle.findElementByNameAndClassName(
				uploadElement, "File name:", "Edit");
		handle.typeKeys(fileNamePath, filePath);
		
		WindowElement openButton = handle.findElementByName(uploadElement,
				"Open");
		handle.click(openButton);
	}
	
	private static void user_auth_ie(WindowHandler handle,WindowElement authwindowElement,String username,String password) throws WindowsHandlerException{
		WindowElement userNameElement=AutoIT_ActionsUtil.elementByName(handle, authwindowElement, "User name");
		AutoIT_ActionsUtil.clear(handle, userNameElement);		
		AutoIT_ActionsUtil.type(handle, userNameElement, username);
		
		WindowElement passwordElement=AutoIT_ActionsUtil.elementByName(handle, authwindowElement, "Password");
		AutoIT_ActionsUtil.clear(handle, passwordElement);		
		AutoIT_ActionsUtil.type(handle, passwordElement, password);
		
		WindowElement button_OK=AutoIT_ActionsUtil.elementByName(handle, authwindowElement, "OK");
		AutoIT_ActionsUtil.clickElement(handle, button_OK);	
	}
}
