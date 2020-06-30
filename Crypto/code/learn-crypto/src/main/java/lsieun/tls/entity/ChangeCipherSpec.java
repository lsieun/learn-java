package lsieun.tls.entity;

import lsieun.tls.cst.TLSConst;
import lsieun.utils.ByteDashboard;
import lsieun.utils.ByteUtils;

import java.io.ByteArrayOutputStream;

public class ChangeCipherSpec {

    public static byte[] toBytes() {
        try {
            ByteArrayOutputStream bao = new ByteArrayOutputStream();

            bao.write(ContentType.CONTENT_CHANGE_CIPHER_SPEC.val);
            bao.write(TLSConst.TLS_VERSION_MAJOR); // major
            bao.write(TLSConst.TLS_VERSION_MINOR); // minor
            bao.write(1); // length
            bao.write(1); // value

            return bao.toByteArray();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void validate(byte[] bytes) {
        try {
            if (bytes.length != 6) {
                throw new RuntimeException("bytes.length is wrong: " + bytes.length);
            }

            ByteDashboard bd = new ByteDashboard(bytes);
            byte type = bd.next();
            if (type != ContentType.CONTENT_CHANGE_CIPHER_SPEC.val) {
                throw new RuntimeException("type is wrong: " + type);
            }

            byte major = bd.next();
            byte minor = bd.next();
            if (major != 3) {
                throw new RuntimeException("major is wrong: " + major);
            }
            if (minor < 0 || minor > 3) {
                throw new RuntimeException("minor is wrong: " + minor);
            }

            int length = ByteUtils.toInt(bd.nextN(2));
            if (length != 1) {
                throw new RuntimeException("length is wrong: " + length);
            }

            byte val = bd.next();
            if (val != 1) {
                throw new RuntimeException("val is wrong: " + val);
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public static ChangeCipherSpec fromBytes(byte[] bytes) {
        validate(bytes);
        return new ChangeCipherSpec();
    }

}
