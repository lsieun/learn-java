package lsieun.crypto.encryption;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// https://examples.javacodegeeks.com/core-java/security/des-with-cbc-example/
public class Symmetric_DES_CBC_PKCS7Padding {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        byte[] input = "www.javaCODEgeeks.com".getBytes();
        System.out.println("input : " + new String(input));

        byte[] keyBytes = new byte[]{0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xab, (byte) 0xcd, (byte) 0xef};
        byte[] ivBytes = new byte[]{0x07, 0x06, 0x05, 0x04, 0x03, 0x02, 0x01, 0x00};


        SecretKeySpec pKey = new SecretKeySpec(keyBytes, "DES");
        IvParameterSpec ivectorSpecv = new IvParameterSpec(ivBytes);

        Cipher c = Cipher.getInstance("DES/CBC/PKCS7Padding", "BC");

        // encryption pass
        c.init(Cipher.ENCRYPT_MODE, pKey, ivectorSpecv);

        byte[] encr = new byte[input.length * 2];
        int ctLen = c.update(input, 0, input.length, encr, 0);
        ctLen += c.doFinal(encr, ctLen);
        System.out.println("cipher: " + new String(encr).getBytes("UTF-8").toString() + " bytes: " + ctLen);


        // decryption pass
        c.init(Cipher.DECRYPT_MODE, pKey, ivectorSpecv);

        byte[] decrpt = new byte[ctLen];
        int ptLen = c.update(encr, 0, ctLen, decrpt, 0);
        ptLen += c.doFinal(decrpt, ptLen);
        System.out.println("plain : " + new String(decrpt) + " bytes: " + ptLen);
    }
}
