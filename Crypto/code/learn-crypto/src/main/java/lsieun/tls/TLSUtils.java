package lsieun.tls;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;
import lsieun.crypto.asym.dh.DHKey;
import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.cert.x509.SignedX509Certificate;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.utils.BigUtils;
import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;

import java.io.IOException;
import java.math.BigInteger;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class TLSUtils {

    /**
     * Negotiate an TLS channel on an already-established socket.
     */
    public static void tls_connect(Connection conn, TLSParameters parameters) throws IOException {
        // Step 1. Send the TLS handshake “client hello” message
        send_client_hello(conn);

        // Step 2. Receive the server hello response
        parameters.server_hello_done = false;
        while (!parameters.server_hello_done) {
            receive_tls_msg(conn, parameters);
        }

        // Step 3. Send client key exchange, change cipher spec (7.1) and encrypted
        // handshake message
        send_client_key_exchange(conn, parameters);
    }

    public static void send_client_key_exchange(Connection conn, TLSParameters parameters) throws IOException {
        byte[] pre_master_key = null;
        byte[] key_exchange_message = null;

        CipherSuiteIdentifier cipher_suite = parameters.pending_send_parameters.suite;
        switch (cipher_suite) {
            case TLS_NULL_WITH_NULL_NULL:
                // TODO: 有没有server支持这个呢？
                // TODO: this is an error, exit here
                throw new RuntimeException("TLS_NULL_WITH_NULL_NULL Not Support");
            case TLS_RSA_WITH_NULL_MD5:
            case TLS_RSA_WITH_NULL_SHA:
            case TLS_RSA_EXPORT_WITH_RC4_40_MD5:
            case TLS_RSA_WITH_RC4_128_MD5:
            case TLS_RSA_WITH_RC4_128_SHA:
            case TLS_RSA_EXPORT_WITH_RC2_CBC_40_MD5:
            case TLS_RSA_WITH_IDEA_CBC_SHA:
            case TLS_RSA_EXPORT_WITH_DES40_CBC_SHA:
            case TLS_RSA_WITH_DES_CBC_SHA:
            case TLS_RSA_WITH_3DES_EDE_CBC_SHA:
                pre_master_key = new byte[TLSConst.MASTER_SECRET_LENGTH];
                key_exchange_message = rsa_key_exchange(parameters.server_public_key.rsa_public_key, pre_master_key);
                break;
            case TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA:
            case TLS_DH_DSS_WITH_DES_CBC_SHA:
            case TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA:
            case TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA:
            case TLS_DH_RSA_WITH_DES_CBC_SHA:
            case TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA:
                int pre_master_secret_len = BigUtils.toByteSize(parameters.server_dh_key.p);
                pre_master_key = new byte[pre_master_secret_len];
                key_exchange_message = dh_key_exchange(parameters.server_dh_key, pre_master_key);
                break;
            default:
                throw new RuntimeException("Unsupported Cipher Suite " + cipher_suite);
        }

        send_handshake_message(conn, HandshakeType.CLIENT_KEY_EXCHANGE, key_exchange_message);

        // Now, turn the premaster secret into an actual master secret (the
        // server side will do this concurrently).
        compute_master_secret(pre_master_key, parameters);

        // TODO: - for security, should also “purge” the premaster secret from
        // memory.
        calculate_keys(parameters);
    }

    public static void send_change_cipher_spec(Connection conn, TLSParameters parameters) throws IOException {
        byte[] send_buffer = new byte[1];
        send_buffer[0] = 1;
        send_message(conn, ContentType.CONTENT_CHANGE_CIPHER_SPEC, send_buffer);

        parameters.active_send_parameters = parameters.pending_send_parameters;
        parameters.pending_send_parameters = null;
    }

    public static byte[] dh_key_exchange(DHKey server_dh_key, byte[] pre_master_secret) {
        BigInteger g = server_dh_key.g;
        BigInteger p = server_dh_key.p;
        BigInteger Y = server_dh_key.Y;

        // TODO: obviously, make this random, and much longer
        BigInteger a = new BigInteger("6");
        BigInteger Yc = g.modPow(a, p);
        BigInteger Z = Y.modPow(a, p);

        byte[] Z_bytes = BigUtils.toByteArray(Z);
        System.arraycopy(Z_bytes, 0, pre_master_secret, pre_master_secret.length - Z_bytes.length, Z_bytes.length);

        byte[] Yc_bytes = BigUtils.toByteArray(Yc);
        int message_size = Yc_bytes.length + 2;

        byte[] key_exchange_message = new byte[message_size];
        key_exchange_message[0] = (byte) ((message_size >> 8) & 0xFF);
        key_exchange_message[1] = (byte) (message_size & 0xFF);
        System.arraycopy(Yc_bytes, 0, key_exchange_message, 2, Yc_bytes.length);

        return key_exchange_message;
    }

    public static byte[] rsa_key_exchange(RSAKey public_key, byte[] pre_master_key) {
        pre_master_key[0] = TLSConst.TLS_VERSION_MAJOR;
        pre_master_key[1] = TLSConst.TLS_VERSION_MINOR;
        for (int i = 2; i < TLSConst.MASTER_SECRET_LENGTH; i++) {
            // TODO: SHOULD BE RANDOM!
            pre_master_key[i] = (byte) i;
        }

        byte[] encrypted_pre_master_key = RSAUtils.rsa_encrypt(pre_master_key, public_key);
        int encrypted_length = encrypted_pre_master_key.length;

        byte[] key_exchange_message = new byte[encrypted_length + 2];
        key_exchange_message[0] = 0;
        key_exchange_message[1] = (byte) encrypted_length;
        System.arraycopy(encrypted_pre_master_key, 0, key_exchange_message, 2, encrypted_length);
        return key_exchange_message;
    }

    /**
     * Build and submit a TLS client hello handshake on the active
     * connection. It is up to the caller of this function to wait
     * for the server reply.
     */
    private static void send_client_hello(Connection conn) throws IOException {
        int major = TLSConst.TLS_VERSION_MAJOR;
        int minor = TLSConst.TLS_VERSION_MINOR;
        int local_time = (int) (System.currentTimeMillis() / 1000);
        byte[] session_id = new byte[0];
        byte[] cipher_suites = new byte[]{0x00, 0x0a};
        byte[] compression_methods = new byte[]{0};

        ProtocolVersion client_version = new ProtocolVersion(major, minor);
        Random random = new Random(local_time);
        ClientHello instance = new ClientHello(client_version, random, session_id, cipher_suites, compression_methods);
        byte[] data = instance.toBytes();
        send_handshake_message(conn, HandshakeType.CLIENT_HELLO, data);

    }

    public static void send_handshake_message(Connection conn, HandshakeType msg_type, byte[] content) throws IOException {
        Handshake instance = new Handshake(msg_type, content);
        byte[] data = instance.toBytes();
        send_message(conn, ContentType.CONTENT_HANDSHAKE, data);
    }

    public static void send_alert_message(Connection conn, AlertDescription alert_code) throws IOException {
        byte[] data = new byte[2];
        data[0] = (byte) AlertLevel.FATAL.val;
        data[1] = (byte) alert_code.val;
        send_message(conn, ContentType.CONTENT_ALERT, data);
    }

    public static void send_message(Connection conn, ContentType content_type, byte[] content) throws IOException {
        ProtocolVersion version = new ProtocolVersion(TLSConst.TLS_VERSION_MAJOR, TLSConst.TLS_VERSION_MINOR);
        TLSPlaintext instance = new TLSPlaintext(content_type, version, content);
        byte[] bytes = instance.toBytes();
        send(conn, bytes);
    }

    public static void send(Connection conn, byte[] data) throws IOException {
        conn.out.write(data);
        conn.out.flush();
    }

    public static void receive_tls_msg(Connection conn, TLSParameters parameters) throws IOException {
        byte[] header = new byte[5];
        // STEP 1 - read off the TLS Record layer
        receive(conn, header, 0, 5);

        int type = header[0];
        int major = header[1];
        int minor = header[2];
        int length = (header[3] & 0xFF) << 8 | (header[4] & 0xFF);

        int accum_bytes = 0;
        byte[] data = new byte[length];
        while (accum_bytes < length) {
            int byte_read = receive(conn, data, accum_bytes, length - accum_bytes);
            accum_bytes += byte_read;
        }

        ByteDashboard bd = new ByteDashboard(data);

        ContentType content_type = ContentType.valueOf(type);
        if (content_type == ContentType.CONTENT_HANDSHAKE) {
            while (bd.hasNext()) {
                byte b0 = bd.next();
                byte b1 = bd.next();
                byte b2 = bd.next();
                byte b3 = bd.next();

                HandshakeType msg_type = HandshakeType.valueOf(b0 & 0xFF);
                int msg_length = ((b1 & 0xFF) << 16) | ((b2 & 0xFF) << 8) | (b3 & 0xFF);
                byte[] bytes = bd.nextN(msg_length);

                switch (msg_type) {
                    case SERVER_HELLO:
                        parse_server_hello(bytes, parameters);
                        break;
                    case CERTIFICATE:
                        parse_x509_chain(bytes, parameters);
                        break;
                    case SERVER_HELLO_DONE:
                        parameters.server_hello_done = true;
                        break;
                    default:
                        System.out.println("Ignoring unrecognized handshake message" + msg_type);
                        break;
                }
            }

        } else if (content_type == ContentType.CONTENT_ALERT) {
            byte b0 = bd.next();
            byte b1 = bd.next();

            AlertLevel level = AlertLevel.valueOf(b0);
            AlertDescription description = AlertDescription.valueOf(b1);

            Alert alert = new Alert(level, description);
            report_alert(alert);

            if (level == AlertLevel.FATAL) {
                throw new RuntimeException("Fatal Alert");
            }
        }
        else if (content_type == ContentType.CONTENT_CHANGE_CIPHER_SPEC) {
            // TODO: 其实，我觉得这里不应该用while
            // 忽然，我想到，会不会packet重发的时候，会把active_recv_parameter给覆盖了
            while (bd.hasNext()) {
                byte change_cipher_spec_type = bd.next();
                if (change_cipher_spec_type != 1) {
                    throw new RuntimeException("Error - received message ChangeCipherSpec, but type != 1");
                }
                else {
                    parameters.active_recv_parameters = parameters.pending_recv_parameters;
                    parameters.pending_recv_parameters = null;
                }
            }
        }
        else {
            // Ignore content types not understood
            System.out.println("Ignoring non-recognized content type " + content_type);
        }
    }

    public static void report_alert(Alert alert) {
        String line = String.format("Alert - %s: %s", alert.level, alert.description);
        System.out.println(line);
    }

    public static ServerHello parse_server_hello(byte[] bytes, TLSParameters parameters) {
        ByteDashboard bd = new ByteDashboard(bytes);

        int major = bd.next();
        int minor = bd.next();
        ProtocolVersion server_version = new ProtocolVersion(major, minor);

        byte b0 = bd.next();
        byte b1 = bd.next();
        byte b2 = bd.next();
        byte b3 = bd.next();
        int gmt_unix_time = (b0 & 0xFF) << 24 | (b1 & 0xFF) << 16 | (b2 & 0xFF) | (b3 & 0xFF);
        byte[] random_bytes = bd.nextN(28);
        Random random = new Random(gmt_unix_time, random_bytes);

        int session_id_length = bd.next();
        byte[] session_id = bd.nextN(session_id_length);
        byte[] cipher_suite_bytes = bd.nextN(2);
        int cipher_suite = (cipher_suite_bytes[0] & 0xFF) << 8 | ((cipher_suite_bytes[1] & 0xFF));
        int compression_method = bd.next();

        parameters.pending_recv_parameters.suite = CipherSuiteIdentifier.valueOf(cipher_suite);
        parameters.pending_send_parameters.suite = CipherSuiteIdentifier.valueOf(cipher_suite);
        byte[] server_random = random.toBytes();
        System.arraycopy(server_random, 0, parameters.server_random, 0, TLSConst.RANDOM_LENGTH);

        ServerHello server_hello = new ServerHello(server_version, random, session_id, cipher_suite, compression_method);
        return server_hello;
    }

    public static List<SignedX509Certificate> parse_x509_chain(byte[] bytes, TLSParameters parameters) {
        ByteDashboard bd = new ByteDashboard(bytes);
        byte b0 = bd.next();

        HandshakeType handshake_type = HandshakeType.valueOf(b0);
        if (handshake_type != HandshakeType.CERTIFICATE) {
            throw new RuntimeException("Not Certificate Handshake");
        }
        int length = bd.nextInt(3);
        int total_certificate_length = bd.nextInt(3);

        List<SignedX509Certificate> list = new ArrayList<>();
        while (bd.hasNext()) {
            int cert_length = bd.nextInt(3);
            byte[] cert_bytes = bd.nextN(cert_length);
            SignedX509Certificate cert = X509Utils.parse_x509_certificate(cert_bytes);
            list.add(cert);
        }

        parameters.server_public_key = list.get(0).tbsCertificate.subjectPublicKeyInfo;

        return list;
    }

    public static int receive(Connection conn, byte[] buff, int off, int len) throws IOException {
        return conn.in.read(buff, off, len);
    }

    /**
     * Send data over an established TLS channel. tls_connect must already
     * have been called with this socket as a parameter.
     */
    public static void tls_send(Connection conn, TLSParameters tls_context, byte[] application_data) {
        //
    }

    /**
     * Received data from an established TLS channel.
     */
    public static int tls_recv(Connection conn, TLSParameters tls_context, byte[] buff) {
        return 0;
    }

    /**
     * Orderly shutdown of the TLS channel (note that the socket itself will
     * still be open after this is called).
     */
    public static void tls_shutdown() {
        //
    }

    public static void compute_master_secret(byte[] pre_master_secret, TLSParameters parameters) {
        byte[] label = "master secret".getBytes(StandardCharsets.UTF_8);
        byte[] seed = ByteUtils.concatenate(parameters.client_random, parameters.server_random);
        byte[] bytes = PRF.PRF(pre_master_secret, label, seed, TLSConst.MASTER_SECRET_LENGTH);
        System.arraycopy(bytes, 0, parameters.master_secret, 0, TLSConst.MASTER_SECRET_LENGTH);
    }

    public static void calculate_keys(TLSParameters parameters) {
        // NOTE: assuming send suite & recv suite will always be the same
        CipherSuite suite = CipherSuite.valueOf(parameters.pending_send_parameters.suite);
        int key_block_length = suite.hash_size * 2 + suite.key_size * 2 + suite.IV_size * 2;

        byte[] label = "key expansion".getBytes(StandardCharsets.UTF_8);
        byte[] seed = ByteUtils.concatenate(parameters.server_random, parameters.client_random);


        byte[] key_block = PRF.PRF(parameters.master_secret, label, seed, key_block_length);

        ByteDashboard bd = new ByteDashboard(key_block);

        ProtectionParameters send_parameters = parameters.pending_send_parameters;
        ProtectionParameters recv_parameters = parameters.pending_recv_parameters;
        send_parameters.mac_secret = bd.nextN(suite.hash_size);
        recv_parameters.mac_secret = bd.nextN(suite.hash_size);
        send_parameters.key = bd.nextN(suite.key_size);
        recv_parameters.key = bd.nextN(suite.key_size);
        send_parameters.iv = bd.nextN(suite.IV_size);
        recv_parameters.iv = bd.nextN(suite.IV_size);
    }
}
