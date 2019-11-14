# Closure

<!-- TOC -->

- [1. Closure (mathematics)](#1-closure-mathematics)
- [2. Closure (computer programming)](#2-closure-computer-programming)
  - [2.1. name binding](#21-name-binding)
  - [2.2. Lexical scoping vs. dynamic scoping](#22-lexical-scoping-vs-dynamic-scoping)
  - [2.3. First-class function](#23-first-class-function)
  - [2.4. Free variables and bound variables](#24-free-variables-and-bound-variables)
- [3. Reference](#3-reference)

<!-- /TOC -->

closure 闭合；封闭性 the characteristic of a set in which the application of a given mathematical operation to any member of the set always has another member of the set as its result

## 1. Closure (mathematics)

A set is **closed** under **an operation** if performance of that operation on members of the set always produces a member of that set. For example, the positive integers are closed under addition, but not under subtraction: `1-2` is not a positive integer even though both 1 and 2 are positive integers. Another example is the set containing only zero, which is closed under addition, subtraction and multiplication (because `0+0=0`, `0-0=0`, and `0*0=0`).

Similarly, a set is said to be **closed under a collection of operations** if it is closed under each of the operations individually.

## 2. Closure (computer programming)

In programming languages, a **closure**, also **lexical closure** or **function closure**, is a technique for implementing **lexically scoped name binding** in a language with **first-class functions**. Operationally, a closure is a **record**(In computer science, a record is a basic data structure.) storing **a function** together with **an environment**. The environment is a mapping associating each **free variable** of the function (variables that are used locally, but defined in an enclosing scope) with the value or reference to which the name was bound when the closure was created. Unlike a plain function, a closure allows the function to access those captured variables through the closure's copies of their values or references, even when the function is invoked outside their scope.

> a closure = a function + an environment  
> The environment is a mapping: free variable --> the value or reference

我觉得，Closure真正想表达的是一种“生存状态”：不依赖于外界，能够自成一个体系，并能存活下去；凡是被认为是缺失的东西，都能从自身找到。

### 2.1. name binding

In programming languages, **name binding** is the association of **entities** (data and/or code) with **identifiers**. An identifier bound to an object is said to **reference** that object. Machine languages have no built-in notion of identifiers<sub>对于机器来说，并不存在identifier的概念</sub>, but **name-object bindings** as a service and notation for the programmer is implemented by programming languages.<sub>这个name-object bindings是给programmer用的，是由具体的programming language来实现的。</sub> Binding is intimately connected with **scoping**<sub>这个binding和scope是有关系的</sub>, as scope determines which names bind to which objects – at which locations in the program code (**lexically**) and in which one of the possible execution paths (**temporally**).

### 2.2. Lexical scoping vs. dynamic scoping

The use of local variables — of variable names with limited scope, that only exist within a specific function — helps avoid the risk of a name collision between two identically named variables. However, there are two very different approaches to answering this question: What does it mean to be "within" a function?

> local variables = variable names with limited scope

In **lexical scoping** (or **lexical scope**; also called **static scoping** or **static scope**), if a variable name's scope is a certain function, then **its scope** is **the program text of the function definition**: within that text, the variable name exists, and is bound to the variable's value, but outside that text, the variable name does not exist.

By contrast, in **dynamic scoping** (or **dynamic scope**), if a variable name's scope is a certain function, then **its scope** is **the time-period during which the function is executing**: while the function is running, the variable name exists, and is bound to its value, but after the function returns, the variable name does not exist.

This means that if function `f` invokes a separately defined function `g`, then under **lexical scoping**, function `g` does not have access to `f`'s local variables (assuming the text of `g` is not inside the text of `f`), while under **dynamic scoping**, function `g` does have access to `f`'s local variables (since `g` is invoked during the invocation of `f`).<sub>这里通过举例来说明两者的不同之处</sub>

Consider, for example, the program on the below. The first line, `x=1`, creates a global variable `x` and initializes it to `1`. The second line, `function g () { echo $x ; x=2 ; }`, defines a function `g` that prints out ("echoes") the current value of `x`, and then sets `x` to `2` (overwriting the previous value). The third line, `function f () { local x=3 ; g ; }` defines a function `f` that creates a local variable `x` (hiding the identically named global variable) and initializes it to `3`, and then calls `g`. The fourth line, `f`, calls `f`. The fifth line, `echo $x`, prints out the current value of `x`.

```bash
# bash language
$ x=1
$ function g () { echo $x ; x=2 ; }
$ function f () { local x=3 ; g ; }
$ f # does this print 1, or 3?
3
$ echo $x # does this print 1, or 2?
1
```

So, what exactly does this program print? It depends on **the scoping rules**. If the language of this program is one that uses **lexical scoping**, then `g` prints and modifies the global variable `x` (because `g` is defined outside `f`), so the program prints `1` and then `2`. By contrast, if this language uses **dynamic scoping**, then `g` prints and modifies `f`'s local variable `x` (because `g` is called from within `f`), so the program prints `3` and then `1`. (As it happens, the language of the program is **Bash**, which uses **dynamic scoping**; so the program prints `3` and then `1`.)

### 2.3. First-class function

In computer science, a programming language is said to have **first-class functions** if it treats functions as **first-class citizens**. This means the language supports **passing functions as arguments to other functions**, **returning them as the values from other functions**, and **assigning them to variables** or **storing them in data structures**. Some programming language theorists require support for **anonymous functions** (function literals) as well. In languages with **first-class functions**, the names of functions **do not have any special status**; they are treated like **ordinary variables** with **a function type**. The term was coined by Christopher Strachey in the context of "functions as first-class citizens" in the mid-1960s.

### 2.4. Free variables and bound variables

In **mathematics**, a **free variable** is a notation (symbol) that specifies places in an expression where substitution may take place and is not a parameter of this or any container expression.

In **computer programming**, the term **free variable** refers to variables used in a function that are neither **local variables** nor **parameters of that function**. The term **non-local variable** is often a synonym in this context.

A **bound variable** is a variable that was **previously free**, but **has been bound to a specific value or set of values** called **domain of discourse or universe**. For example, the variable `x` becomes **a bound variable** when we write: 

- For all `x`, (x + 1)<sup>2</sup> = x<sup>2</sup> + 2x + 1.
- There exists `x` such that x<sup>2</sup> = 2.

## 3. Reference

- [Closure (mathematics)](https://en.wikipedia.org/wiki/Closure_(mathematics))
- [Closure (computer programming)](https://en.wikipedia.org/wiki/Closure_(computer_programming))
- [Name binding](https://en.wikipedia.org/wiki/Name_binding)
- [First-class function](https://en.wikipedia.org/wiki/First-class_function)
