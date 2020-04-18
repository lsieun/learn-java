package lsieun.crypto.x509;

import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.x509.asn1.ASN1Const;
import lsieun.crypto.x509.asn1.ASN1Struct;
import lsieun.crypto.x509.asn1.ASN1Utils;
import lsieun.hash.md5.MD5Utils;
import lsieun.hash.sha1.SHA1Utils;
import lsieun.hash.sha256.SHA256Utils;
import lsieun.utils.ByteUtils;
import lsieun.utils.DateUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

public class X509Utils {
    public static SignedX509Certificate parse_x509_certificate(byte[] bytes) {
        // First, read the whole thing into a traversable ASN.1 structure
        List<ASN1Struct> list = ASN1Utils.asn1parse(bytes);
        ASN1Struct struct = list.get(0);
        List<ASN1Struct> children = struct.children;
        ASN1Struct item0 = children.get(0);
        ASN1Struct item1 = children.get(1);
        ASN1Struct item2 = children.get(2);
        X509Certificate tbsCertificate = parse_tbs_certificate(item0);
        SignatureAlgorithmIdentifier algorithm = parse_algorithm_identifier(item1);
        BigInteger signature_value = parse_signature_value(item2);

        SignedX509Certificate signed_cert = new SignedX509Certificate(tbsCertificate, algorithm, signature_value);
        return signed_cert;
    }

    public static X509Certificate parse_tbs_certificate(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        int size = children.size();
        // Figure out if there’s an explicit version or not; if there is, then
        // everything else “shifts down” one spot.
        ASN1Struct version_struct = children.get(0);
        int version = version_struct.data[0] & 0xFF;
        ASN1Struct serialNumber_struct = children.get(1);
        BigInteger serialNumber = new BigInteger(1, serialNumber_struct.data);
        ASN1Struct signature_struct = children.get(2);
        SignatureAlgorithmIdentifier signature = parse_algorithm_identifier(signature_struct);
        ASN1Struct issuer_struct = children.get(3);
        Name issuer = parse_name(issuer_struct);
        ASN1Struct validity_struct = children.get(4);
        ValidityPeriod validity = parse_validity(validity_struct);
        ASN1Struct subject_struct = children.get(5);
        Name subject = parse_name(issuer_struct);
        ASN1Struct public_key_struct = children.get(6);
        PublicKeyInfo public_key = parse_public_key_info(public_key_struct);
        ASN1Struct extensions_struct = children.get(7);
        parse_extensions(extensions_struct);

//        return new X509Certificate(version,
//                serialNumber,
//                signature,
//                issuer,
//                validity,
//                subject,
//                public_key,
//                null, null, 0);
        return null;
    }

    public static SignatureAlgorithmIdentifier parse_algorithm_identifier(ASN1Struct struct) {
        byte[] data = struct.children.get(0).data;
        if (Arrays.equals(X509Const.OID_MD5WithRSA, data)) {
            return SignatureAlgorithmIdentifier.MD5_WITH_RSA_ENCRYPTION;
        }
        if (Arrays.equals(X509Const.OID_SHA1WithRSA, data)) {
            return SignatureAlgorithmIdentifier.SHA1_WITH_RSA_ENCRYPTION;
        }
        if (Arrays.equals(X509Const.OID_SHA256WithRSA, data)) {
            return SignatureAlgorithmIdentifier.SHA256_WITH_RSA_ENCRYPTION;
        }
        throw new RuntimeException("Unknown OID " + HexUtils.format(data, HexFormat.FORMAT_FF_FF));
    }

    public static BigInteger parse_signature_value(ASN1Struct struct) {
        return null;
    }

    public static Name parse_name(ASN1Struct struct) {
        String countryName = null;
        String stateOrProvinceName = null;
        String localityName = null;
        String organizationName = null;
        String organizationUnitName = null;
        String commonName = null;
        String emailAddress = null;


        List<ASN1Struct> children = struct.children;
        for (ASN1Struct item : children) {
            byte[] oid_bytes = item.children.get(0).children.get(0).data;
            byte[] content_bytes = item.children.get(0).children.get(1).data;
            String content = new String(content_bytes);
            if (Arrays.equals(oid_bytes, X509Const.OID_idAtCommonName)) {
                if (commonName == null) {
                    commonName = content;
                } else {
                    commonName += content;
                }
            } else if (Arrays.equals(oid_bytes, X509Const.OID_idAtCountryName)) {
                if (countryName == null) {
                    countryName = content;
                } else {
                    countryName += content;
                }
            } else if (Arrays.equals(oid_bytes, X509Const.OID_idAtLocalityName)) {
                if (localityName == null) {
                    localityName = content;
                } else {
                    localityName += content;
                }

            } else if (Arrays.equals(oid_bytes, X509Const.OID_idAtStateOrProvinceName)) {
                if (stateOrProvinceName == null) {
                    stateOrProvinceName = content;
                } else {
                    stateOrProvinceName += content;
                }

            } else if (Arrays.equals(oid_bytes, X509Const.OID_idAtOrganizationName)) {
                if (organizationName == null) {
                    organizationName = content;
                } else {
                    organizationName += content;
                }

            } else if (Arrays.equals(oid_bytes, X509Const.OID_idAtOrganizationalUnitName)) {
                if (organizationUnitName == null) {
                    organizationUnitName = content;
                } else {
                    organizationUnitName += content;
                }

            } else if (Arrays.equals(oid_bytes, X509Const.OID_idAtEmailAddress)) {
                if (emailAddress == null) {
                    emailAddress = content;
                } else {
                    emailAddress += content;
                }
            } else {
                // This is just advisory - NOT a problem
                StringBuilder sb = new StringBuilder();
                Formatter fm = new Formatter(sb);
                fm.format("Skipping unrecognized or unsupported name token OID of");
                for (byte b : oid_bytes) {
                    fm.format(" %02x", (b & 0xFF));
                }
                System.out.println(sb.toString());
            }

        }

        Name name = new Name(countryName,
                stateOrProvinceName,
                localityName,
                organizationName,
                organizationUnitName,
                commonName,
                emailAddress);

        return name;
    }

