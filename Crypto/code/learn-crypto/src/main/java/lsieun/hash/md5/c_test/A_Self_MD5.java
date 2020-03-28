package lsieun.hash.md5.c_test;

import lsieun.hash.md5.MD5Example;
import lsieun.hash.md5.MD5Utils;
import lsieun.utils.ByteUtils;

public class A_Self_MD5 {

    public static void main(String[] args) {
//        byte[] bytes = MD5Example.input_52_bytes;
        byte[] bytes = ByteUtils.fromHex(MD5Example.collision_str_2);

        byte[] digest_bytes = MD5Utils.md5_hash(bytes, bytes.length);
        String md5 = ByteUtils.toHex(digest_bytes);
        System.out.println(md5);
    }
}
