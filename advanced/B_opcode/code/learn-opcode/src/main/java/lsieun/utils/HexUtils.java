package lsieun.utils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class HexUtils {

    public static final Map<Character, Integer> hex2IntMap;

    static {
        hex2IntMap = new HashMap<Character, Integer>();
        hex2IntMap.put('0', Integer.valueOf(0));
        hex2IntMap.put('1', Integer.valueOf(1));
        hex2IntMap.put('2', Integer.valueOf(2));
        hex2IntMap.put('3', Integer.valueOf(3));
        hex2IntMap.put('4', Integer.valueOf(4));
        hex2IntMap.put('5', Integer.valueOf(5));
        hex2IntMap.put('6', Integer.valueOf(6));
        hex2IntMap.put('7', Integer.valueOf(7));
        hex2IntMap.put('8', Integer.valueOf(8));
        hex2IntMap.put('9', Integer.valueOf(9));
        hex2IntMap.put('A', Integer.valueOf(10));
        hex2IntMap.put('B', Integer.valueOf(11));
        hex2IntMap.put('C', Integer.valueOf(12));
        hex2IntMap.put('D', Integer.valueOf(13));
        hex2IntMap.put('E', Integer.valueOf(14));
        hex2IntMap.put('F', Integer.valueOf(15));
    }


    public static int toInt(String hexCode) {
        hexCode = hexCode.toUpperCase();

        int base = 16;
        int sum = 0;

        for(int i=0; i<hexCode.length(); i++) {
            char ch = hexCode.charAt(i);
            Integer value = hex2IntMap.get(ch);

            sum = sum * base + value;
        }
        return sum;
    }

    public static float toFloat(String hexCode) {
        Long i = Long.parseLong(hexCode, 16);
        Float f = Float.intBitsToFloat(i.intValue());
        return f;
    }

    public static double toDouble(String hexCode) {
        Long i = Long.parseLong(hexCode, 16);
        Double value = Double.longBitsToDouble(i);
        return value;
    }

    public static String toUtf8(String hexCode) {
        hexCode = hexCode.toLowerCase();
        byte[] bytes = toBytes(hexCode);
        String str = null;
        try {
            str = new String(bytes, "UTF8");
        } catch (UnsupportedEncodingException e) {
            str = null;
            e.printStackTrace();
        }
        return str;
    }

    public static byte[] toBytes(String hexCode) {
        hexCode = hexCode.toLowerCase();
        int len = (hexCode.length() / 2);
        byte[] result = new byte[len];
        char[] array = hexCode.toCharArray();
        for (int i = 0; i < len; i++) {
            int pos = i * 2;
            result[i] = (byte) (toByte(array[pos]) << 4 | toByte(array[pos + 1]));
        }
        return result;
    }

    private static byte toByte(char c) {
        byte b = (byte) "0123456789abcdef".indexOf(c);
        return b;
    }

    public static String format(String hexCode) {
        if(hexCode == null || hexCode.length() < 1) return null;

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(int i=0; i<hexCode.length(); i++) {
            char ch = hexCode.charAt(i);
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
}
