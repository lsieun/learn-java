# Modifier Types

**Modifiers** are **keywords** that you add to those definitions to change their meanings. 

> Modifiers是属于关键字

Java language has a wide variety of modifiers, including the following:

- Java Access Modifiers
- Non Access Modifiers

## Access Control Modifiers

Java provides a number of **access modifiers** to set access levels for **classes**, **variables**, **methods** and **constructors**. 

The **four** access levels are:

- Visible to the package, **the default**. No modifiers are needed.
- Visible to the class only (`private`).
- Visible to the world (`public`).
- Visible to the package and all subclasses (`protected`).


## Non-Access Modifiers

Java provides a number of **non-access modifiers** to achieve many other functionality.

- The `static` modifier for creating class `methods` and `variables`.
- The `final` modifier for finalizing the implementations of `classes`, `methods`, and `variables`.
- The `abstract` modifier for creating abstract `classes` and `methods`.
- The `synchronized` and `volatile` modifiers, which are used for **threads**.

```java
public class Modifier {
    private boolean myFlag;
    static final double week = 9.5;
    protected static final int BOXWIDTH = 42;

    public static void main(String[] args) {
        // do nothing
    }
}
```

```bash
$ javac -g:vars Modifier.java
$ javap -c -v Modifier
```

Output:

```txt
public class Modifier
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
{
  static final double week;
    descriptor: D
    flags: ACC_STATIC, ACC_FINAL
    ConstantValue: double 9.5d

  protected static final int BOXWIDTH;
    descriptor: I
    flags: ACC_PROTECTED, ACC_STATIC, ACC_FINAL
    ConstantValue: int 42

  public Modifier();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   LModifier;

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: ACC_PUBLIC, ACC_STATIC
    Code:
      stack=0, locals=1, args_size=1
         0: return
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       1     0  args   [Ljava/lang/String;
}
```


