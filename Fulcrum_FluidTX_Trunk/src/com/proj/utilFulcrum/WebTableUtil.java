package com.proj.utilFulcrum;

import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.FetchWebElement;
import com.frw.util.WaitUtil;
import com.frw.wait.ExplicitWaitUtil;
import com.proj.Constants.Constants_TimeOuts;
import com.proj.base.TestBase;
import com.proj.library.ElementMethods;
import com.proj.library.commonMethods;
import com.proj.objectRepository.ObjRepository;
import com.report.reporter.Reporting;

public class WebTableUtil extends TestBase{

	/**
	 * Waits for the elements under test to be visible (max timeout is 10 secs)
	 * @author shaik
	 * @date Jan 13 2016
	 * @param driver
	 * @param colXpath
	 * @return
	 */
	public static List<WebElement> waitUntilAllVisible(WebDriver driver, String colXpath){
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
	 * Searches the data in a particular column and clicks on a particular column with check box for success result
	 * @author shaik
	 * @date Jan 13 2016
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param input
	 * @param colToSearch
	 * @param actionPerformCol
	 * @return
	 */

	public static String searchforDataInsearchColumnAndTickInactionableColumn(WebDriver driver,String testcaseName,String Step,String containerName,String input,int colToSearch,int actionPerformCol){
		String flag=Constants_FRMWRK.False;

		String rowNumber="row";
		int rowCount=0;

		WebElement webtableElement=ElementMethods.fetchElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']");
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}
		//String colXpath_search="//div[@class='containerName']/descendant::div[@class='ngCell colToSearchColumn coltToSearchColumn']/descendant::div[@class='gridCellClass ng-scope ng-binding']";
		//String colXpath_tick="//div[@class='containerName']/descendant::div[@class='ngCell colactionPerformCol coltactionPerformCol']/descendant::input[@type='checkbox']";


		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";
		String colXpath_tick="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]/div[@role='checkbox']";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_tick=commonMethods.replaceString("containerName",colXpath_tick,containerName);
		colXpath_tick=commonMethods.replaceString("actionPerformCol",colXpath_tick,Integer.toString(actionPerformCol));

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_tick = waitUntilAllVisible(driver,colXpath_tick);

			WaitUtil.pause(1);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+input.trim());

