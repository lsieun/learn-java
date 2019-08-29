# Java GZIPInputStream Tutorial

URL: 

- http://tutorials.jenkov.com/java-zip/gzipinputstream.html

The Java `GZIPInputStream` class (`java.util.zip.GZIPInputStream`) can be used to decompress files that are compressed with the GZIP compression algorithm, for instance via the `GZIPOutputStream` class.


## 1. Creating a GZIPInputStream

To use the Java `GZIPInputStream` you must first create a `GZIPInputStream` instance. Here is an example of creating a `GZIPInputStream` instance:

```java
InputStream     fileInputStream = new FileInputStream("myfile.zip");
GZIPInputStream gzipInputStream = new GZIPInputStream(fileInputStream);
```

The `GZIPInputStream` constructor takes an `InputStream` as parameter. In the example above I passed it a `FileInputStream` connected to a file named `myfile.zip`. The `GZIPInputStream` will then read the GZIP'ed data in the file and decompress it.

## 2. Reading Data From a GZIPInputStream

After creating a `GZIPInputStream` you can read the decompressed data from it just like you would read data from any other `InputStream`. Here is an example of reading data from a Java `GZIPInputStream`:

```java
int data = gzipInputStream.read();
while(data != -1){
    //do something with data
    data = gzipInputStream.read();
}
```

## 3. Closing a GZIPInputStream

When you are finished reading data from the `GZIPInputStream` you should close it using its `close()` method. Here is a Java example of closing a `GZIPInputStream` :

```java
gzipInputStream.close();
```

You can also open a `GZIPInputStream` using the **try-with-resources** construct, so the `GZIPInputStream` is closed automatically when you are done using it. Here is how that looks:

```java
try(GZIPInputStream gzipInputStream = new GZIPInputStream(new FileInputStream("myfile.zip"))) {
    int data = gzipInputStream.read();
    while(data != -1){
        //do something with data
        data = gzipInputStream.read();
    }
}
```

