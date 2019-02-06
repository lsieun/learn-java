package lsieun.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class IOUtils {
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

            byte[] buffer = new byte[1024];
            int len = -1;
            while((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            byte[] bytes = out.toByteArray();
            return bytes;
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found: " + filename);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            closeQuietly(in);
        }
        return null;
    }

    public static String readHex(String filename) {
        byte[] bytes = readBytes(filename);
        if(bytes == null || bytes.length < 1) return null;

        String str = toHex(bytes);
        return str;
    }

    public static String readFormatHex(String filename) {
        String str = readHex(filename);
        if(str == null || str.length() < 1) return null;
        String upperCaseStr = str.toUpperCase();

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i=0; i<upperCaseStr.length(); i++) {
            char ch = upperCaseStr.charAt(i);
            count++;
            sb.append(ch);
            if(count % 2 == 0) {
                sb.append(" ");
            }
            if(count % 32 == 0) {
                sb.append("\r\n");
            }
        }
        return sb.toString();
    }

    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    public static void closeQuietly(Closeable c) {
        if(c != null) {
            try {
                c.close();
            } catch (IOException e) {
                // Do Nothing
            }
        }
    }
}
