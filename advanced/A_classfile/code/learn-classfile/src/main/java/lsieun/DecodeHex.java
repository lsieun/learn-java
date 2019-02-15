package lsieun;

import lsieun.utils.HexUtils;

public class DecodeHex {
    public static void main(String[] args) {
        String str = HexUtils.toUtf8("6A6176612F6C616E672F4F626A656374");
        System.out.println(str);
        System.out.println(HexUtils.toFloat("4048F5C3"));
        System.out.println(HexUtils.toDouble("3FE70A3D70A3D70A"));
    }
}
