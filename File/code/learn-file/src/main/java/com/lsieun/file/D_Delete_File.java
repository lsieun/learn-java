package com.lsieun.file;

import java.io.File;

public class D_Delete_File {
    public static void main(String[] args) {
        File file = new File("F:\\a.txt");
        System.out.println("删除成功吗？ "+ file.delete()); //delete方法不能用于删除非空的文件夹。 delete方法会马上删除一个文件。
        file.deleteOnExit();  //jvm退出的时候删除文件。  一般用于删除临时 文件。
        System.out.println("OVER");
    }
}
