package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Constant {
	
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
