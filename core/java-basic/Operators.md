# Basic Operators

Java provides a rich set of **operators** to manipulate **variables**. 

We can divide all the Java operators into the following groups

- Arithmetic Operators
- Relational Operators
- Bitwise Operators
- Logical Operators
- Assignment Operators
- Misc Operators

## 1. Arithmetic Operators

**Arithmetic operators** are used in mathematical expressions in the same way that they are used in **algebra**. 

The following table lists the arithmetic operators −

Assume integer variable `A` holds `10` and variable `B` holds `20`, then −



| Operator            | Description                                                  | Example                 |
| :------------------ | ------------------------------------------------------------ | ----------------------- |
| `+`(Addition)       | Adds values on either side of the operator.                  | `A + B` will give `30`  |
| `-`(Subtraction)    | Subtracts right-hand operand from left-hand operand.         | `A - B` will give `-10` |
| `*`(Multiplication) | Multiplies values on either side of the operator.            | `A * B` will give `200` |
| `/`(Division)       | Divides left-hand operand by right-hand operand.             | `B / A` will give `2`   |
| `%`(Modulus)        | Divides left-hand operand by right-hand operand and returns remainder. | `B % A` will give `0`   |
| `++`(Increment)     | Increases the value of operand by 1.                         | `B++` gives `21`        |
| `--`(Decrement)     | Decreases the value of operand by 1.                         | `B--` gives `19`        |


Arithmetic.java

```java
public class Arithmetic {
    public void add() {
        int a = 10;
        int b = 20;
        int result = a + b;
    }

    public void sub() {
        int a = 10;
        int b = 20;
        int result = a - b;
    }

    public void multiply() {
        int a = 10;
        int b = 20;
        int result = a * b;
    }

    public void divide() {
        int a = 10;
        int b = 20;
        int result = b / a;
    }

    public void modulus() {
        int a = 10;
        int b = 20;
        int result = b % a;
    }

    public void incr() {
        int a = 10;
        int b = 20;
        int result = b++;
    }

    public void decr() {
        int a = 10;
        int b = 20;
        int result = b--;
    }
}
```

```bash
$ javac -g:vars Arithmetic.java
$ javap -c -l Arithmetic
```

Output:

```txt
public class Arithmetic {
  public Arithmetic();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       5     0  this   LArithmetic;

  public void add();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: iadd
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LArithmetic;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void sub();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: isub
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LArithmetic;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void multiply();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: imul
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LArithmetic;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void divide();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_2
       7: iload_1
       8: idiv
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LArithmetic;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void modulus();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_2
       7: iload_1
       8: irem
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LArithmetic;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void incr();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_2
       7: iinc          2, 1
      10: istore_3
      11: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      12     0  this   LArithmetic;
          3       9     1     a   I
          6       6     2     b   I
         11       1     3 result   I

  public void decr();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_2
       7: iinc          2, -1
      10: istore_3
      11: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      12     0  this   LArithmetic;
          3       9     1     a   I
          6       6     2     b   I
         11       1     3 result   I
}
```


## 2. Relational Operators

There are following relational operators supported by Java language.

Assume variable `A` holds `10` and variable `B` holds `20`, then

| Operator                        | Description                                                  | Example                 |
| ------------------------------- | ------------------------------------------------------------ | ----------------------- |
| `==` (equal to)                 | Checks if the values of two operands are equal or not, if yes then condition becomes true. | (`A == B`) is not true. |
| `!=` (not equal to)             | Checks if the values of two operands are equal or not, if values are not equal then condition becomes true. | (`A != B`) is true.     |
| `>` (greater than)              | Checks if the value of left operand is greater than the value of right operand, if yes then condition becomes true. | (`A > B`) is not true.  |
| `<` (less than)                 | Checks if the value of left operand is less than the value of right operand, if yes then condition becomes true. | (`A < B`) is true.      |
| `>= `(greater than or equal to) | Checks if the value of left operand is greater than or equal to the value of right operand, if yes then condition becomes true. | (`A >= B`) is not true. |
| `<=` (less than or equal to)    | Checks if the value of left operand is less than or equal to the value of right operand, if yes then condition becomes true. | (`A <= B`) is true.     |


Relational.java

