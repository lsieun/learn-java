# java.net.JarURLConnection

A URL Connection to a Java ARchive (JAR) file or an entry in a JAR file.

The syntax of a JAR URL is:

```txt
jar:<url>!/{entry}
```

for example:

```txt
jar:http://www.foo.com/bar/baz.jar!/COM/foo/Quux.class
```

**Jar URLs** should be used to refer to **a JAR file** or **entries in a JAR file**. The example above is a JAR URL which refers to **a JAR entry**. If the entry name is omitted, the URL refers to **the whole JAR file**: `jar:http://www.foo.com/bar/baz.jar!/`

Users should cast the generic `URLConnection` to a `JarURLConnection` when they know that the URL they created is a JAR URL, and they need JAR-specific functionality. For example:

```java
URL url = new URL("jar:file:/home/duke/duke.jar!/");
JarURLConnection jarConnection = (JarURLConnection)url.openConnection();
Manifest manifest = jarConnection.getManifest();
```

`JarURLConnection` instances can only be used to read from JAR files. It is not possible to get a `OutputStream` to modify or write to the underlying JAR file using this class.<sub>注：JarURLConnection只能用于读数据，而不能用于写数据</sub>

## Examples

A Jar entry

```txt
jar:http://www.foo.com/bar/baz.jar!/COM/foo/Quux.class
```

A Jar file

```txt
jar:http://www.foo.com/bar/baz.jar!/
```

A Jar directory

```txt
jar:http://www.foo.com/bar/baz.jar!/COM/foo/
```

`!/` is refered to as the separator.
