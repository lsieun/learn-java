package lsieun.hash.hmac.b_test;

import lsieun.hash.hmac.MACSample;
import lsieun.hash.hmac.MACUtils;
import lsieun.utils.ByteUtils;

public class C_Self_HMAC_SHA256 {
    public static void main(String[] args) {
        byte[] mac_bytes = MACUtils.hmac_sha256(MACSample.key_bytes, MACSample.data);
        System.out.println(ByteUtils.toHex(mac_bytes));
    }
}