```java
public class Relational {

    public void testEqual() {
        int a = 10;
        int b = 20;
        boolean flag = (a == b);
    }

    public void testNotEqual() {
        int a = 10;
        int b = 20;
        boolean flag = (a != b);
    }

    public void testGreater() {
        int a = 10;
        int b = 20;
        boolean flag = (a > b);
    }

    public void testLess() {
        int a = 10;
        int b = 20;
        boolean flag = (a < b);
    }

    public void testGreaterEqual() {
        int a = 10;
        int b = 20;
        boolean flag = (a >= b);
    }

    public void testLessEqual() {
        int a = 10;
        int b = 20;
        boolean flag = (a <= b);
    }
}
```

```bash
$ javac -g:vars Relational.java 
$ javap -c -l Relational
```

Output:

```txt
public class Relational {
  public Relational();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       5     0  this   LRelational;

  public void testEqual();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmpne     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LRelational;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3  flag   Z

  public void testNotEqual();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmpeq     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LRelational;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3  flag   Z

  public void testGreater();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmple     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LRelational;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3  flag   Z

  public void testLess();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmpge     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LRelational;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3  flag   Z

  public void testGreaterEqual();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmplt     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LRelational;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3  flag   Z

  public void testLessEqual();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmpgt     15
      11: iconst_1
      12: goto          16
      15: iconst_0
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LRelational;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3  flag   Z
}
```


## 3. Bitwise Operators

Java defines several **bitwise operators**, which can be applied to the `integer` types, `long`, `int`, `short`, `char`, and `byte`.

**Bitwise operator** works on **bits** and performs **bit-by-bit operation**. Assume if `a = 60` and `b = 13`; now in binary format they will be as follows −

```txt
a = 0011 1100
b = 0000 1101
```
-----------------

```txt
a&b = 0000 1100
a|b = 0011 1101
a^b = 0011 0001
~a  = 1100 0011
```

The following table lists the **bitwise operators** −

Assume integer variable `A` holds `60` and variable `B` holds `13` then −

| Operator                      | Description                                                  | Example                                                      |
| ----------------------------- | ------------------------------------------------------------ | ------------------------------------------------------------ |
| `&` (bitwise and)             | Binary AND Operator copies a bit to the result if it exists in both operands. | (`A & B`) will give `12` which is `0000 1100`                |
| `|` (bitwise or)              | Binary OR Operator copies a bit if it exists in either operand. | (`A | B`) will give `61` which is `0011 1101`                |
| `^` (bitwise XOR)             | Binary XOR Operator copies the bit if it is set in one operand but not both. | (`A ^ B`) will give `49` which is `0011 0001`                |
| `~` (bitwise compliment)      | Binary Ones Complement Operator is unary and has the effect of 'flipping' bits. | (`~A` ) will give `-61` which is `1100 0011` in 2's complement form due to a signed binary number. |
| `<<` (left shift)             | Binary Left Shift Operator. The left operands value is moved left by the number of bits specified by the right operand. | `A << 2` will give `240` which is `1111 0000`                |
| `>>` (right shift)            | Binary Right Shift Operator. The left operands value is moved right by the number of bits specified by the right operand. | `A >> 2` will give `15` which is `1111`                      |
| `>>>` (zero fill right shift) | Shift right zero fill operator. The left operands value is moved right by the number of bits specified by the right operand and shifted values are filled up with zeros. | `A >>>2` will give `15` which is `0000 1111`                 |

Bitwise.java

```java
public class Bitwise {

    public void and() {
        int a = 60;
        int b = 13;
        int result = a & b;
    }

    public void or() {
        int a = 60;
        int b = 13;
        int result = a | b;
    }

    public void xor() {
        int a = 60;
        int b = 13;
        int result = a ^ b;
    }

    public void compliment() {
        int a = 60;
        int result = ~a;
    }

    public void leftShift() {
        int a = 60;
        int result = a << 2;
    }

    public void rightShift() {
        int a = 60;
        int result = a >> 2;
    }

    public void zeroFillRightShift() {
        int a = 60;
        int result = a >>> 2;
    }
}

```

```bash
$ javac -g:vars Bitwise.java 
$ javap -c -l Bitwise
```

Output:

