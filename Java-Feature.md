# Java Feature

<!-- TOC -->

- [1. History](#1-history)
- [2. Java 1.4](#2-java-14)
  - [2.1. Assertions](#21-assertions)
- [3. Java 5](#3-java-5)
  - [3.1. Generics](#31-generics)
  - [3.2. Enum](#32-enum)
  - [3.3. Annotation](#33-annotation)
  - [3.4. Varargs](#34-varargs)
- [4. Java 7](#4-java-7)
  - [4.1. Diamond Syntax](#41-diamond-syntax)
  - [4.2. try-with-resources](#42-try-with-resources)
  - [4.3. Method Handles](#43-method-handles)
- [5. Java 8](#5-java-8)
  - [5.1. Lambda Expressions](#51-lambda-expressions)
  - [5.2. Streams API](#52-streams-api)
  - [5.3. New Date Time API](#53-new-date-time-api)

<!-- /TOC -->

Java is a general-purpose programming language. It was first made available for public use in 1996. It has evolved a lot during this long period of 20+ years of use. The below infographic shows the version history of Java programming language.

![History-of-Java](History-of-Java.png)

## 1. History

- 1996, Java 1.0
- 2004, Java 5
- 2006, Java 6
- 2011, Java 7
- 2014, Java 8
- 2017, Java 9

## 2. Java 1.4

### 2.1. Assertions

An assertion is a statement in Java which ensures the correctness of any assumptions which have been done in the program. When an assertion is executed, it is assumed to be true. If the assertion is false, the JVM will throw an Assertion error. It finds it application primarily in the testing purposes. Assertion statements are used along with boolean expressions.

Assertions in Java can be done with the help of the `assert` keyword. There are two ways in which an assert statement can be used.

First Way:

```java
assert expression;
```

Second Way:

```java
assert expression1 : expression2
```

By default, assertions are disabled in Java. In order to enable them we use the following command:

```bash
java -ea HelloWorld
(or)
java -enableassertions HelloWorld
```

```java
public class HelloWorld {
   public static void main(String[] args) {
      int age = 14;
      assert age > 18 : "Cannot Vote";
      System.out.println("The voter's age is " + age);
   }
}
```

Out:

```txt
$ java HelloWorld
The voter's age is 14

$ java -ea HelloWorld
Exception in thread "main" java.lang.AssertionError: Cannot Vote
    at HelloWorld.main(HelloWorld.java:4)
```

## 3. Java 5

- `Java 5`: Generics, Enum, Annotation, Java Agent

### 3.1. Generics

The idea of **generics** represents the abstraction over types(提供了对type的抽象). It is a very powerful concept that allows to develop abstract algorithms and data structures(借用generics的概念，可以来编写抽象的算法，或者定义抽象的数据结构) and to provide concrete types to operate on later（在后续使用泛型方法或数据结构的时候，再提供具体的type）. Interestingly, generics were not present in the early versions of Java and were added along the way only in Java 5 release. And since then, it is fair to say that generics revolutionized the way Java programs are being written, delivering **much stronger type guaranties** and making code significantly safer.

### 3.2. Enum

Another two great features introduced into the language as part of Java 5 release along with **generics**: **enums** (or enumerations) and **annotations**. **Enums could be treated as a special type of classes and annotations as a special type of interfaces**.

**The idea of enums** is simple, but quite handy: it represents a fixed, constant set of values. What it means in practice is that enums are often used to design the concepts which have a constant set of possible states. For example, the days of week are a great example of the enums: they are limited to Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday.

### 3.3. Annotation

From the other side, **annotations are a special kind of metadata** which could be associated with different elements and constructs of the Java language. Interestingly, annotations have contributed a lot into the elimination of boilerplate XML descriptors used in Java ecosystem mostly everywhere. They introduced the new, type-safe and robust way of configuration and customization techniques.

### 3.4. Varargs

Varargs were introduced in Java 5 and provide a short-hand for methods that support an arbitrary number of parameters of one type.

Varargs are straightforward to use. But there're a few rules we have to keep in mind:

- Each method can only have one varargs parameter
- The varargs argument must be the last parameter


[Varargs in Java](https://www.baeldung.com/java-varargs)

## 4. Java 7

### 4.1. Diamond Syntax

```java
List<String> list = new ArrayList<>();
```

### 4.2. try-with-resources

### 4.3. Method Handles

In Java 7, a brand new mechanism for introspection and method access was introduced. This was originally designed for use with dynamic languages, which may need to participate in method dispatch decisions at runtime. To support this at the JVM level, the new `invokedynamic` bytecode was introduced. This bytecode was not used by Java 7 itself, but with the advent of Java 8, it was extensively used in both lambda expressions and the Nashorn JavaScript implementation.<sub>在Java 7中产生，在Java 8中强大</sub>

## 5. Java 8

lambda expressions, method references, streams, and default methods

### 5.1. Lambda Expressions

### 5.2. Streams API

### 5.3. New Date Time API
