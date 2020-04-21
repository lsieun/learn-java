package lsieun.crypto.cert.b_test;

import lsieun.crypto.cert.oid.OIDUtils;
import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class C_OIDTest {
    public static void main(String[] args) {
        String hex_str = "2A 86 48 86 F7 0D 01 09 01";
        byte[] bytes = HexUtils.parse(hex_str, HexFormat.FORMAT_FF_SPACE_FF);
        String result = OIDUtils.format(bytes);
        System.out.println(result);

        String oid_str = "2.5.29.15";
        byte[] bytes2 = OIDUtils.parse(oid_str);
        System.out.println(HexUtils.format(bytes2, HexFormat.FORMAT_FF_SPACE_FF));
    }


}
