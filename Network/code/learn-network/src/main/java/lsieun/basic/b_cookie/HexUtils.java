package lsieun.basic.b_cookie;

import java.util.Formatter;

public class HexUtils {
    private static final String HEX_STR = "0123456789ABCDEF";

    public static String byteArrayToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter fm = new Formatter(sb);
        for (byte b : bytes) {
            int value = b & 0xFF;
            int hi = value >> 4;
            int lo = value & 0x0F;
            fm.format("%c%c", HEX_STR.charAt(hi), HEX_STR.charAt(lo));
        }
        return sb.toString();
    }

    public static  byte[] hexStringToByteArray(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            char hi_ch = hexString.charAt(i);
            char lo_ch = hexString.charAt(i + 1);
            int hi = HEX_STR.indexOf(hi_ch);
            int lo = HEX_STR.indexOf(lo_ch);
            data[i / 2] = (byte) (hi << 4 | lo);
        }
        return data;
    }
}
