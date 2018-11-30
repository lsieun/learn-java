package lsieun.zip;

import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class GetAllInZip {
    public static void main(String[] args) {
        try {
            ZipFile zipFile = new ZipFile("/home/liusen/workdir/dummy/test.zip");
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                long size = entry.getSize();
                long compressedSize = entry.getCompressedSize();
                if(entry.isDirectory()){
                    System.out.println("dir  : " + entry.getName());
                } else {
                    System.out.println("file : " + entry.getName());
                }
                System.out.println("\tSize = " + size + " Compressed Size: " + compressedSize);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
