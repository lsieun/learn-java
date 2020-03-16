package lsieun.crypto.encryption;

import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;

// https://examples.javacodegeeks.com/core-java/security/pbe-with-a-pbeparameterspec-example/
public class Symmetric_DESede_CBC_PKCS7Padding_PBE {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        byte[] input = "www.javacodegeeks.com".getBytes();

        byte[] pkey = new byte[]{0x73, 0x2f, 0x2d, 0x33, (byte) 0xc8, 0x01, 0x73, 0x2b, 0x72,
                0x06, 0x75, 0x6c, (byte) 0xbd, 0x44, (byte) 0xf9, (byte) 0xc1, (byte) 0xc1, 0x03,
                (byte) 0xdd, (byte) 0xd9, 0x7c, 0x7c, (byte) 0xbe, (byte) 0x8e};

        byte[] iVector = new byte[]{(byte) 0xb0, 0x7b, (byte) 0xf5, 0x22, (byte) 0xc8, (byte) 0xd6,
                0x08, (byte) 0xb8};

        // encryption with precalculated keys
        Cipher cipherEnc = Cipher.getInstance("DESede/CBC/PKCS7Padding", "BC");
        cipherEnc.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(pkey, "DESede"), new IvParameterSpec(iVector));
        byte[] out = cipherEnc.doFinal(input);


        // decryption with PBE
        char[] passphrase = "password".toCharArray();

        byte[] saltBytes = new byte[]{0x7d, 0x60, 0x43, 0x5f, 0x02, (byte) 0xe9, (byte) 0xe0, (byte) 0xae};

        int cnt = 2048;

        PBEKeySpec pbe = new PBEKeySpec(passphrase);
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBEWithSHAAnd3KeyTripleDES", "BC");

        Cipher cipherDec = Cipher.getInstance("PBEWithSHAAnd3KeyTripleDES", "BC");
        Key skey = keyFactory.generateSecret(pbe);


        cipherDec.init(Cipher.DECRYPT_MODE, skey, new PBEParameterSpec(saltBytes, cnt));

        System.out.println("encrypted : " + new String(out).getBytes("UTF-8").toString());
        System.out.println("generated key: " + new String(skey.getEncoded()));
        System.out.println("initialization vector : " + new String(cipherDec.getIV()).getBytes("UTF-8").toString());
        System.out.println("decrypted  : " + new String(cipherDec.doFinal(out)));
    }
}
