# What does & 0xff do And MD5 Structure?

URL: https://stackoverflow.com/questions/25838473/what-does-0xff-do-and-md5-structure

## 1. Question

```java
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class JavaMD5 {

    public static void main(String[] args) {
        String passwordToHash = "MyPassword123";
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(passwordToHash.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(generatedPassword);
    }
}
```

其中，有一行代码如下：

```java
sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
```

其中，关键的代码是：

```java
(bytes[i] & 0xff) + 0x100
```

这段代码到底是什么意思呢？

## 2. Answer

Presumably most of the code is clear and the only mystery for you here is this expression:

```java
(bytes[i] & 0xff) + 0x100
```

- The first part: `bytes[i] & 0xff`
- The second part: `+ 0x100`

### 2.1 The first part

```java
bytes[i] & 0xff
```

widens the byte at position `i` to an `int` value with zeros in bit positions `8`-`31`. 

In Java, the `byte` data type is **a signed integer value**, so the widening sign-extends the value. 

Without the `& 0xff`, values greater than `0x7f` would end up as **negative** `int` values. 

- (1) `byte`的取值范围是：`-128~127`

```java
System.out.println(Byte.MAX_VALUE);  // 127
System.out.println(Byte.MIN_VALUE);  // -128
```

- (2) `byte & 0xff`主要是针对**负数**进行处理

```java
System.out.printf("|%6s|%6s|%n", "Before", "After");
System.out.printf("|%6d|%6d|%n", 0, (0 & 0xff));
System.out.printf("|%6d|%6d|%n", 1, (1 & 0xff));
System.out.printf("|%6d|%6d|%n", -1, (-1 & 0xff));
System.out.printf("|%6d|%6d|%n", 127, (127 & 0xff));
System.out.printf("|%6d|%6d|%n", -127, (-127 & 0xff));
System.out.printf("|%6d|%6d|%n", -128, (-128 & 0xff));
```

Output:

```txt
|Before| After|
|     0|     0|
|     1|     1|
|    -1|   255| # 前后值有变化
|   127|   127|
|  -127|   129| # 前后值有变化
|  -128|   128| # 前后值有变化
```

对于`0`和**正数**，`byte & 0xff`是不产生影响的；对于**负数**，`byte & 0xff`对结果有影响。

- (3) 对于`byte`和`byte & 0xff`，它们对应的`bit`上的值是不对的，而显示的结果值可能相同，也可能不同，因为`byte`和`Integer`的取值范围是不一样的。

```java
    public static void test () {
        System.out.printf("|%10s|%10s|%10s|%8s|%n", "Byte", "Binary", "& 0xff", "Integer");

        int[] array = new int[] {0, 1, -1, 127, -127, -128};
        for (int i=0; i<array.length; i++) {
            byte b = (byte)array[i];
            System.out.printf("|%10d|%10s|%10s|%8s|%n", b, getBinaryString(b), getBinaryString((b & 0xff)), (b & 0xff));
        }
    }

    public static String getBinaryString(byte b) {
        StringBuilder sb = new StringBuilder();
        sb.append((b >> 7) & 0x01);
        sb.append((b >> 6) & 0x01);
        sb.append((b >> 5) & 0x01);
        sb.append((b >> 4) & 0x01);
        sb.append(" ");
        sb.append((b >> 3) & 0x01);
        sb.append((b >> 2) & 0x01);
        sb.append((b >> 1) & 0x01);
        sb.append((b >> 0) & 0x01);
        return sb.toString();
    }

    public static String getBinaryString(int i) {
        StringBuilder sb = new StringBuilder();
        sb.append((i >> 7) & 0x01);
        sb.append((i >> 6) & 0x01);
        sb.append((i >> 5) & 0x01);
        sb.append((i >> 4) & 0x01);
        sb.append(" ");
        sb.append((i >> 3) & 0x01);
        sb.append((i >> 2) & 0x01);
        sb.append((i >> 1) & 0x01);
        sb.append((i >> 0) & 0x01);
        return sb.toString();
    }
```

Output:

```txt
|      Byte|    Binary|    & 0xff| Integer|
|         0| 0000 0000| 0000 0000|       0|
|         1| 0000 0001| 0000 0001|       1|
|        -1| 1111 1111| 1111 1111|     255|
|       127| 0111 1111| 0111 1111|     127|
|      -127| 1000 0001| 1000 0001|     129|
|      -128| 1000 0000| 1000 0000|     128|
```

观察上面的结果，`bit`位上的值是没有变化的，但是**同样的`bit`位的排列**，在`byte`和`Integer`上可能是不一样的取值。

`byte & 0xff`的取值范围是：`0~255`。

### 2.2 The second part

由上面的部分，我们可以得知：`byte & 0xff`的取值范围是：`0~255`。

使用`Integer.toString(..., 16)`时，在`0~255`范围内，其中，`0~15`表示成十六进制时，只有一位十六进制数`0~f`；而`16~255`表示成十六进制时，由两位十六进制数表示`10~ff`。我们的目标是统一生成两位数的十六进制数。

```java
System.out.println(Integer.toString(0, 16));   // 0
System.out.println(Integer.toString(15, 16));  // f
System.out.println(Integer.toString(16, 16));  // 10
System.out.println(Integer.toString(255, 16)); // ff
```

现在我们借助于`+ 0xff`，再看一下生成的16进制数，发现生成的十六进制数都是三位表示。而我们想要的是两位十六进制数，因此只要去掉第1个十六进制数就可以了。

```java
System.out.println(0x100); // 256
System.out.println(Integer.toString(0 + 0x100, 16));  // 100
System.out.println(Integer.toString(15 + 0x100, 16)); // 10f
System.out.println(Integer.toString(16 + 0x100, 16)); // 110
System.out.println(Integer.toString(255 + 0x100, 16));// 1ff
```

The reason for adding `0x100` and then stripping off the `1` (done by the `substring(1)` call, which takes the substring starting at position `1` through the end) is to guarantee **two hex digits** in the end result. Otherwise, **byte values** below `0x10` would end up as **one-character strings** when converted to **hex**.

It's debatable whether all that has better performance (it certainly isn't clearer) than:

```java
sb.append(String.format("%02x", bytes[i]));
```

## 3. Summary

It's a really messy(凌乱的；难以应付的) way of translating to a hexadecimal string.

- `& 0xFF` performs **a binary AND**, causing the returning value to be between `0` and `255` (which a byte always is anyway)
- `+ 0x100` adds `256` to the result to ensure the result is always `3` digits
- `Integer.toString(src, 16)` converts the integer to a string with radix 16 (hexadecimal)
- Finally `.substring(1)` strips the first character (the 1 from step 2)

So, this is a very elaborate and obfuscated way to convert a byte to an always 2-character hexadecimal string.






