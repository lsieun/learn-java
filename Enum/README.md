# Enums

<!-- TOC -->

- [1. History: from Int to Enum](#1-history-from-int-to-enum)
- [2. When to use enums](#2-when-to-use-enums)

<!-- /TOC -->

## 1. History: from Int to Enum

Before enums had been introduced into the Java language<sub>注：在Enums引入Java之前的情况</sub>, the regular way to model the set of fixed values in Java was just by declaring a number of constants.

For example:

```java
public class DaysOfTheWeekConstants {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;
}
```

Although this approach kind of works, it is far from being the ideal solution<sub>注：虽然这种方法可行，但不够完美</sub>. Primarily, because the constants themselves are just values of type `int`<sub>注：不够完美的地方：这个类只有7个有效值，而int由4个byte组成，也就是32个bit，可以表示2<sup>32</sup>个数，远远大于7个值</sub> and every place in the code where those constants are expected (instead of arbitrary `int` values) should be explicitly documented and asserted all the time. Semantically, it is not a type-safe representation of the concept as the following method demonstrates.

```java
public boolean isWeekend(int day) {
    return (day == SATURDAY || day == SUNDAY);
}
```

From logical point of view, the `day` argument should have one of the values declared in the `DaysOfTheWeekConstants` class. However, it is not possible to guess that without additional documentation being written (and read afterwards by someone). For the Java compiler the call like `isWeekend(100)` looks absolutely correct and raises no concerns.

Here the enums come to the rescue. Enums allow to replace constants with the typed values and to use those types everywhere. Let us rewrite the solution above using enums.

```java
public enum DaysOfTheWeek {  // 第一处不同：由class变成了enum
    MONDAY,  // 第二处不同：变量不再是int类型，而是DaysOfTheWeek类型
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
```

What changed is that the `class` becomes `enum` and the possible values are listed in the enum definition. The distinguishing part however is that every single value is the instance of the enum class it is being declared at (in our example, DaysOfTheWeek). As such, whenever enum are being used, the Java compiler is able to do type checking. For example:

```java
public boolean isWeekend(DaysOfTheWeek day) { // 第三处不同：能够对传入参数进行类型检验
    return (day == SATURDAY || day == SUNDAY);
}
```

Please notice that the usage of **the uppercase naming scheme** in enums is just a convention, nothing really prevents you from not doing that.


## 2. When to use enums

Since Java 5 release enums are the only preferred and recommended way to represent and dial with **the fixed set of constants**. Not only they are strongly-typed, they are extensible and supported by any modern library or framework.
