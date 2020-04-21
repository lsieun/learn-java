package lsieun.crypto.cert.x509;

import java.util.List;

public class X509Certificate {
    public final int version;
    public final String serialNumber; // This can be much longer than a 4-byte long allows
    public final SignatureAlgorithmIdentifier signature;
    public final Name issuer;
    public final ValidityPeriod validity;
    public final Name subject;
    public final PublicKeyInfo subjectPublicKeyInfo;
    public final List<Extension> extensions;


    public X509Certificate(int version,
                           String serialNumber,
                           SignatureAlgorithmIdentifier signature,
                           Name issuer,
                           ValidityPeriod validity,
                           Name subject,
                           PublicKeyInfo subjectPublicKeyInfo,
                           List<Extension> extensions) {
        this.version = version;
        this.serialNumber = serialNumber;
        this.signature = signature;
        this.issuer = issuer;
        this.validity = validity;
        this.subject = subject;
        this.subjectPublicKeyInfo = subjectPublicKeyInfo;
        this.extensions = extensions;
    }
}
