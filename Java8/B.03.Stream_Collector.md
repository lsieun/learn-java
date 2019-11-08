# Stream Collector



## Reducing and Summarizing

### Summarization

- `Collectors.summingInt`
- `Collectors.summingLong`
- `Collectors.summingDouble`

- `Collectors.averagingInt`
- `Collectors.averagingLong`
- `Collectors.averagingDouble`

- `Collectors.summarizingInt`
- `Collectors.summarizingLong`
- `Collectors.summarizingDouble`

```java
int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));
double avgCalories = menu.stream().collect(averagingInt(Dish::getCalories));
IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
```

### Joining Strings

The collector returned by the `joining` factory method concatenates into a single string all strings resulting from invoking the `toString` method on each object in the stream.

```java
String shortMenu = menu.stream().map(Dish::getName).collect(joining());
String shortMenu = menu.stream().map(Dish::getName).collect(joining(", "));
```

Note that `joining` internally makes use of a `StringBuilder` to append the generated strings into one.

### Generalized summarization with reduction

```java
int totalCalories = menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
Optional<Dish> mostCalorieDish = menu.stream().collect(reducing(
    (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2
    ));
```

## Grouping

话语体系

classification function

### Multilevel grouping


## Partitioning



















