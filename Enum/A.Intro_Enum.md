# Intro Enum

<!-- TOC -->

- [1. Intro](#1-intro)
  - [1.1. Class Modifier: Enums as special classes](#11-class-modifier-enums-as-special-classes)
  - [1.2. SuperClass: Enums and generics](#12-superclass-enums-and-generics)
  - [1.3. Fields: Enum and static Fields](#13-fields-enum-and-static-fields)
  - [1.4. Methods](#14-methods)
    - [1.4.1. `<init>:(Ljava/lang/String;I)V`](#141-initljavalangstringiv)
    - [1.4.2. `<clinit>:()V`](#142-clinitv)
    - [1.4.3. `values:()[LDaysOfTheWeek;`](#143-valuesldaysoftheweek)
    - [1.4.4. `valueOf:(Ljava/lang/String;)LDaysOfTheWeek;`](#144-valueofljavalangstringldaysoftheweek)
- [2. Further](#2-further)
  - [2.1. Fields: Enums and instance fields](#21-fields-enums-and-instance-fields)
  - [2.2. Interfaces: Enums and interfaces](#22-interfaces-enums-and-interfaces)
  - [2.3. Methods: Convenient Enums methods](#23-methods-convenient-enums-methods)
- [3. Use](#3-use)
  - [3.1. Creating Enum from String](#31-creating-enum-from-string)
  - [3.2. Switch Enum](#32-switch-enum)

<!-- /TOC -->

## 1. Intro

理解一个类，可以从5个方面着手：

```java
public/*(1)修饰符*/ class A extends B/*(2)父类*/ implements C/*(3)接口*/ {
    private int i; // (4)字段

    public void test() { // (5)方法
        //
    }
}
```

```java
public enum DaysOfTheWeek {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
```

### 1.1. Class Modifier: Enums as special classes

第一点需要注意：`DaysOfTheWeek`的父类是`Enum`类，它自身具有`ACC_PUBLIC`、`ACC_FINAL`、`ACC_SUPER`和`ACC_ENUM`的标识。

```txt
constant_pool
    |022| CONSTANT_Class {Value='#68', HexCode='070044'}
    |068| CONSTANT_Utf8 {Value='java/lang/Enum', HexCode='01000e6a6176612f6c616e672f456e756d'}
......
class_info='4031000400160000'
    access_flags='4031' ([ACC_PUBLIC,ACC_FINAL,ACC_SUPER,ACC_ENUM])
    this_class='0004' (4)
    super_class='0016' (22)
    interfaces_count='0000' (0)
    interfaces='null' ([])
```

### 1.2. SuperClass: Enums and generics

Although it is not visible from a first glance, there is a relation between **enums** and **generics** in Java. Every single enum in Java is automatically inherited from the generic `Enum<T>` class, where `T` is the enum type itself. The Java compiler does this transformation on behalf of the developer at compile time, expanding enum declaration `public enum DaysOfTheWeek` to something like this:

```java
public class DaysOfTheWeek extends Enum<DaysOfTheWeek> {
    // Other declarations here
}
```

It also explains why enums can implement interfaces but cannot extend other classes<sub>注：这里解释了enums为什么可以实现接口，而不能继承类</sub>: they implicitly extend `Enum<T>` and Java does not support multiple inheritance.

The fact that every enum extends `Enum<T>` allows to define generic classes, interfaces and methods which expect the instances of enum types as arguments or type parameters. For example:

```java
public<T extends Enum <?>> void performAction(final T instance) {
    // Perform some action here
}
```

In the method declaration above, the type `T` is constrained to be the instance of any enum and Java compiler will verify that.

### 1.3. Fields: Enum and static Fields

```txt
FieldInfo {Value='MONDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001700180000'}
FieldInfo {Value='TUESDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001900180000'}
FieldInfo {Value='WEDNESDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001a00180000'}
FieldInfo {Value='THURSDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001b00180000'}
FieldInfo {Value='FRIDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001c00180000'}
FieldInfo {Value='SATURDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001d00180000'}
FieldInfo {Value='SUNDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001e00180000'}
FieldInfo {Value='$VALUES:[DaysOfTheWeek;', AccessFlags='[ACC_PRIVATE,ACC_STATIC,ACC_FINAL,ACC_SYNTHETIC]', Attrs='[]', HexCode='101a001f00200000'}

```

第一点需要注意：`DaysOfTheWeek`中的每一个字段（field）都有`ACC_PUBLIC`、`ACC_STATIC`、`ACC_FINAL`和`ACC_ENUM`标识。这个和Interface当中的字段的标识相似，只是多了`ACC_ENUM`标识

```txt
FieldInfo {Value='MONDAY:DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001700180000'}
```

第二点需要注意：`DaysOfTheWeek`的`.class`文件会自动生成一个`$VALUES`字段，这个字段具有的标识是`ACC_PRIVATE`、`ACC_STATIC`、`ACC_FINAL`和`ACC_SYNTHETIC`。

```txt
FieldInfo {Value='$VALUES:[DaysOfTheWeek;', AccessFlags='[ACC_PRIVATE,ACC_STATIC,ACC_FINAL,ACC_SYNTHETIC]', Attrs='[]', HexCode='101a001f00200000'}
```

### 1.4. Methods

```txt
MethodInfo {Value='values:()[LDaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC]', Attrs='[Code]', HexCode='...'}
MethodInfo {Value='valueOf:(Ljava/lang/String;)LDaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC]', Attrs='[Code, MethodParameters]', HexCode='...'}
MethodInfo {Value='<init>:(Ljava/lang/String;I)V', AccessFlags='[ACC_PRIVATE]', Attrs='[Code, MethodParameters, Signature]', HexCode='...'}
MethodInfo {Value='<clinit>:()V', AccessFlags='[ACC_STATIC]', Attrs='[Code]', HexCode='...'}
```

#### 1.4.1. `<init>:(Ljava/lang/String;I)V`

需要注意：默认的构造函数是`<init>:(Ljava/lang/String;I)V`，它会调用父类的构造函数`Enum(String name, int ordinal)`

```txt
0000: aload_0              // 2a
0001: aload_1              // 2b
0002: iload_2              // 1c
0003: invokespecial   #6   // b70006     || java/lang/Enum.<init>:(Ljava/lang/String;I)V
0006: return               // b1
```

```java
/**
 * Sole constructor.  Programmers cannot invoke this constructor.
 * It is for use by code emitted by the compiler in response to
 * enum type declarations.
 *
 * @param name - The name of this enum constant, which is the identifier
 *               used to declare it.
 * @param ordinal - The ordinal of this enumeration constant (its position
 *         in the enum declaration, where the initial constant is assigned
 *         an ordinal of zero).
 */
protected Enum(String name, int ordinal) {
    this.name = name;
    this.ordinal = ordinal;
}
```

#### 1.4.2. `<clinit>:()V`

```txt
（1）创建MONDAY对象
0000: new             #4   // bb0004     || DaysOfTheWeek
0003: dup                  // 59
0004: ldc             #9   // 1209       || MONDAY
0006: iconst_0             // 03
0007: invokespecial   #10  // b7000a     || DaysOfTheWeek.<init>:(Ljava/lang/String;I)V
0010: putstatic       #11  // b3000b     || DaysOfTheWeek.MONDAY:LDaysOfTheWeek;
（1）创建TUESDAY对象
0013: new             #4   // bb0004     || DaysOfTheWeek
0016: dup                  // 59
0017: ldc             #12  // 120c       || TUESDAY
0019: iconst_1             // 04
0020: invokespecial   #10  // b7000a     || DaysOfTheWeek.<init>:(Ljava/lang/String;I)V
0023: putstatic       #13  // b3000d     || DaysOfTheWeek.TUESDAY:LDaysOfTheWeek;
...
（1）创建SUNDAY对象
0078: new             #4   // bb0004     || DaysOfTheWeek
0081: dup                  // 59
0082: ldc             #21  // 1215       || SUNDAY
0084: bipush          6    // 1006
0086: invokespecial   #10  // b7000a     || DaysOfTheWeek.<init>:(Ljava/lang/String;I)V
0089: putstatic       #8   // b30008     || DaysOfTheWeek.SUNDAY:LDaysOfTheWeek;
（2）创建包含7个元素的数组
0092: bipush          7    // 1007
0094: anewarray       #4   // bd0004     || DaysOfTheWeek
（3）添加MONDYA对象
0097: dup                  // 59
0098: iconst_0             // 03
0099: getstatic       #11  // b2000b     || DaysOfTheWeek.MONDAY:LDaysOfTheWeek;
0102: aastore              // 53
（3）添加TUESDAY对象
0103: dup                  // 59
0104: iconst_1             // 04
0105: getstatic       #13  // b2000d     || DaysOfTheWeek.TUESDAY:LDaysOfTheWeek;
0108: aastore              // 53
...
（3）添加SUNDAY对象
0133: dup                  // 59
0134: bipush          6    // 1006
0136: getstatic       #8   // b20008     || DaysOfTheWeek.SUNDAY:LDaysOfTheWeek;
0139: aastore              // 53
（4）将数组保存到$VALUES字段中
0140: putstatic       #1   // b30001     || DaysOfTheWeek.$VALUES:[LDaysOfTheWeek;
0143: return               // b1
```

#### 1.4.3. `values:()[LDaysOfTheWeek;`

```txt
获取$VALUES字段的值
0000: getstatic       #1   // b20001     || DaysOfTheWeek.$VALUES:[LDaysOfTheWeek;
进行clone
0003: invokevirtual   #2   // b60002     || [LDaysOfTheWeek;.clone:()Ljava/lang/Object;
0006: checkcast       #3   // c00003     || [LDaysOfTheWeek;
0009: areturn              // b0
```

#### 1.4.4. `valueOf:(Ljava/lang/String;)LDaysOfTheWeek;`

```txt
0000: ldc             #4   // 1204       || DaysOfTheWeek
0002: aload_0              // 2a
0003: invokestatic    #5   // b80005     || java/lang/Enum.valueOf:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;
0006: checkcast       #4   // c00004     || DaysOfTheWeek
0009: areturn              // b0

LocalVariableTable:
index  start_pc  length  name_and_type
    0         0      10  name:Ljava/lang/String;
```

## 2. Further

### 2.1. Fields: Enums and instance fields

`DaysOfTheWeek`的字段（field）都是`static`类型的。下面介绍`non-static`类型的字段（field）。

Enums are specialized classes and as such are extensible. It means they can have **instance fields, constructors and methods** (although **the only limitations** are that **the default no-args constructor cannot be declared** and **all constructors must be `private`**).

Let us add the property `isWeekend` to every day of the week using the instance field and constructor.

```java
public enum DaysOfTheWeekFields {
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false),
    THURSDAY(false),
    FRIDAY(false),
    SATURDAY(true),
    SUNDAY(true); // 这些是 static fields

    private final boolean isWeekend; // 这是instance fields

    private DaysOfTheWeekFields(final boolean isWeekend) {
        this.isWeekend = isWeekend; // 这是instance constructors
    }

    public boolean isWeekend() { // 这是instance methods
        return isWeekend;
    }

}
```

As we can see, the values of the enums are just constructor calls with the simplification that the `new` keyword is not required. The `isWeekend()` property could be used to detect if the value represents the week day or week-end.

**Instance fields** are an extremely useful capability of the enums in Java. They are used very often to associate some additional details with each value, using regular class declaration rules.<sub>注：这里讲instance fields的作用</sub>

有一点需要注意：构造方法`DaysOfTheWeekFields(final boolean isWeekend)`在`.class`文件中是`<init>:(Ljava/lang/String;IZ)V`

```java
0000: aload_0              // 2a
0001: aload_1              // 2b
0002: iload_2              // 1c
0003: invokespecial   #6   // b70006     || java/lang/Enum.<init>:(Ljava/lang/String;I)V
0006: aload_0              // 2a
0007: iload_3              // 1d
0008: putfield        #7   // b50007     || DaysOfTheWeekFields.isWeekend:Z
0011: return               // b1

LocalVariableTable:
index  start_pc  length  name_and_type
    0         0      12  this:LDaysOfTheWeekFields;
    3         0      12  isWeekend:Z
```

### 2.2. Interfaces: Enums and interfaces

Another interesting feature, which yet one more time confirms that enums are just specialized classes, is that they can implement interfaces (however enums cannot extend any other classes for the reasons explained later in the Enums and generics section). For example, let us introduce the interface `DayOfWeek`.

```java
public interface DayOfWeek {
    boolean isWeekend();
}

public enum DaysOfTheWeekInterfaces implements DayOfWeek {
    MONDAY() {
        @Override
        public boolean isWeekend() {
            return false;
        }
    },
    TUESDAY() {
        @Override
        public boolean isWeekend() {
            return false;
        }
    },
    WEDNESDAY() {
        @Override
        public boolean isWeekend() {
            return false;
        }
    },
    THURSDAY() {
        @Override
        public boolean isWeekend() {
            return false;
        }
    },
    FRIDAY() {
        @Override
        public boolean isWeekend() {
            return false;
        }
    },
    SATURDAY() {
        @Override
        public boolean isWeekend() {
            return true;
        }
    },
    SUNDAY() {
        @Override
        public boolean isWeekend() {
            return true;
        }
    };
}
```

The way we have implemented the interface is a bit verbose, however it is certainly possible to make it better by combining **instance fields** and **interfaces** together. For example:

```java
public enum DaysOfTheWeekFieldsInterfaces implements DayOfWeek {
    MONDAY(false),
    TUESDAY(false),
    WEDNESDAY(false),
    THURSDAY(false),
    FRIDAY(false),
    SATURDAY(true),
    SUNDAY(true);

    private final boolean isWeekend;

    private DaysOfTheWeekFieldsInterfaces(final boolean isWeekend) {
        this.isWeekend = isWeekend;
    }

    @Override
    public boolean isWeekend() {
        return isWeekend;
    }

}
```

### 2.3. Methods: Convenient Enums methods

The base `Enum<T>` class provides a couple of helpful methods which are automatically inherited by every enum instance.

| Method          | Description                                                  |
| --------------- | ------------------------------------------------------------ |
| `String name()` | Returns the name of this enum constant, exactly as declared in its enum declaration. |
| `int ordinal()` | Returns the ordinal of this enumeration constant (its position in its enum declaration, where the initial constant is assigned an ordinal of zero). |

Additionally, Java compiler automatically generates two more helpful `static` methods for every enum type it encounters (let us refer to the particular enum type as `T`).

| Method                   | Description                                               |
| ------------------------ | --------------------------------------------------------- |
| `T[] values()`           | Returns the all declared enum constants for the enum `T`. |
| `T valueOf(String name)` | Returns the enum constant `T` with the specified name.    |


Because of the presence of these methods and hard compiler work, there is one more benefit of using enums in your code: they can be used in `switch/case` statements. For example:

```java
public void performAction(DaysOfTheWeek instance) {
    switch(instance) {
        case MONDAY:
            // Do something
            break;
        case TUESDAY:
            // Do something
            break;
        // Other enum constants here
    }
}
```

## 3. Use

### 3.1. Creating Enum from String

You can create Enum from String by using `Enum.valueOf()` method. `valueOf()` is a static method which is added on every Enum class during compile time and it's implicitly available to all Enum along with `values()`, `name()` and `cardinal()` methods.

In order to create Enum from String, String must be same as declared Enum otherwise, the code will throw "java.lang.IllegalArgumentException: No enum const class".

```java
public class StringToEnum {
    private enum Currency {USD, AUD, GBP, EURO }  

    public static void main(String args[]) {

        //Converting String to Enum in Java
        String usd = "USD";

        //Enum to String using Enum.valueOf()
        Enum currency = Enum.valueOf(Currency.class, usd);

        //Enum to String using Currency.valueOf()
        currency = Currency.valueOf(usd);

        System.out.println("String to Enum Example : " + currency);

        //This Enum to String conversion will throw Exception
        String INR = "INR";
        //java.lang.IllegalArgumentException: No enum const class
        Currency rupee = Currency.valueOf("INR");
    }
}
```

Out:

```txt
String to Enum Example : USD
Exception in thread "main" java.lang.IllegalArgumentException: No enum const class test.CollectionTest$Currency.INR
        at java.lang.Enum.valueOf(Enum.java:196)
        at test.CollectionTest$Currency.valueOf(CollectionTest.java:16)
        at test.CollectionTest.main(CollectionTest.java:32)
Java Result: 1
```

### 3.2. Switch Enum

```java
public class EnumSwitch {

    public void performAction(DaysOfTheWeek instance) {
        String str = "";
        switch(instance) {
            case MONDAY:
                str = "mon";
                break;
            case TUESDAY:
                str = "tue";
                break;
            case WEDNESDAY:
                str = "wed";
                break;
            case THURSDAY:
                str = "thu";
                break;
            case FRIDAY:
                str = "fri";
                break;
            case SATURDAY:
                str = "sat";
                break;
            case SUNDAY:
                str = "sun";
                break;
        }
        System.out.println(str);
    }

}
```

```txt
处理String str = "";
0000: ldc             #2   // 1202       || ""
0002: astore_2             // 4d
获取EnumSwitch$1类的字段，类型为int[]
0003: getstatic       #3   // b20003     || EnumSwitch$1.$SwitchMap$lsieun$sample$java5_enum$DaysOfTheWeek:[I
获取Enum对象的ordinal值
0006: aload_1              // 2b
0007: invokevirtual   #4   // b60004     || DaysOfTheWeek.ordinal:()I
从数组int[]取出对应的value
0010: iaload               // 2e
0011: tableswitch     80   // aa000000500000000100000007000000290000002f000000350000003b00000041000000470000004d
      {
              1: 41
              2: 47
              3: 53
              4: 59
              5: 65
              6: 71
              7: 77
        default: 80
      }
0052: ldc             #5   // 1205       || mon
0054: astore_2             // 4d
0055: goto            36   // a70024
0058: ldc             #6   // 1206       || tue
0060: astore_2             // 4d
0061: goto            30   // a7001e
0064: ldc             #7   // 1207       || wed
0066: astore_2             // 4d
0067: goto            24   // a70018
0070: ldc             #8   // 1208       || thu
0072: astore_2             // 4d
0073: goto            18   // a70012
0076: ldc             #9   // 1209       || fri
0078: astore_2             // 4d
0079: goto            12   // a7000c
0082: ldc             #10  // 120a       || sat
0084: astore_2             // 4d
0085: goto            6    // a70006
0088: ldc             #11  // 120b       || sun
0090: astore_2             // 4d
0091: getstatic       #12  // b2000c     || java/lang/System.out:Ljava/io/PrintStream;
0094: aload_2              // 2c
0095: invokevirtual   #13  // b6000d     || java/io/PrintStream.println:(Ljava/lang/String;)V
0098: return               // b1

LocalVariableTable:
index  start_pc  length  name_and_type
    0         0      99  this:LEnumSwitch;
    1         0      99  instance:LDaysOfTheWeek;
    2         3      96  str:Ljava/lang/String;
```

查看`EnumSwitch$1`类的字段和方法

```txt
fields
    FieldInfo {Value='$SwitchMap$lsieun$sample$java5_enum$DaysOfTheWeek:[I', AccessFlags='[ACC_STATIC,ACC_FINAL,ACC_SYNTHETIC]', Attrs='[]', HexCode='1018000e000f0000'}
methods_count='0001' (1)
methods
    MethodInfo {Value='<clinit>:()V', AccessFlags='[ACC_STATIC]', Attrs='[Code]', HexCode='...'}
```

查看`EnumSwitch$1`类的`<clinit>:()V`方法：

```txt
（1）获取Enum类的元素数量
0000: invokestatic    #1   // b80001     || DaysOfTheWeek.values:()[LDaysOfTheWeek;
0003: arraylength          // be
（2）创建数组int[]
0004: newarray        10   // bc0a       || int
（3）保存数组到字段
0006: putstatic       #2   // b30002     || EnumSwitch$1.$SwitchMap$lsieun$sample$java5_enum$DaysOfTheWeek:[I
（4）保存第1个元素array[0]=1
0009: getstatic       #2   // b20002     || EnumSwitch$1.$SwitchMap$lsieun$sample$java5_enum$DaysOfTheWeek:[I
0012: getstatic       #3   // b20003     || DaysOfTheWeek.MONDAY:LDaysOfTheWeek;
0015: invokevirtual   #4   // b60004     || DaysOfTheWeek.ordinal:()I
0018: iconst_1             // 04
0019: iastore              // 4f
0020: goto            4    // a70004
0023: astore_0             // 4b
（4）保存第2个元素array[1]=2
0024: getstatic       #2   // b20002     || EnumSwitch$1.$SwitchMap$lsieun$sample$java5_enum$DaysOfTheWeek:[I
0027: getstatic       #6   // b20006     || DaysOfTheWeek.TUESDAY:LDaysOfTheWeek;
0030: invokevirtual   #4   // b60004     || DaysOfTheWeek.ordinal:()I
0033: iconst_2             // 05
0034: iastore              // 4f
0035: goto            4    // a70004
0038: astore_0             // 4b
...
（4）保存第7个元素array[6]=7
0100: getstatic       #2   // b20002     || EnumSwitch$1.$SwitchMap$lsieun$sample$java5_enum$DaysOfTheWeek:[I
0103: getstatic       #11  // b2000b     || DaysOfTheWeek.SUNDAY:LDaysOfTheWeek;
0106: invokevirtual   #4   // b60004     || DaysOfTheWeek.ordinal:()I
0109: bipush          7    // 1007
0111: iastore              // 4f
0112: goto            4    // a70004
0115: astore_0             // 4b
0116: return               // b1
```
