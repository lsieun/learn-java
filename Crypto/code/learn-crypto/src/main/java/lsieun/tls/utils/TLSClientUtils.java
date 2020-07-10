package lsieun.tls.utils;

import lsieun.crypto.cert.x509.SignedCertificate;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.crypto.hash.Digest;
import lsieun.tls.cst.TLSConst;
import lsieun.tls.cipher.CipherSuiteIdentifier;
import lsieun.tls.entity.ContentType;
import lsieun.tls.entity.ProtocolVersion;
import lsieun.tls.entity.handshake.TLSRandom;
import lsieun.tls.entity.TLSRecord;
import lsieun.tls.entity.alert.Alert;
import lsieun.tls.entity.alert.AlertDescription;
import lsieun.tls.entity.alert.AlertLevel;
import lsieun.tls.entity.handshake.*;
import lsieun.tls.param.ConnectionEnd;
import lsieun.tls.param.TLSClientParameters;
import lsieun.tls.param.TLSParameters;
import lsieun.utils.BigUtils;
import lsieun.utils.ByteDashboard;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static lsieun.tls.cipher.CipherSuiteIdentifier.*;

public class TLSClientUtils {
    /**
     * Negotiate an TLS channel on an already-established socket.
     */
    public static void tls_connect(TLSConnection conn, TLSClientParameters tls_context) throws IOException {
        tls_context.connection_end = ConnectionEnd.CONNECTION_END_CLIENT;

        // Step 1. Send the TLS handshake “client hello” message
        send_client_hello(conn, tls_context);

        // Step 2. Receive the server hello response
        tls_context.server_hello_done = false;
        while (!tls_context.server_hello_done) {
            receive_tls_msg(conn, tls_context);
        }

        // Step 3. Send client key exchange, change cipher spec (7.1) and encrypted handshake message
        send_client_key_exchange(conn, tls_context);

        TLSUtils.send_change_cipher_spec(conn, tls_context);

        // This message will be encrypted using the newly negotiated keys
        send_finished(conn, tls_context);

        tls_context.peer_finished = false;
        while (!tls_context.peer_finished) {
            receive_tls_msg(conn, tls_context);
        }
    }

    /**
     * Build and submit a TLS client hello handshake on the active
     * connection. It is up to the caller of this function to wait
     * for the server reply.
     */
    public static void send_client_hello(TLSConnection conn, TLSParameters tls_context) throws IOException {
        int major = TLSConst.TLS_VERSION_MAJOR;
        int minor = TLSConst.TLS_VERSION_MINOR;
        int local_time = (int) (System.currentTimeMillis() / 1000);
        byte[] session_id = new byte[0];
//        byte[] cipher_suites = new byte[]{0x00, 0x0a, (byte)0xc0, 0x2f};
        CipherSuiteIdentifier[] cipher_suites = {
                TLS_RSA_WITH_AES_128_CBC_SHA
//                TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256
        };
        byte[] compression_methods = new byte[]{0};

        ProtocolVersion client_version = new ProtocolVersion(major, minor);
        TLSRandom random = new TLSRandom(local_time);

        byte[] client_random = random.toBytes();
        System.arraycopy(client_random, 0, tls_context.client_random, 0, TLSConst.RANDOM_LENGTH);

        ClientHello client_hello = new ClientHello(client_version, random, session_id, cipher_suites, compression_methods);
        TLSUtils.send_handshake_message(conn, tls_context, client_hello);
    }

