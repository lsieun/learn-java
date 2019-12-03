# Building streams

<!-- TOC -->

- [1. Numeric streams](#1-numeric-streams)
- [2. Streams from values](#2-streams-from-values)
- [3. Streams from arrays](#3-streams-from-arrays)
- [4. Streams from Collections](#4-streams-from-collections)
- [5. Streams from files](#5-streams-from-files)
- [6. Streams from functions: creating infinite streams!](#6-streams-from-functions-creating-infinite-streams)
  - [6.1. Iterate](#61-iterate)
  - [6.2. Generate](#62-generate)

<!-- /TOC -->

## 1. Numeric streams

create numerical streams from a range of numbers

```java
public static IntStream range(int startInclusive, int endExclusive)
public static IntStream rangeClosed(int startInclusive, int endInclusive)
```

## 2. Streams from values

You can create a stream with explicit values by using the static method `Stream.of`, which can take any number of parameters.

```java
Stream<String> stream = Stream.of("Java 8 ", "Lambdas ", "In ", "Action");
stream.map(String::toUpperCase).forEach(System.out::println);
```

You can get an empty stream using the `empty` method as follows:

```java
Stream<String> emptyStream = Stream.empty();
```

## 3. Streams from arrays

You can create a stream from an array using the static method `Arrays.stream`, which takes an array as parameter.

```java
int[] numbers = {1, 2, 3, 4};
int sum = Arrays.stream(numbers).sum();
```

## 4. Streams from Collections

Collections in Java 8 support a new `stream` method that returns a stream (the interface definition is available in `java.util.stream.Stream`).

- `Arrays.stream(T[] array)`: 将一个数组转换成Stream对象。
- `Collection.stream()`: 将一个Collection对象转换成Stream对象。

```java
public interface Iterable<T> {
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
}

public interface Collection<E> extends Iterable<E> {
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}

public interface List<E> extends Collection<E> { }

public interface Set<E> extends Collection<E> { }
```


## 5. Streams from files

Java’s NIO API (non-blocking I/O), which is used for I/O operations such as processing a file, has been updated to take advantage of the Streams API. Many static methods in `java.nio.file.Files` return a stream.

For example, a useful method is `Files.lines`, which returns a stream of lines as strings from a given file. Using what you’ve learned so far, you could use this method to find out the number of unique words in a file as follows:

```java
long uniqueWords = 0;
try (Stream<String> lines = Files.lines(Paths.get(filepath), Charset.defaultCharset())) {
    uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
            .distinct()
            .count();

}
catch (IOException ex) {
    ex.printStackTrace();
}
```

You use `Files.lines` to return a stream where each element is a line in the given file. You then split each line into words by calling the `split` method on line. Notice how you use `flatMap` to produce one flattened stream of words instead of multiple streams of words for each line. Finally, you count each distinct word in the stream by chaining the methods `distinct` and `count`.

## 6. Streams from functions: creating infinite streams!

The Streams API provides two static methods to generate a stream from a function: `Stream.iterate` and `Stream.generate`. These two operations let you create what we call an infinite stream: a stream that doesn’t have a fixed size like when you create a stream from a fixed collection. Streams produced by `iterate` and `generate` create values on demand given a function and can therefore calculate values forever! It’s generally sensible to use `limit(n)` on such streams to avoid printing an infinite number of values.

### 6.1. Iterate

Let’s look at a simple example of how to use iterate before we explain it:

```java
Stream.iterate(0, n -> n + 2)
        .limit(10)
        .forEach(System.out::println);
```

### 6.2. Generate

Similarly to the method `iterate`, the method `generate` lets you produce an infinite stream of values computed on demand. But `generate` **doesn’t apply successively a function on each new produced value**. It takes a lambda of type `Supplier<T>` to provide new values.

Let’s look at an example of how to use it:

```java
Stream.generate(Math::random)
.limit(5)
.forEach(System.out::println);
```
