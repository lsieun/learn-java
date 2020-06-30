package lsieun.crypto.cert.b_test;

import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.cert.asn1.PEMUtils;
import lsieun.crypto.cert.asn1.ASN1Struct;
import lsieun.crypto.cert.asn1.ASN1Utils;
import lsieun.crypto.cert.rsa.RSAPublicKey;
import lsieun.crypto.cert.x509.SignedCertificate;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.utils.FileUtils;

import java.util.List;

@SuppressWarnings("Duplicates")
public class F_B_PKCS7 {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/DigiCertSHA2SecureServerCA.pem";
        byte[] bytes = PEMUtils.read(filepath);
        SignedCertificate certificate = X509Utils.parse_x509_certificate(bytes);
        RSAPublicKey rsaKey = certificate.tbs_certificate.subjectPublicKeyInfo.rsa_public_key;

        String cert_filepath = "/home/liusen/Workspace/tmp/www_example_org.der";
        byte[] cert_bytes = FileUtils.readBytes(cert_filepath);

        parse_signature_value(cert_bytes, rsaKey);
    }

    public static void parse_signature_value(byte[] bytes, RSAPublicKey rsaKey) {
        ASN1Struct certificate = ASN1Utils.parse_der(bytes).get(0);
        ASN1Struct signature_value = certificate.children.get(2);

        byte[] bit_string_data = ASN1Utils.get_bit_string_data(signature_value);

        byte[] decoded_bytes = RSAUtils.rsa_decrypt(bit_string_data, rsaKey.toKey());

        List<ASN1Struct> list = ASN1Utils.parse_der(decoded_bytes);
        ASN1Utils.show_raw(list);
    }
}
