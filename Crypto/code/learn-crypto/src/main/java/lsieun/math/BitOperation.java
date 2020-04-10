package lsieun.math;

import java.math.BigInteger;

public class BitOperation {
    public static void main(String[] args) {
        BigInteger val = new BigInteger("20");
        System.out.println(val.bitLength());
        System.out.println(val.bitCount());
        System.out.println(val.toString(2));
        for (int i=0;i<val.bitLength();i++) {
            boolean flag = val.testBit(i);
            System.out.println(i + " : " + flag);
        }

    }
}
