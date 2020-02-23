package lsieun.number.radix;

public class B_Char_Radix {
    private static final String FORMAT = "    %s: %s(%s)";

    public static void main(String[] args) {
        display('A');
        display('a');
        display('宋');
    }

    public static void display(char ch) {
        System.out.println(ch);
        System.out.println(String.format(FORMAT,"DEC", (int)ch, "十进制"));
        System.out.println(String.format(FORMAT,"HEX", Integer.toHexString(ch), "十六进制"));
        System.out.println(String.format(FORMAT,"BIN", Integer.toBinaryString(ch), "二进制"));
        System.out.println(String.format(FORMAT,"OCT", Integer.toOctalString(ch), "八进制"));
    }

}
