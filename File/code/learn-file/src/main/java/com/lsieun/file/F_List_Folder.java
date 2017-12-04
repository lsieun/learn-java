package com.lsieun.file;

import java.io.File;

public class F_List_Folder {
    public static void main(String[] args) {
        File[] roots = File.listRoots(); //列出所有的根目录
        for(File file  : roots){
            System.out.print(file + "\t");
        }
        //输出：C:\	D:\	E:\
        System.out.println("\r\n-----------------------");

        File dir = new File("D:\\tmp");
        String[] fileNames = dir.list(); //把 当前文件夹下面的所有子文件名与子文件夹名 存储到一个String类型 的数组中 返回。
        for(String fileName : fileNames){
            System.out.print(fileName + "\t");
        }
        System.out.println("\r\n-----------------------");

        File[] files = dir.listFiles(); // 把 当前文件夹下面的所有子文件与子文件夹都使用了一个File对象描述，然后把这些File对象存储到一个File数组中返回
        for(File fileItem : files){
            System.out.println("文件名："+ fileItem.getName());
        }
        System.out.println("\r\n-----------------------");
    }
}
