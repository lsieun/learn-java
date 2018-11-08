
- ClassPool
- Class
    - Constructor
    - Field
    - Method
- Interface
- Class loader

## ClassPool

获取`CtClass`:

- get()

```java
ClassPool pool = ClassPool.getDefault();
CtClass cc = pool.makeClass(new ByteArrayInputStream(classfileBuffer));
```


set Class path



The class `Javassist.CtClass` is an abstract representation of a class file. A `CtClass` (**compile-time class**) object is a handle for dealing with a class file.

The `ClassPool` object is a container of `CtClass` object representing a class file.

From the implementation viewpoint, `ClassPool` is a hash table of `CtClass` objects, which uses **the class names** as **keys**. `get()` in `ClassPool` searches **this hash table** to find a `CtClass` object associated with the specified key. If such a `CtClass` object is not found, `get()` reads **a class file** to construct a new `CtClass` object, which is recorded in **the hash table** and then returned as the resulting value of `get()`.

<!-- TOC -->

- [ClassPool](#classpool)
- [1. ClassPool](#1-classpool)
    - [Class search path](#class-search-path)
    - [Cascaded ClassPools](#cascaded-classpools)
- [CtClass](#ctclass)
    - [Defining a new class](#defining-a-new-class)
    - [Output](#output)
    - [Frozen classes](#frozen-classes)
- [Class loader](#class-loader)
    - [The `toClass` method in `CtClass`](#the-toclass-method-in-ctclass)
- [Introspection and customization](#introspection-and-customization)
- [CtMethod](#ctmethod)

<!-- /TOC -->



## 1. ClassPool

A `ClassPool` object is a container of `CtClass` objects. Once a `CtClass` object is created, it is recorded in a `ClassPool` for ever.

- get()
- makeClass()
- makeInterface()

### Class search path

The default `ClassPool` returned by a static method `ClassPool.getDefault()` searches the same path that the underlying JVM (Java virtual machine) has. **If a program is running on a web application server such as JBoss and Tomcat, the `ClassPool` object may not be able to find user classes** since such a web application server uses **multiple class loaders** as well as **the system class loader**. In that case, an **additional class path** must be registered to the `ClassPool`. 

- 通过一个类文件
- 通过一个目录
- 通过URL
- 通过字节数组

Suppose that pool refers to a ClassPool object:

```java
pool.insertClassPath(new ClassClassPath(this.getClass()));
```

This statement registers **the class path** that was used for loading the class of the object that this refers to. You can use any Class object as an argument instead of this.getClass(). The class path used for loading the class represented by that Class object is registered.

You can register **a directory name** as **the class search path**. For example, the following code adds a directory `/usr/local/javalib` to the search path:

```java
ClassPool pool = ClassPool.getDefault();
pool.insertClassPath("/usr/local/javalib");
```

Furthermore, you can directly give **a byte array** to a `ClassPool` object and construct a `CtClass` object from that array. To do this, use `ByteArrayClassPath`. For example,

```java
ClassPool cp = ClassPool.getDefault();
byte[] b = a byte array;
String name = "class name";
cp.insertClassPath(new ByteArrayClassPath(name, b));
CtClass cc = cp.get(name);
```

### Cascaded ClassPools











## CtClass

- 加载一个已有的Class
    - 通过全类名
    - 通过字节数组
- 创建一个新的Class


### Defining a new class

### Output

三种方式：

- 输出到文件（硬盘）
- 生成字节数组（内存，只是字节流）
- 加载到内存当中（内存，作为Class文件被Class Loader加载，应该会进入“类的定义区域”）

`writeFile()` translates the `CtClass` object into **a class file** and writes it on a local disk.

Javassist also provides a method for directly obtaining **the modified bytecode**. To obtain the bytecode, call `toBytecode()`:

```java
byte[] b = cc.toBytecode();
```
You can directly load the `CtClass` as well:

```java
Class clazz = cc.toClass();
```

`toClass()` requests **the context class loader** for **the current thread** to load **the class file** represented by the `CtClass`. It returns `a java.lang.Class` object representing the loaded class.

### Frozen classes

If a `CtClass` object is converted into a class file by `writeFile()`, `toClass()`, or `toBytecode()`, **Javassist freezes that `CtClass` object**. Further modifications of that `CtClass` object are not permitted. This is for warning the developers when they attempt to modify a class file that has been already loaded since the JVM does not allow reloading a class.

**A frozen `CtClass` can be defrost** so that **modifications of the class definition will be permitted**. For example,

```java
CtClasss cc = ...;
    :
cc.writeFile();
cc.defrost();
cc.setSuperclass(...);    // OK since the class is not frozen.
```

After `defrost()` is called, the `CtClass` object can be modified again.

## Class loader

If what classes must be modified is known in advance, the easiest way for modifying the classes is as follows:

1. Get a `CtClass` object by calling `ClassPool.get()`,
2. Modify it, and
3. Call `writeFile()` or `toBytecode()` on that `CtClass` object to obtain **a modified class file**.

### The `toClass` method in `CtClass`

## Introspection and customization

`CtClass` provides methods for introspection. The introspective ability of Javassist is compatible with that of the **Java reflection API**. `CtClass` provides `getName()`, `getSuperclass()`, `getMethods()`, and so on. `CtClass` also provides methods for modifying a class definition. It allows to **add a new field, constructor, and method**. **Instrumenting a method body** is also possible.

添加：

- filed
- constructor
- method

修改：

- method body

## CtMethod

Methods are represented by `CtMethod` objects. `CtMethod` provides several methods for **modifying the definition of the method**. 

**Note that** if **a method is inherited from a super class**, then the same `CtMethod` object that represents the inherited method represents the method declared in that super class. A CtMethod object corresponds to every method declaration.





