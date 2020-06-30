package lsieun.tls.entity.handshake;

import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public abstract class Handshake {
    public final HandshakeType hand_shake_type;

    public Handshake(HandshakeType hand_shake_type) {
        this.hand_shake_type = hand_shake_type;
    }

    public byte[] toBytes() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write(hand_shake_type.val); // handshake type

            byte[] data = getData();
            int length = data.length;

            bao.write((length >> 16) & 0xFF); // length 24-bits(!)
            bao.write((length >> 8) & 0xFF);
            bao.write(length & 0xFF);

            bao.write(data);
            return bao.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public abstract byte[] getData();

    public static Handshake parse(byte[] bytes) {
        ByteDashboard bd = new ByteDashboard(bytes);

        HandshakeType hand_shake_type = HandshakeType.valueOf(bd.next());
        int length = ByteUtils.toInt(bd.nextN(3));
        byte[] data = bd.nextN(length);

        switch (hand_shake_type) {
            case CLIENT_HELLO:
                return ClientHello.fromBytes(data);
            case SERVER_HELLO:
                return ServerHello.fromBytes(data);
            case CERTIFICATE:
                return Certificate.fromBytes(data);
            default:
                throw new RuntimeException("Unsupported Handshake Type: " + hand_shake_type);
        }

    }

}
