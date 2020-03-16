package lsieun.crypto.cert;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.Principal;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;

import javax.security.auth.x500.X500Principal;

import sun.security.x509.X509CertImpl;


public class ReadKeysFromKeyStore {
    public static void main(String[] args) throws Exception{

        String filepath = ReadKeysFromKeyStore.class.getClassLoader().getResource("cert/keystore.jks").toURI().getPath();
        InputStream keyStoreData = new FileInputStream(filepath);

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
        char[] keyStorePassword = "abcdef".toCharArray();
        keyStore.load(keyStoreData, keyStorePassword);

        char[] keyPassword = "123456".toCharArray();
        KeyStore.ProtectionParameter entryPassword = new KeyStore.PasswordProtection(keyPassword);
        KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) keyStore.getEntry("lsieun-server", entryPassword);

        PrivateKey privateKey = privateKeyEntry.getPrivateKey();
        Certificate certificate = privateKeyEntry.getCertificate();
        Certificate[] certificateChain = privateKeyEntry.getCertificateChain();

        System.out.println("Private Key:");
        displayPrivateKey(privateKey);
        System.out.println("===============================\n");


        System.out.println("Certificate:");
        displayCert(certificate);
        System.out.println("===============================\n");

        System.out.println("Certificate Chain:");
        for (Certificate cert : certificateChain) {
            System.out.println("------------------------------");
            displayCert(cert);
            System.out.println("------------------------------");
        }
        System.out.println("===============================");

        keyStoreData.close();
    }

    public static void displayPrivateKey(PrivateKey key) {
        String algorithm = key.getAlgorithm();
        String format = key.getFormat();
        byte[] bytes = key.getEncoded();
        System.out.println("Algorithm: " + algorithm);
        System.out.println("Format: " + format);
    }

    public static void displayCert(Certificate certificate) {
        String certificateType = certificate.getType();

        X509CertImpl cert = (X509CertImpl) certificate;
        //X500Principal subject = cert.getSubjectX500Principal();
        //X500Principal issuer = cert.getIssuerX500Principal();

        Principal subjectDN = cert.getSubjectDN();
        Principal issuerDN = cert.getIssuerDN();

        System.out.println("type: " + certificateType);
        System.out.println("Subject: " + subjectDN);
        System.out.println("Issuer: " + issuerDN);

    }
}
