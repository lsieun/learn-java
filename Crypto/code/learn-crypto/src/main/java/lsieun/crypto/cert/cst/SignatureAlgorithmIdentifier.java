package lsieun.crypto.cert.cst;

import java.util.Arrays;
import java.util.Optional;

/**
 * <p>
 * 1.2.840.113549.1.1 - PKCS-1
 * </p>
 * https://www.alvestrand.no/objectid/1.2.840.113549.1.1.html
 */
public enum SignatureAlgorithmIdentifier {
    MD5_WITH_RSA_ENCRYPTION(ObjectIdentifier.MD5_With_RSA, HashIdentifier.MD5, AlgorithmIdentifier.RSA), // 4
    SHA1_WITH_RSA_ENCRYPTION(ObjectIdentifier.SHA1_With_RSA, HashIdentifier.SHA1, AlgorithmIdentifier.RSA), // 5
    SHA256_WITH_RSA_ENCRYPTION(ObjectIdentifier.SHA256_With_RSA, HashIdentifier.SHA256, AlgorithmIdentifier.RSA), // 11
    DSA_With_SHA256(ObjectIdentifier.DSA_With_SHA256, HashIdentifier.SHA256, AlgorithmIdentifier.DSA),
    ECDSA_With_SHA256(ObjectIdentifier.ECDSA_With_SHA256, HashIdentifier.SHA256, AlgorithmIdentifier.ECDSA),
    ;

    public final ObjectIdentifier oid;
    public final HashIdentifier hid;
    public final AlgorithmIdentifier aid;

    SignatureAlgorithmIdentifier(ObjectIdentifier oid, HashIdentifier hid, AlgorithmIdentifier aid) {
        this.oid = oid;
        this.hid = hid;
        this.aid = aid;
    }

    public static SignatureAlgorithmIdentifier valueOf(ObjectIdentifier oid) {
        Optional<SignatureAlgorithmIdentifier> result = Arrays.stream(values()).filter(item -> item.oid == oid).findFirst();
        if (result.isPresent()) {
            return result.get();
        }
        else {
            throw new RuntimeException("Unknown Signature: " + oid);
        }
    }
}
