package lsieun.tls;

import lsieun.crypto.asym.dh.DHKey;
import lsieun.crypto.cert.x509.PublicKeyInfo;
import lsieun.hash.DigestCtx;

public class TLSParameters {
    public final byte[] master_secret = new byte[TLSConst.MASTER_SECRET_LENGTH];
    public final byte[] client_random = new byte[TLSConst.RANDOM_LENGTH];
    public final byte[] server_random = new byte[TLSConst.RANDOM_LENGTH];

    // TODO: 这里的ProtectionParameters，我觉得，可能并不需要两组，一组就够了
    public ProtectionParameters send_parameters = new ProtectionParameters();
    public ProtectionParameters recv_parameters = new ProtectionParameters();

    // RSA public key, if supplied
    public PublicKeyInfo server_public_key;

    // DH public key, if supplied (either in a certificate or ephemerally)
    // Note that a server can legitimately have an RSA key for signing and
    // a DH key for key exchange (e.g. DHE_RSA)
    public DHKey server_dh_key;

    public boolean server_hello_done;
    public boolean server_finished;

    public final DigestCtx md5_handshake_digest = DigestCtx.new_md5_digest();
    public final DigestCtx sha1_handshake_digest = DigestCtx.new_sha1_digest();


}
