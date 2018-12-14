package lsieun.java;

import java.io.File;

public class JavaHomeDir {
    public static void main(String[] args) {
        String homeDir = System.getProperty("java.home");
        System.out.println(homeDir);
        System.out.println(new File(homeDir).toURI());
    }
}
