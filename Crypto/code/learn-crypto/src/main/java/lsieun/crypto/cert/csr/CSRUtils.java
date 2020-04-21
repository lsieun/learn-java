package lsieun.crypto.cert.csr;

import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.cert.asn1.ASN1Struct;
import lsieun.crypto.cert.asn1.ASN1Utils;
import lsieun.crypto.cert.oid.OIDConst;
import lsieun.crypto.cert.x509.PublicKeyInfo;
import lsieun.crypto.cert.x509.SignatureAlgorithmIdentifier;
import lsieun.crypto.cert.x509.SignatureValue;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.hash.sha256.SHA256Utils;
import lsieun.utils.ByteUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;
import lsieun.utils.Pair;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class CSRUtils {
    public static SignedCertificationRequest parse_csr(byte[] bytes) {
        List<ASN1Struct> list = ASN1Utils.asn1parse(bytes);
        ASN1Struct asn1_certification_request_info = list.get(0).children.get(0);
        ASN1Struct asn1_signature_algorithm = list.get(0).children.get(1);
        ASN1Struct asn1_signature_value = list.get(0).children.get(2);

        CertificationRequestInfo certification_request_info = parse_certification_request_info(asn1_certification_request_info);
        SignatureAlgorithmIdentifier signature_algorithm = X509Utils.parse_algorithm_identifier(asn1_signature_algorithm);
        SignatureValue signature_value = X509Utils.parse_signature_value(asn1_signature_value);

        return new SignedCertificationRequest(certification_request_info, signature_algorithm, signature_value);
    }

    public static CertificationRequestInfo parse_certification_request_info(ASN1Struct asn1_certification_request) {
        int size = asn1_certification_request.children.size();

        // 第一部分，版本号
        ASN1Struct asn1_version = asn1_certification_request.children.get(0);
        int version = ((asn1_version.data[0] & 0xFF) + 1);

        // 第二部分，主体信息（国家、省、市、机构、部门、姓名）
        ASN1Struct asn1_subject = asn1_certification_request.children.get(1);
        List<Pair<String, String>> list = new ArrayList<>();
        for (ASN1Struct child : asn1_subject.children) {
            ASN1Struct item = child.children.get(0);
            String oid_hex = HexUtils.format(item.children.get(0).data, HexFormat.FORMAT_FF_SPACE_FF);
            String key = OIDConst.get(oid_hex);
            String value = new String(item.children.get(1).data, StandardCharsets.UTF_8);

            Pair<String, String> p = new Pair<>(key, value);
            list.add(p);
        }

        // 第三部分，公钥信息
        ASN1Struct asn1_subjectPKInfo = asn1_certification_request.children.get(2);
        PublicKeyInfo subjectPKInfo = X509Utils.parse_public_key_info(asn1_subjectPKInfo);

        byte[] data = ByteUtils.concatenate(asn1_certification_request.header, asn1_certification_request.data);
        CertificationRequestInfo info = new CertificationRequestInfo(version, subjectPKInfo, data);
        info.subject.addAll(list);

        if (size == 4) {
            // 第四部分，其他信息
            ASN1Struct asn1_attributes = asn1_certification_request.children.get(3);
        }

        return info;
    }

    public static void show(SignedCertificationRequest request) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("version: %s%n", request.certification_request_info.version);
        fm.format("Certificate Request Info:%n");
        for (Pair<String, String> item : request.certification_request_info.subject) {
            fm.format("    %s = %s%n", item.key, item.value);
        }
        fm.format("SubjectPublicKeyInfo: %s%n", request.certification_request_info.subject_public_key.algorithm);
        switch (request.certification_request_info.subject_public_key.algorithm) {
            case RSA:
                fm.format("    modulus: %s%n", request.certification_request_info.subject_public_key.rsa_public_key.modulus);
                fm.format("    exponent: %s%n", request.certification_request_info.subject_public_key.rsa_public_key.exponent);
                break;
            default:
                throw new RuntimeException("Unexpected Algorithm: " + request.certification_request_info.subject_public_key.algorithm);
        }
        fm.format("SignatureAlgorithm: %s%n", request.signature_algorithm);
        fm.format("Signature: %s%n", HexUtils.format(request.signature_value.data, HexFormat.FORMAT_FF_SPACE_FF));


        System.out.println(sb.toString());
    }

}
