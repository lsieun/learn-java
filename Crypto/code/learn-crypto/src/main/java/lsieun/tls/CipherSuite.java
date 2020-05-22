package lsieun.tls;

import lsieun.hash.HashAlgorithm;
import lsieun.hash.md5.MD5Const;
import lsieun.hash.md5.MD5Utils;
import lsieun.hash.sha1.SHA1Utils;

import java.util.Arrays;

public enum CipherSuite {
    TLS_NULL_WITH_NULL_NULL(CipherSuiteIdentifier.TLS_NULL_WITH_NULL_NULL, 0, 0, 0, 0,null),
    TLS_RSA_WITH_NULL_MD5(CipherSuiteIdentifier.TLS_RSA_WITH_NULL_MD5, 0, 0, 0, TLSConst.MD5_BYTE_SIZE, MD5Utils::md5_hash),
    TLS_RSA_WITH_NULL_SHA(CipherSuiteIdentifier.TLS_RSA_WITH_NULL_SHA, 0, 0, 0, TLSConst.SHA1_BYTE_SIZE, SHA1Utils::sha1_hash),
    TLS_RSA_WITH_DES_CBC_SHA(CipherSuiteIdentifier.TLS_RSA_WITH_DES_CBC_SHA, 8, 8, 8, TLSConst.SHA1_BYTE_SIZE, SHA1Utils::sha1_hash),
    TLS_RSA_WITH_3DES_EDE_CBC_SHA(CipherSuiteIdentifier.TLS_RSA_WITH_3DES_EDE_CBC_SHA, 8, 8, 24, TLSConst.SHA1_BYTE_SIZE, SHA1Utils::sha1_hash),
    ;
    public final CipherSuiteIdentifier id;
    public final int block_size;
    public final int IV_size;
    public final int key_size;
    public final int hash_size;
    public final HashAlgorithm hash_algorithm;

    CipherSuite(CipherSuiteIdentifier id,
                int block_size, int IV_size, int key_size, int hash_size,
                HashAlgorithm hash_algorithm) {
        this.id = id;
        this.block_size = block_size;
        this.IV_size = IV_size;
        this.key_size = key_size;
        this.hash_size = hash_size;
        this.hash_algorithm = hash_algorithm;
    }

    public static CipherSuite valueOf(CipherSuiteIdentifier id) {
        return Arrays.stream(values()).filter(item -> item.id == id).findFirst().get();
    }
}
