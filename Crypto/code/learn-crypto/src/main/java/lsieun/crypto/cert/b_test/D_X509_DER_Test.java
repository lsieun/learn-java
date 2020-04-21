package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.x509.SignedX509Certificate;
import lsieun.crypto.cert.x509.X509Utils;
import lsieun.utils.FileUtils;

public class D_X509_DER_Test {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/cert.der";
        byte[] bytes = FileUtils.readBytes(filepath);
        SignedX509Certificate signedX509Certificate = X509Utils.parse_x509_certificate(bytes);
        X509Utils.display_x509_certificate(signedX509Certificate);
    }
}
