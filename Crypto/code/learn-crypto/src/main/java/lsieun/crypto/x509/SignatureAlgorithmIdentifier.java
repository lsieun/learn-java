package lsieun.crypto.x509;

/**
 * <p>
 *     1.2.840.113549.1.1 - PKCS-1
 * </p>
 * https://www.alvestrand.no/objectid/1.2.840.113549.1.1.html
 */
public enum SignatureAlgorithmIdentifier {
    RSA_ENCRYPTION, // 1
    MD2_WITH_RSA_ENCRYPTION, // 2
    MD4_WITH_RSA_ENCRYPTION,// 3
    MD5_WITH_RSA_ENCRYPTION, // 4
    SHA1_WITH_RSA_ENCRYPTION, // 5
    SHA256_WITH_RSA_ENCRYPTION, // 11
    SHA384_WITH_RSA_ENCRYPTION, //12
    SHA512_WITH_RSA_ENCRYPTION, // 13
    SHA224_WITH_RSA_ENCRYPTION, // 14
}
