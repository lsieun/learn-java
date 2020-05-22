package lsieun.tls.b_test;

import lsieun.hash.hmac.HMACUtils;
import lsieun.tls.PRF;
import lsieun.utils.ByteUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class PRF_Test_1 {
    public static void main(String[] args) {
        byte[] secret_first = new byte[]{'a', 'b'};
        byte[] secret_second = new byte[]{'c', 'd'};
        byte[] seed = new byte[]{'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l'};
        int length = 40;

        byte[] hmac_md5_bytes = PRF.P_hash(secret_first, seed, length, HMACUtils::hmac_md5);
        System.out.println("HMAC MD5: " + HexUtils.format(hmac_md5_bytes, HexFormat.FORMAT_FF_SPACE_FF));

        byte[] hmac_sh1_bytes = PRF.P_hash(secret_second, seed, length, HMACUtils::hmac_sha1);
        System.out.println("HMAC SHA1: " + HexUtils.format(hmac_sh1_bytes, HexFormat.FORMAT_FF_SPACE_FF));

         byte[] bytes = ByteUtils.xor(hmac_md5_bytes, hmac_sh1_bytes, length);
        System.out.println("Result: " + HexUtils.format(bytes, HexFormat.FORMAT_FF_SPACE_FF));
    }
}
