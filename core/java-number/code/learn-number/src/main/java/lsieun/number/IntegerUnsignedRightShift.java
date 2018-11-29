package lsieun.number;

//|-128        |11111111111111111111111110000000|
//|-128 >>> 1  | 1111111111111111111111111000000|
//
//|-128        |11111111111111111111111110000000|
//|-128 >> 1   |11111111111111111111111111000000|
public class IntegerUnsignedRightShift {
    public static void main(String[] args) {
        System.out.println("The unsigned right shift operator '>>>' shifts zero into the leftmost position.");
        System.out.printf("|%-12s|%32s|%n", "-128", Integer.toBinaryString(-128));
        System.out.printf("|%-12s|%32s|%n", "-128 >>> 1", Integer.toBinaryString(-128 >>> 1));
        System.out.println();
        System.out.printf("|%-12s|%32s|%n", "-128", Integer.toBinaryString(-128));
        System.out.printf("|%-12s|%32s|%n", "-128 >> 1", Integer.toBinaryString(-128 >> 1));
    }
}
