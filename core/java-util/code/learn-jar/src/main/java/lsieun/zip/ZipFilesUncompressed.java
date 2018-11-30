package lsieun.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// http://www.avajava.com/tutorials/lessons/how-can-i-create-an-uncompressed-zip-file-from-a-set-of-files.html
public class ZipFilesUncompressed {
    public static void main(String[] args) {

        try {
            FileOutputStream fos = new FileOutputStream("target/uncompressed-test.zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            // This sets the compression level to STORED, ie, uncompressed
            zos.setLevel(ZipOutputStream.STORED);

            String file1Name = "target/classes/text/readme.txt";
            String file2Name = "target/classes/images/give-yourself-time.jpg";

            addToZipFile(file1Name, zos);
            addToZipFile(file2Name, zos);

            zos.close();
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void addToZipFile(String fileName, ZipOutputStream zos) throws FileNotFoundException, IOException {

        System.out.println("Writing '" + fileName + "' to zip file");

        File file = new File(fileName);
        FileInputStream fis = new FileInputStream(file);
        ZipEntry zipEntry = new ZipEntry(fileName);
        zos.putNextEntry(zipEntry);

        byte[] bytes = new byte[1024];
        int length;
        while ((length = fis.read(bytes)) >= 0) {
            zos.write(bytes, 0, length);
        }

        zos.closeEntry();
        fis.close();
    }
}
