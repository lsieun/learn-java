package lsieun.number.str;

import lsieun.utils.ByteUtils;

public class A_ToBinary {
    private static final String FORMAT = "|%5s|%10s|";

    public static void main(String[] args) {
        testBinary();
    }

    public static void testBinary() {
        String title = String.format(FORMAT, "Byte", "Binary");
        System.out.println(title);

        byte[] array = new byte[]{-128, -127, -64, -1, 0, 1, 64, 127};
        for (byte item : array) {
            print(item);
        }
    }

    public static void print(byte b) {
        String line = String.format(FORMAT, b, ByteUtils.toBinary(b));
        System.out.println(line);
    }
}
