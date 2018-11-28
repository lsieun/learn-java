package lsieun.crypto.cert;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;

import lsieun.util.MyKeyStoreUtil;

public class SignatureWithKeyStore {
    public static void main(String[] args) throws Exception {
        // 1. Add Provider
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        // 2. Get public key and private key
        String filepath = ReadKeysFromKeyStore.class.getClassLoader().getResource("cert/keystore.jks").toURI().getPath();
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) MyKeyStoreUtil.getEntry(filepath, "abcdef", "lsieun-server", "123456");
        PrivateKey privateKey = privateKeyEntry.getPrivateKey();
        PublicKey publicKey = privateKeyEntry.getCertificate().getPublicKey();

        // 3. Signature
        Signature signature = Signature.getInstance("RSA");

        // 3.1 Sign
        signature.initSign(privateKey);
        byte[] data = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
        signature.update(data);
        byte[] digitalSignature = signature.sign();

        // 3.2 Verify
        signature.initVerify(publicKey);
        byte[] data2 = "abcdefghijklmnopqrstuvxyz".getBytes("UTF-8");
        signature.update(data2);
        boolean verified = signature.verify(digitalSignature);

        // 3.3 Print Result
        System.out.println(verified);
    }
}
