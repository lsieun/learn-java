# The fork/join framework

<!-- TOC -->

- [1. Working with RecursiveTask](#1-working-with-recursivetask)
- [2. Best practices for using the fork/join framework](#2-best-practices-for-using-the-forkjoin-framework)
- [3. Work stealing](#3-work-stealing)
- [4. Next](#4-next)

<!-- /TOC -->

The fork/join framework was designed to recursively split a parallelizable task into smaller tasks and then combine the results of each subtask to produce the overall result. It’s an implementation of the `ExecutorService` interface, which distributes those subtasks to **worker threads** in a **thread pool**, called `ForkJoinPool`.

Let’s start by exploring **how to define a task and subtasks**.

## 1. Working with RecursiveTask

To submit tasks to this pool, you have to create a subclass of `RecursiveTask<R>`, where `R` is the type of the result produced by the parallelized task (and each of its subtasks) or of `RecursiveAction` if the task returns no result (it could be updating other nonlocal structures, though). To define `RecursiveTasks` you need only implement its single abstract method, `compute`:

```java
protected abstract R compute();
```

This method defines both **the logic of splitting the task at hand into subtasks** and **the algorithm to produce the result of a single subtask** when it’s no longer possible or convenient to further divide it. For this reason an implementation of this method often resembles the following pseudocode:

```txt
if (task is small enough or no longer divisible) {
    compute task sequentially
} else {
    split task in two subtasks
    call this method recursively possibly further splitting each subtask
    wait for the completion of all subtasks
    combine the results of each subtask
}
```

In general there are **no precise criteria** for deciding whether a given task should be further divided or not, but there are various heuristics that you can follow to help you with this decision.

```java
import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {
    public static final long THRESHOLD = 10000;

    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();

        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightValue = rightTask.compute();
        Long leftValue = leftTask.join();

        return leftValue + rightValue;
    }

    private long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }

    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1,n).toArray();
        ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
        return new ForkJoinPool().invoke(task);
    }

}
```

Note that in a real-world application, it doesn’t make sense to use more than one `ForkJoinPool`. For this reason, what you typically should do is instantiate it only once and keep this instance in a `static` field, **making it a singleton**, so it could be conveniently reused by any part of your software. Here, to create it you’re using its default no-argument constructor, meaning that you want to allow the pool to use all the processors available to the JVM. More precisely, this constructor will use the value returned by `Runtime.availableProcessors` to determine the number of threads used by the pool. Note that the `availableProcessors` method, despite its name, in reality returns the number of **available cores**, including any virtual ones due to hyperthreading.

## 2. Best practices for using the fork/join framework

Even though the fork/join framework is relatively easy to use, unfortunately it’s also easy to misuse. Here are a few best practices to leverage it effectively:

从method的角度来说：

- Invoking the `join` method on a task blocks the caller until the result produced by that task is ready. For this reason, it’s necessary to call it after the computation of both subtasks has been started. Otherwise, you’ll end up with a slower and more complex version of your original sequential algorithm because every subtask will have to wait for the other one to complete before starting.
- The `invoke` method of a `ForkJoinPool` shouldn’t be used from within a `RecursiveTask`. Instead, you should always call the methods `compute` or `fork` directly; only sequential code should use `invoke` to begin parallel computation.
- Calling the `fork` method on a subtask is the way to schedule it on the `ForkJoinPool`. It might seem natural to invoke it on both the left and right subtasks, but this is less efficient than just directly calling `compute` on one of them. Doing this allows you to reuse the same thread for one of the two subtasks and avoid the overhead caused by the unnecessary allocation of a further task on the pool.

从Debug的角度来说：

- Debugging a parallel computation using the fork/join framework can be tricky. In particular, it’s ordinarily quite common to browse a stack trace in your favorite IDE to discover the cause of a problem, but this can’t work with a fork-join computation because the call to compute occurs in a different thread than the conceptual caller, which is the code that called `fork`.

从mental model的角度来说：

- As you’ve discovered with parallel streams, **you should never take for granted that a computation using the fork/join framework on a multicore processor is faster than the sequential counterpart**. We already said that a task should be decomposable into several independent subtasks in order to be parallelizable with a relevant performance gain. All of these subtasks should take longer to execute than forking a new task; one idiom is to put I/O into one subtask and computation into another, thereby overlapping computation with I/O.
- Moreover, you should consider other things when comparing the performance of the sequential and parallel versions of the same algorithm. Like any other Java code, the fork/join framework needs to be “warmed up,” or executed, a few times before being optimized by the JIT compiler. This is why **it’s always important to run the program multiple times before to measure its performance**.
- Also be aware that optimizations built into the compiler could unfairly give an advantage to the sequential version (for example, by performing dead code analysis—removing a computation that’s never used).

从task的角度来说：

The fork/join splitting strategy deserves one last note: you must **choose the criteria** used to decide if a given subtask should be further split or is small enough to be evaluated sequentially.

## 3. Work stealing

**Forking a quite large number of fine-grained tasks is in general a winning choice**. This is because ideally you want to partition the workload of a parallelized task in such a way that each subtask takes exactly the same amount of time, keeping all the cores of your CPU equally busy. Unfortunately, especially in cases closer to real-world scenarios, **the time taken by each subtask can dramatically vary** either due to the use of an inefficient partition strategy or because of unpredictable causes like slow access to the disk or the need to coordinate the execution with external services.

The fork/join framework works around this problem with a technique called **work stealing**. In practice, this means that the tasks are more or less evenly divided on all the threads in the `ForkJoinPool`. Each of these threads holds **a doubly linked queue of the tasks** assigned to it, and as soon as it completes a task it pulls another one from the head of the queue and starts executing it. For the reasons we listed previously, one thread might complete all the tasks assigned to it much faster than the others, which means its queue will become empty while the other threads are still pretty busy. In this case, instead of becoming idle, the thread randomly chooses a queue of a different thread and “steals” a task, taking it from the tail of the queue. This process continues until all the tasks are executed, and then all the queues become empty. That’s why **having many smaller tasks**, instead of only a few bigger ones, **can help in better balancing the workload among the worker threads**.

More generally, this work-stealing algorithm is used to redistribute and balance the tasks among the worker threads in the pool.

## 4. Next

It should now be clear how a stream can use the fork/join framework to process its items in parallel, but there’s still **one missing ingredient**. In this section, we analyzed an example where **you explicitly developed the logic to split an array of numbers** into **multiple tasks**. Nevertheless, you didn’t have to do anything similar when you used the parallel streams at the beginning of this chapter, and this means that there must be **an automatic mechanism splitting the stream** for you. This new automatic mechanism is called the `Spliterator`, and we explore it in the next section.

这段理解：

- （1） Stream的parallism是基于Fork/Join Framework的
- （2） 在Fork/Join Framework中，主要分成两个操作来处理：第一，将大任务分割成小任务；第二，在work thread中执行小任务
- （3） 在使用Stream的parallism时，并没有让我们对数据进行分割，那么它是怎么完成的呢？答案是Spliterator。
