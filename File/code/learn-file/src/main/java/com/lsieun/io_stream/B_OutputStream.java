package com.lsieun.io_stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class B_OutputStream {
    public static void main(String[] args) throws IOException {
        //writeTest1();
        //writeTest2();
        //writeTest3();
        writeTest4();
    }

    //写出方式1：每次只能写一个字节的数据出去。
    public static void writeTest1() throws IOException {
        //(1)找到目标文件
        File file = new File("D:\\tmp\\b.txt");
        //(2)建立数据的输出通道
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //(3)把数据写出
        fileOutputStream.write('h');
        fileOutputStream.write('e');
        fileOutputStream.write('l');
        fileOutputStream.write('l');
        fileOutputStream.write('o');
        //(4)关闭资源
        fileOutputStream.close();
    }

    //写出方式2：使用字节数组把数据写出。
    public static void writeTest2() throws IOException{
        //(1)找到目标文件
        File file = new File("D:\\tmp\\b.txt");
        //(2)建立数据输出通道
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        //(3)把数据写出。
        String data = "\r\nhello world";
        fileOutputStream.write(data.getBytes());
        //(4)关闭资源
        fileOutputStream.close();
    }

    //写出方式3：使用字节数组把数据写出，指定写出的数据长度。
    public static void writeTest3() throws IOException{
        //(1)找到目标文件
        File file = new File("D:\\tmp\\b.txt");
        //(2)建立数据输出通道
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //(3)把数据写出。
        String data = "abc";
        byte[] buf = data.getBytes();
        fileOutputStream.write(buf, 0, 3); // 0 从字节数组的指定索引值开始写， 2：写出两个字节。

        //(4)关闭资源
        fileOutputStream.close();
    }

    //使用FileOutputStream的write方法写数据的时候，虽然接收的是一个int类型的数据，但是真正写出的只是一个字节的数据，
    //只是把低八位的二进制数据写出，其他二十四位数据全部丢弃。
    public static void writeTest4() throws IOException {
        //(1)找到目标文件
        File file = new File("D:\\tmp\\c.txt");
        //(2)建立数据的输出通道
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        //(3)把数据写出
        fileOutputStream.write(609);// 609 = 512 + 97    二进制格式：10 01100001， 输出的值为a
        //(4)关闭资源
        fileOutputStream.close();
    }
}
