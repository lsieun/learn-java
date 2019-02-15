package lsieun.chat;

import lsieun.utils.IntegerUtils;

public class Test {
    public static void main(String[] args) {
        // 07 e3
        int sum = 7 * 256 + 14 * 16 + 3;
        System.out.println(sum);
        System.out.println(IntegerUtils.toBinaryString(2019, 16));
        System.out.println(IntegerUtils.toBinaryString(9999));
        System.out.println(IntegerUtils.toBinaryString(9999, 16));
        System.out.println(IntegerUtils.toBinaryString(52,8));
    }
}
