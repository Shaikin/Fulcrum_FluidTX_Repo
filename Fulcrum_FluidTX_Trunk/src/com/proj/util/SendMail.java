package com.proj.util;

import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.frw.htmlreporting.HTMLReporting;

public class SendMail {

	
	public static void sendMailThroughGmail(final String To,final String From,String user,final String Password,String Subject,String Mailmessage,String attachmentPath){
		
		 String executiondate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		 Mailmessage=Mailmessage.replaceAll("time", executiondate);
		 Mailmessage=Mailmessage.replaceAll("seleniumsuitestatus", TestExecutionUtil.SuiteStatus);
		 Mailmessage=Mailmessage.replaceAll("passCount", String.valueOf(HTMLReporting.passCount));
		 int totalTests=HTMLReporting.passCount+HTMLReporting.failCount;
		 Mailmessage=Mailmessage.replaceAll("totalCount", String.valueOf(totalTests));
		 
		  //Get the session object  
		  Properties props = new Properties();  
		  	props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.from", From);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.debug", "false");
	        props.put("mail.transport.protocol", "true");
	        props.put("mail.smtp.starttls.enable", "true"); 
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(From,Password);  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress(user));//change accordingly  
		 //  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
		   message.setSubject(Subject+TestExecutionUtil.SuiteStatus);  
		 //  message.setText("Testing.......");  
		  
		   BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText(Mailmessage);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         String filename = attachmentPath;
	         DataSource source = new FileDataSource(filename);
	         messageBodyPart.setDataHandler(new DataHandler(source));
	         String attachmentFileName="SeleniumResults-"+executiondate+".zip";
	         messageBodyPart.setFileName(attachmentFileName);
	         multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);	     
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("Mail sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 
		   
		 }
	
	public static void sendHTMLMailThroughGmail(final String To,final String From,String user,final String Password,String Subject,String Mailmessage,String linkLocation,String signature){
		
		 String executiondate = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
		 Mailmessage=Mailmessage.replaceAll("time", executiondate);
		 Mailmessage=Mailmessage.replaceAll("seleniumsuitestatus", TestExecutionUtil.SuiteStatus);
		 Mailmessage=Mailmessage.replaceAll("passCount", String.valueOf(HTMLReporting.passCount));
		 int totalTests=HTMLReporting.passCount+HTMLReporting.failCount;
		 Mailmessage=Mailmessage.replaceAll("totalCount", String.valueOf(totalTests));
		 
		  //Get the session object  
		  Properties props = new Properties();  
		  	props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.from", From);
	        props.put("mail.smtp.port", "587");
	        props.put("mail.debug", "false");
	        props.put("mail.transport.protocol", "true");
	        props.put("mail.smtp.starttls.enable", "true"); 
		   
		  Session session = Session.getDefaultInstance(props,  
		   new javax.mail.Authenticator() {  
		   protected PasswordAuthentication getPasswordAuthentication() {  
		   return new PasswordAuthentication(From,Password);  
		   }  
		  });  
		   
		  //compose message  
		  try {  
		   MimeMessage message = new MimeMessage(session);  
		   message.setFrom(new InternetAddress(user));//change accordingly  
		 //  message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(To));
		   message.setSubject(Subject+TestExecutionUtil.SuiteStatus);  
		 //  message.setText("Testing.......");  
		   
		   BodyPart messageBodyPart = new MimeBodyPart();
		  
	         // Now set the actual message
	         messageBodyPart.setText(Mailmessage);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         multipart.addBodyPart(messageBodyPart);
	        
	         // Send the complete message parts
	        // message.setContent(multipart);	 
	         
	         message.setContent("<html>\n" +
	        		 "<body>\n<pre>" +
	        		 "\n" +
	        		 Mailmessage+
	        		 "<a href=\"http://"+linkLocation+"\">" +
	        		  "Here</a>\n" + 
	        		// "<a href=\"file:///DAKLWEB001/AutomationResults/Fulcrum/2016-01-20-HTMLReporting/TestSummary.html\">Here</a>"+
	        		//  "<a href=\"file:///\\DAKLWEB001\\AutomationResults\\Fulcrum\\2016-01-20-HTMLReporting\\TestSummary.html\">Here</a>"+
	        	/*	"<p>This is an email link:"+
	        		 //"<a href=\"file://///DAKLWEB001\\AutomationResults\\Fulcrum\\2016-01-20-HTMLReporting\\TestSummary.html\">Send Mail</a>"+
	        		 "<a href=\"file:///C:/LeapThoughtProjectWS/Falcrum_Trunk/Results/HTMLReporting/TestSummary.html\">Send Mail</a>"+
	        		
	        		 "</p>"+*/

  		       		
	        		 signature+
	        		 " \n </pre></body> \n" +
	        		 "</html>", "text/html");
		   //send message  
		   Transport.send(message);  
		  
		   System.out.println("Mail sent successfully");  
		   
		  } catch (MessagingException e) {throw new RuntimeException(e);}  
		   
		 
		   
		 }
}
