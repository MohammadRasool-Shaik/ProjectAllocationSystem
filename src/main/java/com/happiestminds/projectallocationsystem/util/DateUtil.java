package com.happiestminds.projectallocationsystem.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static Calendar getDate(String dateString) throws ParseException {
		Calendar calendar = null;
		if (dateString != null && dateString.length() > 0) {
			calendar = Calendar.getInstance();
			calendar.setTime(new Date(dateFormat.parse(dateString).getTime()));
		}
		return calendar;
	}

	public static String parseDate(Calendar calendar) throws ParseException {
		String dateString = null;
		if (calendar == null) {
			dateString = null;
		} else {
			dateString = dateFormat.format(calendar.getTime());
		}
		return dateString;
	}

	public static boolean isEndDateAfterStartDate(String startDate, String endDate) throws ParseException {
		if (startDate == null || startDate.length() < 1 || endDate == null || endDate.length() < 1) {
			return false;
		}
		return (DateUtil.getDate(startDate).before(DateUtil.getDate(endDate))) ? true : false;
	}
}
