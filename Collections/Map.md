# Map

<!-- TOC -->

- [1. Java8](#1-java8)
  - [1.1. getOrDefault](#11-getordefault)
  - [1.2. computeIfAbsent](#12-computeifabsent)
  - [1.3. forEach](#13-foreach)

<!-- /TOC -->

## 1. Java8

### 1.1. getOrDefault

Returns the value to which the specified `key` is mapped, or `defaultValue` if this map contains no mapping for the key.

```java
default V getOrDefault(Object key, V defaultValue) {
    V v;
    return (((v = get(key)) != null) || containsKey(key))
        ? v
        : defaultValue;
}
```

### 1.2. computeIfAbsent

If the specified `key` is not already associated with **a value** (or is mapped to `null`), attempts to compute its value using **the given mapping function** and enters it into this map unless `null`.

```java
default V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
    Objects.requireNonNull(mappingFunction);
    V v;
    if ((v = get(key)) == null) {
        V newValue;
        if ((newValue = mappingFunction.apply(key)) != null) {
            put(key, newValue);
            return newValue;
        }
    }

    return v;
}
```

以前的时候，我是这样写代码的：

```java
Map<String, Pattern> map = new HashMap<>();
String regex = "^com/.+\.class$";

// 功能：如果不存在，则添加到map当中，代码占用5行。
Pattern pattern = map.get(regex);
if (pattern == null) {
    pattern = Pattern.compile(regex);
    map.put(regex, pattern);
}
```

现在，使用`computeIfAbsent`方法，我是这样写代码的：

```java
Map<String, Pattern> map = new HashMap<>();
String regex = "^com/.+\\.class$";

// 功能：如果不存在，则添加到map当中，代码占用1行。
map.computeIfAbsent(regex, Pattern::compile);
```

### 1.3. forEach

Performs the given `action` for **each entry** in this map until all entries have been processed or the action throws an exception.

```java
default void forEach(BiConsumer<? super K, ? super V> action) {
    Objects.requireNonNull(action);
    for (Map.Entry<K, V> entry : entrySet()) {
        K k;
        V v;
        try {
            k = entry.getKey();
            v = entry.getValue();
        } catch(IllegalStateException ise) {
            // this usually means the entry is no longer in the map.
            throw new ConcurrentModificationException(ise);
        }
        action.accept(k, v);
    }
}
```
