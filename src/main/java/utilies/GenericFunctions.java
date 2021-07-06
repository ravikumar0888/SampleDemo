package utilies;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import testbase.BasePage;

/**
 * Generic reusable functions used in scripts
 *
 */
public class GenericFunctions extends BasePage {
	private static Logger logger = LogManager.getLogger(GenericFunctions.class.getName());
	/**
	 * get date as string
	 * 
	 * @param cal
	 *            Calendar object
	 * @return date as string
	 */
	public static String getDate(Calendar cal) {
		return "" + cal.get(Calendar.DATE) + File.separator + (cal.get(Calendar.MONTH) + 1) + File.separator + cal.get(Calendar.YEAR);
	}

	private Random rnd = new Random();

	String inputDateFormat = "dd/MM/yyyy";

	/**
	 * Returns current date as string
	 * 
	 * @return date as string
	 */
	public String currentDate() {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(inputDateFormat);
		LocalDate localDate = LocalDate.now();
		return (dtf.format(localDate));

	}

	/**
	 * Gives date 7 days after current
	 * 
	 * @return date in string format
	 */

	public String currentdateWithAddMoredays(int day)

	{
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT"));

		cal.add(Calendar.DATE, day);

		logger.info("date after {} days : {}", day, getDate(cal));

		return getDate(cal);

	}

	public String dateDisplayFormatToInputFormat(String mMMDDcommaYYYY) {
		if (!mMMDDcommaYYYY.contains("/")) // temporary check till FPLSPS-9256 gets fixed
		{
			SimpleDateFormat ft = new SimpleDateFormat("MMM dd, yyyy", Locale.US);
			java.util.Date t = null;
			try {
				t = ft.parse(mMMDDcommaYYYY);

				ft.applyPattern(inputDateFormat);

			} catch (ParseException e) {
				logger.info("Failed to parse the date ", e);
			}
			return ft.format(t);
		} else {
			return mMMDDcommaYYYY;
		}
	}

	public String getNextDate(String curDate) throws ParseException {

		final SimpleDateFormat format = new SimpleDateFormat(inputDateFormat);
		final Date date = format.parse(curDate);
		final Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		return format.format(calendar.getTime());
	}

	public String getResourceAsFile(String resource) {
		// enter the file path onto the file-selection input field
		File tmpFile = null;

		InputStream cpResource = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
		String fileName = resource.substring(resource.lastIndexOf('/') + 1);
		int dot = fileName.lastIndexOf('.');
		String base = (dot == -1) ? fileName : fileName.substring(0, dot);
		String extension = (dot == -1) ? "" : fileName.substring(dot);

		try {
			tmpFile = File.createTempFile(base, extension);
			Files.copy(cpResource, Paths.get(tmpFile.getPath()), StandardCopyOption.REPLACE_EXISTING);
			return tmpFile.getAbsolutePath();

		} catch (IOException e) {
			logger.error("failed to create file from resourse stream ", e);
		}
		return "";

	}

	/**
	 * Random number generator
	 * 
	 * @return random number as string
	 */
	public String randamGenerator() {
		final int MIN_RANDOM_NUMBER = 100000;
		final int MAX_RANDOM_NUMBER = 100000;

		int n = MIN_RANDOM_NUMBER + rnd.nextInt(MAX_RANDOM_NUMBER);
		return String.valueOf(n);

	}
	
	
}
