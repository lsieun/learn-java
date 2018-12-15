# UnsupportedOperationException

URL: [Why do I get an UnsupportedOperationException when trying to remove an element from a List?](https://stackoverflow.com/questions/2965747/why-do-i-get-an-unsupportedoperationexception-when-trying-to-remove-an-element-f)

```java
public static String SelectRandomFromTemplate(String template,int count) {
   String[] split = template.split("|");
   List<String> list=Arrays.asList(split);
   Random r = new Random();
   while( list.size() > count ) {
      list.remove(r.nextInt(list.size()));
   }
   return StringUtils.join(list, ", ");
}
```

Error:

```txt
06-03 15:05:29.614: ERROR/AndroidRuntime(7737): java.lang.UnsupportedOperationException
06-03 15:05:29.614: ERROR/AndroidRuntime(7737):     at java.util.AbstractList.remove(AbstractList.java:645)
```



## **On `Arrays.asList` returning a fixed-size list**

From the API:

> `Arrays.asList`: Returns **a fixed-size list** backed by the specified array.

You can't add to it; you can't remove from it. You can't structurally modify the List.

**Fix**

Create a `LinkedList`, which supports faster remove.

```java
List<String> list = new LinkedList<String>(Arrays.asList(split));
```

## On `split` taking regex

From the API:

> `String.split(String regex)`: Splits this string around matches of the given **regular expression**.

`|` is a regex metacharacter; if you want to split on a literal `|`, you must escape it to `\|`, which as a Java string literal is "`\\|`".

**Fix**:

```java
template.split("\\|")
```

