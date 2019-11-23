# Stream

<!-- TOC -->

- [1. What are streams?](#1-what-are-streams)
- [2. Summarize](#2-summarize)

<!-- /TOC -->

## 1. What are streams?

**What exactly is a stream?** A short definition is “a **sequence of elements** from a **source** that supports **data processing operations**.”

Let’s break down this definition step by step:

- **Sequence of elements**— Like a collection, a stream provides an interface to a sequenced set of values of a specific element type. Because **collections** are **data structures**, they’re mostly about storing and accessing elements with specific time/space complexities (for example, an `ArrayList` vs. a `LinkedList`). But **streams** are about **expressing computations** such as **filter**, **sorted**, and **map**. **Collections are about data; streams are about computations**.
- **Source**— Streams consume from a data-providing source such as collections, arrays, or I/O resources. Note that generating a stream from an ordered collection preserves the ordering. The elements of a stream coming from a list will have the same order as the list.
- **Data processing operations**— Streams support database-like operations and common operations from functional programming languages to manipulate data, such as `filter`, `map`, `reduce`, `find`, `match`, `sort`, and so on. Stream operations can be executed either sequentially or in parallel.

如何理顺这三个概念之间的关系：

- （1） Sequence of elements，其实是从Collections和Streams的“相似”角度来出发的，但两者不同地方在于：存储内容是不同的。Collections存储的是data，而Streams存储的是computations（换句话说，就是第三个概念Data processing operations）
- （2） Source，其实就是强调“data”部分，有多种不同的来源
- （3） Data processing operations，其实质就是computation

更简单的理解，我觉得是这样的：

- （1） Collections = data
- （2） Streams = data + computation

In addition, **stream operations** have two important characteristics:

- **Pipelining**— Many stream operations return a stream themselves, allowing operations to be chained and form a larger pipeline. This enables certain optimizations, such as **laziness** and **short-circuiting**. A pipeline of operations can be viewed as a database-like query on the data source.<sub>Pipelining的本质就是不同的computation进行组合</sub>
- **Internal iteration**— In contrast to collections, which are iterated explicitly using an iterator, stream operations do the iteration behind the scenes for you.

## 2. Summarize

To summarize, the Streams API in Java 8 lets you write code that’s

- **Declarative**— More concise and readable
- **Composable**— Greater flexibility
- **Parallelizable**— Better performance
