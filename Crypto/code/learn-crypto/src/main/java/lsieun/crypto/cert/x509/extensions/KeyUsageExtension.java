package lsieun.crypto.cert.x509.extensions;

import lsieun.crypto.cert.x509.Extension;

public class KeyUsageExtension extends Extension {
    public final boolean isDigitalSignature;
    public final boolean isNonRepudiation;
    public final boolean isKeyEncipherment;
    public final boolean isDataEncipherment;
    public final boolean isKeyAgreement;
    public final boolean isKeyCertSign;
    public final boolean isCRLSign;
    public final boolean isEncipherOnly;
    public final boolean isDecipherOnly;

    public KeyUsageExtension(byte[] oid_bytes, boolean critical, byte[] data,
                             boolean isDigitalSignature,
                             boolean isNonRepudiation,
                             boolean isKeyEncipherment,
                             boolean isDataEncipherment,
                             boolean isKeyAgreement,
                             boolean isKeyCertSign,
                             boolean isCRLSign,
                             boolean isEncipherOnly,
                             boolean isDecipherOnly) {
        super(oid_bytes, critical, data);
        this.isDigitalSignature = isDigitalSignature;
        this.isNonRepudiation = isNonRepudiation;
        this.isKeyEncipherment = isKeyEncipherment;
        this.isDataEncipherment = isDataEncipherment;
        this.isKeyAgreement = isKeyAgreement;
        this.isKeyCertSign = isKeyCertSign;
        this.isCRLSign = isCRLSign;
        this.isEncipherOnly = isEncipherOnly;
        this.isDecipherOnly = isDecipherOnly;
    }
}
