package lsieun.hash.hmac.b_test;

import lsieun.hash.hmac.MACSample;
import lsieun.hash.hmac.HMACUtils;
import lsieun.hash.sha256.SHA256Utils;
import lsieun.utils.ByteUtils;

public class C_Self_HMAC_SHA256 {
    public static void main(String[] args) {
        byte[] mac_bytes = HMACUtils.hmac(MACSample.key_bytes, MACSample.data, SHA256Utils::sha256_hash);
        System.out.println(ByteUtils.toHex(mac_bytes));
    }
}
