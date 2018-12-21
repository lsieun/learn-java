# Serialization

URL:

- http://en.wikipedia.org/wiki/Serialization

**Serialization** is the process of translating Java objects into a format that can be used to store and be reconstructed later in the same (or another) environment. Serialization not only allows saving and loading Java objects to/from the persistent storage, but is also a very important component of modern distributed systems communication.

**Serialization** is not easy, but **effective serialization** is even harder. Besides the Java standard library, there are many serialization techniques and frameworks available: some of them are using compact binary representation(有些注重压缩后的大小), others put the readability on the first place(有些注重可读性).

## Java standard library

Although we are going to mention many alternatives along the way, our attention will be concentrated on the ones from **Java standard library** (and latest specifications): 

- `Serializable`,
- `Externalizable`,
- Java Architecture for XML Binding (JAXB, [JSR-222](https://jcp.org/en/jsr/detail?id=222)) and
- Java API for JSON Processing (JSON-P, [JSR-353](https://jcp.org/en/jsr/detail?id=353)).

## Cost of serialization

It is very important to understand that though serialization / deserialization looks simple in Java, it is not free and depending on the data model and data access patterns may **consume quite a lot of network bandwidth, memory and CPU resources**. More to that, nevertheless Java has some kind of versioning support for the serializable classes (using **serial version UID** as we have seen in the section `Serializable` interface), it does make the development process much harder as developers are on their own to figure out how to manage data model evolution.

To add another point, **Java serialization does not work well outside of JVM world**. It is a significant constraint for the modern distributed applications which are built using multiple programming languages and runtimes.

That explains why **many alternative serialization frameworks and solutions** have emerged and became very popular choice in the
Java ecosystem.


## Snippet

Probably anyone who has ever worked with serialization of objects, be that in Java or any other language, knows that it should be avoided whenever possible. Just like the first rule of distribution is "Do not distribute!", the first rule of serialization should be "Do not serialize!". However, in many cases, especially in distributed environments, serialization cannot be avoided and therefore must be significantly optimized to achieve any kind of reasonable throughput.


