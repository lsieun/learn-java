# Java Feature

<!-- TOC -->

- [1. History](#1-history)
- [2. Java 5](#2-java-5)
  - [2.1. Generics](#21-generics)
  - [2.2. Enum](#22-enum)
  - [2.3. Annotation](#23-annotation)
  - [2.4. Varargs](#24-varargs)
- [3. Java 7](#3-java-7)
  - [3.1. try-with-resources](#31-try-with-resources)
  - [3.2. Method Handles](#32-method-handles)
- [4. Java 8](#4-java-8)
  - [4.1. Lambda Expressions](#41-lambda-expressions)
  - [4.2. Streams API](#42-streams-api)
  - [4.3. New Date Time API](#43-new-date-time-api)

<!-- /TOC -->

Java is a general-purpose programming language. It was first made available for public use in 1996. It has evolved a lot during this long period of 20+ years of use. The below infographic shows the version history of Java programming language.

![History-of-Java](History-of-Java.png)

## 1. History

- 1996, Java 1.0
- 2011, Java 7

## 2. Java 5

- `Java 5`: Generics, Enum, Annotation, Java Agent

### 2.1. Generics

The idea of **generics** represents the abstraction over types(提供了对type的抽象). It is a very powerful concept that allows to develop abstract algorithms and data structures(借用generics的概念，可以来编写抽象的算法，或者定义抽象的数据结构) and to provide concrete types to operate on later（在后续使用泛型方法或数据结构的时候，再提供具体的type）. Interestingly, generics were not present in the early versions of Java and were added along the way only in Java 5 release. And since then, it is fair to say that generics revolutionized the way Java programs are being written, delivering **much stronger type guaranties** and making code significantly safer.

### 2.2. Enum

Another two great features introduced into the language as part of Java 5 release along with **generics**: **enums** (or enumerations) and **annotations**. **Enums could be treated as a special type of classes and annotations as a special type of interfaces**.

**The idea of enums** is simple, but quite handy: it represents a fixed, constant set of values. What it means in practice is that enums are often used to design the concepts which have a constant set of possible states. For example, the days of week are a great example of the enums: they are limited to Monday, Tuesday, Wednesday, Thursday, Friday, Saturday and Sunday.

### 2.3. Annotation

From the other side, **annotations are a special kind of metadata** which could be associated with different elements and constructs of the Java language. Interestingly, annotations have contributed a lot into the elimination of boilerplate XML descriptors used in Java ecosystem mostly everywhere. They introduced the new, type-safe and robust way of configuration and customization techniques.

### 2.4. Varargs

Varargs were introduced in Java 5 and provide a short-hand for methods that support an arbitrary number of parameters of one type.

Varargs are straightforward to use. But there're a few rules we have to keep in mind:

- Each method can only have one varargs parameter
- The varargs argument must be the last parameter


[Varargs in Java](https://www.baeldung.com/java-varargs)

## 3. Java 7

### 3.1. try-with-resources

### 3.2. Method Handles

In Java 7, a brand new mechanism for introspection and method access was introduced. This was originally designed for use with dynamic languages, which may need to participate in method dispatch decisions at runtime. To support this at the JVM level, the new `invokedynamic` bytecode was introduced. This bytecode was not used by Java 7 itself, but with the advent of Java 8, it was extensively used in both lambda expressions and the Nashorn JavaScript implementation.<sub>在Java 7中产生，在Java 8中强大</sub>

## 4. Java 8

lambda expressions, method references, streams, and default methods

### 4.1. Lambda Expressions

### 4.2. Streams API

### 4.3. New Date Time API
