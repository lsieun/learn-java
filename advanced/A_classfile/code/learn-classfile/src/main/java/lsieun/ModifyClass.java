package lsieun;

import lsieun.utils.IOUtils;

public class ModifyClass {
    public static void main(String[] args) {
        String filepath = "/home/liusen/Workspace/dummy/lab/HelloWorld.class";
        byte[] bytes = IOUtils.readBytes(filepath);

        bytes[7] = 53;
        IOUtils.writeBytes(filepath, bytes);
    }
}
