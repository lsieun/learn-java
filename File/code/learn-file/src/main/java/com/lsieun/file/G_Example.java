package com.lsieun.file;

import java.io.File;
import java.io.FilenameFilter;

public class G_Example {
    public static void main(String[] args) {
        File dir = new File("D:\\tmp");
        listJava(dir);
        System.out.println("-------------------------");
        listJava2(dir);
        System.out.println("-------------------------");
        listFileAndDirectory(dir);
    }

    //第1种方法：列出所有的java文件
    public static void listJava(File dir){
        File[] files = dir.listFiles(); //获取到了所有的子文件
        for(File file : files){
            String fileName = file.getName();
			/*if(fileName.endsWith(".java")&&file.isFile()){
				System.out.println(fileName);
			}*/
            //使用正则表达式匹配
            if(fileName.matches(".+\\.java")&&file.isFile()){
                System.out.println(fileName);
            }

        }
    }

    //第2种方法：列出所有的java文件
    public static void listJava2(File dir){
        File[] files = dir.listFiles(new MyFilter()); //得到文件夹下面的所有子文件与文件夹。
        for(File file : files){
            System.out.println(file.getName());
        }
    }

    //文件夹下面的所有子文件与文件夹
    public static void listFileAndDirectory(File dir){
        File[] files = dir.listFiles();//获取到所有的子文件
        System.out.println("文件：");
        for(File fileItem : files){
            if(fileItem.isFile()){
                System.out.println("\t"+fileItem.getName());
            }
        }


        System.out.println("文件夹：");
        for(File fileItem : files){
            if(fileItem.isDirectory()){
                System.out.println("\t"+fileItem.getName());
            }
        }


    }

}

// 自定义一个文件名过滤器
class MyFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        //System.out.println("文件夹:"+dir+" 文件名："+ name);
        return name.endsWith(".java");
    }

}
