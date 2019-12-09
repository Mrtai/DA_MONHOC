package com.direction.common.util;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateUtil {

    public String TIMEZONE_SERVER = String.valueOf(Calendar.getInstance().getTimeZone().getID());

    private DateUtil() {
    }

    public static long subtractDateAsSecond(Date before, Date after) {
    	if (before == null || after == null) {
    		return -1;
    	}
    	return ((after.getTime() - before.getTime()) / 1000);
    }
    //private static Pattern dateRegexPattern = Pattern.compile("((19|20)\\d\\d)-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])");
    private static Pattern DATE_PATTERN = Pattern.compile(
		      "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
		      + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
		      + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
		      + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
    public static boolean isValidDate(String date) {
		        return DATE_PATTERN.matcher(date).matches();
    }
}
