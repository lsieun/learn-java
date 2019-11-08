# Parallel data processing and performance

`Stream` interface allows you to process its elements in parallel in a very convenient way: it’s possible to turn a collection into a parallel stream by invoking the method `parallelStream` on the collection source. A parallel stream is a stream that splits its elements into multiple chunks, processing each chunk with a different thread. Thus, you can automatically partition the workload of a given operation on all the cores of your multicore processor and keep all of them equally busy.

## Turning a sequential stream into a parallel one

### Configuring the thread pool used by parallel streams

Looking at the stream’s parallel method, you may wonder where the threads used by the parallel stream come from<sub>问题：thread来自哪儿？回答：来自ForkJoinPool</sub>, how many there are<sub>问题：有多少thread呢？回答：与处理器的数量相同。</sub>, and how you can customize the process.

Parallel streams internally use the default `ForkJoinPool`, which by default has as many threads as you have processors, as returned by `Runtime.getRuntime().availableProcessors()`.

```java
int availableProcessors = Runtime.getRuntime().availableProcessors();
System.out.println("availableProcessors = " + availableProcessors);
```

But you can change the size of this pool using the system property `java.util.concurrent.ForkJoinPool.common.parallelism`, as in the following example:

```java
System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "12");
```

This is a global setting, so it will affect all the parallel streams in your code. Conversely, it currently isn’t possible to specify this value for a single parallel stream. **In general, having the size of the `ForkJoinPool` equal to the number of processors on your machine is a meaningful default, and we strongly suggest that you not modify it unless you have a very good reason for doing so.**

You now have three methods executing exactly the same operation in three different ways (**iterative style**, **sequential reduction**, and **parallel reduction**), so let’s see which is the fastest one!

### Using parallel streams effectively

## The fork/join framework
















