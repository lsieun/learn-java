package lsieun.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

// http://www.avajava.com/tutorials/lessons/how-do-i-compress-and-uncompress-a-gzip-file.html
public class GZIPCompressUncompressTest {
    public static void main(String[] args) throws Exception {
        String targetDir = DeflaterInflaterTest.class.getClassLoader().getResource("text").toURI().getPath();
        System.out.println(targetDir);

        FileInputStream fis = new FileInputStream(targetDir + File.separator + "readme.txt");
        FileOutputStream fos = new FileOutputStream(targetDir + File.separator + "compressed.txt.gz");
        GZIPOutputStream gos = new GZIPOutputStream(fos);

        doCopy(fis, gos); // copy and compress

        FileInputStream fis2 = new FileInputStream(targetDir + File.separator + "compressed.txt.gz");
        GZIPInputStream gis = new GZIPInputStream(fis2);
        FileOutputStream fos2 = new FileOutputStream(targetDir + File.separator + "uncompressed.txt");

        doCopy(gis, fos2); // copy and uncompress

    }

    public static void doCopy(InputStream is, OutputStream os) throws Exception {
        int oneByte;
        while ((oneByte = is.read()) != -1) {
            os.write(oneByte);
        }
        os.close();
        is.close();
    }
}
