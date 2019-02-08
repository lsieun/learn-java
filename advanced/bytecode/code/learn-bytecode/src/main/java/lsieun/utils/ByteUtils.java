package lsieun.utils;

public class ByteUtils {

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
