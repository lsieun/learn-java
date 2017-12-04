package com.lsieun.file;


import java.io.File;
import java.io.IOException;

public class C_Create_File_And_Folder {
    public static void main(String[] args) throws IOException{
        File file = new File("E:\\aa");
        System.out.println("创建成功了吗？"+file.createNewFile()); //createNewFile 创建一个指定的文件，如果该文件存在了，则不会再创建，如果还没有存在则创建。创建成功返回true，否则返回false。

        File dir = new  File("E:\\a.txt");//注意这里创建了文件夹a.txt，而不是文件
        System.out.println("创建文件夹成功吗？"+dir.mkdir()); // mkdir 创建一个单级文件夹，
        dir = new File("E:\\hh\\kk");
        System.out.println("创建多级文件夹："+ dir.mkdirs());

        //renameTo()  如果目标文件与源文件是在同一个路径下，那么renameTo的作用是重命名， 如果目标文件与源文件不是在同一个路径下，那么renameTo的作用就是剪切，而且还不能操作文件夹。
        File destFile = new File("F:\\aaaaaaw");
        System.out.println("重命名成功吗？"+file.renameTo(destFile)) ;
    }
}
