# Calendar

URL: 
- http://www.ntu.edu.sg/home/ehchua/programming/java/DateTimeCalendar.html

The `Calendar` class provides support for:

- (1) maintaining a set of calendar fields such as YEAR, MONTH, DAY_OF_MONTH, HOUR, MINUTE, SECOND, MILLISECOND; and
- (2) manipulating these calendar fields, such as getting the date of the previous week, roll forward by 3 days.

`Calendar` provides internationalization support.

## Create Instance

`Calendar` is a abstract class, and you cannot use the constructor to create an instance. Instead, you use the static method `Calendar.getInstance()` to instantiate an implementation sub-class.

- `Calendar.getInstance()`: return a Calendar instance based on the current time in the default time zone with the default locale.
- `Calendar.getInstance(TimeZone zone)`
- `Calendar.getInstance(Locale aLocale)`
- `Calendar.getInstance(TimeZone zone, Locale aLocale)`

Looking into the source code reveals that: `getInstance()` returns a `GregorianCalendar` instance for all locales, (except `BuddhistCalendar` for Thai ("th_TH") and `JapaneseImperialCalendar` for Japanese ("ja_JP")).

## Properties

### Getter

The most important method in `Calendar` is `get(int calendarField)`, which produces an `int`. The `calendarField` are defined as static constant and includes:

- `get(Calendar.DAY_OF_WEEK)`: returns `1` (`Calendar.SUNDAY`) to `7` (`Calendar.SATURDAY`).
- `get(Calendar.YEAR)`: year
- `get(Calendar.MONTH)`: returns `0` (`Calendar.JANUARY`) to `11` (`Calendar.DECEMBER`).
- `get(Calendar.DAY_OF_MONTH)`, `get(Calendar.DATE)`: `1` to `31`
- `get(Calendar.HOUR_OF_DAY)`: `0` to `23`
- `get(Calendar.MINUTE)`: `0` to `59`
- `get(Calendar.SECOND)`: `0` to `59`
- `get(Calendar.MILLISECOND)`: `0` to `999`
- `get(Calendar.HOUR)`: `0` to `11`, to be used together with `Calendar.AM_PM`.
- `get(Calendar.AM_PM)`: returns `0` (`Calendar.AM`) or `1` (`Calendar.PM`).
- `get(Calendar.DAY_OF_WEEK_IN_MONTH)`: DAY_OF_MONTH 1 through 7 always correspond to DAY_OF_WEEK_IN_MONTH 1; 8 through 14 correspond to DAY_OF_WEEK_IN_MONTH 2, and so on.
- `get(Calendar.DAY_OF_YEAR)`: 1 to 366
- `get(Calendar.ZONE_OFFSET)`: GMT offset value of the time zone.
- `get(Calendar.ERA)`: Indicate AD (GregorianCalendar.AD), BC (GregorianCalendar.BC).

Example:

```java
package lsieun.calendar;

import java.util.Calendar;

// Get the year, month, day, hour, minute, second
public class GetYMDHMS {
    public static void main(String[] args) {
        Calendar cal = Calendar.getInstance();
        // You cannot use Date class to extract individual Date fields
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);      // 0 to 11
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);

        System.out.printf("Now is %4d/%02d/%02d %02d:%02d:%02d\n",  // Pad with zero
                year, month+1, day, hour, minute, second);
    }
}

```

Output:

```txt
Now is 2018/12/17 18:35:10
```

### Setter

Calendar has these setters and operations:

- `void set(int calendarField, int value)`
- `void set(int year, int month, int date)`
- `void set(int year, int month, int date, int hour, int minute, int second)`
- `void add(int field, int amount)`: Adds or subtracts the specified amount of time to the given calendar field, based on the calendar's rules.
- `void roll(int calendarField, boolean up)`: Adds or subtracts (up/down) a single unit of time on the given time field without changing larger fields.
- `void roll(int calendarField, int amount)`: Adds the specified (signed) amount to the specified calendar field without changing larger fields.

## Other Methods

Other frequently-used methods are:

- `Date getTime()`: return a `Date` object based on this Calendar's value.
- `void setTime(Date date)`
- `long getTimeInMills()`: Returns this Calendar's time value in milliseconds.
- `void setTimeInMillis(long millis)`
- `void setTimeZone(TimeZone value)`

## Conversion between Calendar and Date

You can use `getTime()` and `setTime()` to convert between `Calendar` and `Date`.

- `Date getTime()`: Returns a Date object representing this Calendar's time value
- `void setTime(Date aDate)`: Sets this Calendar's time with the given Date instance



