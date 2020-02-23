# URI

The `URI` class is an immutable representation of a **Uniform Resource Identifier (URI)**.

## 1. URI vs. URL

A `URI` is a generalization of the **Uniform Resource Locators (URLs)** used on the World Wide Web.

The `URI` supports parsing and textual manipulation of URI strings but does **not** have any direct **networking capabilities** the way that the `URL` class does.

The advantages of the `URI` class over the `URL` class are that the `URI` class provides **more general facilities** for parsing and manipulating URLs than the `URL` class;

- it can represent **relative URIs**, which do **not** include **a scheme (or protocol)**; and
- it can manipulate URIs that include **unsupported or even unknown schemes**.

A `URI` identifies **the name of a resource**, such as a website, or a file on the Internet. It **may** contain **the name of a resource** and **its location**.

A `URL` specifies **where a resource is located**, and **how to retrieve it**(这里应该是指protocol). **A protocol** forms the first part of the URL, and specifies **how data is retrieved**. URLs always contain protocol, such as `HTTP`, or `FTP`. For example, the following two URLs use different protocols. The first one uses the `HTTPS` protocol, and the second one uses the `FTP` protocol:

- `https://www.packtpub.com/`
- `ftp://speedtest.tele2.net/`

## 2. The general syntax

The general syntax of a URI consists of a scheme and a scheme-specific-part:

```txt
[scheme:] scheme-specific-part
```

There are many schemes that are used with a `URI`, including:

- `file`: This is used for files systems
- `FTP`: This is File Transfer Protocol
- `HTTP`: This is commonly used for websites
- `mailto`: This is used as part of a mail service
- `urn`: This is used to identify a resource by name

The **scheme-specific-part** varies by the scheme that is used. URIs can be categorized as **absolute** or **relative**, or as opaque or hierarchical. These distinctions are not of immediate interest to us here, though Java provides methods to determine whether a URI falls into one of these categories.

## 3. Construct URI

Obtain a URI with one of the constructors, which allow a URI to be parsed from a single string, or allow the specification of the individual components of a URI. These constructors can throw URISyntaxException, which is a checked exception.

When using hard-coded URIs (rather than URIs based on user input), you may prefer to use the static `create()` method, which does not throw any checked exceptions.

## 4. URI Info

Once you have created a `URI` object, you can use the **various get methods** to query the various portions of the URI.

The `getRaw()` methods are like the `get()` methods, except that they do not decode hexadecimal escape sequences of the form `%xx` that appear in the URI.

`normalize()` returns a new URI object that has “`.`” and unnecessary “`..`” sequences removed from its path component.

`resolve()` interprets its URI (or string) argument relative to this URI and returns the result.

`relativize()` performs the reverse operation. It returns a new URI that represents the same resource as the specified URI argument but is relative to this URI.

Finally, the `toURL()` method converts an absolute URI object to the equivalent `URL`. Since the `URI` class provides superior textual manipulation capabilities for URLs, it can be useful to use the `URI` class to resolve relative URLs (for example) and then convert those `URI` objects to `URL` objects when they are ready for networking.








