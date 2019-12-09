package com.direction.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author thanhthi.tran
 * @date Apr 26, 2013
 * @since 0.0.1
 * @copyright 2014 DAVITEQ, All rights reserved
 */
public class Util {

	private static final Logger log = Logger.getLogger(Util.class);

	private static byte[] systrace = new byte[] { 0x00, 0x00 };

	public static final String MD5 = "MD5";
	public static final String SHA256 = "SHA-256";
	public static final String SHA1 = "SHA-1";
	public static final String SHA512 = "SHA-512";
	public static final String SHA384 = "SHA-384";

	public static final int BYTES_OF_INT16 = 2;
	public static final int BYTES_OF_SHORT = 2;
	public static final int BYTES_OF_UINT16 = 2;
	public static final int BYTES_OF_INT32 = 4;
	public static final int BYTES_OF_UINT32 = 4;
	public static final int BYTES_OF_FLOAT = 4;
	public static final int BYTES_OF_LONG = 8;
	public static final double R = 6371e3;

	public Util() {
	}

	public static String encryptPassword(String password) {
        String encryptPassword = null;
        try {
            encryptPassword = encrypt("MD5", password);
        } catch (Exception ex) {
            log.error(ex.getMessage());
        }
        return encryptPassword;
    }

    public static String encrypt(String instance, String input)
            throws NoSuchAlgorithmException {

        MessageDigest md = MessageDigest.getInstance(instance);
        byte[] dataBytes = new byte[1024];

        dataBytes = input.getBytes();
        md.update(dataBytes, 0, dataBytes.length);

        byte[] mdbytes = md.digest();

        return bytes2Hex(mdbytes).toUpperCase();
    }
    
