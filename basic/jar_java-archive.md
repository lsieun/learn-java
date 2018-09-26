# Java Archive (Jar)

URL：

－ http://www.ntu.edu.sg/home/ehchua/programming/java/J9d_Jar.html


## 1. Java Archive (JAR) Files

Java Archive (`JAR`) is **a platform-independent file format** that allow you to compress and bundle multiple files associated with a Java application, applet, or WebStart application into a single file. `JAR` is based on the popular `ZIP` algorithm, and mimic the Unix's `tar` (or tape archive) file format (e.g., `jar` and `tar` tools have the same command-line options).

> 这段理解三个意思：  
> （1）JAR是一个平台独立的文件格式，它是一个可以包含多个文件的压缩包。  
> （2）JAR的压缩方式是基于ZIP算法的。  
> （3）jar和tar有相同的命令行参数。

JAR files provide the following functions:

- Data compression (using the ZIP algorithm).
- Ease in distribution: All files in a Java package can be placed in a single file to facilitate distribution. Furthermore, transfer one big file over the network instead of many small files is faster and more efficient because it involves less overhead.
- Authentication: JAR file can be digitally signed by its author. You can authenticate the author of the JAR file by checking the signature and the author's digital certificate.

> JAR文件可以实现的三个功能：（1）数据压缩；（2）易于分发；（3）认证。

The **Java Runtime** (`JRE`) (or Java applications) can load **classes** from the `JAR` file directly, without un-jarring the file. Furthermore, the `JAR` files use the same file format as `ZIP` files, and can be opened and manipulated via `ZIP` programs such as `WinZIP` or `WinRAR`.

> JRE可以从JAR文件中加载class文件。

## 2. Creating JAR File

There are a few ways that you can create a `JAR` file:

- Via the command-line `jar` tool: JDK provides a command-line tool called "jar.exe", to create, manage and manipulate JAR files. The `jar` tool reference is available at http://docs.oracle.com/javase/7/docs/technotes/tools/windows/jar.html.
- Via the "export" option of IDE such as Eclipse or NetBeans, in practice.

> 创建JAR的两种方式：第一种，是使用jar命令行工具；第二种，是使用IDE。


### 2.1 Create JAR File using Command-Line "jar" Tool

Creating `JAR` file using command-line `jar` tool is clumpy! It is presented here for your basic understanding and completeness. In practice, you should use your `IDE` (such as Eclipse/NetBeans) to create `JAR` file instead.

**Syntax**

To create a JAR file using the `jar` tool, issue the `jar` command (on CMD shell) with '`c`' option:

```bash
> jar cvf jarFile inputFileDir1 inputFileDir2 ...
```

where:

- '`c`' option indicates that you want to **create** a new JAR file;
- '`v`' option asks for **verbose mode**, which displays information messages;
- '`f`' specifies that the output go to **a file** specified in `jarFile` (instead of the standard output by default). Option '`f`' and `jarFile` are a pair.
- `inputFileDir` give the input filenames or directory names for the files to be jarred. Multiple names are separated by space. The name may contain wildcard '`*`'.

**Example**

For example, suppose that "`images`" is an sub-directory under the current directory, the following command jar-up all the "`.class`" files of the current directory and the entire "images" sub-directory into "hello.jar". The '`v`' option instructs the tool to produce the information messages.

```
> jar cvf hello.jar *.class images

added manifest
adding: HelloJar$1.class(in = 893) (out= 520)(deflated 41%)
adding: HelloJar$2.class(in = 393) (out= 284)(deflated 27%)
adding: HelloJar.class(in = 1808) (out= 1014)(deflated 43%)
adding: images/(in = 0) (out= 0)(stored 0%)
adding: images/high.png(in = 978) (out= 983)(deflated 0%)
adding: images/muted.png(in = 839) (out= 844)(deflated 0%)
```

**Jarring-Up Files in Packages**

