package lsieun.hash.md5.c_test;

import lsieun.hash.md5.MD5Example;
import lsieun.utils.ByteUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class C_JDK_MD5 {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        byte[] bytes = ByteUtils.fromHex(MD5Example.collision_str_2);

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();
        System.out.println(ByteUtils.toHex(digest));
    }
}
