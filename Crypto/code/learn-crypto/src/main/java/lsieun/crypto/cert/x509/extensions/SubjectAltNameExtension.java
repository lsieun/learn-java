package lsieun.crypto.cert.x509.extensions;

import lsieun.crypto.cert.x509.Extension;
import lsieun.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class SubjectAltNameExtension extends Extension {
    public final List<Pair<String, String>> values = new ArrayList<>();

    public SubjectAltNameExtension(byte[] oid_bytes, boolean critical, byte[] data) {
        super(oid_bytes, critical, data);
    }
}
