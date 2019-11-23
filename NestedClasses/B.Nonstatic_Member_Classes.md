# Non-Static Nested Classes

Here are a few quick points to remember about non-static nested classes:

- They are also called inner classes
- They can have all types of access modifiers in their declaration
- Just like instance variables and methods, inner classes are associated with an instance of the enclosing class
- They have access to all members of the enclosing class, regardless of whether they are static or non-static
- They can only define non-static members

这段理解：

- 外部
  - 归属：instance
  - 访问外部：static and non-static
- 内部
  - modifiers： all types of access modifiers
  - members：non-static members

Here's how we can declare an inner class:

```java
public class Outer {

    public class Inner {
        // ...
    }
}
```

If we declare a nested class with a modifier `static`, then it's a static member one. Otherwise, it's an inner class. Even though syntactically the difference is just a single keyword (i.e., static), semantically there is a huge difference between these kinds of nested classes. Inner class instances are bound to the enclosing class ones and therefore they have access to their members. We should be aware of this issue when selecting whether to make a nested class be an inner one.

To instantiate an inner class, we must first instantiate its enclosing class.

Let's see how we can do that:

```java
Outer outer = new Outer();
Outer.Inner inner = outer.new Inner();
```
