# Zip API Intro

`java.util.zip` package provides classes for **reading** and **writing** the standard `ZIP` and `GZIP` file formats. Also includes classes for **compressing** and **decompressing** data using the `DEFLATE` compression algorithm, which is used by the `ZIP` and `GZIP` file formats. Additionally, there are utility classes for computing the `CRC-32` and `Adler-32` checksums of arbitrary input streams.

操作：

- 读＋写
- 压缩、解压缩
- checksum



## core class

- ZipFile (`java.util.zip.ZipFile`)
- GZIPInputStream (`java.util.zip.GZIPInputStream`)
- GZIPOutputStream (`java.util.zip.GZIPOutputStream`)

The Java `ZipFile` class is used to work with ZIP files containing multiple files. This is useful if you need to access the content of JAR or ZIP files.

The Java `GZIPInputStream` and `GZIPOutputStream` classes are used to ZIP compress a single file. For instance, when returning a file from a web server, the web server can choose to ZIP compress it before sending it to the client, to save bandwidth and increase download speed.

By the way, I have used the Java `GZIPOutputStream` to create **a Servlet filter** that zip compresses all outgoing content. You can see all the code here: [Java ZIP Compression Servlet Filter](http://tutorials.jenkov.com/java-servlets/gzip-servlet-filter.html)

## checksum

The `java.util.zip.Adler32` class is a class that can be used to compute the Adler-32 checksum of a data stream. An Adler-32 checksum is almost as reliable as a CRC-32 but **can be computed much faster**.
