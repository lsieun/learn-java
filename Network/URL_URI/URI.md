# URI

<!-- TOC -->

- [1. syntax](#1-syntax)
  - [1.1. schemes](#11-schemes)
  - [1.2. scheme-specific parts](#12-scheme-specific-parts)
  - [1.3. charset](#13-charset)

<!-- /TOC -->

A Uniform Resource Identifier (URI) is a string of characters in a particular syntax that identifies a resource. The resource identified may be a file on a server; but it may also be an email address, a news message, a book, a person’s name, an Internet host, the current stock price of Oracle, or something else.

A resource is a thing that is identified by a URI. A URI is a string that identifies a resource. Yes, it is exactly that circular. **Don’t spend too much time worrying about what a resource is or isn’t**, because you’ll never see one anyway. **All you ever receive from a server is a representation of a resource which comes in the form of bytes**. However **a single resource may have different representations**. For instance, `https://www.un.org/en/documents/udhr/` identifies the Universal Declaration of Human Rights; but there are representations of the declaration in plain text, XML, PDF, and other formats. There are also representations of this resource in English, French, Arabic, and many other languages.

**Some of these representations may themselves be resources**. For instance, `https://www.un.org/en/documents/udhr/` identifies specifically the English version of the Universal Declaration of Human Rights.

## 1. syntax

The syntax of a URI is composed of a scheme and a scheme-specific part, separated by a colon, like this:

```txt
scheme:scheme-specific-part
```

### 1.1. schemes

The syntax of the scheme-specific part depends on the scheme being used. Current schemes include:

- `data`: Base64-encoded data included directly in a link; see RFC 2397
- `file`: A file on a local disk
- `ftp`: An FTP server
- `http`: A World Wide Web server using the Hypertext Transfer Protocol
- `mailto`: An email address
- `magnet`: A resource available for download via peer-to-peer networks such as BitTorrent
- `telnet`: A connection to a Telnet-based service
- `urn`: A Uniform Resource Name

In addition, Java makes heavy use of nonstandard custom schemes such as `rmi`, `jar`, `jndi`, and `doc` for various purposes.

### 1.2. scheme-specific parts

There is no specific syntax that applies to the **scheme-specific parts** of all URIs. However, many have a hierarchical form, like this:

```txt
//authority/path?query
```

The `authority` part of the URI names the authority responsible for resolving the rest of the URI. For instance, the URI h`ttp://www.ietf.org/rfc/rfc3986.txt` has the scheme `http`, the authority `www.ietf.org`, and the path `/rfc/rfc3986.txt` (initial slash included). This means the server at `www.ietf.org` is responsible for mapping the path `/rfc/rfc3986.txt` to a resource. This URI does not have a query part. The URI `http://www.powells.com/cgi-bin/biblio?inkey=62-1565928709-0` has the scheme `http`, the authority `www.powells.com`, the path `/cgi-bin/biblio`, and the query `inkey=62-1565928709-0`. The URI `urn:isbn:156592870` has the scheme `urn` but doesn’t follow the hierarchical `//authority/path?query` form for scheme-specific parts.

### 1.3. charset

```txt
scheme://authority/path?query
```

The scheme part is composed of **lowercase letters**, **digits**, and **the plus sign**, **period**, and **hyphen**. The other three parts of a typical URI (`authority`, `path`, and `query`) should each be composed of **the ASCII alphanumeric characters** (i.e., the letters `A-Z`, `a-z`, and the digits `0-9`). In addition, the punctuation characters - _ . ! and ~ may also be used. Delimiters such as / ? & and = may be used for their predefined purposes. All other characters, including non-ASCII alphanumerics such as `á` and `ζ` as well as delimiters not being used as delimiters should be escaped by a percent sign (`%`) followed by the hexadecimal codes for the character as encoded in UTF-8. For instance, in UTF-8, `á` is the two bytes `0xC3 0xA1` so it would be encoded as `%c3%a1`. The Chinese character `木` is Unicode code point `0x6728`. In UTF-8, this is encoded as the three bytes `E6`, `9C`, and `A8`. Thus, in a URI it would be encoded as `%E6%9C%A8`.

If you don’t hexadecimally encode non-ASCII characters like this, but just include them directly, then instead of a URI you have an IRI (an Internationalized Resource Identifier). IRIs are easier to type and much easier to read, but a lot of software and protocols expect and support only ASCII URIs.

Punctuation characters such as `/` and `@` must also be encoded with **percent escapes** if they are used in any role other than what’s specified for them in the scheme-specific part of a particular URL. For example, the forward slashes in the URI `http://www.cafeaulait.org/books/javaio2/` do not need to be encoded as `%2F` because they serve to delimit the hierarchy as specified for the http URI scheme. However, if a filename includes a `/` character—for instance, if the last directory were named **Java I/O** instead of javaio2 to more closely match the name of the book—the URI would have to be written as `http://www.cafeaulait.org/books/Java%20I%2FO/`. This is not as far-fetched as it might sound to Unix or Windows users. Mac filenames frequently include a forward slash. Filenames on many platforms often contain characters that need to be encoded, including @, $, +, =, and many more. And of course URLs are, more often than not, not derived from filenames at all.

