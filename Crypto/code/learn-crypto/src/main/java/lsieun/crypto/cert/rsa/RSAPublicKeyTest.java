package lsieun.crypto.cert.rsa;

import lsieun.crypto.cert.asn1.PEMUtils;

public class RSAPublicKeyTest {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/abc/rsa-public.key";
        byte[] bytes = PEMUtils.read(filepath);
        ASN1RSAKeyUtils.parse_public_key(bytes);
    }
}
