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

## Which language features are related to Java generics?

**Features for definition and use of generic types and methods**.

Java Generics support definition and use of generic types and methods. It provides language features for the following purposes: 

- definition of a generic type
- definition of a generic method

---

- type parameters
  - type parameter bounds
- type arguments
  - wildcards
  - wildcard bounds
  - wildcard capture
- instantiation of a generic type = parameterized type
  - raw type
  - concrete instantiation
  - wildcard instantiation
- instantiation of a generic method
  - automatic type inference
  - explicit type argument specification

## Concept

| Term                    | Example                            |
| ----------------------- | ---------------------------------- |
| Parameterized type      | `List<String>`                     |
| Actual type parameter   | `String`                           |
| Generic type            | `List<E>`                          |
| Formal type parameter   | `E`                                |
| Unbounded wildcard type | `List<?>`                          |
| Raw type                | `List`                             |
| Bounded type parameter  | `<E extends Number>`               |
| Recursive type bound    | `<T extends Comparable<T>>`        |
| Bounded wildcard type   | `List<? extends Number>`           |
| Generic method          | `static <E> list<E> asList(E[] a)` |
| Type token              | `String.class`                     |

A class or interface whose declaration has one or more **type parameters** is a **generic class or interface**. Generic classes and interfaces are collectively known as **generic types**.

Each **generic type** defines a set of **parameterized types**, which consist of the class or interface name followed by an angle-bracketed list of **actual type parameters** corresponding to the generic type’s **formal type parameters**.

Finally, each **generic type** defines a **raw type**, which is the name of the generic type used without any accompanying **actual type parameters**.

概念

- generic type
  - type parameter
- parameterized type
  - type argument
    - concrete parameterized type
    - wildcard parameterized type
      - bounded wildcard parameterized type
      - unbounded wildcard parameterized type



乌鸦的故事：上帝要捡最美丽的鸟作禽类的王，乌鸦把孔雀的长毛披在身上，插在尾巴上，到上帝前面去应选，果然为上帝挑中，其它鸟类大怒，把它插上的毛羽都扯下来，依然现出乌鸦的本相。这就是说：披着长头发的，未必就真是艺术家；反过来说，秃顶无发的人，当然未必是学者或思想家，寸草也不生的头脑，你想还会产生什麽旁的东西？这个寓言也不就此结束，这只乌鸦借来的羽毛全给人家拔去，现了原形，老羞成怒，提议索性大家把自己天生的毛羽也拔个干净，到那时候，大家光着身子，看真正的孔雀、天鹅等跟乌鸦有何分别。这个遮羞的方法至少人类是常用的。——钱钟书《读〈伊索寓言〉》

- 元素：`T`, `List<?>`, `List<? extends Number>`, `List<Integer>`, `List`
- class literal
- 创建对象
- 创建数组

Type System: 继承、supertype和subtype；（君臣、父子 = 上与下的关系）

- 三条线：Raw

Type Cast： Raw Type, Unbounded wildcard, Bounded, Concreted

做成表格：怎么会warning，怎么样会error

## 联想

Type Erasure让我想起了《丑八怪 - 薛之谦》中的“如果剧本写好 谁比谁高贵”

