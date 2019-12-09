package com.direction.common.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileUtil {

	public static void store(String filename, StringBuilder sb) throws FileNotFoundException, IOException {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(filename)));
            bos.write(sb.toString().getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }
	
	public static void store(String filename, String content) throws FileNotFoundException, IOException {
        BufferedOutputStream bos = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(new File(filename)));
            bos.write(content.getBytes());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (bos != null)
                try {
                    bos.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }
	
}
