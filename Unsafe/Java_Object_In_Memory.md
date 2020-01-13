# Java Object In Memory

<!-- TOC -->

- [1. Memory Consumption in Java](#1-memory-consumption-in-java)
  - [1.1. Objects, References and Wrapper Classes](#11-objects-references-and-wrapper-classes)
- [2. Reference](#2-reference)

<!-- /TOC -->

## 1. Memory Consumption in Java

Unlike C/C++ where we can use `sizeof()` method to get an object size in bytes, there's no true equivalent of such method in Java. How can we still get the size of a particular object?

Although there is no `sizeof` operator in Java, we actually don't need one. All primitive types have a standard size, and there are typically no pad or alignment bytes. Still, this isn't always straightforward.

**Although primitives must behave as if they have the official sizes, a JVM can store data in any way it pleases internally, with any amount of padding or overhead**. It can choose to store a `boolean[]` in 64-bit long chunks like `BitSet`, allocate some temporary Objects on the stack or optimize some variables or method calls totally out of existence replacing them with constants, etc… But, as long as the program gives the same result, it's perfectly fine.

Taking also into the account hardware and OS caches impact (our data could be duplicated on every cache level), it means that **we can only roughly predict RAM consumption**.

### 1.1. Objects, References and Wrapper Classes

**Minimum object size is 16 bytes for modern 64-bit JDK** since the object has 12-byte header, padded to a multiple of 8 bytes. In 32-bit JDK, the overhead is 8 bytes, padded to a multiple of 4 bytes.

**References have a typical size of 4 bytes on 32-bit platforms and on 64-bits platforms** with heap boundary less than 32Gb (-Xmx32G), and 8 bytes for this boundary above 32Gb.

This means that a 64-bit JVM usually requires 30-50% more heap space.

Especially relevant is to note that **boxed types, arrays, Strings and other containers like multidimensional arrays are memory costly since they add certain overhead**. For example, when we compare `int` primitive (which consumes only 4 bytes) to the `Integer` object which takes 16 bytes, we see that there is 300% memory overhead.

## 2. Reference

- [JavaOne 2013: Memory Efficient Java](https://www.slideshare.net/cnbailey/memory-efficient-java) 这里是一个PPT，有许多图片
- [How to Get the Size of an Object in Java](https://www.baeldung.com/java-size-of-object)这是baeldung网站的示例，还使用了
- [From Java code to Java heap](https://www.ibm.com/developerworks/library/j-codetoheap/index.html)，这是IBM写的文章，虽然还没有仔细看，但我预感到很有价值，是将来要学习的东西
