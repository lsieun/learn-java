# parameterized type

<!-- TOC -->

- [1. concrete parameterized type](#1-concrete-parameterized-type)
  - [1.1. Can I use a concrete parameterized type like any other type?](#11-can-i-use-a-concrete-parameterized-type-like-any-other-type)
- [2. wildcard parameterized type](#2-wildcard-parameterized-type)
  - [What is a wildcard parameterized type?](#what-is-a-wildcard-parameterized-type)
  - [What is the unbounded wildcard parameterized type?](#what-is-the-unbounded-wildcard-parameterized-type)
  - [Can I use a wildcard parameterized type like any other type?](#can-i-use-a-wildcard-parameterized-type-like-any-other-type)

<!-- /TOC -->

## 1. concrete parameterized type

What is a concrete parameterized type? **An instantiation of a generic type where all type arguments are concrete types rather than wildcards**.

Examples of concrete parameterized types are `List<String>` , `Map<String,Date>`, but not `List<? extends Number>` or `Map<String,?>`.

### 1.1. Can I use a concrete parameterized type like any other type?

Almost.

Concrete parameterized types are concrete instantiations of a generic type.  They are almost like types; there are only a few restrictions. They can NOT be used for the following purposes:

- for creation of arrays
- in exception handling
- in a class literal
- in an `instanceof` expression

## 2. wildcard parameterized type

### What is a wildcard parameterized type?

**An instantiation of a generic type where the type argument is a wildcard** (as opposed to a concrete parameterized type).

A wildcard parameterized type is an instantiation of a generic type where at least one type argument is a wildcard. Examples of wildcard parameterized types are `Collection<?>`, `List<? extends Number>`, `Comparator<? super String>` and `Pair<String,?>`. **A wildcard parameterized type denotes a family of types comprising concrete instantiations of a generic type**. The kind of the wildcard being used determines which concrete parameterized types belong to the family. For instance, the wildcard parameterized type `Collection<?>` denotes the family of all instantiations of the `Collection` interface regardless of the type argument. The wildcard parameterized type `List<? extends Number>` denotes the family of all list types where the element type is a subtype of `Number`. The wildcard parameterized type `Comparator<? super String>` is the family of all instantiations of the `Comparator` interface for type argument types that are supertypes of `String`.

**A wildcard parameterized type is not a concrete type that could appear in a new expression**. A wildcard parameterized type is similar to an interface type in the sense that reference variables of a wildcard parameterized type can be declared, but no objects of the wildcard parameterized type can be created. The reference variables of a wildcard parameterized type can refer to an object that is of a type that belongs to the family of types that the wildcard parameterized type denotes.

Examples:

```java
Collection<?> coll = new ArrayList<String>();
List<? extends Number> list = new ArrayList<Long>();
Comparator<? super String> cmp = new RuleBasedCollator("< a< b< c< d");
Pair<String,?> pair = new Pair<String,String>();
```

Counter Example:

```java
List<? extends Number> list = new ArrayList<String>();  // error
```

Type `String` is not a subtype of `Number` and consequently `ArrayList<String>` does not belong to the family of types denoted by `List<? extends Number>`. For this reason the compiler issues an error message.

### What is the unbounded wildcard parameterized type?

**An instantiation of a generic type where all type arguments are the unbounded wildcard "?"**.

Examples of unbounded wildcard parameterized types are `Pair<?,?>` and `Map<?,?>`.

The unbounded wildcard parameterized type is assignment compatible with all instantiations of the correspinding generic type. Assignment of another instantiation to the unbounded wildcard instantiation is permitted without warnings; assignment of the unbounded wildcard instantiation to another instantiation is illegal.<sub>彼此之间的赋值，产生不同的结果</sub>

Example (of assignment compatibility):

```java
ArrayList <?>       anyList    = new ArrayList<Long>();
ArrayList<String> stringList = new ArrayList<String>();
anyList    = stringList;
stringList = anyList;      // error
```

The unbounded wildcard parameterized type is kind of the supertype of all other instantiations of the generic type: "subtypes" can be assigned to the "unbounded supertype", not vice versa.

### Can I use a wildcard parameterized type like any other type?

No. A wildcard parameterized type is not a type in the regular sense (different from a non-parameterized class/interface or a raw type).

Wildcard parameterized types can be used for typing (like non-parameterized classes and interfaces):

- as argument and return types of methods
- as type of a field or local reference variable
- as component type of an array
- as type argument of other parameterized types
- as target type in casts

Wildcard parameterized type can NOT be used for the following purposes (different from non-parameterized classes and interfaces):

- for creation of objects
- for creation of arrays (except unbounded wildcard)
- in exception handling
- in `instanceof` expressions (except unbounded wildcard) （我的理解是这样的：`obj instanceof List<? extends Number>`）
- as supertypes （我的理解是这样的：`class MyClass implements Comparable<?>`）
- in a class literal
