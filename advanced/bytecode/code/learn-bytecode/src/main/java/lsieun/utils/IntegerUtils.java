package lsieun.utils;

public class IntegerUtils {

    public static String toBinaryString(int num) {
        return toBinaryString(num, Integer.SIZE);
    }

    public static String toBinaryString(int num, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i=size-1; i>=0; i--) {
            sb.append((num >> i) & 0x01);
        }
        return sb.toString();
    }

    public static byte[] toBytes(int value, int byteCount) {
        if(byteCount < 0 || byteCount > Integer.BYTES) return null;

        byte[] bytes = new byte[byteCount];
        for(int i=0; i<byteCount; i++) {
            int newValue = value >> (i * 8);
            byte b = (byte) (newValue & 0xff);
            bytes[byteCount - 1 - i] = b;
        }

        return bytes;
    }
}
