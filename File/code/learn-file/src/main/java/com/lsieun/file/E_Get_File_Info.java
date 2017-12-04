package com.lsieun.file;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class E_Get_File_Info {
    public static void main(String[] args) throws IOException{
        File file = new File("D:\\tmp\\..\\tmp\\myPic.png");
        System.out.println("存在吗？"+ file.exists());
        System.out.println("判断是否是一个文件："+file.isFile()); //如果是文件返回true，否则返回false.
        System.out.println("判断是否是一个文件夹："+ file.isDirectory()); // 是文件夹返回ture，否则返回false.
        System.out.println("是隐藏的 文件吗："+ file.isHidden());
        System.out.println("是绝对路吗？"+ file.isAbsolute());

        System.out.println("文件名："+ file.getName());//myPic.png
        System.out.println("获取绝对路径："+ file.getPath());//D:\tmp\..\tmp\myPic.png
        System.out.println("getAbsolutePath获取绝对路径："+file.getAbsolutePath());//D:\tmp\..\tmp\myPic.png
        System.out.println("getCanonicalPath获取绝对路径："+file.getCanonicalPath());//D:\tmp\myPic.png
        System.out.println("获取文件的的大小(字节为单位)："+ file.length());
        System.out.println("获取文件的父路径："+ file.getParent());//D:\tmp\..\tmp

        //使用毫秒值转换成Date对象
        long lastModified = file.lastModified();
        Date date = new Date(lastModified);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
        System.out.println("获取最后一次的修改时间(毫秒值)："+ dateFormat.format(date) );
    }
}
