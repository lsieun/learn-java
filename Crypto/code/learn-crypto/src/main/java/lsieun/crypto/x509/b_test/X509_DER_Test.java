package lsieun.crypto.x509.b_test;

import lsieun.crypto.x509.SignedX509Certificate;
import lsieun.crypto.x509.X509Utils;
import lsieun.utils.FileUtils;

public class X509_DER_Test {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/cert.der";
        byte[] bytes = FileUtils.readBytes(filepath);
        SignedX509Certificate signedX509Certificate = X509Utils.parse_x509_certificate(bytes);

        int val = Integer.parseInt("10011100111000", 2);
        System.out.println(val);
    }
}
