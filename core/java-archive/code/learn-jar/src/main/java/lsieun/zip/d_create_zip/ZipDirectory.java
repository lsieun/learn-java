package lsieun.zip.d_create_zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

// http://www.avajava.com/tutorials/lessons/how-do-i-zip-a-directory-and-all-its-contents.html
public class ZipDirectory {
    public static void main(String[] args) throws IOException {
        String dir = System.getProperty("user.dir") + "/target/classes";
        File directoryToZip = new File(dir);

        List<File> fileList = new ArrayList<>();
        System.out.println("---Getting references to all files in: " + directoryToZip.getCanonicalPath());
        getAllFiles(directoryToZip, fileList);
        System.out.println("---Creating zip file");
        writeZipFile(directoryToZip, fileList);
        System.out.println("---Done");
    }

    public static void getAllFiles(File dir, List<File> fileList) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                fileList.add(file);
                if (file.isDirectory()) {
                    System.out.println("directory: " + file.getCanonicalPath());
                    getAllFiles(file, fileList);
                } else {
                    System.out.println("     file: " + file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeZipFile(File directoryToZip, List<File> fileList) {
        try {
            FileOutputStream fos = new FileOutputStream(directoryToZip.getCanonicalPath() + ".jar");
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (File file : fileList) {
                if (!file.isDirectory()) { // only zip files, not directories
                    addToZip(directoryToZip, file, zos);
                }
            }

            zos.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addToZip(File directoryToZip, File file, ZipOutputStream zos) throws FileNotFoundException,
            IOException {

        FileInputStream fis = new FileInputStream(file);

        // we want the zipEntry's path to be a relative path that is relative
        // to the directory being zipped, so chop off the rest of the path
        String zipFilePath = file.getCanonicalPath().substring(directoryToZip.getCanonicalPath().length() + 1);
        System.out.println("Writing '" + zipFilePath + "' to zip file");
        ZipEntry zipEntry = new ZipEntry(zipFilePath);
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
