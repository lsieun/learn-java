package lsieun.crypto.cert.x509;

import lsieun.crypto.asym.rsa.RSAKey;

public class PublicKeyInfo {
    public final AlgorithmIdentifier algorithm;
    public final RSAKey rsa_public_key;

    public PublicKeyInfo(AlgorithmIdentifier algorithm, RSAKey rsa_public_key) {
        this.algorithm = algorithm;
        this.rsa_public_key = rsa_public_key;
    }
}
