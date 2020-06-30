package lsieun.crypto.cert.x509;

import lsieun.crypto.cert.cst.SignatureAlgorithmIdentifier;

public class SignedCertificate {
    public final TBSCertificate tbs_certificate;
    public final SignatureAlgorithmIdentifier signature_algorithm;
    public final SignatureValue signature_value;

    public SignedCertificate(TBSCertificate tbs_certificate,
                             SignatureAlgorithmIdentifier signature_algorithm,
                             SignatureValue signature_value) {
        this.tbs_certificate = tbs_certificate;
        this.signature_algorithm = signature_algorithm;
        this.signature_value = signature_value;
    }
}