    public static void receive_tls_msg(TLSConnection conn, TLSClientParameters tls_context) throws IOException {
        TLSRecord record = TLSUtils.receive_message(conn, tls_context.active_recv_parameters);

        ContentType content_type = record.content_type;
        byte[] content = record.content;
        ByteDashboard bd = new ByteDashboard(content);


        if (content_type == ContentType.CONTENT_HANDSHAKE) {
            Digest.update_digest(tls_context.md5_handshake_digest, content);
            Digest.update_digest(tls_context.sha1_handshake_digest, content);

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
                        parse_server_hello(bytes, tls_context);
                        break;
                    case CERTIFICATE:
                        parse_x509_chain(bytes, tls_context);
                        break;
                    case SERVER_HELLO_DONE:
                        tls_context.server_hello_done = true;
                        break;
                    case FINISHED:
                        TLSUtils.parse_finished(bytes, tls_context);
                        break;
                    default:
                        System.out.println("Ignoring unrecognized handshake message" + msg_type);
                        break;
                }
            }

        }
        else if (content_type == ContentType.CONTENT_ALERT) {
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
                    tls_context.active_recv_parameters.seq_num = 0;
                }
            }
        }
        else if (content_type == ContentType.CONTENT_APPLICATION_DATA) {
            //
        }
        else {
            // Ignore content types not understood
            System.out.println("Ignoring non-recognized content type " + content_type);
        }
    }

    public static ServerHello parse_server_hello(byte[] bytes, TLSParameters tls_context) {
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
        TLSRandom random = new TLSRandom(gmt_unix_time, random_bytes);

        int session_id_length = bd.next();
        byte[] session_id = bd.nextN(session_id_length);
        byte[] cipher_suite_bytes = bd.nextN(2);
        int cipher_suite = (cipher_suite_bytes[0] & 0xFF) << 8 | ((cipher_suite_bytes[1] & 0xFF));
        int compression_method = bd.next();

        tls_context.active_recv_parameters.suite = CipherSuiteIdentifier.valueOf(cipher_suite);
        tls_context.active_send_parameters.suite = CipherSuiteIdentifier.valueOf(cipher_suite);
        byte[] server_random = random.toBytes();
        System.arraycopy(server_random, 0, tls_context.server_random, 0, TLSConst.RANDOM_LENGTH);

        ServerHello server_hello = new ServerHello(server_version, random, session_id, cipher_suite, compression_method);
        return server_hello;
    }

    public static List<SignedCertificate> parse_x509_chain(byte[] bytes, TLSParameters tls_context) {
        ByteDashboard bd = new ByteDashboard(bytes);
        byte b0 = bd.next();

        HandshakeType handshake_type = HandshakeType.valueOf(b0);
        if (handshake_type != HandshakeType.CERTIFICATE) {
            throw new RuntimeException("Not Certificate Handshake");
        }
        int length = bd.nextInt(3);
        int total_certificate_length = bd.nextInt(3);

        if ((length - 3) != total_certificate_length) {
            throw new RuntimeException("The chain length is Not Right!");
        }

        List<SignedCertificate> list = new ArrayList<>();
        // TODO: each certificate after the first should be checked to ensure that it includes the “is a certificate authority” extension
        // Certificate chain parsing, then, consists of reading the length of the certificate
        // chain from the message, and then reading each certificate in turn, using
        // each to verify the last. Of course, the first must also be verified for freshness
        // and domain name validity.
        while (bd.hasNext()) {
            int cert_length = bd.nextInt(3);
            byte[] cert_bytes = bd.nextN(cert_length);
            SignedCertificate cert = X509Utils.parse_x509_certificate(cert_bytes);
            list.add(cert);
        }

        tls_context.server_public_key = list.get(0).tbs_certificate.subjectPublicKeyInfo;

        return list;
    }


    public static void report_alert(Alert alert) {
        String line = String.format("Alert - %s: %s", alert.level, alert.description);
        System.out.println(line);
    }

    public static void send_client_key_exchange(TLSConnection conn, TLSParameters tls_context) throws IOException {
        byte[] pre_master_key = null;
        byte[] key_exchange_message = null;

        CipherSuiteIdentifier cipher_suite = tls_context.active_send_parameters.suite;
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
                key_exchange_message = TLSUtils.rsa_key_exchange(tls_context.server_public_key.rsa_public_key.toKey(), pre_master_key);
                break;
            case TLS_DH_DSS_EXPORT_WITH_DES40_CBC_SHA:
            case TLS_DH_DSS_WITH_DES_CBC_SHA:
            case TLS_DH_DSS_WITH_3DES_EDE_CBC_SHA:
            case TLS_DH_RSA_EXPORT_WITH_DES40_CBC_SHA:
            case TLS_DH_RSA_WITH_DES_CBC_SHA:
            case TLS_DH_RSA_WITH_3DES_EDE_CBC_SHA:
                int pre_master_secret_len = BigUtils.toByteSize(tls_context.server_dh_key.p);
                pre_master_key = new byte[pre_master_secret_len];
                key_exchange_message = TLSUtils.dh_key_exchange(tls_context.server_dh_key, pre_master_key);
                break;
            default:
                throw new RuntimeException("Unsupported Cipher Suite " + cipher_suite);
        }

        ClientKeyExchange client_key_exchange = new ClientKeyExchange(key_exchange_message);
        TLSUtils.send_handshake_message(conn, tls_context, client_key_exchange);

        // Now, turn the premaster secret into an actual master secret (the
        // server side will do this concurrently).
        TLSUtils.compute_master_secret(pre_master_key, tls_context);

        // TODO: - for security, should also “purge” the pre-master secret from memory.
        TLSUtils.calculate_keys(tls_context);
    }

    public static void send_finished(TLSConnection conn, TLSParameters tls_context) throws IOException {
        byte[] finished_label = "client finished".getBytes(StandardCharsets.UTF_8);
        byte[] verify_data = TLSUtils.compute_verify_data(finished_label, tls_context);
        EncryptedHandshakeMessage encrypted_handshake_message = new EncryptedHandshakeMessage(verify_data);
        TLSUtils.send_handshake_message(conn, tls_context, encrypted_handshake_message);
    }
}
