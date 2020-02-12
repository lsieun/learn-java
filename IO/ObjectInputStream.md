# ObjectInputStream

<!-- TOC -->

- [1. Intro](#1-intro)
- [2. 第一批方法](#2-%E7%AC%AC%E4%B8%80%E6%89%B9%E6%96%B9%E6%B3%95)
- [3. 第二批方法](#3-%E7%AC%AC%E4%BA%8C%E6%89%B9%E6%96%B9%E6%B3%95)

<!-- /TOC -->

## 1. Intro

`ObjectInputStream` deserializes **objects**, **arrays**, and other values from a stream that was previously created with an `ObjectOutputStream`.

> 作者有话说：ObjectInputStream是对objects和arrays进行反序列化，也就是生成对象。


Note that only objects that implement the `Serializable` or `Externalizable` interface can be serialized and deserialized.

> 作者有话说：只有实现了Serializable或Externalizable接口的类才能进行序列化或反序列化。

## 2. 第一批方法

The `readObject()` method deserializes **objects** and **arrays** (which should then be cast to the appropriate type); **various other methods** read **primitive data values** from the stream.

> 作者有话说：这里介绍了一些方法。不过，我觉得如果只记忆一个方法，那么就是readObject()。

```java
public final Object readObject()

public boolean readBoolean()
public byte readByte()
public short readShort()
public char readChar()
public int readInt()
public long readLong()
public float readFloat()
public double readDouble()

public int readUnsignedByte()
public int readUnsignedShort()
```

## 3. 第二批方法

A class may implement its own private `readObject(ObjectInputStream)` method to customize the way it is deserialized.

> 作者有话说：这里是说，如果你想自定义反序列化的过程，可以在序列化的类里添加一个readObject(ObjectInputStream)的私有方法。  
> 下面讲的更细节的东西，我看不下去了。

If you define such a method, there are several `ObjectInputStream` methods you can use to help you deserialize the object.

- `defaultReadObject()` is the easiest. It reads the content of the object just as an `ObjectInputStream` would normally do.

If you wrote additional data before or after the default object contents, you should read that data before or after calling `defaultReadObject()`.

When working with multiple versions or implementations of a class, you may have to deserialize a set of fields that do not match the fields of your class. In this case, give your class a static field named `serialPersistentFields` whose value is an array of `ObjectStreamField` objects that describe the fields to be deserialized. If you do this, your `readObject()` method can call `readFields()` to read the specified fields from the stream and return them in a `ObjectInputStream.GetField` object. See `ObjectStreamField` and `ObjectInputStream.GetField` for more details.

Finally, you can call `registerValidation()` from a custom `readObject()` method. This method registers an `ObjectInputValidation` object (typically the object being deserialized) to be notified when a complete tree of objects has been deserialized, and the original call to the `readObject()` method of the `ObjectInputStream` is about to return to its caller.

The remaining methods include miscellaneous stream-manipulation methods and several protected methods for use by subclasses that want to customize the deserialization behavior of `ObjectInputStream`.
