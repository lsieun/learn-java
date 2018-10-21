
Java's IO package mostly concerns itself with **the reading of raw data from a source** and **writing of raw data to a destination**. The most typical sources and destinations of data are these:

- Files （硬盘）
- Pipes （JVM中的线程之间的数据交换）
- Network Connections （不同计算机之间的通信，或者是不同进行之间的通信）
- In-memory Buffers (e.g. arrays)  （内存条）
- System.in, System.out, System.error （键盘、屏幕）


## Streams

IO Streams are a core concept in Java IO. A stream is a conceptually endless flow of data. You can either read from a stream or write to a stream. A stream is connected to a data source or a data destination. Streams in Java IO can be either byte based (reading and writing bytes) or character based (reading and writing characters).


## Java IO Purposes and Features

Java IO contains many subclasses of the `InputStream`, `OutputStream`, `Reader` and `Writer` classes. The reason is, that all of these subclasses are addressing various different purposes. That is why there are so many different classes. The purposes addressed are summarized below:

File Access
Network Access
Internal Memory Buffer Access
Inter-Thread Communication (Pipes)
Buffering
Filtering
Parsing
Reading and Writing Text (Readers / Writers)
Reading and Writing Primitive Data (long, int etc.)
Reading and Writing Objects



