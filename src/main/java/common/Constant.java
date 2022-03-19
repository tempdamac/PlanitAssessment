package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constant {
	
	//Declare Constant Variable here
	public final String ACT_AS_REQUIRED = "//*[contains(text(),'Act As is required')]";
	public final String AVAILABILITY_DATE_REQUIRED = "//*[contains(text(),'Availability Date is required')]";
	public final String EARLIEST_HIRE_DATE_REQUIRED = "//*[contains(text(),'Earliest Hire Date is required')]";
	
	private static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	public String createDate(int dayAdded) {
		
		Date currentDate = new Date();
	    
		Calendar c = Calendar.getInstance();
	    c.setTime(currentDate);
	    c.add(Calendar.DATE, dayAdded);

	    Date currentDatePlusOne = c.getTime();
	    String datePlusOne = dateFormat.format(currentDatePlusOne);
	    return datePlusOne;
	}
}
