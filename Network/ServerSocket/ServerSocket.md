# ServerSocket

<!-- TOC -->

- [1. Using ServerSockets](#1-using-serversockets)
- [2. Constructing Server Sockets](#2-constructing-server-sockets)
  - [2.1. port](#21-port)
  - [2.2. queue length](#22-queue-length)
  - [2.3. network interface](#23-network-interface)
  - [2.4. anonymous port](#24-anonymous-port)
  - [2.5. Constructing Without Binding](#25-constructing-without-binding)
- [3. Getting Information About a Server Socket](#3-getting-information-about-a-server-socket)
- [4. Closing Server Sockets](#4-closing-server-sockets)
  - [4.1. How to close ServerSocket](#41-how-to-close-serversocket)
  - [4.2. Whether a SocketServer is open](#42-whether-a-socketserver-is-open)

<!-- /TOC -->

For servers that accept connections, Java provides a `ServerSocket` class that represents server sockets. In essence, a server socket’s job is to sit by the phone and wait for incoming calls. More technically, a server socket runs on the server and listens for incoming TCP connections. Each server socket listens on a particular port on the server machine. When a client on a remote host attempts to connect to that port, the server wakes up, negotiates the connection between the client and the server, and returns a regular `Socket` object representing the socket between the two hosts. In other words, server sockets wait for connections while client sockets initiate connections. Once a `ServerSocket` has set up the connection, the server uses a regular `Socket` object to send data to the client. Data always travels over the regular socket.

## 1. Using ServerSockets

The `ServerSocket` class contains everything needed to write servers in Java. It has **constructors** that create new `ServerSocket` objects, **methods** that **listen for connections on a specified port**, **methods** that configure the various **server socket options**, and the usual miscellaneous methods such as `toString()`.

In Java, the basic life cycle of a server program is this:

1. A new `ServerSocket` is created on a particular port using a `ServerSocket()` constructor.
2. The `ServerSocket` listens for incoming connection attempts on that port using its `accept()` method. `accept()` blocks until a client attempts to make a connection, at which point `accept()` returns a `Socket` object connecting the client and the server.
3. Depending on the type of server, either the `Socket`’s `getInputStream()` method, `getOutputStream()` method, or both are called to **get input and output streams** that communicate with the client.
4. **The server and the client interact** according to an agreed-upon protocol until it is time to close the connection.
5. The server, the client, or both **close the connection**.
6. The server returns to step 2 and waits for the next connection.

## 2. Constructing Server Sockets

There are four public `ServerSocket` constructors:

```java
public ServerSocket(int port) throws BindException, IOException
public ServerSocket(int port, int queueLength)
    throws BindException, IOException
public ServerSocket(int port, int queueLength, InetAddress bindAddress)
    throws IOException
public ServerSocket() throws IOException
```

These constructors specify the **port**, **the length of the queue** used to hold incoming connection requests, and **the local network interface** to bind to. They pretty much all do the same thing, though some use default values for the **queue length** and the **address**
to bind to.

### 2.1. port

For example, to create a server socket that would be used by an HTTP server on port `80`, you would write:

```java
ServerSocket httpd = new ServerSocket(80);
```

### 2.2. queue length

To create a server socket that would be used by an HTTP server on port `80` and queues up to `50` unaccepted connections at a time:

```java
ServerSocket httpd = new ServerSocket(80, 50);
```

If you try to expand the queue past the operating system’s maximum queue length, the maximum queue length is used instead.

### 2.3. network interface

By default, if a host has multiple network interfaces or IP addresses, the server socket listens on the specified port on all the interfaces and IP addresses. However, you can add a third argument to bind only to one particular local IP address. That is, the server socket only listens for incoming connections on the specified address; it won’t listen for connections that come in through the host’s other addresses.

For example, `login.ibiblio.org` is a particular Linux box in North Carolina. It’s connected to the Internet with the IP address `152.2.210.122`. The same box has a second Ethernet card with the local IP address `192.168.210.122` that is not visible from the public Internet, only from the local network. If, for some reason, you wanted to run a server on this host that only responded to local connections from within the same network, you could create a server socket that listens on port `5776` of `192.168.210.122` but not on port `5776` of `152.2.210.122`, like so:

```java
InetAddress local = InetAddress.getByName("192.168.210.122");
ServerSocket httpd = new ServerSocket(5776, 10, local);
```

### 2.4. anonymous port

In all three constructors, you can pass `0` for the port number so the system will select an available port for you. A port chosen by the system like this is sometimes called an **anonymous port** because you don’t know its number in advance (though you can find out after the port has been chosen). This is often useful in multisocket protocols such as FTP. In passive FTP the client first connects to a server on the well-known port `21`, so the server has to specify that port. However, when a file needs to be transferred, the server starts listening on any available port. The server then tells the client what other port it should connect to for data using the command connection already open on port `21`. Thus, the data port can change from one session to the next and does not need to be known in advance. (Active FTP is similar except the client listens on an ephemeral port for the server to connect to it, rather than the other way around.)

### 2.5. Constructing Without Binding

The **noargs constructor** creates a `ServerSocket` object but does not actually bind it to a port, so it cannot initially accept any connections. It can be bound later using the `bind()` methods:

```java
public void bind(SocketAddress endpoint) throws IOException
public void bind(SocketAddress endpoint, int queueLength) throws IOException
```

The primary use for this feature is to allow programs to set **server socket options** before binding to a port. Some options are fixed after the server socket has been bound. The general pattern looks like this:

```java
ServerSocket ss = new ServerSocket();
// set socket options...
SocketAddress http = new InetSocketAddress(80);
ss.bind(http);
```

You can also pass null for the `SocketAddress` to select **an arbitrary port**. This is like passing `0` for the port number in the other constructors.

## 3. Getting Information About a Server Socket

The `ServerSocket` class provides two getter methods that tell you the **local address** and **port** occupied by the server socket. These are useful if you’ve opened a server socket on an anonymous port and/or an unspecified network interface. This would be the case, for one example, in the data connection of an FTP session:

```java
public InetAddress getInetAddress()
```

This method returns the address being used by the server (the local host). If the local host has a single IP address (as most do), this is the address returned by `InetAddress.getLocalHost()`. If the local host has more than one IP address, the specific address returned is one of the host’s IP addresses. You can’t predict which address you will get. For example:

```java
ServerSocket httpd = new ServerSocket(80);
InetAddress ia = httpd.getInetAddress();
```

If the `ServerSocket` has not yet bound to a network interface, this method returns `null`.

The `ServerSocket` constructors allow you to listen on an unspecified port by passing `0` for the `port` number. This method lets you find out what port you’re listening on.

```java
public int getLocalPort()
```

You might use this in a peer-to-peer multisocket program where you already have a means to inform other peers of your location. Or a server might spawn several smaller servers to perform particular operations. The well-known server could inform clients on what ports they can find the smaller servers. Of course, you can also use `getLocalPort()` to find a nonanonymous port, but why would you need to?

## 4. Closing Server Sockets

**If you’re finished with a server socket, you should close it**, especially if the program is going to continue to run for some time. This frees up the port for other programs that may wish to use it. **Closing a `ServerSocket`** should not be confused with **closing a `Socket`**. Closing a `ServerSocket` frees a port on the local host, allowing another server to bind to the port; it also breaks all currently open sockets that the `ServerSocket` has accepted.

Server sockets are closed automatically when a program dies, so it’s not absolutely necessary to close them in programs that terminate shortly after the `ServerSocket` is no longer needed. Nonetheless, it doesn’t hurt.

### 4.1. How to close ServerSocket

Programmers often follow the same close-if-not-null pattern in a try-finally block.

```java
ServerSocket server = null;
try {
    server = new ServerSocket(port);
    // ... work with the server socket
} catch (IOException e) {
    e.printStackTrace();
} finally {
    if (server != null) {
        try {
            server.close();
        } catch (IOException ex) {
            // ignore
        }
    }
}
```

You can improve this slightly by using the **noargs `ServerSocket()` constructor**, which does not throw any exceptions and does not bind to a port. Instead, you call the `bind()` method to bind to a socket address after the `ServerSocket()` object has been constructed:

```java
ServerSocket server = new ServerSocket();
try {
    SocketAddress address = new InetSocketAddress(port);
    server.bind(address);
    // ... work with the server socket
} finally {
    try {
        server.close();
    } catch (IOException ex) {
        // ignore
    }
}
```

In Java 7, `ServerSocket` implements `AutoCloseable` so you can take advantage of try-with-resources instead:

```java
try (ServerSocket server = new ServerSocket(port)) {
    // ... work with the server socket
} catch (IOException e) {
    e.printStackTrace();
}
```

### 4.2. Whether a SocketServer is open

After a server socket has been closed, it cannot be reconnected, even to the same port. The `isClosed()` method returns `true` if the `ServerSocket` has been closed, `false` if it hasn’t:

```java
public boolean isClosed()
```

`ServerSocket` objects that were created with the **noargs `ServerSocket()` constructor** and not yet bound to a port are not considered to be closed. Invoking `isClosed()` on these objects returns `false`. The `isBound()` method tells you whether the `ServerSocket` has been bound to a port:

```java
public boolean isBound()
```

As with the `isBound()` method of the `Socket` class, the name is a little misleading. `isBound()` returns `true` if the `ServerSocket` has ever been bound to a port, even if it’s currently closed. If you need to test **whether a `ServerSocket` is open**, you must check both that `isBound()` returns `true` and that `isClosed()` returns `false`. For example:

```java
public static boolean isOpen(ServerSocket ss) {
    return ss.isBound() && !ss.isClosed();
}
```
