# Stream Source

## Collection

- `Arrays.stream(T[] array)`: 将一个数组转换成Stream对象。
- `Collection.stream()`: 将一个Collection对象转换成Stream对象。

```java
public interface Iterable<T> {
    default void forEach(Consumer<? super T> action) {
        Objects.requireNonNull(action);
        for (T t : this) {
            action.accept(t);
        }
    }
}

public interface Collection<E> extends Iterable<E> {
    default Stream<E> stream() {
        return StreamSupport.stream(spliterator(), false);
    }
}

public interface List<E> extends Collection<E> { }

public interface Set<E> extends Collection<E> { }
```

