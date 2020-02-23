# interface

接口内定义的成员变量

接口里的变量是final static类型的

## What is an interface in Java?

An interface is a collection of methods that must be implemented by the implementing class.

An interface defines a contract regarding what a class must do, without saying anything about how the class will do it.

Interface can contain declaration of methods and variables.

implementing class must define all the methods declared in the interface

If a class implements an interface and does not implement all the methods then class itself must be declared as abstract

Variables in interface automatically become `static` and `final` variable of the implementing class

Members of interface are implicitly `public`, so need not be declared as `public`.

An interface must be implemented in class.

## Marker interface

In Java language programming, interfaces with no methods are known as marker interfaces. Marker interfaces are `Serializable`, `Cloneable`, `SingleThreadModel`, `EventListener`. Marker Interfaces are implemented by the classes or their super classes in order to add some functionality.

e.g. Suppose you want to persist (save) the state of an object then you have to implement the `Serializable` interface, otherwise the compiler will throw an error.

Suppose the interface `Cloneable` is neither implemented by a class named `Myclass` nor it's any super class, then a call to the method `clone()` on `Myclass`'s object will give an error. This means, to add this functionality one should implement the `Cloneable` interface. While the `Cloneable` is an empty interface but it provides an important functionality.

Generally, they are used to give additional information about the behaviour of a class.It is just used to "mark" Java classes which support a certain capability.

Examples:

- `java.lang.Clonable`
- `java.io.Serializable`
- `java.util.RandomAccess`
- `java.util.EventListner`
- `javax.ejb.EnterpriseBean`
- `java.rmi.Remote`
- `javax.servlet.SingleThreadModel`

