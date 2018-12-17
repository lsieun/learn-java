# Period

The `Period` class is widely used to modify values of given a date or to obtain the difference between two dates:

```java
LocalDate initialDate = LocalDate.parse("2007-05-10");
LocalDate finalDate = initialDate.plus(Period.ofDays(5));
int five = Period.between(finalDate, initialDate).getDays();
```

The Period class has various getter methods such as `getYears`, `getMonths` and `getDays` to get values from a `Period` object. The below code example returns an int value of 5 as we try to get difference in terms of days:

The `Period` between two dates can be obtained in a specific unit such as days or month or years, using `ChronoUnit.between`:

```java
int five = ChronoUnit.DAYS.between(finalDate , initialDate);
```
