package lsieun.hash.hmac.b_test;

import lsieun.hash.hmac.MACSample;
import lsieun.hash.hmac.HMACUtils;
import lsieun.hash.sha1.SHA1Utils;
import lsieun.utils.ByteUtils;

public class B_Self_HMAC_SHA1 {
    public static void main(String[] args) {
        byte[] mac_bytes = HMACUtils.hmac(MACSample.key_bytes, MACSample.data, SHA1Utils::sha1_hash);
        System.out.println(ByteUtils.toHex(mac_bytes));
    }
}
