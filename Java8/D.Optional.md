# Optional

<!-- TOC -->

- [1. Method](#1-method)
  - [1.1. Method: of and get](#11-method-of-and-get)
  - [1.2. Method: empty and ofNullable](#12-method-empty-and-ofnullable)
  - [1.3. Method: isPresent, orElse and orElseGet](#13-method-ispresent-orelse-and-orelseget)
  - [1.4. Method: ifPresent](#14-method-ifpresent)

<!-- /TOC -->

`Optional` is a new core library data type that is designed to provide a better alternative to `null`.

The `Optional<T>` class (`java.util.Optional`) is a container class to represent the existence or absence of a value.

`null` is often used to represent the absence of a value, and this is the use case that `Optional` is replacing. The problem with using `null` in order to represent absence is the dreaded `NullPointerException`. If you refer to a variable that is `null` , your code blows up.

## 1. Method

### 1.1. Method: of and get

If you want to create an `Optional` instance from a value, there is a factory method called `of`. The `Optional` is now a container for this value, which can be pulled out with `get`.

```java
Optional<String> opt = Optional.of("Hello");
String str = opt.get();
```

### 1.2. Method: empty and ofNullable

Because an `Optional` may also represent an absent value, there’s also a factory method called `empty`, and you can convert a nullable value into an `Optional` using the `ofNullable` method.

```java
Optional emptyOptional = Optional.empty();
Optional alsoEmpty = Optional.ofNullable(null);
```

### 1.3. Method: isPresent, orElse and orElseGet

One approach to using `Optional` is to guard any call to `get()` by checking `isPresent()`. A neater approach is to call the `orElse` method, which provides an alternative value in case the `Optional` is empty. If creating an alternative value is computationally expensive, the `orElseGet` method should be used. This allows you to pass in a `Supplier` that is called only if the `Optional` is genuinely empty.

```java
Optional<String> emptyOptional = Optional.empty();
if (emptyOptional.isPresent()) {
    System.out.println(emptyOptional.get());
}

String strA = emptyOptional.orElse("A");
System.out.println(strA);

String strB = emptyOptional.orElseGet(() -> "B");
System.out.println(strB);
```

### 1.4. Method: ifPresent

`ifPresent(Consumer<T> block)` executes the given block if a value is present. It lets you pass a lambda that takes an argument of type `T` and returns `void`.
