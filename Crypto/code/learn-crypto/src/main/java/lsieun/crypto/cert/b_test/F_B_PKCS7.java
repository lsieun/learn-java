package lsieun.crypto.cert.b_test;

import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.cert.PEMUtils;
import lsieun.crypto.cert.asn1.ASN1Struct;
import lsieun.crypto.cert.asn1.ASN1Utils;
import lsieun.crypto.cert.x509.SignedX509Certificate;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.utils.ByteUtils;
import lsieun.utils.FileUtils;

import java.math.BigInteger;
import java.util.List;

@SuppressWarnings("Duplicates")
public class F_B_PKCS7 {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/DigiCertSHA2SecureServerCA.pem";
        byte[] bytes = PEMUtils.read(filepath);
        SignedX509Certificate certificate = X509Utils.parse_x509_certificate(bytes);
        RSAKey rsaKey = certificate.tbsCertificate.subjectPublicKeyInfo.rsa_public_key;

        String cert_filepath = "/home/liusen/Workspace/tmp/www_example_org.der";
        byte[] cert_bytes = FileUtils.readBytes(cert_filepath);

        parse_signature_value(cert_bytes, rsaKey);
    }

    public static void parse_signature_value(byte[] bytes, RSAKey rsaKey) {
        ASN1Struct certificate = ASN1Utils.asn1parse(bytes).get(0);
        ASN1Struct signature_value = certificate.children.get(2);

        int length = signature_value.data.length;
        byte[] input = new byte[length - 1];
        for (int i = 1; i < length; i++) {
            input[i - 1] = signature_value.data[i];
        }

        byte[] decoded_bytes = RSAUtils.rsa_decrypt(input, rsaKey);

        ASN1Utils.asn1show(decoded_bytes);
    }
}
