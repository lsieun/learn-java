# How do Class Loaders Work?

URL:

- https://www.baeldung.com/java-classloaders
- https://docs.oracle.com/javase/7/docs/technotes/guides/security/spec/security-spec.doc5.html

# 1. Introduction to Class Loaders

## 1.1 What is Java ClassLoader?

The Java **Classloader** is a part of the JRE (Java Runtime Environment) that dynamically loads Java classes into the JVM (Java Virtual Machine). In particular, a Java program, unlike one written in C or C++, isn't a single executable file, but instead is composed of many individual class files, each of which corresponds to a single Java class. **Normally classes are only loaded on demand**. This means, these Java class files are not loaded into memory all at once, but rather are loaded on demand, as needed by the program (Class Loader). Class Loader is a component with the Java Execution Engine which loads the Binary data from the `.class` files available in the classpath into the Method Area. Loading of a class into the method area occurs only the first time when the class is referenced with in the running Java application. For all other references the data is reused from the method area, unless the class has been **UNLOADED** .

There are **three types of built-in ClassLoader** in Java:

- **Bootstrap Class Loader** – It loads JDK internal classes, typically loads `rt.jar` and other core classes for example `java.lang.*` package classes
- **Extensions Class Loader** – It loads classes from the JDK extensions directory, usually `$JAVA_HOME/lib/ext` directory.
- **System Class Loader** – It loads classes from the current classpath that can be set while invoking a program using `-cp` or `-classpath` command line options.

Java ClassLoader are hierarchical and whenever a request is raised to load a class, it delegates it to its parent and in this way uniqueness is maintained in the runtime environment. If the parent class loader doesn’t find the class then the class loader itself tries to load the class.

## 1.2 Classloader Vs JVM

**Class loaders** are responsible for **loading Java classes during runtime dynamically to the JVM** (Java Virtual Machine). Also, they are part of the **JRE (Java Runtime Environment)**. Hence, the JVM doesn’t need to know about the underlying files or file systems in order to run Java programs thanks to class loaders.

> 这段分清两个概念：Class loaders和JVM  
> （1）Class loaders负责将classes加载到JVM当中  
> （2）JVM不需要知道underlying files or file systems

Also, these Java classes aren’t loaded into memory all at once, but when required by an application. This is where **class loaders** come into the picture. They are responsible for **loading classes into memory**.

> 这里讲述了加载classes的时机：when required by an application

## 1.3 When and How are Classes Loaded

When classes are loaded? There are exactly **two cases**:

