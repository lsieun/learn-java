package lsieun.number;

public class BitOp {
    public static void main(String[] args) {
        testInt();
        testUnsignedShift();
    }

    public static void testInt() {
        int a = 10; // 1010
        int b = 3;  // 0011

        System.out.println(String.format("%s & %s = %s", a, b, bitAnd(a, b))); // 0010
        System.out.println(String.format("%s | %s = %s", a, b, bitOr(a, b)));  // 1011
        System.out.println(String.format("%s ^ %s = %s", a, b, bitXor(a, b))); // 1001
    }

    public static int bitAnd(int a, int b) {
        return a & b;
    }

    public static int bitOr(int a, int b) {
        return a | b;
    }

    public static int bitXor(int a, int b) {
        return a ^ b;
    }

    public static void testUnsignedShift() {
        byte b = (byte)-128;
        System.out.println(b >> 1);
        System.out.println(b >>> 1);
    }
}
