package lsieun.tls.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class TLSRecord {
    public final ContentType content_type;
    public final ProtocolVersion version;
    public final byte[] content;

    public TLSRecord(ContentType content_type, ProtocolVersion version, byte[] content) {
        this.content_type = content_type;
        this.version = version;
        this.content = content;
    }

    public byte[] toBytes() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();
            bao.write(content_type.val);
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
