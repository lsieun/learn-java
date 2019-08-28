package lsieun.zip.a_info;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class A_ZipFile_02_AllEntries {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filepath = dir + "/target/hello.jar";

        try {
            ZipFile zipFile = new ZipFile(filepath);
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                long size = entry.getSize();
                long compressedSize = entry.getCompressedSize();
                if(entry.isDirectory()){
                    System.out.print("dir  : " + entry.getName());
                } else {
                    System.out.print("file : " + entry.getName());
                }
                System.out.println("\tSize = " + size + " Compressed Size: " + compressedSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
