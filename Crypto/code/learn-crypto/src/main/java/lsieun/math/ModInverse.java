package lsieun.math;

import java.math.BigInteger;

public class ModInverse {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("7");
        BigInteger b = new BigInteger("20");
        BigInteger c = a.modInverse(b);
        System.out.println(c);
    }
}
