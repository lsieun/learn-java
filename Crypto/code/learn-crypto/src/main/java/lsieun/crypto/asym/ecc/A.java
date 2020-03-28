package lsieun.crypto.asym.ecc;

import java.math.BigInteger;

public class A {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("55066263022277343669578718895168534326250603453777594175500187360389116729240");
        BigInteger result = a.pow(3).add(new BigInteger("7"));
        System.out.println(result);
        BigInteger y = new BigInteger("32670510020758816978083085130507043184471273380659243275938904335757337482424");
        BigInteger result2 = y.pow(2);
        System.out.println(result2);
    }
}
