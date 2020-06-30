package lsieun.crypto.hash.hmac.b_test;

import lsieun.crypto.hash.hmac.MACSample;
import lsieun.crypto.hash.hmac.HMACUtils;
import lsieun.crypto.hash.md5.MD5Utils;
import lsieun.utils.ByteUtils;

public class A_Self_HMAC_MD5 {
    public static void main(String[] args) {
        byte[] mac_bytes = HMACUtils.hmac(MACSample.key_bytes, MACSample.data, MD5Utils::md5_hash);
        System.out.println(ByteUtils.toHex(mac_bytes));
    }
}
