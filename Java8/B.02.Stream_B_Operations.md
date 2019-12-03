# Working with streams

<!-- TOC -->

- [1. Filtering and slicing](#1-filtering-and-slicing)
  - [1.1. filter: Filtering with a predicate](#11-filter-filtering-with-a-predicate)
  - [1.2. distinct: Filtering unique elements](#12-distinct-filtering-unique-elements)
  - [1.3. limit: Truncating a stream](#13-limit-truncating-a-stream)
  - [1.4. skip: Skipping elements](#14-skip-skipping-elements)
- [2. Mapping](#2-mapping)
  - [2.1. map: Applying a function to each element of a stream](#21-map-applying-a-function-to-each-element-of-a-stream)
  - [2.2. flatMap: Flattening streams](#22-flatmap-flattening-streams)
- [3. Finding and matching](#3-finding-and-matching)
  - [3.1. Checking to see if a predicate matches at least one element](#31-checking-to-see-if-a-predicate-matches-at-least-one-element)
  - [3.2. Checking to see if a predicate matches all elements](#32-checking-to-see-if-a-predicate-matches-all-elements)
  - [3.3. noneMatch](#33-nonematch)
  - [3.4. Short-circuiting evaluation](#34-short-circuiting-evaluation)
  - [3.5. Finding an element](#35-finding-an-element)
  - [3.6. Finding the first element](#36-finding-the-first-element)
  - [3.7. When to use findFirst and findAny](#37-when-to-use-findfirst-and-findany)
- [4. Reducing](#4-reducing)
  - [4.1. Summing the elements](#41-summing-the-elements)
  - [4.2. No initial value](#42-no-initial-value)
  - [4.3. Maximum and minimum](#43-maximum-and-minimum)
  - [4.4. count](#44-count)

<!-- /TOC -->

## 1. Filtering and slicing

### 1.1. filter: Filtering with a predicate

The Streams interface supports a `filter` method. This operation takes as argument a `predicate` (a function returning a `boolean`) and returns a stream including all elements that match the predicate.

```java
Stream<T> filter(Predicate<? super T> predicate);
```

```java
List<Dish> vegetarianMenu = menu.stream()
        .filter(Dish::isVegetarian)
        .collect(Collectors.toList());
```

### 1.2. distinct: Filtering unique elements

Streams also support a method called `distinct` that returns a stream with unique elements (according to the implementation of the `hashCode` and `equals` methods of the objects produced by the stream).

```java
List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
numbers.stream()
        .filter(i -> i % 2 == 0)
        .distinct()
        .forEach(System.out::println);
```

### 1.3. limit: Truncating a stream

Streams support the `limit(n)` method, which returns another stream that’s no longer than a given size. The requested size is passed as argument to `limit`. If the stream is ordered, the first elements are returned up to a maximum of `n`.

```java
List<Dish> dishes = menu.stream()
        .filter(d -> d.getCalories() > 300)
        .limit(3)
        .collect(Collectors.toList());
```

### 1.4. skip: Skipping elements

Streams support the `skip(n)` method to return a stream that discards the first `n` elements. If the stream has fewer elements than `n`, then an empty stream is returned. Note that `limit(n)` and `skip(n)` are complementary!

```java
List<Dish> dishes = menu.stream()
        .filter(d -> d.getCalories() > 300)
        .skip(2)
        .collect(Collectors.toList());
```

## 2. Mapping

### 2.1. map: Applying a function to each element of a stream

Streams support the method `map`, which takes a function as argument. The function is applied to each element, mapping it into a new element (the word **mapping** is used because it has a meaning similar to **transforming** but with the nuance of “creating a new version of” rather than “modifying”).

```java
List<String> dishNames = menu.stream()
        .map(Dish::getName)
        .collect(Collectors.toList());

List<Integer> dishNameLengths = menu.stream()
        .map(Dish::getName)
        .map(String::length)
        .collect(Collectors.toList());
```

### 2.2. flatMap: Flattening streams

In a nutshell, the `flatMap` method lets you replace each value of a stream with another stream and then concatenates all the generated streams into a single stream.

```java
List<String> words = Arrays.asList("Hello", "World");
List<String> chars = words.stream()
        .map(word -> word.split(""))
        .flatMap(Arrays::stream)
        .distinct()
        .collect(Collectors.toList());
```

## 3. Finding and matching

Another common data processing idiom is finding whether some elements in a set of data match a given property. The Streams API provides such facilities through the `allMatch`, `anyMatch`, `noneMatch`, `findFirst`, and `findAny` methods of a stream.

### 3.1. Checking to see if a predicate matches at least one element

The `anyMatch` method can be used to answer the question “Is there an element in the stream matching the given predicate?”

```java
if (menu.stream().anyMatch(Dish::isVegetarian)) {
    System.out.println("The menu is (somewhat) vegetarian friendly!");
}
```

### 3.2. Checking to see if a predicate matches all elements

The `allMatch` method works similarly to `anyMatch` but will check to see if all the elements of the stream match the given predicate.

```java
boolean isHealthy = menu.stream().allMatch(d -> d.getCalories() < 1000);
```

### 3.3. noneMatch

The opposite of `allMatch` is `noneMatch`. It ensures that no elements in the stream match the given predicate.

```java
boolean isHealthy = menu.stream().noneMatch(d -> d.getCalories() >= 1000);
```

### 3.4. Short-circuiting evaluation

These three operations, `anyMatch`, `allMatch`, and `noneMatch`, make use of what we call **short-circuiting**, a stream version of the familiar Java short-circuiting `&&` and `||` operators.

**Some operations don’t need to process the whole stream to produce a result**. For example, say you need to evaluate a large boolean expression chained with "and" operators. You need only find out that one expression is `false` to deduce that the whole expression will return `false`, no matter how long the expression is; there’s no need to evaluate the entire expression. This is what short-circuiting refers to.

In relation to streams, certain operations such as `allMatch`, `noneMatch`, `findFirst`, and `findAny` don’t need to process the whole stream to produce a result. As soon as an element is found, a result can be produced. Similarly, `limit` is also a short-circuiting operation: the operation only needs to create a stream of a given size without processing all the elements in the stream. Such operations are useful, for example, when you need to deal with streams of infinite size, because they can turn an infinite stream into a stream of finite size.

### 3.5. Finding an element

The `findAny` method returns an arbitrary element of the current stream. It can be used in conjunction with other stream operations.

```java
Optional<Dish> dish = menu.stream()
    .filter(Dish::isVegetarian)
    .findAny();
```

### 3.6. Finding the first element

Some streams have an **encounter order** that specifies the order in which items logically appear in the stream (for example, a stream generated from a `List` or from a sorted sequence of data). For such streams you may wish to find the first element. There’s the `findFirst` method for this, which works similarly to `findAny`.

### 3.7. When to use findFirst and findAny

You may wonder why we have both `findFirst` and `findAny`. The answer is parallelism. Finding the first element is more constraining in parallel. If you don’t care about which element is returned, use `findAny` because it’s less constraining when using parallel streams.

## 4. Reducing

So far, the terminal operations you’ve seen return a `boolean` (`allMatch` and so on), `void`(`forEach`), or an `Optional` object (`findAny` and so on). You’ve also been using collect to combine all elements in a stream into a List.

In this section, you’ll see how you can combine elements of a stream to express more complicated queries such as “Calculate the sum of all calories in the menu,” or “What is the highest calorie dish in the menu?” using the reduce operation. Such queries combine all the elements in the stream repeatedly to produce a single value such as an Integer. These queries can be classified as reduction operations (a stream is reduced to a value). In functional programming-language jargon, this is referred to as a **fold** because you can view this operation as **repeatedly folding a long piece of paper** (your stream) until it forms a small square, which is the result of the **fold** operation.

### 4.1. Summing the elements

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
//int sum = numbers.stream().reduce(0, (a, b) -> a + b);
int sum = numbers.stream().reduce(0, Integer::sum);
int product = numbers.stream().reduce(1, (a, b) -> a * b);
```

### 4.2. No initial value

There’s also an overloaded variant of `reduce` that doesn’t take an **initial value**, but it returns an `Optional` object:

```java
Optional<Integer> sum = numbers.stream().reduce((a, b) -> (a + b));
```

Why does it return an `Optional<Integer>`? Consider the case when the stream contains no elements. The `reduce` operation can’t return a sum because it doesn’t have an initial value. This is why the result is wrapped in an `Optional` object to indicate that the sum may be absent.

### 4.3. Maximum and minimum

```java
Optional<Integer> max = numbers.stream().reduce(Integer::max);
Optional<Integer> min = numbers.stream().reduce(Integer::min);
```

### 4.4. count

```java
int count = menu.stream().map(d -> 1).reduce(0, (a, b) -> a + b);
long count = menu.stream().count();
```
