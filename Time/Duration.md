# Period

Similar to `Period`, the `Duration` class is use to deal with Time. In the following code we create a LocalTime of 6:30 am and then add a duration of 30 seconds to make a LocalTime of 06:30:30am:

```java
LocalTime initialTime = LocalTime.of(6, 30, 0);
 
LocalTime finalTime = initialTime.plus(Duration.ofSeconds(30));
```

The `Duration` between two instants can be obtained either as a Duration or as a specific unit. In the first code snippet we use the `between()` method of the `Duration` class to find the time difference between `finalTime` and `initialTime` and return the difference in seconds:

```java
int thirty = Duration.between(finalTime, initialTime).getSeconds();
```

In the second example we use the between() method of the ChronoUnit class to perform the same operation:

```java
int thirty = ChronoUnit.SECONDS.between(finalTime, initialTime);
```


