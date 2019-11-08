# Default Methods

Another **default method** that has been added is the `forEach` method on `Iterable`, which provides similar functionality to the **for loop** but lets you use a lambda expression as the body of the loop.

```java
default void forEach(Consumer<? super T> action) {
    for (T t : this) {
        action.accept(t);
    }
}
```









