package lsieun.utils;

import java.math.BigInteger;

public class BigUtils {
    public static BigInteger toBigInteger(char[] chars) {
        int length = chars.length;
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = (byte) chars[i];
        }
        return new BigInteger(1, bytes);
    }
}
