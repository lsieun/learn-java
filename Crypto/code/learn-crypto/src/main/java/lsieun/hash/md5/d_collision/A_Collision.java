package lsieun.hash.md5.d_collision;

import lsieun.hash.md5.MD5Example;
import lsieun.utils.ByteUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class A_Collision {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String str1 = MD5Example.collision_str_1;
        String str2 = MD5Example.collision_str_2;

        System.out.println("str equals: " + str1.equals(str2));

        String str1_md5 = getMD5(str1);
        String str2_md5 = getMD5(str2);
        System.out.println("md5 equals: " + str1_md5.equals(str2_md5));
        System.out.println(str1_md5);
        System.out.println(str2_md5);
    }

    public static String getMD5(String hex_str) throws NoSuchAlgorithmException {
        byte[] bytes = ByteUtils.fromHex(hex_str);

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(bytes);
        byte[] digest = md.digest();
        return ByteUtils.toHex(digest);
    }
}
