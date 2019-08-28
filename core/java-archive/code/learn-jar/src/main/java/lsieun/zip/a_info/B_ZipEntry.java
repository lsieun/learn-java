package lsieun.zip.a_info;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class B_ZipEntry {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filepath = dir + "/target/hello.jar";

        try {
            ZipFile zipFile = new ZipFile(filepath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry zipEntry = entries.nextElement();

                String name = zipEntry.getName();
                boolean directory = zipEntry.isDirectory();
                int method = zipEntry.getMethod();
                long size = zipEntry.getSize();
                long compressedSize = zipEntry.getCompressedSize();

                System.out.println("name: " + name);
                System.out.println("isDirectory: " + directory);
                System.out.println("method: " + method);
                System.out.println("size: " + size);
                System.out.println("compressedSize: " + compressedSize);
                System.out.println("=================================");

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
