package com.direction.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Pattern;

public class FileUtils {

	/**
	 *
	 * createFileFromBase64
	 *
	 * @param base64
	 * @param fileName
	 * @return File
	 * @throws Exception
	 */
	public static File createFileFromBase64(String base64, String fileName) throws Exception {

		String[] csvData = base64.split(Pattern.quote("base64,"));
		byte[] wb = null;
		if (csvData.length == 2) {
			wb = Base64.decode(csvData[1]);
		} else {
			wb = Base64.decode(csvData[0]);
		}
		File file = FileUtils.convertByteArrayToFile(fileName, wb);
		return file;
	}

	/**
	 *
	 * createFileFromBase64
	 *
	 * @param base64
	 * @return byte[]
	 * @throws Exception
	 */
	public static byte[] createByteFromBase64(String base64) throws Exception {

		String[] data = base64.split(Pattern.quote("base64,"));
		byte[] wb = null;
		if (data.length == 2) {
			wb = Base64.decode(data[1]);
		} else {
			wb = Base64.decode(data[0]);
		}
		return wb;
	}

	/**
	 * convertByteArrayToFile()
	 * 
	 * @param fileName
	 * @param input
	 * @return File
	 * @throws IOException
	 */
	public static File convertByteArrayToFile(String fileName, byte[] input) throws IOException {

		String path = System.getProperty("catalina.base");
		File file = new File(path + File.separator + fileName);
		if (file.isFile() && file.exists()) {
			file.delete();
			// result = 1;
		}
		BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
		writer.write(input);
		writer.flush();
		writer.close();

		return file;
	}
}