```txt
public class Bitwise {
  public Bitwise();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       5     0  this   LBitwise;

  public void and();
    Code:
       0: bipush        60
       2: istore_1
       3: bipush        13
       5: istore_2
       6: iload_1
       7: iload_2
       8: iand
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LBitwise;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void or();
    Code:
       0: bipush        60
       2: istore_1
       3: bipush        13
       5: istore_2
       6: iload_1
       7: iload_2
       8: ior
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LBitwise;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void xor();
    Code:
       0: bipush        60
       2: istore_1
       3: bipush        13
       5: istore_2
       6: iload_1
       7: iload_2
       8: ixor
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LBitwise;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3 result   I

  public void compliment();
    Code:
       0: bipush        60
       2: istore_1
       3: iload_1
       4: iconst_m1
       5: ixor
       6: istore_2
       7: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       8     0  this   LBitwise;
          3       5     1     a   I
          7       1     2 result   I

  public void leftShift();
    Code:
       0: bipush        60
       2: istore_1
       3: iload_1
       4: iconst_2
       5: ishl
       6: istore_2
       7: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       8     0  this   LBitwise;
          3       5     1     a   I
          7       1     2 result   I

  public void rightShift();
    Code:
       0: bipush        60
       2: istore_1
       3: iload_1
       4: iconst_2
       5: ishr
       6: istore_2
       7: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       8     0  this   LBitwise;
          3       5     1     a   I
          7       1     2 result   I

  public void zeroFillRightShift();
    Code:
       0: bipush        60
       2: istore_1
       3: iload_1
       4: iconst_2
       5: iushr
       6: istore_2
       7: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       8     0  this   LBitwise;
          3       5     1     a   I
          7       1     2 result   I
}
```


## 4. Logical Operators

The following table lists the **logical operators** −

Assume Boolean variables `A` holds `true` and variable `B` holds `false`, then −

| Operator           | Description                                                  | Example               |
| ------------------ | ------------------------------------------------------------ | --------------------- |
| `&&` (logical and) | Called Logical AND operator. If both the operands are non-zero, then the condition becomes true. | `(A && B)` is `false` |
| `||` (logical or)  | Called Logical OR Operator. If any of the two operands are non-zero, then the condition becomes true. | `(A || B)` is `true`  |
| `!` (logical not)  | Called Logical NOT Operator. Use to reverses the logical state of its operand. If a condition is true then Logical NOT operator will make false. | `!(A && B)` is true   |


Logical.java

```java
public class Logical {

    public void add() {
        boolean a = true;
        boolean b = false;
        boolean result = (a && b);
    }

    public void or() {
        boolean a = true;
        boolean b = false;
        boolean result = (a || b);
    }

    public void not() {
        boolean a = true;
        boolean result = !a;
    }
}
```

```bash
$ javac -g:vars Logical.java 
$ javap -c -l Logical
```

Output:

```txt
public class Logical {
  public Logical();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       5     0  this   LLogical;

  public void add();
    Code:
       0: iconst_1
       1: istore_1
       2: iconst_0
       3: istore_2
       4: iload_1
       5: ifeq          16
       8: iload_2
       9: ifeq          16
      12: iconst_1
      13: goto          17
      16: iconst_0
      17: istore_3
      18: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      19     0  this   LLogical;
          2      17     1     a   Z
          4      15     2     b   Z
         18       1     3 result   Z

  public void or();
    Code:
       0: iconst_1
       1: istore_1
       2: iconst_0
       3: istore_2
       4: iload_1
       5: ifne          12
       8: iload_2
       9: ifeq          16
      12: iconst_1
      13: goto          17
      16: iconst_0
      17: istore_3
      18: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      19     0  this   LLogical;
          2      17     1     a   Z
          4      15     2     b   Z
         18       1     3 result   Z

  public void not();
    Code:
       0: iconst_1
       1: istore_1
       2: iload_1
       3: ifne          10
       6: iconst_1
       7: goto          11
      10: iconst_0
      11: istore_2
      12: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      13     0  this   LLogical;
          2      11     1     a   Z
         12       1     2 result   Z
}
```

## 5. Assignment Operators

Following are **the assignment operators** supported by Java language −

