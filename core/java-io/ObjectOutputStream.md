# ObjectOutputStream

<!-- TOC -->

- [1. Intro](#1-intro)
- [2. 第一批方法](#2-%E7%AC%AC%E4%B8%80%E6%89%B9%E6%96%B9%E6%B3%95)
- [3. 第二批方法](#3-%E7%AC%AC%E4%BA%8C%E6%89%B9%E6%96%B9%E6%B3%95)

<!-- /TOC -->

## 1. Intro

The `ObjectOutputStream` serializes **objects**, **arrays**, and other values to a stream.

> 作者有话说：ObjectOutputStream是对objects和arrays进行序列化。

Note that only objects that implement the `Serializable` or `Externalizable` interface can be serialized.

> 作者有话说：只有实现了Serializable或Externalizable接口的类才能进行序列化或反序列化。

## 2. 第一批方法

The `writeObject()` method serializes an **object** or **array**, and **various other methods** write **primitive data values** to the stream.

```java
public final void writeObject(Object obj)

public void writeBoolean(boolean val)
public void writeByte(int val)
public void writeShort(int val)
public void writeChar(int val)
public void writeInt(int val)
public void writeLong(long val)
public void writeFloat(float val)
public void writeDouble(double val)

public void writeBytes(String str)
public void writeChars(String str)
public void writeUTF(String str)
```

## 3. 第二批方法

A class that wants to customize the way instances are serialized should declare a private `writeObject(ObjectOutputStream)` method.

This method is invoked when an object is being serialized and can use several additional methods of `ObjectOutputStream`.

- `defaultWriteObject()` performs the same serialization that would happen if no `writeObject()` method existed. An object can call this method to serialize itself and then use other methods of `ObjectOutputStream` to write additional data to the serialization stream. The class must define a matching `readObject()` method to read that additional data, of course.

When working with multiple versions or implementations of a class, you may have to serialize a set of fields that do not precisely match the fields of your class. In this case, give your class a static field named `serialPersistentFields` whose value is an array of `ObjectStreamField` objects that describe the fields to be serialized. 

In your `writeObject()` method, call `putFields()` to obtain an `ObjectOutputStream.PutField` object. Store field names and values into
this object, and then call `writeFields()` to write them out to the serialization stream. See `ObjectStreamField` and `ObjectOutputStream.PutField` for further details.

The remaining methods of `ObjectOutputStream` are miscellaneous stream-manipulation methods and protected methods for use by subclasses that want to customize its serialization behavior.
