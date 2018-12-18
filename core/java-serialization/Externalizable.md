# Externalizable interface

## Introduction

In contrast to `Serializable` interface, `Externalizable` delegates to the class the responsibility of how it should be
serialized and deserialized. It has only two methods and here is its declaration from the Java standard library:

> `Serializable`只是标明了Class具有序列化的能力；但是，对于如何进行序列化，是交给其它类（`ObjectOutputStream`/ `ObjectInputStream`）来进行处理的。  
> `Externalizable`，由于继承自`Serializable`，因而也标明Class具有序列化的能力；另外，它将如何进行序列化的细节，掌握在了“自己的手里”。

```java
public interface Externalizable extends java.io.Serializable {
    void writeExternal(ObjectOutput out) throws IOException;
    void readExternal(ObjectInput in) throws IOException, ClassNotFoundException;
}
```

In turn, every class which implements `Externalizable` interface should provide the implementation of these two methods.

## Example

Let us take a look on the example:

```java
public class ExternalizableExample implements Externalizable {
    private String str;
    private int number;
    private SerializableExample obj;

    @Override
    public void readExternal(final ObjectInput in) throws IOException, ClassNotFoundException {
        setStr(in.readUTF());
        setNumber(in.readInt());
        setObj(( SerializableExample )in.readObject());
    }

    @Override
    public void writeExternal(final ObjectOutput out) throws IOException {
        out.writeUTF(getStr());
        out.writeInt(getNumber());
        out.writeObject(getObj());
    }
}

```

Similarly to the classes implementing `Serializable`, the classes implementing `Externalizable` could be stored and retrieved using, for example, `ObjectOutputStream`/`ObjectInputStream`:

```java
final Path storage = new File( "extobject.ser" ).toPath();
final ExternalizableExample instance = new ExternalizableExample();
instance.setStr( "Sample String" );
instance.setNumber( 10 );
instance.setObj( new SerializableExample() );

try( final ObjectOutputStream out = new ObjectOutputStream( Files.newOutputStream( storage ) ) ) {
    out.writeObject( instance );
}

try( final ObjectInputStream in = new ObjectInputStream( Files.newInputStream( storage ) ) ) {
    final ExternalizableExample obj = ( ExternalizableExample )in.readObject();
    // Some implementation here
}
```

## Wrap Up

The `Externalizable` interface allows **a fine-grained serialization/deserialization customization** in the cases when the simpler approach with `Serializable` interface does not work well.


