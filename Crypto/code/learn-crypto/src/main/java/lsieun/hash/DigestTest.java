package lsieun.hash;

import lsieun.hash.hmac.MACSample;
import lsieun.utils.ByteUtils;

public class DigestTest {
    public static void main(String[] args) {
        byte[] key_bytes = MACSample.key_bytes;
        byte[] input = MACSample.data;
        byte[] hmac_bytes = Digest.hmac(key_bytes, input, DigestCtx::new_sha256_digest);
        System.out.println(ByteUtils.toHex(hmac_bytes));
    }
}
