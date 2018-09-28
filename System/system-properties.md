# System Properties

Java maintains a set of system properties for its operations. Each system property is a `key-value` (String-String) pair such as "`java.version"="1.7.0_09`". You can retrieve all the system properties via `System.getProperties()`. You can also retrieve individual property via `System.getProperty(key)`. For example,

```java
import java.util.Properties;

public class PrintSystemProperties {
    public static void main(String[] args) {
        // List all System properties
        Properties pros = System.getProperties();
        pros.list(System.out);

        // Get a particular System property given its key
        // Return the property value or null
        System.out.println(System.getProperty("java.home"));
        System.out.println(System.getProperty("java.library.path"));
        System.out.println(System.getProperty("java.ext.dirs"));
        System.out.println(System.getProperty("java.clas.path"));
    }
}

```

The important system properties are:

- JRE related:
    - `java.home`: JRE home directory, e.g., "C:\Program Files\Java\jdk1.7.0_09\jre".
    - `java.library.path`: JRE library search path for search native libraries. It is usually but not necessarily taken from the environment variable PATH.
    - `java.class.path`: JRE CLASSPATH, e.g., . (for current working directory).
    - `java.ext.dirs`: JRE extension library path(s), e.g, "C:\Program Files\Java\jdk1.7.0_09\jre\lib\ext;C:\Windows\Sun\Java\lib\ext".
    - `java.version`: JRE version, e.g., 1.7.0_09.
    - `java.runtime.version`: JRE version, e.g. 1.7.0_09-b05.
- File related:
    - `file.separator`: symbol for file directory separator such as d:\test\test.java. The default is \ for windows or / for Unix/Mac.
    - `path.separator`: symbol for separating path entries, e.g., in PATH or CLASSPATH. The default is ; for windows or : for Unix/Mac.
    - `line.separator`: symbol for end-of-line (or new line). The default is "\r\n" for windows or "\n" for Unix/Mac OS X.
- User related:
    - `user.name`: the user's name.
    - `user.home`: the user's home directory.
    - `user.dir`: the user's current working directory.
- OS related:
    - `os.name`: the OS's name, e.g., "Windows 7".
    - `os.version`: the OS's version, e.g., "6.1".
    - `os.arch`: the OS's architecture, e.g., "x86".

Access to system properties can be restricted by the Java security manager and policy file. By default, Java programs have unrestricted access to all the system properties.

```txt
java.version
java.home
java.vendor
java.vendor.url

java.class.path
java.library.path
java.ext.dirs
java.io.tmpdir

java.specification.name
java.specification.version
java.specification.vendor

java.runtime.name
java.runtime.version

java.vm.name
java.vm.version
java.vm.info
java.vm.vendor

java.vm.specification.name
java.vm.specification.version
java.vm.specification.vendor

file.encoding
path.separator
file.separator
line.separator

user.name
user.home
user.dir
user.language
user.country

os.name
os.arch
os.version
```

```java
public class PrettyPrintSystemProperties {
    public static void main(String[] args) {
        System.out.println("Java Basic Info:");
        System.out.println("java.version = " + System.getProperty("java.version"));
        System.out.println("java.home = " + System.getProperty("java.home"));
        System.out.println("java.vendor = " + System.getProperty("java.vendor"));
        System.out.println("java.vendor.url = " + System.getProperty("java.vendor.url"));
        System.out.println();

        System.out.println("Java Dirs:");
        System.out.println("java.class.path = " + System.getProperty("java.class.path"));
        System.out.println("java.library.path = " + System.getProperty("java.library.path"));
        System.out.println("java.ext.dirs = " + System.getProperty("java.ext.dirs"));
        System.out.println("java.io.tmpdir = " + System.getProperty("java.io.tmpdir"));
        System.out.println();

        System.out.println("Java Specification:");
        System.out.println("java.specification.name = " + System.getProperty("java.specification.name"));
        System.out.println("java.specification.version = " + System.getProperty("java.specification.version"));
        System.out.println("java.specification.vendor = " + System.getProperty("java.specification.vendor"));
        System.out.println();

        System.out.println("Java Runtime:");
        System.out.println("java.runtime.name = " + System.getProperty("java.runtime.name"));
        System.out.println("java.runtime.version = " + System.getProperty("java.runtime.version"));
        System.out.println();

        System.out.println("Java VM");
        System.out.println("java.vm.name = " + System.getProperty("java.vm.name"));
        System.out.println("java.vm.version = " + System.getProperty("java.vm.version"));
        System.out.println("java.vm.info = " + System.getProperty("java.vm.info"));
        System.out.println("java.vm.vendor = " + System.getProperty("java.vm.vendor"));
        System.out.println();

        System.out.println("Java VM Specification:");
        System.out.println("java.vm.specification.name = " + System.getProperty("java.vm.specification.name"));
        System.out.println("java.vm.specification.version = " + System.getProperty("java.vm.specification.version"));
        System.out.println("java.vm.specification.vendor = " + System.getProperty("java.vm.specification.vendor"));
        System.out.println();

        System.out.println("File:");
        System.out.println("file.encoding = " + System.getProperty("file.encoding"));
        System.out.println("path.separator = " + System.getProperty("path.separator"));
        System.out.println("file.separator = " + System.getProperty("file.separator"));
        System.out.println("line.separator = " + System.getProperty("line.separator"));
        System.out.println();

        System.out.println("User:");
        System.out.println("user.name = " + System.getProperty("user.name"));
        System.out.println("user.home = " + System.getProperty("user.home"));
        System.out.println("user.dir = " + System.getProperty("user.dir"));
        System.out.println("user.language = " + System.getProperty("user.language"));
        System.out.println("user.country = " + System.getProperty("user.country"));
        System.out.println();
        
        System.out.println("OS:");
        System.out.println("os.name = " + System.getProperty("os.name"));
        System.out.println("os.arch = " + System.getProperty("os.arch"));
        System.out.println("os.version = " + System.getProperty("os.version"));
    }
}

```

```txt
Java Basic Info:
java.version = 1.8.0_181
java.home = /usr/local/jdk1.8.0_181/jre
java.vendor = Oracle Corporation
java.vendor.url = http://java.oracle.com/

Java Dirs:
java.class.path = .
java.library.path = /usr/java/packages/lib/amd64:/usr/lib64:/lib64:/lib:/usr/lib
java.ext.dirs = /usr/local/jdk1.8.0_181/jre/lib/ext:/usr/java/packages/lib/ext
java.io.tmpdir = /tmp

Java Specification:
java.specification.name = Java Platform API Specification
java.specification.version = 1.8
java.specification.vendor = Oracle Corporation

Java Runtime:
java.runtime.name = Java(TM) SE Runtime Environment
java.runtime.version = 1.8.0_181-b13

Java VM
java.vm.name = Java HotSpot(TM) 64-Bit Server VM
java.vm.version = 25.181-b13
java.vm.info = mixed mode
java.vm.vendor = Oracle Corporation

Java VM Specification:
java.vm.specification.name = Java Virtual Machine Specification
java.vm.specification.version = 1.8
java.vm.specification.vendor = Oracle Corporation

File:
file.encoding = UTF-8
path.separator = :
file.separator = /
line.separator = 


```


