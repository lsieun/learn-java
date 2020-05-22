package lsieun.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Handshake {
    public final HandshakeType msg_type;
    public final int length; // 24 bits(!)
    public final byte[] content;

    public Handshake(HandshakeType msg_type, byte[] content) {
        this.msg_type = msg_type;
        this.length = content.length;
        this.content = content;
    }

    public byte[] toBytes() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write(msg_type.val);
            bao.write((length >> 16) & 0xFF);
            bao.write((length >> 8) & 0xFF);
            bao.write(length & 0xFF);
            bao.write(content);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Handshake.toBytes() Exception");
        }
    }
}
