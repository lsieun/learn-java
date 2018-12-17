# ZonedDateTime

Java 8 provides `ZonedDateTime` when we need to deal with time zone specific date and time. The `ZoneId` is an identifier used to represent different zones. There are about 40 different time zones and the `ZoneId` are used to represent them as follows.

## Create

In this code snippet we create a Zone for Paris:

```java
ZoneId zoneId = ZoneId.of("Europe/Paris");
```

A set of all zone ids can be obtained as below:

```java
Set<String> allZoneIds = ZoneId.getAvailableZoneIds();
```

The `LocalDateTime` can be converted to a specific zone:

```java
ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zoneId);
```

The `ZonedDateTime` provides parse method to get time zone specific date time:

```java
ZonedDateTime.parse("2015-05-03T10:15:30+01:00[Europe/Paris]");
```

Another way to work with time zone is by using `OffsetDateTime`. The `OffsetDateTime` is an immutable representation of a date-time with an offset. This class stores all date and time fields, to a precision of nanoseconds, as well as the offset from UTC/Greenwich.

The `OffSetDateTime` instance can be created as below using `ZoneOffset`. Here we create a `LocalDateTime` representing 6:30 am on 20th February 2015:

```java
LocalDateTime localDateTime = LocalDateTime.of(2015, Month.FEBRUARY, 20, 06, 30);
```

Then we add two hours to the time by creating a ZoneOffset and setting for the localDateTime instance:

```java
ZoneOffset offset = ZoneOffset.of("+02:00");
 
OffsetDateTime offSetByTwo = OffsetDateTime
  .of(localDateTime, offset);
```

