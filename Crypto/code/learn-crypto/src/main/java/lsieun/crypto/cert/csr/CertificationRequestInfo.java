package lsieun.crypto.cert.csr;

import lsieun.crypto.cert.x509.Name;
import lsieun.crypto.cert.x509.PublicKeyInfo;
import lsieun.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class CertificationRequestInfo {
    public final int version;
//    public final List<Pair<String, String>> subject = new ArrayList<>();
    public final Name subject;
    public final PublicKeyInfo subject_public_key;
    public final byte[] data;

    public CertificationRequestInfo(int version, Name subject, PublicKeyInfo subject_public_key, byte[] data) {
        this.version = version;
        this.subject = subject;
        this.subject_public_key = subject_public_key;
        this.data = data;
    }
}
