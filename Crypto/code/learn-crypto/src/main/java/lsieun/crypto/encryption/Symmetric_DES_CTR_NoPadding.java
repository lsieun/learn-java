package lsieun.crypto.encryption;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// https://examples.javacodegeeks.com/core-java/security/des-with-ctr-example/
public class Symmetric_DES_CTR_NoPadding {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        byte[] input = "www.javacodegeeks.com".getBytes();
        System.out.println("input : " + new String(input));

        byte[] keyBytes = new byte[]{0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};
        byte[] initializationVector = new byte[]{0x00, 0x01, 0x02, 0x03, 0x00, 0x00, 0x00, 0x01};

        SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
        IvParameterSpec ivSpv = new IvParameterSpec(initializationVector);

        Cipher c = Cipher.getInstance("DES/CTR/NoPadding", "BC");

        // encryption input
        c.init(Cipher.ENCRYPT_MODE, key, ivSpv);

        byte[] encText = new byte[input.length];
        int ctLen = c.update(input, 0, input.length, encText, 0);
        ctLen += c.doFinal(encText, ctLen);
        System.out.println("cipher: " + new String(encText).getBytes("UTF-8").toString() + " bytes: " + ctLen);


        // decryption input
        c.init(Cipher.DECRYPT_MODE, key, ivSpv);

        byte[] decrpt = new byte[ctLen];
        int ptLen = c.update(encText, 0, ctLen, decrpt, 0);
        ptLen += c.doFinal(decrpt, ptLen);
        System.out.println("plain : " + new String(decrpt) + " bytes: " + ptLen);
    }
}
