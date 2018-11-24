package lsieun.util;

public class ByteUtil {
    public static String toHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }

    public static String toHex2(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
