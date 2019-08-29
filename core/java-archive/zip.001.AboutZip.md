# About Zip

<!-- TOC -->

- [1. How file compression and archiving works](#1-how-file-compression-and-archiving-works)
  - [1.1. File archiving definition](#11-file-archiving-definition)
  - [1.2. File compression definition](#12-file-compression-definition)
- [2. What is and how a ZIP file works](#2-what-is-and-how-a-zip-file-works)
  - [2.1. What is ZIP](#21-what-is-zip)
  - [2.2. How Zip works](#22-how-zip-works)
- [3. Zip Specification](#3-zip-specification)
- [4. Reference](#4-reference)

<!-- /TOC -->

## 1. How file compression and archiving works

### 1.1. File archiving definition

**File archiving** means to **combine multiple files together** for easier management of the data (i.e. backup, mail attachments, sharing by FTP, torrent, cloud, or any kind of network service, etc) . As for the host filesystem all the data will **be treated as a single file** rather than as multiple ones, eliminating the overhead of handling multiple objects - for each single file.

### 1.2. File compression definition

**File compression** means to **reduce size of data** on disk encoding it to a smaller output, employing various strategies to efficiently map (most cases of) a larger input to a smaller output, i.e. using statistical analisys to reduce redundancy in input data.

## 2. What is and how a ZIP file works

### 2.1. What is ZIP

**ZIP format** is a **lossless data compression and archival format** created in 1989 by Phil Katz.

A ZIP file may contain one or more files or directories that may have been compressed. The `ZIP` file format permits **a number of compression algorithms**, though `DEFLATE` is the most common.

**Many package formats** are based on `deflate` compression and/or same or very similar specs: Java `JAR`/`WAR`/`EAR`, Android `APK`, Apple iOS `IPA` files (iPhone and iPad devices), Microsoft `CAB` and Office compound files.

ZIP files generally use the file extensions `.zip` or `.ZIP` and the MIME media type `application/zip`.

### 2.2. How Zip works

Zip treats the contents of each file separately when compressing. Each file will have its own compressed stream. There is support within the compression algorithm (typically `DEFLATE`) to identify repeated sections. However, there is no support in Zip to find redundancy between files. That's why there is so much extra space when the content is in multiple files: it's putting the same compressed stream in the file multiple times.

In Zip each file is compressed separately. The opposite is '**solid compression**', that is files are compressed together. 7-zip and Rar use solid compression by default. Gzip and Bzip2 can't compress multiple files so Tar is used first, having the same effect as solid compression.

> 随想：“打成一个压缩包”，这其实是两个过程：（1）把多个文件打成1个文件包；（2）对文件包进行压缩。或者是另一个过程：（1）对每个文件进行压缩；（2）将多个文件打成1个文件包。

Zip doesn't just store **the contents of the file**, it also stores **file metadata** like the owning user id, permissions, creation and modification times and so on. If you have one file you have one set of metadata; if you have 10,000 files you have 10,000 sets of metadata.

## 3. Zip Specification

The path stored MUST NOT contain **a drive or device letter**, or **a leading slash**.  All slashes MUST be **forward slashes** '`/`' as opposed to backwards slashes '`\`' for compatibility with Amiga and UNIX file systems etc.

> 第一，对于Windows操作系统，有C盘、D盘这样的符号，不能作为ZIP文件的文件名；另一个需要注意的是Windows的操作系统路径的分隔符是`\`，而在ZIP文件中要求使用`/`。  
> 第二，对于Linux操作系统来说，不能有leading slash。

## 4. Reference

- [ZIP File Format Specification](https://pkware.cachefly.net/webdocs/casestudies/APPNOTE.TXT)
- [Wiki Zip](https://en.wikipedia.org/wiki/Zip_(file_format))
- [How file compression and archiving works](https://www.peazip.org/what-is-zip-file.html)
- [Why is Zip able to compress single file smaller than multiple files with the same content?](https://superuser.com/questions/1013309/why-is-zip-able-to-compress-single-file-smaller-than-multiple-files-with-the-sam)
