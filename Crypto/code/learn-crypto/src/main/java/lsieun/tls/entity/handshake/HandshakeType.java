package lsieun.tls.entity.handshake;

import java.util.Arrays;

public enum HandshakeType {
    HELLO_REQUEST(0),
    CLIENT_HELLO(1),
    SERVER_HELLO(2),
    CERTIFICATE(11),
    SERVER_KEY_EXCHANGE(12),
    CERTIFICATE_REQUEST(13),
    SERVER_HELLO_DONE(14),
    CERTIFICATE_VERIFY(15),
    CLIENT_KEY_EXCHANGE(16),
    FINISHED(20);

    public final int val;

    HandshakeType(int val) {
        this.val = val;
    }

    public static HandshakeType valueOf(int val) {
        return Arrays.stream(values()).filter(item -> item.val == val).findFirst().get();
    }

    public static void main(String[] args) {
        HandshakeType type = valueOf(20);
        System.out.println(type);
    }
}
