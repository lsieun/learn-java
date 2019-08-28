package lsieun.zip.b_read_entry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class A_ReadOneEntry {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filepath = dir + "/target/hello.jar";

        try {
            ZipFile zipFile = new ZipFile(filepath);
            ZipEntry entry = zipFile.getEntry("text/readme.txt");
            System.out.println(entry);

            try (InputStream in = zipFile.getInputStream(entry);
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            ) {
                String line = null;
                while ((line = bufferedReader.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
