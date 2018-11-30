# Java ZipFile Tutorial

URL: 

- http://tutorials.jenkov.com/java-zip/zipfile.html


The Java `ZipFile` class (`java.util.zip.ZipFile`) can be used to read files from **a ZIP file**. 

## 1. Creating a ZipFile Instance

In order to use the Java `ZipFile` class you must first create a `ZipFile` instance. Here is an example of creating a Java `ZipFile` instance:

```java
ZipFile zipFile = new ZipFile("d:\\data\\myzipfile.zip");
```

As you can see, the `ZipFile` class takes a single parameter in its constructor. This parameter is the path to the ZIP file to open.


## 2. Getting a ZipEntry

Each file in the ZIP file is represented by a `ZipEntry` (`java.util.zip.ZipEntry`). To extract a file from the ZIP file you can call the method `getEntry()` method on the `ZipFile` class. Here is an example of calling `getEntry()`:

```java
ZipEntry zipEntry = zipFile.getEntry("file1.txt");
```

This example gets a `ZipEntry` representing the file `file1.txt` which is contained in the ZIP file.

If the file you want to extract is located in one or more directories inside the ZIP file, include the directories in the path, like this:

```java
ZipEntry zipEntry = zipFile.getEntry("dir/subdir/file1.txt");
```

### Reading the File

To read the file represented by a `ZipEntry` you can obtain an `InputStream` from the `ZipFile` like this:

```java
ZipEntry zipEntry = zipFile.getEntry("dir/subdir/file1.txt");

InputStream inputStream = this.zipFile.getInputStream(zipEntry);
```

The `InputStream` obtained from the `getInputStream()` of the `ZipFile` class can be read like any other Java `InputStream`.

## Listing All Entries in a ZipFile

You can list all entries contained in a `ZipFile` using the `entries()` method. Here is an example of calling the `ZipFile.entries()`:

```java
Enumeration<? extends ZipEntry> entries = zipFile.entries();
```

You can iterate the Enumeration returned by the entries() method like this:

```java
Enumeration<? extends ZipEntry> entries = zipFile.entries();

while(entries.hasMoreElements()){
    ZipEntry entry = entries.nextElement();
    if(entry.isDirectory()){
        System.out.println("dir  : " + entry.getName());
    } else {
        System.out.println("file : " + entry.getName());
    }
}
```

## Unzipping All Entries in ZipFile

There is no easy way to unzip all entries of a `ZipFile`. You will have to do that yourself. To make it easier for you, I will show you an example of the code needed to unzip all entries in a Java `ZipFile`. Here is the code:

```java
package com.nanosai.studio.updater;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class FileUnzipper {

    private String zipFileDir  = null;
    private String zipFileName = null;
    private String unzipDir    = null;

    public FileUnzipper(String zipFileDir, String zipFileName, String unzipDir) {
        this.zipFileDir  = zipFileDir;
        this.zipFileName = zipFileName;
        this.unzipDir    = unzipDir;
    }

    public void unzip() {
        String zipFilePath = this.zipFileDir + File.separator + this.zipFileName;
        try{
            System.out.println("zipFilePath = " + zipFilePath);
            ZipFile zipFile = new ZipFile(zipFilePath);

            Enumeration<? extends ZipEntry> entries = zipFile.entries();

            while(entries.hasMoreElements()){
                ZipEntry entry = entries.nextElement();
                if(entry.isDirectory()){
                    System.out.print("dir  : " + entry.getName());
                    String destPath = this.unzipDir + File.separator + entry.getName();
                    System.out.println(" => " + destPath);
                    File file = new File(destPath);
                    file.mkdirs();
                } else {
                    String destPath = this.unzipDir + File.separator + entry.getName();

                    try(InputStream inputStream = zipFile.getInputStream(entry);
                        FileOutputStream outputStream = new FileOutputStream(destPath);
                    ){
                        int data = inputStream.read();
                        while(data != -1){
                            outputStream.write(data);
                            data = inputStream.read();
                        }
                    }
                    System.out.println("file : " + entry.getName() + " => " + destPath);
                }
            }
        } catch(IOException e){
            throw new RuntimeException("Error unzipping file " + zipFilePath, e);
        }
    }
}
```









