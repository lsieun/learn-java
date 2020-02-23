# Readiness Selection

<!-- TOC -->

- [1. The Selector Class](#1-the-selector-class)
  - [1.1. Create Selector](#11-create-selector)
  - [1.2. Register Selector](#12-register-selector)
  - [1.3. Select Channels](#13-select-channels)
  - [1.4. Close](#14-close)
- [2. The SelectionKey Class](#2-the-selectionkey-class)
  - [2.1. channel](#21-channel)
  - [2.2. attachment](#22-attachment)
  - [2.3. cancel](#23-cancel)

<!-- /TOC -->

For network programming, the second part of the new I/O APIs is **readiness selection**, the ability to choose a socket that will not block when read or written. This is primarily of interest to servers, although clients running multiple simultaneous connections with several windows open—such as a web spider or a browser—can take advantage of it as well.

In order to perform **readiness selection**, different channels are registered with a `Selector` object. Each channel is assigned a `SelectionKey`. The program can then ask the `Selector` object for **the set of keys to the channels** that are ready to perform the operation you want to perform without blocking.

## 1. The Selector Class

### 1.1. Create Selector

The only constructor in `Selector` is protected. Normally, a new selector is created by invoking the static factory method `Selector.open()`:

```java
public static Selector open() throws IOException
```

### 1.2. Register Selector

The next step is to **add channels to the selector**. There are no methods in the `Selector` class to add a channel. The `register()` method is declared in the `SelectableChannel` class. Not all channels are selectable—in particular, `FileChannel`s aren’t selectable — but all network channels are. Thus, the channel is registered with a selector by passing the selector to one of the channel’s `register` methods:

```java
public final SelectionKey register(Selector sel, int ops) throws ClosedChannelException
public final SelectionKey register(Selector sel, int ops, Object att) throws ClosedChannelException
```

This approach feels backward to me, but it’s not hard to use. The first argument is the selector the channel is registering with. The second argument is a named constant from the `SelectionKey` class identifying the operation the channel is registering for. The `SelectionKey` class defines four named bit constants used to select the type of the operation:

- SelectionKey.OP_ACCEPT
- SelectionKey.OP_CONNECT
- SelectionKey.OP_READ
- SelectionKey.OP_WRITE

These are bit-flag int constants (1, 2, 4, etc.). Therefore, if a channel needs to register for multiple operations in the same selector (e.g., for both reading and writing on a socket), combine the constants with the bitwise or operator (`|`) when registering:

```java
channel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
```

The optional third argument is an attachment for the key. This object is often used to store state for the connection. For example, if you were implementing a web server, you might attach a `FileInputStream` or `FileChannel` connected to the local file the server streams to the client.

After the different channels have been registered with the selector, you can query the selector at any time to find out which channels are ready to be processed. Channels may be ready for some operations and not others. For instance, a channel could be ready for reading but not writing.

### 1.3. Select Channels

There are **three methods** that select the ready channels. They differ in how long they wait to find a ready channel. The first, `selectNow()`, performs a nonblocking select. It returns immediately if no connections are ready to be processed now:

```java
public abstract int selectNow() throws IOException
```

The other two select methods are blocking:

```java
public abstract int select() throws IOException
public abstract int select(long timeout) throws IOException
```

The first method waits until **at least one registered channel is ready to be processed** before returning. The second waits no longer than timeout milliseconds for a channel to be ready before returning `0`. These methods are useful if your program doesn’t have anything to do when no channels are ready to be processed.

When you know the channels are ready to be processed, retrieve the ready channels using `selectedKeys()`:

```java
public abstract Set<SelectionKey> selectedKeys()
```

You iterate through the returned set, processing each `SelectionKey` in turn. You’ll also want to remove the key from the iterator to tell the selector that you’ve handled it. Otherwise, the selector will keep telling you about it on future passes through the loop.

### 1.4. Close

Finally, when you’re ready to shut down the server or when you no longer need the selector, you should close it:

```java
public abstract void close() throws IOException
```

This step releases any resources associated with the selector. More importantly, it cancels all keys registered with the selector and interrupts up any threads blocked by one of this selector’s `select` methods.

## 2. The SelectionKey Class

`SelectionKey` objects serve as pointers to channels. They can also hold an object **attachment**, which is how you normally store the state for the connection on that channel.

`SelectionKey` objects are returned by the `register()` method when registering a channel with a selector. However, you don’t usually need to retain this reference. The `selectedKeys()` method returns the same objects again inside a `Set`. A single channel can be registered with multiple selectors.

When retrieving a `SelectionKey` from the set of selected keys, you often first test what that key is ready to do. There are four possibilities:

```java
public final boolean isAcceptable()
public final boolean isConnectable()
public final boolean isReadable()
public final boolean isWritable()
```

This test isn’t always necessary. In some cases, the selector is only testing for one possibility and will only return keys to do that one thing. But if the selector does test for multiple readiness states, you’ll want to test which one kicked the channel into the ready state before operating on it. It’s also possible that a channel is ready to do more than one thing.

### 2.1. channel

Once you know what the channel associated with the key is ready to do, retrieve the channel with the `channel()` method:

```java
public abstract SelectableChannel channel()
```

### 2.2. attachment

If you’ve stored an object in the `SelectionKey` to hold state information, you can retrieve it with the `attachment()` method:

```java
public final Object attachment()
```

### 2.3. cancel

Finally, when you’re finished with a connection, deregister its `SelectionKey` object so the selector doesn’t waste any resources querying it for readiness. I don’t know that this is absolutely essential in all cases, but it doesn’t hurt. You do this by invoking the key’s `cancel()` method:

```java
public abstract void cancel()
```

However, this step is only necessary if you haven’t closed the channel. **Closing a channel** automatically deregisters all keys for that channel in all selectors. Similarly, **closing a selector** invalidates all keys in that selector.
