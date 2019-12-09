package com.direction.common.util;

import java.security.MessageDigest;

public class HashUtil {

	public static String MD5(String md5) {
		StringBuffer sb = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			for (int i = 0; i < array.length; ++i) {
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return sb.toString();
	}
	
	public static void main(String[] args) {
		System.out.println(MD5("123456" + "PROPZY"));
	}

}
