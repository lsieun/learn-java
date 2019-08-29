# zip

URL:

- http://tutorials.jenkov.com/java-zip/index.html
- https://www.tutorialspoint.com/javazip/

`java.util.zip` package provides classes for **reading** and **writing** the standard `ZIP` and `GZIP` file formats. Also includes classes for **compressing** and **decompressing** data using the `DEFLATE` compression algorithm, which is used by the `ZIP` and `GZIP` file formats. Additionally, there are utility classes for computing the `CRC-32` and `Adler-32` checksums of arbitrary input streams.

操作：

- 读＋写
- 压缩、解压缩
- checksum

## How file compression and archiving works

URL: http://www.peazip.org/what-is-zip-file.html

### File archiving definition

**File archiving** means to **combine multiple files together** for easier management of the data (i.e. backup, mail attachments, sharing by FTP, torrent, cloud, or any kind of network service, etc) as for the host filesystem all the data will **be treated as a single file** rather than as multiple ones, eliminating the overhead of handling multiple objects - for each single file.

### File compression definition

**File compression** means to **reduce size of data** on disk encoding it to a smaller output, employing various strategies to efficiently map (most cases of) a larger input to a smaller output, i.e. using statistical analisys to reduce redundancy in input data.

### What is and how a ZIP file works

**ZIP format** is a **lossless data compression and archival format** created in 1989 by Phil Katz.

The ZIP file format specifications were released under public domain and the format had long and lasting success, to the point often "zip" is colloquially(口语地；通俗地；俗称) used for any generic compressed archive, and **many package formats** are based on `deflate` compression and/or same or very similar specs: Java `JAR`/`WAR`/`EAR`, Android `APK`, Apple iOS `IPA` files (iPhone and iPad devices), Microsoft `CAB` and Office compound files.


## 随想

“打成一个压缩包”，这其实是两个过程：（1）把多个文件打成1个文件包；（2）对文件包进行压缩。或者是另一个过程：（1）对每个文件进行压缩；（2）将多个文件打成1个文件包。

**ZIP** is an archive file format that supports **lossless data compression**. A ZIP file may contain one or more files or directories that may have been compressed. The `ZIP` file format permits **a number of compression algorithms**, though `DEFLATE` is the most common.  

ZIP files generally use the file extensions `.zip` or `.ZIP` and the MIME media type `application/zip`.

Zip treats the contents of each file separately when compressing. Each file will have its own compressed stream. There is support within the compression algorithm (typically `DEFLATE`) to identify repeated sections. However, there is no support in Zip to find redundancy between files. That's why there is so much extra space when the content is in multiple files: it's putting the same compressed stream in the file multiple times.

Zip doesn't just store the contents of the file, it also stores **file metadata** like the owning user id, permissions, creation and modification times and so on. If you have one file you have one set of metadata; if you have 10,000 files you have 10,000 sets of metadata.






## core class

- ZipFile (`java.util.zip.ZipFile`)
- GZIPInputStream (`java.util.zip.GZIPInputStream`)
- GZIPOutputStream (`java.util.zip.GZIPOutputStream`)

The Java `ZipFile` class is used to work with ZIP files containing multiple files. This is useful if you need to access the content of JAR or ZIP files.

The Java `GZIPInputStream` and `GZIPOutputStream` classes are used to ZIP compress a single file. For instance, when returning a file from a web server, the web server can choose to ZIP compress it before sending it to the client, to save bandwidth and increase download speed.

By the way, I have used the Java `GZIPOutputStream` to create **a Servlet filter** that zip compresses all outgoing content. You can see all the code here: 
[Java ZIP Compression Servlet Filter](http://tutorials.jenkov.com/java-servlets/gzip-servlet-filter.html)

## checksum

The `java.util.zip.Adler32` class is a class that can be used to compute the Adler-32 checksum of a data stream. An Adler-32 checksum is almost as reliable as a CRC-32 but **can be computed much faster**.





