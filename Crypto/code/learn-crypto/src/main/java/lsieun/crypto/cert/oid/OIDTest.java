package lsieun.crypto.cert.oid;

import lsieun.utils.HexFormat;
import lsieun.utils.HexUtils;

public class OIDTest {
    public static void main(String[] args) {
        String hex_str = "2A 86 48 CE 3D 04 03 02";
        byte[] bytes = HexUtils.parse(hex_str, HexFormat.FORMAT_FF_SPACE_FF);
        String result = OIDUtils.format(bytes);
        System.out.println(result);

        String oid_str = "2.5.29.32";
        byte[] bytes2 = OIDUtils.parse(oid_str);
        System.out.println(HexUtils.format(bytes2, HexFormat.FORMAT_FF_SPACE_FF));
    }


}
