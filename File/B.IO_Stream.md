# IO流 #

## 1、File与IO流的区分 ##

**File类**: 用于描述一个文件或者文件夹的。
 
通过File对象我们可以读取文件或者文件夹的属性数据，如果我们需要读取文件的内容数据，那么我们需要使用IO流技术。
 
**IO流（Input Output）**

IO流解决问题： 解决设备与设备之间的数据传输问题。  内存--->硬盘   硬盘--->内存


## 2、IO流技术分类 ##

如果是按照数据的流向划分：
- 输入流
- 输出流
		
如果按照处理的单位划分：
- 字节流: 字节流读取得都是文件中二进制数据，读取到二进制数据不会经过任何的处理。
- 字符流： 字符流读取的数据是以字符为单位的 。 字符流也是读取文件中的二进制数据，不过会把这些二进制数据转换成我们能 识别的字符。  

> 字符流 = 字节流 + 解码
				
## 3、输入字节流 ##

--------| InputStream 所有输入字节流的基类（抽象类）
------------| FileInputStream  读取文件数据的输入字节流 
			
使用FileInputStream读取文件数据的步骤：
1. 找到目标文件
2. 建立数据的输入通道。
3. 读取文件中的数据。
4. 关闭 资源.

### 3.1、读取单个字节 ###

示例一：

```java
    //读取方式1，缺陷： 无法读取完整一个文件的数据.
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
```

### 3.2、循环读取单个字节 ###

示例二：

```java
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
```

### 3.3、读取到字节数组中 ###

示例三：

```java
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
```

### 3.4、循环读取到字节数组中（推荐使用） ###

示例四：

```java
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
```

### 3.5、FAQ ###

问题1： 读取完一个文件的数据的时候，不关闭资源有什么影响?
答案： 资源文件一旦 使用完毕应该马上释放，否则其他的程序无法对该资源文件进行其他 的操作。

问题2：FileInputStream.read()和FileInputStream.read(byte[])，两个方法都返回int类型的值，两者的区别是什么？
答案：FileInputStream.read()返回的是读取的字节内容(ascii码表示)；而FileInputStream.read(byte[])返回的是读取字节的长度，即读取了多少个字节。


## 4、输出字节流 ##
 
 --------| OutputStream 是所有输出字节流 的父类（抽象类）
 -----------| FileOutStream 向文件输出数据的输出字节流。
 
**FileOutputStream如何使用呢？**
1. 找到目标文件
2. 建立数据的输出通道。
3. 把数据转换成字节数组写出。
4. 关闭资源

**FileOutputStream要注意的细节**：
1. 使用FileOutputStream 的时候，如果目标文件不存在，那么会自动创建目标文件对象。 
2. 使用FileOutputStream写数据的时候，如果目标文件已经存在，那么会先清空目标文件中的数据，然后再写入数据。
3. 使用FileOutputStream写数据的时候, 如果目标文件已经存在，需要在原来数据基础上追加数据的时候应该使用new FileOutputStream(file,true)构造函数，第二参数为true。
4. 使用FileOutputStream的write方法写数据的时候，虽然接收的是一个int类型的数据，但是真正写出的只是一个字节的数据，只是
把低八位的二进制数据写出，其他二十四位数据全部丢弃。

### 4.1、写出单个字节FileOutputStream.write(int b) ###

示例一：

```java
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
```

### 4.2、将整个字节数组写出FileOutputStream.write(byte b[]) ###

示例二：

```java
    //写出方式2：使用字节数组把数据全部写出去。
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
```

### 4.3、将整个字节数组的一部分写出FileOutputStream.write(byte b[], int off, int len) ###

示例三：

```java
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
```

### 4.4、FileOutputStream.write(int b)方法的注意事项 ###

示例四：

```java
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
```

### 4.5、案例：拷贝图片 ###

示例五：

```java
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
```


示例N：

```java

```

>至此结束