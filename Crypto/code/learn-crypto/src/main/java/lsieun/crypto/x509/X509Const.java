package lsieun.crypto.x509;

public class X509Const {
    public static final byte[] OID_MD5WithRSA = {
            (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7, (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x04
    };
    public static final byte[] OID_SHA1WithRSA = {
            (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7, (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x05
    };
    public static final byte[] OID_SHA256WithRSA = {
            (byte) 0x2A, (byte) 0x86, (byte) 0x48, (byte) 0x86, (byte) 0xF7, (byte) 0x0D, (byte) 0x01, (byte) 0x01, (byte) 0x0B
    };

    public static final byte[] OID_RSA = {
            0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0x0D, 0x01, 0x01, 0x01
    };

    public static final byte[] OID_DH = {
            0x2A, (byte) 0x86, 0x48, (byte) 0xCE, 0x3E, 0x02, 0x01
    };

    public static final byte[] OID_subjectKeyIdentifier = {0x55, 0x1D, 0x0E};
    public static final byte[] OID_keyUsage = {0x55, 0x1D, 0x0F};
    public static final byte[] OID_basicConstraints = {0x55, 0x1D, 0x13};
    public static final byte[] OID_authorityKeyIdentifier = {0x55, 0x1D, 0x23};

    public static final byte[] OID_idAtCommonName = {0x55, 0x04, 0x03};
    public static final byte[] OID_idAtCountryName = {0x55, 0x04, 0x06};
    public static final byte[] OID_idAtLocalityName = {0x55, 0x04, 0x07};
    public static final byte[] OID_idAtStateOrProvinceName = {0x55, 0x04, 0x08};
    public static final byte[] OID_idAtOrganizationName = {0x55, 0x04, 0x0A};
    public static final byte[] OID_idAtOrganizationalUnitName = {0x55, 0x04, 0x0B};
    public static final byte[] OID_idAtEmailAddress = {0x2A, (byte) 0x86, 0x48, (byte) 0x86, (byte) 0xF7, 0X0D, 0X01, 0x09, 0x01};
}
