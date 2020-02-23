package lsieun.bit.c_use;

import java.util.Random;

public class D_Str_ToHex {
    private static final char[] chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            byte b1 = (byte) rand.nextInt(256);
            byte b2 = (byte) rand.nextInt(256);
            byte[] bytes = new byte[]{b1,b2};

            String result1 = toHex1(bytes);
            String result2 = toHex2(bytes);
            String result3 = toHex3(bytes);

            if (!result1.equalsIgnoreCase(result2) || !result2.equalsIgnoreCase(result3)) {
                System.out.println(b1 + ", " + b2 + ": " + result1 + ", " + result2 + ", " + result3);
            }
        }
    }

    public static String toHex1(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            sb.append(Integer.toHexString((bytes[i] & 0xFF) | 0x100).substring(1, 3));
        }
        return sb.toString();
    }

    public static String toHex2(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < bytes.length; ++i) {
            // NOTE: 这里如果不加上0x100，那么生成的十六进制字符串可能是一个字符，也可能是两个字符
            sb.append(Integer.toString((bytes[i] & 0xFF) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    public static String toHex3(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            // NOTE: 注意不能直接使用下面的写法，当b为负数时，会出现异常，详细参考lsieun.bit.d_pitfall.C_Byte_Shift
            //sb.append(chars[b >>> 4]);
            sb.append(chars[(b & 0xFF) >> 4]);
            sb.append(chars[b & 0x0F]);
        }
        return sb.toString();
    }

    public static String toHex4(byte b) {
        int hi = (b >> 4) & 0x0f;
        int lo = (b >> 0) & 0x0f;

        StringBuilder sb = new StringBuilder();
        sb.append(chars[hi]);
        sb.append(chars[lo]);
        return sb.toString();
    }
}
