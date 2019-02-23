package lsieun.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    public static byte[] readBytes(String filename) {
        System.out.println("Read File: " + filename);

        File file = new File(filename);
        if(!file.exists()) {
            return null;
        }

        InputStream in = null;

        try {
            in = new FileInputStream(file);
            in = new BufferedInputStream(in);

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            IOUtils.copy(in, out);

            byte[] bytes = out.toByteArray();
            return bytes;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            IOUtils.closeQuietly(in);
        }
        return null;
    }

//    public static String readHex(String filename) {
//        byte[] bytes = readBytes(filename);
//        if(bytes == null || bytes.length < 1) return null;
//
//        String str = ByteUtils.toHex(bytes);
//        return str;
//    }

//    public static String readFormatHex(String filename) {
//        byte[] bytes = readBytes(filename);
//        String str = ByteUtils.toHex(bytes);
//        if(str == null || str.length() < 1) return null;
//        String upperCaseStr = str.toUpperCase();
//
//        StringBuilder sb = new StringBuilder();
//        int count = 0;
//        for(int i=0; i<upperCaseStr.length(); i++) {
//            char ch = upperCaseStr.charAt(i);
//            count++;
//            sb.append(ch);
//            if(count % 2 == 0) {
//                sb.append(" ");
//            }
//            if(count % 32 == 0) {
//                sb.append("\r\n");
//            }
//        }
//        return sb.toString();
//    }


    public static void writeBytes(String filename, byte[] bytes) {
        File file = new File(filename);
        File dir = file.getParentFile();
        if(!dir.exists()) {
            dir.mkdirs();
        }

        OutputStream out = null;
        try {
            out = new FileOutputStream(filename);
            out = new BufferedOutputStream(out);

            out.write(bytes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(out);
        }
    }
}
