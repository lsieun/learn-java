package com.lsieun.file;


import java.io.File;
import java.io.IOException;

public class A_Construct_File {
    public static void main(String[] args) {
        //File(String pathname)
        File file1 = new File("D:\\tmp\\myPic.png");
        System.out.println("file1存在吗？ "+ file1.exists());  // exists 判断该文件是否存在，存在返回true，否则返回false。

        //File(File parent, String child)
        File parentFile = new File("D:\\tmp\\");
        File file2 = new File(parentFile,"myPic.png");
        System.out.println("file2存在吗？ "+ file2.exists());

        //File(String parent, String child)
        File file3 = new File("D:\\tmp\\","myPic.png");
        System.out.println("file3存在吗？ "+ file3.exists());

        //在windows机器上的目录分隔符是 `\`,在linux机器上的目录分隔符是`/`.
        //在windows上面`\` 与 `/` 都可以使用作为目录分隔符。 而且，如果写`/` 的时候只需要写一个即可。
        File file4 = new File("D:/tmp/myPic.png");
        System.out.println("file4存在吗？ "+ file4.exists());

		System.out.println("目录分隔符："+ File.separator);
        File file5 = new File("D:"+ File.separator + "tmp" + File.separator + "myPic.png"); //  在linux机器上是不是一个合法路径？？？
        try {
            System.out.println(file5.getCanonicalPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("file5存在吗？ "+ file5.exists());
    }
}
