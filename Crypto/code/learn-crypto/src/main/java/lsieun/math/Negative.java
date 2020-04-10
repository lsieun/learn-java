package lsieun.math;

import java.math.BigInteger;

public class Negative {
    public static void main(String[] args) {
        testRemainder();
    }

    public static void testAdd() {
        BigInteger a = new BigInteger("-5");
        BigInteger b = new BigInteger("3");
        BigInteger c = a.add(b);
        System.out.println(c);
    }

    public static void testMulti() {
        BigInteger a = new BigInteger("-5");
        BigInteger b = new BigInteger("3");
        BigInteger c = a.multiply(b);
        System.out.println(c);
    }

    public static void testRemainder() {
        BigInteger a = new BigInteger("-5");
        BigInteger b = new BigInteger("3");
        BigInteger c = a.remainder(b);
        System.out.println(c);
    }
}
