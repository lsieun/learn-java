package lsieun.crypto.cert.x509;

public class SignedX509Certificate {
    public final X509Certificate tbsCertificate;
    public final SignatureAlgorithmIdentifier algorithm;
    public final SignatureValue signature_value;

    public SignedX509Certificate(X509Certificate tbsCertificate,
                                 SignatureAlgorithmIdentifier algorithm,
                                 SignatureValue signature_value) {
        this.tbsCertificate = tbsCertificate;
        this.algorithm = algorithm;
        this.signature_value = signature_value;
    }
}
