package lsieun.crypto.cert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;

import lsieun.utils.ByteUtils;

public class ReadCertificateFile {
    public static void main(String[] args) {
        try {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            String filepath = ReadCertificateFile.class.getClassLoader().getResource("cert/lsieun-Test-CA-signed-cert.cer").toURI().getPath();
            InputStream inputStream = new FileInputStream(filepath);
            Certificate certificate = certFactory.generateCertificate(inputStream);

            // an encoded version of the Certificate as a byte array
            byte[] certBytes = certificate.getEncoded();
            System.out.println("Certificate: " + ByteUtils.toHex(certBytes).toUpperCase());
            System.out.println("==============================================");


            // PublicKey
            PublicKey publicKey = certificate.getPublicKey();
            System.out.println(publicKey);
            System.out.println("==============================================");

            // Certificate Type
            String certType = certificate.getType();
            System.out.println("Cert Type: " + certType);
            System.out.println("==============================================");

            //CertPath certPath = certFactory.generateCertPath(inputStream);
            //System.out.println(certPath);
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


}
