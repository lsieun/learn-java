package lsieun.math;

import java.math.BigInteger;

public class MathOrder {
    public static void main(String[] args) {

        BigInteger x1 = new BigInteger("1");
        BigInteger x2 = new BigInteger("2");
        BigInteger x3 = new BigInteger("3");
        BigInteger x4 = new BigInteger("4");
        BigInteger x5 = new BigInteger("5");

        BigInteger result = x5.subtract(x3).multiply(x4);
        System.out.println(result);

        BigInteger result2 = x5.subtract(x2).subtract(x2).mod(x3);
        System.out.println(result2);
    }
}
