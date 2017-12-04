# 文件 #
IO流(Input Output) ：

IO技术主要的作用是解决 设备与设备之间 的数据传输问题。 比如： 

	硬盘--->内存          
	内存的数据---->硬盘上            
	把键盘的数据------->内存中

IO技术的应用场景：

	 导出报表 ， 上传大头照   、 下载 、 解释xml文件 ... 

数据保存到硬盘上，该数据就可以做到永久性的保存。   数据一般是以**文件**的形式保存到硬盘上

sun使用了一个**File**类描述了文件或者文件夹的。

## 1、构造File对象 ##

File类可以描述一个文件或者一个文件夹。


File类的构造方法：

	File(String pathname)  指定文件或者文件夹的路径创建一个File文件。
	
	File(File parent, String child)   根据 parent 抽象路径名和 child 路径名字符串创建一个新 File 实例。 

	File(String parent, String child) 

示例：

```java
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
```

### 1.2、Windows和Linux目录分隔符的区别 ###

目录分隔符：在windows机器上的目录分隔符是 `\`,在linux机器上的目录分隔符是`/`.

注意：在windows上面`\` 与 `/` 都可以使用作为目录分隔符。 而且，如果写`/`的时候只需要写一个即可。
```java
File file4 = new File("D:/tmp/myPic.png");
System.out.println("file4存在吗？ "+ file4.exists());

System.out.println("目录分隔符："+ File.separator);
File file5 = new File("D:"+ File.separator + "tmp" + File.separator + "myPic.png"); 
System.out.println(file5.getCanonicalPath());
System.out.println("file5存在吗？ "+ file5.exists());
```

## 2、路径问题 ##

路径分类：绝对路径、相对路径、classpath路径、执行路径。这里只讲述“绝对路径”和“相对路径”。

- 绝对路径： 该文件在硬盘上 的完整路径。绝对路径一般都是以盘符开头的。
- 相对路径:  相对路径就是资源文件相对于当前程序所在的路径。

在**相对路径**中，`.`表示当前路径，`..`表示上一级路径。
 
>注意： 相对路径有一个限制，即如果程序当前所在的路径与资源文件不是在同一个盘下面，是没法写相对路径的。

```java
File file = new  File(".");
System.out.println("当前路径是："+ file.getAbsolutePath());

File file2 = new File("..\\..\\a.txt");
System.out.println("存在吗？"+ file2.exists());
```

## 3、文件操作 ##

### 3.1、创建文件 ###

创建：

	createNewFile()	在指定位置创建一个空文件，成功就返回true，如果已存在就不创建然后返回false
	mkdir()			在指定位置创建目录，这只会创建最后一级目录，如果上级目录不存在就抛异常。
	mkdirs()		在指定位置创建目录，这会创建路径中所有不存在的目录。
	renameTo(File dest)	重命名文件或文件夹，也可以操作非空的文件夹，文件不同时相当于文件的剪切,剪切时候不能操作非空的文件夹。移动/重命名成功则返回true，失败则返回false。

示例代码：

```java
File file = new File("E:\\aa");
System.out.println("创建成功了吗？"+file.createNewFile()); //createNewFile 创建一个指定的文件，如果该文件存在了，则不会再创建，如果还没有存在则创建。创建成功返回true，否则返回false。

File dir = new  File("E:\\a.txt");//注意这里创建了文件夹a.txt，而不是文件
System.out.println("创建文件夹成功吗？"+dir.mkdir()); // mkdir 创建一个单级文件夹，
dir = new File("E:\\aa\\bb");
System.out.println("创建多级文件夹："+ dir.mkdirs());

//renameTo()  如果目标文件与源文件是在同一个路径下，那么renameTo的作用是重命名， 如果目标文件与源文件不是在同一个路径下，那么renameTo的作用就是剪切，而且还不能操作文件夹。
File destFile = new File("F:\\aaaaaaw");
System.out.println("重命名成功吗？"+file.renameTo(destFile)) ;
```

### 3.2、删除文件 ###

删除：

	delete()		删除文件或一个空文件夹，如果是文件夹且不为空，则不能删除，成功返回true，失败返回false。
	deleteOnExit()	在虚拟机终止时，请求删除此抽象路径名表示的文件或目录，保证程序异常时创建的临时文件也可以被删除

示例代码：

```java
File file = new File("F:\\a.txt");
System.out.println("删除成功吗？ "+ file.delete()); //delete方法不能用于删除非空的文件夹。 delete方法会马上删除一个文件。
file.deleteOnExit();  //jvm退出的时候删除文件。  一般用于删除临时 文件。
System.out.println("OVER");
```

### 3.3、获取文件信息 ###

>注意：这里，使用File类只是获取文件的一些属性信息，并没有读取文件的内容；如果要读取文件的内容，则需要用到FileInputStream类。

判断：

	exists()		文件或文件夹是否存在。
	isFile()		是否是一个文件，如果不存在，则始终为false。
	isDirectory()	是否是一个目录，如果不存在，则始终为false。
	isHidden()		是否是一个隐藏的文件或是否是隐藏的目录。
	isAbsolute()	测试此抽象路径名是否为绝对路径名。

获取：

	getName()		获取文件或文件夹的名称，不包含上级路径。
	getPath()       返回绝对路径，可以是相对路径，但是目录要指定
	getAbsolutePath()	获取文件的绝对路径，与文件是否存在没关系
	length()		获取文件的大小（字节数），如果文件不存在则返回0L，如果是文件夹也返回0L。
	getParent()		返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回null。
	lastModified()	获取最后一次被修改的时间。

```java
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
```


## 4、文件夹操作 ##

文件夹相关：

	staic File[] listRoots()    列出所有的根目录（Window中就是所有系统的盘符）
	list()                      返回目录下的文件或者目录名，包含隐藏文件。对于文件这样操作会返回null。
	listFiles()                 返回目录下的文件或者目录对象（File类实例），包含隐藏文件。对于文件这样操作会返回null。


	list(FilenameFilter filter)         返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。
	listFiles(FilenameFilter filter)    返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。

示例：

```java
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
```

## 5、案例 ##

需求描述：
- 需求1：指定一个文件夹，然后该文件夹下面所有java文件。
- 需求2：指定一个文件夹，然后列出文件夹下面的所有子文件与文件夹

需求2格式如下：
 
	文件：
		文件名1
		文件名2
		文件名3
		..
	
	文件夹：
		文件夹名1
		文件夹名2
		文件夹名3
		....

可使用的方法如下：

	listFiles(FilenameFilter filter)	返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。
	list(fileNameFilter filter)	返回指定当前目录中符合过滤条件的子文件或子目录。对于文件这样操作会返回null。

代码：

```java
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

    //需求2：文件夹下面的所有子文件与文件夹
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
```

>至此结束