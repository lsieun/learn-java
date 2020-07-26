package lsieun.cert.cst;

import lsieun.cert.oid.OIDUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

import java.util.Arrays;
import java.util.Optional;

public enum ObjectIdentifier {
    RSAEncryption("2A 86 48 86 F7 0D 01 01 01"),
    MD5_With_RSA("2A 86 48 86 F7 0D 01 01 04"),
    SHA1_With_RSA("2A 86 48 86 F7 0D 01 01 05"),
    SHA256_With_RSA("2A 86 48 86 F7 0D 01 01 0B"),

    EmailAddress("2A 86 48 86 F7 0D 01 09 01"),

    MD5("2A 86 48 86 F7 0D 02 05"),
    HMACWithSHA1("2A 86 48 86 F7 0D 02 07"),
    HMACWithSHA224("2A 86 48 86 F7 0D 02 08"),
    HMACWithSHA256("2A 86 48 86 F7 0D 02 09"),
    HMACWithSHA384("2A 86 48 86 F7 0D 02 0A"),
    HMACWithSHA512("2A 86 48 86 F7 0D 02 0B"),

    DSA("2A 86 48 CE 38 04 01"),
    DSA_With_SHA1("2A 86 48 CE 38 04 03"),

    EC_Public_Key("2A 86 48 CE 3D 02 01"),
    prime256v1("2A 86 48 CE 3D 03 01 07"),
    ECDSA_With_SHA256("2A 86 48 CE 3D 04 03 02"),
    DH("2A 86 48 CE 3E 02 01"),

    ExtendedValidationCertificates("2B 06 01 04 01 D6 79 02 04 02"),
    AuthorityInfoAccess("2B 06 01 05 05 07 01 01"),

//    DSA("2B 0E 03 02 0C"),
    SHA("2B 0E 03 02 12"),


    CommonName("55 04 03"),
    Surname("55 04 04"),
    SerialNumber("55 04 05"),
    CountryName("55 04 06"),
    LocalityName("55 04 07"),
    StateOrProvinceName("55 04 08"),
    StreetAddress("55 04 09"),
    OrganizationName("55 04 0A"),
    OrganizationalUnitName("55 04 0B"),

    SubjectKeyIdentifier("55 1D 0E"),
    KeyUsage("55 1D 0F"),
    PrivateKeyUsagePeriod("55 1D 10"),
    SubjectAltName("55 1D 11"),
    IssuerAltName("55 1D 12"),
    BasicConstraints("55 1D 13"),
    CRLNumber("55 1D 14"),
    CRLDistributionPoints("55 1D 1F"),
    CertificatePolicies("55 1D 20"),
    AuthorityKeyIdentifier("55 1D 23"),
    ExtKeyUsage("55 1D 25"),


    SHA256("60 86 48 01 65 03 04 02 01"),
    SHA384("60 86 48 01 65 03 04 02 02"),
    SHA512("60 86 48 01 65 03 04 02 03"),
    SHA224("60 86 48 01 65 03 04 02 04"),

    DSA_With_SHA224("60 86 48 01 65 03 04 03 01"),
    DSA_With_SHA256("60 86 48 01 65 03 04 03 02"),
    ;

    
    public final byte[] bytes;

    ObjectIdentifier(String hex_str) {
        this.bytes = HexUtils.parse(hex_str, HexFormat.FORMAT_FF_SPACE_FF);
    }

    public boolean equals(byte[] bytes) {
        return Arrays.equals(this.bytes, bytes);
    }

    public String toDecimalString() {
        return OIDUtils.format(bytes);
    }

    public static ObjectIdentifier valueOf(byte[] data) {
        Optional<ObjectIdentifier> result = Arrays.stream(values()).filter(item -> Arrays.equals(item.bytes, data)).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        else {
            throw new RuntimeException("Unknown OID: " + HexUtils.format(data, HexFormat.FORMAT_FF_SPACE_FF));
        }
    }
}
