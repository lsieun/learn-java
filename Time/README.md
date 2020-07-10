# 时间

Use `java.util.Calendar` class if you need to **extract year, month, day, hour, minute, and second**, or **manipulating these field** (e.g., 7 days later, 3 weeks earlier).

Use `java.text.DateFormat` to format a Date (form Date to text) and parse a date string (from text to Date). SimpleDateForamt is a subclass of DateFormat.

`Date` is legacy class, which does not support internationalization. `Calendar` and `DateFormat` support locale (you need to consider locale only if you program is to be run in many countries concurrently).

## Issues with the Existing Date/Time APIs

- **Thread Safety** – The `Date` and `Calendar` classes are not thread safe, leaving developers to deal with the headache of hard to debug concurrency issues and to write additional code to handle thread safety. On the contrary **the new Date and Time APIs** introduced in Java 8 are immutable and thread safe, thus taking that concurrency headache away from developers.
- **APIs Design and Ease of Understanding** – The `Date` and `Calendar` APIs are poorly designed with inadequate methods to perform day-to-day operations. **The new Date/Time APIs** is ISO centric and follows consistent domain models for date, time, duration and periods. There are a wide variety of utility methods that support the commonest operations.
- **ZonedDate and Time** – Developers had to write additional logic to handle timezone logic with the old APIs, whereas with the new APIs, handling of timezone can be done with Local and ZonedDate/Time APIs.


The `Period` class represents a quantity of time in terms of **years**, **months** and **days** and the `Duration` class represents a quantity of time in terms of **seconds** and **nano seconds**.














