package lsieun.crypto.x509;

import java.math.BigInteger;

public class X509Certificate {
    public final int version;
    public final BigInteger serialNumber; // This can be much longer than a 4-byte long allows
    public final SignatureAlgorithmIdentifier signature;
    public final Name issuer;
    public final ValidityPeriod validity;
    public final Name subject;
    public final PublicKeyInfo subjectPublicKeyInfo;
    public final BigInteger issuerUniqueId;
    public final BigInteger subjectUniqueId;
    public final int certificate_authority; // 1 if this is a CA, 0 if not

    public X509Certificate(int version,
                           BigInteger serialNumber,
                           SignatureAlgorithmIdentifier signature,
                           Name issuer,
                           ValidityPeriod validity,
                           Name subject,
                           PublicKeyInfo subjectPublicKeyInfo,
                           BigInteger issuerUniqueId,
                           BigInteger subjectUniqueId,
                           int certificate_authority) {
        this.version = version;
        this.serialNumber = serialNumber;
        this.signature = signature;
        this.issuer = issuer;
        this.validity = validity;
        this.subject = subject;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.issuerUniqueId = issuerUniqueId;
        this.subjectUniqueId = subjectUniqueId;
        this.certificate_authority = certificate_authority;
    }
}
