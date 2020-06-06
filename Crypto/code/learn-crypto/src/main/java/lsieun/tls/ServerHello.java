package lsieun.tls;

public class ServerHello {
    public final ProtocolVersion server_version;
    public final TLSRandom random;
    public final byte[] session_id;
    public final int cipher_suite;
    public final int compression_method;

    public ServerHello(ProtocolVersion server_version,
                       TLSRandom random,
                       byte[] session_id,
                       int cipher_suite,
                       int compression_method) {
        this.server_version = server_version;
        this.random = random;
        this.session_id = session_id;
        this.cipher_suite = cipher_suite;
        this.compression_method = compression_method;
    }
}
