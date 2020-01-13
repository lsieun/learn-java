# Java Object Layout

<!-- TOC -->

- [1. Concept](#1-concept)
  - [1.1. Word Size](#11-word-size)
- [2. JOL类库](#2-jol%e7%b1%bb%e5%ba%93)
  - [2.1. Maven依赖](#21-maven%e4%be%9d%e8%b5%96)
  - [2.2. 基本使用](#22-%e5%9f%ba%e6%9c%ac%e4%bd%bf%e7%94%a8)
- [3. 对象布局](#3-%e5%af%b9%e8%b1%a1%e5%b8%83%e5%b1%80)
  - [3.1. No Field](#31-no-field)
  - [3.2. Primitive Type](#32-primitive-type)
    - [3.2.1. boolean Field](#321-boolean-field)
    - [3.2.2. char Field](#322-char-field)
    - [3.2.3. int Field](#323-int-field)
    - [3.2.4. long Field](#324-long-field)
    - [3.2.5. int and long Field](#325-int-and-long-field)
    - [3.2.6. boolean, int and long Field](#326-boolean-int-and-long-field)
  - [3.3. Object Type](#33-object-type)
    - [3.3.1. String Field](#331-string-field)
    - [3.3.2. Double Field](#332-double-field)
  - [3.4. Array Type](#34-array-type)
    - [3.4.1. int array](#341-int-array)
    - [3.4.2. Object Type](#342-object-type)
- [对象布局：继承](#%e5%af%b9%e8%b1%a1%e5%b8%83%e5%b1%80%e7%bb%a7%e6%89%bf)
  - [int Field](#int-field)
  - [long Field](#long-field)
  - [boolean Field](#boolean-field)
- [4. Reference](#4-reference)

<!-- /TOC -->

## 1. Concept

### 1.1. Word Size

"Word size" refers to the number of bits processed by a computer's CPU in one go (these days, typically 32 bits or 64 bits).

## 2. JOL类库

### 2.1. Maven依赖

```java
<dependency>
    <groupId>org.openjdk.jol</groupId>
    <artifactId>jol-core</artifactId>
    <version>0.9</version>
</dependency>
```

### 2.2. 基本使用

```java
import org.openjdk.jol.vm.VM;
import org.openjdk.jol.vm.VirtualMachine;

public class HelloWorld {
    public static void main(String[] args) {
        VirtualMachine vm = VM.current();
        String details = vm.details();
        System.out.println(details);
    }
}
```

输出：（我还不理解下面的内容表达的含义）

```txt
# Running 64-bit HotSpot VM.
# Using compressed oop with 3-bit shift.
# Using compressed klass with 3-bit shift.
# Objects are 8 bytes aligned.
# Field sizes by type: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
# Array element sizes: 4, 1, 1, 2, 2, 4, 4, 8, 8 [bytes]
```

## 3. 对象布局

```java
import org.openjdk.jol.info.ClassLayout;

public class B {
    public static void main(String[] args) {
        print_class_and_object(Example.class, new Example());
    }

    public static <T> void print_class_and_object(Class<T> clazz, T t) {
        ClassLayout classLayout = ClassLayout.parseClass(clazz);
        String result = classLayout.toPrintable(t);
        System.out.println(result);
    }
}
```

### 3.1. No Field

```java
class Example {

}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 4 bytes external = 4 bytes total
```

### 3.2. Primitive Type

#### 3.2.1. boolean Field

```java
class Example {
    private boolean flag;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     1   boolean Example.flag                              false
     13     3           (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
```

#### 3.2.2. char Field

```java
class Example {
    private char ch;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     2   char Example.ch                                 
     14     2        (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 2 bytes external = 2 bytes total
```

#### 3.2.3. int Field

```java
class Example {
    private int value;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4    int Example.value                             0
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

#### 3.2.4. long Field

```java
class Example {
    private long value;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4        (alignment/padding gap)                  
     16     8   long Example.value                             0
Instance size: 24 bytes
Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
```

#### 3.2.5. int and long Field

```java
class Example {
    private long longValue;
    private int intValue;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4    int Example.intValue                          0
     16     8   long Example.longValue                         0
Instance size: 24 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

#### 3.2.6. boolean, int and long Field

```java
class Example {
    private boolean flag;
    private long longValue;
    private int intValue;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4       int Example.intValue                          0
     16     8      long Example.longValue                         0
     24     1   boolean Example.flag                              false
     25     7           (loss due to the next object alignment)
Instance size: 32 bytes
Space losses: 0 bytes internal + 7 bytes external = 7 bytes total
```

### 3.3. Object Type

#### 3.3.1. String Field

```java
class Example {
    private String str;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
      0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4                    (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4   java.lang.String Example.str                               null
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

#### 3.3.2. Double Field

```java
class Example {
    private Double obj;
}
```

输出：

```txt
lsieun.memory.a_class.Example object internals:
 OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
      0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4                    (object header)                           05 c1 00 f8 (00000101 11000001 00000000 11111000) (-134168315)
     12     4   java.lang.Double Example.obj                               null
Instance size: 16 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

### 3.4. Array Type

#### 3.4.1. int array

```java
print_class_and_object(int[].class, new int[5]);
```

输出：

```txt
[I object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           6d 01 00 f8 (01101101 00000001 00000000 11111000) (-134217363)
     12     4        (object header)                           05 00 00 00 (00000101 00000000 00000000 00000000) (5)
     16     0    int [I.<elements>                             N/A
     16    24        (loss due to the next object alignment)
Instance size: 40 bytes
Space losses: 0 bytes internal + 24 bytes external = 24 bytes total
```

#### 3.4.2. Object Type

```java
print_class_and_object(Object[].class, new Object[3]);
```

输出：

```txt
[Ljava.lang.Object; object internals:
 OFFSET  SIZE               TYPE DESCRIPTION                               VALUE
      0     4                    (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4                    (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4                    (object header)                           3b 23 00 f8 (00111011 00100011 00000000 11111000) (-134208709)
     12     4                    (object header)                           03 00 00 00 (00000011 00000000 00000000 00000000) (3)
     16     0   java.lang.Object Object;.<elements>                        N/A
     16    16                    (loss due to the next object alignment)
Instance size: 32 bytes
Space losses: 0 bytes internal + 16 bytes external = 16 bytes total
```

## 对象布局：继承

### int Field

```java
class A {
    private int a;
}

class B extends A {
    private int b;
}

class C extends B {
    private int c;
}
```

输出：

```txt
lsieun.memory.a_class.C object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           81 c1 00 f8 (10000001 11000001 00000000 11111000) (-134168191)
     12     4    int A.a                                       0
     16     4    int B.b                                       0
     20     4    int C.c                                       0
Instance size: 24 bytes
Space losses: 0 bytes internal + 0 bytes external = 0 bytes total
```

### long Field

```java
class A {
    private long a;
}

class B extends A {
    private long b;
}

class C extends B {
    private long c;
}
```

输出：

```txt
lsieun.memory.a_class.C object internals:
 OFFSET  SIZE   TYPE DESCRIPTION                               VALUE
      0     4        (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4        (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4        (object header)                           81 c1 00 f8 (10000001 11000001 00000000 11111000) (-134168191)
     12     4        (alignment/padding gap)                  
     16     8   long A.a                                       0
     24     8   long B.b                                       0
     32     8   long C.c                                       0
Instance size: 40 bytes
Space losses: 4 bytes internal + 0 bytes external = 4 bytes total
```

### boolean Field

```java
class A {
    private boolean a;
}

class B extends A {
    private boolean b;
}

class C extends B {
    private boolean c;
}
```

输出：

```txt
lsieun.memory.a_class.C object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 00 00 00 (00000001 00000000 00000000 00000000) (1)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           81 c1 00 f8 (10000001 11000001 00000000 11111000) (-134168191)
     12     1   boolean A.a                                       false
     13     3           (alignment/padding gap)                  
     16     1   boolean B.b                                       false
     17     3           (alignment/padding gap)                  
     20     1   boolean C.c                                       false
     21     3           (loss due to the next object alignment)
Instance size: 24 bytes
Space losses: 6 bytes internal + 3 bytes external = 9 bytes total
```

## 4. Reference

- [JVM基础 -- JOL使用教程 1](http://zhongmingmao.me/2016/07/01/jvm-jol-tutorial-1/)
- [JVM基础 -- JOL使用教程 2](http://zhongmingmao.me/2016/07/02/jvm-jol-tutorial-2/)
- [JVM基础 -- JOL使用教程 3](http://zhongmingmao.me/2016/07/03/jvm-jol-tutorial-3/)

