package lsieun.java;

import java.io.File;

public class JavaIOTmpDir {
    public static void main(String[] args) {
        String homeDir = System.getProperty("java.io.tmpdir");
        System.out.println(homeDir);
        System.out.println(new File(homeDir).toURI());
    }
}
