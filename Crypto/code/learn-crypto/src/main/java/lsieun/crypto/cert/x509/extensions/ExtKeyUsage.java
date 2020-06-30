package lsieun.crypto.cert.x509.extensions;

import lsieun.crypto.cert.asn1.ASN1Struct;
import lsieun.crypto.cert.asn1.ASN1Utils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExtKeyUsage extends Extension {
    public List<String> key_usage_list;

    public ExtKeyUsage(byte[] oid_bytes, boolean critical, byte[] data,
                       List<String> key_usage_list) {
        super(oid_bytes, critical, data);
        this.key_usage_list = key_usage_list;
    }

    public static ExtKeyUsage parse_ext_key_usage(byte[] oid_bytes, boolean critical, byte[] data) {
        ASN1Struct asn1_seq = ASN1Utils.parse_der(data).get(0);

        List<String> key_usage_list = new ArrayList<>();
        for (ASN1Struct item : asn1_seq.children) {
            if (Arrays.equals(item.data, OID_serverAuth)) {
                key_usage_list.add("serverAuth");
            }
            else if (Arrays.equals(item.data, OID_clientAuth)) {
                key_usage_list.add("clientAuth");
            }
            else if (Arrays.equals(item.data, OID_codeSigning)) {
                key_usage_list.add("codeSigning");
            }
            else if (Arrays.equals(item.data, OID_emailProtection)) {
                key_usage_list.add("emailProtection");
            }
            else {
                throw new RuntimeException("unknown oid");
            }
        }

        return new ExtKeyUsage(oid_bytes, critical, data, key_usage_list);
    }

    // http://oidref.com/1.3.6.1.5.5.7.3
    public static final byte[] OID_serverAuth = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x01};
    public static final byte[] OID_clientAuth = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x02};
    public static final byte[] OID_codeSigning = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x03};
    public static final byte[] OID_emailProtection = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x04};
    public static final byte[] OID_ipsecEndSystem = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x05};
    public static final byte[] OID_ipsecTunnel = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x06};
    public static final byte[] OID_ipsecUser = {0x2B, 0x06, 0x01, 0x05, 0x05, 0x07, 0x03, 0x07};

}
