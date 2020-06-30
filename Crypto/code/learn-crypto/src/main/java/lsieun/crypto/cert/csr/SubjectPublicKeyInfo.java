package lsieun.crypto.cert.csr;

import lsieun.crypto.cert.cst.AlgorithmIdentifier;
import lsieun.utils.Pair;

import java.util.ArrayList;
import java.util.List;

public class SubjectPublicKeyInfo {
    public final AlgorithmIdentifier algorithm;
    public final List<Pair<String, String>> parameters = new ArrayList<>();

    public SubjectPublicKeyInfo(AlgorithmIdentifier algorithm) {
        this.algorithm = algorithm;
    }
}
