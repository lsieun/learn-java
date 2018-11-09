package lsieun.test;

import lsieun.javaagent.util.IOUtil;

public class Test03 {
    public static void main(String[] args) {
        String filePath = "/home/liusen/workdir/dummy/tmp/my.jpg";
        String result = IOUtil.backupFile(filePath);
        System.out.println(result);
    }
}
