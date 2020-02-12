# URL

This class represents a **Uniform Resource Locator(URL)** and allows the data referred to by the URL to be downloaded. 

One of the easiest ways to connect to a site and retrieve data is through the `URL` class. All that you need to provide is **the URL** for the site and the details of **the protocol**.

## 1. Construct URL


A URL can be specified as a single string or with separate protocol, host, port, and file specifications.

Relative URLs can also be specified with a `String` and the `URL` object to which it is relative. 

## 2. URL Info

`getFile()`, `getHost()`, `getProtocol()` and related methods return the various portions of the URL specified by a `URL` object. 

`sameFile()` determines whether a `URL` object refers to the same file as this one. 

`getDefaultPort()` returns the default port number for the protocol of the `URL` object; it may differ from the number returned by `getPort()`. 

## 3. Open Connection

Use `openConnection()` to obtain a `URLConnection` object with which you can download the content of the URL. 

For simple cases, however, the `URL` class defines shortcut methods that create and invoke methods on a `URLConnection` internally. `getContent()` downloads the URL data and parses it into an appropriate Java object (such as a string or image) if an appropriate `ContentHandler` can be found. 

In Java 1.3 and later, you can pass **an array of `Class` objects** that specify the type of objects that you are willing to accept as the return value of this method. 

If you wish to parse the URL content yourself, call `openStream()` to obtain an InputStream from which you can read the data.





