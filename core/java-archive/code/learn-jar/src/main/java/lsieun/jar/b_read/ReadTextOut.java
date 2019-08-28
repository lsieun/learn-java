package lsieun.jar.b_read;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ReadTextOut {
    public static void main(String[] args) {
        try {
            JarFile jarFile = new JarFile("target/hello.jar");
            JarEntry entry = jarFile.getJarEntry("text/readme.txt");
            InputStream in = jarFile.getInputStream(entry);

            InputStreamReader reader = new InputStreamReader(in);
            BufferedReader br = new BufferedReader(reader);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            reader.close();
            in.close();

            jarFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
