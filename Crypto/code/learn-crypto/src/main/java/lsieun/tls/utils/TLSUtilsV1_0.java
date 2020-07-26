package lsieun.tls.utils;

import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.hash.md5.MD5Utils;
import lsieun.crypto.hash.sha1.SHA1Utils;
import lsieun.utils.ByteUtils;

import java.util.Arrays;

public class TLSUtilsV1_0 {
    public static boolean verify_server_key_exchange_signature(
            byte[] client_random, byte[] server_random, byte[] message,
            RSAKey public_key, byte[] signature) {
        byte[] input = ByteUtils.concatenate(client_random, server_random, message);

        byte[] md5_digest = MD5Utils.md5_hash(input);
        byte[] sha1_digest = SHA1Utils.sha1_hash(input);
        byte[] digest = ByteUtils.concatenate(md5_digest, sha1_digest);

        byte[] decrypted_bytes = RSAUtils.rsa_decrypt(signature, public_key);
        return Arrays.equals(digest, decrypted_bytes);
    }
}
