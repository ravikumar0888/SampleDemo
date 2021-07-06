package utilies;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateCalculator {

	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public String getMandateSignatureDate() {
		Calendar mandateSignatureDateCalendar = Calendar.getInstance();
		mandateSignatureDateCalendar.setTime(new Date());
		mandateSignatureDateCalendar.add(Calendar.MONTH, -5);
		return df.format(mandateSignatureDateCalendar.getTime());
	}

	public String getMandateLastSddSentDate() {

		Calendar mandateLastSddSentDateCalendar = Calendar.getInstance();
		mandateLastSddSentDateCalendar.setTime(new Date());
		mandateLastSddSentDateCalendar.add(Calendar.MONTH, -2);
		return df.format(mandateLastSddSentDateCalendar.getTime());
	}

	public String getSctDueDate() {

		Calendar sctDueDateCalendar = Calendar.getInstance();
		sctDueDateCalendar.setTime(new Date());
		sctDueDateCalendar.add(Calendar.DATE, 7);
		return df.format(sctDueDateCalendar.getTime());
	}

	public String getSctAccDueDate() {

		Calendar sctAccDueDateCalendar = Calendar.getInstance();
		sctAccDueDateCalendar.setTime(new Date());
		sctAccDueDateCalendar.add(Calendar.DATE, 1);
		return df.format(sctAccDueDateCalendar.getTime());
	}

	public String getFormattedCurrentDate(){
		return df.format(new Date());
	}

}
