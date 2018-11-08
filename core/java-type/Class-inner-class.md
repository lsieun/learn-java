# Java Inner Class

Java inner class is defined inside the body of another class. 


**Java inner class** can be declared `private`, `public`, `protected`, or with `default` access whereas **an outer class** can have only `public` or `default` access.

> 访问修饰符  
> inner class: `private`, `public`, `protected`, `default`  
> outer class: `public`, `default`

Java Nested classes are divided into two types:

- static nested class
- non-static nested class

<!-- TOC -->

- [Java Inner Class](#java-inner-class)
    - [1. static nested class](#1-static-nested-class)
    - [2. non-static nested class](#2-non-static-nested-class)
        - [2.1 java inner class](#21-java-inner-class)
        - [2.2 local inner class](#22-local-inner-class)
        - [2.3 anonymous inner class](#23-anonymous-inner-class)
    - [3. Benefits of Java Inner Class](#3-benefits-of-java-inner-class)

<!-- /TOC -->

## 1. static nested class

If the nested class is `static`, then it’s called **static nested class**. 

> 如何定义

**Static nested classes** can access **only static members** of **the outer class**. 

> 对outer class的访问情况

**Static nested class** is same as **any other top-level class** and is nested for only packaging convenience.

> 与top-level class并没有太大不同。

Static class object can be created with following statement.

> 创建对象

```java
OuterClass.StaticNestedClass nestedObject = new OuterClass.StaticNestedClass();
```

## 2. non-static nested class

Any **non-static nested class** is known as **inner class** in java. 

There are two special kinds of java inner classes:

- local inner class
- anonymous inner class

### 2.1 java inner class

**Java inner class** is associated with the object of the class and they can access all the variables and methods of **the outer class**.

> 对outer class的访问情况

Since inner classes are associated with instance, we can’t have any static variables in them.

> 自身存在的约束

Object of java inner class are part of the outer class object and to create an instance of inner class, we first need to create instance of outer class. Java inner class can be instantiated like this:

> 创建对象

```java
OuterClass outerObject = new OuterClass();
OuterClass.InnerClass innerObject = outerObject.new InnerClass();
```

### 2.2 local inner class

If a class is defined in a **method body**, it’s known as **local inner class**.

> 如何定义

Since local inner class is not associated with Object, we can’t use `private`, `public` or `protected` access modifiers with it. The only allowed modifiers are `abstract` or `final`.

> 对访问修饰符的使用情况

A local inner class can access **all the members of the enclosing class** and **local final variables** in the scope it’s defined.

> 对外界的访问情况

Local inner class can be defined as:

```java

public void print() {
        //local inner class inside the method
        class Logger {
            String name;
        }
        //instantiate local inner class in the method to use
        Logger logger = new Logger();
}
```

### 2.3 anonymous inner class

A local inner class without name is known as anonymous inner class. An anonymous class is defined and instantiated in a single statement.

> 如何定义

Anonymous inner class always **extend a class** or **implement an interface**. Since an anonymous class has no name, it is not possible to define a constructor for an anonymous class.

> 通常都是继承父类，或者实现接口。

Anonymous inner classes are accessible only at the point where it is defined.
It’s a bit hard to define how to create anonymous inner class, we will see it’s real time usage in test program.

## 3. Benefits of Java Inner Class

- If a class is useful to only one class, it makes sense to keep it nested and together. It helps in packaging of the classes.
- Java inner classes implements encapsulation. Note that **inner classes** can access **outer class** `private` members and at the same time we can hide inner class from outer world.
- Keeping the small class within top-level classes places the code closer to where it is used and makes code more readable and maintainable.
