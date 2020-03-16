package lsieun.bit.c_use;

import java.util.Arrays;
import java.util.Formatter;
import java.util.Random;

public class D_Str_ToHex2 {
    private static final String HEX_STR = "0123456789ABCDEF";

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            byte b1 = (byte) rand.nextInt(256);
            byte b2 = (byte) rand.nextInt(256);
            byte[] bytes = new byte[]{b1,b2};

            String result1 = byteArrayToHexString1(bytes);
            String result2 = byteArrayToHexString2(bytes);

            byte[] bytes1 = hexStringToByteArray1(result1);
            byte[] bytes2 = hexStringToByteArray2(result2);

            if (!result1.equalsIgnoreCase(result2) || !Arrays.equals(bytes1, bytes2)) {
                System.out.println(b1 + ", " + b2 + ": " + result1 + ", " + result2 );
            }
        }
    }

    public static String byteArrayToHexString1(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        for (byte element : bytes) {
            int v = element & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString();
    }

    public static byte[] hexStringToByteArray1(String hexString) {
        int len = hexString.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hexString.charAt(i), 16) << 4) + Character
                    .digit(hexString.charAt(i + 1), 16));
        }
        return data;
    }

    /**
     * 这个方法，是我最近想出来的，觉得还不错。 2020年02月25日
     * @param bytes
     * @return
     */
    public static String byteArrayToHexString2(byte[] bytes) {
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

    public static byte[] hexStringToByteArray2(String hexString) {
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
