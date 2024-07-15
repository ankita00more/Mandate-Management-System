package com.example.demo.utilities;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
//import org.apache.log4j.Logger;

public class DateMaster {
//	static Logger logger = Logger.getLogger(DateMaster.class);
	public static String Datetoday() {
		LocalDate today = LocalDate.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yy");
		String datetoday = today.format(format);
		return datetoday;
		
	}
	
	public String getDateTime() 
	{
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		String today = dateFormat.format(cal.getTime());
		return today;
	}
	public String getDate()
	{
		Calendar cal = Calendar.getInstance(); 
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	public static String getDate1()
	{
		Calendar cal = Calendar.getInstance(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	public static String getDate12Hr()
	{
		Calendar cal = Calendar.getInstance(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	public static String getDate2()
	{
		Calendar cal = Calendar.getInstance(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	
	public static java.sql.Date getDate4() throws ParseException
	{
		Calendar cal = Calendar.getInstance(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
	    String today=dateFormat.format(cal.getTime());
	    Date parse = dateFormat.parse(today);
	    java.sql.Date sqlStartDate = new java.sql.Date(parse.getTime()); 
		return sqlStartDate;
	}
	public static String getDate3()
	{
		Calendar cal = Calendar.getInstance(); 
		 SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	
	public String getDateonly1(String date1) throws ParseException  {
		DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy"); 
		Date date = formatter.parse(date1); 
		System.out.println("Parsed date would be -->"+date); 
		SimpleDateFormat newFormat = new SimpleDateFormat("dd-MM-yy");
		String finalDate = newFormat.format(date);
		System.out.println("Changed date format -->"+finalDate);
		return finalDate;
	}

	
	public static String getDateonly()
	{
		Calendar cal = Calendar.getInstance(); 
		DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	public static String getTimeStamp()
	{
		Calendar cal = Calendar.getInstance(); 
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
	    String today=dateFormat.format(cal.getTime());
		return today;
	}
	
	public static String getCurrentTimeStamp() {
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyyhhmmss");
		String date = dateFormat.format(cal.getTime());
		return date;
	}
	
	public static boolean validateJavaDate(String strDate)
	   {
		
			SimpleDateFormat sdfrmt = new SimpleDateFormat("MM-dd-yyyy");
		 	sdfrmt.setLenient(false);
		 	return true;

	   }
	
}

