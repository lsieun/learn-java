# Character

<!-- TOC -->

- [1. is方法](#1-is%E6%96%B9%E6%B3%95)
- [2. 获取character category](#2-%E8%8E%B7%E5%8F%96character-category)
- [3. 对字符进行转换](#3-%E5%AF%B9%E5%AD%97%E7%AC%A6%E8%BF%9B%E8%A1%8C%E8%BD%AC%E6%8D%A2)

<!-- /TOC -->

This class provides an immutable object wrapper around the primitive `char` data type.

> 作者有话说：`Character`是原始类型`char`的包装类，它是一个immutable object wrapper，也就是说它的内容是不可变的。

- `charValue()` returns the `char` value of a `Character` object.
- The `compareTo()` method implements the `Comparable` interface so that `Character` objects can be ordered and sorted.

The **static methods** are the most interesting thing about this class, however: they categorize `char` values based on the categories defined by the Unicode standard. (And some of the methods are useful only if you have a detailed understanding of that standard).

> 作者有话说：static methods，是这个类里最有趣的东西。

## 1. is方法

Static methods beginning with “is” test whether a character is in a given category. `isDigit()`, `isLetter()`, `isWhitespace()`, `isUpperCase()`, and `isLowerCase()` are some of the most useful. Note that these methods work for any Unicode character, not just with the familiar Latin letters and Arabic numbers of the ASCII character set.

> 作者有话说：这里介绍了一些is开头的方法

```java
Character.isDigit('0'); // true
Character.isDigit('9'); // true
Character.isDigit('x'); // false

Character.isLetter('0'); // false
Character.isLetter('a'); // true
Character.isLetter('宋'); // true

Character.isWhitespace(' '); // true
Character.isWhitespace('\t'); // true
```

## 2. 获取character category

`getType()` returns a constant that identifies the category of a character.

```java
Character.getType('0'); // 9 数字
Character.getType('a'); // 2 英文小写字母
Character.getType('A'); // 1 英文大写字母
Character.getType('宋'); // 5 汉字
Character.getType(' '); // 12 空格
Character.getType('\t'); // 15 Tab
```

关于上面的数字，可以参考下面的内容。

```java
public final class Character {
    /**
     * General category "Lu" in the Unicode specification.
     * Lu 应该是Letter upper的缩写
     */
    public static final byte UPPERCASE_LETTER = 1;

    /**
     * General category "Ll" in the Unicode specification.
     * Ll 应该是Letter lower的缩写
     */
    public static final byte LOWERCASE_LETTER = 2;

    /**
     * General category "Lo" in the Unicode specification.
     * Lo 应该是Letter other的缩写
     */
    public static final byte OTHER_LETTER = 5;

    /**
     * General category "Nd" in the Unicode specification.
     * Nd 应该是Number decimal的缩写
     */
    public static final byte DECIMAL_DIGIT_NUMBER        = 9;

    /**
     * General category "Zs" in the Unicode specification.
     */
    public static final byte SPACE_SEPARATOR = 12;

    /**
     * General category "Cc" in the Unicode specification.
     */
    public static final byte CONTROL = 15;
}
```

`getDirectionality()` returns a separate `DIRECTIONALITY_` constant that specifies the “directionality category” of a character.

```java
System.out.println(Character.getDirectionality('0')); // 3
System.out.println(Character.getDirectionality('9')); // 3
System.out.println(Character.getDirectionality('a')); // 0
System.out.println(Character.getDirectionality('A')); // 0
System.out.println(Character.getDirectionality('宋')); // 0
System.out.println(Character.getDirectionality(' ')); // 12
System.out.println(Character.getDirectionality('\t')); // 11
```

关于上面的数字，可以参考下面的代码。

```java
public final class Character {
    /**
     * Undefined bidirectional character type. Undefined {@code char}
     * values have undefined directionality in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_UNDEFINED = -1;

    /**
     * Strong bidirectional character type "L" in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_LEFT_TO_RIGHT = 0;

    /**
     * Strong bidirectional character type "R" in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT = 1;

    /**
    * Strong bidirectional character type "AL" in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC = 2;

    /**
     * Weak bidirectional character type "EN" in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_EUROPEAN_NUMBER = 3;

    /**
     * Neutral bidirectional character type "S" in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_SEGMENT_SEPARATOR = 11;

    /**
     * Neutral bidirectional character type "WS" in the Unicode specification.
     */
    public static final byte DIRECTIONALITY_WHITESPACE = 12;
}
```

## 3. 对字符进行转换

In addition to testing **the category of a character**, this class also defines **static methods for converting characters**.

> 作者有话说：我想我自己真正明白的方法只有`toUpperCase()`和`toLowerCase()`两个方法。

- `toUpperCase()` returns the uppercase equivalent of the specified character (or returns the character itself if the character is uppercase or has no uppercase equivalent).
- `toLowerCase()` converts instead to lowercase.
- `digit()` returns the integer equivalent of a given character in a given radix (or base; for example, use 16 for hexadecimal). It works with **any Unicode digit character** and, for sufficiently large radix values, with the ASCII letters `a-z` and `A-Z`.
- `forDigit()` returns the ASCII character that corresponds to the specified value (`0-35`) for the specified radix.
- `getNumericValue()` is similar but also works with any Unicode character including those that represent numbers but are not decimal digits, such as Roman numerals.
- Finally, the static `toString()` method returns a String of length 1 that contains the specified char value.

```java
System.out.println(Character.digit('0', 16)); // 0
System.out.println(Character.digit('9', 16)); // 9
System.out.println(Character.digit('A', 16)); // 10
System.out.println(Character.digit('B', 16)); // 11
System.out.println(Character.digit('C', 16)); // 12
System.out.println(Character.digit('D', 16)); // 13
System.out.println(Character.digit('E', 16)); // 14
System.out.println(Character.digit('F', 16)); // 15
System.out.println(Character.digit('宋', 16)); // -1
System.out.println(Character.digit(' ', 16)); // -1
System.out.println(Character.digit('\t', 16)); // -1
```
