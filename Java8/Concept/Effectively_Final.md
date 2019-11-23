# Effectively Final

<!-- TOC -->

- [1. Capturing Lambdas](#1-capturing-lambdas)
- [2. Local Variables in Capturing Lambdas](#2-local-variables-in-capturing-lambdas)
- [3. Static or Instance Variables in Capturing Lambdas](#3-static-or-instance-variables-in-capturing-lambdas)
- [4. Reference](#4-reference)

<!-- /TOC -->

Java 8 gives us **lambdas**, and by association, **the notion of effectively final variables**. Ever wondered why local variables captured in lambdas have to be final or effectively final?

Well, the JLS gives us a bit of a hint when it says “**The restriction to effectively final variables prohibits access to dynamically-changing local variables**, whose capture would likely introduce concurrency problems.”

## 1. Capturing Lambdas

**Lambda expressions can use variables defined in an outer scope**. We refer to these lambdas as **capturing lambdas**. They can capture static variables, instance variables, and local variables, but **only local variables must be final or effectively final**.

In earlier Java versions, we ran into this when an anonymous inner class captured a variable local to the method that surrounded it – we needed to add the `final` keyword before the local variable for the compiler to be happy.

As a bit of syntactic sugar, now the compiler can recognize situations where, while the `final` keyword isn't present, the reference<sub>尽管final关键字可以不用了，但参数文档的本意并没有发生变化</sub> isn't changing at all, meaning it's effectively final. We could say that a variable is effectively final if the compiler wouldn't complain were we to declare it final.

## 2. Local Variables in Capturing Lambdas

Simply put, this won't compile:

```java
Supplier<Integer> incrementer(int start) {
    return () -> start++;
}
```

The basic reason this won't compile is that the lambda is **capturing the value** of `start`, meaning **making a copy of it**. Forcing the variable to be `final` avoids giving the impression that incrementing `start` inside the lambda could actually modify the `start` method parameter.

But, **why does it make a copy?** Well, notice that we are returning the lambda from our method. Thus, the lambda won't get run until after the `start` method parameter gets garbage collected<sub>确切的说，应该是方法的栈桢空间进行释放后，导致变量不再存在了</sub>. Java has to make a copy of start in order for this lambda to live outside of this method.

## 3. Static or Instance Variables in Capturing Lambdas

The examples before can raise some questions if we compare them with the use of **static or instance variables** in a lambda expression.

We can make our first example compile just by converting our `start` variable into an instance variable:

```java
private int start = 0;
 
Supplier<Integer> incrementer() {
    return () -> start++;
}
```

But, why can we change the value of `start` here?

Simply put, it's about where member variables are stored. **Local variables are on the stack, but member variables are on the heap**. Because we're dealing with heap memory, the compiler can guarantee that the lambda will have access to the latest value of `start`.

Generally speaking, **when capturing an instance variable, we could think of it as capturing the final variable `this`**. Anyway, the fact that the compiler doesn't complain doesn't mean that we shouldn't take precautions, especially in multithreading environments.

## 4. Reference

- [Why Do Local Variables Used in Lambdas Have to Be Final or Effectively Final?](https://www.baeldung.com/java-lambda-effectively-final-local-variables)
