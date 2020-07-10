package lsieun.tls.utils;

import lsieun.crypto.cert.x509.SignedCertificate;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.tls.cipher.CipherSuiteIdentifier;
import lsieun.tls.entity.ContentType;
import lsieun.tls.entity.alert.AlertDescription;
import lsieun.tls.entity.alert.AlertLevel;
import lsieun.tls.entity.handshake.HandshakeType;
import lsieun.utils.*;

import java.util.Date;
import java.util.Formatter;

public class DisplayUtils {
    public static void display_record(byte[] bytes) {
        System.out.println(HexUtils.format(bytes, " ", 32));

        ByteDashboard bd = new ByteDashboard(bytes);
        byte[] content_type_bytes = bd.nextN(1);
        byte[] version_bytes = bd.nextN(2);
        byte[] length_bytes = bd.nextN(2);

        ContentType content_type = ContentType.valueOf(ByteUtils.toInt(content_type_bytes));
        int length = ByteUtils.toInt(length_bytes);

        String content_type_hex = HexUtils.format(content_type_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String version_hex = HexUtils.format(version_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String length_hex = HexUtils.format(length_bytes, HexFormat.FORMAT_FF_SPACE_FF);

        StringBuilder sb = new StringBuilder();
        Formatter fm = new Formatter(sb);

        fm.format("Content Type: %s (%s)%n", content_type, content_type_hex);
        fm.format("Version: %d.%d (%s)%n", version_bytes[0], version_bytes[1], version_hex);
        fm.format("Length: %d (%s)%n", length, length_hex);

        switch (content_type) {
            case CONTENT_CHANGE_CIPHER_SPEC:
                process_content_change_cipher_spec(bd, fm);
                break;
            case CONTENT_ALERT:
                process_content_alert(bd, fm);
                break;
            case CONTENT_HANDSHAKE:
                process_content_handshake(bd, fm);
                break;
            case CONTENT_APPLICATION_DATA:
                process_content_application_data(bd, fm);
                break;
            default:
                throw new RuntimeException("Unknown Content Type: " + content_type_hex);
        }

        int remaining = bd.remaining();
        byte[] remaining_bytes = bd.nextN(remaining);
        String remaining_hex = HexUtils.format(remaining_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        fm.format("Remaining Bytes: %s%n%n", remaining_hex);
        System.out.println(sb.toString());
    }

    public static void process_content_change_cipher_spec(ByteDashboard bd, Formatter fm) {

    }

    public static void process_content_alert(ByteDashboard bd, Formatter fm) {
        byte[] alert_level_bytes = bd.nextN(1);
        byte[] alert_description_bytes = bd.nextN(1);

        String alert_level_hex = HexUtils.format(alert_level_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String alert_description_hex = HexUtils.format(alert_description_bytes, HexFormat.FORMAT_FF_SPACE_FF);

        AlertLevel alert_level = AlertLevel.valueOf(ByteUtils.toInt(alert_level_bytes));
        AlertDescription alert_description = AlertDescription.valueOf(ByteUtils.toInt(alert_description_bytes));

        fm.format("Alert Level: %s (%s)%n", alert_level, alert_level_hex);
        fm.format("Alert Level: %s (%s)%n", alert_description, alert_description_hex);
    }

    public static void process_content_handshake(ByteDashboard bd, Formatter fm) {
        byte[] handshake_type_bytes = bd.nextN(1);
        byte[] length_bytes = bd.nextN(3);

        HandshakeType handshake_type = HandshakeType.valueOf(ByteUtils.toInt(handshake_type_bytes));
        int length = ByteUtils.toInt(length_bytes);

        String handshake_type_hex = HexUtils.format(handshake_type_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String length_hex = HexUtils.format(length_bytes, HexFormat.FORMAT_FF_SPACE_FF);

        fm.format("Handshake Type: %s (%s)%n", handshake_type, handshake_type_hex);
        fm.format("Length: %d (%s)%n", length, length_hex);

        switch (handshake_type) {
            case CLIENT_HELLO:
                process_client_hello(bd, fm);
                break;
            case SERVER_HELLO:
                process_server_hello(bd, fm);
                break;
            case CERTIFICATE:
                process_certificate(bd, fm);
                break;
            case SERVER_KEY_EXCHANGE:
                process_server_key_exchange(bd, fm);
                break;
            case SERVER_HELLO_DONE:
                process_server_hello_done(bd, fm);
                break;
            default:
                throw new RuntimeException("Unsupported handshake type: " + handshake_type);
        }

    }

    // region handshake
    public static void process_client_hello(ByteDashboard bd, Formatter fm) {
        byte[] version_bytes = bd.nextN(2);
        byte[] gmt_unix_time_bytes = bd.nextN(4);
        long gmt_unix_time = ByteUtils.toInt(gmt_unix_time_bytes);
        long timestamp = gmt_unix_time * 1000;
        Date date = new Date(timestamp);
        byte[] random_bytes = bd.nextN(28);
        byte[] session_id_length_bytes = bd.nextN(1);
        int session_id_length = ByteUtils.toInt(session_id_length_bytes);
        byte[] session_id_bytes = bd.nextN(session_id_length);
        byte[] cipher_suite_length_bytes = bd.nextN(2);
        int cipher_suite_length = ByteUtils.toInt(cipher_suite_length_bytes);


        String version_hex = HexUtils.format(version_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String gmt_unix_time_hex = HexUtils.format(gmt_unix_time_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String random_hex = HexUtils.format(random_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String session_id_length_hex = HexUtils.format(session_id_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String session_id_hex = HexUtils.format(session_id_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String cipher_suite_length_hex = HexUtils.format(cipher_suite_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);

        fm.format("Version: %d.%d (%s)%n", version_bytes[0], version_bytes[1], version_hex);
        fm.format("GMT Unix Time: %s (%s)%n", DateUtils.format(date), gmt_unix_time_hex);
        fm.format("Random Bytes: %s%n", random_hex);
        fm.format("Session ID Length: %s (%s)%n", session_id_length, session_id_length_hex);
        fm.format("Session ID: %s%n", session_id_hex);
        fm.format("Cipher Suites Length: %s (%s)%n", cipher_suite_length, cipher_suite_length_hex);

        int cipher_suite_count = cipher_suite_length / 2;
        for (int i = 0; i < cipher_suite_count; i++) {
            byte[] cipher_suite_id_bytes = bd.nextN(2);
            int cipher_suite_value = ByteUtils.toInt(cipher_suite_id_bytes);
            String cipher_suite_id_hex = HexUtils.format(cipher_suite_id_bytes, HexFormat.FORMAT_FF_SPACE_FF);
            CipherSuiteIdentifier cipher_suite_id = CipherSuiteIdentifier.valueOf(cipher_suite_value);
            fm.format("    Cipher Suites: %s (%s)%n", cipher_suite_id, cipher_suite_id_hex);
        }

        byte[] compression_method_length_bytes = bd.nextN(1);
        String compression_method_length_hex = HexUtils.format(compression_method_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        int compression_method_length = ByteUtils.toInt(compression_method_length_bytes);
        fm.format("Compression Methods Length: %s (%s)%n", compression_method_length, compression_method_length_hex);

        for (int i = 0; i < compression_method_length; i++) {
            byte[] compression_method_bytes = bd.nextN(1);
            int compression_method = ByteUtils.toInt(compression_method_bytes);
            String compression_method_hex = HexUtils.format(compression_method_bytes, HexFormat.FORMAT_FF_SPACE_FF);
            fm.format("    Compression Method: %s (%s)%n", compression_method, compression_method_hex);
        }


    }

    public static void process_server_hello(ByteDashboard bd, Formatter fm) {
        byte[] version_bytes = bd.nextN(2);
        byte[] gmt_unix_time_bytes = bd.nextN(4);
        long gmt_unix_time = ByteUtils.toInt(gmt_unix_time_bytes);
        long timestamp = gmt_unix_time * 1000;
        Date date = new Date(timestamp);
        byte[] random_bytes = bd.nextN(28);
        byte[] session_id_length_bytes = bd.nextN(1);
        int session_id_length = ByteUtils.toInt(session_id_length_bytes);
        byte[] session_id_bytes = bd.nextN(session_id_length);
        byte[] cipher_suite_bytes = bd.nextN(2);
        CipherSuiteIdentifier cipher_suite_id = CipherSuiteIdentifier.valueOf(ByteUtils.toInt(cipher_suite_bytes));
        byte[] compression_method_bytes = bd.nextN(1);
        int compression_method = ByteUtils.toInt(compression_method_bytes);

        String version_hex = HexUtils.format(version_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String gmt_unix_time_hex = HexUtils.format(gmt_unix_time_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String random_hex = HexUtils.format(random_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String session_id_length_hex = HexUtils.format(session_id_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String session_id_hex = HexUtils.format(session_id_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String cipher_suite_hex = HexUtils.format(cipher_suite_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String compression_method_hex = HexUtils.format(compression_method_bytes, HexFormat.FORMAT_FF_SPACE_FF);

        fm.format("Version: %d.%d (%s)%n", version_bytes[0], version_bytes[1], version_hex);
        fm.format("GMT Unix Time: %s (%s)%n", DateUtils.format(date), gmt_unix_time_hex);
        fm.format("Random Bytes: %s%n", random_hex);
        fm.format("Session ID Length: %s (%s)%n", session_id_length, session_id_length_hex);
        fm.format("Session ID: %s%n", session_id_hex);
        fm.format("Cipher Suite: %s (%s)%n", cipher_suite_id, cipher_suite_hex);
        fm.format("Compression Method: %s (%s)%n", compression_method, compression_method_hex);
    }

    public static void process_certificate(ByteDashboard bd, Formatter fm) {
        byte[] certificates_length_bytes = bd.nextN(3);
        int certificates_length = ByteUtils.toInt(certificates_length_bytes);

        String certificates_length_hex = HexUtils.format(certificates_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        fm.format("Certificates Length: %s (%s)", certificates_length, certificates_length_hex);

        byte[] data = bd.nextN(certificates_length);
        parse_multi_certificates(data, fm);
    }

    public static void parse_multi_certificates(byte[] data, Formatter fm) {
        ByteDashboard bd = new ByteDashboard(data);
        while (bd.hasNext()) {
            byte[] certificate_length_bytes = bd.nextN(3);
            int certificate_length = ByteUtils.toInt(certificate_length_bytes);

            String certificate_length_hex = HexUtils.format(certificate_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
            fm.format("Certificate Length: %s (%s)%n", certificate_length, certificate_length_hex);

            byte[] cert_bytes = bd.nextN(certificate_length);
            SignedCertificate signed_certificate = X509Utils.parse_x509_certificate(cert_bytes);
            fm.format("Certificate: %s%n", signed_certificate.tbs_certificate.subject.CommonName);
        }
    }

    public static void process_server_key_exchange(ByteDashboard bd, Formatter fm) {
        parse_ecdhe(bd, fm);
    }

    public static void parse_ecdhe(ByteDashboard bd, Formatter fm) {
        byte[] curve_type_bytes = bd.nextN(1);
        byte[] named_curve_bytes = bd.nextN(2);
        byte[] public_key_length_bytes = bd.nextN(1);
        int public_key_length = ByteUtils.toInt(public_key_length_bytes);
        byte[] public_key_bytes = bd.nextN(public_key_length);
        byte[] hash_algorithm_bytes = bd.nextN(1);
        byte[] signature_algorithm_bytes = bd.nextN(1);
        byte[] signature_length_bytes = bd.nextN(2);
        int signature_length = ByteUtils.toInt(signature_length_bytes);
        byte[] signature_bytes = bd.nextN(signature_length);

        String curve_type_hex = HexUtils.format(curve_type_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String named_curve_hex = HexUtils.format(named_curve_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String public_key_length_hex = HexUtils.format(public_key_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String public_key_hex = HexUtils.format(public_key_bytes, " ", 33);
        String hash_algorithm_hex = HexUtils.format(hash_algorithm_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String signature_algorithm_hex = HexUtils.format(signature_algorithm_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String signature_length_hex = HexUtils.format(signature_length_bytes, HexFormat.FORMAT_FF_SPACE_FF);
        String signature_hex = HexUtils.format(signature_bytes, " ", 32);


        fm.format("Curve Type: named_curve (%s)%n", curve_type_hex);
        fm.format("Named Curve: secp256r1 (%s)%n", named_curve_hex);
        fm.format("Pubkey Length: %d (%s)%n", public_key_length, public_key_length_hex);
        fm.format("Pubkey: %s%n", public_key_hex);
        fm.format("Signature Hash Algorithm Hash: SHA256 (%s)%n", hash_algorithm_hex);
        fm.format("Signature Hash Algorithm Signature: RSA (%s)%n", signature_algorithm_hex);
        fm.format("Signature Length: %d (%s)%n", signature_length, signature_length_hex);
        fm.format("Signature: %s%n", signature_hex);
    }

    public static void process_server_hello_done(ByteDashboard bd, Formatter fm) {

    }

    // endregion

    public static void process_content_application_data(ByteDashboard bd, Formatter fm) {

    }
}
