# LocalTime

The `LocalTime` represents time without a date.

<!-- TOC -->

- [1. Create](#1-create)
- [2. Modify](#2-modify)
- [3. Getter](#3-getter)
- [4. Other](#4-other)

<!-- /TOC -->

## 1. Create

Similar to `LocalDate`, an instance of `LocalTime` can be created from system clock or by using “`parse`” and “`of`” method. Quick look at some of the commonly used APIs below.

An instance of current `LocalTime` can be created from the system clock as below:

```java
LocalTime now = LocalTime.now();
```

In the below code sample, we create a `LocalTime` representing 06:30 AM by parsing a string representation:

```java
LocalTime sixThirty = LocalTime.parse("06:30");
```

The Factory method “of” can be used to create a LocalTime. For example the below code creates LocalTime representing 06:30 AM using the factory method:

```java
LocalTime sixThirty = LocalTime.of(6, 30);
```

## 2. Modify

The below example creates a LocalTime by parsing a string and adds an hour to it by using the “plus” API. The result would be LocalTime representing 07:30 AM:

```java
LocalTime sevenThirty = LocalTime.parse("06:30").plus(1, ChronoUnit.HOURS);
```

## 3. Getter

Various getter methods are available which can be used to get specific units of time like hour, min and secs like below:

```java
int six = LocalTime.parse("06:30").getHour();
```

## 4. Other

We can also check if a specific time is before or after another specific time. The below code sample compares two LocalTime for which the result would be true:

```java
boolean isbefore = LocalTime.parse("06:30").isBefore(LocalTime.parse("07:30"));
```

The max, min and noon time of a day can be obtained by constants in LocalTime class. This is very useful when performing database queries to find records within a given span of time. For example, the below code represents 23:59:59.99:

```java
LocalTime maxTime = LocalTime.MAX
```