- when the `new` bytecode is executed (for example, `MyClass mc = new MyClass()`
- when the bytecodes make a `static` reference to a class (for example, `System.out`).

Class loaders are hierarchical. The very first class is specially loaded with the help of `static main()` method declared in your class. All the subsequently loaded classes are loaded by the classes, which are already loaded and running.

## 1.4 Static vs. Dynamic class loading

Classes are **statically** loaded with Java’s “`new`” operator. **Dynamic loading** is a technique for programmatically invoking the functions of a class loader at run-time by using `Class.forName()`

## 1.5 Difference between `loadClass` and `Class.forName`

`loadClass` only loads the class but **doesn’t initialize** the object whereas `Class.forName` initialize the object after loading it. For example, if you use `ClassLoader.loadClass` to load a JDBC driver, it won’t get registered, and you won’t be able to use JDBC.

The `java.lang.Class.forName(String className)` method returns the Class object associated with the class or interface with the given string name. This method throws `ClassNotFoundException` if the class is not found.

# 2. How do Class Loaders Work?

ClassLoader in Java works on three principle:

- Delegation
- Visibility
- Uniqueness

The **Delegation** principle forward request of class loading to parent class loader and only loads the class, if parent is not able to find or load class. **Visibility** principle allows child class loader to see all the classes loaded by parent ClassLoader, but parent class loader cannot see classes loaded by child. **Uniqueness** principle allows to load a class exactly once, which is basically achieved by delegation and ensures that child ClassLoader doesn't reload the class already loaded by parent.


## 2.1 Delegation Model

When the **JVM** requests a class, the **class loader** tries to locate the class and load the class definition into the runtime using **the fully qualified class name**.

> class loader -> the fully qualified class name -> JVM

**Class loaders** follow the **delegation model** where **on request to find a class or resource, a ClassLoader instance will delegate the search of the class or resource to the parent class loader**.

Let’s say we have a request to **load an application class into the JVM**. **The system class loader** first **delegates** the loading of that class to **its parent extension class loader** which in turn delegates it to **the bootstrap class loader**.

Only if the **bootstrap** and then **the extension class loader** is unsuccessful in loading the class, **the system class loader** tries to load the class itself.

If the last child class loader isn’t able to load the class either, it throws `java.lang.NoClassDefFoundError` or `java.lang.ClassNotFoundException`.

Let’s look at an example of output when `ClassNotFoundException` is thrown.

```java
package lsieun.jvm.classloader.loader_system;

public class LoadNoneExistedClass {
    public static void main(String[] args) {
        try {
            Class.forName("lsieun.jvm.SimpleClass");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
```

Output:

```txt
java.lang.ClassNotFoundException: lsieun.jvm.SimpleClass
	at java.net.URLClassLoader.findClass(URLClassLoader.java:381)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
	at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:349)
	at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
	at java.lang.Class.forName0(Native Method)
	at java.lang.Class.forName(Class.java:264)
	at lsieun.jvm.classloader.loader_system.LoadNoneExistedClass.main(LoadNoneExistedClass.java:6)
```

## 2.2 Unique Classes

As a consequence of the **delegation model**, **it’s easy to ensure unique classes** as we always try to delegate upwards.

If the parent class loader isn’t able to find the class, only then the current instance would attempt to do so itself.

## 2.3 Visibility

In addition, **children class loaders have visibility into classes loaded by its parent class loaders**.

For instance, classes loaded by the system class loader have visibility into classes loaded by the extension and Bootstrap class loaders but not vice-versa.

To illustrate this, if Class A is loaded by an application class loader and class B is loaded by the extensions class loader, then both A and B classes are visible as far as other classes loaded by Application class loader are concerned.

Class B, nonetheless, is the only class visible as far as other classes loaded by the extension class loader are concerned.

> 上面是从理论(Theory)角度来理解Classloader
> 下面是从代码(Code)角度来理解Classloader

# 3. Understanding `java.lang.ClassLoader`

Let’s discuss **a few essential methods** from the `java.lang.ClassLoader` class to get **a clearer picture of how it works**.

## 3.1 The `loadClass()` Method


```java
public Class<?> loadClass(String name) throws ClassNotFoundException {
    return loadClass(name, false);
}

protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException
{
    synchronized (getClassLoadingLock(name)) {
        // First, check if the class has already been loaded
        Class<?> c = findLoadedClass(name);
        if (c == null) {
            try {
                if (parent != null) {
                    c = parent.loadClass(name, false);
                } else {
                    c = findBootstrapClassOrNull(name);
                }
            } catch (ClassNotFoundException e) {
                // ClassNotFoundException thrown if class not found
                // from the non-null parent class loader
            }

            if (c == null) {
                // If still not found, then invoke findClass in order
                // to find the class.
                c = findClass(name);
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}
```


This method `loadClass(String name, boolean resolve)` is responsible for loading the class given a `name` parameter. The `name` parameter refers to **the fully qualified class name**.

The **Java Virtual Machine** invokes `loadClass()` method to resolve class references setting `resolve` to `true`. However, it isn’t always necessary to resolve a class. If we only need to determine if the class exists or not, then `resolve` parameter is set to `false`.

> 我还是不明白resolve的作用

The default implementation of the method searches for classes in the following order:

- (1) Invokes the `findLoadedClass(String)` method to see if the class is already loaded.
- (2) Invokes the `loadClass(String)` method on the parent class loader.
- (3) Invoke the `findClass(String)` method to find the class.


## 3.2 The `defineClass()` Method

```java
protected final Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError
{
    return defineClass(name, b, off, len, null);
}
```

This method is responsible for the conversion of **an array of bytes** into **an instance of a class**. And before we use the class, we need to resolve it.

In case data didn’t contain a valid class, it throws a `ClassFormatError`.

Also, we can’t override this method since it’s marked as `final`.


## 3.3 The `findClass()` Method

```java
protected Class<?> findClass(String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
}
```

This method finds the class with **the fully qualified name** as a parameter. We need to override this method in **custom class loader** implementations that follow the **delegation model** for loading classes.

Also, `loadClass()` invokes this method if **the parent class loader** couldn’t find the requested class.

The default implementation throws a `ClassNotFoundException` if no parent of the class loader finds the class.

## 3.4 The `getParent()` Method

```java
@CallerSensitive
public final ClassLoader getParent() {
    if (parent == null)
        return null;
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        // Check access to the parent class loader
        // If the caller's class loader is same as this class loader,
        // permission check is performed.
        checkClassLoaderPermission(parent, Reflection.getCallerClass());
    }
    return parent;
}
```

This method returns **the parent class loader** for **delegation**.

Some implementations use `null` to represent **the bootstrap class loader**.

## 3.5 The `getResource()` Method

```java
public URL getResource(String name) {
    URL url;
    if (parent != null) {
        url = parent.getResource(name);
    } else {
        url = getBootstrapResource(name);
    }
    if (url == null) {
        url = findResource(name);
    }
    return url;
}
```

This method tries to find a resource with the given name.

It will first **delegate** to the parent class loader for the resource. **If the parent is `null`, the path of the class loader built into the virtual machine is searched**.

```java
/**
* Find resources from the VM's built-in classloader.
*/
private static URL getBootstrapResource(String name) {
    URLClassPath ucp = getBootstrapClassPath();
    Resource res = ucp.getResource(name);
    return res != null ? res.getURL() : null;
}
```

If that fails, then the method will invoke `findResource(String)` to find the resource. The resource name specified as an input can be relative or absolute to the classpath.

```java
protected URL findResource(String name) {
    return null;
}
```

It returns an `URL` object for reading the resource, or `null` if the resource could not be found or if the invoker doesn’t have adequate privileges to return the resource.

It’s important to note that Java loads resources from the classpath.

Finally, **resource loading in Java is considered location-independent** as it doesn’t matter where the code is running as long as the environment is set to find the resources.

## 4. Context Classloaders

In general, **context class loaders** provide an **alternative** method to the **class-loading delegation scheme** introduced in J2SE.

> 虽然，我对context class loaders不甚了解，但是看上面这段话，应该是加载class的另一种选择。

Like we’ve learned before, **classloaders in a JVM follow a hierarchical model such that every class loader has a single parent with the exception of the bootstrap class loader**.

However, sometimes when JVM core classes need to dynamically load classes or resources provided by application developers, we might encounter a problem.

> 这里讲了，会遇到问题，到底是什么问题呢？

For example, in JNDI the core functionality is implemented by bootstrap classes in `rt.jar`. But these JNDI classes may load JNDI providers implemented by independent vendors (deployed in the application classpath). This scenario calls for **the bootstrap class loader** (parent class loader) to **load a class** visible to **application loader** (child class loader).

J2SE delegation doesn’t work here and to get around this problem, we need to find alternative ways of class loading. And it can be achieved using **thread context loaders**.

The `java.lang.Thread` class has a method `getContextClassLoader()` that returns the **ContextClassLoader** for the particular thread. The **ContextClassLoader** is provided by the creator of the thread when loading resources and classes.

If the value isn’t set, then it defaults to the class loader context of the parent thread.


## 5. Conclusion

Class loaders are essential to execute a Java program. 

We talked about **different types of class loaders** namely – **Bootstrap, Extensions and System class loaders**. **Bootstrap** serves as a parent for all of them and is responsible for loading the JDK internal classes. **Extensions and system**, on the other hand, loads classes from the Java extensions directory and classpath respectively.




