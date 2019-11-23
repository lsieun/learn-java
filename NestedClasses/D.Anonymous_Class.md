# Anonymous Classes

<!-- TOC -->

- [1. Anonymous Class Declaration](#1-anonymous-class-declaration)
  - [1.1. Extend a Class](#11-extend-a-class)
  - [1.2. Implement an Interface](#12-implement-an-interface)
- [2. Anonymous Class Properties](#2-anonymous-class-properties)
  - [2.1. Constructor](#21-constructor)
  - [2.2. Static Members](#22-static-members)
  - [2.3. Scope of Variables](#23-scope-of-variables)
- [3. Anonymous Class Use Cases](#3-anonymous-class-use-cases)
  - [3.1. Class Hierarchy and Encapsulation](#31-class-hierarchy-and-encapsulation)
  - [3.2. Cleaner Project Structure](#32-cleaner-project-structure)
  - [3.3. UI Event Listeners](#33-ui-event-listeners)

<!-- /TOC -->

Anonymous classes can be used to define an implementation of an interface or an abstract class without having to create a reusable implementation.

Let's list a few points to remember about anonymous classes:

- They cannot have access modifiers in their declaration
- They have access to both static and non-static members in the enclosing context
- They can only define instance members
- They're the only type of nested classes that cannot define constructors or extend/implement other classes or interfaces

## 1. Anonymous Class Declaration

**Anonymous classes are inner classes with no name**. Since they have no name, we can't use them in order to create instances of anonymous classes. As a result, we have to declare and instantiate anonymous classes in a single expression at the point of use.<sub>注：由于anonymous classes没有名字，declare和instantiate需要在一个expression内完成。</sub>

We may either **extend an existing class** or **implement an interface**.

### 1.1. Extend a Class

When we instantiate an anonymous class from an existent one, we use the following syntax:

![](images/AnonymousClass-InstantiateFromClass.png)

In the parentheses, we specify the parameters that are required by the constructor of the class that we are extending:

```java
new Book("Design Patterns") {
    @Override
    public String description() {
        return "Famous GoF book.";
    }
}
```

Naturally, if the parent class constructor accepts no arguments, we should leave the parentheses empty.

### 1.2. Implement an Interface

We may instantiate an anonymous class from an interface as well:

![](images/AnonymousClass-InstantiateFromInterface.png)

Obviously, Java's interfaces have no constructors, so the parentheses always remain empty. This is the only way we should do it to implement the interface's methods:

```java
new Runnable() {
    @Override
    public void run() {
        ...
    }
}
```

## 2. Anonymous Class Properties

There are **certain particularities** in using anonymous classes with respect to usual top-level classes. Here we briefly touch the most practical issues.

### 2.1. Constructor

The syntax of anonymous classes does not allow us to make them implement multiple interfaces. During construction, **there might exist exactly one instance of an anonymous class**. Therefore, they can never be abstract. Since they have no name, we can't extend them. For the same reason, anonymous classes cannot have explicitly declared constructors.

这段理解：anonymous classes

- 从“修饰符”角度：不能带有abstract，不能是抽象类，只能是具体的类
- 从“接口”角度：不能继承多个接口
- 从“构造器”角度：不能显示声明，因为anonymous classes没有名字

- 从“实例化”角度：只能有一个实例

In fact, **the absence of a constructor** doesn't represent any problem for us for the following reasons:

- we create anonymous class instances at the same moment as we declare them
- from anonymous class instances, we can access local variables and enclosing class's members

### 2.2. Static Members

Anonymous classes cannot have any static members except for those that are constant.

For example, this won't compile:

```java
new Runnable() {
    static final int x = 0;
    static int y = 0; // compilation error!

    @Override
    public void run() {...}
};
```

Instead, we'll get the following error:

```txt
The field y cannot be declared static in a non-static inner type, unless initialized with a constant expression
```

### 2.3. Scope of Variables

Anonymous classes capture **local variables** that are in the scope of the block in which we have declared the class:

```java
int count = 1;
Runnable action = new Runnable() {
    @Override
    public void run() {
        System.out.println("Runnable with captured variables: " + count);
    }
};
```

**Note that in order to be able to use local variables, they must be effectively final**. Since JDK 8, it is not required anymore that we declare variables with the keyword `final`. Nevertheless, those variables must be `final`. Otherwise, we get a compilation error:

```txt
[ERROR] local variables referenced from an inner class must be final or effectively final
```

## 3. Anonymous Class Use Cases

There might be a big variety of applications of anonymous classes. Let's explore some possible use cases.

### 3.1. Class Hierarchy and Encapsulation

We should use **inner classes** in **general use cases** and **anonymous ones** in **very specific ones** in order to achieve a cleaner hierarchy of classes in our application. When using inner classes, we may achieve a finer encapsulation of the enclosing class's data. If we define the inner class functionality in a top-level class, then the enclosing class should have public or package visibility of some of its members. Naturally, there are situations when it is not very appreciated or even accepted.

### 3.2. Cleaner Project Structure

We usually use anonymous classes when we have to modify on the fly the implementation of methods of some classes. In this case, we can avoid adding new `*.java` files to the project in order to define top-level classes. This is especially true if that top-level class would be used just one time.

### 3.3. UI Event Listeners

In applications with a graphical interface, the most common use case of anonymous classes is to create various event listeners. For example, in the following snippet:

```java
button.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        ...
    }
}
```

we create an instance of an anonymous class that implements interface `ActionListener`. Its `actionPerformed` method gets triggered when a user clicks the button.
