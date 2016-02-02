package com.proj.util;




import java.util.List;

import atu.utils.windows.handler.WindowElement;
import atu.utils.windows.handler.WindowHandler;
import atu.utils.windows.handler.exceptions.WindowsHandlerException;

public class AutoIT_ActionsUtil {

	/**
	 * fetches the AutoIT handler
	 * @return
	 * @throws WindowsHandlerException
	 */
	public static WindowHandler getHandler() throws WindowsHandlerException{
		return  new WindowHandler();
	}

	/** 
	 * fetches the dialog/window element
	 * @param handler
	 * @param dialogName
	 * @return
	 * @throws WindowsHandlerException
	 */
	public static WindowElement getDialog(WindowHandler handler ,String dialogName) throws WindowsHandlerException{		  
		return handler.getWindowElement(dialogName);
	}

	/**
	 * find the element with name on the dialog/window
	 * @param handle
	 * @param windowElement
	 * @param name
	 * @return
	 * @throws WindowsHandlerException
	 */
	public static WindowElement elementByName(WindowHandler handle,WindowElement windowElement,String name) throws WindowsHandlerException{
		return handle.findElementByName(windowElement, name);
	}
	/**
	 * find the element with classname on the dialog/window
	 * @param handle
	 * @param windowElement
	 * @param className
	 * @return
	 * @throws WindowsHandlerException
	 */
	public static WindowElement elementByClassName(WindowHandler handle,WindowElement windowElement,String className) throws WindowsHandlerException{
		return handle.findElementByClassName(windowElement, className);
	}
	/**
	 * clicks on the element displayed on the dialog/window
	 * @param handle
	 * @param element
	 * @throws WindowsHandlerException
	 */
	public static void clickElement(WindowHandler handle,WindowElement element) throws WindowsHandlerException{
		handle.click(element);
	}

	public static void clear(WindowHandler handle,WindowElement element) throws WindowsHandlerException{
		handle.clearText(element);
	}

	public static void type(WindowHandler handle,WindowElement element,String text) throws WindowsHandlerException{
		handle.typeKeys(element,text);
	}
	/**
	 * fetches the list of elements by localized controltype
	 * @param handle
	 * @param windowElement
	 * @param controlType
	 * @return
	 * @throws WindowsHandlerException
	 */
	public static List<WindowElement> elementsByLocalizedControlType(WindowHandler handle,WindowElement windowElement,String controlType) throws WindowsHandlerException{
		List<WindowElement> elements= handle.findElementsByLocalizedControlType(windowElement, controlType);
		return elements;
	}

}
