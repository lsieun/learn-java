package lsieun.crypto.asym.ecc.ecdsa;

import lsieun.crypto.hash.dsa_ecc.ECDSASample;
import lsieun.utils.BigUtils;

import java.math.BigInteger;

public class TestA {
    public static void main(String[] args) {
        BigInteger p = BigUtils.toBigInteger(ECDSASample.P);
        BigInteger a = new BigInteger("3");
        BigInteger b = BigUtils.toBigInteger(ECDSASample.b);

        BigInteger gx = BigUtils.toBigInteger(ECDSASample.gx);
        BigInteger gy = BigUtils.toBigInteger(ECDSASample.gy);

        BigInteger w = BigUtils.toBigInteger(ECDSASample.w);

        BigInteger[] p1 = {gx, gy};
        BigInteger[] p2 = EcOperations.pointMultiply(p1, p, a, w);
        System.out.println(p2[0].toString(16));
        System.out.println(p2[1].toString(16));
    }
}
