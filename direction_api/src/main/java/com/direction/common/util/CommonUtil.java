package com.direction.common.util;

public class CommonUtil {

	/**
	 * Delay n seconds
	 * 
	 * @param n
	 */
	public static void delay(long n) {
		try {
			Thread.sleep(n);
		} catch (Exception e) {
		}
	}

	/**
	 * Parse the object of exception to string
	 * 
	 * @param e
	 * @return String
	 */
	public static String convertExceptionToString(Exception ex) {
		ex.printStackTrace();
		StackTraceElement[] traces = ex.getStackTrace();
		StringBuilder message = new StringBuilder();
		message.append(ex.toString() + "\n");
		for (StackTraceElement element : traces) {
			message.append(element).append("\n");
		}
		return message.toString();
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmpty(final String s) {
		return (s == null || s.trim().length() == 0);
	}

	/**
	 * Checks if is null or empty.
	 * 
	 * @param s
	 *            the s
	 * @return true, if is null or empty
	 */
	public static boolean isNullOrEmptyNotTrim(final String s) {
		return (s == null || s.length() == 0);
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		for (char c : str.toCharArray()) {
			if (!Character.isDigit(c)) {
				return false;
			}
		}
		return true;
	}

}
