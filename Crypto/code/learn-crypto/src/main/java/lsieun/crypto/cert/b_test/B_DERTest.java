package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.asn1.ASN1Utils;
import lsieun.utils.FileUtils;

public class B_DERTest {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/cert.der";
        byte[] bytes = FileUtils.readBytes(filepath);

        ASN1Utils.asn1show(bytes);
    }
}
