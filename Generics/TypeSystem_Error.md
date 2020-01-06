# Type System

## Array

bounded wildcard

```java
Pair<? extends Number, ? extends Number>[] array = new Pair[10]; // ok

Pair<? extends Number, ? extends Number>[] array = new Pair<?, ?>[10]; // error
Pair<? extends Number, ? extends Number>[] array = new Pair<? extends Number, ? extends Number>[10]; // error
```

unbounded wildcard

```java
Pair<?, ?>[] array = new Pair<?, ?>[10]; // ok
```
