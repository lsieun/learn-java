# DataInputStream

<!-- TOC -->

- [1. Intro](#1-intro)
- [2. Methods](#2-methods)

<!-- /TOC -->

## 1. Intro

This class is a type of `FilterInputStream` that allows you to read binary representations of **Java primitive data types** in a portable way.

> 作者有话说：这句话主要是解释DataInputStream是继承自FilterInputStream类，并且实现了DataInput接口。

```java
public class DataInputStream extends FilterInputStream implements DataInput
```

Create a `DataInputStream` by specifying the `InputStream` that is to be filtered in the call to the constructor.

> 作者有话说：这句话是告诉我们如何创建instance。

```java
    /**
     * Creates a DataInputStream that uses the specified
     * underlying InputStream.
     *
     * @param  in   the specified input stream
     */
    public DataInputStream(InputStream in) {
        super(in);
    }
```

`DataInputStream` reads only **primitive Java types**; use `ObjectInputStream` to read **object values**.

> 作者有话说：这句话是讲述DataInputStream和ObjectInputStream两者的区别。

## 2. Methods

Many of the methods read and return a single Java primitive type, in binary format, from the stream.

- `readUnsignedByte()` and `readUnsignedShort()` read unsigned values and return them as `int` values, since unsigned byte and short types are not supported in Java.

> 作者有话说：readUnsignedByte()是读取1个字节，而readUnsignedShort()是读取2个字节。

```java
    public final int readUnsignedByte() throws IOException {
        int ch = in.read();
        if (ch < 0)
            throw new EOFException();
        return ch;
    }

    public final int readUnsignedShort() throws IOException {
        int ch1 = in.read();
        int ch2 = in.read();
        if ((ch1 | ch2) < 0)
            throw new EOFException();
        return (ch1 << 8) + (ch2 << 0);
    }
```

- `read()` reads data into an array of bytes, blocking until at least some data is available. By contrast, `readFully()` reads data into an array of bytes, but blocks until all requested data becomes available.

> 作者有话说：readFully()是读取多个字节。暂时忽略掉read()方法，它是父类FilterInputStream定义的方法。

```java
    public final void readFully(byte b[]) throws IOException {
        readFully(b, 0, b.length);
    }

    public final void readFully(byte b[], int off, int len) throws IOException {
        if (len < 0)
            throw new IndexOutOfBoundsException();
        int n = 0;
        while (n < len) {
            int count = in.read(b, off + n, len - n);
            if (count < 0)
                throw new EOFException();
            n += count;
        }
    }
```

- `skipBytes()` blocks until the specified number of bytes have been read and discarded.

```java
    public final int skipBytes(int n) throws IOException {
        int total = 0;
        int cur = 0;

        while ((total<n) && ((cur = (int) in.skip(n-total)) > 0)) {
            total += cur;
        }

        return total;
    }
```

- `readLine()` reads characters from the stream until it encounters a newline, a carriage return, or a newline/carriage return pair. The returned string is not terminated with a newline or carriage return. This method is deprecated as of Java 1.1; This method does not properly convert bytes to characters. As of JDK 1.1, the preferred way to read lines of text is via the `BufferedReader.readLine()` method.

```java
    @Deprecated
    public final String readLine() throws IOException
```

- `readUTF()` reads a string of Unicode text encoded in a slightly modified version of the UTF-8 transformation format.

```java
    public final String readUTF() throws IOException {
        return readUTF(this);
    }
```

UTF-8 is an ASCII-compatible encoding of Unicode characters that is often used for the transmission and storage of Unicode text. This class uses a modified UTF-8 encoding that never contains embedded null characters.

