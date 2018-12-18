# Functional interfaces, default and static methods

With **the release of Java 8**, interfaces have obtained new very interesting capabilities: **static methods**, **default methods** and **automatic conversion from lambdas (functional interfaces)**.

<!-- TOC -->

- [1. default methods](#1-default-methods)
- [2. static methods](#2-static-methods)
- [3. functional interfaces](#3-functional-interfaces)

<!-- /TOC -->

## 1. default methods

In previous, we have emphasized on the fact that interfaces in Java can only declare methods but are not allowed to
provide their implementations.

> Java 8以前的情况

With **default methods**, it is not true anymore: an interface can mark a method with the `default` keyword and provide the implementation for it. For example:

```java
package lsieun.advanced.design;

public interface InterfaceWithDefaultMethods {
    void performAction();

    default void performDefaultAction() {
        // Implementation here
    }
}
```

Being **an instance level**, defaults methods could be overridden by each interface implementer. 

## 2. static methods

From now, interfaces may also include static methods, for example:

```java
package lsieun.advanced.design;

public interface InterfacesWithStaticMethods {
    static void createAction() {
        // Implementation here
    }
}
```

One may say that providing an implementation in the interface defeats the whole purpose of contract-based development, but
there are many reasons why these features were introduced into the Java language and no matter how useful or confusing they
are, they are there for you to use.

## 3. functional interfaces

The **functional interfaces** are a different story and they are proven to be very helpful add-on to the language. Basically, the
**functional interface** is **the interface with just a single abstract method** declared in it.

The `Runnable` interface from Java standard library is a good example of this concept:

```java
@FunctionalInterface
public interface Runnable {
    void run();
}
```

The Java compiler treats functional interfaces differently and is able to convert **the lambda function** into the functional interface implementation where it makes sense. Let us take a look on following function definition:

```java
public void runMe(final Runnable r) {
    r.run();
}
```

To invoke this function in Java 7 and below, the implementation of the `Runnable` interface should be provided (for example
using Anonymous classes), but in Java 8 it is enough to pass `run()` method implementation using lambda syntax:

```java
runMe( () -> System.out.println( "Run!" ) );
```

Additionally, the `@FunctionalInterface` annotation hints **the compiler** to **verify that the interface contains only one abstract method** so any changes introduced to the interface in the future will not break this assumption.

```java
package lsieun.advanced.design;

public class FunctionalInterfaceExample {
    public void runMe(final Runnable r) {
        r.run();
    }

    public static void main(String[] args) {
        FunctionalInterfaceExample instance = new FunctionalInterfaceExample();
        instance.runMe(new Runnable() {
            @Override
            public void run() {
                System.out.println("Anonymous Class");
            }
        });

        instance.runMe(() -> System.out.println("Lambda expression"));
    }
}
```
