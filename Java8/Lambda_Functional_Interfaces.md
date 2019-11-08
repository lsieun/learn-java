# Functional Interfaces

**Lambda expressions** are statically typed, so let’s investigate the types of lambda expressions themselves: these types are called **functional interfaces**.

这段的重点在于：理解Lambda expression和Functional Interface之间的关系？

- （1） Lambda expression的目的是将behavior作为“参数”进行传递（behavior parameterization）。换句话说，Lambda Expression可以作为方法的“参数”。但我们知道，方法的参数都是有一个类型的（In Java, all method parameters have types），那么Lambda Expression如果是作为“参数”，那么它的类型是什么呢？（So what’s the type of a lambda expression?）
- （2） Java8告诉我们，Lambda Expression是一个静态类型（statically typed），这个静态类型是根据使用场景（Method Invokation Context）来决定的。同时，Java8给了Lambda Expression的类型一个专用的名词，称之为Functional Interface。

A functional interface is an interface with a single abstract method that is used as the type of a lambda expression.










