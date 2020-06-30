package lsieun.tls.entity.handshake;

import lsieun.tls.entity.ProtocolVersion;
import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ServerHello extends Handshake {
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
        super(HandshakeType.SERVER_HELLO);
        this.server_version = server_version;
        this.random = random;
        this.session_id = session_id;
        this.cipher_suite = cipher_suite;
        this.compression_method = compression_method;
    }

    public byte[] getData() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            bao.write(server_version.major); // version
            bao.write(server_version.minor);

            bao.write(random.toBytes()); // random

            bao.write(session_id.length); // session
            bao.write(session_id);

            bao.write(cipher_suite >> 8 & 0xFF); // cipher suite
            bao.write(cipher_suite & 0xFF);

            bao.write(compression_method); // compression

            return bao.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @SuppressWarnings("Duplicates")
    public static ServerHello fromBytes(byte[] data) {
        try {
            ByteDashboard bd = new ByteDashboard(data);
            byte major = bd.next();
            byte minor = bd.next();

            ProtocolVersion version = new ProtocolVersion(major, minor);
            TLSRandom random = TLSRandom.fromBytes(bd.nextN(32));

            byte session_id_length = bd.next();
            byte[] session_id = bd.nextN(session_id_length);

            int cipher_suite = ByteUtils.toInt(bd.nextN(2));
            int compression_method = bd.next() & 0xFF;

            return new ServerHello(version, random, session_id, cipher_suite, compression_method);
        }
        catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
