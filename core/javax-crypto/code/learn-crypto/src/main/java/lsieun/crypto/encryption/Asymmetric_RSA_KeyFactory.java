package lsieun.crypto.encryption;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

public class Asymmetric_RSA_KeyFactory {
    public static final String MODULUS = "9616540267013058477253762977293425063379243458473593816900454019721117570003248808113992652836857529658675570356835067184715201230519907361653795328462699";
    public static final String PUBLIC_EXPONENT = "65537";
    public static final String PRIVATE_EXPONENT = "4802033916387221748426181350914821072434641827090144975386182740274856853318276518446521844642275539818092186650425384826827514552122318308590929813048801";


    public static Signature getPrivateSignature() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(new RSAPrivateKeySpec(new BigInteger(MODULUS), new BigInteger(PRIVATE_EXPONENT)));

        Signature localSignature = Signature.getInstance("SHA256withRSA");
        localSignature.initSign(privateKey);

        return localSignature;
    }

    public static Signature getPublicSignature() throws Exception {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(new RSAPublicKeySpec(new BigInteger(MODULUS), new BigInteger(PUBLIC_EXPONENT)));
        Signature localSignature = Signature.getInstance("SHA256withRSA");
        localSignature.initVerify(publicKey);
        return localSignature;
    }

    public static byte[] generateSignature(String str, Signature signature) throws Exception {
        signature.update(str.getBytes("UTF-8"));
        byte[] signData = signature.sign();
        return signData;
    }

    public static void verifySignature(byte[] bytes, String str, Signature signature) throws Exception{
        signature.update(str.getBytes("UTF-8"));
        boolean flag = signature.verify(bytes);
        System.out.println(flag);
    }



    public static void main(String[] args) throws Exception {
        String str = "Hello World";
        Signature privateSignature = getPrivateSignature();
        byte[] bytes = generateSignature(str, privateSignature);

        Signature publicSignature = getPublicSignature();
        verifySignature(bytes, str, publicSignature);
    }
}
