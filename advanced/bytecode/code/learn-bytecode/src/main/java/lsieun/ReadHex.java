package lsieun;

import lsieun.utils.HexUtils;

public class ReadHex {
    public static void main(String[] args) {
        String hexCode="E4B";
        String str = HexUtils.toUtf8(hexCode);
        System.out.println(str);
    }
}

