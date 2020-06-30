package lsieun.tls.param;

import lsieun.crypto.asym.dh.DHKey;
import lsieun.crypto.cert.x509.PublicKeyInfo;
import lsieun.crypto.hash.DigestCtx;
import lsieun.tls.cst.TLSConst;

public class TLSParameters {
    public ConnectionEnd connection_end;

    public final byte[] master_secret = new byte[TLSConst.MASTER_SECRET_LENGTH];
    public final byte[] client_random = new byte[TLSConst.RANDOM_LENGTH];
    public final byte[] server_random = new byte[TLSConst.RANDOM_LENGTH];

    public ProtectionParameters pending_send_parameters = new ProtectionParameters();
    public ProtectionParameters pending_recv_parameters = new ProtectionParameters();
    public ProtectionParameters active_send_parameters = new ProtectionParameters();
    public ProtectionParameters active_recv_parameters = new ProtectionParameters();

    // RSA public key, if supplied
    public PublicKeyInfo server_public_key;

    // DH public key, if supplied (either in a certificate or ephemerally)
    // Note that a server can legitimately have an RSA key for signing and
    // a DH key for key exchange (e.g. DHE_RSA)
    public DHKey server_dh_key;

    // Internal state
    public boolean peer_finished;

    public final DigestCtx md5_handshake_digest = DigestCtx.new_md5_digest();
    public final DigestCtx sha1_handshake_digest = DigestCtx.new_sha1_digest();


}
