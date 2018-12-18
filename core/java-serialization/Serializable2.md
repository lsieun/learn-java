# More about Serializable interface

## Special methods

In the previous section we mentioned that the `Serializable` interface does not provide a lot of control over what should be serialized and how. In fact, **it is not completely true** (at least when `ObjectOutputStream`/`ObjectInputStream` are used). **There are some special methods** which any serializable class can implement in order to control the default serialization and deserialization.

```java
private void writeObject(ObjectOutputStream out) throws IOException;
```

This method is responsible for writing the state of the object for its particular class so that the corresponding `readObject` method can restore it (the default mechanism for saving the Object’s fields can be invoked by calling `out.defaultWriteObject`).

```java
private void readObject(ObjectInputStream in) throws IOException,ClassNotFoundException;
```

This method is responsible for reading from the stream and restoring the state of the object (the default mechanism for restoring
the Object’s fields can be invoked by calling `in.defaultReadObject`).

```java
private void readObjectNoData() throws ObjectStreamException;
```

This method is responsible for initializing the state of the object in the case when the serialization stream does not list the given class as a superclass of the object being deserialized.

```java
Object writeReplace() throws ObjectStreamException;
```

This method is used when serializable classes need to designate an alternative object to be used when writing an object to the stream.

```java
Object readResolve() throws ObjectStreamException;
```

And lastly, this method is used when serializable classes need to designate a replacement when an instance of it is read from the
stream.

The default serialization mechanism (using `Serializable` interface) could get really cumbersome in Java once you know the
intrinsic implementation details and those special methods to use. More code you are writing to support serialization, more likely
more bugs and vulnerabilities will show off.

## Serialization Proxy

However, there is a way to reduce those risks by employing quite simple pattern named **Serialization Proxy**, which is based on utilizing `writeReplace` and `readResolve` methods. The basic idea of this pattern is to introduce **dedicated companion class for serialization** (usually as `private` `static` inner class), which complements the class required to be serialized. Let us take a look on this example:

```java
public class SerializationProxyExample implements Serializable {

    private static final long serialVersionUID = 6163321482548364831L;

    private String str;
    private int number;

    public SerializationProxyExample( final String str, final int number) {
        this.setStr(str);
        this.setNumber(number);
    }

    private void readObject(ObjectInputStream stream) throws InvalidObjectException {
        throw new InvalidObjectException( "Serialization Proxy is expected" );
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    // Setters and getters here
}
```

When the instances of this class are being serialized, the class `SerializationProxyExample` implementation provides the replacement object (instance of the `SerializationProxy` class) instead. It means that instances of the `SerializationProxyExample` class will never be serialized (and deserialized) directly. It also explains why the `readObject` method raises an exception in case a deserialization attempt somehow happens. Now, let us take a look on the companion `SerializationProxy` class:

```java
private static class SerializationProxy implements Serializable {
    private static final long serialVersionUID = 8368440585226546959L;

    private String str;
    private int number;

    public SerializationProxy( final SerializationProxyExample instance ) {
        this.str = instance.getStr();
        this.number = instance.getNumber();
    }

    private Object readResolve() {
        return new SerializationProxyExample(str, number); // Uses public constructor
    }
}
```

In our somewhat simplified case, the `SerializationProxy` class just duplicates all the fields of the `SerializationProxyExample` (but it could be much complicated than that). Consequently, when the instances of this class are being deserialized, the `readResolve` method is called and `SerializationProxy` provides the replacement as well, this time in a shape of `SerializationProxyExample` instance. As such, the `SerializationProxy` class serves as a serialization proxy for `SerializationProxyExample` class.
