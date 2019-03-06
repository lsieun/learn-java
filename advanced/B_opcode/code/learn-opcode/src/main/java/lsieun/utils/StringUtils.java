package lsieun.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static String trim(String str) {
        if(str == null) return "";
        String strTrim = str.trim();
        return strTrim;
    }

    public static boolean isBlank(String str) {
        if(str == null) return true;
        String trimStr = str.trim();
        if("".equals(trimStr)) return true;
        return false;
    }

    public static List<String> split(String str, String regex) {
        if(isBlank(str)) return null;

        String[] array = str.split(regex);
        if(array == null || array.length < 1) return null;
        int length = array.length;

        List<String> list = new ArrayList();
        for(int i=0;i<length;i++) {
            String item = array[i];
            list.add(item);
        }
        return list;
    }

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

    public static String list2str(List<String> list, String separator) {
        if(list == null || list.size() < 1) return "";

        StringBuilder sb = new StringBuilder();

        int size = list.size();
        for(int i=0; i<size-1; i++) {
            String item = list.get(i);
            sb.append(item + separator);
        }
        String theLast = list.get(size-1);
        sb.append(theLast);

        return sb.toString();
    }
}
