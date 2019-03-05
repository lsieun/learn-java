package lsieun.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

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
