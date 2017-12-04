package com.lsieun.io_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyImage {
    public static void main(String[] args) throws IOException {
        //(1)找到目标文件
        File inFile = new File("D:\\tmp\\img01.jpg");
        File destFile = new File("D:\\tmp\\img10.jpg");
        //(2)建立数据的输入输出通道
        FileInputStream fileInputStream = new  FileInputStream(inFile);
        FileOutputStream fileOutputStream = new FileOutputStream(destFile); //追加数据....

        //(3)每新创建一个FileOutputStream的时候，默认情况下FileOutputStream 的指针是指向了文件的开始的位置。 每写出一次，指向都会出现相应移动。
        //建立缓冲数据，边读边写
        byte[] buf = new byte[1024];
        int length = 0 ;
        while((length = fileInputStream.read(buf))!=-1){
            fileOutputStream.write(buf,0,length); //这里指明了write的字节长度，因为最后一次读取时，buf中可能读不满数据
        }
        //(4)关闭资源 原则： 先开后关，后开先关。
        fileOutputStream.close();
        fileInputStream.close();
    }
}
