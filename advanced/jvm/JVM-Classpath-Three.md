# How Classes are Found

URL: https://docs.oracle.com/javase/8/docs/technotes/tools/unix/findingclasses.html

The **java command** is called the **Java launcher** because it launches Java applications. When the Java launcher is called, it gathers input from the user and the user's environment (such as the class path and the boot class path), interfaces with the Virtual Machine (VM), and gets it started via some bootstrapping. The Java Virtual Machine (JVM) does the rest of the work.

## 1. How the Java Runtime Finds Classes

The JVM searches for and loads classes in this order:

- (1) **Bootstrap classes**, which are classes that comprise the Java platform, including the classes in `rt.jar` and several other important JAR files.
- (2) **Extension classes**, which use the Java Extension mechanism. These classes are bundled as JAR files and located in the extensions directory.
- (3) **User classes** are classes that are defined by developers and third parties and that do not take advantage of the extension mechanism. You identify the location of these classes with the `-classpath` option on the command line (**preferred**) or with the `CLASSPATH` environment variable.


In effect, the **three search paths** together form **a simple class path**. This is similar to the flat class path previously used, but the current model has the following improvements:

- (1) It is relatively **difficult** to accidentally hide or omit **the bootstrap classes**.
- (2) In general, you **only** have to specify **the location of user classes**. **Bootstrap classes** and **extension classes** are found **automatically**.
- (3) The tools classes are now in a separate archive (`tools.jar`) and can only be used if included in the user class path.


## 2. How the Java Runtime Finds Bootstrap Classes

**Bootstrap classes** are the classes that implement Java SE. Bootstrap classes are in the `rt.jar` file and several other JAR files in the `jre/lib` directory. These archives are specified by the value of **the bootstrap class path** that is stored in the `sun.boot.class.path` system property. This system property is for reference only and should not be directly modified. It is unlikely that you will need to redefine the bootstrap class path. The nonstandard option, `-Xbootclasspath`, allows you to do so in those rare circumstances in which it is necessary to use a different set of core classes.

```java
package lsieun.jvm.classloader.clazzpath;

public class PrintBootClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("sun.boot.class.path");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }

    }
}
```

Output:

```txt
/usr/local/jdk1.8.0_181/jre/lib/resources.jar
/usr/local/jdk1.8.0_181/jre/lib/rt.jar
/usr/local/jdk1.8.0_181/jre/lib/sunrsasign.jar
/usr/local/jdk1.8.0_181/jre/lib/jsse.jar
/usr/local/jdk1.8.0_181/jre/lib/jce.jar
/usr/local/jdk1.8.0_181/jre/lib/charsets.jar
/usr/local/jdk1.8.0_181/jre/lib/jfr.jar
/usr/local/jdk1.8.0_181/jre/classes
```


Note that the classes that implement **the JDK tools** are in a separate archive from **the bootstrap classes**. **The tools archive** is the `JDK/lib/tools.jar` file. **The development tools** add **this archive** to** the user class path** when invoking the launcher(这里的launcher是指command line命令行). However, this augmented user class path is only used to execute the tool. The tools that process source code, the `javac` command, and the `javadoc` command, use **the original class path**, not the augmented version. 


## 3. How the Java Runtime Finds Extension Classes

**Extension classes** are classes that extend the Java platform. Every `.jar` file in **the extension directory**, `jre/lib/ext`, is assumed to be an extension and is loaded with the Java Extension Framework. **Loose class files** in **the extension directory** are **not found**. They must be contained in a JAR file or Zip file. There is no option provided for changing the location of the extension directory.


```java
package lsieun.jvm.classloader.clazzpath;

public class PrintExtClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("java.ext.dirs");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }
    }
}
```

Output:

```txt
/usr/local/jdk1.8.0_181/jre/lib/ext
/usr/java/packages/lib/ext
```


If the `jre/lib/ext` directory contains multiple JAR files, and those files contain classes with the same name, **the class that actually gets loaded** is **undefined**.

## 4. How the Java Runtime Finds User Classes

To find user classes, the launcher refers to **the user class path**, which is a list of directories, JAR files, and Zip files that contain class files.

**A class file** has **a subpath name** that reflects **the fully-qualified name of the class**. For example, if the class `com.mypackage.MyClass` is stored under `myclasses`, then `myclasses` must be in **the user class path**, and **the full path to the class file** must be `/myclasses/com/mypackage/MyClass.class` on Oracle Solaris or in `\myclasses\com\mypackage\MyClass.class` on Windows.

If the class is stored in **an archive** named `myclasses.jar`, then `myclasses.jar` must be in **the user class path**, and **the class file** must be stored in the archive as `com/mypackage/MyClass.class` on Windows or in `com\mypackage\MyClass.class` on Oracle Solaris.

**The user class path** is specified as a string, with **a colon** (`:`) to separate the class path entries on Oracle Solaris, and a **semicolon** (`;`) to separate the entries on Windows systems. The Java launcher puts the user class path string in the `java.class.path` system property. The possible sources of this value are:

- (1) **The default value**, `*.*`, which means that user class files are all the class files **in** or **under** **the current directory**.
- (2) The value of the `CLASSPATH` environment variable that overrides **the default value**.
- (3) The value of the `-cp` or `-classpath` **command-line option** that overrides both **the default value** and the `CLASSPATH` value.
- (4) **The JAR archive** specified by the `-jar` option overrides **all other values** if it contains a `Class-Path` entry in its **manifest**. If this option is used, all user classes must come from the specified archive.

```java
package lsieun.jvm.classloader.clazzpath;

public class PrintUserClassPath {
    public static void main(String[] args) {
        String property = System.getProperty("java.class.path");
        String[] array = property.split(":");
        for (String item : array) {
            System.out.println(item);
        }

    }
}
```

Output:

```txt
/home/liusen/workdir/git-repo/learn-java/advanced/jvm/code/learn-jvm/target/classes
```

