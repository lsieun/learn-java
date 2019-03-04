# `Serializable` interface

## Introduction

Arguably(可以认为；可以说), the easiest way in Java to mark the class as available for serialization is by implementing the `java.io.Serializable` interface. For example:

```java
public class SerializableExample implements Serializable {
}
```

The serialization runtime associates with each serializable class a special version number, called **a serial version UID**, which is used during **deserialization** (the process opposite to **serialization**) to make sure that the loaded classes for the serialized object are compatible. In case the compatibility has been compromised, the `InvalidClassException` will be raised.

A serializable class may introduce its own **serial version UID** explicitly by declaring a field with name `serialVersionUID` that must be `static`, `final`, and of type `long`. For example:

```java
public class SerializableExample implements Serializable {
    private static final long serialVersionUID = 8894f47504319602864L;
}
```

However, if a serializable class does not explicitly declare a `serialVersionUID` field, then the serialization runtime will
generate a default `serialVersionUID` field for that class. It is worth to know that **it is strongly recommended** by **all classes implementing `Serializable` to explicitly declare the `serialVersionUID` field**, because the default `serialVersionUID` generation heavily relies on intrinsic class details and may vary depending on Java compiler implementation and its version.
As such, to guarantee a consistent behavior, a serializable class must always declare an explicit `serialVersionUID` field.

## Example

Once the class becomes serializable (implements `Serializable` and declares `serialVersionUID`), it could be stored and retrieved using, for example, `ObjectOutputStream`/ `ObjectInputStream`:

```java
final Path storage = new File("object.ser").toPath();
try( final ObjectOutputStream out = new ObjectOutputStream( Files.newOutputStream( storage ) ) ) {
    out.writeObject( new SerializableExample() );
}
```

Once stored, it could be retrieved in a similar way, for example:

```java
try( final ObjectInputStream in = new ObjectInputStream( Files.newInputStream( storage ) ) ) {
    final SerializableExample instance = ( SerializableExample )in.readObject();
    // Some implementation here
}
```

## Wrap Up

As we can see, the `Serializable` interface does not provide a lot of control over what should be serialized and how (with
exception of `transient` keyword which **marks the fields as non-serializable**). Moreover, it **limits the flexibility** of **changing the internal class representation**(吾不解) as it could break the serialization / deserialization process. That is why another interface, `Externalizable`, has been introduced.

