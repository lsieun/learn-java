# Reflection

2020年01月06日。我对于Reflection的理解是这样的：我之前的时候，对于ClassFile的格式进行了研究，现在呢，我再来回看Reflection这部分API，我觉得这两者竟如此的相似。换句话说，我觉得，Reflection的本质就是对于ClassFile的bytecode的进行分析，并封装成可以供Programmer进行调用的API。当然，Reflection并不止于对静态的ClassFile进行分析，它还能够对Class实例化后的Instance进行操作（例如，获取某个字段的值，或者调用某个方法）。对于静态的ClassFile进行分析，这是在compile time之后，就可以做的；而要创建Instance或者对Instance进行操作，就必须要在runtime时才能进行。

2020年01月06日。Reflection的三个主要作用：

- （1）解释ClassFile (runtime，根据我的理解，在compile time之后，就可以自己写程序进行解释)
- （2）创建Instance (runtime)
- （3）操作Instance (runtime)

```txt
compile time  |  runtime
-------------------------------------------------------
ClassFile    --> create Instance --> Instance Operation
-------------------------------------------------------
Reflection
```

Reflection was introduced in Java 1.1.

The Reflection API, which is part of the Java standard library, provides a way to (1)**explore intrinsic class details at runtime**, (2)**dynamically create new class instances** (without the explicit usage of the `new` operator), (3)**dynamically invoke methods**, (4)**introspect annotations**, and much, much more. It gives the Java developers a freedom to write the code which could adapt, verify, execute and even modify itself while it is being running.

这段列举了Reflection API的4个作用：

- (1) explore intrinsic class details
- (2) dynamically create new class
- (3) dynamically invoke methods
- (4) introspect annotations  备注：在`.class`文件当中，annotation是作为ClassFile/method_info/field_info属性的attribute属性在存储的

关于Reflection与bytecode(ASM/Javassist/BCEL)的最大区别就是：bytecode是在ClassLoader将`.class`文件加载到JVM内存之前发生的事情，也就是在runtime之前发生的；而Reflection是在runtime过程当中发生的。

The Reflection API is designed in quite intuitive way and is hosted under the `java.lang.reflect` package. Its structure follows closely the **Java language concepts** and has all the elements to represent **classes** (including generic versions), **methods**, **fields** (members), **constructors**, **interfaces**, **parameters** and **annotations**.

## Class

The entry point for the Reflection API is the respective instance of the `Class<?>` class. For example, the simplest way to **list all public methods** of the class `String` is by using the `getMethods()` method call:

```java
final Method[] methods = String.class.getMethods();
for( final Method method: methods ) {
    System.out.println( method.getName());
}
```

Following the same principle, we can **list all public fields** of the class `String` is by using the `getFields()` method call, for example:

```java
final Field[] fields = String.class.getFields();
for( final Field field: fields ) {
    System.out.println( field.getName() );
}
```

## Method

- `getMethod()`: find **any `public` method**, be it `static` or `instance` that is **defined in the class** or **any of its superclasses**.
- `getDeclaredMethod()`: get **any method defined in the class**. This includes `public`, `protected`, `default access`, and even `private` methods but excludes inherited ones.

## Reflection API pitfalls

Also, be aware that even though the reflection API is very powerful, it has a few pitfalls.

> 尽管Reflection API很强大，但是它也有缺点。

First of all, it is a subject of security permissions and may not be available in all environments your code is running on.

> 第一个缺点，受运行环境制约。

Secondly, it could have a performance impact on your applications. From execution prospective, the calls to reflection API are quite expensive.

> 第二个缺点，降低Java的性能。

Lastly, reflection API does not provide enough **type safety guarantees**, forcing developers to use `Object` instances in most of the places, and is quite limited in transforming `constructor/method arguments` or `method return values`.

> 第三个缺点，缺少对type safety的保证。

Since the Java 7 release, there are a couple of useful features which could provide much faster, alternative way to access some functionality used to be available only through reflection calls. The next section will introduce you to them.

> 从Java 7以后，有一些替代性的feature来取代Reflection API。

## Method Handles

The Java 7 release introduced a new very important feature into the JVM and Java standard library - **method handles**. Method handle is a typed(对应第三个缺点), directly executable reference(对应第二个缺点) to an underlying **method**, **constructor** or **field** (or similar low-level operation) with optional transformations of arguments or return values(对应第三个缺点). They are in many respects better alternative to method invocations performed using the Reflection API.

Let us take a look on a code snippet which uses method handles to dynamically invoke the method `length()` on the `String` class.

```java
final Lookup lookup = MethodHandles.lookup();
final MethodType methodType = MethodType.methodType(int.class);
final MethodHandle methodHandle = lookup.findVirtual(String.class, "length", methodType);
final int length = (int) methodHandle.invokeExact("sample string");
```

The example above is not very complicated and just outlines the basic idea of what method handles are capable of. Please compare it with the same implementation which uses the Reflection API from Reflection API section. However it does look **a bit more verbose**(看起来有点繁琐) but from the **performance**(对应Reflection API的第二个缺点) and **type safety**(对应Reflection API的第三个缺点) prospective method handles are better alternative.

Method handles are very powerful tool and they build a necessary foundation for effective implementation of dynamic (and scripting) languages on the JVM platform.







