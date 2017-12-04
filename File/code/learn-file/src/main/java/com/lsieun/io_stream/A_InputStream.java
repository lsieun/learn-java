package com.lsieun.io_stream;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class A_InputStream {
    public static void main(String[] args) throws IOException {
        //readTest1();
        //readTest2();
        //readTest3();
        readTest4();
    }

    //读取方式1，缺陷： 无法读取完整一个文件 的数据.
    public static void readTest1() throws IOException {
        //(1)找到目标文件
        File file = new File("D:\\tmp\\a.txt");
        //(2)建立数据的输入通道。
        FileInputStream fileInputStream = new FileInputStream(file);
        //(3)读取文件中的数据
        int content = fileInputStream.read(); // read() 读取一个字节的数据，把读取的数据返回。
        System.out.println("读到的内容是："+ (char)content);
        //(4)关闭资源，实际上就是释放资源。
        fileInputStream.close();
    }

    //读取方式2 ： 使用循环读取文件的数据
    public static void readTest2() throws IOException{
        long startTime = System.currentTimeMillis();
        //(1)找到目标文件
        File file = new File("D:\\tmp\\a.txt");
        //(2)建立数据的输入通道
        FileInputStream fileInputStream = new FileInputStream(file);
        //(3)读取文件的数据
        int content = 0; //声明该变量用于存储读取到的数据
        while((content = fileInputStream.read())!=-1){
            System.out.print((char)content);
        }
        System.out.println("\r\n-------------------");
        //(4)关闭资源
        fileInputStream.close();

        long endTime = System.currentTimeMillis();
        System.out.println("读取的时间是："+ (endTime-startTime)); //446
    }

    //读取方式3：使用缓冲 数组 读取。    缺点： 无法读取完整一个文件的数据。想像有一个12G的文件。
    public static void readTest3() throws IOException{
        //(1)找到目标文件
        File file = new File("D:\\tmp\\a.txt");
        //(2)建立数据的输入通道
        FileInputStream fileInputStream = new FileInputStream(file);
        //(3)建立缓冲字节数组，读取文件的数据。
        byte[] buf = new byte[1024];  //相当于超市里面的购物车。
        int length = fileInputStream.read(buf); // 如果使用read读取数据传入字节数组，那么数据是存储到字节数组中的，而这时候read方法的返回值是表示的是本次读取了几个字节数据到字节数组中。
        System.out.println("length:"+ length);

        //使用字节数组构建字符串
        String content = new String(buf,0,length);
        System.out.println("内容："+ content);
        //(4)关闭资源
        fileInputStream.close();
    }

    //读取方式4：使用缓冲数组配合循环一起读取。28原则：缓冲数组的大小满足80%的读取文件效率较高就可以。
    public static void readTest4() throws IOException{
        long startTime = System.currentTimeMillis();
        //(1)找到目标文件
        File file = new File("D:\\tmp\\my_big_file.pdf");
        //(2)建立数据的输入通道
        FileInputStream fileInputStream = new FileInputStream(file);
        //(3)建立缓冲数组配合循环读取文件的数据。
        int length = 0; //保存每次读取到的字节个数。
        byte[] buf = new byte[1024]; //存储读取到的数据    缓冲数组 的长度一般是1024的倍数，因为与计算机的处理单位。  理论上缓冲数组越大，效率越高

        while((length = fileInputStream.read(buf))!=-1){ // read方法如果读取到了文件的末尾，那么会返回-1表示。
            System.out.print(new String(buf,0,length));
        }

        //(4)关闭资源
        fileInputStream.close();

        long endTime = System.currentTimeMillis();
        System.out.println("读取的时间是："+ (endTime-startTime));
    }
}
