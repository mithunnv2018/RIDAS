package com.ridas.common;

import java.io.File;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommonUtil {
	public static String ImagePath = "\\RIDASReports\\";

	public static String getImagePathForWs(HttpServletRequest request, HttpServletResponse response) {

		String serverPath = request.getServerName();
		System.out.println("serverPath : " + serverPath);
		int portNo = request.getServerPort();
		String serverPathAndPort = "http:" + "//" + serverPath + ":" + portNo + "/";
		String ImgPath = serverPathAndPort + "RIDASReports/reports/";
		System.out.println("ImgPath : " + ImgPath);
		return ImgPath;
	}

	public static String getImagePathForSave(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession();
//		String WEBAPPNAME=request.getContextPath().replaceAll("/", "\\");
	
		String ImagePathsave = session.getServletContext().getRealPath(ImagePath);
		String ImagePathsaveNew=ImagePathsave.substring(0, ImagePathsave.indexOf("\\RIDAS"));
		//String ImagePathsaveNew=ImagePathsave.replace("BillingWebApp/BillingWebAppReport","");
		String ImageSavePath =  ImagePathsaveNew+ImagePath+"reports"+"\\"; // report_"+billId+".pdf";
		System.out.println("ImageSavePath : "+ImageSavePath);
		
		//done as on 28/01/2015  Implemented code to create folder if not exist
		File subfolder = new File(ImageSavePath);
		if(subfolder.exists()==false){
			subfolder.mkdir();
		}
		
		return ImageSavePath;
	}
	
	public static Integer getAgeOfPerson(Date memberBirthDate)
	{
		System.out.println("CommonUtil.getAgeOfPerson()");
		
		
		Calendar instance = Calendar.getInstance();
		
		instance.setTime(memberBirthDate);
		
		Calendar currentinstance = Calendar.getInstance();
		
		currentinstance.setTime(new Date());
		
		int year = instance.get(Calendar.YEAR);
		int currentyear = currentinstance.get(Calendar.YEAR);
		
		int month = instance.get(Calendar.MONTH);
		int currentmonth = currentinstance.get(Calendar.MONTH);
		int days = 0;
		
	    int yearage=currentyear-year;		
	    int monthage=currentmonth-month;		
		
	  //if month difference is in negative then reduce years by one and calculate the number of months.
	      if (monthage < 0)
	      {
	    	  yearage--;
	    	  monthage = 12 - month + currentmonth;
	         if (currentinstance.get(Calendar.DATE) < instance.get(Calendar.DATE))
	        	 monthage--;
	      } else if (monthage == 0 && currentinstance.get(Calendar.DATE) < instance.get(Calendar.DATE))
	      {
	    	  yearage--;
	    	  monthage = 11;
	      }
	    
		
	    //Calculate the days
	      if (currentinstance.get(Calendar.DATE) > instance.get(Calendar.DATE))
	         days = currentinstance.get(Calendar.DATE) - instance.get(Calendar.DATE);
	      else if (currentinstance.get(Calendar.DATE) < instance.get(Calendar.DATE))
	      {
	         int today = currentinstance.get(Calendar.DAY_OF_MONTH);
	         currentinstance.add(Calendar.MONTH, -1);
	         days = currentinstance.getActualMaximum(Calendar.DAY_OF_MONTH) - instance.get(Calendar.DAY_OF_MONTH) + today;
	      } else
	      {
	         days = 0;
	         if (monthage == 12)
	         {
	        	 yearage++;
	        	 monthage = 0;
	         }
	      }
	      String a=yearage+"."+monthage;
	      double parseDouble = Double.parseDouble(a);
	      System.out.println("double="+parseDouble);
	      long round = Math.round(parseDouble);
	      yearage=(int) round;
//	      Calendar realinstance2 = Calendar.getInstance();
	      
		return yearage;
		
	}
}