				if(rowSearch.getText().trim().equals(input.trim())){
					WaitUtil.pause(1);
					String expted=rowSearch.getText();
					logsObj.log("Table-Expected Data-"+expted);
					int counter=0;
					boolean selected;
					try{


						while (counter< 5){
							try{
								WebElement row_tick=rows_tick.get(rowCount);
								WaitUtil.pause(1);								
								selected=row_tick.isSelected();
								WaitUtil.pause(1);
								if(selected==false){
									logsObj.log("Grid Checkbox after click action is not tick");
									WaitUtil.pause(1);
									row_tick.click();
									logsObj.log("Checkbox is selected for the record");
									/*if(browserName.equalsIgnoreCase(Constants.browserie)){
										WorkaroundsSelenium.sendControlToElement(browserName, Constants.browserie, row_tick);
										row_tick.click();
										logsObj.log("Grid Checkbox is forced to send keys to select for ie browser");
									}else{
										row_tick.click();
										logsObj.log("Grid Checkbox is clicked to select for non ie browser");
									}*/


									selected=row_tick.isSelected();
								}else{
									logsObj.log("Grid Checkbox status is already "+selected);
								}
								logsObj.log("Successfully Tick the required column in the table") ;

								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);

						}


					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to Tick the required column data after successfully matching the expected data in the table"+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

					} 	
					if(selected=Constants_FRMWRK.TrueB){
						flag=Constants_FRMWRK.True;
						Reporting.logStep(driver, Step, "Successfully Ticked on the required column data after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
					}else{
						Reporting.logStep(driver, Step, "Unable to tick the Checkbox after matching the expected data:-"+input+" with actual data:-"+expted+" in the table "+colXpath_search, Constants_FRMWRK.Fail);
						flag=Constants_FRMWRK.False;
					}

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required input data "+input+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}

		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required input data "+input+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Searches and validate the data in the respective columns provided
	 * @author shaik
	 * @date Jan 14 2016
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param searchData
	 * @param actionData
	 * @param colToSearch
	 * @param actionPerformCol
	 * @return
	 */


	public static String searchforDataInsearchColumnAndValidateDataInactionableColumn(WebDriver driver,String testcaseName,String Step,String containerName,String searchData,String actionData,int colToSearch,int actionPerformCol){
		String flag=Constants_FRMWRK.False;		
		String actual_actionData = "";
		String rowNumber="row";
		int rowCount=0;
		boolean retrieved=Constants_FRMWRK.FalseB;
		
		WebElement webtableElement=FetchWebElement.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']",Constants_TimeOuts.Element_TimeOut);
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";
		String colXpath_action="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_action=commonMethods.replaceString("containerName",colXpath_action,containerName);
		colXpath_action=commonMethods.replaceString("actionPerformCol",colXpath_action,Integer.toString(actionPerformCol));

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_action = waitUntilAllVisible(driver,colXpath_action);

			WaitUtil.pause(100L);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

				if(rowSearch.getText().trim().equals(searchData.trim())){
					WaitUtil.pause(100L);					
					int counter=0;
					
					try{							
						while (counter< 5){
							try{
								actual_actionData=rows_action.get(rowCount).getText();
								WaitUtil.pause(100L);							
								if(actual_actionData.equals(actionData)){
									retrieved=Constants_FRMWRK.TrueB;
								}
								logsObj.log("searchforDataInColumnAndValidateDataInAcionableColumn:Successfully retrieved the text from required column in the table") ;

								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(Constants_TimeOuts.Save_TimeOut);

						}


					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to retrieve data from the required column data after successfully matching the expected data from search coloumn in the table"+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

					} 	
					if(retrieved==Constants_FRMWRK.TrueB){
						flag=Constants_FRMWRK.True;
						Reporting.logStep(driver, Step, "Successfully retrieved data from the required column and  the expected data:-"+actionData+" matches with actual data:-"+actual_actionData+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
					}
					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}
		if(retrieved==Constants_FRMWRK.FalseB){
			Reporting.logStep(driver, Step, "Retrieved data from the required column but the expected data:-"+actionData+" does not match with actual data:-"+actual_actionData+" in the table "+colXpath_search+" for the search record "+searchData, Constants_FRMWRK.Fail);			
		}
		else if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	public static String searchforDataInsearchColumnAndValidateDataInactionableLinkColumn(WebDriver driver,String testcaseName,String Step,String containerName,String searchData,String actionData,int colToSearch,int actionPerformCol){
		String flag=Constants_FRMWRK.False;		
		String actual_actionData = "";
		String rowNumber="row";
		int rowCount=0;
		boolean retrieved=Constants_FRMWRK.FalseB;

		WebElement webtableElement=ElementMethods.fetchElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']");
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";
		String colXpath_action="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]/a";

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_action=commonMethods.replaceString("containerName",colXpath_action,containerName);
		colXpath_action=commonMethods.replaceString("actionPerformCol",colXpath_action,Integer.toString(actionPerformCol));

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_action = waitUntilAllVisible(driver,colXpath_action);

			WaitUtil.pause(100L);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

				if(rowSearch.getText().trim().equals(searchData.trim())){
					WaitUtil.pause(100L);					
					int counter=0;
					try{							
						while (counter< 5){
							try{
								actual_actionData=rows_action.get(rowCount).getText();
								WaitUtil.pause(100L);								
								if(actual_actionData.equals(actionData)){
									retrieved=Constants_FRMWRK.TrueB;
								}
								logsObj.log("searchforDataInColumnAndValidateDataInAcionableColumn:Successfully retrieved the text from required column in the table") ;

								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(100L);

						}


					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to retrieve data from the required column data after successfully matching the expected data from search coloumn in the table"+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

					} 	
					if(retrieved==Constants_FRMWRK.TrueB){
						flag=Constants_FRMWRK.True;
						Reporting.logStep(driver, Step, "Successfully retrieved data from the required column and  the expected data:-"+actionData+" matches with actual data:-"+actual_actionData+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
					}

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}
		if(retrieved==Constants_FRMWRK.FalseB){
			Reporting.logStep(driver, Step, "Retrieved data from the required column but the expected data:-"+actionData+" does not match with actual data:-"+actual_actionData+" in the table "+colXpath_search+" for the search record "+searchData, Constants_FRMWRK.Fail);			
		}
		else if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}
	/**
	 * Searches and validate the data in the respective columns provided and clicks on required column
	 * @author shaikka
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param searchData
	 * @param actionColumnType
	 * @param actionData
	 * @param colToSearch
	 * @param actionPerformCol
	 * @return
	 */
	public static String searchforDataInsearchColumnAndClickInactionableLinkColumn(WebDriver driver,String testcaseName,String Step,String containerName,String searchData,String actionColumnType,String actionData,int colToSearch,int actionPerformCol){
		String flag=Constants_FRMWRK.False;
		String colXpath_action;
		String rowNumber="row";
		String pageNumber="page";
		int pageCount=1;
		String actual_actionData = "";
		boolean clicked=Constants_FRMWRK.FalseB;
		boolean isNextDisplayed;

		WebElement webtableElement=ElementMethods.fetchElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']");
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";
		if(actionColumnType.equalsIgnoreCase("LINK")){
			colXpath_action="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]/a";
		}else{
			colXpath_action="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]";
		}


		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));

		colXpath_action=commonMethods.replaceString("containerName",colXpath_action,containerName);
		colXpath_action=commonMethods.replaceString("actionPerformCol",colXpath_action,Integer.toString(actionPerformCol));

		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();
//
do{
	int rowCount=0;

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_action = waitUntilAllVisible(driver,colXpath_action);

			WaitUtil.pause(100L);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(pageNumber+Integer.toString(pageCount)+" "+rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- "+"Page-"+pageCount+" ,RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

				if(rowSearch.getText().trim().equals(searchData.trim())){
					WaitUtil.pause(100L);				
					int counter=0;
					
					try{							
						while (counter< 5){
							try{
								rows_action.get(rowCount).click();
								WaitUtil.pause(1);
								clicked=Constants_FRMWRK.TrueB;
								logsObj.log("searchforDataInColumnAndClickInAcionableColumn:Successfully clicked the record from required column in the table") ;
								break;
							}catch(StaleElementReferenceException ex){
								counter=counter+1;
								WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
							}
							WaitUtil.pause(100L);

						}


					}catch (ElementNotVisibleException e){
						isTestPass=Constants_FRMWRK.FalseB;
						Reporting.logStep(driver, Step, "Unable to click the record from the required column after successfully matching the expected data from search coloumn in the table"+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);

					} 	
					if(clicked==Constants_FRMWRK.TrueB){
						flag=Constants_FRMWRK.True;
						Reporting.logStep(driver, Step, "Successfully clicked the record  from the required column after matching the expected data:-"+actionData+" with actual data:-"+actual_actionData+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
					}

					break;
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}
		if(clicked==Constants_FRMWRK.TrueB){
			break;
		}else{
			pageCount++;
			isNextDisplayed=clicknextPage(driver);
		}
		
}while (isNextDisplayed==Constants_FRMWRK.TrueB && clicked==Constants_FRMWRK.FalseB);
//
		if(clicked==Constants_FRMWRK.FalseB){
			Reporting.logStep(driver, Step, "Unable to click the record from the required column after matching the expected data:-"+actionData+" with actual data:-"+actual_actionData+" in the table "+colXpath_search+" for the search record "+searchData, Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}
		else if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}


	/**
	 * Searches the data in the required column (Without Reporting)
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param searchData
	 * @param colToSearch
	 * @return
	 */
	public static String searchforDataInsearchColumn_WOR(WebDriver driver,String testcaseName,String Step,String containerName,String searchData,int colToSearch){
		String flag=Constants_FRMWRK.False;
		String rowNumber="row";
		int rowCount=0;

		WebElement webtableElement=ElementMethods.fetchElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']");
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";


		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));


		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			
			WaitUtil.pause(100L);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

				if(rowSearch.getText().trim().equals(searchData.trim())){
					flag=Constants_FRMWRK.True;
					logsObj.log("searchforDataInsearchColumn_WOR : actual data equals to expected data for the record from required column in the table") ;
					WaitUtil.pause(100L);
					break;					
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			flag=Constants_FRMWRK.Error;
			Reporting.logStep(driver, Step, "Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}

		return flag;
	}
	/**
	 * Searches the data in the required column (With Reporting)
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param searchData
	 * @param colToSearch
	 * @return
	 */
	public static String searchforDataInsearchColumn(WebDriver driver,String testcaseName,String Step,String containerName,String searchData,String actionColumnType,int colToSearch){
		String flag=Constants_FRMWRK.False;
		String rowNumber="row";
		int rowCount=0;

		WebElement webtableElement=ElementMethods.fetchElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']");
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";


		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));


		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			WaitUtil.pause(100L);

			for (WebElement rowSearch : rows_search) { 

				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());

				if(rowSearch.getText().trim().equals(searchData.trim())){
					flag=Constants_FRMWRK.True;					
					logsObj.log("searchforDataInsearchColumn_WOR : actual data equals to expected data for the record from required column in the table") ;
					Reporting.logStep(driver, Step, "Sucessfully The actual data:-"+rowSearch.getText()+" matches with expected data:-"+searchData+" in the table "+colXpath_search, Constants_FRMWRK.Pass);
					WaitUtil.pause(100L);
					break;					
				}
				rowCount++;
			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			flag=Constants_FRMWRK.Error;
			Reporting.logStep(driver, Step, "Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}


		if(flag.equalsIgnoreCase(Constants_FRMWRK.False)){
			isTestPass=Constants_FRMWRK.FalseB;
			Reporting.logStep(driver, Step, "Unable to list the required search data "+searchData+" in the table "+colXpath_search+ " the complete table data available is: "+tableData, Constants_FRMWRK.Fail);
		}
		return flag;
	}
	
	/**
	 * Searches the data in the required column and fetches the data in the actionable column(With Reporting)
	 * @author Khaleel
	 * @param driver
	 * @param testcaseName
	 * @param Step
	 * @param containerName
	 * @param searchData
	 * @param actionColumnType
	 * @param colToSearch
	 * @param actionPerformCol
	 * @return data for success, Error for failure and False for un-successful search
	 */
	public static String searchforDataInsearchColumnAndFetchDataInactionableColumn_WOR(WebDriver driver,String testcaseName,String Step,String containerName,String searchData,String actionColumnType,int colToSearch,int actionPerformCol){
		String flag=Constants_FRMWRK.False;
		String colXpath_action;
		String rowNumber="row";
		int rowCount=0;		

		WebElement webtableElement=ElementMethods.fetchElement(driver, Constants_FRMWRK.FindElementByXPATH, "//table[@summary='"+containerName+"']");
		if(webtableElement==null){
			Reporting.logStep(driver, Step, "Webtable-"+ containerName+" is not displayed on the page", Constants_FRMWRK.Fail);
			flag=Constants_FRMWRK.False;
		}

		String colXpath_search= "//table[@summary='containerName']/descendant :: tr /td[ToSearchColumn]";		
		if(actionColumnType.equalsIgnoreCase("LINK")){
			colXpath_action="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]/a";
		}else{
			colXpath_action="//table[@summary='containerName']/descendant :: tr /td[actionPerformCol]/span";
		}

		colXpath_search=commonMethods.replaceString("containerName",colXpath_search,containerName);
		colXpath_search=commonMethods.replaceString("ToSearchColumn",colXpath_search,Integer.toString(colToSearch));
		colXpath_action=commonMethods.replaceString("containerName",colXpath_action,containerName);
		colXpath_action=commonMethods.replaceString("actionPerformCol",colXpath_action,Integer.toString(actionPerformCol));


		LinkedHashMap<String,String> tableData=new LinkedHashMap<String,String>();

		try{
			logsObj.log("Get Grid row count for column under search");

			final List<WebElement> rows_search = waitUntilAllVisible(driver,colXpath_search);
			final List<WebElement> rows_action = waitUntilAllVisible(driver,colXpath_action);
			WaitUtil.pause(100L);

			for (WebElement rowSearch : rows_search) { 
				tableData.put(rowNumber+Integer.toString(rowCount), rowSearch.getText());
				logsObj.log("searchforDataInsearchColumnAndFetchDataInactionableColumn_WOR: Table- RowNumber-"+rowNumber+" Data:-"+rowSearch.getText().trim()+" Input Data:-"+searchData.trim());
				if(rowSearch.getText().trim().equals(searchData.trim())){
					flag=rows_action.get(rowCount).getText();					
					logsObj.log("searchforDataInsearchColumnAndFetchDataInactionableColumn_WOR : actual data equals to expected data for the record from required column in the table") ;
					WaitUtil.pause(100L);
					break;					
				}
				rowCount++;

			}
		}catch(Exception e){
			isTestPass=Constants_FRMWRK.FalseB;
			flag=Constants_FRMWRK.Error;
			Reporting.logStep(driver, Step, "searchforDataInsearchColumnAndFetchDataInactionableColumn_WOR : Could not locate the required search data "+searchData+" in the table "+colXpath_search+" due to error-->"+e+ "and stack is "+commonMethods.getStackTrace(e), Constants_FRMWRK.Fail);
			return flag;		
		}

		return flag;
	}
	
	
	private static boolean clicknextPage(WebDriver driver){
		boolean flag=Constants_FRMWRK.FalseB;
		try{
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
			WebElement element=ExplicitWaitUtil.waitForElement(driver, Constants_FRMWRK.FindElementByXPATH, ObjRepository.grid_nextButton, Constants_TimeOuts.Element_TimeOut);
			element.click();
			WaitUtil.pause(Constants_TimeOuts.generic_TimeOut);
			flag=Constants_FRMWRK.TrueB;
			logsObj.log("Click next page of Webtable");
		}
		
		catch(StaleElementReferenceException st){
			logsObj.log("Click next page of Webtable occured stale..need to recover..");
			clicknextPage(driver);
		}		
		catch (Exception ex){
			logsObj.log("Unable to click the next page from webtable due to "+commonMethods.getStackTrace(ex));
		}
		return flag;
	}
}
