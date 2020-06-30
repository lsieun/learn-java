package lsieun.jdk.encryption;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

// https://examples.javacodegeeks.com/core-java/security/symmetric-encryption-example/
public class Symmetric_AES_ECB_NoPadding {
    public static void main(String[] args) throws Exception {
        // Raw Data
        byte[] data = "JavaJavaJavaJava".getBytes("UTF-8");
        System.out.println("User data(plaintext) : " + new String(data));

        // (0) Add Provider and Get Key
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        byte[] keyBytes = "keykeykekeykeykekeykeykekeykeyke".getBytes("UTF-8");
        SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");

        // (1) create Cipher
        Cipher c = Cipher.getInstance("AES/ECB/NoPadding");

        // (2) init Cipher
        c.init(Cipher.ENCRYPT_MODE, secretKey);


        // (3) encrypt data
        byte[] cText = new byte[data.length];
        int ctLen = c.update(data, 0, data.length, cText, 0);
        ctLen += c.doFinal(cText, ctLen);
        System.out.println("Password encrypted: " + cText.toString().getBytes("UTF-8").toString() + " bytes: " + ctLen);

        // (2) init Cipher
        c.init(Cipher.DECRYPT_MODE, secretKey);

        // (3) decrypt data
        byte[] plainText = new byte[ctLen];
        int plen = c.update(cText, 0, ctLen, plainText, 0);
        plen += c.doFinal(plainText, plen);
        System.out.println("User data(plaintext) : " + new String(plainText) + " bytes: " + plen);
    }
}
