package lsieun.crypto.cert.oid;

import java.util.HashMap;
import java.util.Map;

public class OIDConst {
    public static final Map<String, String> oid_name_map = new HashMap<>();

    static {
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 01", "RsaEncryption");
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 04", "Md5WithRSAEncryption");
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 05", "Sha1-with-rsa-signature");
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 0B", "Sha256WithRSAEncryption");
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 0C", "Sha384WithRSAEncryption");
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 0D", "Sha512WithRSAEncryption");
        oid_name_map.put("2A 86 48 86 F7 0D 01 01 0E", "Sha224WithRSAEncryption");

        oid_name_map.put("2A 86 48 86 F7 0D 01 09 01", "EmailAddress");
        oid_name_map.put("2A 86 48 86 F7 0D 01 09 07", "ChallengePassword");

        oid_name_map.put("2B 06 01 04 01 D6 79 02 04 02", "Extended validation certificates");
        oid_name_map.put("2B 06 01 05 05 07 01 01", "AuthorityInfoAccess");

        oid_name_map.put("55 04 03", "CommonName");
        oid_name_map.put("55 04 04", "Surname");
        oid_name_map.put("55 04 05", "serialNumber");
        oid_name_map.put("55 04 06", "CountryName");
        oid_name_map.put("55 04 07", "LocalityName");
        oid_name_map.put("55 04 08", "StateOrProvinceName");
        oid_name_map.put("55 04 09", "StreetAddress");
        oid_name_map.put("55 04 0A", "OrganizationName");
        oid_name_map.put("55 04 0B", "OrganizationUnitName");

        oid_name_map.put("55 1D 0E", "SubjectKeyIdentifier");
        oid_name_map.put("55 1D 0F", "KeyUsage");
        oid_name_map.put("55 1D 10", "PrivateKeyUsagePeriod");
        oid_name_map.put("55 1D 11", "SubjectAltName");
        oid_name_map.put("55 1D 12", "issuerAltName");
        oid_name_map.put("55 1D 13", "BasicConstraints");
        oid_name_map.put("55 1D 14", "CRLNumber");

        oid_name_map.put("55 1D 1F", "CRLDistributionPoints");

        oid_name_map.put("55 1D 20", "CertificatePolicies");
        oid_name_map.put("55 1D 23", "AuthorityKeyIdentifier");
        oid_name_map.put("55 1D 25", "ExtKeyUsage");
    }

    public static String get(String oid_hex) {
        final String value = oid_name_map.get(oid_hex);
        if (value == null) return oid_hex;
        return value;
    }
}
