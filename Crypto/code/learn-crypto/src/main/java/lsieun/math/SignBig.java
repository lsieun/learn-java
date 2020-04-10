package lsieun.math;

import java.math.BigInteger;

public class SignBig {
    public static void main(String[] args) {
        byte[] bytes1 = new byte[0];
        BigInteger val = new BigInteger(0, bytes1);
        System.out.println(val);

        byte[] bytes2 = {'a', 'b', 'c'};
        BigInteger val1 = new BigInteger(1, bytes2);
        System.out.println(val1); // 6382179

        BigInteger val2 = new BigInteger(-1, bytes2);
        System.out.println(val2); // -6382179
    }
}
