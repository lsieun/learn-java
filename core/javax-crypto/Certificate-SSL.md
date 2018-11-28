# SSL vs. TLS - What's the Difference?

URL: 

- https://www.globalsign.com/en/blog/ssl-vs-tls-difference/
- https://www.entrustdatacard.com/blog/2011/may/is-it-ssl-tls-or-https

**Internet security** is a bit like alphabet soup – `SSL`, `TLS`, ECC, SHA, the list goes on. All these acronyms can make it confusing to figure out what you actually need. 

> 关于Internet security， 有许多acronyms，例如`SSL`和`TLS`。

Perhaps the one we get asked about the most is - what’s the **difference** between **SSL (Secure Socket Layers)** and **TLS (Transport Layer Security)**? You know you want to secure your website (or other type of communication), but do you need SSL? TLS? Both? Let’s break it down.

## 1. A Brief History of SSL and TLS

`SSL` and `TLS` are both **cryptographic protocols** that provide **authentication** and **data encryption** between servers, machines and applications operating over a network (e.g. a client connecting to a web server). 

`SSL` is the **predecessor** to `TLS`. Over the years, new versions of the protocols have been released to address vulnerabilities and support stronger, more secure cipher suites and algorithms.

`SSL` was originally developed by Netscape and first came onto the scene way back in **1995** with `SSL 2.0` (1.0 was never released to the public). Version 2.0 was quickly replaced by `SSL 3.0` in **1996** after a number of vulnerabilities were found. Note: Versions 2.0 and 3.0 are sometimes written as SSLv2 and SSLv3.

`TLS` was introduced in **1999** as a new version of `SSL` and was based on `SSL 3.0`: The differences between this protocol and SSL 3.0 are not dramatic, but they are significant enough that `TLS 1.0` and `SSL 3.0` do **not interoperate(互通)**.

`TLS` is currently at `v.1.2`, with `TLS v.1.3` currently in draft.


## 2. Should You Be Using SSL or TLS?

Both `SSL 2.0` and `3.0` have been deprecated by the **IETF** (in 2011 and 2015, respectively). Over the years vulnerabilities have been and continue to be discovered in the deprecated `SSL` protocols (e.g. POODLE, DROWN). Most modern browsers will show a degraded user experience (e.g. line through the padlock or https in the URL bar, security warnings) when they encounter a web server using the old protocols. For these reasons, you should **disable SSL 2.0 and 3.0** in your server configuration, **leaving only TLS protocols enabled**.

## 3. Certificates are not the same as protocols

Before anyone starts worrying that they need to replace their existing SSL Certificates with TLS Certificates, it’s important to note that **certificates are not dependent on protocols**. That is, you don’t need to use a TLS Certificate vs. an SSL Certificate. While many vendors tend to use the phrase “SSL/TLS Certificate”, it may be more accurate to call them “**Certificates for use with SSL and TLS**", since **the protocols** are determined by **your server configuration**, **not the certificates themselves**.

It’s likely you will continue to see certificates referred to as SSL Certificates because at this point that’s the term more people are familiar with, but we’re beginning to see increased usage of the term TLS across the industry. `SSL/TLS` is a common compromise until more people become familiar with `TLS`.

## 4. Are SSL and TLS Any Different Cryptographically?

In truth, the answer to this question is yes, but you can say the same about the historic versions of SSL 2 and 3 or the TLS versions 1 with 1.1, 1.2 or 1.3. 

SSL and TLS are both about the same protocol but because of the version differences, SSL 2 was not interoperable with version 3, and SSL version 3 not with TLS version 1. You could argue that Transport Layer Security (TLS) was just a new name for SSL v4 - essentially, we are talking about the same protocol.

Each newly released version of the protocol came and will come with its own improvements and/or new/deprecated features. `SSL 1.0` was never released, `version 2.0` did but had some major flaws, `SSL version 3.0` was a rewrite of `version 2.0` (to fix these flaws) and `TLS version 1` an improvement of `SSL version 3`. Since the release of `TLS 1.0` the changes have been less significant, but never less important.

It’s worth noting here that `SSL` and `TLS` simply refer to **the handshake** that takes place between a client and a server. The handshake doesn’t actually do any encryption itself, it just agrees on a shared secret and type of encryption that is going to be used.

## 5. SSL/TLS/HTTPS

### 5.1 SSL

**Secure Sockets Layer (SSL)** is a **cryptographic protocol** that enables secure communications over the Internet. 

- (1) SSL was originally developed by Netscape and released as `SSL 2.0` in **1995**.  
- (2) A much improved `SSL 3.0` was released in **1996**.

### 5.2 TLS

**Transport Layer Security (TLS)** is the successor to `SSL`. 

- (1) `TLS 1.0` was defined in RFC 2246 in January **1999**. 
- (2) `TLS 1.1` (RFC 4346, April **2006**) and `TLS 1.2` (RFC 5246, August **2008**) are the later editions in the TLS family. 

### 5.3 HTTPS

**Hypertext Transfer Protocol Secure (HTTPS)**, or “**HTTP Secure**,” is an **application-specific implementation** that is a combination of the **Hypertext Transfer Protocol (HTTP)** with the `SSL/TLS`. HTTPS is used to provide encrypted communication with and secure identification of a Web server.
