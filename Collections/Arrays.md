# Arrays

<!-- TOC -->

- [1. Java6](#1-java6)
  - [1.1. copyOf](#11-copyof)
- [2. Java8](#2-java8)
  - [2.1. setAll](#21-setall)
  - [2.2. parallelSetAll](#22-parallelsetall)
  - [2.3. parallelPrefix](#23-parallelprefix)
  - [2.4. parallelSort](#24-parallelsort)

<!-- /TOC -->

## 1. Java6

### 1.1. copyOf

```java
public static int[] copyOf(int[] original, int newLength) {
    int[] copy = new int[newLength];
    System.arraycopy(original, 0, copy, 0,
                        Math.min(original.length, newLength));
    return copy;
}
```

Code:

```java
int[] array = new int[]{1, 2, 3, 4, 5};
int[] newArray = Arrays.copyOf(array, array.length);
System.out.println(Arrays.toString(newArray));
```

Out:

```txt
[1, 2, 3, 4, 5]
```

## 2. Java8

### 2.1. setAll

- `setAll(int[] array, IntUnaryOperator generator)`
- `setAll(long[] array, IntToLongFunction generator)`
- `setAll(double[] array, IntToDoubleFunction generator)`
- `setAll(T[] array, IntFunction<? extends T> generator)`

```java
public static void setAll(int[] array, IntUnaryOperator generator) {
    Objects.requireNonNull(generator);
    for (int i = 0; i < array.length; i++)
        array[i] = generator.applyAsInt(i);
}

public static <T> void setAll(T[] array, IntFunction<? extends T> generator) {
    Objects.requireNonNull(generator);
    for (int i = 0; i < array.length; i++)
        array[i] = generator.apply(i);
}
```

### 2.2. parallelSetAll

```java
public static void parallelSetAll(int[] array, IntUnaryOperator generator) {
    Objects.requireNonNull(generator);
    IntStream.range(0, array.length).parallel().forEach(i -> { array[i] = generator.applyAsInt(i); });
}
```

Old Code:

```java
int[] array = new int[5];
for (int i = 0; i < array.length; i++) {
    array[i] = i;
}
System.out.println(Arrays.toString(array));
```

New Code:

```java
int[] array = new int[5];
Arrays.parallelSetAll(array, i -> i);
System.out.println(Arrays.toString(array));
```

Out:

```txt
[0, 1, 2, 3, 4]
```

### 2.3. parallelPrefix

```java
public static void parallelPrefix(double[] array, DoubleBinaryOperator op) {
    Objects.requireNonNull(op);
    if (array.length > 0)
        new ArrayPrefixHelpers.DoubleCumulateTask
                (null, op, array, 0, array.length).invoke();
}
```

### 2.4. parallelSort

