# Differences Between Enumeration Vs Iterator In Java

`Enumeration` and `Iterator` are two interfaces in `java.util` package which are used to traverse over the elements of a `Collection` object. Though they perform the same function i.e traversing the Collection object, there are some differences exist between them. Using `Enumeration`, you can only traverse the Collection object. But using `Iterator`, you can also remove an element while traversing the Collection. This is the **one major difference** between `Enumeration` and `Iterator` in java. You can say `Iterator` is some what advanced version of `Enumeration`. In this post, we will see the differences between Enumeration Vs Iterator In Java.

`Iterator` interface is introduced from `JDK 1.2` where as `Enumeration` interface is there from `JDK 1.0`.

```java
package java.util;

/**
 * @since   JDK1.0
 */
public interface Enumeration<E> {
    boolean hasMoreElements();

    E nextElement();
}
```

```java
package java.util;

import java.util.function.Consumer;

/**
 * An iterator over a collection.  {@code Iterator} takes the place of
 * {@link Enumeration} in the Java Collections Framework.  Iterators
 * differ from enumerations in two ways:
 *
 * <ul>
 *      <li> Iterators allow the caller to remove elements from the
 *           underlying collection during the iteration with well-defined
 *           semantics.
 *      <li> Method names have been improved.
 * </ul>
 *
 * <p>This interface is a member of the
 * <a href="{@docRoot}/../technotes/guides/collections/index.html">
 * Java Collections Framework</a>.
 *
 * @since 1.2
 */
public interface Iterator<E> {

    boolean hasNext();

    E next();

    default void remove() {
        throw new UnsupportedOperationException("remove");
    }

    /**
     * @since 1.8
     */
    default void forEachRemaining(Consumer<? super E> action) {
        Objects.requireNonNull(action);
        while (hasNext())
            action.accept(next());
    }
}
```

This is **the main difference** between `Enumeration` and `Iterator` interface. `Enumeration` only traverses the Collection object. You can’t do any modifications to Collection while traversing the Collection using Enumeration. Where as `Iterator` interface allows us to remove an element while traversing the Collection object. Iterator has `remove()` method which is not there in the Enumeration interface. 


## Legacy Interface

`Enumeration` is a **legacy** interface used to traverse only the legacy classes like `Vector`, `HashTable` and `Stack`. Where as `Iterator` is not a legacy code which is used to traverse most of the classes in the collection framework. For example, `ArrayList`, `LinkedList`, `HashSet`, `LinkedHashSet`, `TreeSet`, `HashMap`, `LinkedHashMap`, `TreeMap` etc.





