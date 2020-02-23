package lsieun.bit.a_bitwise;

import lsieun.bit.utils.BitOp;
import lsieun.bit.utils.Terminal;

public class A_BinaryOp {
    public static void main(String[] args) {
        testInt();
    }

    public static void testInt() {
        int a = 10; // 1010
        int b = 3;  // 0011

        System.out.println(String.format("%s & %s = %s", a, b, bitAnd(a, b))); // 0010
        System.out.println(String.format("%s | %s = %s", a, b, bitOr(a, b)));  // 1011
        System.out.println(String.format("%s ^ %s = %s", a, b, bitXor(a, b))); // 1001
        System.out.println();

        Terminal.printBinaryOperation(a, b, BitOp.AND);
        Terminal.printBinaryOperation(a, b, BitOp.INCLUSIVE_OR);
        Terminal.printBinaryOperation(a, b, BitOp.EXCLUSIVE_OR);

        Terminal.printBinaryOperation(1024, 996, BitOp.INCLUSIVE_OR);
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
