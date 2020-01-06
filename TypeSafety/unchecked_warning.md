# unchecked warning

## What is an "unchecked" warning?

**A warning** by which the compiler indicates that it cannot ensure **type safety**.

The term "unchecked" warning is misleading. It does not mean that the warning is unchecked in any way. The term "unchecked" refers to the fact that **the compiler and the runtime system do not have enough type information to perform all type checks that would be necessary to ensure type safety**. In this sense, certain operations are "unchecked".

The most common source of "unchecked" warnings is **the use of raw types**.  "unchecked" warnings are issued when an object is accessed through a raw type variable, because the raw type does not provide enough type information to perform all necessary type checks.

Example (of unchecked warning in conjunction with **raw types**):

```java
import java.util.TreeSet;

public class HelloWorld {
    public void test() {
        TreeSet set = new TreeSet();
        set.add("abc");        // unchecked warning
        set.remove("abc");
    }
}
```

```bash
$ javac -Xlint:unchecked HelloWorld.java
HelloWorld.java:6: warning: [unchecked] unchecked call to add(E) as a member of the raw type TreeSet
        set.add("abc");        // unchecked warning
               ^
  where E is a type-variable:
    E extends Object declared in class TreeSet
1 warning
```

When the `add` method is invoked the compiler does not know whether it is safe to add a `String` object to the collection.  If the `TreeSet` is a collection that contains `String`s (or a supertype thereof), then it would be safe.  But from the type information provided by the raw type `TreeSet` the compiler cannot tell.  Hence the call is potentially unsafe and an "unchecked" warning is issued.

"unchecked" warnings are also reported when the compiler finds **a cast** whose target type is either **a parameterized type** or **a type parameter**.

Example (of an unchecked warning in conjunction with a cast to a parameterized type or type variable):

```java
import java.lang.reflect.Method;

public class Wrapper<T> {
    private T wrapped;

    public Wrapper(T arg) {
        wrapped = arg;
    }

    public Wrapper<T> clone() {
        Wrapper<T> clon = null;
        try {
            clon = (Wrapper<T>) super.clone(); // unchecked warning
        } catch (CloneNotSupportedException e) {
            throw new InternalError();
        }
        try {
            Class<?> clzz = this.wrapped.getClass();
            Method meth = clzz.getMethod("clone", new Class[0]);
            Object dupl = meth.invoke(this.wrapped, new Object[0]);
            clon.wrapped = (T) dupl; // unchecked warning
        } catch (Exception e) {
        }
        return clon;
    }
}
```

```bash
$ javac -Xlint:unchecked Wrapper.java 
Wrapper.java:13: warning: [unchecked] unchecked cast
            clon = (Wrapper<T>) super.clone(); // unchecked warning
                                           ^
  required: Wrapper<T>
  found:    Object
  where T is a type-variable:
    T extends Object declared in class Wrapper
Wrapper.java:21: warning: [unchecked] unchecked cast
            clon.wrapped = (T) dupl; // unchecked warning
                               ^
  required: T
  found:    Object
  where T is a type-variable:
    T extends Object declared in class Wrapper
2 warnings
```

**A cast** whose target type is either **a (concrete or bounded wildcard) parameterized type** or **a type parameter** is unsafe, if a dynamic type check at runtime is involved.  At runtime, only the type erasure is available, not the exact static type that is visible in the source code. As a result, the runtime part of the cast is performed based on the type erasure, not on the exact static type.

In the example, the cast to `Wrapper<T>` would check whether the object returned from `super.clone` is a `Wrapper`, not whether it is a wrapper with a particular type of members.  Similarly, the casts to the type parameter `T` are cast to type `Object` at runtime, and probably optimized away altogether. Due to type erasure, the runtime system is unable to perform more useful type checks at runtime.

In a way, the source code is misleading, because it suggests that a cast to the respective target type is performed, while in fact the dynamic part of the cast only checks against the type erasure of the target type.  The "unchecked" warning is issued to draw the programmer's attention to this mismatch between the static and dynamic aspect of the cast.

## How can I disable or enable "unchecked" warnings?

Via the compiler options `-Xlint:unchecked` and `-Xlint:-unchecked` and via the standard annotation `@SuppressWarnings("unchecked")`.

The compiler option `-Xlint:-unchecked` disables all unchecked warnings that would occur in a compilation.

The annotation `@SuppressWarnings("unchecked")` suppresses all warnings for the annotated part of the program.

Note, in the first release of Java 5.0 the `SuppressWarnings` annotation is not yet supported.

## Reference

- [Under The Hood Of The Compiler](http://www.angelikalanger.com/GenericsFAQ/FAQSections/TechnicalDetails.html)
