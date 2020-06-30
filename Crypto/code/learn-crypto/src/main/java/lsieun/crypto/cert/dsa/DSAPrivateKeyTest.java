package lsieun.crypto.cert.dsa;

import lsieun.crypto.cert.asn1.PEMUtils;
import lsieun.utils.FileUtils;

public class DSAPrivateKeyTest {
    public static void main(String[] args) {
        String filepath = FileUtils.getFilePath("cert/dsa/dsa.key");
        byte[] bytes = PEMUtils.read(filepath);
        ASN1DSAUtils.parse_private_key(bytes);
    }
}
