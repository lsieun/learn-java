# Lambda expressions

<!-- TOC -->

- [1. Lambdas in a nutshell](#1-lambdas-in-a-nutshell)
  - [1.1. lambda expression definition](#11-lambda-expression-definition)
  - [1.2. lambda expression composition](#12-lambda-expression-composition)

<!-- /TOC -->

For now you can think of **lambda expressions** as **anonymous functions**, basically methods without declared names, but which can also be passed as arguments to a method as you can with an anonymous class.

```txt
behavior parameterization = 将“代码”作为“参数”进行传递。

这里很重要的一点是：“参数”是一个“接口”类型，这个接口中定义了“方法”，作为“代码”的容器

在Java 8之前，
- （1） 使用普通类，实现那个“接口”中的方法 named class
- （2） 使用匿名类，实现那个“接口”中的方法 anonymous class

在Java 8之后，
- （1） 使用普通方法 named functions
- （2） 使用lambda expression = anonymous functions
```

## 1. Lambdas in a nutshell

### 1.1. lambda expression definition

A **lambda expression** can be understood as a **concise** representation of an **anonymous function** that can be **passed around**: it doesn’t have a name, but it has a list of parameters, a body, a return type, and also possibly a list of exceptions that can be thrown.

That’s one big definition; let’s break it down:

- **Anonymous**— We say anonymous because it doesn’t have an explicit name like a method would normally have: less to write and think about!
- **Function**— We say function because a lambda isn’t associated with a particular class like a method is. But like a method, a lambda has a list of parameters, a body, a return type, and a possible list of exceptions that can be thrown.
- **Passed around**— A lambda expression can be passed as argument to a method or stored in a variable.
- **Concise**— You don’t need to write a lot of boilerplate like you do for anonymous classes.

**Lambda expressions** will encourage you to adopt the style of **behavior parameterization**.

### 1.2. lambda expression composition

A **lambda expression** is composed of **parameters**, **an arrow**, and **a body**.

```txt
(Apple a1, Apple2)  ->         a1.getWeight().compareTo(a2.getWeight())
-----------------------------------------------------------------------
parameters          arrow       lambda body
```

- **A list of parameters**— In this case it mirrors the parameters of the compare method of a Comparator —two Apples.
- **An arrow**— The arrow `->` separates **the list of parameters** from **the body of the lambda**.
- **The body of the lambda**— Compare two Apples using their weights. The expression is considered the lambda’s return value.

The basic syntax of a lambda is:

```txt
(parameters) -> expression
(parameters) -> { statements; }
```

注意：下面两个不合法：

- （1） `(Integer i) -> return "Alan" + i;`，因为`return "Alan" + i;`不是expression，而是statement，正确的写法应该加上`{}`，即`{return "Alan" + i;}`
- （2） `(String s) -> {"Iron Man";}`也不对，是因为“Iron Man” is an expression, not a statement。正确的写法是：`(String s) -> "Iron Man"`，又或者是`(String s) -> {return "Iron Man";}`。



