package lsieun.utils;

public class ByteUtils {

    public static String toHex(byte[] bytes) {
        if(bytes == null || bytes.length < 1) return null;

        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1,3));
        }
        return sb.toString();
    }
}
