# strictfp

<!-- TOC -->

- [1. Some Rules about strictfp](#1-some-rules-about-strictfp)
- [2. Java strictfp keyword Examples](#2-java-strictfp-keyword-examples)
- [3. Reference](#3-reference)

<!-- /TOC -->

In Java, the `strictfp` keyword is used to force the precision of floating point calculations (float or double) in Java conform to [IEEE’s 754 standard](http://ieeexplore.ieee.org/xpl/mostRecentIssue.jsp?punumber=2355), explicitly. **Without using `strictfp` keyword**, the floating point precision depends on target platform’s hardware, i.e. CPU’s floating point processing capability. In other words, **using `strictfp`** ensures result of floating point computations is always same on all platforms.

The `strictfp` keyword can be applied for `classes`, `interfaces` and `methods`.

## 1. Some Rules about strictfp

- `strictfp` cannot be applied for **constructors**.
- If an `interface` or `class` is declared with `strictfp`, then all methods and nested types within that interface or class are implicitly `strictfp`.
- `strictfp` cannot be applied for `interface` methods.

## 2. Java strictfp keyword Examples

The following **class** is declared with `strictfp`, hence all the floating point computations within that class conform to **IEEE’s 754 standard**:

```java
strictfp class StrictFPClass {
    double num1 = 10e+102;
    double num2 = 6e+08;
    double calculate() {
        return num1 + num2;
    }
}
```

The following **interface** is declared with `strictfp`, but its methods cannot:

```java
strictfp interface StrictFPInterface {
    double calculate();
    strictfp double compute();    // compile error
}
```

The following **method** is declared with `strictfp`:

```java
class StrictFPMethod {
    strictfp double computeTotal(double x, double y) {
        return x + y;
    }
}
```

## 3. Reference

- [Java strictfp keyword example](https://www.codejava.net/java-core/the-java-language/java-keyword-strictfp)
