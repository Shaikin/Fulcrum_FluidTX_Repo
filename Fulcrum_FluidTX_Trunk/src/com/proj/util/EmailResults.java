package com.proj.util;

import com.frw.util.Zip;

public class EmailResults {
	
	public static void ZipAndpublish(String resultsFolder,String zipFileName,String From,String User,String Password,String recepients,String Subject,String Mailmessage,String attachmentPath) throws Throwable{
		
		Zip.zipDir(resultsFolder, zipFileName);
		
		SendMail.sendMailThroughGmail(recepients, From, User, Password, Subject, Mailmessage, attachmentPath);
	}
	
	
	public static void publish(String From,String User,String Password,String recepients,String Subject,String Mailmessage,String linkLocation,String signature) throws Throwable{
			SendMail.sendHTMLMailThroughGmail(recepients, From, User, Password, Subject, Mailmessage,linkLocation,signature);
	}
}
