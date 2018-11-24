package lsieun.crypto.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

// https://examples.javacodegeeks.com/core-java/security/generate-public-private-key-pairs-for-dsa-dh-rsa-algorithm-example/
public class Asymmetric_GenerateKeyPairs_DSA_DH_RSA {
    public static void main(String[] args) {
        try {

            // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            keyGen.initialize(1024);
            KeyPair keypair = keyGen.genKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();

            System.out.println(privateKey + "\n" + publicKey);
            System.out.println("===========================");

            // Generate a 576-bit DH key pair
            keyGen = KeyPairGenerator.getInstance("DH");
            keyGen.initialize(576);
            keypair = keyGen.genKeyPair();
            privateKey = keypair.getPrivate();
            publicKey = keypair.getPublic();

            System.out.println(privateKey + "\n" + publicKey);
            System.out.println("===========================");

            // Generate a 1024-bit RSA key pair
            keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            keypair = keyGen.genKeyPair();
            privateKey = keypair.getPrivate();
            publicKey = keypair.getPublic();

            System.out.println(privateKey + "\n" + publicKey);

        } catch (java.security.NoSuchAlgorithmException e) {
        }

    }
}
