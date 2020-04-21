package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.PEMUtils;
import lsieun.crypto.cert.asn1.ASN1Utils;

@SuppressWarnings("Duplicates")
public class B_PEMTest {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/fd.csr";
        byte[] decode_bytes = PEMUtils.read(filepath);

        ASN1Utils.asn1show(decode_bytes);
    }
}
