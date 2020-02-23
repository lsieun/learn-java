# URL

<!-- TOC -->

- [1. Creating New URLs](#1-creating-new-urls)
- [2. Splitting a URL into Pieces](#2-splitting-a-url-into-pieces)
- [3. Equality and Comparison](#3-equality-and-comparison)
- [4. Conversion](#4-conversion)
  - [4.1. toURI](#41-touri)

<!-- /TOC -->

The `java.net.URL` class is an abstraction of a Uniform Resource Locator such as `http://www.lolcats.com/` or `ftp://ftp.redhat.com/pub/`. It extends `java.lang.Object`, and it is a final class that cannot be subclassed. Rather than relying on inheritance to configure instances for different kinds of URLs, it uses the strategy design pattern. **Protocol handlers** are the strategies, and the `URL` class itself forms the context through which the different strategies are selected.

Although storing a `URL` as a string would be trivial, it is helpful to think of URLs as objects with fields that include the **scheme** (a.k.a. the protocol), **hostname**, **port**, **path**, **query string**, and **fragment identifier** (a.k.a. the ref), each of which may be set independently. Indeed, this is almost exactly how the `java.net.URL` class is organized, though the details vary a little between different versions of Java.

URLs are immutable. After a URL object has been constructed, its fields do not change. This has the side effect of making them thread safe.

## 1. Creating New URLs

We can construct instances of `java.net.URL`. The constructors differ in the information they require:

```java
public URL(String url) throws MalformedURLException
public URL(String protocol, String hostname, String file) throws MalformedURLException
public URL(String protocol, String host, int port, String file) throws MalformedURLException
public URL(URL base, String relative) throws MalformedURLException
```

Which constructor you use depends on the information you have and the form it’s in. All these constructors throw a `MalformedURLException` if you try to create a `URL` for **an unsupported protocol** or if the URL is **syntactically incorrect**.

Exactly which protocols are supported is implementation dependent. The only protocols that have been available in all virtual machines are `http` and `file`, and the latter is notoriously flaky. Today, Java also supports the `https`, `jar`, and `ftp` protocols. Some virtual machines support `mailto` and `gopher` as well as some custom protocols like `doc`, `netdoc`, `systemresource`, and `verbatim` used internally by Java.

If the protocol you need isn’t supported by a particular VM, you may be able to install a **protocol handler** for that scheme to enable the URL class to speak that protocol. In practice, this is way more trouble than it’s worth. You’re better off using a library that exposes a custom API just for that protocol.

Other than verifying that it recognizes the URL scheme, Java does not check the correctness of the URLs it constructs. The programmer is responsible for making sure that URLs created are valid. For instance, Java does not check that the hostname in an HTTP URL does not contain spaces or that the query string is x-www-form-URL-encoded. It does not check that a `mailto` URL actually contains an email address. You can create URLs for hosts that don’t exist and for hosts that do exist but that you won’t be allowed to connect to.

## 2. Splitting a URL into Pieces

URLs are composed of five pieces:

- The **scheme**, also known as the protocol
- The **authority**
- The **path**
- The **fragment identifier**, also known as the section or ref
- The **query string**

For example, in the URL `http://www.ibiblio.org/javafaq/books/jnp/index.html?isbn=1565922069#toc`, the **scheme** is `http`, the **authority** is `www.ibiblio.org`, the **path** is `/javafaq/books/jnp/index.html`, the **fragment identifier** is `toc`, and the **query string** is `isbn=1565922069`. However, not all URLs have all these pieces. For instance, the URL `http://www.faqs.org/rfcs/rfc3986.html` has a **scheme**, an **authority**, and a **path**, but no **fragment identifier** or **query string**.

The authority may further be divided into the **user info**, the **host**, and the **port**. For example, in the URL `http://admin@www.blackstar.com:8080/`, the authority is `admin@www.blackstar.com:8080`. This has the user info `admin`, the host `www.blackstar.com`, and the port `8080`.

Read-only access to these parts of a URL is provided by nine public methods: `getFile()`, `getHost()`, `getPort()`, `getProtocol()`, `getRef()`, `getQuery()`, `getPath()`, `getUserInfo()`, and `getAuthority()`.

## 3. Equality and Comparison

The `URL` class contains the usual `equals()` and `hashCode()` methods. These behave almost as you’d expect. Two URLs are considered equal if and only if both URL s point to the same resource on the same **host**, **port**, and **path**, with the same **fragment identifier** and **query string**.

However there is one surprise here. The `equals()` method actually tries to resolve the host with DNS so that, for example, it can tell that `http://www.ibiblio.org/` and `http://ibiblio.org/` are the same.

This means that `equals()` on a `URL` is potentially a blocking I/O operation! For this reason, you should avoid storing `URL`s in data structure that depend on `equals()` such as `java.util.HashMap`. Prefer `java.net.URI` for this, and convert back and forth from `URI`s to `URL`s when necessary.

On the other hand, `equals()` does not go so far as to actually compare the resources identified by two URLs. For example, `http://www.oreilly.com/` is not equal to `http://www.oreilly.com/index.html`; and `http://www.oreilly.com:80` is not equal to `http://www.oreilly.com/`.

## 4. Conversion

### 4.1. toURI

The `toURI()` method converts a `URL` object to an equivalent `URI` object:

```java
public URI toURI() throws URISyntaxException
```
