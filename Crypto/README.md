

## Design Principles

Over the years, researchers have devised dozens of different ways to generate digital signatures. The algorithms they developed differ in speed, strength, and flexibility. If you look into the **Java Security API**, however, you won't find classes that implement these algorithms directly. To understand why not, you must first understand a bit about **the principles** that guided the development of the API.

The **Java Cryptography Architecture** was designed around **two guiding principles**. 

**First**, the architecture should support **algorithm independence and extensibility**. A developer must be able to write applications without tying them too closely to a particular algorithm(体现algorithm independence). In addition, as new algorithms are developed they must be easily integrated with existing algorithms(体现algorithm extensibility).

**Second**, the architecture should support **implementation independence and interoperability**. A developer must be able to write applications without tying them to a particular vender's implementation of an algorithm. In addition, implementations of an algorithm provided by different vendors must interoperate.

## Providers and Engines

If the Java Security API had been designed in the same way as many class libraries, the library would contain classes that implement specific algorithms. For example, it might contain classes named `DSA` and `RSA` (for digital signatures) and `MD5` and `SHA` (for message digests). The application developer would write the application around the available classes. If the developer later decided to use another algorithm (or another company's implementation of an algorithm), she would have to modify the source code.

> Java Security API的设计方式，与寻常的设计方式，是不同的。

The application's user would face a much larger problem though. In order to use the application, he would need access to the library the developer used. Unfortunately, the library may not be exportable (many countries restrict the exportation of cryptography), the library may not be usable (some countries restrict the use of cryptography), and/or the library may not perform as well as another library the user has access to. **Worse, the user is unlikely to have access to the source code**.

> 出于某些国家的限制问题，有些算法在该国家之外无法获取到。

For reasons like these, the **Java Cryptography Architecture** was built upon the concept of **providers** and **engines**.

A **Cryptography package provider** (or provider) is a package or library that provides **an implementation** of some subset of the Java Security API. The **Java Security API** comes with **a default provider**. Other companies and individuals can implement their own versions of popular (or newly developed) cryptographic algorithms and package them as providers.

An **engine** provides **a uniform interface** to **providers** (including Sun's default provider). The `MessageDigest` class is an engine that generates message digest instances. The same is true of the `Signature` class. These classes are not meant to be instantiated directly. Instead, the **engines** return **instances of classes** provided by **the installed providers**.

This approach frees developers of many algorithm-specific details, and the allows users to replace the standard provider with an alternative that meets their needs.

Here's a brief glimpse of what the code looks like:

```java
// The getInstance() method is a static method of the
//  Signature class.  It returns an instance of the
//  installed class that implements the SHA/DSA
//  digital signature algorithm.
Signature signature = Signature.getInstance("SHA/DSA");
```

## JCA Vs JCE

The basic difference between **JCA** and **JCE** is that `JCE` is an extension of `JCA`, not a replacement. 

The **JCA** includes classes like `MessageDigest`, `SecureRandom`, `KeyFactory`, `Signature` and `KeyStore`. 

**JCE** add some more classes of cryptography like `Cipher`, `Mac` and `KeyGeneration`. 

The distinction between **JCA** and **JCE** has largely faded as the JCE has been provided with the standard runtime for some time now.

**JCA/JCE** is designed to separate **cryptographic implementation** from **abstraction**. It is a **provider based architecture** where you can plug in a provider of your choice, for instance `BouncyCastle`, that has more cryptographic algorithm support than what is provided by the providers contained within the standard Java runtime.



## Reference


- [Signed and delivered: An introduction to security and authentication](https://www.javaworld.com/article/2076821/java-se/signed-and-delivered--an-introduction-to-security-and-authentication.html)

