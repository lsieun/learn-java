# Loggers

<!-- TOC -->

- [1. Methods](#1-methods)
  - [1.1. Create a logger](#11-create-a-logger)
  - [1.2. Logging Methods](#12-logging-methods)
  - [1.3. Adding and Removing Handlers](#13-adding-and-removing-handlers)
  - [1.4. Setting a Log Filter](#14-setting-a-log-filter)
  - [1.5. Setting the Log Level](#15-setting-the-log-level)
  - [1.6. Parent Logger](#16-parent-logger)
- [2. Hierarchical Loggers](#2-hierarchical-loggers)

<!-- /TOC -->

As stated earlier, client code sends log requests to `Logger` objects. Each logger keeps track of a **log level** that it is interested in, and discards log requests that are below this level.

> 笔记：Logger和Level之间的关系

`Logger` objects are normally **named entities**, using **dot-separated names** such as `java.awt`. **The namespace is hierarchical** and is managed by the `LogManager`. The namespace should typically be aligned with the Java packaging namespace, but is not required to follow it exactly. For example, a `Logger` called `java.awt` might handle logging requests for classes in the `java.awt` package, but it might also handle logging for classes in `sun.awt` that support the client-visible abstractions defined in the `java.awt` package.

> `Logger`对应于namespace，而namespace由`LogManager`管理。

In addition to **named Logger objects**, it is also possible to create **anonymous Logger objects** that don't appear in the shared namespace.

> 除了named Logger objects，还有anonymous Logger objects

`Logger`s keep track of their parent loggers in the **logging namespace**<sub>笔记：父logger记录在logging namespace当中</sub>. A logger's parent is its nearest extant ancestor in the logging namespace. **The root logger** (named "") has no parent. **Anonymous loggers** are all given **the root logger** as their parent. Loggers may inherit various attributes from their parents in the logger namespace. In particular, a logger may inherit:

- **Logging level**: If a logger's level is set to be null, then the logger will use an effective Level that will be obtained by walking up the parent tree and using the first non-null Level.
- **Handlers**: By default, a Logger will log any output messages to its parent's handlers, and so on recursively up the tree.
- **Resource bundle names**: If a logger has a null resource bundle name, then it will inherit any resource bundle name defined for its parent, and so on recursively up the tree.

## 1. Methods

### 1.1. Create a logger

The most common way of using the Java Logging API is to create a `Logger` in each class that needs to log. This instance is typically made `static` and `final`, meaning all instances of that class use the same `Logger` instance.

```java
import java.util.logging.Logger;

// assumes the current class is called MyClass
private static final Logger LOGGER = Logger.getLogger(MyClass.class.getName());
```

The `Logger` you create is actually a hierarchy of `Logger`s, and a `.` (dot) in the hierarchy indicates a level in the hierarchy. So if you get a `Logger` for the `com.example` key, this `Logger` is a child of the `com` Logger and the `com` Logger is child of the Logger for the **empty String**(`""`). You can configure the main logger and this affects all its children.

Once instantiated, you can call the various logging methods on the Logger.

### 1.2. Logging Methods

The `Logger` class provides a large set of convenience methods for generating log messages. For convenience, there are methods for each logging level, named after the logging level name. Thus rather than calling `logger.log(Level.WARNING, ...)`, a developer can simply call the convenience method `logger.warning(...)`.

There are **two different styles of logging methods**, to meet the needs of different communities of users.

First, there are methods that take **an explicit source class name** and **source method name**. These methods are intended for developers who want to be able to quickly locate the source of any given logging message. An example of this style is:

```java
void warning(String sourceClass, String sourceMethod, String msg);
```

Second, there are a set of methods that do not take **explicit source class** or **source method names**. These are intended for developers who want easy-to-use logging and do not require detailed source information.

```java
void warning(String msg);
```

For this second set of methods, the Logging framework will make a "best effort" to determine **which class and method** called into the logging framework and will add this information into the `LogRecord`. However, it is important to realize that this automatically inferred information may only be approximate. Virtual machines perform extensive optimizations when just-in-time compiling and may entirely remove stack frames, making it impossible to reliably locate the calling class and method.

### 1.3. Adding and Removing Handlers

You can add `Handler`'s to the `Logger` using the `addHandler()` method. Here is an example:

```java
Logger logger = Logger.getLogger("myLogger");

logger.addHandler(new ConsoleHandler());
```

A `Logger` can have multiple `Handler`'s. When logging, messages are forwarded to all `Handler`'s.

You can obtain all `Handler`'s of a `Logger` using the `getHandlers()` method, like this:

```java
Handler[] handlers = logger.getHandlers();
```

You can remove a Handler using the `removeHandler()` method. Here is an example:

```java
Logger logger = Logger.getLogger("myLogger");
Handler handler = new ConsoleHandler();
logger.addHandler(handler);
logger.remove(handler)
```

### 1.4. Setting a Log Filter

You can set filters on a `Logger` which filters out what `LogRecord`s that gets forwarded to the `Handler`'s of the `Logger`. You set the `Filter` on a `Logger` using the `setFilter()` method, like this:

```java
Filter filter = new MyFilterImpl();
logger.setFilter(filter);
```

The class `MyFilterImpl` should be your own implementation of the `Filter` interface.

You can obtain the `Filter` in use by calling the `getFilter()` method, like this:

```java
Filter filter = logger.getFilter();
```

### 1.5. Setting the Log Level

You can set the minimum log level of messages to be forwarded to the `Handler`'s. Here is a code example:

```java
Logger logger = Logger.getLogger("myLogger");
logger.setLevel(Level.INFO);
```

This example sets the minimum log level of messages to be forwarded, to `Level.INFO`.

You can obtain the log level of a `Logger` using the `getLevel()` method:

```java
logger.getLevel();
```

You can also check if a given log level would be logged, if you tried logging a message with that log level. You do so using the `isLoggable()` method, like this:

```java
boolean isInfoLoggable = logger.isLoggable(Level.INFO);
```

### 1.6. Parent Logger

As mentioned elsewhere in this tutorial, the Logger's are organized into a hierarchy. That means, that a `Logger` can have a parent `Logger` in the hierarchy. You can obtain the parent `Logger` of a given `Logger` using the `getParent()` method. Here is an example:

```java
Logger parent = logger.getParent();
```

You can also tell the `Logger` to use or not use the Parent logger when logging. You do so using the `setUseParentHandlers()`, like this:

```java
logger.setUseParentHandlers(false);
```

This example switched off the forwarding of log messages to the parent Logger's Handler's.

You can check if a Logger forwards log messages to its parent Logger using the method `getUseParentHandlers()`. Here is an example:

```java
boolean useParentLogger = logger.getUseParentHandlers();
```

## 2. Hierarchical Loggers



Loggers are organized in a **hierarchical namespace**. The recommended namespace should follow the package naming style of the Java classes. For example we can create loggers with following names:

```txt
Logger1 > "com.logicbig"
Logger2 > "com.logicbig.MyClass2" > parent namespace= "com.logicbig"
Logger3 > "com.logicbig.MyClass3" > parent namespace= "com.logicbig"
```

In above example `Logger1` is the parent of `Logger2` and `Logger3`. If we setup `Logger1` with specific properties (`Level`, `Handler`, `Formatter` etc) then those properties will be inherited to their children. A child is free to setup it's own properties to selectively override the parent ones.

An application will always has a 'root' logger. **The root Logger (named "") has no parent**.























