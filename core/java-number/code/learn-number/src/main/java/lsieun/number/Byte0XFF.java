package lsieun.number;

import lsieun.utils.ByteUtils;
import lsieun.utils.IntegerUtils;

public class Byte0XFF {
    public static void main(String[] args) {
        System.out.println("Byte的取值范围是：-128~127");
        System.out.println("Byte Max Value: " + Byte.MAX_VALUE);  // 127
        System.out.println("Byte Min Value: " + Byte.MIN_VALUE);  // -128
        System.out.println("================================\n");

        System.out.println("& 0xff 操作前后的变化");
        and0XFF();

        System.out.println("观察二进制的形式是否变化");
        and0XFFWithBinary();

        add0X100();
    }

    public static void and0XFF() {
        System.out.printf("|%6s|%6s|%n", "Before", "After");
        System.out.printf("|%6d|%6d|%n", -128, (-128 & 0xff));
        System.out.printf("|%6d|%6d|%n", -127, (-127 & 0xff));
        System.out.printf("|%6d|%6d|%n", -1, (-1 & 0xff));
        System.out.printf("|%6d|%6d|%n", 0, (0 & 0xff));
        System.out.printf("|%6d|%6d|%n", 1, (1 & 0xff));
        System.out.printf("|%6d|%6d|%n", 127, (127 & 0xff));
        System.out.println("================================\n");
    }

    public static void and0XFFWithBinary() {
        System.out.printf("|%10s|%10s|%32s|%8s|%n", "Byte", "Binary", "byte & 0xff (32bit)", "Integer");

        int[] array = new int[] {0, 1, -1, 127, -127, -128};
        for (int i=0; i<array.length; i++) {
            byte b = (byte)array[i];
            System.out.printf("|%10d|%10s|%32s|%8s|%n", b, ByteUtils.toBinary(b), IntegerUtils.toBinary((b & 0xff)), (b & 0xff));
        }
        System.out.println("================================\n");
    }

    public static void add0X100() {
        System.out.println("0x100 = " + 0x100); // 256
        System.out.println("+0x100之前的十六进制");
        System.out.println(Integer.toString(0, 16));   // 0
        System.out.println(Integer.toString(15, 16));  // f
        System.out.println(Integer.toString(16, 16));  // 10
        System.out.println(Integer.toString(255, 16)); // ff
        System.out.println("+0x100之后的十六进制");
        System.out.println(Integer.toString(0 + 0x100, 16));  // 100
        System.out.println(Integer.toString(15 + 0x100, 16)); // 10f
        System.out.println(Integer.toString(16 + 0x100, 16)); // 110
        System.out.println(Integer.toString(255 + 0x100, 16));// 1ff
        System.out.println("================================\n");
    }


}
