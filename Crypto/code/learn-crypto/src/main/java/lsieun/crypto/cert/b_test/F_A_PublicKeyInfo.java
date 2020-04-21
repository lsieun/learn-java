package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.PEMUtils;
import lsieun.crypto.cert.x509.SignedX509Certificate;
import lsieun.crypto.cert.x509.X509Utils;

public class F_A_PublicKeyInfo {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/zhihu.pem";
        byte[] bytes = PEMUtils.read(filepath);
        SignedX509Certificate certificate = X509Utils.parse_x509_certificate(bytes);
        System.out.println(certificate.tbsCertificate.subjectPublicKeyInfo.rsa_public_key.modulus);
        System.out.println(certificate.tbsCertificate.subjectPublicKeyInfo.rsa_public_key.exponent);
    }
}
