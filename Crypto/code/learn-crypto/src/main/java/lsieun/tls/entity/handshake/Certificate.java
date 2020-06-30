package lsieun.tls.entity.handshake;

import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Certificate extends Handshake {

    public List<byte[]> certificates;

    public Certificate(List<byte[]> certificates) {
        super(HandshakeType.CERTIFICATE);
        this.certificates = certificates;
    }

    @Override
    public byte[] getData() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            int total_length = 0;
            for (byte[] bytes : certificates) {
                total_length += bytes.length;
            }

            bao.write((total_length >> 16) & 0xFF); // total length 24-bits(!)
            bao.write((total_length >> 8) & 0xFF);
            bao.write(total_length & 0xFF);

            for (byte[] bytes : certificates) {
                int length = bytes.length;

                bao.write((length >> 16) & 0xFF); // length 24-bits(!)
                bao.write((length >> 8) & 0xFF);
                bao.write(length & 0xFF);

                bao.write(bytes);
            }

            return bao.toByteArray();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static Certificate fromBytes(byte[] data) {
        try {
            ByteDashboard bd = new ByteDashboard(data);

            int certificates_length = ByteUtils.toInt(bd.nextN(3));

            List<byte[]> list = new ArrayList<>();
            while (bd.hasNext()) {
                int length = ByteUtils.toInt(bd.nextN(3));
                byte[] cert_bytes = bd.nextN(length);
                list.add(cert_bytes);
            }

            return new Certificate(list);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

}
