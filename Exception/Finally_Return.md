# finally Return

<!-- TOC -->

- [1. finally mechanism](#1-finally-mechanism)
- [2. finally with return-statement](#2-finally-with-return-statement)
  - [2.1. Runtime: return value](#21-runtime-return-value)
  - [2.2. Compile Time: Warning](#22-compile-time-warning)

<!-- /TOC -->

## 1. finally mechanism

从下面这段代码，可以更清楚的理解finally的实现机制。

```java
public static int test(int a, int b) {
    try {
        return a / b;
    }
    catch (ArithmeticException ex) {
        return 10;
    }
    finally {
        System.out.println("HelloWorld");
    }
}
```

```txt
=== === ===  === === ===  === === ===
0000: iload_0              // 1a     try block
0001: iload_1              // 1b
0002: idiv                 // 6c
0003: istore_2             // 3d
0004: getstatic       #2   // b20002     || java/lang/System.out:Ljava/io/PrintStream;
0007: ldc             #3   // 1203       || HelloWorld
0009: invokevirtual   #4   // b60004     || java/io/PrintStream.println:(Ljava/lang/String;)V
0012: iload_2              // 1c
0013: ireturn              // ac
=== === ===  === === ===  === === ===
0014: astore_2             // 4d     catch block
0015: bipush          10   // 100a
0017: istore_3             // 3e
0018: getstatic       #2   // b20002     || java/lang/System.out:Ljava/io/PrintStream;
0021: ldc             #3   // 1203       || HelloWorld
0023: invokevirtual   #4   // b60004     || java/io/PrintStream.println:(Ljava/lang/String;)V
0026: iload_3              // 1d
0027: ireturn              // ac
=== === ===  === === ===  === === ===
0028: astore          4    // 3a04   finally block
0030: getstatic       #2   // b20002     || java/lang/System.out:Ljava/io/PrintStream;
0033: ldc             #3   // 1203       || HelloWorld
0035: invokevirtual   #4   // b60004     || java/io/PrintStream.println:(Ljava/lang/String;)V
0038: aload           4    // 1904
0040: athrow               // bf

LocalVariableTable:
index  start_pc  length  name_and_type
    0         0      41  a:I
    1         0      41  b:I
    2        15      13  ex:Ljava/lang/ArithmeticException;

Exception Table:
from    to  target  type
   0     4      14  java/lang/ArithmeticException
   0     4      28  All Exceptions(catch_type = 0)
  14    18      28  All Exceptions(catch_type = 0)
  28    30      28  All Exceptions(catch_type = 0)
```

## 2. finally with return-statement

```java
public static int test(int a, int b) {
    try {
        return a / b;
    }
    catch (ArithmeticException ex) {
        return 10;
    }
    finally {
        return 20;
    }
}
```

### 2.1. Runtime: return value

问题：在下面的代码当中，请问`value1`和`value2`的值分别是多少呢？

```java
int value1 = test(10, 0);
int value2 = test(10, 2);
```

答案：`value1`和`value2`的值都是20。

```txt
=== === ===  === === ===  === === ===
0000: iload_0              // 1a
0001: iload_1              // 1b     try block
0002: idiv                 // 6c
0003: istore_2             // 3d
=== === ===  === === ===  === === ===
0004: bipush          20   // 1014   finally block
0006: ireturn              // ac
=== === ===  === === ===  === === ===
0007: astore_2             // 4d     catch block
0008: bipush          10   // 100a
0010: istore_3             // 3e
=== === ===  === === ===  === === ===
0011: bipush          20   // 1014   finally block
0013: ireturn              // ac
=== === ===  === === ===  === === ===
0014: astore          4    // 3a04   finally block
0016: bipush          20   // 1014
0018: ireturn              // ac

LocalVariableTable:
index  start_pc  length  name_and_type
    0         0      19  a:I
    1         0      19  b:I
    2         8       6  ex:Ljava/lang/ArithmeticException;

Exception Table:
from    to  target  type
   0     4       7  java/lang/ArithmeticException
   0     4      14  All Exceptions(catch_type = 0)
   7    11      14  All Exceptions(catch_type = 0)
  14    16      14  All Exceptions(catch_type = 0)
```

### 2.2. Compile Time: Warning

在编译的时候，使用`-Xlint:all`参数会提示“finally clause cannot complete”。

```bash
$ javac -Xlint:all HelloWorld.java
HelloWorld.java:14: warning: [finally] finally clause cannot complete normally
        }
        ^
1 warning
```

在[StackOverflow](https://stackoverflow.com/questions/17481251/finally-block-does-not-complete-normally-eclipse-warning)有一个这样的解释：

```txt
remove "return" statement from it. Final block is considered to be cleanup block, "return" is not generally expected in it.

Generally a "finally" block should never have a "return" statement because it would overwrite other "return-statements" or "Exceptions".
```


