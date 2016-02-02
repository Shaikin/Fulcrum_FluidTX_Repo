package com.proj.util;

import com.frw.Constants.Constants_FRMWRK;
import com.frw.util.DataGeneratorUtil;
import com.proj.base.TestBase;

public class FieldLengthUtil extends TestBase{
	
	
	/**
	 * Get the required length String using characters 
	 * @author khshaik
	 * @date Apr 07 2015
	 * @param flv_length
	 * @return
	 */
	public static String getFieldLevelValidationAlphaData(String flv_length){
		String flag=Constants_FRMWRK.False;
		try{
				flag=DataGeneratorUtil.getRandomString(Integer.valueOf(flv_length)+1, true, false);
		}catch(Throwable t){
				System.out.println("Unable to fetch the FLV due "+t);
		}
		return flag;
	}
	
	
	/**
	 * Get the required length String using numbers 
	 * @author khshaik
	 * @date Apr 07 2015
	 * @param flv_length
	 * @return
	 */
	public static String getFieldLevelValidationNumbData(String flv_length){
		String flag=Constants_FRMWRK.False;
		try{
			flag=DataGeneratorUtil.getRandomString(Integer.valueOf(flv_length)+1, false, true);
		}catch(Throwable t){
			System.out.println("Unable to fetch the FLV due "+t);
		}
		return flag;
	}

}
