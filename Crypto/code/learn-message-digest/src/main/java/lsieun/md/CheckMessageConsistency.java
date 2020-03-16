package lsieun.md;

import java.security.MessageDigest;

// https://examples.javacodegeeks.com/core-java/security/check-message-consistency-using-hash-functions/
public class CheckMessageConsistency {
    public static void main(String[] args) throws Exception {
        String str1 = "javacodegeeks.com";
        String str2 = "javacodegeeks";

        byte[] fDigest = getDigest(str1);
        byte[] sDigest = getDigest(str2);

        if (MessageDigest.isEqual(fDigest, sDigest)) {
            System.out.println("str1 is equal to str2");
        } else {
            System.out.println("str1 is NOT equal to str2");
        }
    }

    public static byte[] getDigest(String str) throws Exception {
        MessageDigest hash = MessageDigest.getInstance("MD5");
        byte[] data = str.getBytes();
        hash.update(data);
        return hash.digest();
    }
}
