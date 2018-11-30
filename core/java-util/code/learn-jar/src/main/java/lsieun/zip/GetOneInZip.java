package lsieun.zip;

import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class GetOneInZip {
    public static void main(String[] args) {
        try {
            ZipFile zipFile = new ZipFile("/home/liusen/workdir/dummy/test.zip");
            ZipEntry entry = zipFile.getEntry("test/index.html");
            System.out.println(entry);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
