package lsieun.math;

import java.math.BigInteger;

public class A_ToBinaryString {
    public static void main(String[] args) {
        long[] array = {-1, -5, -127, -128};
        for (long item : array) {
            BigInteger val = BigInteger.valueOf(item);
            String bin_str = val.toString(2);
            String line = String.format("%3s: %s", val, bin_str);
            System.out.println(line);
        }

    }
}
