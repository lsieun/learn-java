# peek

## 有些Operation可以进行合并

这段代码的目的，Stream会进行一些优化，将一些代码进行合并

```java
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        Stream.of(2, 4, 3, 5).peek(item -> {
            System.out.println("of -> " + item);
        })
                .map(i -> i + 1).peek(item -> {
            System.out.println("map -> " + item);
        })
                .map(i -> i * 2).peek(item -> {
            System.out.println("map2 -> " + item);
        })
                .forEach(System.out::println);
    }
}
```

Out:

```txt
of -> 2
map -> 3
map2 -> 6
6
of -> 4
map -> 5
map2 -> 10
10
of -> 3
map -> 4
map2 -> 8
8
of -> 5
map -> 6
map2 -> 12
12
```

## 有些Operation不能进行合并

下面这段代码，我想验证的想法就是：像`sorted`这种方法，它必须获取到“所有的数据之后才能进行排序”，不能再像之前那样“拿一个数据处理一个数据”。

```java
import java.util.stream.Stream;

public class Demo {
    public static void main(String[] args) {
        Stream.of(2, 4, 3, 5).peek(item -> {
            System.out.println("of -> " + item);
        })
                .map(i -> i + 1).peek(item -> {
            System.out.println("map -> " + item);
        })
                .sorted().peek(item -> {
            System.out.println("sorted -> " + item);
        })
                .forEach(System.out::println);
    }
}
```

Output:

```txt
of -> 2
map -> 3
of -> 4
map -> 5
of -> 3
map -> 4
of -> 5
map -> 6
sorted -> 3   注意：在sorted之前，它完成一次循环
3
sorted -> 4
4
sorted -> 5
5
sorted -> 6
6
```
