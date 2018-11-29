package lsieun.number;

public class Number2Radix {
    public static void main(String[] args) {
        display('A');
        display('a');
        display('宋');

        getStringRadix();
    }

    public static void display(char ch) {
        System.out.println(ch);
        System.out.println("\tDEC: " + (int)ch + "(十进制)");
        System.out.println("\tHEX:" + Integer.toHexString(ch) + "(十六进制)");
        System.out.println("\tBIN:" + Integer.toBinaryString(ch) + "(二进制)");
        System.out.println("\tOCT:" + Integer.toOctalString(ch) + "(八进制)");
    }

    public static void getStringRadix() {
         /* returns the string representation of the
          unsigned integer in concern radix*/
        System.out.println("Binary eqivalent of 100 = " + Integer.toString(100, 2));
        System.out.println("Octal eqivalent of 100 = " + Integer.toString(100, 8));
        System.out.println("Decimal eqivalent of 100 = " + Integer.toString(100, 10));
        System.out.println("Hexadecimal eqivalent of 100 = " + Integer.toString(100, 16));

        // 自定义进制
        System.out.println("五进制 of 100 = " + Integer.toString(100, 5));
    }
}
