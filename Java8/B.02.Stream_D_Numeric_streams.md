# Numeric streams

<!-- TOC -->

- [1. Primitive stream specializations](#1-primitive-stream-specializations)
  - [1.1. Mapping to a numeric stream](#11-mapping-to-a-numeric-stream)
  - [1.2. Converting back to a stream of objects](#12-converting-back-to-a-stream-of-objects)
  - [1.3. Default values: OptionalInt](#13-default-values-optionalint)
- [2. Numeric ranges](#2-numeric-ranges)

<!-- /TOC -->

You could use the `reduce` method to calculate the sum of the elements of a stream. For example, you can calculate the number of calories in the `menu` as follows:

```java
int calories = menu.stream()
        .map(Dish::getCalories)
        .reduce(0, Integer::sum);
```

The problem with this code is that there’s an insidious **boxing** cost. Behind the scenes each `Integer` needs to be **unboxed** to a primitive before performing the summation.

## 1. Primitive stream specializations

Java 8 introduces **three primitive specialized stream interfaces** to tackle this issue, `IntStream`, `LongStream`, and `DoubleStream`, that respectively specialize the elements of a stream to be `int`, `long`, and `double`—and thereby **avoid hidden boxing costs**.

Each of these interfaces brings new methods to perform common numeric reductions such as `sum` to calculate the sum of a numeric stream and `max` to find the maximum element. In addition, they have methods to **convert back to a stream of objects** when necessary.

The thing to remember is that these specializations aren’t more complexity about streams but instead more complexity caused by boxing—the (efficiency-based) difference between `int` and `Integer` and so on.

### 1.1. Mapping to a numeric stream

The most common methods you’ll use to convert a stream to a specialized version are `mapToInt`, `mapToDouble`, and `mapToLong`. These methods work exactly like the method `map` that you saw earlier but return **a specialized stream** instead of a `Stream<T>`.

```java
int calories = menu.stream()
        .mapToInt(Dish::getCalories)
        .sum();
```

Here, the method `mapToInt` extracts all the calories from each dish (represented as an `Integer`) and returns an `IntStream` as the result (rather than a `Stream<Integer>`). You can then call the `sum` method defined on the `IntStream` interface to calculate the sum of calories!

Note that if the stream were empty, `sum` would return `0` by default. `IntStream` also supports other convenience methods such as `max`, `min`, and `average`.

### 1.2. Converting back to a stream of objects

Similarly, once you have **a numeric stream**, you may be interested in converting it back to **a nonspecialized stream**. For example, the operations of an `IntStream` are restricted to produce primitive integers: the `map` operation of an `IntStream` takes a lambda that takes an `int` and produces an `int` (an `IntUnaryOperator`).

But you may want to produce a different value such as a `Dish`. For this you need to access the operations defined in the Streams interface that are more general. To convert from **a primitive stream** to **a general stream** (each `int` will be boxed to an `Integer`) you can use the method `boxed` as follows:

```java
IntStream intStream = menu.stream().mapToInt(Dish::getCalories);
Stream<Integer> stream = intStream.boxed();
```

### 1.3. Default values: OptionalInt

The sum example was convenient because it has a default value: `0`. But if you want to **calculate the maximum element** in an `IntStream`, you need something different because `0` is a wrong result. How can you differentiate that the stream has no element and that the real maximum is `0`?

Earlier we introduced the `Optional` class, which is a container that indicates the presence or absence of a value. `Optional` can be parameterized with reference types such as `Integer`, `String`, and so on. There’s **a primitive specialized version** of `Optional` as well for **the three primitive stream specializations**: `OptionalInt`, `OptionalDouble`, and `OptionalLong`.

For example, you can find the maximal element of an `IntStream` by calling the `max` method, which returns an `OptionalInt`:

```java
OptionalInt maxCalories = menu.stream()
        .mapToInt(Dish::getCalories)
        .max();
int max = maxCalories.orElse(1);
```

## 2. Numeric ranges

A common use case when dealing with numbers is working with ranges of numeric values. For example, suppose you’d like to generate all numbers between `1` and `100`. Java 8 introduces two static methods available on `IntStream` and `LongStream` to help generate such ranges: `range` and `rangeClosed`. Both methods take the starting value of the range as the first parameter and the end value of the range as the second parameter. But `range` is **exclusive**, whereas `rangeClosed` is **inclusive**.

```java
public static IntStream range(int startInclusive, int endExclusive)
public static IntStream rangeClosed(int startInclusive, int endInclusive)
```

Let’s look at an example:

```java
IntStream evenNumbers = IntStream.rangeClosed(1, 100)
        .filter(n -> n % 2 == 0);
System.out.println(evenNumbers.count());
```
