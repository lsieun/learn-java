# Parallel data processing and performance

<!-- TOC -->

- [1. Parallel streams](#1-parallel-streams)
  - [1.1. Turning a sequential stream into a parallel one](#11-turning-a-sequential-stream-into-a-parallel-one)
  - [1.2. Configuring the thread pool used by parallel streams](#12-configuring-the-thread-pool-used-by-parallel-streams)
- [2. Performance analysis of parallel streams](#2-performance-analysis-of-parallel-streams)
  - [2.1. Measuring stream performance](#21-measuring-stream-performance)
  - [2.2. Using more specialized methods](#22-using-more-specialized-methods)
- [3. Using parallel streams correctly and effectively](#3-using-parallel-streams-correctly-and-effectively)
  - [3.1. Using parallel streams correctly](#31-using-parallel-streams-correctly)
  - [3.2. Using parallel streams effectively](#32-using-parallel-streams-effectively)

<!-- /TOC -->

## 1. Parallel streams

`Stream` interface allows you to process its elements in parallel in a very convenient way: it’s possible to turn a collection into a parallel stream by invoking the method `parallelStream` on the collection source. A parallel stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread. Thus, you can automatically partition the workload of a given operation on all the cores of your multicore processor and keep all of them equally busy.

这段理解：

- （1） 生成Stream的Source，可能有多种情况，而Collection只是其中的一种Source
- （2） 针对Collection这种Source，可以直接使用`parallelStream`来获取一个并行的Stream
- （3） 对于其他形式的Source，可以先转换成Stream，再使用`parallel()`获取一个并行的Stream

### 1.1. Turning a sequential stream into a parallel one

Call the method `parallel` on the sequential stream。

```java
Stream.iterate(1L, i -> i + 1)
    .limit(n)
    .parallel() // Turn the stream into a parallel one
    .reduce(0L, Long::sum);
```

Note that, in reality, calling the method `parallel` on a sequential stream doesn’t imply any concrete transformation on the stream itself. Internally, a boolean flag is set to signal that you want to run in parallel all the operations that follow the invocation to parallel.

```java
// java.util.stream.AbstractPipeline
public final S parallel() {
    sourceStage.parallel = true;
    return (S) this;
}
```

Similarly, you can turn **a parallel stream** into **a sequential one** by just invoking the method `sequential` on it.

```java
// java.util.stream.AbstractPipeline
public final S sequential() {
    sourceStage.parallel = false;
    return (S) this;
}
```

Note that you might think that you could achieve finer-grained control over which operations you want to perform in parallel and which one sequentially while traversing the stream by combining these two methods. For example, you could do something like the following:

```java
stream.parallel()
    .filter(...)
    .sequential()
    .map(...)
    .parallel()
    .reduce();
```

**But the last call to `parallel` or `sequential` wins and affects the pipeline globally**. In this example, the pipeline will be executed in parallel because that’s the last call in the pipeline.

### 1.2. Configuring the thread pool used by parallel streams

Looking at the stream’s `parallel` method, you may wonder where the threads used by the parallel stream come from<sub>问题：thread来自哪儿？回答：来自ForkJoinPool</sub>, how many there are<sub>问题：有多少thread呢？回答：与处理器的数量相同。</sub>, and how you can customize the process.

Parallel streams internally use the default `ForkJoinPool`, which by default has as many threads as you have processors, as returned by `Runtime.getRuntime().availableProcessors()`.

```java
int availableProcessors = Runtime.getRuntime().availableProcessors();
System.out.println("availableProcessors = " + availableProcessors);
```

But you can change the size of this pool using the system property `java.util.concurrent.ForkJoinPool.common.parallelism`, as in the following example:

```java
System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
```

This is a global setting, so it will affect all the parallel streams in your code. Conversely, it currently isn’t possible to specify this value for a specific parallel stream<sub>注：这是一个全局的设置，会对所有的parallel stream生效，而不能实现“只对某一个特定parallel stream生效，而对其他的parallel stream生效”</sub>. **In general, having the size of the `ForkJoinPool` equal to the number of processors on your machine is a meaningful default, and we strongly suggest that you not modify it unless you have a very good reason for doing so.**

## 2. Performance analysis of parallel streams

### 2.1. Measuring stream performance

```java
public static long iterativeSum(long n) {
    long result = 0;
    for (long i = 1L; i <= n; i++) {
        result += i;
    }
    return result;
}

public static long sequentialSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .reduce(0L, Long::sum);
}

public static long parallelSum(long n) {
    return Stream.iterate(1L, i -> i + 1)
            .limit(n)
            .parallel() // Turn the stream into a parallel one
            .reduce(0L, Long::sum);
}
```

You now have three methods executing exactly the same operation in three different ways (**iterative style**, **sequential reduction**, and **parallel reduction**), so let’s see which is the fastest one!

We claimed that **the parallelized summing method** should perform better than **the sequential** and **the iterative methods**. Nevertheless, in software engineering guessing is never a good idea! Especially when optimizing performance you should always follow **three golden rules**: **measure, measure, measure**.

```java
public static long measureSumPerformance(Function<Long, Long> adder, long n) {
    long fastest = Long.MAX_VALUE;
    for (int i=0; i< 10; i++) {
        long start = System.nanoTime();
        long sum = adder.apply(n);
        long duration = (System.nanoTime() - start) / 1000000;
        System.out.println("Result: " + sum);
        if (duration < fastest) fastest = duration;
    }
    return fastest;
}
```

Out:

```txt
Iterative sum done in: 3 msecs
Sequential sum done in: 84 msecs
Parallel sum done in: 117 msecs
```

This is quite disappointing: **the parallel version of the summing method** is much slower than **the sequential one**. How can you explain this unexpected result? There are actually two issues mixed together:

- `iterate` generates **boxed objects**, which have to be **unboxed** to numbers before they can be added
- `iterate` is difficult to divide into independent chunks to execute in parallel.

The second issue is particularly interesting because you need to keep a **mental model** that some stream operations are more parallelizable than others. Specifically, the `iterate` operation is hard to split into chunks that can be executed independently because the input of one function application always depends on the result of the previous application.

This demonstrates how parallel programming can be tricky and sometimes counterintuitive. When misused (for example, using an operation that’s not parallel-friendly, like `iterate`) it can actually worsen the overall performance of your programs, so it’s mandatory to understand what happens behind the scenes when you invoke that apparently magic `parallel` method.

### 2.2. Using more specialized methods

So how can you **leverage your multicore processors** and **use the stream to perform a parallel sum in an effective way**? The anwser is `LongStream.rangeClosed`. This method has two benefits compared to `iterate`:

- `LongStream.rangeClosed` works on primitive `long` numbers directly so **there’s no boxing and unboxing overhead**.
- `LongStream.rangeClosed` produces ranges of numbers, which can be easily split into independent chunks. For example, the range `1-20` can be split into `1-5`, `6-10`, `11-15`, and `16-20`.

```java
public static long rangedSum(long n) {
    return LongStream.rangeClosed(1, n)
            .reduce(0L, Long::sum);
}

public static long parallelRangedSum(long n) {
    return LongStream.rangeClosed(1, n)
            .parallel()
            .reduce(0L, Long::sum);
}
```

Out:

```txt
Ranged sum done in: 6 msecs
Parallel range sum done in: 1 msecs
```

This demonstrates that **using the right data structure** and then **making it work in parallel** guarantees the best performance.

Nevertheless, keep in mind that parallelization doesn’t come for free. The parallelization process itself requires you to recursively partition the stream, assign the reduction operation of each substream to a different thread, and then combine the results of these operations in a single value. But **moving data between multiple cores is also more expensive than you might expect**, so it’s important that work to be done in parallel on another core takes longer than the time required to transfer the data from one core to another.

## 3. Using parallel streams correctly and effectively

In general, there are many cases where it isn’t possible or convenient to use parallelization. **But before you use a parallel Stream to make your code faster, you have to be sure that you’re using it correctly; it’s not helpful to produce a result in less time if the result will be wrong**.

### 3.1. Using parallel streams correctly

The main cause of errors generated by misuse of parallel streams is the use of algorithms that **mutate some shared state**.

```java
public class Accumulator {
    public long total = 0;

    public void add(long value) {
        total += value;
    }
}

public static long sideEffectSum(long n) {
    Accumulator accumulator = new Accumulator();
    LongStream.rangeClosed(1, n).forEach(accumulator::add);
    return accumulator.total;
}

public static long sideEffectParallelSum(long n) {
    Accumulator accumulator = new Accumulator();
    LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
    return accumulator.total;
}
```

Out:

```txt
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
Result: 50000005000000
SideEffect sum done in: 4 msecs
Result: 27833724614056
Result: 14406663448171
Result: 9462940602918
Result: 1979817586866
Result: 12451259595800
Result: 8006242937548
Result: 12081044018679
Result: 5239727802862
Result: 8972152908530
Result: 11333684115657
SideEffect parallel sum done in: 1 msecs
```

This time the performance of your method isn’t important: the only relevant thing is that each execution returns a different result, all very distant from the correct value of `50000005000000`.

This is caused by the fact that multiple threads are concurrently accessing the `accumulator` and in particular executing total `+=` value, which, despite its appearance, isn’t an atomic operation. The origin of the problem is that the method invoked inside the `forEach` block has **the side effect of changing the mutable state of an object shared among multiple threads**. It’s mandatory to avoid these kinds of situations if you want to use parallel Streams without incurring similar bad surprises.

Now you know that shared mutable state doesn’t play well with parallel streams and with parallel computations in general. Avoiding shared mutable state ensures that your parallel Stream will produce the right result. Next, we’ll look at some practical advice you can use to figure out when it’s appropriate to use parallel streams to gain performance.

### 3.2. Using parallel streams effectively

In general it’s impossible (and pointless) to try to give any **quantitative hint** on when to use a parallel stream because any suggestion like “use a parallel stream only if you have at least one thousand (or one million or whatever number you want) elements” could be correct for a specific operation running on a specific machine, but it could be completely wrong in an even marginally different context. Nonetheless, it’s at least possible to provide some **qualitative advice** that could be useful when deciding whether it makes sense to use a parallel stream in a certain situation:

- If in doubt, measure. Turning a sequential stream into a parallel one is trivial but not always the right thing to do. As we already demonstrated in this section, a parallel stream isn’t always faster than the corresponding sequential version. Moreover, parallel streams can sometimes work in a counterintuitive way, so the first and most important suggestion when choosing between sequential and parallel streams is to always check their performance with an appropriate benchmark.
- Watch out for **boxing**. Automatic boxing and unboxing operations can dramatically hurt performance. Java 8 includes primitive streams (`IntStream`, `LongStream`, and `DoubleStream`) to avoid such operations, so use them when possible.
- **Some operations** naturally perform worse on a parallel stream than on a sequential stream. In particular, operations such as `limit` and `findFirst` that rely on the order of the elements are expensive in a parallel stream. For example, `findAny` will perform better than `findFirst` because it isn’t constrained to operate in the encounter order. You can always turn an ordered stream into an unordered stream by invoking the method `unordered` on it. So, for instance, if you need N elements of your stream and you’re not necessarily interested in the first N ones, calling `limit` on an unordered parallel stream may execute more efficiently than on a stream with an encounter order (for example, when the source is a `List`).
- Consider **the total computational cost of the pipeline of operations** performed by the stream. With `N` being the number of elements to be processed and `Q` the approximate cost of processing one of these elements through the stream pipeline, the product of `N*Q` gives a rough qualitative estimation of this cost. A higher value for `Q` implies a better chance of good performance when using a parallel stream.
- For **a small amount of data**, choosing a parallel stream is almost never a winning decision. The advantages of processing in parallel only a few elements aren’t enough to compensate for the additional cost introduced by the parallelization process.
- Take into account how well **the data structure** underlying the stream decomposes. For instance, an `ArrayList` can be split much more efficiently than a `LinkedList`, because the first can be evenly divided without traversing it, as it’s necessary to do with the second. Also, **the primitive streams** created with the `range` factory method can be decomposed quickly. Finally, you can get full control of this decomposition process by implementing your own `Spliterator`.
- The **characteristics of a stream**, and how **the intermediate operations** through the pipeline modify them, can change the performance of the decomposition process. For example, a `SIZED` stream can be divided into two equal parts, and then each part can be processed in parallel more effectively, but a `filter` operation can throw away an unpredictable number of elements, making the size of the stream itself unknown.
- Consider whether **a terminal operation** has a cheap or expensive merge step (for example, the `combiner` method in a `Collector`). If this is expensive, then the cost caused by the combination of the partial results generated by each substream can outweigh the performance benefits of a parallel stream.

Stream sources and decomposability

| Source          | Decomposability |
| --------------- | --------------- |
| ArrayList       | Excellent       |
| LinkedList      | Poor            |
| IntStream.range | Excellent       |
| Stream.iterate  | Poor            |
| HashSet         | Good            |
| TreeSet         | Good            |

Finally, we need to emphasize that **the infrastructure** used behind the scenes by parallel streams to execute operations in parallel is **the fork/join framework** introduced in Java 7. The parallel summing example proved that it’s vital to have a good understanding of the parallel stream internals in order to use them correctly, so we’ll investigate in detail the fork/join framework in the next section.
