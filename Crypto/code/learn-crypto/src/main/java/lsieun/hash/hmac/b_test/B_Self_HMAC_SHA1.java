package lsieun.hash.hmac.b_test;

import lsieun.hash.hmac.MACSample;
import lsieun.hash.hmac.MACUtils;
import lsieun.utils.ByteUtils;

public class B_Self_HMAC_SHA1 {
    public static void main(String[] args) {
        byte[] mac_bytes = MACUtils.hmac_sha1(MACSample.key_bytes, MACSample.data);
        System.out.println(ByteUtils.toHex(mac_bytes));
    }
}
