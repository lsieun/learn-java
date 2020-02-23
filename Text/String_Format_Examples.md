# Java String Format Examples

<!-- TOC -->

- [1. Introduction](#1-introduction)
- [2. String Formatting three Methods](#2-string-formatting-three-methods)
  - [2.1. String.format](#21-stringformat)
  - [2.2. printf](#22-printf)
  - [2.3. Formatter](#23-formatter)
- [3. Argument Index](#3-argument-index)
- [4. Different Data](#4-different-data)
  - [4.1. Integer Formatting](#41-integer-formatting)
  - [4.2. String Formatting](#42-string-formatting)
  - [4.3. Date and Time Formatting](#43-date-and-time-formatting)
- [5. Reference](#5-reference)

<!-- /TOC -->

## 1. Introduction

Always forgetting the Java String formatting specifiers? Or maybe you never took the time to learn. Here's a reference for you of the various flags you can use.

Have you tried to read and understand Java’s String [format documentation](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)? I have and found it hard to understand. While it does include all the information, the organization leaves something to be desired.

This guide is an attempt to bring some clarity and ease the usage of string formatting in java.

- String Formatting介绍三种方法，最常用的是`String.format()`
- 各种时间的说明符
- 三种类型数据的举例
  - Integer
  - String
  - Date And Time

## 2. String Formatting three Methods

### 2.1. String.format

Most common way of formatting a string in java is using `String.format()`. If there were a “**java sprintf**” then this would be it.

```java
String output = String.format("%s = %d", "joe", 35);
```

### 2.2. printf

For formatted **console output**, you can use `printf()` or the `format()` method of `System.out` and `System.err` PrintStreams.

```java
System.out.printf("My name is %s%n", "joe");
System.out.format("%s = %d", "joe", 35);
```

### 2.3. Formatter

Create a `Formatter` and link it to a `StringBuilder`. Output formatted using the `format()` method will be appended to the `StringBuilder`.

```java
StringBuilder sb = new StringBuilder();
Formatter fmt = new Formatter(sb);
fmt.format("PI = %f%n", Math.PI);
System.out.print(sb.toString());
```

Full Code

```java
import java.util.Formatter;

public class ThreeMethod {
    public static void main(String[] args) {
        // 第一种方式：使用String.format方法（最常用）
        String output = String.format("%s = %d", "joe", 35);
        System.out.println(output);

        // 第二种方式：使用PrintStream的printf或format方法
        // printf方法本质上是调用format方法
        System.out.printf("My name is %s%n", "joe");
        System.out.format("%s = %d%n", "joe", 35);

        // 第三种方式：使用Formatter和StringBuilder相结合
        StringBuilder sb = new StringBuilder();
        Formatter fmt = new Formatter(sb);
        fmt.format("PI = %f%n", Math.PI);
        System.out.print(sb.toString());
    }
}
```

## 3. Argument Index

An argument index is specified as **a number** ending with a “`$`” after the “`%`” and selects the specified argument in the argument list.

```java
String.format("%2$s", 32, "Hello"); // Hello
```

Full Code:

```java
package lsieun.format;

public class ArgumentIndex {
    public static void main(String[] args) {
        String value = String.format("%2$s", 32, "Hello"); // Hello
        System.out.println(value);

        String name = "Tom";
        int age = 12;
        String intro = String.format("My Name is %2$s, I'm %1$d years old, My Email is %2$s@gmail.com", age, name);
        System.out.println(intro);
    }
}
```

Output:

```txt
Hello
My Name is Tom, I'm 12 years old, My Email is Tom@gmail.com
```

## 4. Different Data

### 4.1. Integer Formatting

With the `%d` format specifier, you can use an argument of all integral types including `byte`, `short`, `int`, `long` and `BigInteger`.

**Default formatting**:

```java
String.format("%d", 93);
// prints 93
```

**Specifying a width**:

```java
String.format("|%20d|", 93);
// prints: |                  93|
```

**Left-justifying within the specified width**:

```java
String.format("|%-20d|", 93);
// prints: |93                  |
```

**Pad with zeros**:

```java
String.format("|%020d|", 93);
// prints: |00000000000000000093|
```

**Print positive numbers with a “+”**:
(Negative numbers always have the “-” included):

```java
String.format("|%+20d|", 93);
// prints: |                 +93|
```

**A `space` before positive numbers**.
A “`-`” is included for negative numbers as per normal.

```java
String.format("|% d|", 93);
// prints: | 93|

String.format("|% d|", -36);
// prints: |-36|
```

**Use locale-specific thousands separator**.
For the US locale, it is “,”:

```java
String.format("|%,d|", 10000000);
// prints: |10,000,000|
```

**Enclose negative numbers within parantheses (“`()`”) and skip the “`-`“**:

```java
String.format("|%(d|", -36);
// prints: |(36)|
```

**Octal Output**

```java
String.format("|%o|"), 93);
// prints: 135
```

**Hex Output**

```java
String.format("|%x|", 93);
// prints: 5d
```

**Alternate Representation for Octal and Hex Output**

Prints octal numbers with a leading “`0`” and hex numbers with leading “`0x`“.

```java
String.format("|%#o|", 93);
// prints: 0135

String.format("|%#x|", 93);
// prints: 0x5d

String.format("|%#X|", 93);
// prints: 0X5D
```

Full Code:

```java
package lsieun.format;

public class IntegerFormatting {
    public static void main(String[] args) {
        // Default formatting
        System.out.println("\n\n默认");
        System.out.printf("%d%n", 93);        // 93
        System.out.printf("%d%n", -93);       // 93

        // "%+d", 结果带正负号
        System.out.println("\n\n正负号");
        System.out.printf("|%+d|%n", 99);     // |+99|
        System.out.printf("|%+d|%n", -99);    // |-99|
        //System.out.printf("|%-d|%n", 99);   // Exception

        // A space before positive numbers
        // A “-” is included for negative numbers as per normal.
        System.out.println("\n\n空格");
        System.out.printf("|% d|%n", 93);     // | 93|
        System.out.printf("|% d|%n", -93);    // |-93|

        // Specifying a width
        System.out.println("\n\n指定宽度");
        System.out.printf("|%20d|%n", 93);    // |                  93|
        System.out.printf("|%20d|%n", -93);   // |                 -93|

        // Left-justifying within the specified width
        System.out.println("\n\n左对齐");
        System.out.printf("|%-20d|%n", 93);   // |93                  |
        System.out.printf("|%-20d|%n", -93);  // |-93                 |
        System.out.printf("|%-+20d|%n", 93);  // |+93                 |

        // Print positive numbers with a “+”
        System.out.println("\n\n指定宽度 and 正负号");
        System.out.printf("|%+20d|%n", 93);   // |                 +93|
        System.out.printf("|%+20d|%n", -93);  // |                 -93|


        // Pad with zeros
        System.out.println("\n\nPadding with zeros");
        System.out.printf("|%020d|%n", 93);   // |00000000000000000093|

        //
        System.out.println("\n\n逗号分隔");
        System.out.printf("|%,d|%n", 10000000);    // |10,000,000|
        System.out.printf("|%,d|%n", -10000000);   // |-10,000,000|

        System.out.println("\n\n左小括号");
        System.out.printf("|%(d|%n", 36);    // |36|
        System.out.printf("|%(d|%n", -36);   // |(36)|

        System.out.println("\n\n八进制");
        System.out.printf("|%o|%n", 15);    // |17|
        System.out.printf("|%#o|%n", 15);   // |017|

        System.out.println("\n\n十六进制");
        System.out.printf("|%x|%n", 93);    // |5d|
        System.out.printf("|%#x|%n", 93);   // |0x5d|
        System.out.printf("|%#X|%n", 93);   // |0X5D|
    }
}
```

### 4.2. String Formatting

**Default formatting**: Prints the whole string.

```java
String.format("|%s|", "Hello World");
// prints: "Hello World"
```

**Specify Field Length**

```java
String.format("|%30s|", "Hello World");
// prints: |                   Hello World|
```

**Left Justify Text**

```java
String.format("|%-30s|", "Hello World");
// prints: |Hello World                   |
```

**Specify Maximum Number of Characters**

```java
String.format("|%.5s|", "Hello World");
// prints: |Hello|
```

**Field Width and Maximum Number of Characters**

```java
String.format("|%30.5s|", "Hello World");
|                         Hello|
```

Full Code:

```java
package lsieun.format;

public class StringFormatting {
    public static void main(String[] args) {
        // Default formatting
        System.out.println("\n默认");
        System.out.printf("|%s|%n", "Hello World");    // |Hello World|

        // Specify Field Length
        System.out.println("\n指定宽度");
        System.out.printf("|%30s|%n", "Hello World");  // |                   Hello World|

        // Left Justify Text
        System.out.println("\n指定宽度+左对齐");
        System.out.printf("|%-30s|%n", "Hello World"); // |Hello World                   |

        // Specify Maximum Number of Characters
        System.out.println("\n限制字符个数");
        System.out.printf("|%.5s|%n", "Hello World");  // |Hello|

        // Field Width and Maximum Number of Characters
        System.out.println("\n指定宽度+限制字符个数");
        System.out.printf("|%30.5s|%n", "Hello World");// |                         Hello|
    }
}
```

### 4.3. Date and Time Formatting

Note: Using the formatting characters with “`%T`” instead of “`%t`” in the table below makes the output uppercase.

| Flag   | Notes                                                        |
| ------ | ------------------------------------------------------------ |
| `%tA`  | Full name of the day of the week, e.g. “`Sunday`“, “`Monday`“ |
| `%ta`  | Abbreviated name of the week day e.g. “`Sun`“, “`Mon`“, etc. |
| `%tB`  | Full name of the month e.g. “`January`“, “`February`“, etc.  |
| `%tb`  | Abbreviated month name e.g. “`Jan`“, “`Feb`“, etc.           |
| `%tC`  | Century part of year formatted with two digits e.g. “00” through “99”. |
| ` %tc` | Date and time formatted with “`%ta %tb %td %tT %tZ %tY`” e.g. “`Fri Feb 17 07:45:42 PST 2017`“ |
| `%tD`  | Date formatted as “`%tm/%td/%ty`“                            |
| `%td`  | Day of the month formatted with two digits. e.g. “`01`” to “`31`“. |
| `%te`  | Day of the month formatted without a leading 0 e.g. “1” to “31”. |
| `%tF`  | ISO 8601 formatted date with “`%tY-%tm-%td`“.                |
| `%tH`  | Hour of the day for the 24-hour clock e.g. “`00`” to “`23`“. |
| `%th`  | Same as %tb.                                                 |
| `%tI`  | Hour of the day for the 12-hour clock e.g. “`01`” – “`12`“.  |
| `%tj`  | Day of the year formatted with leading 0s e.g. “`001`” to “`366`“. |
| `%tk`  | Hour of the day for the 24 hour clock without a leading 0 e.g. “`0`” to “`23`“. |
| `%tl`  | Hour of the day for the 12-hour click without a leading 0 e.g. “`1`” to “`12`“. |
| `%tM`  | Minute within the hour formatted a leading 0 e.g. “`00`” to “`59`“. |
| `%tm`  | Month formatted with a leading 0 e.g. “`01`” to “`12`“.      |
| `%tN`  | Nanosecond formatted with 9 digits and leading 0s e.g. “000000000” to “999999999”. |
| `%tp`  | Locale specific “am” or “pm” marker.                         |
| `%tQ`  | Milliseconds since epoch Jan 1 , 1970 00:00:00 UTC.          |
| `%tR`  | Time formatted as 24-hours e.g. “`%tH:%tM`“.                 |
| `%tr`  | Time formatted as 12-hours e.g. “`%tI:%tM:%tS %Tp`“.         |
| `%tS`  | Seconds within the minute formatted with 2 digits e.g. “00” to “60”. “60” is required to support leap seconds. |
| `%ts`  | Seconds since the epoch Jan 1, 1970 00:00:00 UTC.            |
| `%tT`  | Time formatted as 24-hours e.g. “`%tH:%tM:%tS`“.             |
| `%tY`  | Year formatted with 4 digits e.g. “`0000`” to “`9999`“.      |
| `%ty`  | Year formatted with 2 digits e.g. “`00`” to “`99`“.          |
| `%tZ`  | Time zone abbreviation. e.g. “`UTC`“, “`PST`“, etc.          |
| `%tz`  | Time Zone Offset from GMT e.g. “`-0800`“.                    |

```java
package lsieun.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeFormatting2 {
    public static void main(String[] args) {
        System.out.println("===Composite");
        testComposite();
        System.out.println();

        System.out.println("===Year");
        testYear();
        System.out.println();

        System.out.println("===Month");
        testMonth();
        System.out.println();

        System.out.println("===Day");
        testDay();
        System.out.println();

        System.out.println("===Hour");
        testHour();
        System.out.println();

        System.out.println("===Minute");
        testMinute();
        System.out.println();

        System.out.println("===Second");
        testSecond();
        System.out.println();


        System.out.println("===Week");
        testWeek();
        System.out.println();

        System.out.println("===Other");
        testOther();
        System.out.println();

    }

    public static void testComposite() {
        // Date and time formatted with “%ta %tb %td %tT %tZ %tY” e.g. “Fri Feb 17 07:45:42 PST 2017“
        System.out.printf("%tc%n", getDate("2018-11-11 12:00:00")); // Sun Nov 11 12:00:00 CST 2018
        // Date formatted as “%tm/%td/%ty“
        System.out.printf("%tD%n", getDate("2018-08-09 12:00:00")); // 08/09/18
        // ISO 8601 formatted date with “%tY-%tm-%td“.
        System.out.printf("%tF%n", getDate("2018-08-09 12:00:00")); // 2018-08-09


        // Time formatted as 24-hours e.g. “%tH:%tM“.
        System.out.printf("%tR%n", getDate("2018-11-20 09:09:00")); // 09:09
        // Time formatted as 12-hours e.g. “%tI:%tM:%tS %Tp“.
        System.out.printf("%tr%n", getDate("2018-11-20 11:00:00")); // 11:00:00 AM
        System.out.printf("%tr%n", getDate("2018-11-20 13:00:00")); // 01:00:00 PM

        // Time formatted as 24-hours e.g. “%tH:%tM:%tS“.
        System.out.printf("%tT%n", getDate("1970-01-01 08:00:09")); // 08:00:09
        System.out.printf("%tT%n", getDate("1970-01-01 13:00:09")); // 13:00:09
    }

    public static void testYear() {
        // Century part of year formatted with two digits e.g. “00” through “99”.
        System.out.printf("%tC%n", getDate("2018-11-11 12:00:00")); // 20
        System.out.printf("%tC%n", getDate("1970-01-01 12:00:00")); // 19

        // Year formatted with 4 digits e.g. “0000” to “9999“.
        System.out.printf("%tY%n", getDate("1970-01-01 12:00:00")); // 1970
        // Year formatted with 2 digits e.g. “00” to “99“.
        System.out.printf("%ty%n", getDate("1970-01-01 12:00:00")); // 70
    }

    public static void testMonth() {
        // Full name of the month e.g. “January“, “February“, etc.
        System.out.printf("%tB%n", getDate("2018-11-11 12:00:00")); // November
        // Abbreviated month name e.g. “Jan“, “Feb“, etc.
        System.out.printf("%tb%n", getDate("2018-11-11 12:00:00")); // Nov
        // Same as %tb.
        System.out.printf("%th%n", getDate("2018-08-09 13:00:00")); // Aug

        // Month formatted with a leading 0 e.g. “01” to “12“.
        System.out.printf("%tm%n", getDate("2018-08-09 07:06:00")); // 08
    }

    public static void testDay() {
        // Day of the month formatted with two digits. e.g. “01” to “31“.
        System.out.printf("%td%n", getDate("2018-08-09 12:00:00")); // 09
        // Day of the month formatted without a leading 0 e.g. “1” to “31”.
        System.out.printf("%te%n", getDate("2018-08-09 12:00:00")); // 9

        // Day of the year formatted with leading 0s e.g. “001” to “366“.
        System.out.printf("%tj%n", getDate("2018-01-10 13:00:00")); // 010
    }

    public static void testHour() {
        // Hour of the day for the 24-hour clock e.g. “00” to “23“.
        System.out.printf("%tH%n", getDate("2018-08-09 13:00:00")); // 13

        // Hour of the day for the 12-hour clock e.g. “01” – “12“.
        System.out.printf("%tI%n", getDate("2018-08-09 09:00:00")); // 09
        System.out.printf("%tI%n", getDate("2018-08-09 13:00:00")); // 01

        // Hour of the day for the 24 hour clock without a leading 0 e.g. “0” to “23“.
        System.out.printf("%tk%n", getDate("2018-08-09 07:00:00")); // 7
        System.out.printf("%tk%n", getDate("2018-08-09 13:00:00")); // 13

        // Hour of the day for the 12-hour click without a leading 0 e.g. “1” to “12“.
        System.out.printf("%tl%n", getDate("2018-08-09 07:00:00")); // 7
        System.out.printf("%tl%n", getDate("2018-08-09 13:00:00")); // 1
    }

    public static void testMinute() {
        // Minute within the hour formatted a leading 0 e.g. “00” to “59“.
        System.out.printf("%tM%n", getDate("2018-08-09 07:06:00")); // 06
    }

    public static void testSecond() {
        // Milliseconds since epoch Jan 1 , 1970 00:00:00 UTC.
        System.out.printf("%tQ%n", getDate("1970-01-01 08:00:01")); // 1000

        // Seconds within the minute formatted with 2 digits e.g. “00” to “60”.
        System.out.printf("%tS%n", getDate("1970-01-01 08:00:09")); // 09

        // Seconds since the epoch Jan 1, 1970 00:00:00 UTC.
        System.out.printf("%ts%n", getDate("1970-01-01 08:00:09")); // 9
    }

    public static void testWeek() {
        // Full name of the day of the week, e.g. “Sunday“, “Monday“
        System.out.printf("%tA%n", getDate("2018-11-11 12:00:00")); // Sunday
        // Abbreviated name of the week day e.g. “Sun“, “Mon“, etc.
        System.out.printf("%ta%n", getDate("2018-11-11 12:00:00")); // Sun
    }

    public static void testOther() {
        // Nanosecond formatted with 9 digits and leading 0s e.g. “000000000” to “999999999”.
        System.out.printf("%tN%n", getDate("2018-11-20 12:00:00")); // 000000000
        System.out.printf("%tN%n", new Date()); // 变化的值

        // Locale specific “am” or “pm” marker.
        System.out.printf("%tp%n", getDate("2018-11-20 11:00:00")); // am
        System.out.printf("%tp%n", getDate("2018-11-20 12:00:00")); // pm
        System.out.printf("%tp%n", getDate("2018-11-20 13:00:00")); // pm
        System.out.printf("%tp%n", getDate("2018-11-20 24:00:00")); // am

        // Time zone abbreviation. e.g. “UTC“, “PST“, etc.
        System.out.printf("%tZ%n", new Date()); // CST
        // Time Zone Offset from GMT e.g. “-0800“.
        System.out.printf("%tz%n", new Date()); // +0800
    }



    public static Date getDate(String str) {
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = fmt.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
```

## 5. Reference

- [Java String Format Examples](https://www.novixys.com/blog/java-string-format-examples/)
- [Java 8 Doc: Class Formatter](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)
