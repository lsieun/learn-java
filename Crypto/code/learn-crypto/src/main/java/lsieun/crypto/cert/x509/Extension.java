package lsieun.crypto.cert.x509;

import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class Extension {
    public final String oid;
    public final byte[] oid_bytes;
    public final boolean critical;
    public final byte[] data;

    public Extension(byte[] oid_bytes, boolean critical, byte[] data) {
        this.oid_bytes = oid_bytes;
        this.critical = critical;
        this.data = data;

        this.oid = HexUtils.format(oid_bytes, HexFormat.FORMAT_FF_SPACE_FF);
    }
}