| Operator | Description                                                  | Example                                           |
| -------- | ------------------------------------------------------------ | ------------------------------------------------- |
| `=`      | Simple assignment operator. Assigns values from right side operands to left side operand. | `C = A + B` will assign value of `A + B` into `C` |
| `+=`     | Add AND assignment operator. It adds right operand to the left operand and assign the result to left operand. | `C += A` is equivalent to `C = C + A`             |
| `-=`     | Subtract AND assignment operator. It subtracts right operand from the left operand and assign the result to left operand. | `C -= A` is equivalent to `C = C – A`             |
| `*=`     | Multiply AND assignment operator. It multiplies right operand with the left operand and assign the result to left operand. | `C *= A` is equivalent to `C = C * A`             |
| `/=`     | Divide AND assignment operator. It divides left operand with the right operand and assign the result to left operand. | `C /= A` is equivalent to `C = C / A`             |
| `%=`     | Modulus AND assignment operator. It takes modulus using two operands and assign the result to left operand. | `C %= A` is equivalent to `C = C % A`             |
| `<<=`    | Left shift AND assignment operator.                          | `C <<= 2` is same as `C = C << 2`                 |
| `>>=`    | Right shift AND assignment operator.                         | `C >>= 2` is same as `C = C >> 2`                 |
| `&=`     | Bitwise AND assignment operator.                             | `C &= 2` is same as `C = C & 2`                   |
| `^=`     | bitwise exclusive OR and assignment operator.                | `C ^= 2` is same as `C = C ^ 2`                   |
| `|=`     | bitwise inclusive OR and assignment operator.                | `C |= 2` is same as `C = C | 2`                   |

Assignment.java

```java
public class Assignment {
    
    public void assign() {
        int a = 10;
        int b = 20;
        int c = a + b;
    }

    public void addAssign() {
        int a = 10;
        int b = 20;
        b += a;
    }

    public void subAssign() {
        int a = 10;
        int b = 20;
        b -= a;
    }
}

```

```bash
$ javac -g:vars Assignment.java 
$ javap -c -l Assignment
```

Output:

```txt
public class Assignment {
  public Assignment();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       5     0  this   LAssignment;

  public void assign();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: iadd
       9: istore_3
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LAssignment;
          3       8     1     a   I
          6       5     2     b   I
         10       1     3     c   I

  public void addAssign();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_2
       7: iload_1
       8: iadd
       9: istore_2
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LAssignment;
          3       8     1     a   I
          6       5     2     b   I

  public void subAssign();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_2
       7: iload_1
       8: isub
       9: istore_2
      10: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      11     0  this   LAssignment;
          3       8     1     a   I
          6       5     2     b   I
}
```


## 6. Misc Operators

There are few other operators supported by Java Language.

**Conditional Operator (`?:`)**

**Conditional operator** is also known as the **ternary operator**(三元运算符). This operator consists of **three** operands(操作数；运算对象) and is used to evaluate `Boolean` expressions. The goal of the operator is to decide, which value should be assigned to the variable. The operator is written as −

```txt
variable x = (expression) ? value if true : value if false
```

**instanceof Operator**

This operator is used only for **object reference variables**. The operator checks whether the object is of a particular type (class type or interface type). instanceof operator is written as −

```txt
( Object reference variable ) instanceof  (class/interface type)
```

If **the object referred by the variable** on the left side of the operator passes the `IS-A` check for **the class/interface type** on the right side, then the result will be true. 

Miscellaneous.java

```java
public class Miscellaneous {
    public void testConditional() {
        int a = 10;
        int b = 20;
        int result = (a > b) ? a : b;
    }

    public void testInstanceOf() {
        String name = "Hello";
        boolean result = name instanceof String;
    }
}

```


```bash
$ javac -g:vars Miscellaneous.java 
$ javap -c -l Miscellaneous
```

Output:

```txt
public class Miscellaneous {
  public Miscellaneous();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       5     0  this   LMiscellaneous;

  public void testConditional();
    Code:
       0: bipush        10
       2: istore_1
       3: bipush        20
       5: istore_2
       6: iload_1
       7: iload_2
       8: if_icmple     15
      11: iload_1
      12: goto          16
      15: iload_2
      16: istore_3
      17: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      18     0  this   LMiscellaneous;
          3      15     1     a   I
          6      12     2     b   I
         17       1     3 result   I

  public void testInstanceOf();
    Code:
       0: ldc           #2                  // String Hello
       2: astore_1
       3: aload_1
       4: instanceof    #3                  // class java/lang/String
       7: istore_2
       8: return
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0       9     0  this   LMiscellaneous;
          3       6     1  name   Ljava/lang/String;
          8       1     2 result   Z
}
```

