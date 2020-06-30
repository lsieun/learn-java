package lsieun.tls.utils;

import lsieun.crypto.hash.hmac.HMAC;
import lsieun.crypto.hash.hmac.HMACUtils;
import lsieun.utils.ByteUtils;

public class PRFUtils {
    public static byte[] P_hash(byte[] secret, byte[] seed, int out_len, HMAC func) {
        byte[] result_bytes = new byte[out_len];
        byte[] A = seed;

        int current_len = 0;
        while (current_len < out_len) {
            A = func.apply(secret, A);
            byte[] hash_bytes = func.apply(secret, ByteUtils.concatenate(A, seed));
            int hash_length = hash_bytes.length;

            int len = (current_len + hash_length > out_len) ? (out_len - current_len) : hash_length;
            System.arraycopy(hash_bytes, 0, result_bytes, current_len, len);
            current_len += len;
        }

        return result_bytes;
    }

    public static byte[] PRF(byte[] secret, byte[] label, byte[] seed, int out_len) {
        int secret_len = secret.length;
        int half_secret_len = secret_len / 2;
        byte[] secret_first = new byte[half_secret_len];
        byte[] secret_second = new byte[half_secret_len];
        System.arraycopy(secret, 0, secret_first, 0, half_secret_len);
        System.arraycopy(secret, half_secret_len, secret_second, 0, half_secret_len);

        byte[] input = ByteUtils.concatenate(label, seed);

        byte[] hmac_md5_bytes = P_hash(secret_first, input, out_len, HMACUtils::hmac_md5);
        byte[] hmac_sha1_bytes = P_hash(secret_second, input, out_len, HMACUtils::hmac_sha1);

        return ByteUtils.xor(hmac_md5_bytes, hmac_sha1_bytes, out_len);
    }
}
