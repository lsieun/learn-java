package lsieun.jar;

import java.io.IOException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class GetAllFiles {
    public static void main(String[] args) {
        try {
            JarFile jarFile = new JarFile("target/hello.jar");
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                System.out.println(entry.getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
