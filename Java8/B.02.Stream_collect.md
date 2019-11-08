# Stream collect Method

`collect()` is a special case for `reduce()` in Java 8. It is more
efficient with the Java `String` class.

理解：

- （1） `collect()`方法是`reduce()`方法的一种特殊形式
- （2） 在处理String时，`collect()`方法比`reduce()`方法更有效率。


## joining

```java
String value = Stream.of("A", "B", "C").collect(Collectors.joining(", "));
System.out.println(value);
```

Out:

```txt
A, B, C
```

