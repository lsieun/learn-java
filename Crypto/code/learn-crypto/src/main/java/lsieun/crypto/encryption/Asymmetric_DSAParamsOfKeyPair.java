package lsieun.crypto.encryption;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.DSAParams;

import sun.security.provider.DSAPrivateKey;
import sun.security.provider.DSAPublicKey;

// https://examples.javacodegeeks.com/core-java/security/get-dsa-parameters-of-a-key-pair-example/
public class Asymmetric_DSAParamsOfKeyPair {
    public static void main(String[] args) {

        try {

            // Generate a 1024-bit Digital Signature Algorithm (DSA) key pair
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            keyPairGenerator.initialize(1024);
            KeyPair keyPair = keyPairGenerator.genKeyPair();
            DSAPrivateKey privateKey = (DSAPrivateKey) keyPair.getPrivate();
            DSAPublicKey publicKey = (DSAPublicKey) keyPair.getPublic();


            /*
             * DSA requires three parameters to create a key pair
             *  prime (P)
             *  subprime (Q)
             *  base (G)
             * These three values are used to create a private key (X)
             * and a public key (Y)
             */
            DSAParams dsaParams = privateKey.getParams();
            BigInteger prime = dsaParams.getP();
            BigInteger subPrime = dsaParams.getQ();
            BigInteger base = dsaParams.getG();
            BigInteger x = privateKey.getX();
            BigInteger y = publicKey.getY();
            //System.out.println(dsaParams);
            System.out.println(prime);
            System.out.println(subPrime);
            System.out.println(base);
            System.out.println(x);
            System.out.println(y);

        } catch (NoSuchAlgorithmException e) {
        }

    }
}

