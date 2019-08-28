package lsieun.zip.a_info;

import java.io.IOException;
import java.util.zip.ZipFile;

public class A_ZipFile_01_BasicInfo {
    public static void main(String[] args) {
        String dir = System.getProperty("user.dir");
        String filepath = dir + "/target/hello.jar";

        try {
            ZipFile zipFile = new ZipFile(filepath);
            String name = zipFile.getName(); // 注意：这里返回的不是简单的“文件名”，而是完整的“文件路径”
            String comment = zipFile.getComment();
            int size = zipFile.size(); // 注意：这里的size并不是指“占用硬盘空间大小”，而是指“存储的zip entry数量”

            System.out.println("Class: " + zipFile.getClass());
            System.out.println("name: " + name);
            System.out.println("comment: " + comment);
            System.out.println("size: " + size);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
