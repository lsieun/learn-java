# Difference

## What is the difference between the unbounded wildcard parameterized type and the raw type?

**The compiler issues error messages for an unbounded wildcard parameterized type while it only reports "unchecked" warnings for a raw type**.

```java
List<?> wildcardList = new ArrayList<>();
List rawList = new ArrayList();
List<String> concreteList = new ArrayList<>();

wildcardList = concreteList; // fine
rawList = concreteList; // fine

wildcardList = rawList; // fine
rawList = wildcardList; // fine

concreteList = wildcardList; // error
concreteList = (List<String>) wildcardList; // Unchecked cast: 'java.util.List<capture<?>>' to 'java.util.List<java.lang.String>'
concreteList = rawList; // Unchecked assignment: 'java.util.List' to 'java.util.List<java.lang.String>'
```

In code written after the introduction of genericity into the Java programming language you would usually **avoid use of raw types**, because it is discouraged and raw types might no longer be supported in future versions of the language (according to the Java Language Specification). Instead of the raw type you can use the unbounded wildcard parameterized type.

The **raw type** and the **unbounded wildcard parameterized type** have **a lot in common**. Both act as kind of a supertype of all instantiations of the corresponding generic type. Both are so-called reifiable types. Reifiable types can be used in `instanceof` expressions and as the component type of arrays, where non-reifiable types (such as concrete and bounded wildcard parameterized type) are not permitted.

In other words, the raw type and the unbounded wildcard parameterized type are semantically equivalent. **The only difference** is that **the compiler applies stricter rules to the unbounded wildcard parameterized type than to the corresponding raw type**. Certain operations performed on the raw type yield "unchecked" warnings. The same operations, when performed on the corresponding unbounded wildcard parameterized type, are rejected as errors.

