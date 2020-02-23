package lsieun.bit.d_pitfall;

import lsieun.bit.utils.ByteUtils;

public class C_Byte_Shift {
    public static void main(String[] args) {
        byte b = -1;
        System.out.println(ByteUtils.toBinary(b, false) + " = " + b);

        int shift = 4;

        // NOTE: 这里面比较tricky的地方，当byte值为负数的情况下，将byte类型转换为int类型的过程中，bit位会发生很大的变化
        int result1 = b >> shift;
        System.out.println(ByteUtils.toBinary(result1, 32) + " = " + result1);

        int result2 = b >>> shift;
        System.out.println(ByteUtils.toBinary(result2, 32) + " = " + result2);

        // NOTE: 这里非常关键的一步就是 b & 0xFF
        int result3 = (b & 0xFF) >> shift;
        System.out.println(ByteUtils.toBinary(result3, 32) + " = " + result3);
    }
}
