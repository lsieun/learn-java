package lsieun.md;

import org.apache.commons.codec.digest.DigestUtils;

public class SHA256ApacheCommons {
    public static void main(String[] args) {
        String sha256hex = DigestUtils.sha256Hex("admin");
        System.out.println(sha256hex);
    }
}
