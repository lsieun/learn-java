# Configuring the Client Request HTTP Header

<!-- TOC -->

- [1. setRequestProperty](#1-setrequestproperty)
- [2. addRequestProperty](#2-addrequestproperty)
- [3. getRequestProperty](#3-getrequestproperty)
- [4. getRequestProperties](#4-getrequestproperties)

<!-- /TOC -->

Each `URLConnection` sets a number of different `name-value` pairs in the header by default.

## 1. setRequestProperty

You add headers to the HTTP header using the `setRequestProperty()` method before you open the connection:

```java
public void setRequestProperty(String name, String value)
```

The `setRequestProperty()` method adds a field to the header of this `URLConnection` with a specified `name` and `value`. **This method can be used only before the connection is opened**. It throws an `IllegalStateException` if the connection is already open. The `getRequestProperty()` method returns the value of the named field of the HTTP header used by this `URLConnection`.

HTTP allows a single named request property to have multiple values. In this case, the separate values will be separated by commas. For example, the `Accept` header in the following code snippet has the four values `text/html`, `image/gif`, `image/jpeg`, and `*`.

```txt
Accept: text/html, image/gif, image/jpeg, *; q=.2, */*; q=.2
```

For instance, web servers and clients store some limited persistent information with cookies. A cookie is a collection of name-value pairs. The server sends a cookie to a client using the response HTTP header. From that point forward, whenever the client requests a URL from that server, it includes a Cookie field in the HTTP request header that looks like this:

```txt
Cookie: username=elharo; password=ACD0X9F23JJJn6G; session=100678945
```

This particular Cookie field sends three name-value pairs to the server. There’s no limit to the number of name-value pairs that can be included in any one cookie. Given a `URLConnection` object `uc`, you could add this cookie to the connection, like this:

```java
uc.setRequestProperty("Cookie", "username=elharo; password=ACD0X9F23JJJn6G; session=100678945");
```

You can set the same property to a new value, but this changes the existing property value. To add an additional property value, use the `addRequestProperty()` method instead.

## 2. addRequestProperty

To add an additional property value, use the `addRequestProperty()` method:

```java
public void addRequestProperty(String key, String value)
```

There’s no fixed list of legal headers. Servers usually ignore any headers they don’t recognize. HTTP does put a few restrictions on the content of the names and values of header fields. For instance, **the names can’t contain whitespace** and **the values can’t contain any line breaks**. Java enforces the restrictions on fields containing line breaks, but not much else. If a field contains a line break, `setRequestProperty()` and `addRequestProperty()` throw an `IllegalArgumentException`. Otherwise, it’s quite easy to make a `URLConnection` send malformed headers to the server, so be careful. Some servers will handle the malformed headers gracefully. Some will ignore the bad header and return the requested document anyway, but some will reply with an HTTP 400, Bad Request error.

## 3. getRequestProperty

If, for some reason, you need to inspect the headers in a `URLConnection`, there’s a standard getter method:

```java
public String getRequestProperty(String name)
```

## 4. getRequestProperties

Java also includes a method to get all the request properties for a connection as a `Map`:

```java
public Map<String,List<String>> getRequestProperties()
```

The keys are the header field names. The values are lists of property values. Both names and values are stored as strings.
