# The ClassLoader structure

# 1. ClassLoader structure overview

A ClassLoader's basic purpose is to service a request for a class. The JVM needs a class, so it asks the ClassLoader, by name, for this class, and the ClassLoader attempts to return a Class object that represents the class.

By overriding different methods corresponding to different stages of this process, you can create a custom ClassLoader.

In the remainder of this section, you'll learn about the critical methods of the Java ClassLoader. You'll find out what each one does and how it fits into the process of loading class files. You'll also find out what code you'll need to write when creating your own ClassLoader.

- 加载Class
    - `loadClass`
    - `findLoadedClass` 从已经加载的类中查找
    - `findSystemClass` 使用默认的Classloader加载
    - `findClass` 进行自定义加载Class
- 将bytes转换为Class
    - `defineClass`
    - `resolveClass` 对Class进行初始化
- 获取Classloader
    - `getSystemClassLoader` 获取默认的ClassLoader
    - `getParent` 可以一步步获取上一层的Classloader

# 2. `java.lang.Classloader` Source Code

## 2.1 Method `loadClass`

`ClassLoader.loadClass()` is the entry point to the ClassLoader. Its signature is as follows:

```java
Class loadClass(String name, boolean resolve);
```

The `name` parameter specifies the name of the class that the JVM needs.

The `resolve` parameter tells the method whether or not the class needs to be resolved. You can think of **class resolution** as the task of **completely preparing the class for execution**. Resolution is not always needed. If the JVM needs only to determine that the class exists or to find out what its superclass is, then resolution is not required.

In Java version 1.1 and earlier, the `loadClass` method is the only method that you need to override to create a custom ClassLoader. (ClassLoader changes in Java 2 provides information about the `findClass()` method available in Java 1.2.)

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
            long t0 = System.nanoTime();
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
                long t1 = System.nanoTime();
                c = findClass(name);

                // this is the defining class loader; record the stats
                sun.misc.PerfCounter.getParentDelegationTime().addTime(t1 - t0);
                sun.misc.PerfCounter.getFindClassTime().addElapsedTimeFrom(t1);
                sun.misc.PerfCounter.getFindClasses().increment();
            }
        }
        if (resolve) {
            resolveClass(c);
        }
        return c;
    }
}

/**
    * Returns a class loaded by the bootstrap class loader;
    * or return null if not found.
    */
private Class<?> findBootstrapClassOrNull(String name)
{
    if (!checkName(name)) return null;

    return findBootstrapClass(name);
}

// return null if not found
private native Class<?> findBootstrapClass(String name);
```


## 2.2 Method `findLoadedClass`

`findLoadedClass` serves as a cache: when `loadClass` is asked to load a class, it can call this method to see if the class has already been loaded by this ClassLoader, saving the trouble of reloading a class that has already been loaded. This method should be called first.

```java
protected final Class<?> findLoadedClass(String name) {
    if (!checkName(name))
        return null;
    return findLoadedClass0(name);
}

private native final Class<?> findLoadedClass0(String name);
```

## 2.3 Method `findSystemClass`

The `findSystemClass` method loads files from the local filesystem. It looks for a class file in the local filesystem, and if it's there, turns it into a class using `defineClass` to convert raw bytes into a Class object. This is the default mechanism for how the JVM normally loads classes when you are running a Java application.

For our custom ClassLoader, we'll use `findSystemClass` only after we've tried everything else to load a class. The reason is simple: our ClassLoader is responsible for carrying out special steps for loading classes, but not for all classes. For example, even if our ClassLoader loads some classes from a remote Web site, there are still plenty of basic Java libraries on the local machine that must also be loaded. These classes aren't our concern, so we ask the JVM to load them in the default way: from the local filesystem. This is what `findSystemClass` does.

```java
protected final Class<?> findSystemClass(String name) throws ClassNotFoundException
{
    ClassLoader system = getSystemClassLoader();
    if (system == null) {
        if (!checkName(name))
            throw new ClassNotFoundException(name);
        Class<?> cls = findBootstrapClass(name);
        if (cls == null) {
            throw new ClassNotFoundException(name);
        }
        return cls;
    }
    return system.loadClass(name);
}
```

The procedure works as follows:

- Our custom ClassLoader is asked to load a class.
- We check the remote Web site, to see if the class is there.
- If it is, fine; we grab the class and we're done.
- If it's not there, we assume that this class is one from the basic Java libraries and call `findSystemClass` to load it from the filesystem.

In most custom ClassLoaders, you would want to call `findSystemClass` first to save time spent looking on the remote Web site for the many Java library classes that are typically loaded. However, as we'll see in the next section, we don't want to let the JVM load a class from the local filesystem until we've made sure that we've automatically compiled our application's code.

## 2.4 Method: `findClass`

This method is called by the default implementation of `loadClass`. The purpose of `findClass` is to contain all your specialized code for your ClassLoader, without having to duplicate the other code (such as calling the system ClassLoader when your specialized method has failed).

```java
protected Class<?> findClass(String name) throws ClassNotFoundException {
    throw new ClassNotFoundException(name);
}
```


## 2.5 Method `defineClass`

The `defineClass` method is the central mystery of the ClassLoader. This method takes **a raw array of bytes** and turns it into **a Class object**. The raw array contains the data that, for example, was loaded from the filesystem or across the network.

`defineClass` takes care of a lot of complex, mysterious, and implementation-dependent aspects of the JVM -- it parses the bytecode format into a run-time data structure, checks for validity, and so on. But don't worry, you don't have to write it yourself. In fact, you couldn't override it even if you wanted to because it's marked as final.

```java
protected final Class<?> defineClass(String name, byte[] b, int off, int len) throws ClassFormatError
{
    return defineClass(name, b, off, len, null);
}

protected final Class<?> defineClass(String name, byte[] b, int off, int len, ProtectionDomain protectionDomain) throws ClassFormatError
{
    protectionDomain = preDefineClass(name, protectionDomain);
    String source = defineClassSourceLocation(protectionDomain);
    Class<?> c = defineClass1(name, b, off, len, protectionDomain, source);
    postDefineClass(c, protectionDomain);
    return c;
}
```


## 2.6 Method `resolveClass`

As I mentioned previously, loading a class can be done **partially** (without resolution) or **completely** (with resolution). When we write our version of `loadClass`, we may need to call `resolveClass`, depending on the value of the `resolve` parameter to `loadClass`.

```java
protected final void resolveClass(Class<?> c) {
    resolveClass0(c);
}

private native void resolveClass0(Class<?> c);
```



## 2.7 Method: `getSystemClassLoader`

Whether you override findClass or loadClass, getSystemClassLoader gives you direct access to the system ClassLoader in the form of an actual ClassLoader object (instead of accessing it implicitly through the findSystemClass call).

```java
public static ClassLoader getSystemClassLoader() {
    initSystemClassLoader();
    if (scl == null) {
        return null;
    }
    SecurityManager sm = System.getSecurityManager();
    if (sm != null) {
        checkClassLoaderPermission(scl, Reflection.getCallerClass());
    }
    return scl;
}
```

## 2.8 Method: `getParent`

This method allows a ClassLoader to get at its parent ClassLoader, in order to delegate class requests to it. You might use this approach when your custom ClassLoader can't find a class using your specialized method.

The parent of a ClassLoader is defined as the ClassLoader of the object containing the code that created that ClassLoader.

```java
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







