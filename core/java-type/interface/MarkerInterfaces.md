# Marker Interfaces

**Marker interfaces** are a special kind of interfaces which have no methods or other nested constructs defined. 

One example is the `Cloneable` interface. Here is how it is defined in the Java library:

```java
public interface Cloneable {
}
```

**Marker interfaces** are not contracts **per se** but somewhat **useful technique to “attach” or “tie” some particular trait to the class**. For
example, with respect to `Cloneable`, the class is marked as being available for cloning however the way it should or could be done is not a part of the interface.

> 注解：  
> per se的意思是“本身；本质上”  
> 要理解“Marker interfaces are not contracts per se”这句话，可以参考下面这段话：  
> In object-oriented programming, **the concept of interfaces** forms the basics of **contract-driven** (or contract-based) development. **In a nutshell**(简言之；简而言之；总而言之), interfaces define the set of methods (contract) and every class which claims to support this particular interface must provide the implementation of those methods: a pretty simple, but powerful idea.

Another very well-known and widely used example of marker interface is `Serializable`:

```java
public interface Serializable {
}
```

This interface marks the class as being available for serialization and deserialization, and again, it does not specify the way it could or should be done.

> Serializable接口，只是标明了class可以进行serialization和deserialization的能力；  
> 但是，并没有指明进行serialization和deserialization的方式。  
> 哈哈，我联想到一个类比：条条大路通罗马，却不管你是走路，还是坐车，只要到达就好。  
> 2018-12-18  
> 我此刻理解如下：  
> interface分为三个层次：  
> 第1层，抽象层，赋予能力，就像marker interface一样；  
> 第2层，Standard层，指定有哪些方法要进行实现；  
> 第3层，实现层，每个不同的subclass有自己的实现方法。  

The marker interfaces have their place in object-oriented design, although they do not satisfy the main purpose of interface to be a contract.

> 这段话说明两点：  
> （1） marker interface自有其存在的价值；  
> （2）但是，marker interface并不是一个contract。  

