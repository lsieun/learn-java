package lsieun;

import lsieun.utils.ByteCodeUtils;
import lsieun.utils.IOUtils;

public class ReadClass {
    public static void main(String[] args) {
        String dir = ReadClass.class.getResource(".").getPath();
        //String filepath = dir + "example/HelloWorld.class";
        String filepath = dir + "utils/ByteCodeUtils.class";
        String hexCodeStr = IOUtils.readFormatHex(filepath);
        System.out.println(hexCodeStr);

        hexCodeStr = IOUtils.readHex(filepath).toUpperCase();
        ByteCodeUtils.deCode(hexCodeStr);
    }

}
