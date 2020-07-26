package lsieun.cert.csr;

import lsieun.cert.cst.SignatureAlgorithmIdentifier;
import lsieun.cert.x509.SignatureValue;

public class CertificationRequest {
    public final CertificationRequestInfo certification_request_info;
    public final SignatureAlgorithmIdentifier signature_algorithm;
    public final SignatureValue signature_value;

    public CertificationRequest(CertificationRequestInfo certification_request_info,
                                SignatureAlgorithmIdentifier signature_algorithm,
                                SignatureValue signature_value) {
        this.certification_request_info = certification_request_info;
        this.signature_algorithm = signature_algorithm;
        this.signature_value = signature_value;
    }
}
