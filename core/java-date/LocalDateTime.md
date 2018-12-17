# LocalDateTime

The `LocalDateTime` is used to represent **a combination of date and time**.

This is the most commonly used class when we need a combination of date and time. The class offers a variety of APIs and we will look at some of the most commonly used ones.

## Create

An instance of `LocalDateTime` can be obtained from the system clock similar to `LocalDate` and `LocalTime`:

```java
LocalDateTime.now();
```

The below code samples explain how to create an instance using the factory “`of`” and “`parse`” methods. The result would be a `LocalDateTime` instance representing 20 February 2015, 06:30 AM:

```java
LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);

LocalDateTime.parse("2015-02-20T06:30:00");
```

## Change

There are utility APIs to support addition and subtraction of specific units of time like days, months, year and minutes are available. The below code samples demonstrates the usage of “plus” and “minus” methods. These APIs behave exactly like their counterparts in LocalDate and LocalTime:

```java
localDateTime.plusDays(1);

localDateTime.minusHours(2);
```

## Getter

Getter methods are available to extract specific units similar to the date and time classes. Given the above instance of LocalDateTime, the below code sample will return the month February:

```java
localDateTime.getMonth();
```
