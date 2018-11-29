package lsieun.util;

public class IntegerUtil {
    public static String toBinary(int num) {
        return toBinary(num, Integer.SIZE);
    }

    public static String toBinary(int num, int size) {
        StringBuilder sb = new StringBuilder();
        for (int i=size-1; i>=0; i--) {
            sb.append((num >> i) & 0x01);
        }
        return sb.toString();
    }
}
