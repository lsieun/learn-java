package com.lsieun.file;

import java.io.File;

public class B_File_Path {
    public static void main(String[] args) {
        File file = new  File(".");
        System.out.println("当前路径是："+ file.getAbsolutePath());

        File file2 = new File("..\\..\\a.txt");
        System.out.println("存在吗？"+ file2.exists());
    }
}
