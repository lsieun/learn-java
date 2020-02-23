# String

把这个方法单独拿出来，就是希望引起重视。

## split方法

```java
/**
    * Splits this string around matches of the given <a
    * href="../util/regex/Pattern.html#sum">regular expression</a>.
    *
    *
    * @param  regex
    *         the delimiting regular expression
    *
    * @return  the array of strings computed by splitting this string
    *          around matches of the given regular expression
    *
    * @throws  PatternSyntaxException
    *          if the regular expression's syntax is invalid
    *
    * @see java.util.regex.Pattern
    *
    * @since 1.4
    * @spec JSR-51
    */
public String[] split(String regex) {
    return split(regex, 0);
}
```

以"."分隔域名：

```java
String str = "www.google.com";
#错误写法：因为split接受的是一个正则表达式
String[] array = str.split(".");
#正确写法
String[] array = str.split("[.]");
```
