package lsieun.crypto.x509.oid;

import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class OIDTest {
    public static void main(String[] args) {
        String hex_str = "2A 86 48 86 F7 0D 01 01 01";
        byte[] bytes = HexUtils.parse(hex_str, HexFormat.FORMAT_FF_FF);
        String result = OIDUtils.format(bytes);
        System.out.println(result);

        String oid_str = "1.2.840.113549.1.1.1";
        byte[] bytes2 = OIDUtils.parse(oid_str);
        System.out.println(HexUtils.format(bytes2, HexFormat.FORMAT_FF_FF));
    }


}
