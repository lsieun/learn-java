package lsieun.number.radix;

public class A_Difference_Radix {
    public static void main(String[] args) {
        getStringRadix();
    }

    public static void getStringRadix() {
         /* returns the string representation of the
          unsigned integer in concern radix*/
        System.out.println("Binary equivalent of 100 = " + Integer.toString(100, 2));
        System.out.println("Octal equivalent of 100 = " + Integer.toString(100, 8));
        System.out.println("Decimal equivalent of 100 = " + Integer.toString(100, 10));
        System.out.println("Hexadecimal equivalent of 100 = " + Integer.toString(100, 16));
    }
}
