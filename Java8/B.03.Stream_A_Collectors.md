# Stream collect Method

<!-- TOC -->

- [1. Collectors in a nutshell](#1-collectors-in-a-nutshell)
  - [1.1. Collectors as advanced reductions](#11-collectors-as-advanced-reductions)
  - [1.2. Predefined collectors](#12-predefined-collectors)
- [2. Reducing and summarizing](#2-reducing-and-summarizing)
  - [2.1. Collectors.counting()](#21-collectorscounting)
  - [2.2. Finding maximum and minimum in a stream of values](#22-finding-maximum-and-minimum-in-a-stream-of-values)
  - [2.3. Summarization](#23-summarization)
    - [2.3.1. summingInt](#231-summingint)
    - [2.3.2. averagingInt](#232-averagingint)
    - [2.3.3. summarizingInt](#233-summarizingint)
  - [2.4. Joining Strings](#24-joining-strings)
  - [2.5. Generalized summarization with reduction](#25-generalized-summarization-with-reduction)
  - [2.6. Choosing the best solution for your situation](#26-choosing-the-best-solution-for-your-situation)
- [3. Grouping](#3-grouping)
  - [3.1. Single level grouping](#31-single-level-grouping)
  - [3.2. Multilevel grouping](#32-multilevel-grouping)
  - [3.3. Collecting data in subgroups](#33-collecting-data-in-subgroups)
    - [3.3.1. Adapting the collector result to a different type](#331-adapting-the-collector-result-to-a-different-type)
    - [3.3.2. Other examples of collectors used in conjunction with groupingBy](#332-other-examples-of-collectors-used-in-conjunction-with-groupingby)
- [4. Partitioning](#4-partitioning)
  - [4.1. Advantages of partitioning](#41-advantages-of-partitioning)
  - [4.2. Partitioning numbers into prime and nonprime](#42-partitioning-numbers-into-prime-and-nonprime)

<!-- /TOC -->

`collect()` is a special case for `reduce()` in Java 8. It is more
efficient with the Java `String` class.

理解：

- （1） `collect()`方法是`reduce()`方法的一种特殊形式
- （2） 在处理String时，`collect()`方法比`reduce()`方法更有效率。

`collect` is a reduction operation, just like `reduce`, that takes as argument various recipes for accumulating the elements of a stream into a summary result. These recipes are defined by a new `Collector` interface, so it’s important to distinguish `Collection`, `Collector`, and `collect`!

区分这三个概念：

- （1） `collect`是方法
- （2） `Collector`是`collect`方法接收的参数类型
- （3） `Collection`是原来的API，与Streams API形成对比

## 1. Collectors in a nutshell

### 1.1. Collectors as advanced reductions

The implementation of the methods of the `Collector` interface defines how to perform a reduction operation on a stream. We investigate **how to create customized collectors** in sections 6.5 and 6.6. But the `Collectors` utility class provides lots of static factory methods to conveniently create an instance of the most common collectors that are ready to use. The most straightforward and frequently used collector is the `toList` static method, which gathers all the elements of a stream into a List:

```java
List<Transaction> transactions = transactionStream.collect(Collectors.toList());
```

### 1.2. Predefined collectors

The `Collectors` class offer three main functionalities:

- Reducing and summarizing stream elements to a single value
- Grouping elements
- Partitioning elements

We’ll also describe **partitioning** as a special case of **grouping**, using a predicate, a one-argument function returning a `boolean`, as a grouping function.

## 2. Reducing and summarizing

As you just learned, **collectors** (the parameters to the Stream method `collect`) are typically used in cases where it’s necessary to reorganize the stream’s items into a collection. But more generally, they can be used every time you want to **combine all the items in the stream** into **a single result**. This result can be of any type, as complex as a multilevel map representing a tree or as simple as a single integer.

### 2.1. Collectors.counting()

As a first simple example, let’s count the number of dishes in the menu, using the collector returned by the `counting` factory method:

```java
long howManyDishes = menu.stream().collect(Collectors.counting());
```

You can write this far more directly as

```java
long howManyDishes = menu.stream().count();
```

but the `counting` collector can be especially useful when used in combination with other collectors, as we demonstrate later.

In the rest of this chapter, we assume that you’ve imported all the static factory methods of the `Collectors` class with

```java
import static java.util.stream.Collectors.*;
```

so you can write `counting()` instead of `Collectors.counting()` and so on.

### 2.2. Finding maximum and minimum in a stream of values

Suppose you want to find the highest-calorie dish in the menu. You can use two collectors, `Collectors.maxBy` and `Collectors.minBy`, to calculate the maximum or minimum value in a stream. These two collectors take a `Comparator` as argument to compare the elements in the stream.

Here you create a `Comparator` comparing dishes based on their calorie content and pass it to `Collectors.maxBy`:

```java
Comparator<Dish> dishCaloriesComparator =
Comparator.comparingInt(Dish::getCalories);

Optional<Dish> mostCalorieDish =
menu.stream()
.collect(maxBy(dishCaloriesComparator));
```

### 2.3. Summarization

Another common reduction operation that returns a single value is to **sum the values of a numeric field of the objects in a stream**. Alternatively, you may want to **average the values**. Such operations are called **summarization operations**.

#### 2.3.1. summingInt

The `Collectors` class provides a specific factory method for summing: `Collectors.summingInt`. It accepts a function that maps **an object** into the `int` that has to be summed and returns a collector that, when passed to the usual `collect` method, performs the requested summarization.

```java
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
```

The `Collectors.summingLong` and `Collectors.summingDouble` methods behave exactly the same way and can be used where the field to be summed is respectively a `long` or a `double`.

#### 2.3.2. averagingInt

But there’s more to summarization than mere summing; also available is a `Collectors.averagingInt`, together with its `averagingLong` and `averagingDouble` counterparts, to calculate the average of the same set of numeric values:

```java
double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
```

#### 2.3.3. summarizingInt

So far, you’ve seen how to use collectors to count the elements in a stream, find the maximum and minimum values of a numeric property of those elements, and calculate their sum and average. Quite often, though, you may want to retrieve two or more of these results, and possibly you’d like to do it in a single operation. In this case, you can use the collector returned by the `summarizingInt` factory method.

For example, you can count the elements in the menu and obtain the sum, average, maximum, and minimum of the calories contained in each dish with a single summarizing operation:

```java
IntSummaryStatistics menuStatistics =
menu.stream().collect(summarizingInt(Dish::getCalories));
```

This collector gathers all that information in a class called `IntSummaryStatistics` that provides convenient getter methods to access the results. Printing the menu-Statistic object produces the
following output:

```txt
IntSummaryStatistics{count=9, sum=4300, min=120, average=477.777778, max=800}
```

As usual, there are corresponding `summarizingLong` and `summarizingDouble` factory methods with associated types `LongSummaryStatistics` and `DoubleSummaryStatistics`; these are used when the property to be collected is a primitive-type `long` or a `double`.

### 2.4. Joining Strings

The collector returned by the `joining` factory method concatenates into a single string all strings resulting from invoking the `toString` method on each object in the stream. This means you can concatenate the names of all the dishes in the menu as follows:

```java
String shortMenu = menu.stream().map(Dish::getName).collect(joining());
```

Note that `joining` internally makes use of a `StringBuilder` to append the generated strings into one. Also note that if the `Dish` class had a `toString` method returning the dish’s name, you’d obtain the same result without needing to map over the original stream with a function extracting the name from each dish:

```java
String shortMenu = menu.stream().collect(joining());
```

Both produce the following string,

```txt
porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon
```

which isn’t very readable. Fortunately, the `joining` factory method has an overloaded version that accepts **a delimiter** string between two consecutive elements, so you can obtain a comma-separated list of the dishes’ names with

```java
String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
```

which, as expected, will generate

```txt
pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon
```

```java
String value = Stream.of("A", "B", "C").collect(Collectors.joining(", "));
System.out.println(value);
```

Out:

```txt
A, B, C
```

Until now, we’ve explored various collectors that reduce a stream to **a single value**. In the next section, we demonstrate how all the reduction processes of this form are **special cases of the more general reduction collector** provided by the `Collectors.reducing` factory method.

### 2.5. Generalized summarization with reduction

All the collectors we’ve discussed so far are, in reality, only convenient specializations of a reduction process that can be defined using the `reducing` factory method.

The `Collectors.reducing` factory method is a generalization of all of them. The special cases discussed earlier are arguably provided only for programmer convenience. (But remember that **programmer convenience and readability are of prime importance**!)

For instance, it’s possible to calculate the total calories in your menu with a collector created from the `reducing` method as follows:

```java
int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
```

Similarly, you could find the highest-calorie dish using the one-argument version of reducing as follows:

```java
Optional<Dish> mostCalorieDish =
menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));
```

You can think of the collector created with the one-argument `reducing` factory method as a particular case of the three-argument method, which uses the first item in the stream as a
starting point and **an identity function** (that is, a function doing nothing more than returning its input argument as is) as a **transformation function**. This also implies that the one-argument `reducing` collector won’t have any starting point when passed to the `collect` method of an empty stream and, for this reason it returns an `Optional<Dish>` object.

### 2.6. Choosing the best solution for your situation

```java
// 方法一：这是通过使用collect方法和Collector结合
int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));

// 方法二：这是通过使用reduce方法
int totalCalories = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();

// 方法三：这是通过使用IntStream
int totalCalories = menu.stream().mapToInt(Dish::getCalories).sum();
```

Once again, this demonstrates how functional programming in general often provides multiple ways to perform the same operation.<sub>第一点：实现同样的功能，可能有多种方法</sub>

This example also shows that collectors are somewhat more complex to use than the methods directly available on the Streams interface, but in exchange they offer higher levels of abstraction and generalization and are more reusable and customizable.<sub>第二点：有失有得</sub>

**Our suggestion is to explore the largest number of solutions possible for the problem at hand, but always choose the most specialized one that’s general enough to solve it**. This is often the best decision for both readability and performance reasons. 

For instance, to calculate the total calories in our menu, we’d prefer the last solution (using `IntStream`) because it’s the most concise and likely also the most readable one. At the same time, it’s also the one that performs best, because `IntStream` lets us **avoid all the auto-unboxing operations**, or implicit conversions from `Integer` to `int`, that are useless in this case.

## 3. Grouping

You can easily perform grouping task using a collector returned by the `Collectors.groupingBy` factory method as follows:

### 3.1. Single level grouping

group the dishes in the menu by their type

```java
Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
```

group the dishes in the menu by calories

```java
public enum CaloricLevel {
    DIET, NORMAL, FAT
}

Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
        groupingBy(dish -> {
            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
            else return CaloricLevel.FAT;
        }));
```

### 3.2. Multilevel grouping

You can achieve multilevel grouping by using a collector created with **a two-argument version** of the `Collectors.groupingBy` factory method, which accepts a second argument of type collector besides the usual classification function. So to perform a two-level grouping, you can pass an **inner groupingBy** to the **outer groupingBy**, defining a second-level criterion to classify the stream’s items.

```java
Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
        groupingBy(Dish::getType,
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                    else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                }))
);
```

### 3.3. Collecting data in subgroups

The second collector passed to the first `groupingBy` can be any type of collector, not just another `groupingBy`.

For instance, it’s possible to count the number of Dishes in the menu for each type, by passing the `counting` collector as a second argument to the `groupingBy` collector:

```java
Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));
```

Also note that the regular one-argument `groupingBy(f)`, where `f` is the classification function, is in reality just shorthand for `groupingBy(f, toList())`.

To give another example, you could rework the collector you already used to find the highest-calorie dish in the menu to achieve a similar result, but now classified by the type of dish:

```java
Map<Dish.Type, Optional<Dish>> mostCaloricByType =
menu.stream().collect(groupingBy(Dish::getType, maxBy(comparingInt(Dish::getCalories))));
```

Note: The values in this `Map` are `Optional`s because this is the resulting type of the collector generated by the `maxBy` factory method, but in reality if there’s no Dish in the menu for a given type, that type won’t have an `Optional.empty()` as value; it won’t be present at all as a key in the Map. The `groupingBy` collector lazily adds a new key in the grouping Map only the first time it finds an element in the stream, producing that key when applying on it the grouping criteria being used. This means that in this case, the `Optional` wrapper isn’t very useful, because it’s not modeling a value that could be eventually absent but is there incidentally, only because this is the type returned by the reducing collector.

#### 3.3.1. Adapting the collector result to a different type

Because the `Optional`s wrapping all the values in the Map resulting from the last grouping operation aren’t very useful in this case, you may want to get rid of them. To achieve this, or more generally, to adapt the result returned by a collector to a different type, you could use the collector returned by the `Collectors.collectingAndThen` factory method.

```java
Map<Dish.Type, Dish> mostCaloricByType = menu.stream()
        .collect(groupingBy(Dish::getType,
                collectingAndThen(maxBy(comparingInt(Dish::getCalories)),
                        Optional::get)));
```

#### 3.3.2. Other examples of collectors used in conjunction with groupingBy

For example, you could also reuse the collector created to **sum the calories of all the dishes in the menu** to obtain a similar result, but this time for each group of Dishes:

```java
Map<Dish.Type, Integer> totalCaloriesByType = menu.stream()
        .collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));
```

Yet another collector, commonly used in conjunction with `groupingBy`, is one generated by the `mapping` method. This method takes **two arguments**: **a function transforming the elements in a stream** and **a further collector accumulating the objects resulting from this transformation**. Its purpose is to adapt a collector accepting elements of a given type to one working on objects of a different type, by applying a `mapping` function to each input element before accumulating them.

To see a practical example of using this collector, suppose you want to know which CaloricLevels are available in the menu for each type of Dish. You could achieve this result combining a `groupingBy` and a `mapping` collector as follows:

```java
Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
        menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },
                        toSet())));
```

Note that in the previous example, there are no guarantees about what type of `Set` is returned. But by using `toCollection`, you can have more control. For example, you can ask for a `HashSet` by passing a constructor reference to it:

```java
Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType =
        menu.stream().collect(
                groupingBy(Dish::getType, mapping(
                        dish -> {
                            if (dish.getCalories() <= 400) return CaloricLevel.DIET;
                            else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
                            else return CaloricLevel.FAT;
                        },
                        toCollection(HashSet::new))));
```

## 4. Partitioning

Partitioning is a special case of grouping: having a predicate (a function returning a `boolean`), called a **partitioning function**, as a classification function. The fact that the **partitioning function** returns a `boolean` means the resulting grouping Map will have a `Boolean` as a key type and therefore there can be at most two different groups—one for `true` and one for `false`.

```java
Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));
```

So you could retrieve all the vegetarian dishes by getting from this Map the value indexed with the key `true`:

```java
List<Dish> vegetarianDishes = partitionedMenu.get(true);
```

Note that you could achieve the same result by just filtering the stream created from the menu List with the same predicate used for partitioning and then collecting the result in an additional List:

```java
List<Dish> vegetarianDishes = menu.stream().filter(Dish::isVegetarian).collect(toList());
```

Partitioning has the advantage of **keeping both lists of the stream elements**, for which the application of the partitioning function returns `true` or `false`. So in the previous example, you can obtain the `List` of the nonvegetarian Dishes by accessing the value of the key `false` in the `partitionedMenu` Map, using **two separate filtering operations**: **one with the predicate** and **one with its negation**.

### 4.1. Advantages of partitioning

Also, as you already saw for `groupingBy`, the `partitioningBy` factory method has
**an overloaded version** to which you can pass **a second collector**, as shown here:

```java
Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType =
        menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
```

As another example, you can reuse your earlier code to find **the most caloric dish** among both vegetarian and nonvegetarian dishes:

```java
Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
        menu.stream().collect(
                partitioningBy(
                        Dish::isVegetarian,
                        collectingAndThen(
                                maxBy(comparingInt(Dish::getCalories)),
                                Optional::get)));
```

### 4.2. Partitioning numbers into prime and nonprime

Suppose you want to write a method accepting as argument an int `n` and partitioning the first `n` natural numbers into prime and nonprime. But first, it will be useful to develop a predicate that tests to see if a given candidate number is prime or not:

```java
public boolean isPrime(int candidate) {
    return IntStream.range(2, candidate)
        .noneMatch(i -> candidate % i == 0);
}
```

A simple optimization is to test only for factors less than or equal to the square root of the `candidate`:

```java
public boolean isPrime(int candidate) {
    int candidateRoot = (int) Math.sqrt((double) candidate);
    return IntStream.range(2, candidateRoot)
        .noneMatch(i -> candidate % i == 0);
}
```

Now the biggest part of the job is done. To partition the first `n` numbers into prime and nonprime, it’s enough to create a stream containing those `n` numbers and reduce it with a `partitioningBy` collector using as predicate the `isPrime` method you just developed:

```java
public Map<Boolean, List<Integer>> partitionPrimes(int n) {
    return IntStream.rangeClosed(2, n).boxed()
        .collect(
                partitioningBy(candidate -> isPrime(candidate)));
}
```
