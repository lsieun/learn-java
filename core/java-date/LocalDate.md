# LocalDate

The `LocalDate` represents a date in ISO format (**yyyy-MM-dd**) without time.

It can be used to store dates like birthdays and paydays.

<!-- TOC -->

- [1. Create Instance](#1-create-instance)
- [2. Change Date](#2-change-date)
- [3. Get Date Info](#3-get-date-info)
- [4. Other](#4-other)

<!-- /TOC -->

## 1. Create Instance

An instance of current date can be created from the system clock as below:

```java
LocalDate localDate = LocalDate.now();
```

The `LocalDate` representing a specific day, month and year can be obtained using the “`of`” method or by using the “`parse`” method. For example the below code snippets represents the LocalDate for 20 February 2015:

```java	
LocalDate.of(2015, 02, 20);
LocalDate.parse("2015-02-20");
```

## 2. Change Date

The `LocalDate` provides various utility methods to obtain a variety of information. Let’s have a quick peek at some of these APIs methods.

The following code snippet gets the current local date and adds one day:

```java	
LocalDate tomorrow = LocalDate.now().plusDays(1);
```

This example obtains the current date and subtracts one month. Note how it accepts an enum as the time unit:

```java
LocalDate previousMonthSameDay = LocalDate.now().minus(1, ChronoUnit.MONTHS);
```

## 3. Get Date Info

In the following two code examples we parse the date “2016-06-12” and get the day of the week and the day of the month respectively. Note the return values, the first is an object representing the DayOfWeek while the second in an int representing the ordinal value of the month:

```java
DayOfWeek sunday = LocalDate.parse("2016-06-12").getDayOfWeek(); 
int twelve = LocalDate.parse("2016-06-12").getDayOfMonth();
```

## 4. Other
We can test if a date occurs in a leap year. In this example we test if the current date occurs is a leap year:

```java	
boolean leapYear = LocalDate.now().isLeapYear();
```

The relationship of a date to another can be determined to occur before or after another date:

```java
boolean notBefore = LocalDate.parse("2016-06-12").isBefore(LocalDate.parse("2016-06-11"));
boolean isAfter = LocalDate.parse("2016-06-12").isAfter(LocalDate.parse("2016-06-11"));
```

Date boundaries can be obtained from a given date. In the following two examples we get the LocalDateTime that represents the beginning of the day (2016-06-12T00:00) of the given date and the LocalDate that represents the beginning of the month (2016-06-01) respectively:

```java
LocalDateTime beginningOfDay = LocalDate.parse("2016-06-12").atStartOfDay();
LocalDate firstDayOfMonth = LocalDate.parse("2016-06-12").with(TemporalAdjusters.firstDayOfMonth());
```


