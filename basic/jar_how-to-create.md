# How to Create a Jar File in Java

URL:

- https://www.webucator.com/how-to/how-create-jar-file-java.cfm

A Java class can be stored in a jar (**J**ava **Ar**chive) file. The classes in a jar file are stored in a compressed format, much like a zip file. A jar file is a portable container of classes. 

**The benefit** of storing your class file in a jar file is that **you can execute your class from any location on the file system**. 

1、新建文件`CreateAJarFile.java`，内容如下：

```java
public class CreateAJarFile {
    public static void main (String[] args) {
        System.out.println("We will put this class in a jar file!");
    }
}
```

2、编译

```bash
$ javac CreateAJarFile.java 
```

3、测试

```bash
$ java CreateAJarFile 

We will put this class in a jar file!
```

4、打成Jar包，Jar包的好处是可以在任意地方运行class文件。

```bash
$ jar -cvf MyJarFile.jar CreateAJarFile.class 

added manifest
adding: CreateAJarFile.class(in = 459) (out= 314)(deflated 31%)
```

5、将Jar包移动到别的目录，再次测试Jar包内的class文件，命令如下：

```bash
$ java -cp ./workdir/MyJarFile.jar CreateAJarFile 

We will put this class in a jar file!
```


