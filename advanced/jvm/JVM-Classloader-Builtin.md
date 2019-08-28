# Types of Built-in Class Loaders

<!-- TOC -->

- [1. Bootstrap Class Loader](#1-bootstrap-class-loader)
- [2. Extension Class Loader](#2-extension-class-loader)
- [3. System Class Loader](#3-system-class-loader)

<!-- /TOC -->

```java
package lsieun.jvm.classloader.loader_all;

import com.sun.crypto.provider.SunJCE;

public class PrintClassLoader {
    public static void main(String[] args) {
        // String class is loaded by bootstrap loader, and
        // bootstrap loader is not Java object, hence null
        System.out.println(String.class.getClassLoader());
        System.out.println("===========================");

        System.out.println(SunJCE.class.getClassLoader());
        System.out.println("===========================");

        // Test class is loaded by Application loader
        System.out.println(ClassLoader.getSystemClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(PrintClassLoader.class.getClassLoader());
        System.out.println("===========================");
    }
}
```

Output:

```txt
null
===========================
sun.misc.Launcher$ExtClassLoader@5cad8086
===========================
sun.misc.Launcher$AppClassLoader@18b4aac2
sun.misc.Launcher$AppClassLoader@18b4aac2
sun.misc.Launcher$AppClassLoader@18b4aac2
===========================
```

We can see that the `String` Classloader it displays `null` in the output. This is because **the bootstrap class loader** is written in **native code**, not Java – so it doesn’t show up as a Java class. Due to this reason, the behavior of the bootstrap class loader will differ across JVMs.

## 1. Bootstrap Class Loader

**Bootstrap Class Loader** mainly responsible for loading JDK internal classes, typically `rt.jar` and other core libraries located in `$JAVA_HOME/jre/lib` directory. Additionally, **Bootstrap class loader** serves as **a parent** of **all the other ClassLoader instances**.

This **bootstrap class loader** is part of the core JVM and is written in **native code**. Different platforms might have different implementations of this particular class loader.

> Bootstrap class loader is written in native code.

Given all classes in one Java application environment, we can easily form **a class loading tree** to reflect the class loading relationship. Each class that is not a class loader is a leaf node. Each class's parent node is its class loader, with **the `null` class loader** being the root class. Such a structure is a tree because there cannot be cycles -- a class loader cannot have loaded its own ancestor class loader.

> a class loading tree

## 2. Extension Class Loader

The **extension class loader** is a child of the **bootstrap class loader** and takes care of loading the extensions of the standard core Java classes so that it’s available to all applications running on the platform.

**Extension class loader** loads from the JDK extensions directory, usually `$JAVA_HOME/lib/ext` directory or any other directory mentioned in the `java.ext.dirs` system property.

## 3. System Class Loader

The **system or application class loader**, on the other hand, takes care of loading all the application level classes into the JVM. It loads files found in the `classpath` environment variable, `-classpath` or `-cp` command line option. Also, it’s a child of **Extensions classloader**.


