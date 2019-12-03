# Stream Operations

<!-- TOC -->

- [1. Stream operations categories](#1-stream-operations-categories)
  - [1.1. Intermediate operations](#11-intermediate-operations)
  - [1.2. Terminal operations](#12-terminal-operations)
  - [1.3. Working with streams](#13-working-with-streams)
- [2. Higher-Order Functions](#2-higher-order-functions)
- [3. Good Use of Lambda Expressions](#3-good-use-of-lambda-expressions)
- [4. Element Ordering](#4-element-ordering)
  - [4.1. the source of the data](#41-the-source-of-the-data)
  - [4.2. operations change encounter order](#42-operations-change-encounter-order)
  - [4.3. eliminating ordering](#43-eliminating-ordering)
- [5. stateless vs. stateful](#5-stateless-vs-stateful)

<!-- /TOC -->

## 1. Stream operations categories

The `Stream` interface in `java.util.stream.Stream` defines many operations. They can be classified into two categories.

- `filter`, `map`, and `limit` can be connected together to form a pipeline.
- `collect` causes the pipeline to be executed and closes it.

Stream operations that can be connected are called **intermediate operations**, and operations that close a stream are called **terminal operations**.

It’s very easy to figure out whether an operation is **eager** or **lazy**: look at what it returns. If it gives you back a `Stream`, it’s **lazy**; if it gives you back another value or `void`, then it’s **eager**.<sub>区分两种operation的方式</sub> This makes sense because the preferred way of using these methods is to form a sequence of lazy operations chained together and then to have a single eager operation at the end that generates your result.<sub>两种operation的常用方式</sub>

I’m sure you’re asking, “Why would we want to have the differentiator between **lazy** and **eager** options?” By waiting until we know more about what result and operations are needed, we can perform the computations more efficiently. It also means that we can string together lots of different operations over our collection and iterate over the collection only once.

### 1.1. Intermediate operations

Intermediate operations such as `filter` or `sorted` return another stream as the return type. This allows the operations to be connected to form a query.

What’s important is that **intermediate operations** don’t perform any processing until a **terminal operation** is invoked on the stream pipeline—they’re lazy. This is because **intermediate operations** can usually be merged and processed into a single pass by the **terminal operation**.

方法列表：

- `filter`
- `map`
- `distinct`
- `limit`
- `sorted`

### 1.2. Terminal operations

Terminal operations produce a result from a stream pipeline. A result is any nonstream value such as a `List`, an `Integer`, or even `void`.

For example, in the following pipeline, `forEach` is a terminal operation that returns `void` and applies a lambda to each dish in the source. Passing `System.out.println` to `forEach` asks it to print every Dish in the stream created from menu:

```java
menu.stream().forEach(System.out::println);
```

方法列表：

- `forEach`
- `findFirst`
- `anyMatch`
- `count`
- `reduce`
- `collect`

### 1.3. Working with streams

To summarize, working with streams in general involves three items:

- A **data source** (such as a collection) to perform a query on
- **A chain of intermediate operations** that form a stream pipeline
- **A terminal operation** that executes the stream pipeline and produces a result

```java
Stream.iterate(0, n -> n+2)
    .limit(10)
    .forEach(System.out::println);
```

Note that this operation produces an infinite stream—the stream doesn’t have an end because values are computed on demand and can be computed forever. We say the stream is unbounded. As we discussed earlier, this is a key difference between a stream and a collection.

## 2. Higher-Order Functions

A **higher-order function** is a function that either **takes another function as an argument** or **returns a function as its result**. It’s very easy to spot a higher-order function: just look at its signature. If a **functional interface** is used as a **parameter** or **return type**, you have a higher-order function.

`map` is a higher-order function because its `mapper` argument is a function. In fact, nearly all the functions that we’ve encountered on the `Stream` interface are higher-order functions.<sub>示例一：map方法，接收一个functional interface类型的parameter</sub>

```java
<R> Stream<R> map(Function<? super T, ? extends R> mapper);
```

`Comparator.comparing()` not only took another function in order to extract an index value, but also returns a new `Comparator`. You might think of a `Comparator` as an object, but it has only a single abstract method, so it’s a **functional interface**.<sub>示例二：comparing方法，接收一个functional interface类型的parameter，同时将functional interface作为return type。</sub>

```java
public static <T, U extends Comparable<? super U>> Comparator<T> comparing(Function<? super T, ? extends U> keyExtractor)
```

## 3. Good Use of Lambda Expressions

**Functions with no side effects** don’t change the state of anything else in the program or the outside world.

The following lambda expression had side effects because it printed some output on the console—an observable side effect of the function.<sub>示例一：它的side effect是改变了System.out的状态</sub>

```java
Stream.of("a", "b", "c")
        .map(str -> {
            System.out.println(str);
            return str.toUpperCase();
        });
```

What about the following example? Here we save away the `event` parameter into a field. This is **a more subtle way of generating a side effect: assigning to variables**. You may not see it directly in the output of your program, but it does change the program’s state.<sub>示例二：它的side effect是改变了类中field的状态</sub>

```java
private ActionEvent lastEvent;
private void registerHandler() {
    button.addActionListener((ActionEvent event) -> {
        this.lastEvent = event;
    });
}
```

Take a look at the assignment to `localEvent` in this code snippet:

```java
ActionEvent localEvent = null;
button.addActionListener(event -> {
    localEvent = event;
});
```

I know this won’t actually compile! That’s actually a deliberate choice on behalf of the designers: **an attempt to encourage people to use lambda expressions to capture values rather than capturing variables**. Capturing values encourages people to write code that is free from side effects by making it harder to do so. Even though local variables don’t need the `final` keyword in order to be used in lambda expressions, they still need to be **effectively final**.

Whenever you pass **lambda expressions** into the **higher-order functions** on the `Stream` interface, you should seek to avoid **side effects**. The only exception to this is the `forEach` method, which is a terminal operation.

## 4. Element Ordering

A `Stream` intuitively presents an order because each element is operated upon, or encountered, in turn. We call this the **encounter order**. How the **encounter order** is defined depends on both **the source of the data** and **the operations performed on the `Stream`**.

### 4.1. the source of the data

When you create a `Stream` from a collection with **a defined order**, the `Stream` has a defined encounter order.

```java
List<Integer> list = Arrays.asList(29, 50, 70, 95);
List<Integer> newList = list.stream().collect(Collectors.toList());
System.out.println(newList);
```

Out:

```txt
[29, 50, 70, 95]
```

If there’s no defined order to begin, the `Stream` produced by that source **doesn’t have a defined order**. A `HashSet` is an example of a collection without a defined ordering

```java
Set<Integer> set = new HashSet<>(Arrays.asList(29, 50, 70, 95));
List<Integer> list = set.stream().collect(Collectors.toList());
System.out.println(list);
```

Out:

```txt
[50, 70, 29, 95]
```

### 4.2. operations change encounter order

The purpose of streams isn’t just to convert from one collection to another; it’s to be able to provide a common set of operations over data. These operations may create an **encounter order** where there wasn’t one to begin with.<sub>有一些operations能够改变encounter order</sub>

```java
Set<Integer> set = new HashSet<>(Arrays.asList(29, 50, 70, 95));
List<Integer> list = set.stream()
        .sorted()
        .collect(Collectors.toList());
System.out.println(list);
```

Out:

```txt
[29, 50, 70, 95]
```

### 4.3. eliminating ordering

Some operations are more expensive on **ordered streams**. This problem can be solved by **eliminating ordering**. To do so, call the stream’s `unordered` method. Most operations, however, such as `filter`, `map`, and `reduce`, can operate very efficiently on ordered streams.

This can cause unexpected behavior, for example, `forEach` provides no guarantees as to encounter order if you’re using parallel streams.

## 5. stateless vs. stateful

There are issues about what **internal state** they need to operate.

Operations like `map` and `filter` take each element from the input stream and produce zero or one result in the output stream. These operations are thus in general **stateless**: **they don’t have an internal state** (assuming the user-supplied lambda or method reference has no internal mutable state).

But operations like `reduce`, `sum`, and `max` need to have **internal state** to accumulate the result. In this case the internal state is small. The **internal state** is of **bounded size** no matter how many elements are in the stream being processed.

By contrast, some operations such as `sorted` or `distinct` seem at first to behave like `filter` or `map`—all take a stream and produce another stream (an intermediate operation), but there’s a crucial difference. Both sorting and removing duplicates from a stream require knowing the previous history to do their job. For example, sorting requires all the elements to be buffered before a single item can be added to the output stream; **the storage requirement of the operation is unbounded**. This can be problematic if the data stream is large or infinite. (What should reversing the stream of all prime numbers do? It should return the largest prime number, which mathematics tells us doesn’t exist.) We call these operations **stateful operations**.
