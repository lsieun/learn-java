# Socket Addresses

<!-- TOC -->

- [1. From Socket](#1-from-socket)
- [2. InetSocketAddress](#2-inetsocketaddress)
  - [2.1. Constructor](#21-constructor)
  - [2.2. Static Factory Method](#22-static-factory-method)
  - [2.3. Getter Methods](#23-getter-methods)

<!-- /TOC -->

The `SocketAddress` class represents a connection endpoint. It is an empty abstract class with no methods aside from a default constructor. At least theoretically, the `SocketAddress` class can be used for both TCP and non-TCP sockets. In practice, only TCP/IP sockets are currently supported and the socket addresses you actually use are all instances of `InetSocketAddress`.

The primary purpose of the `SocketAddress` class is to provide a convenient store for transient socket connection information such as the **IP address** and **port** that can be reused to create new sockets, even after the original socket is disconnected and garbage collected.

```txt
socket address = ip address + port
```

## 1. From Socket

To this end, the `Socket` class offers two methods that return `SocketAddress` objects (`getRemoteSocketAddress()` returns the address of the system being connected to and `getLocalSocketAddress()` returns the address from which the connection is made):

```java
public SocketAddress getRemoteSocketAddress()
public SocketAddress getLocalSocketAddress()
```

Both of these methods return `null` if the socket is not yet connected.

## 2. InetSocketAddress

### 2.1. Constructor

The `InetSocketAddress` class (which is the only subclass of `SocketAddress` in the JDK, and the only subclass I’ve ever encountered) is usually created with a **host** and a **port** (for clients) or just a **port** (for servers):

```java
public InetSocketAddress(InetAddress address, int port)
public InetSocketAddress(String host, int port)
public InetSocketAddress(int port)
```

### 2.2. Static Factory Method

You can also use the static factory method `InetSocketAddress.createUnresolved()` to skip looking up the host in DNS:

```java
public static InetSocketAddress createUnresolved(String host, int port)
```

### 2.3. Getter Methods

`InetSocketAddress` has a few getter methods you can use to inspect the object:

```java
public final InetAddress getAddress()
public final int getPort()
public final String getHostName()
```
