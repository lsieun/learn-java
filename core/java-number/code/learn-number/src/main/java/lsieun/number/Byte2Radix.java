package lsieun.number;

import lsieun.utils.ByteUtils;

public class Byte2Radix {
    public static void main(String[] args) {
        testBinary();
        System.out.println("=========================\n");

        testHex();
        System.out.println("=========================\n");
    }

    public static void testBinary() {
        System.out.printf("|%5s|%10s|%n", "Byte", "Binary");
        System.out.printf("|% 5d|%10s|%n", 0, ByteUtils.toBinary((byte)0));
        System.out.printf("|% 5d|%10s|%n", 1, ByteUtils.toBinary((byte)1));
        System.out.printf("|% 5d|%10s|%n", -1, ByteUtils.toBinary((byte)-1));
        System.out.printf("|% 5d|%10s|%n", 127, ByteUtils.toBinary((byte)127));
        System.out.printf("|% 5d|%10s|%n", -127, ByteUtils.toBinary((byte)-127));
        System.out.printf("|% 5d|%10s|%n", -128, ByteUtils.toBinary((byte)-128));
        System.out.printf("|% 5d|%10s|%n", -64, ByteUtils.toBinary((byte)-64));

//      Output:
//      |Value|    Binary|
//      |    0| 0000 0000|
//      |    1| 0000 0001|
//      |   -1| 1111 1111|
//      |  127| 0111 1111|
//      | -127| 1000 0001|
//      | -128| 1000 0000|
    }

    public static void testHex() {
        System.out.printf("|%5s|%10s|%n", "Byte", "Hex");
        System.out.printf("|% 5d|%10s|%n", 0, getHexString((byte)0));
        System.out.printf("|% 5d|%10s|%n", 1, getHexString((byte)1));
        System.out.printf("|% 5d|%10s|%n", -1, getHexString((byte)-1));
        System.out.printf("|% 5d|%10s|%n", 15, getHexString((byte)15));
        System.out.printf("|% 5d|%10s|%n", 65, getHexString((byte)65));
        System.out.printf("|% 5d|%10s|%n", 127, getHexString((byte)127));
        System.out.printf("|% 5d|%10s|%n", -127, getHexString((byte)-127));
        System.out.printf("|% 5d|%10s|%n", -128, getHexString((byte)-128));


        //| Byte|       Hex|
        //|    0|        00|
        //|    1|        01|
        //|   -1|        ff|
        //|   15|        0f|
        //|   65|        41|
        //|  127|        7f|
        //| -127|        81|
        //| -128|        80|
    }

    public static String getHexString(byte b) {
        char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int hi = (b >> 4) & 0x0f;
        int lo = (b >> 0) & 0x0f;

        StringBuilder sb = new StringBuilder();
        sb.append(chars[hi]);
        sb.append(chars[lo]);
        return sb.toString();
    }
}
