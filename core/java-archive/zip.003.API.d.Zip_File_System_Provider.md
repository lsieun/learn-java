# Zip File System Provider

## Introduction

The **zip file system provider** introduced in the Java SE 7 release<sub>注：说明Java6之前是没有的</sub> is an implementation of a custom file system provider. The **zip file system provider** treats a `zip` or `JAR` file<sub>注：适用于zip，也适用于jar包</sub> as **a file system** and provides the ability to manipulate the contents of the file. The zip file system provider creates multiple file systems — one file system for each zip or JAR file.

## Using the Zip File System Provider

You can use the factory methods of the `java.nio.file.FileSystems` class to **create a new zip file system**<sub>注：创建一个新的zip文件</sub> or to **obtain a reference to an existing zip file system**<sub>注：使用一个已有的zip文件</sub>.

Create a zip file system by specifying the path of the zip or JAR file in one of the following ways:

- By using the JAR URL syntax defined in the `java.net.JarURLConnection` class

```java
URI uri = URI.create("jar:file:/codeSamples/zipfs/zipfstest.zip");
FileSystem fs = FileSystems.newFileSystem(uri, env);
```

- By specifying a path and using automatic file type detection

```java
Path zipfile = Paths.get("/codeSamples/zipfs/zipfstest.zip");
FileSystem fs = FileSystems.newFileSystem(zipfile, env, null);
```

Specify the configuration options for the zip file system in the `java.util.Map` object passed to the `FileSystems.newFileSystem` method.

Once you have an instance of a zip file system, you can invoke the methods of the `java.nio.file.FileSystem` and `java.nio.file.Path` classes to perform operations such as copying, moving, and renaming files, as well as modifying file attributes.

The following code sample shows how to create a zip file system and copy a file to the new zip file system.

```java
import java.util.*;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.*;

public class ZipFSPUser {
    public static void main(String [] args) throws Throwable {
        Map<String, String> env = new HashMap<>();
        env.put("create", "true");
        // locate file system by using the syntax
        // defined in java.net.JarURLConnection
        URI uri = URI.create("jar:file:/codeSamples/zipfs/zipfstest.zip");

       try (FileSystem zipfs = FileSystems.newFileSystem(uri, env)) {
            Path externalTxtFile = Paths.get("/codeSamples/zipfs/SomeTextFile.txt");
            Path pathInZipfile = zipfs.getPath("/SomeTextFile.txt");
            // copy a file into the zip file
            Files.copy( externalTxtFile,pathInZipfile,
                    StandardCopyOption.REPLACE_EXISTING );
        }
    }
}
```

## Zip File System Properties

The following table shows the configurable properties of a zip file system created by the zip file system provider in the Java™ Development Kit (JDK).

| **Property Name** | **Allowed Values**                    | **Property Description**                                     |
| -------------------------------------------------- | ------------------------------------- | ------------------------------------------------------------ |
| create                                             | `true` / `false`                      | The value should be of type `java.lang.String`. The default value is `false`. If the value is `true`, the zip file system provider creates a new zip file if it does not exist. |
| encoding                                           | String indicating the encoding scheme | The value should be of type `java.lang.String`. The value of the property indicates the encoding scheme for the names of the entries in the zip or JAR file. The default value is `UTF-8`. |



## Reference

- [Zip File System Provider](https://docs.oracle.com/javase/7/docs/technotes/guides/io/fsp/zipfilesystemprovider.html)
- [Zip File System Properties](https://docs.oracle.com/javase/7/docs/technotes/guides/io/fsp/zipfilesystemproviderprops.html)
