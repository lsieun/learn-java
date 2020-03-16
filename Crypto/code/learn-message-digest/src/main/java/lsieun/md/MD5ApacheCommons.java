package lsieun.md;

import org.apache.commons.codec.digest.DigestUtils;

public class MD5ApacheCommons {
    public static void main(String[] args) {
        String str = "Hello MD5";
        String md5Hex = DigestUtils.md5Hex(str).toUpperCase();
        System.out.println(md5Hex);
    }
}
