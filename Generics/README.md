# Generics

**The idea of generics represents the abstraction over types**. It is a very powerful concept that allows to develop abstract algorithms and data structures and to provide concrete types to operate on later.

> 泛型，最核心的思想就是对type进行抽象（abstraction），可以将type作为参数传递。

Interestingly, generics were not present in the early versions of Java and were added along the way only in Java 5 release.(泛型是在Java 5引入的) And since then, it is fair to say that generics revolutionized the way Java programs are being written, delivering much stronger type guaranties and making code significantly safer.

Collections that have all elements of the same type are called **homogeneous**, while the collections that can have elements of potentially different types are called **heterogeneous** (or sometimes “mystery meat collections”).

![](images/generics_mind_map.png)

Use Case:

问题一：对于一个`.class`文件来说，它可以分成多个部分：Magic Number、Version、Constant Pool、Class Info、Fields、Methods和Attributes。那么，泛型`<T>`可以应用到哪些地方？

我的回答：

- Class Info，分成三个地方：this_class、super_class、interfaces
- Fields
- Methods，分成两种：一种是依赖于Class的，另一种是独立的

问题二：Generics和Collections之间是什么样的关系呢？

我的回答：

- 从时间上来说，Collections是在Java 1.2加入的，而Generics是在Java 5加入的，以前的Collections的代码需要经过重构才能支持泛型，而泛型在实现上也是不完全的，它采用了Type Erase的方式来实现对过去的代码的兼容性
- 从我现在的理解来说，Generics是对Type的参数化，现在的Collections和Generics进行结合之后，就是对Collections内的Class、Field和Methods中的Type的进行一种有效的约束。

