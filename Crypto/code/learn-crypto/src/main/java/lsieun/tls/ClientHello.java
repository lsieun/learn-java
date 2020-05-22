package lsieun.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ClientHello {
    public final ProtocolVersion client_version;
    public final Random random;
    public final byte[] session_id;
    public final byte[] cipher_suites;
    public final byte[] compression_methods;

    public ClientHello(ProtocolVersion client_version,
                       Random random,
                       byte[] session_id,
                       byte[] cipher_suites,
                       byte[] compression_methods) {
        this.client_version = client_version;
        this.random = random;
        this.session_id = session_id;
        this.cipher_suites = cipher_suites;
        this.compression_methods = compression_methods;
    }

    public byte[] toBytes() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write(client_version.major);
            bao.write(client_version.minor);
            bao.write(random.toBytes());
            bao.write(session_id.length);
            bao.write(session_id);

            int cipher_suites_length = cipher_suites.length;
            bao.write(cipher_suites_length >> 8);
            bao.write(cipher_suites_length);
            bao.write(cipher_suites);

            int compression_method_length = compression_methods.length;
            bao.write(compression_method_length);
            bao.write(compression_methods);

            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("ClientHello.toBytes()");
        }
    }
}
