package lsieun.tls;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TLSPlaintext {
    public final ContentType type;
    public final ProtocolVersion version;
    public final byte[] content;

    public TLSPlaintext(ContentType type, ProtocolVersion version, byte[] content) {
        this.type = type;
        this.version = version;
        this.content = content;
    }

    public byte[] toBytes() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write(type.val);
            bao.write(version.major);
            bao.write(version.minor);

            int length = content.length;
            bao.write((length >> 8) & 0xFF);
            bao.write(length & 0xFF);
            bao.write(content);
            return bao.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("TLSPlaintext.toBytes() Exception");
        }
    }
}
