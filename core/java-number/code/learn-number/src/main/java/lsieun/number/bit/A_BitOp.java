package lsieun.number.bit;

public class A_BitOp {
    public static void main(String[] args) {
        testInt();
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
}
