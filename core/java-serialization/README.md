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

