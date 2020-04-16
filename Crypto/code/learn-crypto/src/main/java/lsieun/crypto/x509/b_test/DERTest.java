package lsieun.crypto.x509.b_test;

import lsieun.crypto.x509.asn1.ASN1Struct;
import lsieun.crypto.x509.asn1.ASN1Utils;
import lsieun.utils.FileUtils;

import java.util.List;

public class DERTest {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/cert.der";
        byte[] bytes = FileUtils.readBytes(filepath);
        System.out.println(bytes.length);


        List<ASN1Struct> list = ASN1Utils.asn1parse(bytes);
        System.out.println(list.size());
        ASN1Utils.asn1show(list, 0);
    }
}
