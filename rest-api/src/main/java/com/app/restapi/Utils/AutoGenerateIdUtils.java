package com.app.restapi.Utils;

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class AutoGenerateIdUtils {
	public static String autoGenerate() {
		int chooseFrom = 48; // numeral '0'
	    int to = 122; // letter 'z'
	    int targetStringLength = 7;
	    Random random = new Random();
	 
	    String generatedString = random.ints(chooseFrom, to + 1)
	      .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
	      .limit(targetStringLength)
	      .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
	      .toString();
	 
	    return generatedString;
	}
	//auto generate alphanumeric to lower or upper case
	public static String generateAlphaNumeriqueLowerCase(int lenght) {
		return RandomStringUtils.randomAlphanumeric(lenght).toLowerCase();
	}
	public static String generateAlphaNumeriqueUpperCase(int lenght) {
		 return RandomStringUtils.randomAlphanumeric(lenght).toUpperCase();
	}
	//auto generate alphabetic to lower or upper case
	public static String generateOnlyLettersLowerCase(int lenght) {
		return RandomStringUtils.randomAlphabetic(lenght).toLowerCase();
	}
	public static String generateOnlyLettersUpperCase(int lenght) {
		 return RandomStringUtils.randomAlphabetic(lenght).toUpperCase();
	}
	//auto generate number
	public static String generateNumbers(int lenght) {
		 return RandomStringUtils.randomNumeric(lenght);
	}
	public static String generateNumbersExtendable(int lenghtmin, int lenghtmax) {
		 return RandomStringUtils.randomNumeric(lenghtmin,lenghtmax);
	}
}
