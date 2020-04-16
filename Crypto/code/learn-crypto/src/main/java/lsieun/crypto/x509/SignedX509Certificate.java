package lsieun.crypto.x509;

import java.math.BigInteger;

public class SignedX509Certificate {
    public final X509Certificate tbsCertificate;
    public final SignatureAlgorithmIdentifier algorithm;
    public final BigInteger signature_value;

    public SignedX509Certificate(X509Certificate tbsCertificate,
                                 SignatureAlgorithmIdentifier algorithm,
                                 BigInteger signature_value) {
        this.tbsCertificate = tbsCertificate;
        this.algorithm = algorithm;
        this.signature_value = signature_value;
    }
}
