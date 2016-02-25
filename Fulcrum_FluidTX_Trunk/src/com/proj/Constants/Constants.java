package com.proj.Constants;

public class Constants {
	
	
	//Required as per new Frame work
	
	public static final String browserfirefox="firefox";
	public static final String browserchrome="chrome";
	public static final String browserie="ie";
	
	
	public static final String CONFIG_PROP_FILE="config.properties";
	
	
	public static final String CONFIG_FILE_NAME="Config.xlsx";
	
	
	// ********  NON Final Constants ******************************************
	public static String isJavaScript="False";
			
			
			
			
	// ****************** Suite Related Constants *********************************************
	
	public static final String SUITE_DOCS_FILE_NAME="DOCS Suite.xlsx";
	public static final String SUITE_TRANS_FILE_NAME="TRANSMITTALS Suite.xlsx";
	


	
	
	public static final String SUITE_DOCS_SUITENAME="DOCS Suite";
	public static final String SUITE_TRANS_SUITENAME="TRANSMITTALS Suite";

	
	
	public static final String Module_TRANS="TRANSMITTALS";
	public static final String Module_DOCS="DOCS";
	
	
	//OR Paths
	public static final String OR_Nav_Path=System.getProperty("user.dir")+"\\src\\com\\proj\\objectRepository\\ObjectsFile_Navigation.xlsx";
	public static final String OR_Toolbar_Path=System.getProperty("user.dir")+"\\src\\com\\proj\\objectRepository\\ObjectsFile_Toolbars.xlsx";
	public static final String OR_Popup_Path=System.getProperty("user.dir")+"\\src\\com\\proj\\objectRepository\\ObjectsFile_Popup.xlsx";
	public static final String OR_TRANS_Path=System.getProperty("user.dir")+"\\src\\com\\proj\\objectRepository\\ObjectsFile_Transmittals.xlsx";
	public static final String OR_DOCS_Path=System.getProperty("user.dir")+"\\src\\com\\proj\\objectRepository\\ObjectsFile_Documents.xlsx";

	
	
	//Data sheets Path
	public static final String DataFileLocation_Transmittal=System.getProperty("user.dir")+"\\src\\com\\proj\\suiteTRANSMITTALS\\testdata\\";
	public static final String DataFileLocation_Documents=System.getProperty("user.dir")+"\\src\\com\\proj\\suiteDOCS\\testdata\\";
	
	
	// Application Name 
	public static final String App_Fluid="FluidTx7";
	public static final String App_Fulcrum="Fulcrum";
	
	//Locator Types
	public static final String LocatorTypeButton="button";
	public static final String LocatorTypeImage="image";
		
	
	
	// HTML tagNames
	public static final String tagName_td="td";
	public static final String tagName_tr="tr";
	public static final String tagName_tbody="tbody";
	public static final String tagName_span="span";
	public static final String tagName_a="a";
	public static final String tagName_div="div";
	
	
	//object types
	public static final String objectType_Image="image";
	public static final String objectType_Textbox="textbox";
	public static final String objectType_Button="button";
	public static final String objectType_TextboxAutoSuggest="TEXTBOX_AUTOSUGGEST";
	public static final String objectType_TextboxgetValue="textbox_getvalue";
	public static final String objectType_Link="link";
	public static final String objectType_WebElement="webelement";
	
	//common buttons
	public static final String ok="OK";
	public static final String cancel="Cancel";
	
	//Delimiters
	public static final String delimiter_data="@@";
}
