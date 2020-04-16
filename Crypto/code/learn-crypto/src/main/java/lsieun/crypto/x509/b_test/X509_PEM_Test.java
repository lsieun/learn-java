package lsieun.crypto.x509.b_test;

import lsieun.crypto.x509.SignedX509Certificate;
import lsieun.crypto.x509.X509Utils;
import lsieun.utils.FileUtils;

import java.util.Base64;
import java.util.List;

public class X509_PEM_Test {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/google.crt";
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
        SignedX509Certificate signedX509Certificate = X509Utils.parse_x509_certificate(decode_bytes);
    }
}
