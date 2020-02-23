# Types

<!-- TOC -->

- [1. Type System](#1-type-system)
- [2. Java Compiler](#2-java-compiler)
  - [2.1. Types of Expressions](#21-types-of-expressions)
  - [2.2. Type Checking](#22-type-checking)
  - [2.3. Type Safety](#23-type-safety)
  - [2.4. Consequence](#24-consequence)
  - [2.5. Strong Typing](#25-strong-typing)
  - [2.6. Dynamic typing vs. static typing](#26-dynamic-typing-vs-static-typing)
  - [2.7. Difficulties in Static Type Checking](#27-difficulties-in-static-type-checking)
  - [2.8. What belongs to type checking](#28-what-belongs-to-type-checking)
- [3. Aggregates: arrays](#3-aggregates-arrays)
- [4. Reference](#4-reference)

<!-- /TOC -->

## 1. Type System

The main purpose of a type system is to reduce possibilities for bugs in computer programs by defining interfaces between different parts of a computer program, and then checking that the parts have been connected in a consistent way. This checking can happen statically (at compile time), dynamically (at run time), or as a combination of both.

> The types of the Java programming language are divided into **two categories**: primitive types and reference types. The **primitive types** are the `boolean` type and the numeric types. The numeric types are the integral types `byte`, `short`, `int`, `long`, and `char`, and the floating-point types `float` and `double`. **The reference** types are class types, interface types, and array types. There is also a special null type. An object is a dynamically created instance of a class type or a dynamically created array. The values of a reference type are references to objects. All objects, including arrays, support the methods of class `Object`. String literals are represented by `String` objects.

- types of Java
  - primitive types
    - boolean type
    - numeric types
      - byte
      - short
      - int
      - long
      - char
      - float
      - double
  - reference types
    - class types
    - interface types
    - array types

## 2. Java Compiler

越早发现错误越好：越早发现错误，越早修改错误代码。

### 2.1. Types of Expressions

- If `f` has type `S -> T` and `x` has type `S`, then `f(x)` has type `T`
  - type of `3 div 2` is `int`
  - type of `round(3.5)` is `int`
- **Type error** - using wrongly typed operands in an operation
  - round(“Nancy”)
  - 3.5 div 2
  - “abc”+ 3

### 2.2. Type Checking

**Type checking** is all about ensuring that the program is **type-safe**, minimizing the possibility of **type errors**.

Goal: to find out as early as possible, if each procedure and operator is supplied with the correct type of arguments<sub>type checking的本质就是“操作”和“值”的匹配</sub>

- Type error: when a type is used improperly in a context
- Type checking performed to prevent type errors

Modern PLs often designed to do type checking (as much as possible) during compilation.<sub>在编译时期，进行type checking</sub>

- Compile-time (static)
  - At compile-time, uses declaration information or can infer types from variable uses
- Runtime (dynamic)
  - During execution, checks type of object before doing operations on it
    - Uses type tags to record types of variables
- Combined (compile- and runtime)

Type checking is the process of verifying and enforcing the constraints of types. Type Checking may occur either at compile-time (a static check) or at run-time (dynamic check).

### 2.3. Type Safety

A **type safe** program executes on all inputs without type errors

- Goal of **type checking** is to ensure **type safety**
- **Type safe** does not mean without **errors**

```python
#!/usr/bin/env python3

if __name__ == '__main__':
    str = input("input number: ")
    n = int(str)
    if n > 0:
        y = "ab"
        if n < 0:
            x = y - 5
        print(y)
```

Note that assignment to `x` is never executed so program is **type safe** (but contains an error).

### 2.4. Consequence

When a program is considered not to be type-safe, there is no single standard course of action that happens upon encountering a type error. Many Programming languages throw type errors which halts the run-time or compilation of the program, depending on the language type — static or dynamically typed.

### 2.5. Strong Typing

**Strongly typed PL**: By definition, PL requires all programs to be type checkable

- **Statically strongly typed PL** - compiler allows only programs that can be type checked fully at compiletime
  - Algol68, ML
- **Dynamically strongly typed PL** - Operations include code to check runtime types of operands, if type cannot be determined at compile-time
  - Pascal, Java

### 2.6. Dynamic typing vs. static typing

这个问题，讨论的是一个variable，它的类型到底是不是固定的。

**Dynamically-typed languages** perform type checking at **runtime**, while **statically typed languages** perform type checking at **compile time**. This means that scripts written in dynamically-typed languages (like Groovy) can compile even if they contain errors that will prevent the script from running properly (if at all). If a script written in a statically-typed language (such as Java) contains errors, it will fail to compile until the errors have been fixed.

Statically-typed languages require you to declare the data types of your variables before you use them, while dynamically-typed languages do not.

### 2.7. Difficulties in Static Type Checking

If validity of expression depends not only on **the types of the operands** but on their **values**, **static type checking** cannot be accomplished

### 2.8. What belongs to type checking

对于一个Type来说，它由两部分组成：value和operator。以primitive type的int来说，它包含了2^32个值，同时也包含了`+`、`-`、`*`、`/`等类型的操作；以Class来说，它的value就是各个instance，而它的operator就是Class内定义的方法。因此说，Type由value和operator两部分组成。

那么type checking的本质是对operator的检查。operator本身是一个“函数”有“输入”，也有“输出”，更进一步的说，所谓的type checking就是对于operator的“输入”和“输出”进行检查。

Depending on language, the type checker can prevent

- application of a function to wrong number of arguments, 参数数量不对 （输入个数不对）
- application of integer functions to floats, 参数类型不对（输入类型不对）
- use of undeclared variables in expressions, 使用没有声明参数（输入无效值）
- functions that do not return values, 方法定义了返回值类型，但是没有返回值 （没有输出值）
- division by zero 除以零 （输入参数的值有问题）
- array indices out of bounds, 数组索引不对（输入参数的值有错误）
- nonterminating recursion, 死循环
- sorting algorithms that don't sort...

Languages differ greatly in how strict their static semantics is: none of the things above is checked by all programming languages!

In general, the more there is static checking in the compiler, the less need there is for manual debugging.

## 3. Aggregates: arrays

## 4. Reference

- [Wiki: Type system](https://en.wikipedia.org/wiki/Type_system)
- [Types](https://www.cs.rutgers.edu/~lou/314-f04-slides/topic11-typesA.pdf) 这是一个PDF文档
