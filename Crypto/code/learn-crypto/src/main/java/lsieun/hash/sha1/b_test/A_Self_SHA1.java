package lsieun.hash.sha1.b_test;

import lsieun.hash.sha1.SHA1Example;
import lsieun.hash.sha1.SHA1Utils;
import lsieun.utils.ByteUtils;

import java.nio.charset.StandardCharsets;

public class A_Self_SHA1 {
    public static void main(String[] args) {
        byte[] bytes = SHA1Example.example_2.getBytes(StandardCharsets.UTF_8);

        byte[] digest = SHA1Utils.sha1_hash(bytes, bytes.length);
        System.out.println(ByteUtils.toHex(digest));
    }
}
