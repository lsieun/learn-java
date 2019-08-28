# Enums

## Enums as special classes

Before enums had been introduced into the Java language<sub>注：在Enums引入Java之前的情况</sub>, the regular way to model the set of fixed values in Java was just by declaring a number of constants.

For example:

```java
public class DaysOfTheWeekConstants {
    public static final int MONDAY = 0;
    public static final int TUESDAY = 1;
    public static final int WEDNESDAY = 2;
    public static final int THURSDAY = 3;
    public static final int FRIDAY = 4;
    public static final int SATURDAY = 5;
    public static final int SUNDAY = 6;
}
```

Although this approach kind of works, it is far from being the ideal solution<sub>注：虽然这种方法可行，但不够完美</sub>. Primarily, because the constants themselves are just values of type `int`<sub>注：不够完美的地方：这个类只有7个有效值，而int由4个byte组成，也就是32个bit，可以表示2<sup>32</sup>个数，远远大于7个值</sub> and every place in the code where those constants are expected (instead of arbitrary `int` values) should be explicitly documented and asserted all the time. Semantically, it is not a type-safe representation of the concept as the following method demonstrates.

```java
public boolean isWeekend(int day) {
    return (day == SATURDAY || day == SUNDAY);
}
```

From logical point of view, the `day` argument should have one of the values declared in the `DaysOfTheWeekConstants` class. However, it is not possible to guess that without additional documentation being written (and read afterwards by someone). For the Java compiler the call like `isWeekend(100)` looks absolutely correct and raises no concerns.

Here the enums come to the rescue. Enums allow to replace constants with the typed values and to use those types everywhere. Let us rewrite the solution above using enums.

```java
public enum DaysOfTheWeek {  // 第一处不同：由class变成了enum
    MONDAY,  // 第二处不同：变量不再是int类型，而是DaysOfTheWeek类型
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;
}
```

What changed is that the `class` becomes `enum` and the possible values are listed in the enum definition. The distinguishing part however is that every single value is the instance of the enum class it is being declared at (in our example, DaysOfTheWeek). As such, whenever enum are being used, the Java compiler is able to do type checking. For example:

```java
public boolean isWeekend(DaysOfTheWeek day) { // 第三处不同：能够对传入参数进行类型检验
    return (day == SATURDAY || day == SUNDAY);
}
```

Please notice that the usage of **the uppercase naming scheme** in enums is just a convention, nothing really prevents you from not doing that.

第一点需要注意：DaysOfTheWeek的父类是Enum类，它自身具有`ACC_PUBLIC`、`ACC_FINAL`、`ACC_SUPER`和`ACC_ENUM`的标识。

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

第二点需要注意：默认的构造函数是`<init>:(Ljava/lang/String;I)V`，它会调用父类的构造函数`Enum(String name, int ordinal)`

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

第三点需要注意：`DaysOfTheWeek`中的每一个字段（field）都有`ACC_PUBLIC`、`ACC_STATIC`、`ACC_FINAL`和`ACC_ENUM`标识。这个和Interface当中的字段的标识相似，只是多了`ACC_ENUM`标识

```txt
FieldInfo {Value='MONDAY:Llsieun/sample/java5_enum/DaysOfTheWeek;', AccessFlags='[ACC_PUBLIC,ACC_STATIC,ACC_FINAL,ACC_ENUM]', Attrs='[]', HexCode='4019001700180000'}
```

第四点需要注意：`DaysOfTheWeek`的`.class`文件会自动生成一个`$VALUES``字段，这具有的标识是ACC_PRIVATE`、`ACC_STATIC`、`ACC_FINAL`和`ACC_SYNTHETIC`。

```txt
FieldInfo {Value='$VALUES:[Llsieun/sample/java5_enum/DaysOfTheWeek;', AccessFlags='[ACC_PRIVATE,ACC_STATIC,ACC_FINAL,ACC_SYNTHETIC]', Attrs='[]', HexCode='101a001f00200000'}
```

换句话说，从第三点和第四点来看，`DaysOfTheWeek`的字段（field）都是`static`类型的。下面介绍`non-static`类型的字段（field）。

## Enums and instance fields

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
0008: putfield        #7   // b50007     || lsieun/sample/java5_enum/DaysOfTheWeekFields.isWeekend:Z
0011: return               // b1

LocalVariableTable:
index  start_pc  length  name_and_type
    0         0      12  this:Llsieun/sample/java5_enum/DaysOfTheWeekFields;
    3         0      12  isWeekend:Z
```

## Enums and interfaces

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

## Enums and generics

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

## Convenient Enums methods

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

## Specialized Collections: EnumSet and EnumMap

Instances of enums, as all other classes, could be used with the standard Java collection library<sub>注：enums可以和collection一起使用</sub>. However, certain collection types have been optimized for enums specifically and are recommended in most cases to be used instead of general-purpose counterparts.

We are going to look on two specialized collection types: `EnumSet<T>` and `EnumMap<T,?>`. Both are very easy to use and we are going to start with the `EnumSet<T>`.

The `EnumSet<T>` is the regular set optimized to store enums effectively. Interestingly, `EnumSet<T>` cannot be instantiated using constructors and provides a lot of helpful factory methods instead.

For example, the `allOf` factory method creates the instance of the `EnumSet<T>` containing all enum constants of the enum type in question:

```java
final Set<DaysOfTheWeek> enumSetAll = EnumSet.allOf(DaysOfTheWeek.class);
```

Consequently, the `noneOf` factory method creates the instance of an empty `EnumSet<T>` for the enum type in question:

```java
final Set<DaysOfTheWeek> enumSetNone = EnumSet.noneOf(DaysOfTheWeek.class);
```

It is also possible to specify which enum constants of the enum type in question should be included into the `EnumSet<T>`, using the `of` factory method:

```java
final Set<DaysOfTheWeek> enumSetSome = EnumSet.of(
    DaysOfTheWeek.SUNDAY,
    DaysOfTheWeek.SATURDAY
);
```

The `EnumMap<T,?>` is very close to the regular map with the difference that its keys could be the enum constants of the enum type in question. For example:

```java
final Map<DaysOfTheWeek, String> enumMap = new EnumMap<>(DaysOfTheWeek.class);
enumMap.put( DaysOfTheWeek.MONDAY, "Lundi");
enumMap.put( DaysOfTheWeek.TUESDAY, "Mardi");
```

Please notice that, as most collection implementations, `EnumSet<T>` and `EnumMap<T, ?>` are not thread-safe and cannot be used as-is in multithreaded environment.

## When to use enums

Since Java 5 release enums are the only preferred and recommended way to represent and dial with **the fixed set of constants**. Not only they are strongly-typed, they are extensible and supported by any modern library or framework.