    public static ValidityPeriod parse_validity(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        ASN1Struct item0 = children.get(0);
        ASN1Struct item1 = children.get(1);
        Date notBefore = parse_date(item0);
        Date notAfter = parse_date(item1);

        ValidityPeriod validity = new ValidityPeriod(notBefore, notAfter);
        System.out.println(validity);
        return validity;
    }

    public static Date parse_date(ASN1Struct struct) {
        String year = null;
        String month = null;
        String day = null;
        String hour = null;
        String minute = null;
        String second = null;

        int tag = struct.tag;
        byte[] data = struct.data;
        if (tag == 23) {
            year = String.format("%c%c", data[0], data[1]);
            int val = Integer.parseInt(year);
            if (val < 50) {
                year = "20" + year;
            } else {
                year = "19" + year;
            }
            month = String.format("%c%c", data[2], data[3]);
            day = String.format("%c%c", data[4], data[5]);
            hour = String.format("%c%c", data[6], data[7]);
            minute = String.format("%c%c", data[8], data[9]);
            second = String.format("%c%c", data[10], data[11]);
        }
        if (tag == 24) {
            year = String.format("%c%c%c%c", data[0], data[1], data[2], data[3]);
            month = String.format("%c%c", data[4], data[5]);
            day = String.format("%c%c", data[6], data[7]);
            hour = String.format("%c%c", data[8], data[9]);
            minute = String.format("%c%c", data[10], data[11]);
            second = String.format("%c%c", data[12], data[13]);
        }

        String line = String.format("%s-%s-%s %s:%s:%s", year, month, day, hour, minute, second);
        return DateUtils.parse(line);
    }

    public static PublicKeyInfo parse_public_key_info(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        ASN1Struct item0 = children.get(0);
        ASN1Struct item1 = children.get(1);

        ASN1Struct item00 = item0.children.get(0);
        ASN1Struct item01 = item0.children.get(1);
        byte[] oid_bytes = item00.data;

        if (Arrays.equals(oid_bytes, X509Const.OID_RSA)) {
            // RSA
            // item01.data 这里是参数，RSA为空。

            int len = item1.data.length - 1;
            byte[] bytes = new byte[len];
            for (int i = 1; i < len; i++) {
                bytes[i - 1] = item1.data[i];
            }
            ASN1Struct algorithm_struct = ASN1Utils.asn1parse(bytes).get(0);
            ASN1Struct modulus_struct = algorithm_struct.children.get(0);
            ASN1Struct exponent_struct = algorithm_struct.children.get(1);
            BigInteger modulus = new BigInteger(1, modulus_struct.data);
            BigInteger exponent = new BigInteger(1, exponent_struct.data);
            RSAKey rsa_key = new RSAKey(modulus, exponent);

            AlgorithmIdentifier algorithm = AlgorithmIdentifier.RSA;
            PublicKeyInfo public_key = new PublicKeyInfo(algorithm, rsa_key);
            return public_key;
        } else if (Arrays.equals(oid_bytes, X509Const.OID_DH)) {
            // DH
        } else {
            // Unexpected:
        }

        return null;
    }

    public static void parse_extensions(ASN1Struct struct) {
        if (struct.children.size() != 1) {
            throw new RuntimeException("extensions children size = " + struct.children.size());
        }

        List<ASN1Struct> children = struct.children.get(0).children;
        for (ASN1Struct child : children) {
            parse_one_extension(child);
        }
    }

