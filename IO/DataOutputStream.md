# DataOutputStream

## Intro

This class is a subclass of `FilterOutputStream` that allows you to write **Java primitive data types** in a portable binary format.

> 作者有话说：这句话主要是解释DataOutputStream是继承自FilterOutputStream类，并且实现了DataOutput接口。

```java
public class DataOutputStream extends FilterOutputStream implements DataOutput
```

Create a `DataOutputStream` by specifying the `OutputStream` that is to be filtered in the call to the constructor.

> 作者有话说：这句话是告诉我们如何创建instance。

```java
    public DataOutputStream(OutputStream out) {
        super(out);
    }
```

`DataOutputStream` has methods that output only **primitive types**; use `ObjectOutputStream` to output **object values**.

> 作者有话说：这句话是讲述DataOutputStream和ObjectOutputStream两者的区别。

## Methods

Many of this class’s methods write a single Java primitive type, in binary format, to the output stream.

- `write()` writes a single byte, an array, or a subarray of bytes.
- `flush()` forces any buffered data to be output.
- `size()` returns the number of bytes written so far.
- `writeUTF()` outputs a Java string of Unicode characters using a slightly modified version of the UTF-8 transformation format.

UTF-8 is an ASCII-compatible encoding of Unicode characters that is often used for the transmission and storage of Unicode text.

Except for the `writeUTF()` method, this class is used for **binary output** of data. **Textual output** should be done with `PrintWriter` (or `PrintStream` in Java 1.0).

> 作者有话说：这句话表明DataOutputStream用于输出二进制数据，当然writeUTF()是个例外；而PrintWriter用于输出文本数据。