Java classes are often placed in packages. To jar up all the classes of a package, you must provide the proper sub-directory structure (as depicted by the package name). Recall that package name with '`.`' are mapped to sub-directory, e.g., the class file `com.test.MyClass` is stored as `com\test\MyClass.class`.

For example, the following command jar-up all the classes in `mypackage` and the `image` sub-directory. **Take note** that the `jar` command should be issued from the **project root directory**, i.e., the base directory of the packages.

```cmd
> jar cvf hello.jar mypackage\*.class images

added manifest
adding: mypackage/HelloJarInPackage$1.class(in = 1016) (out= 536)(deflated 47%)
adding: mypackage/HelloJarInPackage$2.class(in = 440) (out= 303)(deflated 31%)
adding: mypackage/HelloJarInPackage.class(in = 1931) (out= 1034)(deflated 46%)
adding: images/(in = 0) (out= 0)(stored 0%)
adding: images/high.png(in = 978) (out= 983)(deflated 0%)
adding: images/muted.png(in = 839) (out= 844)(deflated 0%)
```

### 2.3 Manifest

Many of the `JAR` functions, such as main-class specification, digital signing, version control, package sealing, are supported though a file called `manifest`. The `manifest` is a special file, called "`MANIFEST.MF`" under the "`META-INF`" sub-directory, that contains information about the files contained in a `JAR` file.

When you create a `JAR` file without an input manifest, it automatically receives a default manifest file (called "`META-INF\MANIFEST.MF`") which contains the following. 

```txt
Manifest-Version: 1.0
Created-By: 1.7.0_03 (Oracle Corporation)
```


The `entries` in `manifest` take the form of "`name: values`" pair. The `name` and `value` are separated by a colon '`:`'. The `name` is also called the **attribute**.

