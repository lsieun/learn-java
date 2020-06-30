package lsieun.tls.utils;

import lsieun.tls.cipher.CipherSuiteIdentifier;
import lsieun.tls.cst.TLSConst;
import lsieun.tls.entity.*;
import lsieun.tls.entity.handshake.ClientHello;
import lsieun.tls.entity.handshake.ServerHello;
import lsieun.tls.entity.handshake.TLSRandom;
import lsieun.tls.param.ConnectionEnd;
import lsieun.tls.param.TLSServerParameters;

import java.io.IOException;

public class TLSServerUtils {
    public static void tls_accept(TLSConnection conn, TLSServerParameters tls_context) throws IOException {
        tls_context.connection_end = ConnectionEnd.CONNECTION_END_SERVER;

        // The client sends the first message
        tls_context.got_client_hello = false;
        ClientHello client_hello = null;
        while (!tls_context.got_client_hello) {
            TLSRecord record = TLSUtils.receive_message(conn, tls_context.active_recv_parameters);
            ContentType type = record.content_type;
            byte[] content = record.content;

            if (type == ContentType.CONTENT_HANDSHAKE) {
                client_hello = ClientHello.fromBytes(content);
                tls_context.got_client_hello = true;
            }
            else {
                throw new RuntimeException("type is wrong: " + type);
            }
        }

        select_cipher_suite(client_hello, tls_context);

        send_server_hello(conn, tls_context);
        send_certificate(conn, tls_context);
        send_server_hello_done(conn, tls_context);

        // Now the client should send a client key exchange, change cipher spec, and
        // an encrypted “finalize” message
        tls_context.peer_finished = false;
        while (!tls_context.peer_finished) {
            TLSRecord record = TLSUtils.receive_message(conn, tls_context.active_recv_parameters);
            ContentType type = record.content_type;
            byte[] content = record.content;

            if (type == ContentType.CONTENT_CHANGE_CIPHER_SPEC) {
                ChangeCipherSpec.validate(content);
                tls_context.peer_finished = true;
            }
            else {
                throw new RuntimeException("type is wrong: " + type);
            }
        }

        // Handshake is complete; now ready to start sending encrypted data
    }

    public static void select_cipher_suite(ClientHello client_hello, TLSServerParameters tls_context) {
        // FIXME: 这里应该根据Client提供的cipher里面进行选择
        tls_context.pending_send_parameters.suite = CipherSuiteIdentifier.TLS_NULL_WITH_NULL_NULL;
    }

    public static void send_server_hello(TLSConnection conn, TLSServerParameters tls_context) throws IOException {
        ProtocolVersion version = new ProtocolVersion(TLSConst.TLS_VERSION_MAJOR, TLSConst.TLS_VERSION_MINOR);
        int gmt_unix_time = (int) (System.currentTimeMillis() / 1000);
        TLSRandom random = new TLSRandom(gmt_unix_time);
        byte[] session_id = new byte[0];
        int cipher_suite = tls_context.pending_send_parameters.suite.val;
        int compression_method = 0;

        ServerHello server_hello = new ServerHello(version, random, session_id, cipher_suite, compression_method);
        TLSUtils.send_handshake_message(conn, tls_context, server_hello);
    }

    public static void send_certificate(TLSConnection conn, TLSServerParameters tls_context) {
        //
    }

    public static void send_server_hello_done(TLSConnection conn, TLSServerParameters tls_context) {
        //
    }

    /**
     * Finally, send server change cipher spec/finished message
     */
    public static void send_change_cipher_spec(TLSConnection conn, TLSServerParameters tls_context) {
        //
    }

    /**
     * This message will be encrypted using the newly negotiated keys
     */
    public static void send_finished(TLSConnection conn, TLSServerParameters tls_context) {
        //
    }
}
