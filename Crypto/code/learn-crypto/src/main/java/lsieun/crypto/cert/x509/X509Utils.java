package lsieun.crypto.cert.x509;

import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.cert.asn1.ASN1Struct;
import lsieun.crypto.cert.asn1.ASN1Utils;
import lsieun.crypto.cert.x509.extensions.BasicConstraintsExtension;
import lsieun.crypto.cert.x509.extensions.KeyUsageExtension;
import lsieun.crypto.cert.x509.extensions.SubjectAltNameExtension;
import lsieun.hash.md5.MD5Utils;
import lsieun.hash.sha1.SHA1Utils;
import lsieun.hash.sha256.SHA256Utils;
import lsieun.utils.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class X509Utils {
    public static SignedX509Certificate parse_x509_certificate(byte[] bytes) {
        // First, read the whole thing into a traversable ASN.1 structure
        List<ASN1Struct> list = ASN1Utils.asn1parse(bytes);
        List<ASN1Struct> children = list.get(0).children;
        ASN1Struct asn1_tbs_certificate = children.get(0);
        ASN1Struct asn1_signature_algorithm = children.get(1);
        ASN1Struct asn1_signature_value = children.get(2);
        X509Certificate tbs_certificate = parse_tbs_certificate(asn1_tbs_certificate);
        SignatureAlgorithmIdentifier signature_algorithm = parse_algorithm_identifier(asn1_signature_algorithm);
        SignatureValue signature_value = parse_signature_value(asn1_signature_value);

        return new SignedX509Certificate(tbs_certificate, signature_algorithm, signature_value);
    }

    public static X509Certificate parse_tbs_certificate(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        int size = children.size();
        // Figure out if there’s an explicit version or not; if there is, then
        // everything else “shifts down” one spot.
        ASN1Struct asn1_version = children.get(0);
        int version = (asn1_version.data[0] & 0xFF) + 1;
        ASN1Struct asn1_serialNumber = children.get(1);
        String serial_number = HexUtils.format(asn1_serialNumber.data, HexFormat.FORMAT_FF_SPACE_FF);
        ASN1Struct asn1_signature = children.get(2);
        SignatureAlgorithmIdentifier signature = parse_algorithm_identifier(asn1_signature);
        ASN1Struct asn1_issuer = children.get(3);
        Name issuer = parse_name(asn1_issuer);
        ASN1Struct asn1_validity = children.get(4);
        ValidityPeriod validity = parse_validity(asn1_validity);
        ASN1Struct asn1_subject = children.get(5);
        Name subject = parse_name(asn1_subject);
        ASN1Struct asn1_public_key_info = children.get(6);
        PublicKeyInfo public_key_info = parse_public_key_info(asn1_public_key_info);
        ASN1Struct asn1_extensions = children.get(7);
        List<Extension> extensions = parse_extensions(asn1_extensions);

        return new X509Certificate(version,
                serial_number,
                signature,
                issuer,
                validity,
                subject,
                public_key_info,
                extensions);
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
        throw new RuntimeException("Unknown OID " + HexUtils.format(data, HexFormat.FORMAT_FF_SPACE_FF));
    }

    public static SignatureValue parse_signature_value(ASN1Struct asn1_signature_value) {
        int length = asn1_signature_value.data.length;
        byte[] bytes = new byte[length - 1];
        for (int i = 1; i < length; i++) {
            bytes[i - 1] = asn1_signature_value.data[i];
        }
        return new SignatureValue(bytes);
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
            String content = new String(content_bytes, StandardCharsets.UTF_8);
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
                    fm.format(" %02X", (b & 0xFF));
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
        ASN1Struct asn1_not_before = children.get(0);
        ASN1Struct asn1_not_after = children.get(1);
        Date notBefore = parse_date(asn1_not_before);
        Date notAfter = parse_date(asn1_not_after);

        return new ValidityPeriod(notBefore, notAfter);
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
        } else if (tag == 24) {
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
        ASN1Struct asn1_algorithm_identifier = children.get(0);
        ASN1Struct asn1_subject_public_key = children.get(1);

        ASN1Struct asn1_algorithm = asn1_algorithm_identifier.children.get(0);
        ASN1Struct asn1_parameters = asn1_algorithm_identifier.children.get(1);
        byte[] oid_bytes = asn1_algorithm.data;

        if (Arrays.equals(oid_bytes, X509Const.OID_RSA)) {
            // RSA
            // asn1_parameters 这里是参数，RSA为空。
            int len = asn1_subject_public_key.data.length;
            byte[] bytes = new byte[len - 1];
            for (int i = 1; i < len; i++) {
                bytes[i - 1] = asn1_subject_public_key.data[i];
            }
            ASN1Struct algorithm_struct = ASN1Utils.asn1parse(bytes).get(0);
            ASN1Struct modulus_struct = algorithm_struct.children.get(0);
            ASN1Struct exponent_struct = algorithm_struct.children.get(1);
            BigInteger modulus = new BigInteger(1, modulus_struct.data);
            BigInteger exponent = new BigInteger(1, exponent_struct.data);
            RSAKey rsa_key = new RSAKey(modulus, exponent);

            return new PublicKeyInfo(AlgorithmIdentifier.RSA, rsa_key);
        } else if (Arrays.equals(oid_bytes, X509Const.OID_DH)) {
            // DH
            throw new RuntimeException("Unsupported AlgorithmIdentifier DH");
        } else {
            // Unexpected:
            String oid_str = HexUtils.format(oid_bytes, HexFormat.FORMAT_FF_SPACE_FF);
            throw new RuntimeException("Unexpected AlgorithmIdentifier " + oid_str);
        }
    }

    public static List<Extension> parse_extensions(ASN1Struct struct) {
        if (struct.children.size() != 1) {
            throw new RuntimeException("extensions children size = " + struct.children.size());
        }

        List<Extension> list = new ArrayList<>();
        List<ASN1Struct> children = struct.children.get(0).children;
        for (ASN1Struct child : children) {
            Extension extension = parse_one_extension(child);
            list.add(extension);
        }
        return list;
    }

    //TODO: 如果有一个extension是critical的，但不认识的OID，就应该抛出异常
    public static Extension parse_one_extension(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        int size = children.size();
        ASN1Struct asn1_extension_id = children.get(0);
        ASN1Struct asn1_extension_value = children.get(1);
        boolean critical = false;
        if (size == 3) {
            critical = (asn1_extension_value.data[0] & 0xFF) == 0xFF;
            asn1_extension_value = children.get(2);
        }

        if (1 == 2) {
            return null;
        }
        // TODO: 分析具体的extension选项
//        else if (Arrays.equals(asn1_extension_id.data, X509Const.OID_subjectKeyIdentifier)) {
//            System.out.println("OID_subjectKeyIdentifier");
//        }
        else if (Arrays.equals(asn1_extension_id.data, X509Const.OID_keyUsage)) {
            return parse_key_usage_extension(struct);
        } else if (Arrays.equals(asn1_extension_id.data, X509Const.OID_subjectAltName)) {
            return parse_subject_alt_name_extension(struct);
        }
//        else if (Arrays.equals(asn1_extension_id.data, X509Const.OID_basicConstraints)) {
//            return parse_basic_constraints(struct);
//        }
//        else if (Arrays.equals(asn1_extension_id.data, X509Const.OID_authorityKeyIdentifier)) {
//            System.out.println("OID_authorityKeyIdentifier");
//        }
        else {
            System.out.print("Unknown extension OID:");
            for (byte b : asn1_extension_id.data) {
                System.out.printf(" %02X", (b & 0xFF));
            }
            System.out.println();
            return new Extension(asn1_extension_id.data, critical, asn1_extension_value.data);
        }

    }

    public static KeyUsageExtension parse_key_usage_extension(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        ASN1Struct asn1_extension_id = children.get(0);
        ASN1Struct asn1_critical = children.get(1);
        ASN1Struct asn1_extension_value = children.get(2);

        if (!Arrays.equals(asn1_extension_id.data, X509Const.OID_keyUsage)) {
            String msg = getMessage("Key Usage", X509Const.OID_keyUsage, asn1_extension_id.data);
            throw new RuntimeException(msg);
        }

        if ((asn1_critical.data[0] & 0xFF) != 0xFF) {
            throw new RuntimeException("critical field is not 0xFF");
        }

        ASN1Struct asn1_key_usage = ASN1Utils.asn1parse(asn1_extension_value.data).get(0);
        if (asn1_key_usage.tag != 3) {
            throw new RuntimeException("tag is Not ASN1_BIT_STRING");
        }
//        int padding = asn1_key_usage.data[0] & 0xFF;
//        System.out.println("padding: " + padding);

        boolean isDigitalSignature = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_DIGITAL_SIGNATURE);
        boolean isNonRepudiation = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_NON_REPUDIATION);
        boolean isKeyEncipherment = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_KEY_ENCIPHERMENT);
        boolean isDataEncipherment = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_DATA_ENCIPHERMENT);
        boolean isKeyAgreement = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_KEY_AGREEMENT);
        boolean isKeyCertSign = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_KEY_CERT_SIGN);
        boolean isCRLSign = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_CRL_SIGN);
        boolean isEncipherOnly = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_ENCIPHER_ONLY);
        boolean isDecipherOnly = asn1_get_bit(asn1_key_usage.data, X509Const.BIT_DECIPHER_ONLY);

        return new KeyUsageExtension(asn1_extension_id.data, true, asn1_extension_value.data,
                isDigitalSignature,
                isNonRepudiation,
                isKeyEncipherment,
                isDataEncipherment,
                isKeyAgreement,
                isKeyCertSign,
                isCRLSign,
                isEncipherOnly,
                isDecipherOnly);
    }

    public static SubjectAltNameExtension parse_subject_alt_name_extension(ASN1Struct struct) {
        List<ASN1Struct> children = struct.children;
        ASN1Struct asn1_extension_id = children.get(0);

        if (!Arrays.equals(asn1_extension_id.data, X509Const.OID_subjectAltName)) {
            throw new RuntimeException("Not SubjectAltNameExtension");
        }

        ASN1Struct asn1_extension_value = children.get(1);
        List<ASN1Struct> list = ASN1Utils.asn1parse(asn1_extension_value.data);

        SubjectAltNameExtension extension = new SubjectAltNameExtension(asn1_extension_id.data, false, asn1_extension_value.data);
        for (ASN1Struct item : list.get(0).children) {
            int tag = item.tag;
            byte[] data = item.data;

            Pair<String, String> p;
            switch (tag) {
                case 2:
                    p = new Pair<>("DNSName", new String(data, StandardCharsets.UTF_8));
                    break;
                default:
                    throw new RuntimeException("Unknown tag " + tag);
            }
            extension.values.add(p);
        }

        return extension;
    }

    public static BasicConstraintsExtension parse_basic_constraints(ASN1Struct struct) {
        return null;
    }

    public static boolean asn1_get_bit(byte[] bit_string, int bit) {
        if (bit < 0) return false;
        if (bit >= (bit_string.length - 1) * 8) return false;
        int index = 1 + bit / 8;
        int mask = (0x80 >> (bit % 8));
        return (bit_string[index] & mask) == mask;
    }

    public static String getMessage(String name, byte[] expected_value, byte[] actual_value) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("%s OID Unexpected%n", name);
        fm.format("Expected Value: %s%n", HexUtils.format(expected_value, HexFormat.FORMAT_FF_SPACE_FF));
        fm.format("  Actual Value: %s", HexUtils.format(actual_value, HexFormat.FORMAT_FF_SPACE_FF));
        return sb.toString();
    }

    /**
     * An RSA signature is an ASN.1 DER-encoded PKCS-7 structure including
     * the OID of the signature algorithm (again), and the signature value.
     */
    public static boolean validate_certificate_rsa(byte[] bytes, RSAKey rsaKey) {
        // 第一步，解析bytes成证书，将证书拆分成tbsCertificate、signatureAlgorithm和signatureValue三部分
        ASN1Struct asn1_certificate = ASN1Utils.asn1parse(bytes).get(0);
        ASN1Struct asn1_tbs_certificate = asn1_certificate.children.get(0);
        ASN1Struct asn1_signature_algorithm = asn1_certificate.children.get(1);
        ASN1Struct asn1_signature_value = asn1_certificate.children.get(2);

        // 第二步，获取tbsCertificate的byte表示形式
        byte[] tbs_certificate_bytes = ByteUtils.concatenate(asn1_tbs_certificate.header, asn1_tbs_certificate.data);

        // 第三步，获取证书的签名算法，并计算hash值
        SignatureAlgorithmIdentifier algorithm = parse_algorithm_identifier(asn1_signature_algorithm);

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
        int length = asn1_signature_value.data.length;
        byte[] input = new byte[length - 1];
        for (int i = 1; i < length; i++) {
            input[i - 1] = asn1_signature_value.data[i];
        }

        byte[] decoded_bytes = RSAUtils.rsa_decrypt(input, rsaKey);
        ASN1Struct pkcs7_signature = ASN1Utils.asn1parse(decoded_bytes).get(0);
        byte[] original_hash_bytes = pkcs7_signature.children.get(1).data;

        // 第五步，验证两个hash是否相等
        return Arrays.equals(tbs_certificate_hash_bytes, original_hash_bytes);
    }

    public static void display_x509_certificate(SignedX509Certificate certificate) {
        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);
        fm.format("Certificate details:%n");
        fm.format("Version: %d%n", certificate.tbsCertificate.version);
        fm.format("Serial number: %s%n", certificate.tbsCertificate.serialNumber);
        fm.format("Signature: %s%n", certificate.tbsCertificate.signature);
        fm.format("issuer: %s%n", output_x500_name(certificate.tbsCertificate.issuer));
        fm.format("not before: %s%n", DateUtils.format(certificate.tbsCertificate.validity.notBefore));
        fm.format("not after: %s%n", DateUtils.format(certificate.tbsCertificate.validity.notAfter));
        fm.format("subject: %s%n", output_x500_name(certificate.tbsCertificate.subject));
        fm.format("Public key algorithm: ");

        switch (certificate.tbsCertificate.subjectPublicKeyInfo.algorithm) {
            case RSA:
                fm.format("RSA%n");
                fm.format("    modulus: %s%n", certificate.tbsCertificate.subjectPublicKeyInfo.rsa_public_key.modulus);
                fm.format("    exponent: %s%n", certificate.tbsCertificate.subjectPublicKeyInfo.rsa_public_key.exponent);
                break;
            case DH:
                fm.format("DH%n");
                break;
            default:
                fm.format("???%n");
                break;
        }

        fm.format("Extensions%n");
        for (Extension extension : certificate.tbsCertificate.extensions) {
            if (extension instanceof KeyUsageExtension) {
                KeyUsageExtension ext = (KeyUsageExtension) extension;
                fm.format("    %s:", "KeyUsageExtension");
                if (ext.critical) {
                    fm.format(" Critical");
                }
                if (ext.isDigitalSignature) {
                    fm.format(" DigitalSignature");
                }
                if (ext.isNonRepudiation) {
                    fm.format(" NonRepudiation");
                }
                if (ext.isKeyEncipherment) {
                    fm.format(" KeyEncipherment");
                }
                if (ext.isDataEncipherment) {
                    fm.format(" DataEncipherment");
                }
                if (ext.isKeyAgreement) {
                    fm.format(" KeyAgreement");
                }
                if (ext.isKeyCertSign) {
                    fm.format(" KeyCertSign");
                }
                if (ext.isCRLSign) {
                    fm.format(" CRLSign");
                }
                if (ext.isEncipherOnly) {
                    fm.format(" EncipherOnly");
                }
                if (ext.isDecipherOnly) {
                    fm.format(" DecipherOnly");
                }
            } else if (extension instanceof SubjectAltNameExtension) {
                SubjectAltNameExtension ext = (SubjectAltNameExtension) extension;
                fm.format("    %s:%n", "SubjectAltNameExtension");
                for (Pair<String, String> item : ext.values) {
                    fm.format("        %s=%s%n", item.key, item.value);
                }
            } else {
                fm.format("    %s:", extension.oid);
                if (extension.critical) {
                    fm.format(" Critical");
                }

            }
            fm.format("%n");
        }

        fm.format("Signature algorithm: %s%n", certificate.algorithm);
        fm.format("Signature Value: %s%n", HexUtils.format(certificate.signature_value.data, HexFormat.FORMAT_FF_SPACE_FF));

        fm.format("%n");
        System.out.println(sb.toString());
    }

    public static String output_x500_name(Name name) {
        return String.format("/C=%s/ST=%s/L=%s/O=%s/OU=%s/CN=%s",
                name.CountryName, name.StateOrProvinceName, name.LocalityName,
                name.OrganizationName, name.OrganizationUnitName, name.CommonName);
    }
}
