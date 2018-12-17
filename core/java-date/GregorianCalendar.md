# GregorianCalendar

`java.util.GregorianCalendar`

The calendar that we use today, called **Gregorian calendar**, came into effect in October 15, 1582 in some countries and later in other countries. It replaces the Julian calendar. 10 days were removed from the calendar, i.e., October 4, 1582 (Julian) was followed by October 15, 1582 (Gregorian). The only difference between the Gregorian and the Julian calendar is the "leap-year rule". In Julian calendar, every four years is a leap year. In Gregorian calendar, a leap year is a year that is divisible by 4 but not divisible by 100, or it is divisible by 400, i.e., the Gregorian calendar omits century years which are not divisible by 400 (removing 3 leap years (or 3 days) for every 400 years). Furthermore, Julian calendar considers the first day of the year as march 25th, instead of January 1st.

`java.util.Calendar` is an abstract class. `Calendar.getInstance()` returns an implementation class `java.util.GregorianCalendar` (except locales of "th" and "jp"). In Java, this `GregorianCalendar` handles both the Gregorian calendar as well as the Julian calendar, including the cut over.

`GregorianCalendar` has the following constructors:

- `GregorianCalendar()`: using the current time, with the default time zone and locale.
- `GregorianCalendar(int year, int month, int dayOfMonth)`: with the default time zone and locale.
- `GregorianCalendar(int year, int month, int dayOfMonth, int hourOfDay, int minute, int second)`
- `GregorianCalendar(TimeZone zone, Locale aLocale)`: using current time.
- `GregorianCalendar(TimeZone zone)`
- `GregorianCalendar(Locale aLocale)`

For Example:

```java
Calendar cal1 = new GregorianCalendar();  // allocate an instance and upcast to Calendar
Calendar cal2 = new GregorianCalendar(2010, 9, 26);  // allocate with the specified date
cal2.get(Calendar.DAY_OF_WEEK);                      // 1 (Sunday) to 7 (Saturday)
```
