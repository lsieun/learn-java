# stream Package

## Primitives

In Java we have **a set of parallel types**—for example, `int` and `Integer` —where one is a primitive type and the other a boxed type. **Primitive types** are built into the language and runtime environment as fundamental building blocks; **boxed types** are just normal Java classes that wrap up the primitives.<sub>在Java语言中，对于数的支持有两种数据类型：primitive type和boxed type</sub>

Because **Java generics** are based around **erasing a generic parameter**—in other words, pretending it’s an instance of `Object` —only the **boxed types** can be used as generic arguments. This is why if you want a list of integer values in Java it will always be `List<Integer>` and not `List<int>`.<sub>在Java语言中，Generics只支持boxed type，并不支持primitive type。</sub>

Unfortunately, because **boxed types** are objects, there is a memory overhead to them. For example, although an `int` takes 4 bytes of memory, an `Integer` takes 16 bytes. This gets even worse when you start to look at arrays of numbers, as each element of a primitive array is just the size of the primitive, while each element of a boxed array is actually an in-memory pointer to another object on the Java heap. In the worst case, this might make an `Integer[]` take up nearly **six times** more memory than an `int[]` of the same size.<sub>在JVM运行过程中，从占用内存的角度来说，Integer比int要占用4倍的内存。</sub>

There is also a **computational overhead** when converting from a **primitive type** to a **boxed type**, called **boxing**, and vice versa, called **unboxing**. For algorithms that perform lots of numerical operations, the cost of boxing and unboxing combined with the additional memory bandwidth used by allocated boxed objects can make the code significantly slower.<sub>在JVM运行过程中，boxing和unboxing会影响程序的性能</sub>

As a consequence of these performance overheads, the streams library differentiates between the primitive and boxed versions of some library functions. The `mapToLong` higher-order function and `ToLongFunction`, are examples of this effort. Only the `int`, `long`, and `double` types have been chosen as the focus of the **primitive specialization implementation** in Java 8 because the impact is most noticeable in numerical algorithms.

```java
// mapToLong是方法名
// ToLongFunction是functional interface
// LongStream是specialized versions of Stream
LongStream mapToLong(ToLongFunction<? super T> mapper);
```

There are also **specialized versions of Stream for these primitive types** that prefix the type name, such as `LongStream`. In fact, methods like `mapToLong` don’t return a `Stream`; they return these specialized streams. On the specialized streams, the `map` implementation is also specialized: it takes a function called `LongUnaryOperator`,  which maps a `long` to a `long`. It’s also possible to get back from a **primitive stream** to a **boxed stream** through higher-order function variations such as `mapToObj` and the boxed method, which returns a stream of boxed objects such as Stream<Long> .

```java
public interface LongStream extends BaseStream<Long, LongStream> {
    LongStream map(LongUnaryOperator mapper);

    <U> Stream<U> mapToObj(LongFunction<? extends U> mapper);
}

@FunctionalInterface
public interface LongUnaryOperator {
    long applyAsLong(long operand);
}
```

It’s a good idea to use the **primitive specialized functions** wherever possible because of the performance benefits. You also get additional functionality available on the **specialized streams**. This allows you to avoid having to implement common functionality and to use code that better conveys the intent of numerical operations.

```java
public static void printTrackLengthStatistics(Album album) {
    IntSummaryStatistics trackLengthStats
            = album.getTracks()
            .mapToInt(track -> track.getLength())
            .summaryStatistics();
    System.out.printf("Max: %d, Min: %d, Ave: %f, Sum: %d",
            trackLengthStats.getMax(),
            trackLengthStats.getMin(),
            trackLengthStats.getAverage(),
            trackLengthStats.getSum());
}
```

We map each track to its length, using the primitive specialized `mapToInt` method. Because this method returns an `IntStream`, we can call `summaryStatistics`, which calculates statistics such as the minimum, maximum, average, and sum values on the `IntStream`.

These values are available on all the **specialized streams**, such as `DoubleStream` and `LongStream`. It’s also possible to calculate the individual summary statistics if you don’t need all of them through the min, max, average, and sum methods, which are all also available on all three primitive specialized Stream variants.














