package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.asn1.PEMUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class A_PEM_HEX {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/tmp/cert.pem";
        byte[] decode_bytes = PEMUtils.read(filepath);
        String result = HexUtils.format(decode_bytes, HexFormat.FORMAT_FF_SPACE_FF_16);
        System.out.println(result);
    }
}
