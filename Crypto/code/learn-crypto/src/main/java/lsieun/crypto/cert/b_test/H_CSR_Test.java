package lsieun.crypto.cert.b_test;

import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.cert.PEMUtils;
import lsieun.crypto.cert.csr.CSRUtils;
import lsieun.crypto.cert.csr.SignedCertificationRequest;
import lsieun.crypto.cert.x509.X509Utils;

public class H_CSR_Test {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/fd.csr";
        byte[] decode_bytes = PEMUtils.read(filepath);
        SignedCertificationRequest csr = CSRUtils.parse_csr(decode_bytes);
        CSRUtils.show(csr);

        RSAKey rsaKey = csr.certification_request_info.subject_public_key.rsa_public_key;
        boolean flag = X509Utils.validate_certificate_rsa(decode_bytes, rsaKey);
        System.out.println("Signature Verification: " + flag);

    }
}
