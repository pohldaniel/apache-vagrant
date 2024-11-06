package de.app.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CalendarUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(CalendarUtils.class);
	
	public static String GetTimeStamp(Calendar calendar, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        return dateFormat.format(calendar.getTime());
	}
	
	public static Calendar GetTimeStamp(String date, String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
        Calendar calendar = Calendar.getInstance();
        try {
			calendar.setTime(dateFormat.parse(date));
		} catch (ParseException e) {
			LOG.info("Could not fill Database");
		}
        return calendar;
	}
	
	public static Calendar CalendarFrom(LocalDateTime time) {
		return new Calendar.Builder()
		.setDate(time.getYear(), time.getMonthValue(), time.getDayOfMonth())
		.setTimeOfDay(time.getHour(), time.getMinute(), time.getSecond())
		.build();
	}
	
	public static Calendar AddHours(Calendar calendar, int hours) {
		Calendar calendarNew = (Calendar)calendar.clone();
		calendarNew.add(Calendar.HOUR_OF_DAY, hours);
		return calendarNew;
	}
	
	public static Calendar GetDailyMin(Calendar calendar) {
		Calendar calendarNew = (Calendar)calendar.clone();		
		calendarNew.set(Calendar.HOUR_OF_DAY, 0);
		calendarNew.set(Calendar.MINUTE, 0);
		calendarNew.set(Calendar.SECOND, 0);
		calendarNew.set(Calendar.MILLISECOND, 0);
		return calendarNew;
	}
	
	public static Calendar GetDailyMax(Calendar calendar) {
		Calendar calendarNew = (Calendar)calendar.clone();		
		calendarNew.set(Calendar.HOUR_OF_DAY, 23);
		calendarNew.set(Calendar.MINUTE, 59);
		calendarNew.set(Calendar.SECOND, 59);
		calendarNew.set(Calendar.MILLISECOND, 0);
		return calendarNew;
	}
}