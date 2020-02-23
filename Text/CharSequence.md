# CharSequence

This interface defines a simple API for read-only access to sequences of characters. In the core platform it is implemented by the `String`, `StringBuffer`, `StringBuilder` and `java.nio.CharBuffer` classes.

- `charAt()` returns the character at a specified position in the sequence.
- `length()` returns the number of characters in the sequence.
- `subSequence()` returns a `CharSequence` that consists of the characters starting at, and including, the specified `start` index, and continuing up to, but not including, the specified `end` index.
- Finally, `toString()` returns a String version of the sequence.

```java
public interface CharSequence {
    // Public Instance Methods
    public abstract char charAt(int index);
    public abstract int length();
    public abstract CharSequence subSequence(int star t, int end);
    public abstract String toString();
}
```

我的总结：

`CharSequence`，从字面理解，就是“字符的序列”，就是一长串字符排列在一起，它主要提供三个方法：第一个方法获取其中一个字符`charAt()`，第二个方法获取字符序列的长度`length()`，第三个方法得到一个子序列`subSequence()`。它本身就是一个接口，是一个契约，定义了子类的三种行为。

> 其实，也并不是三个方法，我只是为了自己方便记忆，故意省略掉一些东西。
