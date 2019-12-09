package com.direction.common.util;

import java.util.Random;

public class RandomCertCode {

	private static char[] buf = new char[4];
	private static final char[] symbols = new char[36];

	static {
		for (int idx = 0; idx < 10; ++idx)
			symbols[idx] = (char) ('0' + idx);
		for (int idx = 10; idx < 36; ++idx)
			symbols[idx] = (char) ('a' + idx - 10);
	}

	/**
	 * constructor method
	 * 
	 * @param length
	 */
	public RandomCertCode() {
	}

	/**
	 * Generate string random
	 * 
	 * @return
	 */
	public static synchronized String getRandomString() {
		for (int idx = 0; idx < buf.length; ++idx)
			buf[idx] = symbols[new Random().nextInt(symbols.length)];
		return new String(buf);
	}

}
