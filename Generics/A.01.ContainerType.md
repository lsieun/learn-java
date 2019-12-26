# Container Type

<!-- TOC -->

- [1. Are there any types that cannot have type parameters?](#1-are-there-any-types-that-cannot-have-type-parameters)
- [2. static members](#2-static-members)

<!-- /TOC -->

## 1. Are there any types that cannot have type parameters?

**All types, except enum types, anonymous inner classes and exception classes, can be generic**.

Almost all reference types can be generic.  This includes classes, interfaces, nested (static) classes, nested interfaces, inner (non-static) classes, and local classes.

The following types cannot be generic:

**Anonymous inner classes**. They can implement a parameterized interface or extend a parameterized class, but they cannot themselves be generic classes. A generic anonymous class would be nonsensical.  Anonymous classes do not have a name, but the name of a generic class is needed for declaring an instantiation of the class and providing the type arguments.  Hence, generic anonymous classes would be pointless.

**Exception types**. A generic class must not directly or indirectly be derived from class `Throwable`. Generic exception or error types are disallowed because the exception handling mechanism is a runtime mechanism and the Java virtual machine does not know anything about Java generics. The JVM would not be capable of distinguishing between different instantiations of a generic exception type. Hence, generic exception types would be pointless.

**Enum types**. Enum types cannot have type parameters.  Conceptually, an enum type and its enum values are static.  Since type parameters cannot be used in any static context, the parameterization of an enum type would be pointless.

Annotation. 这是我自己测试的结果。

```java
// 会编译出错，提示@interface may not have type parameters
public @interface HelloWorld<T> {
    Class<T> value();
}
```

## 2. static members

Can generic types have static members? **Yes**.

Generic types can have **static members**, including **static fields**, **static methods** and **static nested types**. Each of these static members exists once per enclosing type, that is, independently of the number of objects of the enclosing type and regardless of the number of instantiations of the generic type  that may be used somewhere in the program. The name of the static member consists - as is usual for static members - of the scope (packages and enclosing type) and the member's name. If the enclosing type is generic, then the type in the scope qualification must be the **raw type**, not a parameterized type.
