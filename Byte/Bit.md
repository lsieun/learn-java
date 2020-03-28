# Java Bitwise and Bit Shift Operators

<!-- TOC -->

- [1. Bitwise OR](#1-bitwise-or)
- [2. Bitwise AND](#2-bitwise-and)
- [3. Bitwise Complement](#3-bitwise-complement)
- [4. Bitwise XOR](#4-bitwise-xor)
- [5. Signed Left Shift](#5-signed-left-shift)
- [6. Signed Right Shift](#6-signed-right-shift)
- [7. Unsigned Right Shift](#7-unsigned-right-shift)
- [Reference](#reference)

<!-- /TOC -->

**Bitwise** is a level of operations that involves working with **individual bits**, which are the smallest units of data in a computer. Each `bit` has **a single binary value**: `0` or `1`.

Java provides `4` **bitwise** and `3` **bit shift operators** to perform **bit operations**.

**Bitwise** and **bit shift operators** are used on **integral types** (`byte`, `short`, `int` and `long`) to perform **bit-level operations**.

There are **7 operators** to perform **bit-level operations** in Java (**4 bitwise** and **3 bit shift**).

Java Bitwise and Bit Shift Operators

| Operator | Description                                                  |
| -------- | ------------------------------------------------------------ |
| `|`      | [Bitwise OR](https://www.programiz.com/java-programming/bitwise-operators#or) |
| `&`      | [Bitwise AND](https://www.programiz.com/java-programming/bitwise-operators#and) |
| `~`      | [Bitwise Complement](https://www.programiz.com/java-programming/bitwise-operators#complement) |
| `^`      | [Bitwise XOR](https://www.programiz.com/java-programming/bitwise-operators#xor) |
| `<<`     | [Left Shift](https://www.programiz.com/java-programming/bitwise-operators#left-shift) |
| `>>`     | [Right Shift](https://www.programiz.com/java-programming/bitwise-operators#right-shift) |
| `>>>`    | [Unsigned Right Shift](https://www.programiz.com/java-programming/bitwise-operators#unsigned-right-shift) |


## 1. Bitwise OR

Bitwise OR is a binary operator (operates on two operands). It's denoted by `|`.

The `|` operator compares corresponding bits of two operands. If either of the bits is 1, it gives 1. If not, it gives 0. For example,

```txt
12 = 00001100 (In Binary)
25 = 00011001 (In Binary)

Bitwise OR Operation of 12 and 25
  00001100
| 00011001
  ________
  00011101  = 29 (In decimal)
```

```java
class BitwiseOR {
    public static void main(String[] args) {

        int number1 = 12, number2 = 25, result;

        result = number1 | number2;
        System.out.println(result);
    }
}
```

## 2. Bitwise AND

Bitwise AND is a binary operator (operates on two operands). It's denoted by `&`.

The `&` operator compares corresponding bits of two operands. If both bits are 1, it gives 1. If either of the bits is not 1, it gives 0. For example,

```txt
12 = 00001100 (In Binary)
25 = 00011001 (In Binary)

Bit Operation of 12 and 25
  00001100
& 00011001
  ________
  00001000  = 8 (In decimal)
```

```java
class BitwiseAND {
    public static void main(String[] args) {

        int number1 = 12, number2 = 25, result;

        result = number1 & number2;
        System.out.println(result);
    }
}
```

## 3. Bitwise Complement

**Bitwise complement** is an **unary operator**(一元运算子) (works on only one operand). It is denoted by `~`.

The `~` operator inverts the bit pattern. It makes every `0` to `1`, and every `1` to `0`.

```txt
35 = 00100011 (In Binary)

Bitwise complement Operation of 35
~ 00100011
  ________
  11011100  = 220 (In decimal)
```

```java
class Complement {
    public static void main(String[] args) {

        int number = 35, result;

        result = ~number;
        System.out.println(result);
    }
}
```

It's because the compiler is showing `2's` **complement** of that number; negative notation of the binary number. 

For any integer `n`, **2's complement** of `n` will be `-(n+1)`.

```txt
 Decimal         Binary                      2's complement
---------       ---------          ---------------------------------------  
  0             00000000           -(11111111+1) = -00000000 = -0(decimal)
  1             00000001           -(11111110+1) = -11111111 = -256(decimal)
 12             00001100           -(11110011+1) = -11110100 = -244(decimal)
220             11011100           -(00100011+1) = -00100100 = -36(decimal)

Note: Overflow is ignored while computing 2's complement.
```

## 4. Bitwise XOR

Bitwise XOR is a binary operator (operates on two operands). It's denoted by `^`.

The `^` operator compares corresponding bits of two operands. If corresponding bits are different, it gives 1. If corresponding bits are same, it gives 0. For example,

```txt
12 = 00001100 (In Binary)
25 = 00011001 (In Binary)

Bitwise XOR Operation of 12 and 25
  00001100
| 00011001
  ________
  00010101  = 21 (In decimal)
```

```java
class Xor {
    public static void main(String[] args) {

        int number1 = 12, number2 = 25, result;

        result = number1 ^ number2;
        System.out.println(result);
    }
}
```

## 5. Signed Left Shift

The left shift operator `<<` shifts a bit pattern to the left by certain number of specified bits, and zero bits are shifted into the low-order positions.

```txt
212 (In binary: 11010100)

212 << 1 evaluates to 424 (In binary: 110101000)
212 << 0 evaluates to 212 (In binary: 11010100)
212 << 4 evaluates to 3392 (In binary: 110101000000)
```

```java
class LeftShift {
    public static void main(String[] args) {

        int number = 212, result;

        System.out.println(number << 1);
        System.out.println(number << 0);
        System.out.println(number << 4);
    }
}
```

## 6. Signed Right Shift

The right shift operator `>>` shifts a bit pattern to the right by certain number of specified bits.

```txt
212 (In binary: 11010100)

212 >> 1 evaluates to 106 (In binary: 01101010)
212 >> 0 evaluates to 212 (In binary: 11010100)
212 >> 8 evaluates to 0 (In binary: 00000000)
```

```java
class RightShift {
    public static void main(String[] args) {

        int number = 212, result;

        System.out.println(number >> 1);
        System.out.println(number >> 0);
        System.out.println(number >> 8);
    }
}
```

## 7. Unsigned Right Shift

The unsigned right shift operator `>>>` shifts zero into the leftmost position.

```java
class RightShift {
    public static void main(String[] args) {

        int number1 = 5, number2 = -5;

        // Signed right shift 
        System.out.println(number1 >> 1);

        // Unsigned right shift
        System.out.println(number1 >>> 1);

        // Signed right shift 
        System.out.println(number2 >> 1);

        // Unsigned right shift
        System.out.println(number2 >>> 1);
    }
}
```

Output:

```txt
2
2
-3
2147483645
```

## Reference

- [Java Bitwise and Bit Shift Operators](https://www.programiz.com/java-programming/bitwise-operators)
