package lsieun.crypto.encryption;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

// https://examples.javacodegeeks.com/core-java/security/get-bytes-of-a-key-pair-example/
public class Asymmetric_KeyPairBytesExample {
    public static void main(String[] args) {

        try {
            String algorithm = "DSA";  // or RSA, DH, etc.

            // Generate a 1024-bit Digital Signature Algorithm (RSA) key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Get the formats of the encoded bytes
            String privateKeyFormat = privateKey.getFormat();
            System.out.println("PrivateKey format : " + privateKeyFormat);
            String publicKeyFormat = publicKey.getFormat();
            System.out.println("PublicKey format : " + publicKeyFormat);

            // Get bytes of the public and private keys
            byte[] privateKeyBytes = privateKey.getEncoded();
            byte[] publicKeyBytes = publicKey.getEncoded();

            // Get key pair Objects from their respective byte arrays
            // We initialize encoded key specifications based on the encoding formats
            EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
            EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
            PrivateKey newPrivateKey = keyFactory.generatePrivate(privateKeySpec);
            PublicKey newPublicKey = keyFactory.generatePublic(publicKeySpec);

            System.out.println("Is transformation valid ? " + (privateKey.equals(newPrivateKey) && publicKey.equals(newPublicKey)));

        } catch (InvalidKeySpecException e) {
        } catch (NoSuchAlgorithmException e) {
        }

    }
}
