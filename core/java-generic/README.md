# Generics

**The idea of generics represents the abstraction over types**. It is a very powerful concept that allows to develop abstract algorithms and data structures and to provide concrete types to operate on later.

> 泛型，最核心的思想就是对type进行抽象（abstraction），可以将type作为参数传递。

Interestingly, generics were not present in the early versions of Java and were added along the way only in Java 5 release.(泛型是在Java 5引入的) And since then, it is fair to say that generics revolutionized the way Java programs are being written, delivering much stronger type guaranties and making code significantly safer.

## Generics and interfaces

## Generics and classes

## Generics and methods

## Limitation of generics

Being one of the brightest features of the language, **generics** unfortunately have some limitations, mainly caused by the fact that they were introduced quite late into already mature language. Most likely, more thorough implementation required significantly more time and resources so the trade-offs had been made in order to have generics delivered in a timely manner.

> Generics有自己的限制。

Firstly, **primitive types** (like `int`, `long`, `byte`, . . . ) are not allowed to be used in generics. It means whenever you need to parameterize your generic type with a primitive one, the respective class wrapper (`Integer`, `Long`, `Byte`, . . . ) has to be used instead. Not only that, because of necessity to use class wrappers in generics, it causes implicit boxing and unboxing of primitive values.

> 限制一：不能使用primitive types。

```java
final List<Long> longs = new ArrayList<>();
longs.add( 0L ); // ’long’ is boxed to ’Long’
long value = longs.get( 0 ); // ’Long’ is unboxed to ’long’
```

Another one, more obscure, is **type erasure**. It is important to know that **generics exist only at compile time**: the Java compiler uses a complicated set of rules to enforce type safety with respect to generics and their type parameters usage, however the produced JVM bytecode has all concrete types erased (and replaced with the `Object` class). It could come as a surprise first that the following code does not compile:

> 限制二：（1）在compile过程中，类型擦除后，可能导致两个看起来不同的方法有相同的签名

```java
void sort(Collection<String> strings) {
    // Some implementation over strings heres
}

void sort(Collection<Number> numbers) {
    // Some implementation over numbers here
}
```

From the developer’s standpoint, it is a perfectly valid code, however because of type erasure, those two methods are narrowed down to the same signature and it leads to compilation error (with a weird message like “Erasure of method `sort(Collection<String>)` is the same as another method . . . ”)

Another disadvantage caused by type erasure come from the fact that **it is not possible to use generics’ type parameters in any meaningful way**, for example to (1)create new instances of the type, or (2)get the concrete class of the type parameter or use it in the `instanceof` operator. The examples shown below do no pass compilation phase:

限制二：（2）在runtime过程中，经过类型擦除后，type parameters有一些事情是做不了的：

- （1） 根据type parameters不能创建对象 create new instances of the type
- （2） 获得type parameters的真正类型   get the concrete class of the type parameter。换句话说，就是不能使用`instanceof`来验证type parameters的真正类型

And lastly, it is also not possible to create the array instances using generics’ type parameters. For example, the following code does not compile (this time with a clean error message “Cannot create a generic array of T”):

> 限制三：不能创建数组

```java
public <T> void performAction(final T action) {
    T[] actions = new T[0];
}
```

## Generics, wildcards and bounded types

So far we have seen the examples using generics with **unbounded type parameters**. **The extremely powerful ability of generics is imposing the constraints (or bounds) on the type** they are parameterized with using the `extends` and `super` keywords.

By using upper and lower type bounds (with `extends` and `super`) along with **type wildcards**, the generics provide a way to fine-tune the type parameter requirements or, is some cases, completely omit them, still preserving the generics type-oriented semantic.

## Generics and type inference

When generics found their way into the Java language, they blew up the amount of the code developers had to write in order to satisfy the language syntax rules. For example:

```java
final Map<String, Collection<String> > map = new HashMap<String, Collection<String> >();
for( final Map.Entry<String, Collection<String> > entry: map.entrySet() ) {
    // Some implementation here
}
```

The Java 7 release somewhat addressed this problem by making changes in the compiler and introducing the new diamond operator `<>`. For example:

```java
final Map<String, Collection<String> > map = new HashMap<>();
```

The compiler is able to infer the generics type parameters from the left side and allows omitting them in the right side of the expression. **It was a significant progress towards making generics syntax less verbose**, however **the abilities of the compiler to infer generics type parameters were quite limited**. For example, the following code does not compile in Java 7(在Java 8可以编译通过):

```java
public static <T> void performAction( final Collection<T> actions, final Collection<T> defaults ) {
    // Some implementation here
}

final Collection<String> strings = new ArrayList<>();
performAction(strings, Collections.emptyList());
```

The Java 7 compiler cannot infer the type parameter for the `Collections.emptyList()` call and as such requires it to be passed explicitly:

```java
performAction(strings, Collections.<String>emptyList());
```

Luckily, the Java 8 release brings more enhancements into the compiler and, particularly, into the type inference for generics so the code snippet shown above compiles successfully, saving the developers from unnecessary typing.

## Generics and annotations

It is worth mentioning that in the pre-Java 8 era the generics were not allowed to have annotations associated with their type parameters. But Java 8 changed that and now it becomes possible to annotate generics type parameters at the places they are declared or used. 

For example, here is how the generic method could be declared and its type parameter is adorned with annotations:

```java
public<@Actionable T> void performAction(final T action) {
    // Some implementation here
}
```

Or just another example of applying the annotation when generic type is being used:

```java
final Collection<@NotEmpty String> strings = new ArrayList<>();
// Some implementation here
```

## Accessing generic type parameters

As you already know from the section Limitation of generics, it is not possible to **get the class of the generic type parameter**. One simple trick to work-around that is to require additional argument to be passed, `Class<T>`, in places where it is necessary to know the class of the type parameter `T`. For example:

```java
public <T> void performAction(final T action, final Class<T> clazz) {
    // Some implementation here
}
```

It might blow the amount of arguments required by the methods but with careful design it is not as bad as it looks at the first glance.

Another interesting use case which often comes up while working with generics in Java is to **determine the concrete class of the type** which generic instance has been parameterized with. It is not as straightforward and requires **Java reflection API** to be involved. The `ParameterizedType` instance is the central point to do the **reflection** over **generics**.

## When to use generics

However, please be aware of the limitations of the current implementation of generics in Java, type erasure and the famous implicit boxing and unboxing for primitive types. **Generics are not a silver bullet solving all the problems you may encounter** and **nothing could replace careful design and thoughtful thinking**.



