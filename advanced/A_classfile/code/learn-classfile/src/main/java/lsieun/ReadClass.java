package lsieun;

import lsieun.utils.ByteCodeUtils;
import lsieun.utils.ByteUtils;
import lsieun.utils.FileUtils;
import lsieun.utils.HexUtils;
import lsieun.utils.JarUtils;

public class ReadClass {

    private static final boolean READ_JAR = false;

    public static void main(String[] args) {

        byte[] bytes = null;
        if(READ_JAR) {
            String jarPath = "/usr/local/jdk8/jre/lib/rt.jar";
            String entryName = "java/lang/Object.class";
            bytes = JarUtils.readBytes(jarPath, entryName);
        }
        else {
            String dir = ReadClass.class.getResource(".").getPath();
            String filepath = dir + "example/Example_08_Fields_0B_AccessFlags.class";
            bytes = FileUtils.readBytes(filepath);
        }

        String hexCodeStr = ByteUtils.toHex(bytes).toUpperCase();
        System.out.println(hexCodeStr);
        System.out.println(HexUtils.format(hexCodeStr));

        ByteCodeUtils.deCode(hexCodeStr);
    }

}
