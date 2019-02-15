package lsieun.utils;

import java.io.UnsupportedEncodingException;

public class StringUtils {

    public static byte[] getBytes(String str, String charsetName) {

        if(str == null || "".equals(str)) return null;
        if(charsetName == null || "".equals(charsetName)) return null;

        try {
            byte[] bytes = str.getBytes(charsetName);
            return bytes;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
