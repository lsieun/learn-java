package lsieun.crypto.x509.b_test;

import lsieun.crypto.x509.asn1.ASN1Struct;
import lsieun.crypto.x509.asn1.ASN1Utils;
import lsieun.utils.FileUtils;

import java.util.Base64;
import java.util.List;

public class PEMTest {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/GlobalSignDomainValidationCA-SHA256-G2.crt";
        List<String> lines = FileUtils.readLines(filepath);

        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            if (line.startsWith("-----BEGIN")) continue;
            if (line.startsWith("-----END")) continue;
            if (line.contains(":")) continue;
            if ("".equalsIgnoreCase(line.trim())) continue;
            sb.append(line);
        }

        String base64_str = sb.toString();
        byte[] decode_bytes = Base64.getDecoder().decode(base64_str);
        List<ASN1Struct> list = ASN1Utils.asn1parse(decode_bytes);
        ASN1Utils.asn1show(list, 0);
    }
}