    public static String bytes2Hex(byte[] buf) {
        StringBuffer sb = new StringBuffer();
        try {
            for (int i = 0; i < buf.length; i++) {
                sb.append(Integer.toString((buf[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
        } catch (Exception ex) {
        }
        return sb.toString();
    }
	
	/* begin methods to convert Integer to */

	/**
	 * Convert integer to hex (ASCII)
	 * 
	 * @param i
	 * @return
	 */
	public static String int32ToHex(int i) {

		return Util.bytesToHex(Util.int32ToBytes(i));

		// String rs = Integer.toHexString(Integer.valueOf(String.valueOf(i),
		// 10));
		// if (rs.length() % 2 != 0)
		// rs = "0" + rs;
		// return rs;
	}

	/**
	 * Convert integer to byte array
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] getBytesOfInt32(int value, int size) {

		if (size > BYTES_OF_INT32)
			return null;

		ByteBuffer bb = ByteBuffer.allocate(BYTES_OF_INT32);
		bb.putInt(value);
		byte[] rs = new byte[size];
		System.arraycopy(bb.array(), BYTES_OF_INT32 - size, rs, 0, size);

		return rs;
	}

	public static byte[] getBytesOfLong(long value, int size) {

		if (size > BYTES_OF_LONG)
			return null;

		ByteBuffer bb = ByteBuffer.allocate(BYTES_OF_LONG);
		bb.putLong(value);
		byte[] rs = new byte[size];
		System.arraycopy(bb.array(), BYTES_OF_LONG - size, rs, 0, size);

		return rs;
	}

	public static byte[] getBytesOfShort(short value, int size) {

		if (size > BYTES_OF_SHORT)
			return null;

		ByteBuffer bb = ByteBuffer.allocate(BYTES_OF_SHORT);
		bb.putShort(value);
		byte[] rs = new byte[size];
		System.arraycopy(bb.array(), BYTES_OF_SHORT - size, rs, 0, size);

		return rs;
	}

	public static byte[] getBytesOfFloat(float value, int size) {

		if (size > BYTES_OF_FLOAT)
			return null;

		ByteBuffer bb = ByteBuffer.allocate(BYTES_OF_FLOAT);
		bb.putFloat(value);
		byte[] rs = new byte[size];
		System.arraycopy(bb.array(), BYTES_OF_FLOAT - size, rs, 0, size);

		return rs;
	}

	/* end methods to convert Integer to */

	/* begin methods to convert Hex to */

	/**
	 * Convert hex to byte array
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] hexToBytes(String s) {

		int len = s.length();

		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2)
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));

		return data;

	}

	/**
	 * Convert hex to float
	 * 
	 * @param hex
	 * @return
	 */
	public static float hexToFloat(String hex) {
		Long i = Long.parseLong(hex, 16);
		Float f = Float.intBitsToFloat(i.intValue());
		return f;
	}

	/* end methods to convert Hex to */

	/* begin methods to convert Integer to */

	/**
	 * Convert byte array to Long
	 * 
	 * @param buf
	 * @return
	 */
	public static long bytesToLong(byte[] buf) {
		ByteBuffer buffer = ByteBuffer.wrap(buf);
		// buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getLong();
	}

	/**
	 * Convert byte array to Float
	 * 
	 * @param buf
	 * @return
	 */
	public static float bytesToFloat(byte[] buf) {
		ByteBuffer buffer = ByteBuffer.wrap(buf);
		// buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getFloat();
	}

	/**
	 * Convert byte array to integer (*) - default ByteOrder is BIG_ENDIAN
	 * 
	 * @param buf
	 * @return
	 */
	public static int bytesToInt32(byte[] buf) {
		ByteBuffer buffer = ByteBuffer.wrap(buf);
		// buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getInt();
	}

	public static int bytesToShort(byte[] buf) {
		ByteBuffer buffer = ByteBuffer.wrap(buf);
		// buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getShort();
	}

	/**
	 * Convert byte array to hex
	 * 
	 * @param data
	 * @return
	 */
	public static String bytesToHex(byte[] data) {

		if (data == null)
			return null;

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			sb.append(Integer.toString((data[i] & 0xff) + 0x100, 16).substring(1));
		}
		return sb.toString().toUpperCase();
	}

	/**
	 * Convert byte array to string of binary
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToBinary(byte[] bytes) {
		StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
		for (int i = 0; i < Byte.SIZE * bytes.length; i++)
			sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
		return sb.toString();
	}

	/* end methods to convert byte array to */

	/* begin methods to convert byte to */

	/**
	 * Convert byte to hex
	 * 
	 * @param b
	 * @return
	 */
	public static String byteToHex(byte b) {
		return Util.bytesToHex(Util.getBytesOfInt32(Util.uint8ToInt32(b), 1));
	}

	/**
	 * Convert byte to integer
	 * 
	 * @param b
	 * @return
	 */
	public static int uint8ToInt32(byte b) {
		return (int) b & 0xFF;
	}

	/* end methods to convert byte to */

	/* end methods to char to */

	/**
	 * Convert char to hex
	 * 
	 * @param c
	 * @return
	 */
	public static String charToHex(char c) {
		return Integer.toHexString(c & 0xFF);
	}

	/* end methods to char to */

	/* begin methods to string to */

	/**
	 * Convert string to hex
	 * 
	 * @param s
	 * @return
	 */
	public static String stringToHex(String s) {

		char[] chars = s.toCharArray();
		StringBuffer strBuffer = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			strBuffer.append(charToHex(chars[i]));
		}
		return strBuffer.toString();
	}

	/* end methods to string to */

	/* begin methods to long to */

	public static byte[] int32ToBytes(int value) {
		return ByteBuffer.allocate(4).putInt(value).array();
	}

	/* end methods to long to */

	/* begin methods to binary string to */

	/**
	 * Convert binary string to byte array
	 * 
	 * @param s
	 * @return
	 */
	public static byte[] binaryToBytes(String s) {
		int sLen = s.length();
		byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
		char c;
		for (int i = 0; i < sLen; i++)
			if ((c = s.charAt(i)) == '1')
				toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
			else if (c != '0')
				throw new IllegalArgumentException();
		return toReturn;
	}

	/* end methods to binary string to */

	/* begin methods for cloud-des */

	/**
	 * Reversal a byte array
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] reverseBytes(byte[] b) {

		byte[] buf = b.clone();

		for (int i = 0, j = buf.length - 1; i < j; i++, j--) {
			byte temp = buf[i];
			buf[i] = buf[j];
			buf[j] = temp;
		}

		return buf;

	}

	// /**
	// * Build a packet from queue
	// *
	// * @param q
	// * @return
	// */
	// public static byte[] buildPacket(Queue<I12Obj> q) {
	//
	// if (q == null || q.isEmpty())
	// return null;
	//
	// int size = 0;
	// try {
	// Iterator<I12Obj> iter = q.iterator();
	// while (iter.hasNext()) {
	// I12Obj temp = iter.next();
	// if (temp.getType().equals(I12Obj.BYTE)) {
	// size++;
	// } else {
	// byte[] value = (byte[]) temp.getValue();
	// size += value.length;
	// }
	// }
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	//
	// byte[] rs = new byte[size];
	//
	// try {
	//
	// int pos = 0;
	//
	// while (!q.isEmpty()) {
	// I12Obj temp = q.remove();
	// if (temp.getType().equals(I12Obj.BYTE)) {
	// rs[pos++] = (byte) temp.getValue();
	// } else {
	// System.arraycopy((byte[]) temp.getValue(), 0, rs,
	// pos, ((byte[]) temp.getValue()).length);
	// pos += ((byte[]) temp.getValue()).length;
	// }
	//
	// }
	//
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// return rs;
	// }

	/**
	 * Get a byte array
	 * 
	 * @param src
	 * @param startPos
	 * @param length
	 * @return
	 */
	public static byte[] getBytes(byte[] src, int startPos, int length) {

		if (length < 0)
			throw new RuntimeException("The length value must be greater than zezo");

		byte[] dest = new byte[length];

		for (int i = 0; i < length; i++) {
			dest[i] = src[i + startPos];
		}

		return dest;

	}

	/**
	 * Convert integer to string and justify-right
	 * 
	 * @param in
	 * @return
	 */
	public static String padleft(int in) {
		if (in < 10) {
			return "0" + in;
		}
		return "" + in;
	}

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
	public static String convertExceptionToString(Exception e) {
		e.printStackTrace();
		StackTraceElement[] traces = e.getStackTrace();
		StringBuilder message = new StringBuilder();
		message.append(e.toString() + "\n");
		for (StackTraceElement element : traces)
			message.append(element).append("\n");

		return message.toString();
	}

	/**
	 * Hash algorithm (SHA-256, MD5,)
	 * 
	 * @param input
	 * @return String
	 * @throws NoSuchAlgorithmException
	 */
	public static String Checksum(String instance, String input) throws NoSuchAlgorithmException {

		MessageDigest md = MessageDigest.getInstance(instance);
		byte[] dataBytes = new byte[1024];

		dataBytes = input.getBytes();
		md.update(dataBytes, 0, dataBytes.length);

		byte[] mdbytes = md.digest();

		return bytesToHex(mdbytes).toUpperCase();

	}

	/**
	 * Increment integer number 1 unit
	 * 
	 * @param b
	 * @return
	 */
	public static byte[] nextBitString(byte[] b) {

		byte[] rs = new byte[b.length];

		System.arraycopy(b, 0, rs, 0, b.length);

		for (int i = rs.length - 1; i >= 0; i--) {
			byte mask = 0x01;
			do {
				if ((rs[i] & mask) == mask) {
					rs[i] = (byte) (rs[i] ^ mask);
					mask = (byte) (mask << 1);
				} else {
					rs[i] = (byte) (rs[i] ^ mask);
					return rs;
				}

			} while (mask != (byte) 0x00);

		}

		return rs;

	}

	/**
	 * Increment integer number n unit
	 * 
	 * @param b
	 * @param n
	 * @return
	 */
	public static byte[] nextBitString(byte[] b, int n) {

		byte[] rs = b;

		for (int i = 0; i < n; i++) {
			rs = Util.nextBitString(rs);
		}

		return rs;
	}

	/**
	 * @param s
	 * @return
	 */
	public static boolean isNullStr(String s) {
		if (s == null || s.trim().length() <= 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Convert
	 * 
	 * @param buf
	 * @return
	 */
	public static int int16ToInt32(byte[] buf) {
		ByteBuffer buffer = ByteBuffer.wrap(buf);
		// buffer.order(ByteOrder.BIG_ENDIAN);
		return buffer.getShort();

	}

	public static int uint16ToInt32(byte[] bytes) {
		int value = (byteAsUInt32(bytes[0]) | (byteAsUInt32(bytes[1]) << 8));
		return value;
	}

	public static int byteAsUInt32(byte b) {
		return ((int) (uint8ToInt32(b)) & 0x000000FF);
	}

	public static long byteAsULong(byte b) {
		return ((long) b) & 0x00000000000000FFL;
	}

	public static long uint32ToLong(byte[] bytes) {
		long value = byteAsULong(bytes[0]) | (byteAsULong(bytes[1]) << 8) | (byteAsULong(bytes[2]) << 16)
				| (byteAsULong(bytes[3]) << 24);
		return value;
	}

	// thanhuy.nguyen ADD START
	public static byte[] shortToInt32(short value) {

		if (Short.MIN_VALUE > value || value > Short.MAX_VALUE) {
			return null;
		}

		return Util.getBytesOfInt32(value, BYTES_OF_INT32);
	}

	public static byte[] int32ToByte(int value) {

		int maxByte = 256;
		if (value < 0 || value > maxByte) {
			return null;
		}

		return getBytesOfInt32(value, 1);
	}

	/**
	 * reviewed by thanhthi.tran, result: FAIL
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] int32ToUInt16(int value) {

		int maxUInt16 = 65535;
		if (value < 0 || value > maxUInt16) {
			return null;
		}

		return getBytesOfInt32(value, 2);
	}

	/**
	 * reviewed by thanhthi.tran, result: FAIL
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] longToUInt32(long value) {

		long maxUInt32 = 4294967295L;
		if (value < 0 || value > maxUInt32) {
			return null;
		}

		return getBytesOfLong(value, 4);
	}

	/**
	 * reviewed by thanhthi.tran, result: OK
	 * 
	 * @param value
	 * @return
	 */
	public static byte[] booleanToByte(boolean value) {
		return new byte[] { (byte) (value ? 0x01 : 0x00) };
	}

	// thanhuy.nguyen ADD END

	public static byte[] getSysTrace() {

		systrace = Util.nextBitString(systrace);

		/* exclude 0x0000 */
		if (bytesToShort(systrace) == 0) {
			systrace = Util.nextBitString(systrace);
		}

		return systrace;
	}

	public static byte[] longToBytes(long l, int size) {
		return ByteBuffer.allocate(size).putLong(l).array();
	}

	public String convertHostNameHexToString(String hostName) {
		StringBuffer result = new StringBuffer();
		try {
			byte[] temp = Util.hexToBytes(hostName);
			byte[] temp1 = { 0x00, 0x00, 0x00, temp[0] };
			byte[] temp2 = { 0x00, 0x00, 0x00, temp[1] };
			byte[] temp3 = { 0x00, 0x00, 0x00, temp[2] };
			byte[] temp4 = { 0x00, 0x00, 0x00, temp[3] };
			int[] temp5 = { Util.bytesToInt32(temp1), Util.bytesToInt32(temp2), Util.bytesToInt32(temp3),
					Util.bytesToInt32(temp4) };
			for (int i = 0, n = (temp5.length - 1); i <= n; i++) {
				result.append(temp5[i]);
				if (i < n) {
					result.append(".");
				}
			}
		} catch (Exception e) {
			log.error(Util.convertExceptionToString(e));
		}
		return result.toString();
	}

	private static JsonParser parser = new JsonParser();

	public static void printJSONHashmap(HashMap<String, Object> jsonHashmap) {
		jsonHashmap.forEach((key, val) -> {
			System.out.println(key);
			if (val instanceof String) {
				
			}
		});
		
	}

	/* recursion method: Parsing JSON in Java without knowing JSON format */
	public static HashMap<String, Object> createHashMapFromJsonString(String json) {

		JsonObject object = (JsonObject) parser.parse(json);
		Set<Map.Entry<String, JsonElement>> set = object.entrySet();
		Iterator<Map.Entry<String, JsonElement>> iterator = set.iterator();
		HashMap<String, Object> map = new HashMap<String, Object>();

		while (iterator.hasNext()) {

			Map.Entry<String, JsonElement> entry = iterator.next();
			String key = entry.getKey();
			JsonElement value = entry.getValue();

			if (null != value) {
				if (!value.isJsonPrimitive()) {
					if (value.isJsonObject()) {
						map.put(key, createHashMapFromJsonString(value.toString()));
					} else if (value.isJsonArray() && value.toString().contains(":")) {
						List<HashMap<String, Object>> list = new ArrayList<>();
						JsonArray array = value.getAsJsonArray();
						if (null != array) {
							for (JsonElement element : array) {
								list.add(createHashMapFromJsonString(element.toString()));
							}
							map.put(key, list);
						}
					} else if (value.isJsonArray() && !value.toString().contains(":")) {
						map.put(key, value.getAsJsonArray());
					}
				} else {
					map.put(key, value.getAsString());
				}
			}
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	public static JSONObject Access_URL(String url){
		try {
			URL url2 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url2.openConnection();
			con.setRequestMethod("GET");
			//con.setDoOutput(true);
			con.setRequestProperty("Content-Type", "application/json");
			con.setConnectTimeout(5000);
			con.setReadTimeout(5000);
			con.setInstanceFollowRedirects(false);
			Reader streamReader = null;
			
			if (con.getResponseCode() > 299) {
	            streamReader = new InputStreamReader(con.getErrorStream());
	            JSONObject error = new JSONObject();
	            error.put("error_access", "get access URL fail");
				return error;
	        } else {
	            streamReader = new InputStreamReader(con.getInputStream());
	        }
			
			BufferedReader in = new BufferedReader(streamReader);
	        String inputLine;
	        StringBuilder content = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	            content.append(inputLine);
	        }

	        in.close();
	        
	        String json = content.toString();
	        Object obj = JSONValue.parse(json);  
	        JSONObject jsonObject = (JSONObject) obj;
	        return jsonObject;
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			JSONObject error = new JSONObject();
            error.put("error_access", CommonUtil.convertExceptionToString(e));
			return error;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static String AccessUrlToString(String url){
		try {
			URL url2 = new URL(url);
			HttpURLConnection con = (HttpURLConnection) url2.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Accept", "application/json");
			
			Reader streamReader = null;
			int code = con.getResponseCode();
			if (con.getResponseCode() > 299) {
				return "{'error_access' : 'get access URL fail'}";
	        } else {
	            streamReader = new InputStreamReader(con.getInputStream());
	        }
			
			BufferedReader in = new BufferedReader(streamReader);
	        String inputLine;
	        StringBuilder content = new StringBuilder();
	        while ((inputLine = in.readLine()) != null) {
	            content.append(inputLine);
	        }

	        in.close();
	        
	        String json = content.toString();
	        
	        return json;
		} catch (Exception e) {
			log.error(CommonUtil.convertExceptionToString(e));
			return "{'error_access':'"+ CommonUtil.convertExceptionToString(e)+"'}";
		}
	}
	
	public static double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
			double dLat = (la2 - la1) * (Math.PI / 180);
			double dLon = (lo2 - lo1) * (Math.PI / 180);
			double la1ToRad = la1 * (Math.PI / 180);
			double la2ToRad = la2 * (Math.PI / 180);
			double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(la1ToRad)
			* Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
			double d = R * c;
			return d;
	}
	public static double calculateLimitLongitude(double la1,double d) {
		double la1ToRad = la1 * (Math.PI / 180);
		double c = d/R;
		double tan = Math.tan(c/2);
		double a = (tan*tan)/(1+tan);
		double sin2 = a/(Math.cos(la1ToRad)*Math.cos(la1ToRad));
		double delta = Math.sqrt(sin2)*2;
		return delta/(Math.PI/180);
	}
	public static double calculateLimitLatitude(double la1,double d) {
		double c = d/R;
		double tan = Math.tan(c/2);
		double a = (tan*tan)/(1+tan);
		double sin2 = a;
		double delta = Math.sqrt(sin2)*2;
		return delta/(Math.PI/180);
	}
	
	
//	public static Cookie getCookieByName(HttpServletRequest req, String cookieName) {
//		Cookie[] cookies = req.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals(cookieName)) {
//					return cookie;
//				}
//			}
//		}
//		return null;
//	}
//	
//	public static boolean removeCookie(HttpServletRequest req, HttpServletResponse res, String cookieName) {
//		Cookie[] cookies = req.getCookies();
//		for (int j = 0, m = cookies.length; j < m; j++) {
//			Cookie cookie = cookies[j];
//			if (cookieName.equals(cookie.getName())) {
//				cookie.setValue("");
//				cookie.setMaxAge(0);
//				cookie.setPath("/");
//				res.addCookie(cookie);
//				return true;
//			}
//		}
//		
//		return false;
//	}

}
