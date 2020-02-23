# Reading the Response Header

<!-- TOC -->

- [1. Retrieving Specific Response Header Fields](#1-retrieving-specific-response-header-fields)
  - [1.1. getContentType()](#11-getcontenttype)
  - [1.2. getContentLength()](#12-getcontentlength)
  - [1.3. getContentEncoding()](#13-getcontentencoding)
  - [1.4. getDate()](#14-getdate)
  - [1.5. getExpiration()](#15-getexpiration)
  - [1.6. getLastModified()](#16-getlastmodified)
- [2. Retrieving Arbitrary Response Header Fields](#2-retrieving-arbitrary-response-header-fields)
  - [2.1. getHeaderFieldKey(int n)](#21-getheaderfieldkeyint-n)
  - [2.2. getHeaderField(int n)](#22-getheaderfieldint-n)
  - [2.3. getHeaderField(String name)](#23-getheaderfieldstring-name)
  - [2.4. getHeaderFieldDate(String name, long default)](#24-getheaderfielddatestring-name-long-default)
  - [2.5. getHeaderFieldInt(String name, int default)](#25-getheaderfieldintstring-name-int-default)

<!-- /TOC -->

## 1. Retrieving Specific Response Header Fields

The first six methods request specific, particularly common fields from the header.

These are:

- Content-type: `public String getContentType()`
- Content-length: `public int getContentLength()`
- Content-encoding: `public String getContentEncoding()`
- Date: `public long getDate()`
- Last-modified: `public long getLastModified()`
- Expires: `public long getExpiration()`

### 1.1. getContentType()

The `getContentType()` method returns the MIME media type of the response body. It relies on the web server to send a valid content type. It throws no exceptions and returns `null` if the content type isn’t available. `text/html` will be the most common content type you’ll encounter when connecting to web servers. Other commonly used types include `text/plain`, `image/gif`, `application/xml`, and `image/jpeg`.

If the content type is some form of text, this header may also contain a character set part identifying the document’s character encoding. For example:

```txt
Content-type: text/html; charset=UTF-8
```

In this case, `getContentType()` returns the full value of the `Content-type` field, including the character encoding.

### 1.2. getContentLength()

The `getContentLength()` method tells you how many bytes there are in the content. If there is no `Content-length` header, `getContentLength()` returns `-1`. The method throws no exceptions. It is used when you need to know exactly how many bytes to read or when you need to create a buffer large enough to hold the data in advance.

As networks get faster and files get bigger, it is actually possible to find resources whose size exceeds the maximum int value (about 2.1 billion bytes). In this case, `getContentLength()` returns `-1`. Java 7 adds a `getContentLengthLong()` method that works just like `getContentLength()` except that it returns a `long` instead of an `int` and thus can handle much larger resources:

```java
public int getContentLengthLong() // Java 7
```

### 1.3. getContentEncoding()

The `getContentEncoding()` method returns a `String` that tells you how the content is encoded. If the content is sent unencoded (as is commonly the case with HTTP servers), this method returns `null`. It throws no exceptions. The most commonly used content encoding on the Web is probably `x-gzip`, which can be straightforwardly decoded using a `java.util.zip.GZipInputStream`.

The **content encoding** is not the same as the **character encoding**. The **character encoding** is determined by the `Content-type` header or information internal to the document, and specifies how characters are encoded in bytes. **Content encoding** specifies how the bytes are encoded in other bytes.

### 1.4. getDate()

The `getDate()` method returns a `long` that tells you when the document was sent, in milliseconds since midnight, Greenwich Mean Time (GMT), January 1, 1970. You can convert it to a `java.util.Date`. For example:

```java
Date documentSent = new Date(uc.getDate());
```

This is the time the document was sent as seen from the server; it may not agree with the time on your local machine. If the HTTP header does not include a `Date` field, `getDate()` returns `0`.

### 1.5. getExpiration()

Some documents have server-based expiration dates that indicate when the document should be deleted from the cache and reloaded from the server. `getExpiration()` is very similar to `getDate()`, differing only in how the return value is interpreted. It returns a `long` indicating the number of milliseconds after **12:00 A.M., GMT, January 1, 1970**, at which the document expires. If the HTTP header does not include an `Expiration` field, `getExpiration()` returns `0`, which means that the document does not expire and can remain in the cache indefinitely.

### 1.6. getLastModified()

The final date method, `getLastModified()`, returns the date on which the document was last modified. Again, the date is given as the number of milliseconds since **midnight, GMT, January 1, 1970**. If the HTTP header does not include a `Last-modified` field (and many don’t), this method returns `0`.

## 2. Retrieving Arbitrary Response Header Fields

### 2.1. getHeaderFieldKey(int n)

This method returns the key (i.e., the field name) of the `n`<sup>th</sup> header field (e.g., `Content-length` or `Server`). The request method is header `zero` and has a `null` key. The **first header** is **one**. For example, in order to get the sixth key of the header of the `URLConnection uc`, you would write:

```java
String header6 = uc.getHeaderFieldKey(6);
```

### 2.2. getHeaderField(int n)

This method returns the value of the `n`<sup>th</sup> header field. In HTTP, **the starter line** containing the request method and path is header field **zero** and the **first actual header is one**.

```java
uc.getHeaderField(0)
```

```txt
HTTP/1.1 200 OK
```

### 2.3. getHeaderField(String name)

The `getHeaderField()` method returns the value of a named header field. **The name of the header** is **not case sensitive** and does not include a closing colon. For example, to get the value of the `Content-type` and `Content-encoding` header fields of a `URLConnection` object `uc`, you could write:

```java
String contentType = uc.getHeaderField("content-type");
String contentEncoding = uc.getHeaderField("content-encoding"));
```

To get the `Date`, `Content-length`, or `Expires` headers, you’d do the same:

```java
String data = uc.getHeaderField("date");
String expires = uc.getHeaderField("expires");
String contentLength = uc.getHeaderField("Content-length");
```

These methods all return `String`, not `int` or `long` as the `getContentLength()`, `getExpirationDate()`, `getLastModified()`, and `getDate()` methods that the preceding section did. If you’re interested in a numeric value, convert the `String` to a `long` or an `int`.

Do not assume the value returned by `getHeaderField()` is valid. You must check to make sure it is non-null.

### 2.4. getHeaderFieldDate(String name, long default)

This method first retrieves the header field specified by the name argument and tries to convert the string to a `long` that specifies the milliseconds since **midnight, January 1, 1970, GMT**. `getHeaderFieldDate()` can be used to retrieve a header field that represents a date (e.g., the `Expires`, `Date`, or `Last-modified` headers). To convert the string to an integer, `getHeaderFieldDate()` uses the `parse()` method of `java.util.Date`. The `parse()` method does a decent job of understanding and converting most common date formats, but it can be stumped—for instance, if you ask for a header field that contains something other than a date. If `parse()` doesn’t understand the date or if `getHeaderFieldDate()` is unable to find the requested header field, `getHeaderFieldDate()` returns the default argument. For example:

```java
Date expires = new Date(uc.getHeaderFieldDate("expires", 0));
long lastModified = uc.getHeaderFieldDate("last-modified", 0);
Date now = new Date(uc.getHeaderFieldDate("date", 0));
```

You can use the methods of the `java.util.Date` class to convert the `long` to a `String`.

### 2.5. getHeaderFieldInt(String name, int default)

This method retrieves the value of the header field name and tries to convert it to an `int`. If it fails, either because it can’t find the requested header field or because that field does not contain a recognizable integer, `getHeaderFieldInt()` returns the default argument. This method is often used to retrieve the `Content-length` field. For example, to get the content length from a `URLConnection uc`, you would write:

```java
int contentLength = uc.getHeaderFieldInt("content-length", -1);
```

In this code fragment, `getHeaderFieldInt()` returns `-1` if the `Content-length` header isn’t present.
