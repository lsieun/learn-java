# Using Collections

<!-- TOC -->

- [1. 遍历](#1-%e9%81%8d%e5%8e%86)
  - [1.1. Iterating through a List](#11-iterating-through-a-list)
  - [1.2. Transforming a List](#12-transforming-a-list)
- [2. 查找](#2-%e6%9f%a5%e6%89%be)
  - [2.1. Finding Elements](#21-finding-elements)
  - [2.2. Picking an Element](#22-picking-an-element)
- [3. 聚合](#3-%e8%81%9a%e5%90%88)
  - [3.1. Reducing a Collection to a Single Value](#31-reducing-a-collection-to-a-single-value)
  - [3.2. Joining Elements](#32-joining-elements)

<!-- /TOC -->

## 1. 遍历

### 1.1. Iterating through a List

```java
import java.util.Arrays;
import java.util.List;

public class A_Collections_01_Iteration {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_04_iteration_method_reference();
    }

    public static void test_01_iteration_for_loop() {
        for (int i = 0; i < friends.size(); i++) {
            System.out.println(friends.get(i));
        }
    }

    public static void test_02_iteration_for_each() {
        for (String name : friends) {
            System.out.println(name);
        }
    }

    public static void test_03_iteration_lambda() {
        friends.forEach(name -> System.out.println(name));
    }

    public static void test_04_iteration_method_reference() {
        friends.forEach(System.out::println);
    }
}
```

### 1.2. Transforming a List

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class A_Collections_02_Transform {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_03_transform();
    }

    public static void test_01_transform() {
        final List<String> uppercaseNames = new ArrayList<>();
        for (String name : friends) {
            uppercaseNames.add(name.toUpperCase());
        }
        System.out.println(uppercaseNames);
    }

    public static void test_02_transform() {
        List<String> uppercaseNames = friends.stream()
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(uppercaseNames);
    }

    public static void test_03_transform() {
        friends.stream()
                .map(String::toUpperCase)
                .forEach(System.out::println);
    }
}
```

## 2. 查找

### 2.1. Finding Elements

```java
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class A_Collections_03_FindElements {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_03_find_elements();
    }

    public static void test_01_find_elements() {
        final List<String> startsWithN = new ArrayList<String>();
        for (String name : friends) {
            if (name.startsWith("N")) {
                startsWithN.add(name);
            }
        }
        System.out.println(startsWithN);
    }

    public static void test_02_find_elements() {
        final List<String> startsWithN =
                friends.stream()
                        .filter(name -> name.startsWith("N"))
                        .collect(Collectors.toList());
        System.out.println(startsWithN);
    }

    public static Predicate<String> checkIfStartsWith(final String letter) {
        return name -> name.startsWith(letter);
    }

    public static void test_03_find_elements() {
        final long countFriendsStartN =
                friends.stream()
                        .filter(checkIfStartsWith("N")).count();
        final long countFriendsStartB =
                friends.stream()
                        .filter(checkIfStartsWith("B")).count();

        System.out.println(countFriendsStartN);
        System.out.println(countFriendsStartB);
    }

    public static void test_04_find_elements() {
//        final Function<String, Predicate<String>> startsWithLetter =
//                (String letter) -> {
//                    Predicate<String> checkStarts = (String name) -> name.startsWith(letter);
//                    return checkStarts;
//                };

//        final Function<String, Predicate<String>> startsWithLetter =
//                (String letter) -> (String name) -> name.startsWith(letter);

        final Function<String, Predicate<String>> startsWithLetter =
                letter -> name -> name.startsWith(letter);

        final long countFriendsStartN =
                friends.stream()
                        .filter(startsWithLetter.apply("N")).count();
        final long countFriendsStartB =
                friends.stream()
                        .filter(startsWithLetter.apply("B")).count();

        System.out.println(countFriendsStartN);
        System.out.println(countFriendsStartB);
    }
}
```

### 2.2. Picking an Element

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class A_Collections_04_PickAnElement {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_02_pick();
    }

    public static void test_01_pick() {
        final String startingLetter = "N";
        String foundName = null;
        for(String name : friends) {
            if(name.startsWith(startingLetter)) {
                foundName = name;
                break;
            }
        }
        System.out.print(String.format("A name starting with %s: ", startingLetter));
        if(foundName != null) {
            System.out.println(foundName);
        } else {
            System.out.println("No name found");
        }
    }

    public static void test_02_pick() {
        final String startingLetter = "M";
        final Optional<String> foundName =
                friends.stream()
                        .filter(name ->name.startsWith(startingLetter))
                        .findFirst();
        System.out.println(String.format("A name starting with %s: %s",
                startingLetter, foundName.orElse("No name found")));
    }
}
```

## 3. 聚合

### 3.1. Reducing a Collection to a Single Value

```java
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class A_Collections_05_Reduce {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_03_reduce();
    }

    public static void test_01_reduce() {
        System.out.println("Total number of characters in all names: " +
                friends.stream()
                        .mapToInt(name -> name.length())
                        .sum());
    }

    public static void test_02_reduce() {
        final Optional<String> aLongName =
                friends.stream()
                        .reduce((name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2);
        aLongName.ifPresent(name ->
                System.out.println(String.format("A longest name: %s", name)));
    }

    public static void test_03_reduce() {
        final String steveOrLonger =
                friends.stream()
                        .reduce("Steve", (name1, name2) ->
                                name1.length() >= name2.length() ? name1 : name2);
        System.out.println(steveOrLonger);
    }
}
```

### 3.2. Joining Elements

```java
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class A_Collections_06_JoinElements {
    private static final List<String> friends =
            Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");

    public static void main(String[] args) {
        test_05_join();
    }

    public static void test_01_join() {
        for(String name : friends) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }

    public static void test_02_join() {
        for(int i = 0; i < friends.size() - 1; i++) {
            System.out.print(friends.get(i) + ", ");
        }
        if(friends.size() > 0)
            System.out.println(friends.get(friends.size() - 1));
    }

    public static void test_03_join() {
        System.out.println(String.join(", ", friends));
    }

    public static void test_04_join() {
        System.out.println(
                friends.stream()
                        .map(String::toUpperCase)
                        .collect(joining(", ")));
    }

    public static void test_05_join() {
        System.out.println(
                friends.stream()
                        .collect(joining(", ", "[", "]")));
    }
}
```
