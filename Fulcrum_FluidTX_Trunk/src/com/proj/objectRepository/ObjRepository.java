package com.proj.objectRepository;

public class ObjRepository {

	public static String backToHR="backToHR";

	// Login page objects	
	public static String textbox_login_UserName="logon:username:input";
	public static String textbox_login_Password="logon:userPassword:input";	
	public static String button_login_LoginBeforeCredentials=".//*[@class='realme_button_padding']";
	public static String button_login_Login="logon:logon";


	public static String menu_logout="zz5_Menu";
	public static String heading_working=".//*[@title='Working on it...']";
	public static String overlay_working=".//*[text()='Working on it...']";



	//logout objects
	public static String link_user="zz5_Menu";
	public static String link_signOut="//*[@class='ms-core-menu-title' and text()='Sign Out']";

	//frame objects	
	public static String frame_single=".//iframe[contains(@id,'DlgFrame')]";	
	public static String frame_double="(.//iframe[contains(@id,'DlgFrame')])[2]";
	public static String frame_list_pattern="(.//iframe[contains(@id,'DlgFrame')])[framelist]";
	//choice objects
	public static String choice_select=".//*[@title='Add the highlighted item to this field']";
	public static String choice_ok=".//*[contains(@id,'OkButton')]";
	public static String choice_prvpage=".//*[@title='Previous Page']";
	
	
	//popup Objects
	public static String popup_head="//span[@class='heading left']";
	public static String popup_head2="//span[@class='heading left ng-binding']";
	public static String popup_head3="//span[contains(@class,'smallHeading left')]";
	public static String popup_success="dialog_success";
	public static String popup_cancel="dialog_cancel";
	public static String popup_save="//button[@title='Save']";


	//autosuggest text box item objects
	public static String js_autosuggest_items="//li[@class='ui-menu-item']/a";
	public static String js_dropdown_items="//div[@class='option']/div";

	//Grid Container
	public static String container_subMenu="//*[@class='ui popup inverted right center']";
	public static String container_transmittals="Transmittals";
	//Cookies popup
	public static String cookies_popup="//div[@class='bannerTextRight right']/span[1]";
	public static String validation_popup_span="//div[contains(@class,'alert-error')]/span";
	public static String validation_popup_div="//div[contains(@class,'alert-error')]/div";
	public static String validation_fieldLength_popup="//div[@class='formErrorContent']";



	//session timeout objects



	//Icons

}