    public static void parse_one_extension(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        ASN1Struct item0 = children.get(0);

        if (Arrays.equals(item0.data, X509Const.OID_subjectKeyIdentifier)) {
            System.out.println("OID_subjectKeyIdentifier");
        } else if (Arrays.equals(item0.data, X509Const.OID_keyUsage)) {
            System.out.println("OID_keyUsage");
            parse_key_usage_extension(struct);
        } else if (Arrays.equals(item0.data, X509Const.OID_basicConstraints)) {
            System.out.println("OID_basicConstraints");
        } else if (Arrays.equals(item0.data, X509Const.OID_authorityKeyIdentifier)) {
            System.out.println("OID_authorityKeyIdentifier");
        } else {
            System.out.print("Unknown extension OID:");
            for (byte b : item0.data) {
                System.out.printf(" %02X", (b & 0xFF));
            }
            System.out.println();
        }
    }

    public static void parse_key_usage_extension(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        ASN1Struct oid_struct = children.get(0);
        ASN1Struct critical_struct = children.get(1);
        ASN1Struct data_struct = children.get(2);

        if (!Arrays.equals(oid_struct.data, X509Const.OID_keyUsage)) {
            String msg = getMessage("Key Usage", X509Const.OID_keyUsage, oid_struct.data);
            throw new RuntimeException(msg);
        }

        if ((critical_struct.data[0] & 0xFF) != 0xFF) {
            throw new RuntimeException("critical field is not 0xFF");
        }

        ASN1Struct key_usage_struct = ASN1Utils.asn1parse(data_struct.data).get(0);
        if (key_usage_struct.tag != 3) {
            throw new RuntimeException("tag is Not ASN1_BIT_STRING");
        }
        int padding = key_usage_struct.data[0] & 0xFF;
        System.out.println("padding: " + padding);

        int total = 1;
        for (int i = 1; i < key_usage_struct.length; i++) {
            total = (total << 8) | (key_usage_struct.data[i] & 0xFF);
        }
        String binary_str = Integer.toString(total, 2);
        String final_str = binary_str.substring(1, binary_str.length() - padding);
        System.out.println(final_str);
    }

    public static String getMessage(String name, byte[] expected_value, byte[] actual_value) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("%s OID Unexpected%n", name);
        fm.format("Expected Value: %s%n", HexUtils.format(expected_value, HexFormat.FORMAT_FF_FF));
        fm.format("  Actual Value: %s", HexUtils.format(actual_value, HexFormat.FORMAT_FF_FF));
        return sb.toString();
    }

    /**
     * An RSA signature is an ASN.1 DER-encoded PKCS-7 structure including
     * the OID of the signature algorithm (again), and the signature value.
     */
    public static boolean validate_certificate_rsa(byte[] bytes, RSAKey rsaKey) {
        // 第一步，解析bytes成证书，将证书拆分成tbsCertificate、signatureAlgorithm和signatureValue三部分
        ASN1Struct certificate = ASN1Utils.asn1parse(bytes).get(0);
        ASN1Struct tbs_certificate = certificate.children.get(0);
        ASN1Struct signature_algorithm = certificate.children.get(1);
        ASN1Struct signature_value = certificate.children.get(2);

        // 第二步，获取tbsCertificate的byte表示形式
        byte[] tbs_certificate_bytes = ByteUtils.concatenate(tbs_certificate.header, tbs_certificate.data);

        // 第三步，获取证书的签名算法，并计算hash值
        SignatureAlgorithmIdentifier algorithm = parse_algorithm_identifier(signature_algorithm);

        byte[] tbs_certificate_hash_bytes = null;
        switch (algorithm) {
            case MD5_WITH_RSA_ENCRYPTION:
                tbs_certificate_hash_bytes = MD5Utils.md5_hash(tbs_certificate_bytes, tbs_certificate_bytes.length);
                break;
            case SHA1_WITH_RSA_ENCRYPTION:
                tbs_certificate_hash_bytes = SHA1Utils.sha1_hash(tbs_certificate_bytes, tbs_certificate_bytes.length);
                break;
            case SHA256_WITH_RSA_ENCRYPTION:
                tbs_certificate_hash_bytes = SHA256Utils.sha256_hash(tbs_certificate_bytes, tbs_certificate_bytes.length);
                break;
            default:
                throw new RuntimeException("Unknown Algorithm " + algorithm);
        }

        // 第四步，使用RSA公钥解析signatureValue，获取由RSA私钥加密的hash值
        int length = signature_value.data.length;
        byte[] input = new byte[length - 1];
        for (int i = 1; i < length; i++) {
            input[i - 1] = signature_value.data[i];
        }

        byte[] decoded_bytes = RSAUtils.rsa_decrypt(input, rsaKey);
        ASN1Struct pkcs7_signature = ASN1Utils.asn1parse(decoded_bytes).get(0);
        byte[] original_hash_bytes = pkcs7_signature.children.get(1).data;

        // 第五步，验证两个hash是否相等
        return Arrays.equals(tbs_certificate_hash_bytes, original_hash_bytes);
    }

    public static ASN1Struct get_signature_value(ASN1Struct signature_value, RSAKey rsaKey) {
        int tag = signature_value.tag;
        if (tag != ASN1Const.ASN1_BIT_STRING) {
            throw new RuntimeException("Signature Value tag is not ASN1_BIT_STRING(3)");
        }

        return null;
    }

}
