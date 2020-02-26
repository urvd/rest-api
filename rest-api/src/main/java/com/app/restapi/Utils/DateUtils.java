package com.app.restapi.Utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateUtils {

	@SuppressWarnings("deprecation")
	public static String setDate() {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

	}
	public static String setDate(String an, String mois, String jour) {
		LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(an), Integer.parseInt(mois), Integer.parseInt(jour),2,5,8);
		return localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));		
	}

}
