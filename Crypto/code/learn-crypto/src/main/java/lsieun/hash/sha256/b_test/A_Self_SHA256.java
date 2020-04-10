package lsieun.hash.sha256.b_test;

import lsieun.hash.sha256.SHA256Example;
import lsieun.hash.sha256.SHA256Utils;
import lsieun.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class A_Self_SHA256 {
    public static void main(String[] args) {
        byte[] bytes = SHA256Example.example_2.getBytes(StandardCharsets.UTF_8);

        byte[] digest = SHA256Utils.sha256_hash(bytes, bytes.length);
        System.out.println(ByteUtils.toHex(digest));
    }
}
