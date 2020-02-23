package lsieun.bit.d_pitfall;

import lsieun.bit.utils.ByteUtils;

public class A_Bitwise_Shift_Order {
    public static void main(String[] args) {
        int value = 11;
        int mask = 0x03;
        int shift = 1;

        System.out.println(ByteUtils.toBinary(value, 8));
        System.out.println(ByteUtils.toBinary(mask, 8));

        int result1 = value & mask << shift;
        System.out.println(result1);
        System.out.println(ByteUtils.toBinary(result1, 8));

        int result2 = (value & mask) << shift;
        System.out.println(result2);
        System.out.println(ByteUtils.toBinary(result2, 8));

        int result3 = value & (mask << shift);
        System.out.println(result3);
        System.out.println(ByteUtils.toBinary(result3, 8));
    }
}
