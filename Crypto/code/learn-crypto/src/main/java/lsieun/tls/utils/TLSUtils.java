package lsieun.tls.utils;

import lsieun.crypto.asym.dh.DHKey;
import lsieun.crypto.asym.rsa.RSAKey;
import lsieun.crypto.asym.rsa.RSAUtils;
import lsieun.crypto.sym.CBCUtils;
import lsieun.crypto.sym.ECBUtils;
import lsieun.crypto.sym.OperationMode;
import lsieun.crypto.hash.Digest;
import lsieun.tls.cst.TLSConst;
import lsieun.tls.cipher.CipherSuite;
import lsieun.tls.entity.ContentType;
import lsieun.tls.entity.ProtocolVersion;
import lsieun.tls.entity.TLSRecord;
import lsieun.tls.entity.alert.AlertDescription;
import lsieun.tls.entity.alert.AlertLevel;
import lsieun.tls.entity.handshake.Handshake;
import lsieun.tls.param.ProtectionParameters;
import lsieun.tls.param.TLSParameters;
import lsieun.utils.BigUtils;
import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TLSUtils {

    public static void parse_finished(byte[] bytes, TLSParameters tls_context) {
        byte[] finished_label;
        switch (tls_context.connection_end) {
            case CONNECTION_END_CLIENT:
                finished_label = "server finished".getBytes(StandardCharsets.UTF_8);
                break;
            case CONNECTION_END_SERVER:
                finished_label = "client finished".getBytes(StandardCharsets.UTF_8);
                break;
            default:
                throw new RuntimeException("Connection End Unsupported");
        }

        byte[] verify_data = compute_verify_data(finished_label, tls_context);
        if (!Arrays.equals(bytes, verify_data)) {
            throw new RuntimeException("Not Equal");
        }
        tls_context.peer_finished = true;
    }

    /**
     * 7.4.9:
     * verify_data = PRF( master_secret, “client finished”, MD5(handshake_messages)
     * + SHA-1(handshake_messages)) [0..11]
     * <p>
     * master_secret = PRF( pre_master_secret, “master secret”, ClientHello.random +
     * ServerHello.random );
     * always 48 bytes in length.
     */
    public static byte[] compute_verify_data(byte[] finished_label, TLSParameters tls_context) {
        byte[] md5_digest = Digest.finalize_digest(tls_context.md5_handshake_digest);
        byte[] sha1_digest = Digest.finalize_digest(tls_context.sha1_handshake_digest);
        byte[] handshake_hash = ByteUtils.concatenate(md5_digest, sha1_digest);
        byte[] verify_data = PRFUtils.PRF(tls_context.master_secret, finished_label, handshake_hash, TLSConst.VERIFY_DATA_LEN);
        return verify_data;
    }


    public static void send_change_cipher_spec(TLSConnection conn, TLSParameters tls_context) throws IOException {
        byte[] send_buffer = new byte[1];
        send_buffer[0] = 1;
        send_message(conn, tls_context.active_send_parameters, ContentType.CONTENT_CHANGE_CIPHER_SPEC, send_buffer);

        tls_context.active_send_parameters.seq_num = 0L;
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


//    public static void send_handshake_message(Connection conn, TLSParameters tls_context, HandshakeType msg_type, byte[] content) throws IOException {
//        Handshake instance = new Handshake(msg_type, content);
//        byte[] data = instance.toBytes();
//        Digest.update_digest(tls_context.md5_handshake_digest, data);
//        Digest.update_digest(tls_context.sha1_handshake_digest, data);
//        send_message(conn, tls_context.send_parameters, ContentType.CONTENT_HANDSHAKE, data);
//    }

    public static void send_handshake_message(TLSConnection conn, TLSParameters tls_context, Handshake handshake) throws IOException {
        byte[] data = handshake.toBytes();
        Digest.update_digest(tls_context.md5_handshake_digest, data);
        Digest.update_digest(tls_context.sha1_handshake_digest, data);
        send_plain_record(conn, ContentType.CONTENT_HANDSHAKE, data);
    }

    public static void send_plain_record(TLSConnection conn, ContentType content_type, byte[] content) throws IOException {
        ProtocolVersion version = new ProtocolVersion(TLSConst.TLS_VERSION_MAJOR, TLSConst.TLS_VERSION_MINOR);
        TLSRecord instance = new TLSRecord(content_type, version, content);
        byte[] bytes = instance.toBytes();
        conn.send(bytes);
    }

    public static void send_alert_message(TLSConnection conn, TLSParameters tls_context, AlertDescription alert_code) throws IOException {
        byte[] data = new byte[2];
        data[0] = (byte) AlertLevel.FATAL.val;
        data[1] = (byte) alert_code.val;
        send_message(conn, tls_context.active_send_parameters, ContentType.CONTENT_ALERT, data);
    }

    /**
     * send TLS Recode
     *
     * @param conn
     * @param content_type
     * @param content
     * @throws IOException
     */
    public static void send_message(TLSConnection conn, ProtectionParameters parameters, ContentType content_type, byte[] content) throws IOException {
        int content_length = content.length;
        ProtocolVersion version = new ProtocolVersion(TLSConst.TLS_VERSION_MAJOR, TLSConst.TLS_VERSION_MINOR);


        CipherSuite active_suite = CipherSuite.valueOf(parameters.suite);

        byte[] mac = null;
        if (active_suite.hash_algorithm != null) {
            // Allocate enough space for the 8-byte sequence number, the 5-byte pseudo
            // header, and the content.
            byte[] seq_num_bytes = ByteUtils.fromLong(parameters.seq_num);

            byte[] mac_buffer = new byte[13 + content_length];
            System.arraycopy(seq_num_bytes, 0, mac_buffer, 0, 8);
            mac_buffer[8] = (byte) content_type.val;
            mac_buffer[9] = (byte) version.major;
            mac_buffer[10] = (byte) version.minor;
            mac_buffer[11] = (byte) (content_length >> 8 & 0xFF);
            mac_buffer[12] = (byte) (content_length & 0xFF);
            System.arraycopy(content, 0, mac_buffer, 13, content_length);

            mac = Digest.hmac(parameters.mac_secret, mac_buffer, active_suite.hash_algorithm);
            if (mac.length != active_suite.hash_size) {
                throw new RuntimeException("Hash Size Not Equals");
            }
        }

        int send_buffer_size = content_length + active_suite.hash_size;
        parameters.seq_num++;

        int padding_length = 0;
        if (active_suite.block_size != 0) {
            padding_length = active_suite.block_size - (send_buffer_size % active_suite.block_size);
            send_buffer_size += padding_length;
        }


        // Add space for the header, but only after computing padding
        send_buffer_size += 5;
        byte[] send_buffer = new byte[send_buffer_size];
        if (mac != null) {
            System.arraycopy(mac, 0, send_buffer, content_length + 5, active_suite.hash_size);
        }

        if (padding_length > 0) {
            for (int i = 0; i < padding_length; i++) {
                send_buffer[content_length + 5 + active_suite.hash_size + i] = (byte) (padding_length - 1);
            }
        }

        send_buffer[0] = (byte) content_type.val;
        send_buffer[1] = (byte) version.major;
        send_buffer[2] = (byte) version.minor;
        send_buffer[3] = (byte) (send_buffer.length >> 8 & 0xFF);
        send_buffer[4] = (byte) (send_buffer.length & 0xFF);
        System.arraycopy(content, 0, send_buffer, 5, content_length);

        if (active_suite.bulk_encrypt != null) {
            int len1 = content_length + active_suite.hash_size + padding_length;
            byte[] input = new byte[len1];
            System.arraycopy(content, 0, input, 0, content_length);
            System.arraycopy(mac, 0, input, content_length, active_suite.hash_size);
            for (int i = 0; i < padding_length; i++) {
                input[content_length + 5 + active_suite.hash_size + i] = (byte) (padding_length - 1);
            }

            // The first 5 bytes (the header) aren’t encrypted
            byte[] output = null;
            switch (active_suite.mode) {
                case ECB:
                    output = ECBUtils.ecb_encrypt(input, parameters.key, active_suite.block_size, active_suite.bulk_encrypt);
                    break;
                case CBC:
                    output = CBCUtils.cbc_encrypt(input, parameters.key, parameters.iv, active_suite.block_size, active_suite.bulk_encrypt);
                    break;
                default:
                    throw new RuntimeException("Unknown Mode" + active_suite.mode);
            }

            System.arraycopy(output, 0, send_buffer, 5, len1);
        }


        TLSRecord instance = new TLSRecord(content_type, version, send_buffer);
        byte[] bytes = instance.toBytes();
        conn.send(bytes);
    }




    public static int receive(TLSConnection conn, byte[] buff, int off, int len) throws IOException {
        return conn.in.read(buff, off, len);
    }

    /**
     * Send data over an established TLS channel. tls_connect must already
     * have been called with this socket as a parameter.
     */
    public static void tls_send(TLSConnection conn, TLSParameters tls_context, byte[] application_data) throws IOException {
        send_message(conn, tls_context.active_send_parameters, ContentType.CONTENT_APPLICATION_DATA, application_data);
    }

    /**
     * Received data from an established TLS channel.
     */
    public static TLSRecord tls_recv(TLSConnection conn, TLSParameters tls_context) throws IOException {
        return receive_message(conn, tls_context.active_recv_parameters);
    }

    /**
     * Orderly shutdown of the TLS channel (note that the socket itself will
     * still be open after this is called).
     */
    public static void tls_shutdown(TLSConnection conn, TLSParameters tls_context) throws IOException {
        send_alert_message(conn, tls_context, AlertDescription.CLOSE_NOTIFY);
    }

    public static void compute_master_secret(byte[] pre_master_secret, TLSParameters tls_context) {
        byte[] label = "master secret".getBytes(StandardCharsets.UTF_8);
        byte[] seed = ByteUtils.concatenate(tls_context.client_random, tls_context.server_random);
        byte[] bytes = PRFUtils.PRF(pre_master_secret, label, seed, TLSConst.MASTER_SECRET_LENGTH);
        System.arraycopy(bytes, 0, tls_context.master_secret, 0, TLSConst.MASTER_SECRET_LENGTH);
    }

    public static void calculate_keys(TLSParameters tls_context) {
        // NOTE: assuming send suite & recv suite will always be the same
        CipherSuite suite = CipherSuite.valueOf(tls_context.active_send_parameters.suite);
        int key_block_length = suite.hash_size * 2 + suite.key_size * 2 + suite.iv_size * 2;

        byte[] label = "key expansion".getBytes(StandardCharsets.UTF_8);
        byte[] seed = ByteUtils.concatenate(tls_context.server_random, tls_context.client_random);


        byte[] key_block = PRFUtils.PRF(tls_context.master_secret, label, seed, key_block_length);

        ByteDashboard bd = new ByteDashboard(key_block);

        ProtectionParameters send_parameters = tls_context.active_send_parameters;
        ProtectionParameters recv_parameters = tls_context.active_recv_parameters;
        send_parameters.mac_secret = bd.nextN(suite.hash_size);
        recv_parameters.mac_secret = bd.nextN(suite.hash_size);
        send_parameters.key = bd.nextN(suite.key_size);
        recv_parameters.key = bd.nextN(suite.key_size);
        send_parameters.iv = bd.nextN(suite.iv_size);
        recv_parameters.iv = bd.nextN(suite.iv_size);
    }

    public static TLSRecord receive_message(TLSConnection conn, ProtectionParameters parameters) throws IOException {
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

        byte[] content = tls_decrypt(header, data, parameters);

        ContentType content_type = ContentType.valueOf(type);
        ProtocolVersion version = new ProtocolVersion(major, minor);

        return new TLSRecord(content_type, version, content);
    }

    public static byte[] receive_plain_message(TLSConnection conn) throws IOException {
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        byte[] header = new byte[5];
        // STEP 1 - read off the TLS Record layer
        receive(conn, header, 0, 5);
        int length = (header[3] & 0xFF) << 8 | (header[4] & 0xFF);

        int accum_bytes = 0;
        byte[] data = new byte[length];
        while (accum_bytes < length) {
            int byte_read = receive(conn, data, accum_bytes, length - accum_bytes);
            accum_bytes += byte_read;
        }

        bao.write(header);
        bao.write(data);
        return bao.toByteArray();
    }

    public static byte[] tls_decrypt(byte[] header, byte[] data, ProtectionParameters parameters) {
        int data_length = data.length;

        CipherSuite active_suite = CipherSuite.valueOf(parameters.suite);
        int block_size = active_suite.block_size;
        int hash_size = active_suite.hash_size;

        byte[] decrypted_bytes;
        if (active_suite.bulk_decrypt != null) {
            OperationMode mode = active_suite.mode;
            switch (mode) {
                case ECB:
                    decrypted_bytes = ECBUtils.ecb_decrypt(data, parameters.key, active_suite.block_size, active_suite.bulk_decrypt);
                    break;
                case CBC:
                    decrypted_bytes = CBCUtils.cbc_decrypt(data, parameters.key, parameters.iv, active_suite.block_size, active_suite.bulk_decrypt);
                    break;
                default:
                    throw new RuntimeException("Unsupported mode: " + mode);
            }
        }
        else {
            // Do nothing, no bulk cipher algorithm chosen.
            decrypted_bytes = Arrays.copyOf(data, data_length);
        }

        int content_mac_length = data_length;
        byte[] content_mac_bytes;
        // Strip off padding
        if (block_size != 0) {
            int padding_length = decrypted_bytes[data_length - 1] + 1;
            content_mac_length -= padding_length;
        }
        content_mac_bytes = Arrays.copyOf(decrypted_bytes, content_mac_length);

        // Now, verify the MAC (if the active cipher suite includes one)
        if (active_suite.hash_algorithm != null) {
            int content_length = content_mac_length - hash_size;

            byte[] content = Arrays.copyOf(content_mac_bytes, content_length);
            byte[] received_hmac = Arrays.copyOfRange(content_mac_bytes, content_length, content_length + hash_size);

            // Allocate enough space for the 8-byte sequence number, the TLSPlainText
            // header, and the fragment (e.g. the decrypted message).
            byte[] mac_buffer = new byte[13 + content_length];

            byte[] seq_num_bytes = ByteUtils.fromLong(parameters.seq_num);
            System.arraycopy(seq_num_bytes, 0, mac_buffer, 0, 8);

            // Copy first three bytes of header; last two bytes reflected the
            // message length, with MAC attached. Since the MAC was computed
            // by the other side before it was attached (obviously), that MAC
            // was computed using the original length.
            System.arraycopy(header, 0, mac_buffer, 8, 3);
            mac_buffer[11] = (byte) (content_length >> 8 & 0xFF);
            mac_buffer[12] = (byte) (content_length & 0xFF);
            System.arraycopy(content, 0, mac_buffer, 13, content_length);

            byte[] hmac = Digest.hmac(parameters.key, mac_buffer, active_suite.hash_algorithm);

            if (!Arrays.equals(received_hmac, hmac)) {
                throw new RuntimeException("hmac not equals");
            }

            parameters.seq_num++;
            return content;
        }

        return content_mac_bytes;
    }

    public static byte[] tls_encrypt(ProtectionParameters parameters, ContentType type, byte[] content) {
        int content_length = content.length;
        int major = TLSConst.TLS_VERSION_MAJOR;
        int minor = TLSConst.TLS_VERSION_MINOR;

        CipherSuite active_suite = CipherSuite.valueOf(parameters.suite);
        int hash_size = active_suite.hash_size;
        int block_size = active_suite.block_size;


        // (1) HMAC
        byte[] mac = new byte[0];
        if (active_suite.hash_algorithm != null) {
            // Allocate enough space for the 8-byte sequence number, the 5-byte pseudo
            // header, and the content.
            byte[] mac_buffer = new byte[13 + content_length];

            // sequence number
            byte[] seq_num_bytes = ByteUtils.fromLong(parameters.seq_num);
            System.arraycopy(seq_num_bytes, 0, mac_buffer, 0, 8);

            // header
            mac_buffer[8] = (byte) type.val;
            mac_buffer[9] = (byte) major;
            mac_buffer[10] = (byte) minor;
            mac_buffer[11] = (byte) (content_length >> 8 & 0xFF);
            mac_buffer[12] = (byte) (content_length & 0xFF);

            // content
            System.arraycopy(content, 0, mac_buffer, 13, content_length);

            // hmac
            mac = Digest.hmac(parameters.mac_secret, mac_buffer, active_suite.hash_algorithm);
            if (mac.length != hash_size) {
                throw new RuntimeException("Hash Size Not Equals");
            }
        }

        parameters.seq_num++;

        // (2) Padding
        int padding_length = 0;
        if (block_size != 0) {
            padding_length = block_size - ((content_length + hash_size) % block_size);
        }

        // (3) prepare Input Data
        byte[] input = new byte[content_length + hash_size + padding_length];
        System.arraycopy(content, 0, input, 0, content_length);
        System.arraycopy(mac, 0, input, content_length, mac.length);
        for (int i = 0; i < padding_length; i++) {
            input[content_length + hash_size + i] = (byte) (padding_length - 1);
        }

        // (4) Encrypt
        if (active_suite.bulk_encrypt != null) {
            byte[] output;
            OperationMode mode = active_suite.mode;
            switch (mode) {
                case ECB:
                    output = ECBUtils.ecb_encrypt(input, parameters.key, active_suite.block_size, active_suite.bulk_encrypt);
                    break;
                case CBC:
                    output = CBCUtils.cbc_encrypt(input, parameters.key, parameters.iv, active_suite.block_size, active_suite.bulk_encrypt);
                    break;
                default:
                    throw new RuntimeException("Unknown Mode" + mode);
            }
            return output;
        }
        else {
            return input;
        }
    }



}
