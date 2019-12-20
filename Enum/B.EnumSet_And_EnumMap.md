# Specialized Collections: EnumSet and EnumMap

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
