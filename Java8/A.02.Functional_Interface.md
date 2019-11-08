# Functional interfaces

<!-- TOC -->

- [1. Where and how to use lambdas](#1-where-and-how-to-use-lambdas)
  - [1.1. Functional interface](#11-functional-interface)
  - [1.2. Function descriptor](#12-function-descriptor)
- [2. What about `@FunctionalInterface`?](#2-what-about-functionalinterface)
  - [2.1. functional interface criteria](#21-functional-interface-criteria)
  - [2.2. coincidental interface](#22-coincidental-interface)
  - [2.3. javac check functional interface](#23-javac-check-functional-interface)
- [3. Using functional interfaces](#3-using-functional-interfaces)
- [4. Type checking, type inference, and restrictions](#4-type-checking-type-inference-and-restrictions)
  - [4.1. Type checking](#41-type-checking)
  - [4.2. Special void-compatibility rule](#42-special-void-compatibility-rule)
  - [4.3. Type inference](#43-type-inference)
  - [4.4. Using local variables](#44-using-local-variables)
  - [4.5. Restrictions on local variables](#45-restrictions-on-local-variables)
  - [4.6. Closure](#46-closure)
- [Example](#example)

<!-- /TOC -->

## 1. Where and how to use lambdas

So where exactly can you use lambdas? You can use **a lambda expression** in the context of **a functional interface**.

### 1.1. Functional interface

In a nutshell, **a functional interface** is **an interface** that specifies **exactly one abstract method**.

**Interfaces** can now also have **default methods** (that is, a method with a body that provides some default implementation for a method in case it isn’t implemented by a class). **An interface** is still **a functional interface** if it has **many default methods** as long as it specifies **only one abstract method**.

What can you do with **functional interfaces**? **Lambda expressions** let you provide **the implementation of the abstract method** of **a functional interface** directly inline and treat the whole expression as an instance of a functional interface (more technically speaking, an instance of a concrete implementation of the functional interface).

### 1.2. Function descriptor

**The signature of the abstract method of the functional interface** essentially describes **the signature of the lambda expression**. We call this abstract method a **function descriptor**.

For example, the `Runnable` interface can be viewed as the signature of a function that accepts nothing and returns nothing (`void`) because it has only one abstract method called `run`, which accepts nothing and returns nothing (`void`).

For now, it suffices to understand that a lambda expression can be assigned to a variable or passed to a method expecting a functional interface as argument, provided the lambda expression has the same signature as the abstract method of the functional interface.

```java
public void process(Runnable r){
    r.run();
}

process(() -> System.out.println("This is awesome!!"));
```

## 2. What about `@FunctionalInterface`?

总结：

- （1） 要使用`@FunctionalInterface`，或者成为functional interface要符合一定的“标准”或“规则”
- （2） 虽然有一些接口符合了这些“标准”或“规则”，但它们并不是用来做functional interface的，只是一种“巧合”而已，例如：`java.lang.Comparable`和`java.io.Closeable`.
- （3） 真正要对`@FunctionalInterface`进行检验的过程是由javac来完成的

### 2.1. functional interface criteria

If you explore the new Java API, you’ll notice that functional interfaces are annotated with `@FunctionalInterface`. This annotation is used to indicate that the interface is intended to be a functional interface. The compiler will return a meaningful error if you define an interface using the `@FunctionalInterface` annotation and it isn’t a functional interface.

For example, an error message could be “Multiple non-overriding abstract methods found in interface Foo” to indicate that more than one abstract method is available.

Note that the `@FunctionalInterface` annotation isn’t mandatory, but it’s good practice to use it when an interface is designed for that purpose. You can think of it like the `@Override` notation to indicate that a method is overridden.

### 2.2. coincidental interface

`@FunctionalInterface` is an annotation that should be applied to any interface that is intended to be used as a functional interface.

What does that really mean? Well, there are some interfaces in Java that have only a single method but aren’t normally meant to be implemented by lambda expressions. For example, they might assume that the object has internal state and be interfaces with a single method only coincidentally. A couple of good examples are `java.lang.Comparable` and `java.io.Closeable`.

If a class is `Comparable`, it means there is a defined order between instances, such as alphabetical order for strings. You don’t normally think about functions themselves as being comparable objects because they lack fields and state, and if there are no fields and no state, what is there to sensibly compare?<sub>示例一：Comparable虽然是只有一个方法的接口，但并不是为了用作Functional Interface</sub>

For an object to be `Closeable` it must hold an open resource, such as a file handle that needs to be closed at some point in time. Again, the interface being called cannot be a pure function because closing a resource is really another example of mutating state.<sub>示例二：Closeable虽然也是只有一个方法的接口，但也不是为了用作Functional Interface</sub>

In contrast to `Closeable` and `Comparable`, all the new interfaces introduced in order to provide `Stream` interoperability are expected to be implemented by lambda expressions. They are really there to bundle up blocks of code as data. Consequently, they have the `@FunctionalInterface` annotation applied.

### 2.3. javac check functional interface

Using the annotation compels `javac` to actually check whether the interface meets the criteria for being a functional interface. If the annotation is applied to an `enum`, `class`, or `annotation`, or if the type is an `interface` with more than one single abstract method, then `javac` will generate an error message. This is quite helpful for being able to catch errors easily when refactoring your code.

## 3. Using functional interfaces

## 4. Type checking, type inference, and restrictions

### 4.1. Type checking

**The type of a lambda** is deduced from **the context** in which the lambda is used. The type expected for the lambda expression inside the context is called the **target type**.

### 4.2. Special void-compatibility rule

If **a lambda** has **a statement expression as its body**, it’s compatible with a **function descriptor** that returns `void` (provided the parameter list is compatible too). For example, both of the following lines are legal even though the method `add` of a `List` returns a `boolean` and not void as expected in the `Consumer` context (`T -> void`):

```java
// Predicate has a boolean return
Predicate<String> p = s -> list.add(s);
// Consumer has a void return
Consumer<String> b = s -> list.add(s);
```

By now you should have a good understanding of when and where you’re allowed to use lambda expressions. They can get their **target type** from **an assignment context**, **method invocation context** (parameters and return), and **a cast context**.

```java
// an assignment context
Predicate<String> p = s -> list.add(s);

// method invocation context
process(() -> System.out.println("This is awesome!!"));

public void process(Runnable r){
    r.run();
}
```

### 4.3. Type inference

Note that when a lambda has just **one parameter** whose type is inferred, the **parentheses** surrounding the parameter name can also be omitted.

```java
List<Apple> greenApples = filter(inventory, a -> "green".equals(a.getColor()))
```

The benefits of code readability are more noticeable with lambda expressions that have several parameters. For example, here’s how to create a `Comparator` object:

```java
// Without type inference
Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));

//With type inference
Comparator<Apple> c = (a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
```

Note that sometimes it’s more readable to include the types explicitly and sometimes more readable to exclude them. There’s no rule for which way is better; developers must make their own choices about what makes their code more readable.

### 4.4. Using local variables

All the lambda expressions we’ve shown so far used only their arguments inside their body. But lambda expressions are also allowed to use **free variables** (variables that aren’t the parameters and defined in an outer scope) just like **anonymous classes** can. They’re called **capturing lambdas**.

For example, the following lambda captures the variable `portNumber`:

```java
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
```

Nonetheless, there’s a small twist: there are some restrictions on what you can do with these variables. Lambdas are allowed to capture (that is, to reference in their bodies) **instance variables** and **static variables** without restrictions. But **local variables** have to be explicitly **declared `final`** or are **effectively `final`**. In other words, lambda expressions can capture **local variables** that are assigned to them only once. (Note: capturing an **instance variable** can be seen as capturing the `final local variable this`.)

For example, the following code doesn’t compile because the variable `portNumber` is assigned to twice:

```java
// Error: local variables referenced from lambda expression
// must be final or effective final
int portNumber = 1337;
Runnable r = () -> System.out.println(portNumber);
portNumber = 31337;
```

### 4.5. Restrictions on local variables

You may be asking yourself why local variables have these restrictions. First, there’s a key difference in how instance and local variables are implemented behind the scenes. **Instance variables** are stored on the **heap**, whereas **local variables** live on the **stack**. If a lambda could access the local variable directly and the lambda were used in a thread, then the thread using the lambda could try to access the variable after the thread that allocated the variable had deallocated it. Hence, Java implements access to a **free local variable** as **access to a copy of it** rather than **access to the original variable**. This makes no difference if the **local variable** is assigned to only once—hence the restriction.

Second, this restriction also discourages typical imperative programming patterns that mutate an outer variable.

### 4.6. Closure

You may have heard of the term **closure** and may be wondering whether lambdas meet the definition of a closure. To put it scientifically, **a closure** is **an instance of a function** that can reference **nonlocal variables** of that function **with no restrictions**. For example, a closure could be passed as argument to another function. It could also access and modify variables defined outside its scope.

Now Java 8 lambdas and anonymous classes do something similar to closures: they can be passed as argument to methods and can access variables outside their scope. But they have a restriction: they can’t modify the content of local variables of a method in which the lambda is defined. Those variables have to be implicitly `final`. It helps to think that lambdas close over values rather than variables. As explained previously, this restriction exists because local variables live on the stack and are implicitly confined to the thread they’re in. Allowing capture of mutable local variables opens new thread-unsafe possibilities, which are undesirable (instance variables are fine because they live on the heap, which is shared across threads).

## Example

- `java.lang.Runnable`
- `java.util.function.*`






