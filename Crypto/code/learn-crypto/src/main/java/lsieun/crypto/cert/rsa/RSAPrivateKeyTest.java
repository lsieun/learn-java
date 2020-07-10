package lsieun.crypto.cert.rsa;

import lsieun.crypto.cert.asn1.PEMUtils;

public class RSAPrivateKeyTest {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/abc/rsa.key";
        byte[] bytes = PEMUtils.read(filepath);
        RSAKeyUtils.parse_private_key(bytes);
    }
}