Refer to **JDK API Specification** on the `JAR` for [detailed syntax on the manifest](http://docs.oracle.com/javase/7/docs/technotes/guides/jar/jar.html#JAR_Manifest).

### 2.3 Creating an Executable JAR File with Manifest using "jar" tool

As mentioned, `JRE` can load **classes** from a `JAR` file directly without un-jarring the file. In fact, you can run a Java application directly from a `JAR` file. The `JAR` file, however, must contain a `manifest` with a header called `Main-Class` which specifies the `main-class` that contains the entry `main()` method. (Otherwise, which class to launch the application?)

**Example**

Let's create an executable `JAR` file.

First, prepare the following text file (to be used as input manifest) called "`hello.mf`" (using a text editor). This file specifies the `main-class` that contains the entry `main()` method for launching the application. Take note that the file **MUST BE** terminated with **a blank line** as shown.

```txt
Manifest-Version: 1.0
Main-Class: mypackage.HelloJarInPackage
```
 
Next, create the `JAR` file with the input manifest using the command-line `jar` tool:

```cmd
> jar cvfm hello.jar hello.mf mypackage\*.class images

added manifest
adding: mypackage/HelloJarInPackage$1.class(in = 1016) (out= 536)(deflated 47%)
adding: mypackage/HelloJarInPackage$2.class(in = 440) (out= 303)(deflated 31%)
adding: mypackage/HelloJarInPackage.class(in = 1931) (out= 1034)(deflated 46%)
adding: images/(in = 0) (out= 0)(stored 0%)
adding: images/high.png(in = 978) (out= 983)(deflated 0%)
adding: images/muted.png(in = 839) (out= 844)(deflated 0%)
```

where option '`m`' specifies the inclusion of **an input manifest**. **Take note** that the '`m`' option comes after the '`f`' option, hence, **the manifest file** shall be placed after **the output JAR file**.

Use `WinZIP` or `WinRAR` to inspect the manifest "`META-INF\MANIFEST.MF`" created:

```txt
Manifest-Version: 1.0
Created-By: 1.7.0_03 (Oracle Corporation)
Main-Class: mypackage.HelloJarInPackage
```

To run the application directly from `JAR` file, invoke the JRE with option "`-jar`":

```cmd
> java -jar Hello.jar
```

In windows, you can also double-click the `JAR` file to launch the application, provided that the file type "`.jar`" is associated with the JRE "`java.exe`".


## 3. Signing and Verifying a JAR file

A JAR file author can digitally sign the `JAR` file for ownership authentication. The integrity of the files are also ensured by signing the hash of each of the files.

> 这里没有详细看，因为目前还用不到。

## 4. More on jar Tool

As mentioned, you can easily inspect `JAR` files using `ZIP` programs such as `WinZIP` or `WinRAR`. Nonetheless, you can also use the command-line `jar` tool, which is pretty clumpy, but described here for completeness!

**List Table of Content of JAR File (`t`)**

To list the table of contents of a jar file, use option '`t`', as shown:

```cmd
> jar tvf jarFile
```

The '`v`' (verbose) option displays the information messages; the '`f`' (file) option specifies the name of the jar-file.

**Extract JAR file (`x`)**

To extract the contents an entire `JAR` file, use option '`x`':

```cmd
> jar xvf jarFile
```

To extract specific file(s) from a `JAR` file, use option '`x`' and specify the file(s) to be extracted:

```cmd
> jar xvf jarFile filesToExtract
```

**Update JAR File (`u`)**

To update JAR file, use '`u`' option::

```cmd
> jar uvf jarFile inputFiles
```

**Add Index to JAR File (`i`)**

Use option '`i`' to generate index information in a file called `INDEX.LIST` inside the specified jarfile, which contains location information for each package in JAR file and all the JAR files specified in the Class-Path attribute.

```cmd
> jar i jarFile
```

## 5. Processing JAR Files and JAR API

The API reference for "Java Archive (JAR) Files" is available @ http://docs.oracle.com/javase/7/docs/technotes/guides/jar/index.html.

### 5.1  Reading Resources from JAR File - `ClassLoader`'s `getResource()` and `getResourceAsStream()`

Your programs often require reading resources (such as images, data files, native libraries, ResourceBundle and properties-files) bundled in a `JAR` file. You can use `ClassLoader`'s method `getResource()` or `getResourceAsStream()` to access the JAR (as well as the regular file sytem). `getResource()` returns a `java.net.URL`; while `getResourceAsStream()` returns an `java.io.InputStream`.

For example,

```java
String filename = "images/duke.gif";
java.net.URL imgURL = this.getClass().getClassLoader().getResource(filename);  // 1
// or
java.net.URL imgURL = this.getClass().getResource(filename);  // 2
```

The difference is `[1]` and `[2]` is that the filename in `[1]` is relative to the project root; while `[2]` is relative to the current class-file. Suppose that this class is `mypackage.myClass` and stored as `somepath\mypackage\myClass.class`. `[1]` asks for `somepath\images\duke.gif`, while `[2]` asks for `somepath\mypackage\image\duke.gif`.

### 5.2  Package java.util.jar

- Class `JarFile`: used to read the contents of a jar file from any file that can be opened with `java.io.RandomAccessFile`.
- Class `JarEntry`: represent an entry in a JAR file.
- Class `Manifest`: used to maintain manifest entries of "`name: value`" pairs.
- Class `Attributes`: maps manifest attribute names to their values.
- Class `Attributes.Name`: inner class of Attributes, representing the attribute names.

Example:

```java
JarFile jarfile = new JarFile(jarFileName);
Manifest mf = jarfile.getManifestI();
Attributes attrs = mf.getMainAttributes();
String mainClassName = attrs.getValue(Attributes.Name.MAIN_CLASS);
```

## REFERENCES & RESOURCES

- jar - The Java Archive Tool @ http://docs.oracle.com/javase/7/docs/technotes/tools/windows/jar.html.
- Java Archive (JAR) Files @ http://docs.oracle.com/javase/7/docs/technotes/guides/jar/index.html.
- JAR Manifest Format @ http://docs.oracle.com/javase/7/docs/technotes/guides/jar/jar.html#JAR_Manifest.