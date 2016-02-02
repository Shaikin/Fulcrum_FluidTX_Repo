package com.proj.utilFulcrum;

import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.PageLoadWaitUtil;
import com.frw.util.WaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.ElementMethods;
import com.proj.library.commonMethods;
import com.report.reporter.Reporting;

public class WebTableUtil_old extends TestBase{

	/**
	 * Searches the required data in the table and report the result
	 * @author shaik
	 * @date Jan 13 2016
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param input
	 * @param colToSearch
	 * @return
	 */

	public static String searchforDataInColumn(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch){
		String flag=Constants_FRMWRK.False;
		String rowNumber="row";
		int rowCount=0;
		String colXpath="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colToSearchColumn coltToSearchColumn')]/descendant::div[contains(@class,'gridCellClass')]";

		colXpath=commonMethods.replaceString("containerName",colXpath,containerName);
		colXpath=commonMethods.replaceString("ToSearchColumn",colXpath,Integer.toString(colToSearch));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			final List<WebElement> columns = waitUntilAllVisible(driver,colXpath);			
			WaitUtil.pause(2);
			logsObj.log("Grid Column under search xpath-"+colXpath+"has "+columns.size()+" number of rows");

			for (WebElement row : columns) { 

				tableData.put(rowNumber+Integer.toString(rowCount), row.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+row.getText().trim()+" Input Data:-"+input.trim());

				if(row.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=row.getText();
					logsObj.log("Table-Expected Data-"+expted);
					flag=expted;
					Reporting.logStep(driver, Step, "Successfully able to list the required input data "+input+" in the table "+colXpath, Constants_FRMWRK.Pass);
					break;
				}
				rowCount++;
			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data"+input+" in the table "+colXpath+" due to error-->"+e, Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}

	/**
	 * Searches the required data in the table and will return the result 
	 * @author khshaik
	 * @date Nov 18 2014
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param input
	 * @param colToSearch
	 * @return input for availability , false for non-availability, error details for failure
	 */
	public static String searchforDataInColumn_WOR(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch){
		String flag=Constants_FRMWRK.False;
		String rowNumber="row";
		int rowCount=0;
		String colXpath="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";

		colXpath=commonMethods.replaceString("containerName",colXpath,containerName);
		colXpath=commonMethods.replaceString("ToSearchColumn",colXpath,Integer.toString(colToSearch));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			final List<WebElement> columns = waitUntilAllVisible(driver,colXpath);			
			WaitUtil.pause(2);
			logsObj.log("Grid Column under search xpath-"+colXpath+"has "+columns.size()+" number of rows");

			for (WebElement row : columns) { 

				tableData.put(rowNumber+Integer.toString(rowCount), row.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+row.getText().trim()+" Input Data:-"+input.trim());

				if(row.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=row.getText();
					logsObj.log("Table-Expected Data-"+expted);
					flag=expted;
					break;
				}
				rowCount++;
			}
		}catch(Exception e){
			flag="Could not locate the required input data"+input+" in the table "+colXpath+" due to error-->"+e;
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){			
			flag="Unable to list the required input data "+input+" in the table "+colXpath+ " the complete table data available is: "+tableData;
		}
		return flag;
	}

	/**
	 * Searches the data in the required column and clicks if able to locate
	 * @author khshaik
	 * @date Nov 13 2014
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param input
	 * @param colToSearch
	 * @return
	 * @throws Throwable 
	 */
	public static String searchforDataInColumnAndClick(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch) throws Throwable{
		String flag=Constants_FRMWRK.False;
		String rowNumber="row";
		int rowCount=0;
		String colXpath="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";

		colXpath=commonMethods.replaceString("containerName",colXpath,containerName);
		colXpath=commonMethods.replaceString("ToSearchColumn",colXpath,Integer.toString(colToSearch));


		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{

			final List<WebElement> columns = waitUntilAllVisible(driver,colXpath);

			WaitUtil.pause(2);
			logsObj.log("Grid Column under search xpath-"+colXpath+"has "+columns.size()+" number of rows");

			for (WebElement row : columns) { 
				tableData.put(rowNumber+Integer.toString(rowCount), row.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+row.getText().trim()+" Input Data:-"+input.trim());

				if(row.getText().trim().equals(input.trim())){
					WaitUtil.pause(1000L);
					String expted=row.getText();
					logsObj.log("Table-Expected Data-"+expted);

					try{

						int counter=0;

						while (counter< 5){
							try{

								row.click();
								PageLoadWaitUtil.waitForAjax(driver);
								PageLoadWaitUtil.waitForPageToLoad(driver);
								//WorkaroundsSelenium.sendControlToElement(browserName, Constants.browserie, row);							

								logsObj.log("searchforDataAndClick:Successfully click the required column in the table");
								flag=expted;
								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);

						}

						Reporting.logStep(driver, Step, "Successfully clicked on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath, Constants_FRMWRK.Pass);

					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to click the required column data after successfully matching the expected data in the table"+colXpath+" due to error-->"+e, Constants_FRMWRK.Fail);

					} 	

					flag=Constants_FRMWRK.True;
					break;
				}
				rowCount++;
			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data"+input+" in the table "+colXpath+" due to error-->"+e, Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}
	


	/**
	 * Searches the data in a particular column and enters on a particular column for success result
	 * @author khshaik
	 * @date Dec 13 2014
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param input
	 * @param colToSearch
	 * @param actionPerformCol
	 * @param inputData
	 * @return
	 */


	public static String searchforDataInColumnAndEnter(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch,int actionPerformCol,String inputData){
		String flag=Constants_FRMWRK.False;

		String rowNumber="row";
		int rowCount=0;

		String colXpath_search="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";
		String colXpath_enter="//div[@class='containerName']/descendant::div[@class='ngCell colactionPerformCol coltactionPerformCol']/descendant::input";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));
		colXpath_enter=commonMethods.replaceString("containerName",colXpath_enter,containerName);
		colXpath_enter=commonMethods.replaceString("actionPerformCol",colXpath_enter,Integer.toString(actionPerformCol));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_enter = waitUntilAllVisible(driver,colXpath_enter);

			WaitUtil.pause(2);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+input.trim());

				if(rowSearch.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=rowSearch.getText();					
					logsObj.log("Table-Expected Data-"+expted);
					int counter=0;

					try{


						while (counter< 5){
							try{
								WebElement row_enter=rows_enter.get(rowCount);
								WaitUtil.pause(1);
								row_enter.clear();
								row_enter.sendKeys(inputData);
								flag=Constants_FRMWRK.True;
								Reporting.logStep(driver, Step, "Successfully Entered data"+inputData+" on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Pass);

								logsObj.log("searchforDataInColumnAndEnter:Successfully entred data"+inputData+" the required column in the table") ;

								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);

						}

					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to Enter the data"+inputData+" the required column data after successfully matching the expected data in the table"+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);
						System.out.println("Unable to enter data in the required column data after successfully matching the expected data in the table"+colXpath_search);
					} 	

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data "+input+" in the table "+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Search data in a column and validates
	 * @author khshaik
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param input
	 * @param colToSearch
	 * @param actionPerformCol
	 * @param validateData
	 * @return
	 */
	public static String searchforDataInColumnAndValidateEnterValue(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch,int actionPerformCol,String validateData){
		String flag=Constants_FRMWRK.False;

		String rowNumber="row";
		int rowCount=0;

		String colXpath_search="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";
		String colXpath_enter="//div[@class='containerName']/descendant::div[@class='ngCell colactionPerformCol coltactionPerformCol']/descendant::input";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));
		colXpath_enter=commonMethods.replaceString("containerName",colXpath_enter,containerName);
		colXpath_enter=commonMethods.replaceString("actionPerformCol",colXpath_enter,Integer.toString(actionPerformCol));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_enter = waitUntilAllVisible(driver,colXpath_enter);

			WaitUtil.pause(2);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+input.trim());

				if(rowSearch.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=rowSearch.getText();					
					logsObj.log("Table-Expected Data-"+expted);
					int counter=0;

					try{


						while (counter< 5){
							try{
								WebElement row_enter=rows_enter.get(rowCount);
								WaitUtil.pause(1);								
								String actualData=row_enter.getAttribute("value");
								if(validateData.equals(actualData)){
									flag=Constants_FRMWRK.True;
									Reporting.logStep(driver, Step, "Successfully validated the entered data"+validateData+" on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
									logsObj.log("searchforDataInColumnAndEnter:Successfully validated the entred data"+validateData+" the required column in the table") ;

								}

								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);

						}

					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to validate the Enter the data"+validateData+" the required column data "+input +"after successfully matching the expected data in the table"+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);
						logsObj.logError( "Unable to validate the Enter the data"+validateData+" the required column data "+input +"after successfully matching the expected data in the table"+colXpath_search+" due to error", e);
					} 	

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data "+input+" in the table "+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Wait for all elements to be visible
	 * @author khshaik
	 * @param driver
	 * @param elements
	 */
	public static void waitForAllVisibleElements(WebDriver driver, final List <WebElement> elements){
		logsObj.log("Inside waitForAllVisibleElements");

		WebDriverWait wait= new WebDriverWait(driver, Constants_TimeOuts.Element_TimeOut);

		wait.until(ExpectedConditions.visibilityOfAllElements(elements));

		logsObj.log("Done waitForAllVisibleElements");

	}

	/**
	 * Waits for the elements under test to be visible (max timeout is 10 secs)
	 * @author khshaik
	 * @date Nov 18 2014
	 * @param driver
	 * @param colXpath
	 * @return
	 */
	private static List<WebElement> waitUntilAllVisible(WebDriver driver, String colXpath){
		int counter =1;
		List<WebElement> rows_search_previous =null;
		List<WebElement> rows_search_current = null;
		logsObj.log("Inside waitUntilAllVisible");

		while (counter <=10){
			rows_search_previous = driver.findElements(By.xpath(colXpath)); 
			WaitUtil.pause(1);
			rows_search_current = driver.findElements(By.xpath(colXpath)); 

			if(rows_search_previous.size()==rows_search_current.size()){
				logsObj.log("Visibility of elements Previous-Current:"+rows_search_previous.size()+"-"+rows_search_current.size());
				break;
			}
			counter++;
		}

		if(counter>10){
			logsObj.log("All elements are not Visibility  of elements yet after 10 secs Previous-Current"+rows_search_previous.size()+"-"+rows_search_current.size());
		}

		return rows_search_current;

	}



	/**
	 * Clicks on the element listed in the left navigation frame
	 * @author khshaik
	 * @date Nov 26 2014
	 * @param driver
	 * @param workFlow
	 * @param elementUnderSearch
	 * @return
	 */
	public static String Click_ListElement(WebDriver driver,String workFlow,String elementUnderSearch ){
		String flag=Constants_FRMWRK.False;
		String actual = null;
		List <WebElement> elements=null;
		int count=0;
		int elementCount=0;
		int elementClick=0;
		String colXpath="//div[@id='treeFullView']/ul/li";
		elements=FetchWebElement.visibleElementsList(driver,  Constants_FRMWRK.FindElementByXPATH, colXpath);
		if(elements==null){
			logsObj.log("Click_ListElement:-Visibile elements are zero");
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, "Verification of element in the left navigation", " Unable to locate the Element "+elementUnderSearch+" as there are no elements fetched with locator-"+colXpath, Constants_FRMWRK.Fail);
			return flag;
		}else{
			logsObj.log("Click_ListElement:-Visibile elements are "+elements.size());			
		}

		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);

		for (WebElement element:elements){
			try{
				actual=element.getAttribute("locname");
			}catch(StaleElementReferenceException stl){
				logsObj.log("Click_ListElement:Element while fetching attribute is stale so recovering again and counter-"+count);	
				//WaitUtil.pause(1);
				if(count<=5){
					count=count+1;
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
					elements=FetchWebElement.visibleElementsList(driver,  Constants_FRMWRK.FindElementByXPATH, colXpath);
					actual=elements.get(elementCount).getAttribute("locname");
					element=elements.get(elementCount);
				}

			}
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
			logsObj.log("Click_ListElement:The actual is "+actual +" and search is "+elementUnderSearch);
			if(actual.equals(elementUnderSearch)){
				try{
					logsObj.log("The actual is "+actual +" matches with expected-"+elementUnderSearch+" next action is click");
					element.click();
					flag=actual;
					Reporting.logStep(driver, workFlow+"Verification of element in the left navigation", "Element "+elementUnderSearch+" is located and clicked", Constants_FRMWRK.Pass);
					break;
				}
				catch (StaleElementReferenceException stl){
					logsObj.log("Click_ListElement:Element stale  while clicking after matching the text ,so recovering again and counter-"+elementClick);
					if(elementClick<=5){
						elementClick=elementClick+1;
						elements=FetchWebElement.visibleElementsList(driver,  Constants_FRMWRK.FindElementByXPATH, colXpath);
						element=elements.get(elementCount);
						element.click();
						flag=actual;
						Reporting.logStep(driver, workFlow+"Verification of element in the left navigation after recovering stale", "Element "+elementUnderSearch+" is located and clicked", Constants_FRMWRK.Warning);
						break;
					}

				}

				catch(Throwable t){
					isTestPass=Constants_FRMWRK.FalseB;
					logsObj.logError("fetchListElementsAndClick:-Able to locate the element under search-"+elementUnderSearch+" but unable to click due to error-", t);
					Reporting.logStep(driver, workFlow+"Verification of element in the left navigation-Error", "Element "+elementUnderSearch+" is located at "+colXpath+" but unable to click due to error-"+t, Constants_FRMWRK.Pass);
				}

			}
			elementCount=elementCount+1;
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, "Verification of element in the left navigation", "Element "+elementUnderSearch+" is not located at "+colXpath, Constants_FRMWRK.Fail);
		}

		return flag;
	}
	/**
	 * Clicks on the element listed in the left navigation frame
	 * @author khshaik
	 * @date Nov 27 2014
	 * @param driver
	 * @param workFlow
	 * @param elementUnderSearch
	 * @return elementUnderSearch for availability , false for non-availability, error details for failure
	 */
	public static String Click_ListElement_WOR(WebDriver driver,String workFlow,String elementUnderSearch ){
		String flag=Constants_FRMWRK.False;
		String actual=null;
		List <WebElement> elements=null;
		int count=0;
		int elementCount=0;
		int elementClick=0;
		String colXpath="//div[@id='treeFullView']/ul/li";
		elements=FetchWebElement.visibleElementsList(driver,  Constants_FRMWRK.FindElementByXPATH, colXpath);
		WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
		if(elements==null){
			logsObj.log("Click_ListElement_WOR:-Visibile elements are zero");
			isTestPass=Constants_FRMWRK.FalseB;
			flag="Click_ListElement_WOR:-Unable to locate the Element "+elementUnderSearch+" as there are no elements fetched with locator-"+colXpath;
			return flag;
		}else{
			logsObj.log("Click_ListElement_WOR:-Visibile elements are "+elements.size());			
		}


		for (WebElement element:elements){
			try{
				actual=element.getAttribute("locname");
			}catch(StaleElementReferenceException stl){
				logsObj.log("Click_ListElement_WOR:Element while fetching attribute is stale so recovering again and counter-"+count);	
				if(count<=5){
					count=count+1;
					WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
					elements=FetchWebElement.visibleElementsList(driver,  Constants_FRMWRK.FindElementByXPATH, colXpath);
					actual=elements.get(elementCount).getAttribute("locname");
					element=elements.get(elementCount);
				}
			}
			if(actual.equals(elementUnderSearch)){
				try{
					element.click();
					flag=actual;					
					break;

				}

				catch (StaleElementReferenceException stl){
					logsObj.log("Click_ListElement_WOR:Element stale  while clicking after matching the text ,so recovering again and counter-"+elementClick);
					if(elementClick<=5){
						elementClick=elementClick+1;
						elements=FetchWebElement.visibleElementsList(driver,  Constants_FRMWRK.FindElementByXPATH, colXpath);
						element=elements.get(elementCount);
						element.click();
						flag=actual;
						break;
					}

				}

				catch(Throwable t){
					isTestPass=Constants_FRMWRK.FalseB;
					logsObj.logError("Click_ListElement_WOR:-Able to locate the element under search-"+elementUnderSearch+" but unable to click due to error-", t);
					flag="Click_ListElement_WOR:-Able to locate the element under search-"+elementUnderSearch+" but unable to click due to error-"+ t;
				}

			}
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			flag="Verification of element in the left navigation Element "+elementUnderSearch+" is not located at "+colXpath;
		}

		return flag;
	}

	/**Searches the data in the required column and selects an item from the drop-down if able to locate
	 * @author khshaik
	 * @date Jan 6 2014
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param input
	 * @param colToSearch
	 * @param actionPerformCol
	 * @param dropdownItem
	 * @return
	 */
	public static String searchforDataInColumnAndDoubleClickToSelectDropdownItem(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch,int actionPerformCol,String dropdownItem){
		String flag=Constants_FRMWRK.False;
		String doubleClickflag=Constants_FRMWRK.False;

		String rowNumber="row";
		int rowCount=0;

		//String colXpath_search="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";
		//String colXpath_doubleClick="//div[@class='containerName']/descendant::div[@class='ngCell colactionPerformCol coltactionPerformCol']/descendant::span[@class='resemp']";
		//String colXpath_dropdown="//div[@class='containerName']/descendant::div[@class='ngCell colactionPerformCol coltactionPerformCol']/descendant::select";


		String colXpath_search="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colToSearchColumn coltToSearchColumn')]/descendant::div[contains(@class,'gridCellClass')]";
		String colXpath_doubleClick="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colactionPerformCol coltactionPerformCol')]/descendant::span[contains(@class,'resemp')]";
		String colXpath_dropdown="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colactionPerformCol coltactionPerformCol')]/descendant::select";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_dropdown=commonMethods.replaceString("containerName",colXpath_dropdown,containerName);
		colXpath_dropdown=commonMethods.replaceString("actionPerformCol",colXpath_dropdown,Integer.toString(actionPerformCol));

		colXpath_doubleClick=commonMethods.replaceString("containerName",colXpath_doubleClick,containerName);
		colXpath_doubleClick=commonMethods.replaceString("actionPerformCol",colXpath_doubleClick,Integer.toString(actionPerformCol));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_doubleClick = waitUntilAllVisible(driver,colXpath_doubleClick);

			WaitUtil.pause(2);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+input.trim());

				if(rowSearch.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=rowSearch.getText();
					logsObj.log("Table-Expected Data-"+expted);
					int counter=0;

					try{


						while (counter< 5){
							try{
								WebElement row_doubleClick=rows_doubleClick.get(rowCount);
								WaitUtil.pause(1);								

								Actions action = new Actions(driver);
								action.doubleClick(row_doubleClick).build().perform();								

								logsObj.log("searchforDataAndDoubleClick:Successfully Double Clicked the required column in the table") ;
								doubleClickflag=Constants_FRMWRK.True;
								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);

						}

					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to Double Click the required column data after successfully matching the expected data in the table"+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);

					} 	
					if(doubleClickflag.equalsIgnoreCase(Constants_FRMWRK.True)){

						Reporting.logStep(driver, Step, "Successfully Double Click on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
						final List<WebElement> rows_dropdown = waitUntilAllVisible(driver,colXpath_dropdown);

						Select dropdown=FetchWebElement.waitForDropdownItems(driver, rows_dropdown.get(0), Constants_TimeOuts.Element_TimeOut);

						List<WebElement> options =dropdown.getOptions();

						if(options.size()==0){
							Reporting.logStep(driver, Step, "Successfully Double Click on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search+" but there are no items in the dropdown", Constants_FRMWRK.Fail);
							return flag;
						}

						try{
							dropdown.selectByVisibleText(dropdownItem);						
							Reporting.logStep(driver, Step,    " After successfully double click and an item "+dropdownItem+" is selected", Constants_FRMWRK.Pass);
							flag=dropdownItem;											       							       					
							break;
						}catch (Exception e){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,    " After successfully double click unable to select item "+input+" due to error --> "+e.getMessage(), Constants_FRMWRK.Fail);
							break;
						}


					}else{
						Reporting.logStep(driver, Step, "Could not perform Double Click action after selection on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Fail);
						flag=Constants_FRMWRK.False;
					}

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data "+input+" in the table "+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Searches the drop down data in the required column and selects an item from the drop-down if able to locate
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param input
	 * @param colToSearch
	 * @param actionPerformCol
	 * @param dropdownItem
	 * @return
	 */
	public static String searchforDropdownDataInColumnAndDoubleClickToSelectDropdownItem(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch,int actionPerformCol,String dropdownItem){
		String flag=Constants_FRMWRK.False;
		String doubleClickflag=Constants_FRMWRK.False;

		String rowNumber="row";
		int rowCount=0;

		String colXpath_search="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colToSearchColumn coltToSearchColumn')]/descendant::div[contains(@class,'restext')]";
		String colXpath_doubleClick="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colactionPerformCol coltactionPerformCol')]/descendant::span[contains(@class,'resemp')]";
		String colXpath_dropdown="//div[@class='containerName']/descendant::div[contains(@class,'ngCell colactionPerformCol coltactionPerformCol')]/descendant::select";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_dropdown=commonMethods.replaceString("containerName",colXpath_dropdown,containerName);
		colXpath_dropdown=commonMethods.replaceString("actionPerformCol",colXpath_dropdown,Integer.toString(actionPerformCol));

		colXpath_doubleClick=commonMethods.replaceString("containerName",colXpath_doubleClick,containerName);
		colXpath_doubleClick=commonMethods.replaceString("actionPerformCol",colXpath_doubleClick,Integer.toString(actionPerformCol));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_doubleClick = waitUntilAllVisible(driver,colXpath_doubleClick);

			WaitUtil.pause(2);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+input.trim());

				if(rowSearch.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=rowSearch.getText();
					logsObj.log("Table-Expected Data-"+expted);
					int counter=0;

					try{


						while (counter< 5){
							try{
								WebElement row_doubleClick=rows_doubleClick.get(rowCount);
								WaitUtil.pause(1);								

								Actions action = new Actions(driver);
								action.doubleClick(row_doubleClick).build().perform();								

								logsObj.log("searchforDataAndDoubleClick:Successfully Double Clicked the required column in the table") ;
								doubleClickflag=Constants_FRMWRK.True;
								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);

						}

					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to Double Click the required column data after successfully matching the expected data in the table"+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);

					} 	
					if(doubleClickflag.equalsIgnoreCase(Constants_FRMWRK.True)){

						Reporting.logStep(driver, Step, "Successfully Double Click on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
						final List<WebElement> rows_dropdown = waitUntilAllVisible(driver,colXpath_dropdown);

						Select dropdown=FetchWebElement.waitForDropdownItems(driver, rows_dropdown.get(0), Constants_TimeOuts.Element_TimeOut);

						List<WebElement> options =dropdown.getOptions();

						if(options.size()==0){
							Reporting.logStep(driver, Step, "Successfully Double Click on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search+" but there are no items in the dropdown", Constants_FRMWRK.Fail);
							return flag;
						}

						try{
							dropdown.selectByVisibleText(dropdownItem);						
							Reporting.logStep(driver, Step,    " After successfully double click and an item "+dropdownItem+" is selected", Constants_FRMWRK.Pass);
							flag=dropdownItem;											       							       					
							break;
						}catch (Exception e){
							isTestPass=Constants_FRMWRK.FalseB;
							Reporting.logStep(driver, refID, Step,    " After successfully double click unable to select item "+input+" due to error --> "+e.getMessage(), Constants_FRMWRK.Fail);
							break;
						}


					}else{
						Reporting.logStep(driver, Step, "Could not perform Double Click action after selection on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Fail);
						flag=Constants_FRMWRK.False;
					}

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data "+input+" in the table "+colXpath_search+" due to error-->"+e, Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	public static Hashtable<String,String> getDatafromColumn(WebDriver driver,String testcaseName,String Step,String containerName,int colToSearch){
		String rowNumber="row";
		int rowCount=0;
		String colXpath="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";

		colXpath=commonMethods.replaceString("containerName",colXpath,containerName);
		colXpath=commonMethods.replaceString("ToSearchColumn",colXpath,Integer.toString(colToSearch));
		Hashtable<String,String> tableData=new Hashtable<String,String>();

		try{
			final List<WebElement> columns = waitUntilAllVisible(driver,colXpath);			
			WaitUtil.pause(2);
			logsObj.log("getDatafromColumn:Grid Column under search xpath-"+colXpath+"has "+columns.size()+" number of rows");
			
			if(columns.size()!=0){
				for (WebElement row : columns) { 
					tableData.put(rowNumber+Integer.toString(rowCount), row.getText());								
					rowCount++;
				}
			}else{
				Reporting.logStep(driver, "Collect data from the table/grid", "There no records displayed for table/grid-"+colXpath, Constants_FRMWRK.Fail);
			}
		}catch(Exception e){
			logsObj.logError("getDatafromColumn: Could not collect the required  data in the table "+colXpath+" due to error-->",e);
			Reporting.logStep(driver, "Collect data from the table/grid", "Could not collect the required  data in the table "+colXpath+" due to error-->"+e, Constants_FRMWRK.Fail);
			return tableData;		
		}

		
		return tableData;
	}


}
