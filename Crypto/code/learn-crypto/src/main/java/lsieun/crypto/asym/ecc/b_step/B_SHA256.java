package lsieun.crypto.asym.ecc.b_step;

import lsieun.crypto.hash.sha256.SHA256Utils;
import lsieun.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class B_SHA256 {
    public static void main(String[] args) {
        String msg = "abc";
        byte[] input = msg.getBytes(StandardCharsets.UTF_8);
        byte[] digest = SHA256Utils.sha256_hash(input);
        String hex_str = ByteUtils.toHex(digest);
        System.out.println(hex_str.toUpperCase());
    }
}
