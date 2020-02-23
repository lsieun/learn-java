package lsieun.bit.a_bitwise;

import lsieun.bit.utils.BitOp;
import lsieun.bit.utils.Terminal;

public class B_UnaryOp {
    public static void main(String[] args) {
        print_long(0L);
        print_long(~0L);
        print_long(~0L >>> 32);

        // -1, 0, 1
        Terminal.printUnaryOperator(-1, BitOp.COMPLEMENT);
        Terminal.printUnaryOperator(0, BitOp.COMPLEMENT);
        Terminal.printUnaryOperator(1, BitOp.COMPLEMENT);
        // 127 and 128
        Terminal.printUnaryOperator(127, BitOp.COMPLEMENT);
        Terminal.printUnaryOperator(128, BitOp.COMPLEMENT);
        // min and max
        Terminal.printUnaryOperator(Integer.MIN_VALUE, BitOp.COMPLEMENT);
        Terminal.printUnaryOperator(Integer.MAX_VALUE, BitOp.COMPLEMENT);
    }

    public static void print_long(long value) {
        String str = Long.toBinaryString(value);

        System.out.println("=======================");
        System.out.println("Value : " + value);
        System.out.println("Binary: " + str);
        System.out.println("Length: " + str.length());
        System.out.println();
    }
}
