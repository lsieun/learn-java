package lsieun.tls.cipher;

import lsieun.crypto.sym.BlockOperation;
import lsieun.crypto.sym.OperationMode;
import lsieun.crypto.sym.des.DESUtils;
import lsieun.crypto.sym.des.TripleDESUtils;
import lsieun.crypto.hash.DigestCtx;
import lsieun.crypto.hash.HashContextAlgorithm;
import lsieun.tls.cst.TLSConst;

import java.util.Arrays;

public enum CipherSuite {
    TLS_NULL_WITH_NULL_NULL(
            CipherSuiteIdentifier.TLS_NULL_WITH_NULL_NULL,
            0, 0, 0, 0,
            null, null, OperationMode.ECB,
            null),
    TLS_RSA_WITH_NULL_MD5(
            CipherSuiteIdentifier.TLS_RSA_WITH_NULL_MD5,
            0, 0, 0, TLSConst.MD5_BYTE_SIZE,
            null, null, OperationMode.ECB,
            DigestCtx::new_md5_digest),
    TLS_RSA_WITH_NULL_SHA(
            CipherSuiteIdentifier.TLS_RSA_WITH_NULL_SHA,
            0, 0, 0, TLSConst.SHA1_BYTE_SIZE,
            null, null, OperationMode.ECB,
            DigestCtx::new_sha1_digest),
    TLS_RSA_WITH_DES_CBC_SHA(
            CipherSuiteIdentifier.TLS_RSA_WITH_DES_CBC_SHA,
            8, 8, 8, TLSConst.SHA1_BYTE_SIZE,
            DESUtils::des_block_encrypt, DESUtils::des_block_decrypt, OperationMode.CBC,
            DigestCtx::new_sha1_digest),
    TLS_RSA_WITH_3DES_EDE_CBC_SHA(
            CipherSuiteIdentifier.TLS_RSA_WITH_3DES_EDE_CBC_SHA,
            8, 8, 24, TLSConst.SHA1_BYTE_SIZE,
            TripleDESUtils::des_block_encrypt, TripleDESUtils::des_block_decrypt, OperationMode.CBC,
            DigestCtx::new_sha1_digest),
    ;
    public final CipherSuiteIdentifier id;
    public final int block_size;
    public final int iv_size;
    public final int key_size;
    public final int hash_size;
    public final BlockOperation bulk_encrypt;
    public final BlockOperation bulk_decrypt;
    public final OperationMode mode;
    public final HashContextAlgorithm hash_algorithm;

    CipherSuite(CipherSuiteIdentifier id,
                int block_size, int iv_size, int key_size, int hash_size,
                BlockOperation bulk_encrypt,
                BlockOperation bulk_decrypt,
                OperationMode mode,
                HashContextAlgorithm hash_algorithm) {
        this.id = id;
        this.block_size = block_size;
        this.iv_size = iv_size;
        this.key_size = key_size;
        this.hash_size = hash_size;
        this.bulk_encrypt = bulk_encrypt;
        this.bulk_decrypt = bulk_decrypt;
        this.mode = mode;
        this.hash_algorithm = hash_algorithm;
    }

    public static CipherSuite valueOf(CipherSuiteIdentifier id) {
        return Arrays.stream(values()).filter(item -> item.id == id).findFirst().get();
    }
}
