# Type Safety

<!-- TOC -->

- [1. What does type-safety mean?](#1-what-does-type-safety-mean)
  - [1.1. The Core](#11-the-core)
  - [1.2. The Approach](#12-the-approach)
- [2. How Java Enforces Type Safety](#2-how-java-enforces-type-safety)
- [3. Type Confusion](#3-type-confusion)
- [4. Type Safety and Encapsulation](#4-type-safety-and-encapsulation)
- [5. Reference](#5-reference)

<!-- /TOC -->

## 1. What does type-safety mean?

我的总结：

- 从概念上来说，Type Safety，最本质上，就是对于内存（Memory）的“正确访问”。假如说，在内存的一块区域上存储着一个String类型的对象；按照正常的逻辑，这块内存区域就应该被当作String来进行处理，这就是所谓的“正确访问”；但是，如果将这块内存区域当成Integer来进行处理，就会产生错误，这就是不正确的访问。
- 从实现方法上来说，也就是JVM是怎么实现Type Safety的呢？要理解这个问题，就需要先知道compile time和runtime两个概念。知道了这两个概念之后，就好解释了。它的实现方式就是，在compile time的时候做尽量多的static type check，从而尽量避免在runtime的时候不出现`ClassCastException`。换句话说，编译器（compiler）在实现Type Safety上是绝对的“主角”或“Number One”。

### 1.1. The Core

The Java language is designed to enforce type safety. This means that programs are prevented from accessing memory in inappropriate ways. More specifically, every piece of memory is part of some Java object. Each object has some class. Each class defines both a set of objects and operations to be performed on the objects of that class. **Type safety** means that **a program cannot perform an operation on an object unless that operation is valid for that object**.

这段理解：

- （1） Java language支持type safety。更本质的说，type safety是指Java对于内存的访问控制。
- （2） 一个class，可以生成很多object，而每一个object对应一段内存空间。
- （3） 所谓的type safety，就是对一个object进行合理operation，而不出会出现错误。

### 1.2. The Approach

In Java, a program is considered **type-safe** if it **compiles** without errors and warnings and does not raise any unexpected `ClassCastException` s at **runtime**.<sub>这句话说的好：在compile时，不报错；在runtime时，不抛异常。</sub>

The idea is that a well-formed program enables the **compiler** to perform **enough type checks** based on **static type information** that no unexpected type error can occur at runtime. An unexpected type error in this sense is a `ClassCastException` being raised without any visible `cast` expression in the source code.<sub>核心思想就是：在前期的时候，让compiler做尽量多的type检查工作；等到runtime的时候，减少出错的机率。</sub>

## 2. How Java Enforces Type Safety

Every Java object is stored in some region of the computer's memory. Java labels every object by putting **a class tag** next to the object. **One simple way** to enforce **type safety** is to check the **class tag** of the object before every operation on the object. This will help make sure the object's class allows the operation. This approach is called **dynamic type checking**.

**Though dynamic type checking works, it is inefficient**. The more time a system spends checking **class tags**, the more slowly programs run. To **improve performance**, Java uses **static type checking** whenever it can. Java looks at the program before it runs and carefully tries to determine which way the tag checking operations will come out. This is more complicated, but more efficient than **dynamic type checking**. **If Java can figure out that a particular tag checking operation will always succeed, then there is no reason to do it more than once. The check can safely be removed, speeding up the program**. Similarly, **if Java can figure out that a particular tag checking operation will always fail, then it can generate an error before the program is even loaded**.

The designers of Java carefully crafted the Java language and byte code formats to facilitate static type checking. The **byte code Verifier** is a very effective **static type checker**, eliminating almost all of the tag checking operations from Java programs. The result is a type safe program that runs quite efficiently.

Static type checking has other advantages, too. For example, static type checking can be done at compile time, thus informing the software developer of any type errors before the code is shipped.

## 3. Type Confusion

There is only **one problem** with **Java's static type checking strategy**: **It's complicated**. Though Java's designers obviously got the overall strategy right, there are a great many details that have to be perfect for type safety to be enforced. An error in any of these details would be a tiny but crucial hole in Java's type safety enforcement dike.

A clever cracker who finds such a hole can launch a type confusion attack. Such an attacker could write a Java applet designed to trigger a tiny type enforcement error. The attacker could then create a situation  in which the program has one kind of object but Java thinks that object has some other kind. This seemingly harmless confusion can be exploited to breach Java's security.

## 4. Type Safety and Encapsulation

**Type safety is the cornerstone of Java security**. There is much more to the rest of the edifice, of course, but without type safety the entire building would be unsound.

**Type safety guarantees that programs will not do terrible and dangerous things** such as treating pointers as integers (or vice versa) or falling off the end of an array. These are the sorts of things that make it very easy to write insecure code in C and C++.

**The typing constraints in Java exist to prevent arbitrary access to memory**. This in turn makes it possible for a software module to encapsulate its state. This encapsulation takes the form of allowing a software module to declare that some of its methods and variables may not be accessed by anything outside the code itself. The more control is placed on access points (and the fewer access points there are), the better a module can control access to its state.

It is this idea that permeates the design of the Security Manager. **The VM controls access to potentially dangerous operating system calls by wrapping the calls in an API that invokes a security check before making the call. Only the VM can make a direct system call. All other code must call into the VM through explicit entry points that implement security checks**.

## 5. Reference

- [Securing Java- Type Safety](http://www.securingjava.com/chapter-two/chapter-two-10.html)
