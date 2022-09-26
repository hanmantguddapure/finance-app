package com.app.finance.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtils {
	public static String convertDateToString(LocalDate date) {

		SimpleDateFormat newFormat = new SimpleDateFormat("yyyy-MM-dd");
		return newFormat.format(date);

	}

	public static int getMontDifference(LocalDate startDate, LocalDate endDate) {
		Period period = Period.between(startDate, endDate);
		if(period.getYears()>0)
			return (period.getYears()*12)+period.getMonths();
		return period.getMonths();

	}
	public static int getDays(LocalDate startDate, LocalDate endDate) {
		Period period = Period.between(startDate, endDate);
		return period.getDays();

	}
	public static LocalDate convertStringToLocalDate(String date){
		  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	      LocalDate localDate = LocalDate.parse(date, formatter);
	      return localDate;
	}

}
