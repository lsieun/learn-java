package lsieun.crypto.cert.x509.extensions;

import lsieun.crypto.cert.x509.Extension;

public class BasicConstraintsExtension extends Extension {
    public BasicConstraintsExtension(byte[] oid_bytes, boolean critical, byte[] data) {
        super(oid_bytes, critical, data);
    }
}
