package lsieun.math;

import java.math.BigInteger;

public class Mod_VS_Remainder {
    public static void main(String[] args) {
        BigInteger a = BigInteger.valueOf(-5);
        BigInteger b = BigInteger.valueOf(13);

        BigInteger remainder = a.remainder(b);
        System.out.println(remainder);

        BigInteger mod = a.mod(b);
        System.out.println(mod);
    }
}
