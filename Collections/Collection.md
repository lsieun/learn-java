# Collection

<!-- TOC -->

- [1. toArray](#1-toarray)

<!-- /TOC -->

## 1. toArray

```java
// Generic Method
<T> T[] toArray(T[] a);
```

- `a` – the array into which the elements of this collection are to be stored, if it is big enough; **otherwise, a new array of the same runtime type is allocated for this purpose**.

Suppose `x` is a collection known to contain only strings. The following code can be used to dump the collection into a newly allocated array of String:

```java
String[] y = x.toArray(new String[0]);
```

Note that `toArray(new Object[0])` is identical in function to `toArray()`.

```java
// None-Generic Method
Object[] toArray();
```
