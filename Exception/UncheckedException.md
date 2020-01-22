# Unchecked Exception

<!-- TOC -->

- [1. Runtime Exception](#1-runtime-exception)
  - [1.1. ArrayIndexOutOfBoundsException](#11-arrayindexoutofboundsexception)
  - [1.2. NullPointerException](#12-nullpointerexception)
  - [1.3. NumberFormatException](#13-numberformatexception)
  - [1.4. ClassCastException](#14-classcastexception)
  - [1.5. IllegalArgumentException](#15-illegalargumentexception)
  - [1.6. IllegalStateException](#16-illegalstateexception)
- [2. Error](#2-error)
  - [2.1. NoClassDefFoundError](#21-noclassdeffounderror)

<!-- /TOC -->

- RuntimeException
  - NullPointerException
  - IllegalArgumentException
    - NumberFormatException
  - IndexOutOfBoundsException
    - ArrayIndexOutOfBoundsException

## 1. Runtime Exception

### 1.1. ArrayIndexOutOfBoundsException

thrown by JVM when your code uses an array index, which is is outside the array's bounds. For example,

```java
int[] anArray = new int[3];
System.out.println(anArray[3]);
```

```txt
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 3
```

### 1.2. NullPointerException

thrown by the JVM when your code attempts to use a `null` reference where an object reference is required. For example,

```java
String[] strs = new String[3];
System.out.println(strs[0].length());
```

```txt
Exception in thread "main" java.lang.NullPointerException
```

### 1.3. NumberFormatException

Thrown programmatically (e.g., by `Integer.parseInt()`) when an attempt is made to convert a string to a numeric type, but the string does not have the appropriate format. For example,

```java
Integer.parseInt("abc");
```

```txt
Exception in thread "main" java.lang.NumberFormatException: For input string: "abc"
```

### 1.4. ClassCastException

thrown by JVM when an attempt is made to cast an object reference fails. For example,

```java
Object o = new Object();
Integer i = (Integer)o;
```

```txt
Exception in thread "main" java.lang.ClassCastException: java.lang.Object cannot be cast to java.lang.Integer
```

### 1.5. IllegalArgumentException

thrown programmatically to indicate that a method has been passed an illegal or inappropriate argument. You could re-use this exception for your own methods.

### 1.6. IllegalStateException

thrown programmatically when a method is invoked and the program is not in an appropriate state for that method to perform its task. This typically happens when a method is invoked out of sequence, or perhaps a method is only allowed to be invoked once and an attempt is made to invoke it again.

## 2. Error

### 2.1. NoClassDefFoundError

thrown by the JVM or class loader when the definition of a class cannot be found.

Prior to JDK 1.7, you will see this exception call stack trace if you try to run a non-existent class. JDK 1.7 simplifies the error message to "Error: Could not find or load main class xxx".

```java
// File: A.java
public class A {
    static int data = 1 / 0;
}
```

```java
// File: B.java
public class B {
    public static void main(String[] args) {
        try {
            Class.forName("A");
        }
        catch(Throwable t) {
            // do nothing
        }

        A a = new A();
    }
}
```

```bash
$ javac *.java
$ java B
Exception in thread "main" java.lang.NoClassDefFoundError: Could not initialize class A
	at B.main(B.java:10)
```
