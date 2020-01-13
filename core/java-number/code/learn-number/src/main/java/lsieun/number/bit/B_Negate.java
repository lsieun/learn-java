package lsieun.number.bit;

public class B_Negate {
    public static void main(String[] args) {
        print_long(0L);
        print_long(~0L);
        print_long(~0L >>> 32);
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
