package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.PEMUtils;
import lsieun.crypto.cert.x509.SignedX509Certificate;
import lsieun.crypto.cert.x509.X509Utils;

@SuppressWarnings("Duplicates")
public class D_X509_PEM_Test {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/zhihu.pem";
        byte[] bytes = PEMUtils.read(filepath);
        SignedX509Certificate signedX509Certificate = X509Utils.parse_x509_certificate(bytes);
        X509Utils.display_x509_certificate(signedX509Certificate);
    }
}
