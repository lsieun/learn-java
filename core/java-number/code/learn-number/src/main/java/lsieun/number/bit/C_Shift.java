package lsieun.number.bit;

/**
 * The unsigned right shift operator '>>>' shifts zero into the leftmost position.
 */
public class C_Shift {
    private static final String FORMAT = "|%-12s|%32s|";

    public static void main(String[] args) {
        System.out.println("Left Shift:");
        print("1", 1);
        print("1 << 1", 1 << 1);
        print("1 << 2", 1 << 2);

        System.out.println(System.lineSeparator() + "Right Shift:");
        print("-128", -128);
        print("-128 >> 1", -128 >> 1);
        print("-128 >> 2", -128 >> 2);

        System.out.println(System.lineSeparator() + "Unsigned Right Shift:");
        print("-128", -128);
        print("-128 >>> 1", -128 >>> 1);
        print("-128 >>> 2", -128 >>> 2);
    }

    public static void print(String first, int second) {
        String binary_str = Integer.toBinaryString(second);
        String line = String.format(FORMAT, first, binary_str);
        System.out.println(line);
    }
}
