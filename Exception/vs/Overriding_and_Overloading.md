# Overriding and Overloading

An **overriding method** must have **the same argument list** and **return-type** (or subclass of its original from JDK 1.5). An **overloading method** must have **different argument list**, but it can have any return-type.

An **overriding method** **cannot** have more **restricted access**. For example, a method with `protected` access may be overridden to have `protected` or `public` access but not `private` or default access. This is because an overridden method is considered to be a replacement of its original, hence, it cannot be more restrictive.

An **overriding method** cannot declare **exception types** that were not declared in its original. However, it may declare exception types are the same as, or subclass of its original. It needs not declare all the exceptions as its original. It can throw fewer exceptions than the original, but not more.

An **overloading method** must be differentiated by its argument list. It cannot be differentiated by the return-type, the exceptions, and the modifier, which is illegal. It can have any return-type, access modifier, and exceptions, as long as it can be differentiated by the argument list.
