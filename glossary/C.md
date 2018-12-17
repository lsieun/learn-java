# C

## Closure

A **closure** is **a block of code** that can be referenced (and passed around) with access to the variables of the enclosing scope.

Since Java 1.1, **anonymous inner class** have provided this facility in a highly verbose manner. They also have a restriction of only being able to use **`final` (and definitely assigned) local variables**. (Note, even non-final local variables are in scope, but cannot be used.)

Java SE 8 is intended to have a more concise version of this for single-method interfaces, called "**lambdas**". Lambdas have much the same restrictions as anonymous inner classes, although some details vary randomly.


