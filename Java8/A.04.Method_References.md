# Method references

<!-- TOC -->

- [1. Recipe for constructing method references](#1-recipe-for-constructing-method-references)
- [2. Constructor references](#2-constructor-references)
  - [2.1. zero-argument constructor](#21-zero-argument-constructor)
  - [2.2. one-argument constructor](#22-one-argument-constructor)
  - [2.3. two-argument constructor](#23-two-argument-constructor)
  - [2.4. interesting applications](#24-interesting-applications)
- [create arrays](#create-arrays)

<!-- /TOC -->

Method references let you reuse existing method definitions and pass them just like lambdas.

Before:

```java
inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
```

After (using a method reference and `java.util.Comparator.comparing`):

```java
inventory.sort(comparing(Apple::getWeight));
```

Indeed, a **method reference** lets you create a **lambda expression** from **an existing method implementation**. But by referring to a method name explicitly, your code can gain better readability. How does it work? When you need a **method reference**, the **target reference** is placed before the delimiter `::` and the name of the method is provided after it. For example, `Apple::getWeight` is a method reference to the method `getWeight` defined in the `Apple` class. Remember that **no brackets are needed** because you’re not actually calling the method. The method reference is shorthand for the lambda expression `(Apple a) -> a.getWeight()`.

You can think of **method references** as syntactic sugar for lambdas that refer only to a single method because you write less to express the same thing.

## 1. Recipe for constructing method references

There are three main kinds of method references:

- (1) A method reference to a **static method** (for example, the method `parseInt` of `Integer`, written `Integer::parseInt`)
- (2) A method reference to an **instance method** of an arbitrary type (for example, the method `length` of a `String`, written `String::length`)
- (3) A method reference to an **instance method** of **an existing object** (for example, suppose you have a local variable `expensiveTransaction` that holds an object of type `Transaction`, which supports an instance method `getValue`; you can write `expensiveTransaction::getValue`)

Recipes for constructing **method references** for three different types of **lambda expressions**

| No   | Lambda                                      | Method reference            |
| ---- | ------------------------------------------- | --------------------------- |
| 1    | `(args) -> ClassName.staticMethod(args)`    | `ClassName::staticMethod`   |
| 2    | `(arg0, rest) -> arg0.instanceMethod(rest)` | `ClassName::instanceMethod` |
| 3    | `(args) -> expr.instanceMethod(args)`       | `expr::instanceMethod`      |

```java
List<String> list = Arrays.asList("a","b","A","B");
// 第一种：使用lambda
list.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
// 第二种：使用method reference
list.sort(String::compareToIgnoreCase);
```

## 2. Constructor references

### 2.1. zero-argument constructor

You can create a reference to **an existing constructor** using its **name** and the keyword `new` as follows: `ClassName::new`. It works similarly to a reference to a `static` method. For example, suppose there’s a zero-argument constructor.

This fits the signature `() -> Apple` of Supplier; you can do the following:

```java
// 第一种：lambda expression
Supplier<Apple> c1 = () -> new Apple();

// 第二种：Constructor references
Supplier<Apple> c1 = Apple::new;

Apple a1 = c1.get();
```

### 2.2. one-argument constructor

If you have a constructor with signature `Apple(Integer weight)`, it fits the signature of the `Function` interface, so you can do this,

```java
// 第一种：lambda expression
Function<Integer, Apple> c1 = (weight) -> new Apple(weight);

// 第二种：Constructor references
Function<Integer, Apple> c2 = Apple::new;

Apple a2 = c2.apply(110);
```

In the following code, each element of a `List` of `Integers` is passed to the constructor of `Apple` using a similar `map` method we defined earlier, resulting in a `List` of **apples** with different weights:

```java
List<Integer> weights = Arrays.asList(7, 3, 4, 10);
List<Apple> apples = map(weights, Apple::new);

public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
    List<Apple> result = new ArrayList<>();
    for(Integer e : list) {
        result.add(f.apply(e));
    }
    return result;
}
```

### 2.3. two-argument constructor

If you have a two-argument constructor, `Apple(String color, Integer weight)`, it fits the signature of the `BiFunction` interface, so you can do this:

```java
// 第一种：lambda expression
BiFunction<String, Integer, Apple> c3 = (color, weight) -> new Apple(color, weight);

// 第二种：Constructor references
BiFunction<String, Integer, Apple> c3 = Apple::new;
Apple a3 = c3.apply("green", 110);
```

### 2.4. interesting applications

The capability of referring to a constructor without instantiating it enables interesting applications. For example, you can use a `Map` to associate **constructors** with **a string value**. You can then create a method `giveMeFruit` that, given a `String` and an `Integer`, can create different types of fruits with different weights:

```java
static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
static {
    map.put("apple", Apple::new);
    map.put("orange", Orange::new);
    // ctc...
}
public static Fruit giveMeFruit(String fruit, Integer weight) {
    return map.get(fruit.toLowerCase()).apply(weight);
}
```

```java
public interface TriFunction<T, U, V, R>{
    R apply(T t, U u, V v);
}
```

## create arrays

It’s also possible to create arrays using this method. Here is how you would create a String array: `String[]::new`.

```java
import java.util.Arrays;
import java.util.function.Function;

public class Demo {
    public static void main(String[] args) {
        Function<Integer, String[]> func = String[]::new;
        String[] array = func.apply(3);
        System.out.println(Arrays.toString(array));
    }
}
```

Out:

```txt
[null, null, null]
```

