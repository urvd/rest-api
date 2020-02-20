package com.app.restapi.Utils;

import java.sql.Date;

public class DateUtils {

	@SuppressWarnings("deprecation")
	public static Date setDateMySqlFormat() {
		java.util.Date d1 = new java.util.Date();
		
		return (java.sql.Date) new java.sql.Date(d1.getYear(),d1.getMonth(),d1.getDate());

	}
	public static Date setDateMySqlFormat(String datestr) {
		return (java.sql.Date) Date.valueOf(datestr); //param = format "yyyy-[m]m-[d]d"

	}

}
