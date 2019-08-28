# Java String Format Examples

URL: 

- https://www.novixys.com/blog/java-string-format-examples/

<!-- TOC -->

- [1. Introduction](#1-introduction)
- [2. String Formatting](#2-string-formatting)
- [3. Format Specifiers](#3-format-specifiers)
- [4. Argument Index](#4-argument-index)
- [5. Integer Formatting](#5-integer-formatting)
- [6. String Formatting](#6-string-formatting)
- [7. Date and Time Formatting](#7-date-and-time-formatting)
  - [7.1. 三个概念: CST/UTC/GMT](#71-%e4%b8%89%e4%b8%aa%e6%a6%82%e5%bf%b5-cstutcgmt)
  - [7.2. The Difference Between GMT and UTC](#72-the-difference-between-gmt-and-utc)

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


## 2. String Formatting

Most common way of formatting a string in java is using `String.format()`. If there were a “**java sprintf**” then this would be it.

```java
String output = String.format("%s = %d", "joe", 35);
```

For formatted **console output**, you can use `printf()` or the `format()` method of `System.out` and `System.err` PrintStreams.

```java
System.out.printf("My name is %s%n", "joe");
System.out.format("%s = %d", "joe", 35);
```

Create a `Formatter` and link it to a `StringBuilder`. Output formatted using the `format()` method will be appended to the `StringBuilder`.

```java
StringBuilder sb = new StringBuilder();
Formatter fmt = new Formatter(sb);
fmt.format("PI = %f%n", Math.PI);
System.out.print(sb.toString());
```

Full Code

```java
package lsieun.format;

import java.util.Formatter;

public class ABC {
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

## 3. Format Specifiers

> Specifiers 说明符；变量说明符

| Specifier | Applies to                                                   | Output                                                       |
| --------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `%a`      | floating point (except [*BigDecimal*](https://docs.oracle.com/javase/8/docs/api/java/math/BigDecimal.html)) | Hex output of floating point number                          |
| `%b`      | Any type                                                     | “true” if non-null, “false” if null                          |
| `%c`      | character                                                    | Unicode character                                            |
| `%d`      | integer (incl. byte, short, int, long, bigint)               | Decimal Integer                                              |
| `%e`      | floating point                                               | decimal number in scientific notation                        |
| `%f`      | floating point                                               | decimal number                                               |
| `%g`      | floating point                                               | decimal number, possibly in scientific notation depending on the precision and value. |
| `%h`      | any type                                                     | Hex String of value from hashCode() method.                  |
| `%n`      | none                                                         | Platform-specific line separator.                            |
| `%o`      | integer (incl. byte, short, int, long, bigint)               | Octal number                                                 |
| `%s`      | any type                                                     | String value                                                 |
| `%t`      | Date/Time (incl. long, Calendar, Date and TemporalAccessor)  | %t is the prefix for Date/Time conversions. More formatting flags are needed after this. See Date/Time conversion below. |
| `%x`      | integer (incl. byte, short, int, long, bigint)               | Hex string.                                                  |

我整理的分类：

| 分类      | Specifier    |
| --------- | ------------ |
| boolean   | `%b`         |
| char      | `%c`         |
| int       | `%d`         |
| float     | `%a`         |
|           | `%e`         |
|           | `%f`         |
|           | `%g`         |
| String    | `%s`         |
| Date/Time | `%t`         |
| 特殊      | `%h`         |
|           | `%o` 八进制  |
|           | `%x`十六进制 |
|           | `%n`换行     |




```java
package lsieun.format;

import java.util.Date;

public class FormatSpecifier{
    public static void main(String[] args) {
        testX();
    }

    public static void testA() {
        System.out.printf("%a%n", 1.0); // 0x1.0p0
        System.out.printf("%a%n", 2.0); // 0x1.0p1
        System.out.printf("%a%n", 3.0); // 0x1.8p1
        System.out.printf("%a%n", 4.0); // 0x1.0p2
        System.out.printf("%a%n", 5.0); // 0x1.4p2
        System.out.printf("%a%n", 6.0); // 0x1.8p2
    }

    public static void testB() {
        // Integer
        System.out.printf("%b%n", 0); // true
        System.out.printf("%b%n", 1); // true

        // Boolean
        System.out.printf("%b%n", true); // true
        System.out.printf("%b%n", false); // false

        // Object
        System.out.printf("%b%n", new Object()); // true
        System.out.printf("%b%n", null); // false
    }

    public static void testC() {
        // Char
        System.out.printf("%c%n", 'a');
        System.out.printf("%c%n", 'A');
        System.out.printf("%c%n", '宋');

        // String
        //System.out.printf("%c%n", "A"); // Exception
    }

    public static void testD() {
        // Char
        System.out.printf("%d%n", (byte)'a'); // 97
        System.out.printf("%d%n", (byte)'A'); // 65
        System.out.printf("%d%n", (byte)'宋'); // -117

        // Integer
        System.out.printf("%d%n", 100); // 100
    }

    public static void testE() {
        System.out.printf("%e%n", 0.0);  // 0.000000e+00
        System.out.printf("%e%n", 0.01); //1.000000e-02
        System.out.printf("%e%n", 0.1);  //1.000000e-01

        System.out.printf("%e%n", 1.0);   //1.000000e+00
        System.out.printf("%e%n", 10.0);  //1.000000e+01
        System.out.printf("%e%n", 100.0); //1.000000e+02

        System.out.printf("%e%n", Math.PI); // 3.141593e+00
    }

    public static void testF() {
        System.out.printf("%f%n", 0.0);  // 0.000000
        System.out.printf("%f%n", 0.01); // 0.010000
        System.out.printf("%f%n", 0.1);  // 0.100000

        System.out.printf("%f%n", 1.0);   // 1.000000
        System.out.printf("%f%n", 10.0);  // 10.000000
        System.out.printf("%f%n", 100.0); // 100.000000

        System.out.printf("%f%n", Math.PI); // 3.141593
    }

    public static void testG() {
        System.out.printf("%g%n", 0.0);  // 0.00000
        System.out.printf("%g%n", 0.01); // 0.0100000
        System.out.printf("%g%n", 0.1);  // 0.100000

        System.out.printf("%g%n", 1.0);   // 1.00000
        System.out.printf("%g%n", 10.0);  // 10.0000
        System.out.printf("%g%n", 100.0); // 100.000

        System.out.printf("%g%n", Math.PI); // 3.14159
    }

    public static void testH() {
        // char
        System.out.printf("%h%n", 'a'); // 61
        System.out.printf("%h%n", 'A'); // 41

        // String
        System.out.printf("%h%n", "A"); // 41

        // Object
        System.out.printf("%h%n", new Object()); // 7d4991ad
    }

    public static void testO() {
        System.out.printf("%o%n", 0); // 0
        System.out.printf("%o%n", 1); // 1
        System.out.printf("%o%n", 7); // 7
        System.out.printf("%o%n", 8); // 10

        System.out.printf("%o%n", 15); // 17
        System.out.printf("%o%n", 16); // 20

        System.out.printf("%o%n", 64); // 100
        System.out.printf("%o%n", 65); // 101

        System.out.printf("%o%n", 255); // 377
        System.out.printf("%o%n", 256); // 400
    }

    public static void testS() {
        // Integer
        System.out.printf("%s%n", 0);
        System.out.printf("%s%n", 1);

        // char
        System.out.printf("%s%n", 'a');
        System.out.printf("%s%n", 'A');
        System.out.printf("%s%n", '宋');

        // String
        System.out.printf("%s%n", "不知不觉，就会心生懈(XIE)怠(DAI)");
    }

    public static void testT() {
        System.out.printf("%tF%n", new Date()); // 2018-11-19
        System.out.printf("%tT%n", new Date()); // 18:44:59
    }

    public static void testX() {
        System.out.printf("%x%n", 0);  // 0
        System.out.printf("%x%n", 1);  // 1
        System.out.printf("%x%n", 10); // a
        System.out.printf("%x%n", 11); // b
        System.out.printf("%x%n", 12); // c
        System.out.printf("%x%n", 13); // d
        System.out.printf("%x%n", 14); // e
        System.out.printf("%x%n", 15); // f
        System.out.printf("%x%n", 16); // 10
    }
}
```


## 4. Argument Index

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

## 5. Integer Formatting

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

## 6. String Formatting

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


## 7. Date and Time Formatting

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

### 7.1. 三个概念: CST/UTC/GMT

三个概念：

- CST
- UTC
- GMT

CST可视为美国、澳大利亚、古巴或中国的标准时间。
CST可以为如下4个不同的时区的缩写：
- 美国中部时间：Central Standard Time (USA) UT-6:00
- 古巴标准时间：Cuba Standard Time UT-4:00
- 中国标准时间：China Standard Time UT+8:00
- 澳大利亚中部时间：Central Standard Time (Australia) UT+9:30

协调世界时，又称世界统一时间、世界标准时间、国际协调时间。由于英文（CUT）和法文（TUC）的缩写不同，作为妥协，简称**UTC**。

Greenwich Mean Time (**GMT**) 世界标准时刻；格林威治时间

### 7.2. The Difference Between GMT and UTC

There is no time difference between **Coordinated Universal Time** and **Greenwich Mean Time**. **2:44 a.m. Tuesday, Coordinated Universal Time (UTC)** is
**2:44 a.m. Tuesday, Greenwich Mean Time (GMT)**.

**Greenwich Mean Time (GMT)** is often interchanged or confused with **Coordinated Universal Time (UTC)**. But **GMT** is **a time zone** and **UTC** is **a time standard**.

Although **GMT** and **UTC** share the same current time in practice, there is a basic difference between the two:

- **GMT** is a **time zone** officially used in some European and African countries. The time can be displayed using both the 24-hour format (0 - 24) or the 12-hour format (1 - 12 am/pm).
- **UTC** is not a time zone, but **a time standard** that is the basis for civil time and time zones worldwide. This means that no country or territory officially uses UTC as a local time.

**GMT** defines the **Time Zones** around the world which are based on the
"Prime Meridian" (longitude zero) at Greenwich, England. **GMT** is the **local time** at the longitude of Greenwich. It is also the time anywhere within the time zone where the time is the time at Greenwich. **That time zone** is also called **GMT**. In theory the GMT zone should be 7.5 degrees either side of Greenwich, but the actual zone is far different, for convenience and political reasons.

```txt
UTC = GMT+0
```

**The UTC time** is always the **GMT** at `0` meridian, so to calculate `UTC` from `GMT` you just need to substract the timezone of the GMT for making it zero.

Examples:

- 1) For `09:00:00 GMT+2`, then `UTC = 09:00:00 - 2hs = 07:00:00`
- 2) For `09:00:00 GMT-5`, then `UTC = 09:00:00 + 5hs = 14:00:00`

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


