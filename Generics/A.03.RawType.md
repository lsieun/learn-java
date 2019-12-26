# raw type

A generic type without type arguments is called **raw type** and is only allowed for reasons of compatibility with non-generic Java code. Use of raw types is discouraged.<sub>现在</sub> The Java Language Specification even states that it is possible that future versions of the Java programming language will disallow the use of raw types.<sub>未来</sub>

## What is the raw type?

**The generic type without any type arguments**.

The generic type without any type arguments, like `Collection`, is called **raw type**.

The raw type is assignment compatible with all instantiations of the generic type. Assignment of an instantiation of a generic type to the corresponding raw type is permitted without warnings; assignment of the raw type to an instantiation yields an "unchecked conversion" warning.

Example (of assignment compatibility):

```java
ArrayList         rawList    = new ArrayList(); 
ArrayList<String> stringList = new ArrayList<String>();
rawList    = stringList;
stringList = rawList;      // unchecked warning
```

The "unchecked" warning indicates that the compiler does not know whether the raw type `ArrayList` really contains strings. A raw type `ArrayList` can in principle contain any type of object and is similar to a `ArrayList<Object>`.

## Why are raw types permitted?

**To facilitate interfacing with non-generic (legacy) code**.

Raw types are permitted in the language predominantly to facilitate interfacing with non-generic (legacy) code.

If, for instance, you have a non-generic legacy method that takes a `List` as an argument, you can pass a parameterized type such as `List<String>` to that method. Conversely, if you have a method that returns a `List`, you can assign the result to a reference variable of type `List<String>`, provided you know for some reason that the returned list really is a list of strings.

Example (of interfacing with legacy code using raw types):

```java
class SomeLegacyClass {
  public void setNames(List c) { ... }
  public List getNames() { ... }
}

final class Test {
  public static void main(String[] args) {
    SomeLegacyClass obj = new SomeLegacyClass();
    List<String> names = new LinkedList<String>();
    ...  fill list ...

    obj.setNames(names);

    names = obj.getNames();    // unchecked warning
  }
}
```

A `List<String>` is passed to the `setNames` method that asks for an argument of the raw type `List`.  The  conversion from a `List<String>` to a `List` is safe because a method that can handle a heterogeneous list of objects can certainly cope with a list of strings.

The `getNames` method returns a raw type `List`, which we assign to a variable of type `List<String>`. The compiler has not enough information to ensure that the list returned really is a list of strings. Despite of that, the compiler permits the conversion from the raw type `List` to the more specific type `List<String>`, in order to allow this kind of mixing of non-generic and generic Java code. Since the conversion from `List` to `List<String>` is not type-safe, the assignment is flagged as an "unchecked assignment".

The use of raw types in code written after the introduction of genericity into the Java programming language is discouraged. According to the Java Language Specification, it is possible that future versions of the Java programming language will disallow the use of raw types.

## Can I use a raw type like any other type?

Yes, but certain uses will result in "unchecked" warnings.

Raw types can be used like regular types without any restrictions, except that certain uses will result in "unchecked" warnings.

Example (of a parameterized type):

```java
interface Copyable<T> {
  T copy();
}

final class Wrapped <Elem extends Copyable<Elem>> {
  private Elem theObject;
  public Wrapped(Elem arg) { theObject = arg.copy(); }

  public void setObject( Elem arg) { theObject = arg.copy(); }

  public Elem getObject() { return theObject.copy(); }

  public boolean equals(Object other) {
    if (other == null) return false;
    if (! (other instanceof Wrapped))  return false;
    return (this.theObject.equals(((Wrapped)other).theObject));
  }
}
```

Methods or constructors of a raw type have the signature that they would have after type erasure. A method or constructor call to a raw type generates an unchecked warning if the erasure changes the argument types.

Example (same as above - after type erasure):

```java
interface Copyable {
  Object copy();
}

final class Wrapped {
  private Copyable theObject;
  public Wrapped(Copyable arg) { theObject = arg.copy(); }

  public void setObject(Copyable arg) { theObject = arg.copy(); }

  public Copyable getObject() { return theObject.copy(); }

  public boolean equals(Object other) {
    if (other == null) return false;
    if (! (other instanceof Wrapped))  return false;
    return (this.theObject.equals(((Wrapped)other).theObject));
  }
}
```

Invocation of a method or constructor, whose argument type changed in the course of type erasure is unsafe and is flagged as an "unchecked" operation. For instance, the method `setObject` has the signature `void setObject(Copyable)` after type erasure and its invocation results in an "unchecked" warning. The invocation is unsafe because the compiler cannot ensure that the argument passed to the method is compatible to the "erased" type that the type parameter `Elem` stands for.

Example (using the raw type):

```java
class MyString implements Copyable<MyString> {
  private StringBuilder buffer;
  public MyString(String s) { buffer = new StringBuilder(s); }
  public MyString copy() { return new MyString(buffer.toString()); }
  ...
}

class Test {
  private static void test(Wrapped wrapper) {
    wrapper.setObject (new MyString("Deutsche Bank"));  // unchecked warning
    Object s = wrapper.getObject ();
  }

  public static void main(String[] args) {
    Wrapped<MyString> wrapper = new Wrapped<MyString>(new MyString("Citibank"));
    test(wrapper);
  }
}
```

If the method's argument type is not changed by type erasure, then the method call is safe. For instance, the method `getObject` has the signature `Copyable getObject(void)` after type erasure and its invocation is safe and warning-free.

Fields of a raw type have the type that they would have after type erasure. A field assignment to a raw type generates an unchecked warning if erasure changes the field type. In our example, the field `theObject` of the raw type `Wrapped` is changed by type erasure and is of type `Copyable` after type erasure.

If the `theObject` field  were public and we could assign to it, the assignment would be unsafe because the compiler cannot ensure that the value being assigned really is of type `Elem`. Yet the assignment is permitted and flagged as an "unchecked" assignment. Reading the field is safe and does not result in a warning.
