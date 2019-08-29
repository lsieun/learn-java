# Java GZIPOutputStream Tutorial

URL: http://tutorials.jenkov.com/java-zip/gzipoutputstream.html


The Java `GZIPOutputStream` class (`java.util.zip.GZIPOutStream`) can be used to GZIP compress data and write it to an `OutputStream`.

## 1. Creating a GZIPOutputStream

Before you can use a `GZIPOutputStream` you must create a `GZIPOutputStream` instance. Here is an example of creating a `GZIPOutputStream`:

```java
FileOutputStream outputStream     = new FileOutputStream("myfile.zip");
GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream);
```

This example creates a `GZIPOutputStream` that GZIP compresses all data you write to it, and write the compressed data to the underlying `OutputStream` (a `FileOutputStream`).

## 2. Writing Data to a GZIPOutputStream

You can write data to a Java `GZIPOutputStream` just like you write data to any other `OutputStream`. Here is an example of writing data to a `GZIPOutputStream`:

```java
byte[] data = ... ; // get data from somewhere.

gzipOutputStream.write(data);
```

When you are done writing data to the `GZIPOutputStream` you have to close it. You close a `GZIPOutputStream` by calling its `close()` method. Here is an example of how that is done:

```java
gzipOutputStream.close();
```

You can also close a `GZIPOutputStream` using the **try-with-resources** construct like this:

```java
try(
    FileOutputStream outputStream     = new FileOutputStream("myfile.zip");
    GZIPOutputStream gzipOutputStream = new GZIPOutputStream(outputStream)
    ) {
        byte[] data = ... ; // get data from somewhere.
        gzipOutputStream.write(data);
}
```

When the **try-with-resources** block exits, the `GZIPOutputStream` and the `FileOutputStream` will be closed.

