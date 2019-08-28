# jar File

URL: 

- https://docs.oracle.com/javase/tutorial/deployment/jar/index.html
- https://docs.oracle.com/javase/tutorial/deployment/jar/apiindex.html



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

## Manifest

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
