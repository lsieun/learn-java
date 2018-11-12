package lsieun.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestSHA1 {
    public static void main(String[] args) {
        String message = "String to be hashed";

        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(message.getBytes());
            byte[] digest = messageDigest.digest();


            StringBuffer buffer = new StringBuffer();
            for (byte element : digest) {
                String hex = Integer.toString((element & 0xff) + 0x100, 16).substring(1);

                //System.out.println("" + element + ": ");
                //System.out.println("\tBinary Code: " + Integer.toBinaryString(element));
                //System.out.println("\tHex  Format: " + hex);

                buffer.append(hex);
            }
            System.out.println("Digest Len: " + digest.length);
            System.out.println("Hex format: " + buffer.toString());
        }
        catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }
}
