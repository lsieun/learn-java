package lsieun.zip.e_deflate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

// http://www.avajava.com/tutorials/lessons/how-do-i-deflate-and-inflate-a-file.html
public class DeflaterInflaterTest {
    public static void main(String[] args) throws Exception {

        String targetDir = DeflaterInflaterTest.class.getClassLoader().getResource("text").toURI().getPath();
        System.out.println(targetDir);

        FileInputStream fis = new FileInputStream(targetDir + File.separator + "readme.txt");
        FileOutputStream fos = new FileOutputStream(targetDir + File.separator + "deflated.txt");
        DeflaterOutputStream dos = new DeflaterOutputStream(fos);

        doCopy(fis, dos); // copy original.txt to deflated.txt and compress it

        FileInputStream fis2 = new FileInputStream(targetDir + File.separator + "deflated.txt");
        InflaterInputStream iis = new InflaterInputStream(fis2);
        FileOutputStream fos2 = new FileOutputStream(targetDir + File.separator + "inflated.txt");

        doCopy(iis, fos2); // copy deflated.txt to inflated.txt and uncompress it

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
