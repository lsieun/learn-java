package lsieun.bit.c_use;

import lsieun.bit.utils.ByteUtils;

public class B_And_0XFF {
    private static final String FORMAT = "|%10s|%10s|%32s|%8s|";

    public static void main(String[] args) {
        System.out.println("Byte的取值范围是：-128~127");
        System.out.println("Byte Min Value: " + Byte.MIN_VALUE);  // -128
        System.out.println("Byte Max Value: " + Byte.MAX_VALUE);  // 127
        System.out.println("================================" + System.lineSeparator());

        System.out.println("num & 0xff 操作前后的变化");
        and0XFF();
        System.out.println(System.lineSeparator());

        System.out.println("观察二进制的形式是否变化");
        and0XFFWithBinary();

    }

    public static void and0XFF() {
        System.out.printf("|%6s|%6s|%n", "Before", "After");
        System.out.printf("|%6d|%6d|%n", -128, (-128 & 0xff));
        System.out.printf("|%6d|%6d|%n", -127, (-127 & 0xff));
        System.out.printf("|%6d|%6d|%n", -1, (-1 & 0xff));
        System.out.printf("|%6d|%6d|%n", 0, (0 & 0xff));
        System.out.printf("|%6d|%6d|%n", 1, (1 & 0xff));
        System.out.printf("|%6d|%6d|%n", 127, (127 & 0xff));
    }

    public static void and0XFFWithBinary() {
        System.out.println("-----------------------------------------------------------------");
        String title = String.format(FORMAT, "Byte", "Binary", "byte & 0xff (32bit)", "Integer");
        System.out.println(title);

        int[] array = new int[]{-128, -127, -1, 0, 1, 127};
        for (int i = 0; i < array.length; i++) {
            byte b = (byte) array[i];
            String line = String.format(FORMAT, b, ByteUtils.toBinary(b), ByteUtils.toBinary((b & 0xff)), (b & 0xff));
            System.out.println(line);
        }
        System.out.println("-----------------------------------------------------------------");
    }

}
