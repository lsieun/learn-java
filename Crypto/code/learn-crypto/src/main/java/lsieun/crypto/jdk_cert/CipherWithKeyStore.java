package lsieun.crypto.jdk_cert;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;

import javax.crypto.Cipher;

import lsieun.utils.ByteUtils;
import lsieun.utils.MyKeyStoreUtil;

public class CipherWithKeyStore {
    public static void main(String[] args) throws Exception {
        // 1. Add Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // 2. Get public key and private key
        String filepath = ReadKeysFromKeyStore.class.getClassLoader().getResource("cert/keystore.jks").toURI().getPath();
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) MyKeyStoreUtil.getEntry(filepath, "abcdef", "lsieun-server", "123456");
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();
        PublicKey publicKey = privateKeyEntry.getCertificate().getPublicKey();

        // 3. Cipher
        Cipher cipher = Cipher.getInstance("RSA");
        byte[] plainText  = "abcdefghijklmnopqrstuvwxyz".getBytes("UTF-8");
        System.out.println("Plain Data: ");
        printByteArray(plainText);
        System.out.println("====================");

        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        byte[] encryptText = cipher.doFinal(plainText);
        System.out.println("Encrypt Data: ");
        printByteArray(encryptText);
        System.out.println("====================");

        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        byte[] decryptText = cipher.doFinal(encryptText);
        System.out.println("Decrypt Data: ");
        printByteArray(decryptText);
        System.out.println("====================");
    }

    public static void printByteArray(byte[] bytes) {
        System.out.println("Length: " + bytes.length);
        for (byte b : bytes) {
            System.out.print((char)b);
        }
        System.out.println();
        System.out.println("Hex: " + ByteUtils.toHex(bytes).toUpperCase());
    }
}
