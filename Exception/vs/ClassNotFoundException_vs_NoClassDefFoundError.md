# ClassNotFoundException vs NoClassDefFoundError

<!-- TOC -->

- [1. Introduction](#1-introduction)
- [2. ClassNotFoundException](#2-classnotfoundexception)
  - [2.1. Use Case](#21-use-case)
  - [2.2. Hierarchy](#22-hierarchy)
  - [2.3. Reasons](#23-reasons)
- [3. NoClassDefFoundError](#3-noclassdeffounderror)
  - [3.1. Use Case](#31-use-case)
    - [3.1.1. Example 1](#311-example-1)
    - [3.1.2. Example 2](#312-example-2)
    - [3.1.3. Example 3](#313-example-3)
  - [3.2. Hierarchy](#32-hierarchy)
  - [3.3. Reasons](#33-reasons)
- [4. Difference](#4-difference)
- [5. Summary](#5-summary)
- [6. Reference](#6-reference)

<!-- /TOC -->

## 1. Introduction

`ClassNotFoundException` and `NoClassDefFoundError` both occur when class is not found at runtime. They are related to Java classpath.

这段理解：

- （1） 这两者相同的地方在于：在runtime的时候，在classpath中找不到某个class。
- （2） 这两者不同的地方在于：如果是ClassNotFoundException的情况，在compile time的时候，该class可能出现在classpath中，也可能不出现在classpath中；如果是NoClassDefFoundError的情况，在compile time的时候，则要求该class一定出现在classpath内，只是在runtime的时候才出错。

Both `ClassNotFoundException` and `NoClassDefFoundError` occur when the JVM can not find a requested class on the classpath. Although they look familiar, there are some core differences between these two.

## 2. ClassNotFoundException

`ClassNotFoundException` is a **checked exception** which occurs when an application tries to load a class through its fully-qualified name and can not find its definition on the classpath.

### 2.1. Use Case

This occurs mainly when trying to load classes using `Class.forName()`, `ClassLoader.loadClass()` or `ClassLoader.findSystemClass()`. Therefore, we need to be extra careful of `java.lang.ClassNotFoundException` while working with reflection.

For example, let’s try to load the JDBC driver class without adding necessary dependencies which will get us `ClassNotFoundException`:

```java
@Test(expected = ClassNotFoundException.class)
public void givenNoDrivers_whenLoadDriverClass_thenClassNotFoundException() 
  throws ClassNotFoundException {
      Class.forName("oracle.jdbc.driver.OracleDriver");
}
```

### 2.2. Hierarchy

This exception is a **checked Exception** derived from `java.lang.Exception` class and you need to provide explicit handling for it.

```java
public class ClassNotFoundException extends ReflectiveOperationException {
}

public class ReflectiveOperationException extends Exception {
}
```

### 2.3. Reasons

Most of the time this exception will occur when you try to run application without updating classpath with JAR files.<sub>第一个原因：classpath没有更新</sub>

This exception also occurs when you have two class loaders and if a ClassLoader tries to access a class which is loaded by another classloader in Java.<sub>第二个原因：由两个classloader造成的</sub>

You must be wondering that what actually is classloader in Java. **Java ClassLoader** is a part of Java Runtime Environment that dynamically loads Java classes in JVM(Java Virtual Machine). The Java Runtime System does not need to know about files and files system because of classloaders.

## 3. NoClassDefFoundError

`NoClassDefFoundError` occurs when class was present during compile time and program was compiled and linked successfully but class was not present during runtime.

我的理解：对于compile time和runtime都能理解，但是对于link这个步骤，我不是特别清楚。

### 3.1. Use Case

`NoClassDefFoundError` is a fatal error. It occurs when JVM can not find the definition of the class while trying to:

- Instantiate a class by using the new keyword
- Load a class with a method call

The error occurs when a compiler could successfully compile the class, but Java runtime could not locate the class file. It usually happens when there is an exception while executing a static block or initializing static fields of the class, so class initialization fails.

#### 3.1.1. Example 1

```java
// File: A.java
public class A {

}
```

```java
// File: B.java
public class B {
    public static void main(String[] args) {
        A a = new A();
    }
}
```

从下面的输出来看，是由`ClassNotFoundException`引发的`NoClassDefFoundError`

```bash
$ javac A.java B.java
$ ls
A.class  A.java  B.class  B.java
$ java B
$ rm A.class
$ java B
Exception in thread "main" java.lang.NoClassDefFoundError: A
	at B.main(B.java:3)
Caused by: java.lang.ClassNotFoundException: A
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	... 1 more
```

#### 3.1.2. Example 2

这个例子与上一个例子有一些差别：在上一个例子当中，是在classpath中找不到class文件，而在这个例子中是能找到class文件的，只是在static initalizer当中出现了异常，而后续

```java
// File: A.java
public class A {
    static int data = 1 / 0;
}
```

```java
// File: B.java
public class B {
    public static void main(String[] args) {
        try {
            Class.forName("A");
        }
        catch(Throwable t) {
            // do nothing
        }

        A a = new A();
    }
}
```

```bash
$ javac *.java
$ java B
Exception in thread "main" java.lang.NoClassDefFoundError: Could not initialize class A
	at B.main(B.java:10)
```

#### 3.1.3. Example 3

Let's consider a scenario which is one simple way to reproduce the issue. `ClassWithInitErrors` initialization throws an exception. So, when we try to create an object of `ClassWithInitErrors`, it throws `ExceptionInInitializerError`.

If we try to load the same class again, we get the `NoClassDefFoundError`:

```java
public class ClassWithInitErrors {
    static int data = 1 / 0;
}
```

```java
public class NoClassDefFoundErrorExample {
    public ClassWithInitErrors getClassWithInitErrors() {
        ClassWithInitErrors test;
        try {
            test = new ClassWithInitErrors();
        } catch (Throwable t) {
            System.out.println(t);
        }
        test = new ClassWithInitErrors();
        return test;
    }
}
```

Let us write a test case for this scenario:

```java
@Test(expected = NoClassDefFoundError.class)
public void givenInitErrorInClass_whenloadClass_thenNoClassDefFoundError() {
  
    NoClassDefFoundErrorExample sample
     = new NoClassDefFoundErrorExample();
    sample.getClassWithInitErrors();
}
```

### 3.2. Hierarchy

It is error which is derived from `LinkageError`. Linkage error occurs when a class has some dependencies on another class and latter class changes after compilation of former class.

```java
public class NoClassDefFoundError extends LinkageError {
}

public class LinkageError extends Error {
}
```

### 3.3. Reasons

`NoClassFoundError` is the result of implicit loading of class because of calling a method or accessing a variable from that class.

This error is more difficult to debug and find the reason why this error occurred. So in this case you should always check the classes which are dependent on this class.

## 4. Difference

Sometimes, it can be quite time-consuming to diagnose and fix these two problems. The main reason for both problems is the unavailability of the class file (in the classpath) at runtime.

Let's take a look at few approaches we can consider when dealing with either of these:

- We need to make sure whether class or jar containing that class is available in the classpath. If not, we need to add it<sub>不在classpath中</sub>
- If it's available on application's classpath then most probably classpath is getting overridden. To fix that we need to find the exact classpath used by our application<sub>出于某种原因，classpath被修改了</sub>
- Also, if an application is using multiple class loaders, classes loaded by one classloader may not be available by other class loaders. To troubleshoot it well, it's essential to know **how classloaders work in Java**<sub>可能是classloader造成的</sub>

## 5. Summary

While both of these exceptions are related to **classpath** and **Java runtime unable to find a class at run time**, it's important to note their differences.

Java runtime throws `ClassNotFoundException` while trying to load a class at runtime only and the name was provided during runtime. In the case of `NoClassDefFoundError`, the class was present at compile time, but Java runtime could not find it in Java classpath during runtime.

## 6. Reference

- [ClassNotFoundException vs NoClassDefFoundError](https://www.baeldung.com/java-classnotfoundexception-and-noclassdeffounderror)
