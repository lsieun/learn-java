# Java Exception

<!-- TOC -->

- [1. Overview](#1-overview)
- [2. Why use Exception Handling?](#2-why-use-exception-handling)
- [3. Exception Hierarchy](#3-exception-hierarchy)
- [4. Reference](#4-reference)

<!-- /TOC -->

## 1. Overview

Handling Exceptions in Java is one of the most basic and fundamental things a developer should know by heart. Sadly, this is often overlooked and the importance of exception handling is underestimated - it's as important as the rest of the code.

## 2. Why use Exception Handling?

When building applications, we're usually working in an **ideal environment** - the file system can provide us with all of the files we request, our internet connection is stable and the JVM can always provide enough memory for our needs.<sub>理想的情况</sub>

Sadly, **in reality**, the environment is far from ideal - the file cannot be found, the internet connection breaks from time to time and the JVM can't provide enough memory and we're left with a daunting `StackOverflowError`.<sub>现实的情况</sub>

If we fail to handle such conditions, the whole application will end up in ruins, and all other code becomes obsolete. Therefore, we must be able to write code that can adapt to such situations.<sub>面对现实的问题，我们要进行处理</sub>

## 3. Exception Hierarchy

All of this just begs the question - what are these exceptions in the eyes of Java and the JVM?

Exceptions are, after all, simply Java objects that extend the `Throwable` interface:

![](images/Exception_Classes.png)

When we talk about exceptional conditions, we are usually referring to one of the three:

- Checked Exceptions
- Unchecked Exceptions / Runtime Exceptions
- Errors

Note: The terms "Runtime" and "Unchecked" are often used interchangeably and refer to the same kind of exceptions.

## 4. Reference

- [Lesson: Exceptions](https://docs.oracle.com/javase/tutorial/essential/exceptions/index.html) 这是Oracle的关于Exception的教程，因此放在第一个
- [Exception Handling in Java: A Complete Guide with Best and Worst Practices](https://stackabuse.com/exception-handling-in-java-a-complete-guide-with-best-and-worst-practices/)
