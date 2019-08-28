package lsieun.jar.b_manifest;

import java.io.IOException;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ReadManifest {
    public static void main(String[] args) {
        try {
            JarFile jarFile = new JarFile("target/hello.jar");
            Manifest mf = jarFile.getManifest();
            Attributes attrs = mf.getMainAttributes();

            // Get One
            String mainClassName = attrs.getValue(Attributes.Name.MAIN_CLASS);
            System.out.println("Main-Class: " + mainClassName);
            System.out.println("============================");

            // Get All
            Iterator it = attrs.entrySet().iterator();
            while(it.hasNext()) {
                Object entry = it.next();
                System.out.println(entry);
            }
            System.out.println("============================");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
