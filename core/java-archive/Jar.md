# jar File

<!-- TOC -->

- [1. Scheme syntax](#1-scheme-syntax)
- [2. Manifest](#2-manifest)
- [3. Reference](#3-reference)

<!-- /TOC -->

JAR file is a file format based on the popular ZIP file format and is used for aggregating many files into one. A  JAR file is essentially a zip file that contains an optional `META-INF` directory.

> JAR文件是基于ZIP文件的，是将多个文件聚合成一个文件。

A JAR file can be created by the command-line `jar` tool, or by using the  `java.util.jar` API in the Java platform.

> 注：这里是讲创建jar包的两种方式

There is no restriction on the name of a JAR file, it can be any legal file name on a particular platform.

> jar的名称没有约束

## 1. Scheme syntax

Scheme syntax

```txt
jar:<url>!/[<entry>]
```

Example

```txt
jar:file://localhost/home/liusen/workdir/hello.jar!/lsieun/start/Main.class
jar:http://www.abc.com/book/bookStore.jar!/home/everyday/Recommend.class
```

## 2. Manifest

- `Main-Class`: gives the fully qualified name of the class you want executed if the jar is executed without specifying a class.

```txt
Main-Class: com.mindprod.canadiantax.CanadianTaxCalculator
```

Note, there is **no space** before the colon and **exactly one** afterwards. There must be a line feed at the end of the line. There is no `*.class` on the end.

- `Class-Path`

```txt
Class-Path: myplace/myjar.jar myplace/other.jar jardir/
```

> Note how the elements are separated by **space**, not semicolon or colon as on the command line.

The elements might be **absolute or relative URLs**, but I have not done experiments or found any documentation that describes what they are relative to. I presume **the main jar**. It could be the code base of the root jar file. It could be the `CWD` (Current Working Directory). If you figure it out, please let me know.

If you have multiple secondary jars, you must specify them in the manifest `Class-Path` entry of the master jar. It won’t do you any good to specify them in the `SET CLASSPATH` environment parameter or on the `java.exe -classpath` parameter.

## 3. Reference

- [JAR File Specification](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jar.html)
- [Lesson: Packaging Programs in JAR Files](https://docs.oracle.com/javase/tutorial/deployment/jar/index.html)
- [Using JAR-related APIs](https://docs.oracle.com/javase/tutorial/deployment/jar/apiindex.html)
