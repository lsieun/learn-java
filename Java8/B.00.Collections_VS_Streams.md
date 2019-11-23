# Collections VS Streams

<!-- TOC -->

- [1. iteration process](#1-iteration-process)
- [2. multicore computer](#2-multicore-computer)
  - [2.1. Multithreading is difficult](#21-multithreading-is-difficult)
  - [2.2. Solution](#22-solution)
  - [2.3. Parallelism in Java and no shared mutable state](#23-parallelism-in-java-and-no-shared-mutable-state)
- [3. memory](#3-memory)
- [4. Traversable only once](#4-traversable-only-once)
- [5. Streams and collections philosophically](#5-streams-and-collections-philosophically)

<!-- /TOC -->

## 1. iteration process

**The Streams API** provides a very different way to process data in comparison to **the Collections API**. **Using a collection**, you’re managing the iteration process yourself. You need to iterate through each element one by one using a for-each loop and then process the elements. We call this way of iterating over data **external iteration**. In contrast, **using the Streams API**, you don’t need to think in terms of loops at all. The data processing happens internally inside the library. We call this idea **internal iteration**.

这段理解：第一点不同，如何进行循环

- using Collections API: external iteration
- using Streams API: internal iteration

## 2. multicore computer

As a second pain point of working with collections, think for a second about how you would process the list of transactions if you had a vast number of them; how can you process this huge list? A single CPU wouldn’t be able to process this large amount of data, but you probably have a multicore computer on your desk. Ideally, you’d like to share the work among the different CPU cores available on your machine to reduce the processing time. In theory, if you have eight cores, they should be able to process your data eight times as fast as using one core because they work in parallel.

第二点不同：

- Collections API不能有效的利用multicore CPU的硬件资源

### 2.1. Multithreading is difficult

The problem is that exploiting parallelism by writing **multithreaded code** (using **the Threads API** from previous versions of Java) is difficult. You have to think differently: **threads can access and update shared variables at the same time. As a result, data could change unexpectedly if not coordinated properly**. This model is harder to think about than a step-by-step sequential model.

Traditionally via the keyword `synchronized`, but many subtle bugs arise from its misplacement. **Java 8’s Stream-based parallelism** encourages a functional programming style where `synchronized` is rarely used; it focuses on **partitioning the data** rather than **coordinating access to it**.

### 2.2. Solution

Java 8 also addresses both problems (**boilerplate and obscurity involving processing collections** and **difficulty leveraging multicore**) with **the Streams API** (`java.util.stream`).

**The first design motivator** is that there are **many data processing patterns** that occur over and over again and that would benefit from forming part of a library: **filtering data based on a criterion** (for example, heavy apples), **extracting data** (for example, extracting the weight field from each apple in a list), or **grouping data** (for example, grouping a list of numbers into separate lists of even and odd numbers), and so on.

**The second motivator** is that **such operations can often be parallelized**. For instance, filtering a list on two CPUs could be done by asking one CPU to process the first half of a list and the second CPU to process the other half of the list (this is called the forking step (1)). The CPUs then filter their respective half-lists (2). Finally (3), one CPU would join the two results (this is closely related to how Google searches work so quickly, of course using many more than two processors).

For now, we’ll just say that the new Streams API behaves very similarly to Java’s existing Collections API: both provide access to sequences of data items. But it’s useful for now to keep in mind that **Collections is mostly about storing and accessing data, whereas Streams is mostly about describing computations on data**. **The key point** here is that **Streams allows and encourages the elements within a Stream to be processed in parallel**. Although it may seem odd at first, often the fastest way to filter a `Collection`  is to convert it to a `Stream`, process it in parallel, and then convert it back to a `List`, as exemplified here for both the serial and parallel cases. Again we’ll just say “**parallelism almost for free**” and provide a taste of how you can filter heavy apples from a list sequentially or in parallel using Streams and a lambda expression.

Sequential processing:

```java
import static java.util.stream.Collectors.toList;
List<Apple> heavyApples = inventory.stream()
    .filter((Apple a) -> a.getWeight() > 150)
    .collect(toList());
```

Parallel processing:

```java
import static java.util.stream.Collectors.toList;
List<Apple> heavyApples = inventory.parallelStream()
    .filter((Apple a) -> a.getWeight() > 150)
    .collect(toList());
```

### 2.3. Parallelism in Java and no shared mutable state

People have always said parallelism in Java is difficult, and all this stuff about `synchronized` is error prone. Where’s the magic bullet in Java 8? There are actually **two magic bullets**. First, **the library handles partitioning—breaking down a big stream into several smaller streams to be processed in parallel for you**. Second, **this parallelism almost for free from streams works only if the methods passed to library methods** like filter **don’t interact**, for example, by having mutable shared objects. But it turns out that this restriction feels quite natural as a coder (see, by way of example, our `Apple::isGreenApple` example). Indeed, although the primary meaning of functional in functional programming means “using functions as first class values,” it often has a secondary nuance of “no interaction during execution between components.”

## 3. memory

In coarsest terms, the difference between **collections** and **streams** has to do with **when things are computed**.

**A collection is an in-memory data structure** that holds all the values the data structure currently has—**every element in the collection has to be computed before it can be added to the collection**. (You can add things to, and remove them from, the collection, but at each moment in time, every element in the collection is stored in memory; elements have to be computed before becoming part of the collection.)

By contrast, **a stream is a conceptually fixed data structure** (you can’t add or remove elements from it) **whose elements are computed on demand**. This gives rise to significant programming benefits. The idea is that a user will extract only the values they require from a stream, and these elements are produced—invisibly to the user—only as and when required. This is a form of a producer-consumer relationship. **Another view is that a stream is like a lazily constructed collection**: values are computed when they’re solicited by a consumer (in management speak this is demand-driven, or even just-in-time, manufacturing).

In contrast, **a collection is eagerly constructed** (supplier-driven: fill your warehouse before you start selling). Attempting to construct a collection of all prime numbers would result in a program loop that forever computes a new prime, adding it to the collection, but of course could never finish making the collection, so the consumer would never get to see it.

## 4. Traversable only once

Note that, similarly to **iterators**, **a stream can be traversed only once**. After that a stream is said to be consumed. You can get a new stream from the initial data source to traverse it again just like for an iterator (assuming it’s a repeatable source like a collection; if it’s an I/O channel, you’re out of luck). 

For example, the following code would throw an exception indicating the stream has been consumed:

```java
// 注意：编译器不会报错，运行时才会报错
List<String> list = Arrays.asList("Java8", "In", "Action");
Stream<String> s = list.stream();
s.forEach(System.out::println);
s.forEach(System.out::println);
```

Output:

```txt
Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
```

## 5. Streams and collections philosophically

For readers who like philosophical viewpoints, you can see **a stream** as **a set of values spread out in time**. In contrast, **a collection** is **a set of values spread out in space** (here, computer memory), which all exist at a single point in time—and which you access using an iterator to access members inside a for-each loop.



