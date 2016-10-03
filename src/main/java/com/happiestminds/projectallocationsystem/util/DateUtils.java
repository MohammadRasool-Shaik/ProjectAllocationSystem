package com.happiestminds.projectallocationsystem.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtils {

	public static Date getDate(String dateString) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new java.sql.Date(dateFormat.parse(dateString).getTime());
		return date;

	}

	public static String parseDate(Date date) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = null;
		if (date == null) {
			dateString = null;
		} else {
			dateString = dateFormat.format(date);
		}
		return dateString;

	}
}
