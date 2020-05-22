# BigInteger

<!-- TOC -->

- [1. Use BigInteger Carefully](#1-use-biginteger-carefully)
  - [1.1. To Binary String](#11-to-binary-string)
    - [1.1.1. 正数](#111-%e6%ad%a3%e6%95%b0)
    - [1.1.2. 负数](#112-%e8%b4%9f%e6%95%b0)
  - [To Byte Array](#to-byte-array)

<!-- /TOC -->

## 1. Use BigInteger Carefully

### 1.1. To Binary String

这里基于`BigInteger`的`toString(int)`方法：

```java
String toString(int radix)
```

#### 1.1.1. 正数

```java
long[] array = {1, 5, 127, 128};
for (long item : array) {
    BigInteger val = BigInteger.valueOf(item);
    String bin_str = val.toString(2);
    String line = String.format("%3s: %s", val, bin_str);
    System.out.println(line);
}
```

实际输出结果：

```text
  1: 1
  5: 101
127: 1111111
128: 10000000
```

有的时候，我预想的输出结果是下面这样的：

```text
  1: 00000001
  5: 00000101
127: 01111111
128: 00000001 0000000
```

#### 1.1.2. 负数

之所以进行这样的测试，是因为我觉得BigInteger会不会以“补数”的行为来显示

```java
long[] array = {-1, -5, -127, -128};
for (long item : array) {
    BigInteger val = BigInteger.valueOf(item);
    String bin_str = val.toString(2);
    String line = String.format("%3s: %s", val, bin_str);
    System.out.println(line);
}
```

输出：

```text
 -1: -1
 -5: -101
-127: -1111111
-128: -10000000
```

### To Byte Array

这里基于`BigInteger`的`toByteArray()`方法：

```java
public byte[] toByteArray()
```

但在，在方法的实现代码中是这样的：

```java
public byte[] toByteArray() {
    int byteLen = bitLength()/8 + 1; // 我觉得，问题出在这里，有时候会多使用一个字节
    byte[] byteArray = new byte[byteLen];

    for (int i=byteLen-1, bytesCopied=4, nextInt=0, intIndex=0; i >= 0; i--) {
        if (bytesCopied == 4) {
            nextInt = getInt(intIndex++);
            bytesCopied = 1;
        } else {
            nextInt >>>= 8;
            bytesCopied++;
        }
        byteArray[i] = (byte)nextInt;
    }
    return byteArray;
}
```

示例代码：

```java
String format = "%s: %s %s";
long[] array = {127, 128, 256};
for (long item : array) {
    BigInteger val = BigInteger.valueOf(item);
    byte[] bytes = val.toByteArray();
    String hex_str = HexUtils.format(bytes, HexFormat.FORMAT_FF_SPACE_FF);
    String bin_str = val.toString(2);
    String line = String.format(format, val, hex_str, bin_str);
    System.out.println(line);
}
```

输出结果：

```text
127: 7F 1111111
128: 00 80 10000000   注意：在这里多占用了一个字节，有时候，我并不想要这个`00`字节
256: 01 00 100000000
```

