# bounded wildcards example

<!-- TOC -->

- [1. copy](#1-copy)
- [2. sort](#2-sort)
  - [2.1. When would I use a wildcard parameterized type with a lower bound?](#21-when-would-i-use-a-wildcard-parameterized-type-with-a-lower-bound)
- [3. max/min](#3-maxmin)

<!-- /TOC -->

## 1. copy

```java
public class Collections {
  public static <T> void copy(List<? super T> dest, List<? extends T> src) {  // uses bounded wildcards
      for (int i=0; i<src.size(); i++)
        dest.set(i,src.get(i));
  }
}
```

## 2. sort

```java
public static <T extends Comparable<? super T>> void sort(List<T> list) {
    list.sort(null);
}

public static <T> void sort(List<T> list, Comparator<? super T> c) {
    list.sort(c);
}
```

### 2.1. When would I use a wildcard parameterized type with a lower bound?

**When a concrete parmeterized type would be too restrictive**.

Consider a class hierarchy where a the topmost superclass implements an instantiation of the generic `Comparable` interface.

Example:

```java
class Person implements Comparable<Person> {
  ...
}

class Student extends Person {
  ...
}
```

Note, the `Student` class does not and cannot implement `Comparable<Student>`, because it would be a subtype of two different instantiations of the same generic type then, and that is illegal.

Consider also a method that tries to sort a sequence of subtype objects, such as a `List<Student>`.

Example:

```java
class Utilities {
  public static <T extends Comparable<T>> void sort(List<T> list) {
    ...
  }
  ...
}
```

This `sort` method cannot be applied to a list of students.

Example:

```java
List<Student> list = new ArrayList<Student>();
...
Utilities.sort(list);       // error
```

The reason for the error message is that the compiler infers the **type parameter** of the `sort` method as `T:=Student` and that class `Student` is not `Comparable<Student>`. It is `Comparable<Person>`, but that does not meet the requirements imposed by the bound of the type parameter of method `sort`.  It is required that `T` (i.e. `Student`) is `Comparable<T>` (i.e. `Comparable<Student>`), which in fact it is not.

In order to make the `sort` method applicable to a list of subtypes we would have to use a wildcard with a lower bound, like in the re-engineered version of the `sort` method below.

Example:

```java
class Utilities {
  public static <T extends Comparable <? super T > > void sort(List<T> list) {
    ...
  }
  ...
}
```

Now, we can sort a list of students, because students are comparable to a supertype of `Student`, namely `Person`.

## 3. max/min

- `Collections#max`

```java
public static <T extends Object & Comparable<? super T>> T max(Collection<? extends T> coll) {
    Iterator<? extends T> i = coll.iterator();
    T candidate = i.next();

    while (i.hasNext()) {
        T next = i.next();
        if (next.compareTo(candidate) > 0)
            candidate = next;
    }
    return candidate;
}

public static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp) {
    if (comp==null)
        return (T)max((Collection) coll);

    Iterator<? extends T> i = coll.iterator();
    T candidate = i.next();

    while (i.hasNext()) {
        T next = i.next();
        if (comp.compare(next, candidate) > 0)
            candidate = next;
    }
    return candidate;
}
```

