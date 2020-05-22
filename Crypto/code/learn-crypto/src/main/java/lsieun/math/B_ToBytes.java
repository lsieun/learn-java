package lsieun.math;

import lsieun.utils.BigUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

import java.math.BigInteger;

public class B_ToBytes {
    public static void main(String[] args) {
        String format = "%s: %s %s";
        long[] array = {127, 128, 256};
        for (long item : array) {
            BigInteger val = BigInteger.valueOf(item);
            byte[] bytes = val.toByteArray();
            //byte[] bytes = BigUtils.toByteArray(val);
            String hex_str = HexUtils.format(bytes, HexFormat.FORMAT_FF_SPACE_FF);
            String bin_str = val.toString(2);
            String line = String.format(format, val, hex_str, bin_str);
            System.out.println(line);
        }
    }
}
