# Cookies

<!-- TOC -->

- [1. Intro](#1-intro)
  - [1.1. Why cookie](#11-why-cookie)
  - [1.2. CookieHandler](#12-cookiehandler)
  - [1.3. CookieManager](#13-cookiemanager)
  - [1.4. CookieStore](#14-cookiestore)
- [2. CookieManager](#2-cookiemanager)
  - [2.1. Enable CookieManager](#21-enable-cookiemanager)
  - [2.2. CookiePolicy](#22-cookiepolicy)
- [3. CookieStore](#3-cookiestore)
  - [3.1. Get CookieStore](#31-get-cookiestore)
  - [3.2. Custom CookieStore](#32-custom-cookiestore)
  - [3.3. HttpCookie](#33-httpcookie)

<!-- /TOC -->

## 1. Intro

### 1.1. Why cookie

Whenever a client sends an HTTP request to a server and receives a response for it, the server forgets about this client. The next time the client requests again, it will be seen as a totally new client.

However, cookies, as we know, make it possible to establish a session between the client and server such that the server can remember the client across multiple request response pairs.

### 1.2. CookieHandler

Consider this scenario; we are communicating with the server at `http://baeldung.com`, or any other URL that uses HTTP protocol, the `URL` object will be using an engine called the **HTTP protocol handler**.

This **HTTP protocol handler** checks if there is a default `CookieHandler` instance in the system. If there is, it invokes it to take charge of state management.

So the `CookieHandler` class has a purpose of providing a callback mechanism for the benefit of the HTTP protocol handler.

`CookieHandler` is an abstract class. It has a static `getDefault()` method that can be called to retrieve the current `CookieHandler` installation or we can call `setDefault(CookieHandler)` to set our own. Note that calling `setDefault` installs a `CookieHandler` object on a system-wide basis.

### 1.3. CookieManager

At this point, the `CookieManager` class is worth our attention. This class offers a complete implementation of `CookieHandler` class for most common use cases.

To have a **complete cookie management framework**, we need to have implementations of `CookiePolicy` and `CookieStore`.

`CookiePolicy` establishes the rules for accepting and rejecting cookies. We can of course change these rules to suit our needs.

Next – `CookieStore` does exactly what it's name suggests, it has methods for saving and retrieving cookies. Naturally we can tweak the storage mechanism here as well if we need to.

### 1.4. CookieStore

`CookieManager` adds the cookies to the `CookieStore` for every HTTP response and retrieves cookies from the `CookieStore` for every HTTP request.

The default `CookieStore` implementation **does not have persistence**, it rather loses all it's data when the JVM is restarted. More like RAM in a computer.

So if we would like our `CookieStore` implementation to behave like the hard disk and retain the cookies across JVM restarts, we must customize it's storage and retrieval mechanism.

## 2. CookieManager

Java 5 includes an abstract `java.net.CookieHandler` class that defines an API for storing and retrieving cookies. However, it does not include an implementation of that abstract class, so it requires a lot of grunt work. Java 6 fleshes this out by adding a concrete `java.net.CookieManager` subclass of `CookieHandler` that you can use. However, **it is not turned on by default**.

### 2.1. Enable CookieManager

Before Java will store and return cookies, you need to enable it:

```java
CookieManager manager = new CookieManager();
CookieHandler.setDefault(manager);
```

If all you want is to receive cookies from sites and send them back to those sites, you’re done. That’s all there is to it. After installing a `CookieManager` with those two lines of code, Java will store any cookies sent by HTTP servers you connect to with the `URL` class, and will send the stored cookies back to those same servers in subsequent requests.

> 笔记：这说明CookieManager和URL这两个类密切相关

### 2.2. CookiePolicy

However, you may wish to be a bit more careful about whose cookies you accept. You can do this by specifying a `CookiePolicy`. Three policies are predefined:

- `CookiePolicy.ACCEPT_ALL`: All cookies allowed
- `CookiePolicy.ACCEPT_NONE`: No cookies allowed
- `CookiePolicy.ACCEPT_ORIGINAL_SERVER`: Only first party cookies allowed

For example, this code fragment tells Java to block third-party cookies but accept first-party cookies:

```java
CookieManager manager = new CookieManager();
manager.setCookiePolicy(CookiePolicy.ACCEPT_ORIGINAL_SERVER);
CookieHandler.setDefault(manager);
```

That is, it will only accept cookies for the server that you’re talking to, not for any server on the Internet.

If you want more fine-grained control, for instance to allow cookies from some known domains but not others, you can implement the `CookiePolicy` interface yourself and override the `shouldAccept()` method:

```java
public boolean shouldAccept(URI uri, HttpCookie cookie)
```

A cookie policy that blocks all `.gov` cookies but allows others:

```java
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URI;

public class NoGovernmentCookies implements CookiePolicy {
    @Override
    public boolean shouldAccept(URI uri, HttpCookie cookie) {
        if (uri.getAuthority().toLowerCase().endsWith(".gov")
                || cookie.getDomain().toLowerCase().endsWith(".gov")) {
            return false;
        }
        return true;
    }
}
```

## 3. CookieStore

### 3.1. Get CookieStore

**It is sometimes necessary to put and get cookies locally**. For instance, when an application quits, it can save the cookie store to disk and load those cookies again when it next starts up. You can retrieve the store in which the CookieManager saves its cookies with the `getCookieStore()` method:

```java
CookieStore store = manager.getCookieStore();
```

> 笔记：如果此时调用`store.getClass()`方法，就会知道它是使用了`java.net.InMemoryCookieStore`类。

The `CookieStore` class allows you to **add, remove, and list cookies** so you can control the cookies that are sent outside the normal flow of HTTP requests and responses:

```java
//添加
public void add(URI uri, HttpCookie cookie)
//获取
public List<HttpCookie> get(URI uri)
public List<HttpCookie> getCookies()
public List<URI> getURIs()
//移除
public boolean remove(URI uri, HttpCookie cookie)
public boolean removeAll()
```

### 3.2. Custom CookieStore

One thing to note is that we cannot pass a `CookieStore` instance to `CookieManager` after creation. Our only option is to pass it during the creation of `CookieManager` or obtain a reference to the default instance by calling new `CookieManager().getCookieStore()` and complementing its behavior.

Here is the implementation of `PersistentCookieStore`:

```java
public class PersistentCookieStore implements CookieStore, Runnable {
    private CookieStore store;

    public PersistentCookieStore() {
        store = new CookieManager().getCookieStore();
        // deserialize cookies into store
        Runtime.getRuntime().addShutdownHook(new Thread(this));
    }

    @Override
    public void run() {
        // serialize cookies to persistent storage
    }

    @Override
    public void add(URI uri, HttpCookie cookie) {
        store.add(uri, cookie);

    }

    // delegate all implementations to store object like above
}
```

### 3.3. HttpCookie

Each cookie in the store is encapsulated in an `HttpCookie` object that provides methods for inspecting the attributes of the cookie.

```java
package java.net;
public class HttpCookie implements Cloneable {
    public HttpCookie(String name, String value)

    // obsolete
    public void setComment(String comment)
    public String getComment()
    public void setCommentURL(String url)
    public String getCommentURL()
    public void setDiscard(boolean discard)
    public boolean getDiscard()
    public int getVersion()
    public void setVersion(int v)

    public String getName()
    public void setValue(String value)
    public String getValue()
    public void setDomain(String domain)
    public String getDomain()
    public void setPortlist(String ports)
    public String getPortlist()
    public void setPath(String path)
    public String getPath()

    public boolean hasExpired()
    public void setMaxAge(long expiry)
    public long getMaxAge()
    public void setSecure(boolean flag)
    public boolean getSecure()


    public static boolean domainMatches(String domain, String host)
    public static List<HttpCookie> parse(String header)

    public String toString()
    public boolean equals(Object obj)
    public int hashCode()
    public Object clone()
}
```

Several of these attributes are not actually used any more. In particular `comment`, `comment URL`, `discard`, and `version` are only used by the now obsolete Cookie 2 specification that never caught on.
