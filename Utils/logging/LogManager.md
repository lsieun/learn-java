# LogManager

<!-- TOC -->

- [1. logging configuration](#1-logging-configuration)
- [2. Method](#2-method)
  - [2.1. create LogManager](#21-create-logmanager)
  - [2.2. readConfiguration](#22-readconfiguration)
  - [2.3. reset()](#23-reset)
  - [2.4. LoggingMXBean](#24-loggingmxbean)

<!-- /TOC -->

There is a global `LogManager` object that keeps track of global logging information. This includes:

- **A hierarchical namespace of named `Logger`s**.
- **A set of logging control properties** read from the configuration file.

There is a single `LogManager` object that can be retrieved using the static `LogManager.getLogManager` method. This is created during `LogManager` initialization, based on a system property. This property allows container applications (such as EJB containers) to substitute their own subclass of `LogManager` in place of the default class.

## 1. logging configuration

`java.util.logging.LogManager` is the class that reads the **logging configuration**, create and maintains the logger instances. We can use this class to set our own application specific configuration.

```java
LogManager.getLogManager().readConfiguration(new FileInputStream("mylogging.properties"));
```

Here is an example of Java Logging API Configuration file. If we don’t specify any configuration, it’s read from `JRE_HOME/lib/logging.properties` file.

File: mylogging.properties

```txt
handlers= java.util.logging.ConsoleHandler

.level= FINE

# default file output is in user's home directory.
java.util.logging.FileHandler.pattern = %h/java%u.log
java.util.logging.FileHandler.limit = 50000
java.util.logging.FileHandler.count = 1
java.util.logging.FileHandler.formatter = java.util.logging.XMLFormatter

# Limit the message that are printed on the console to INFO and above.
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter

com.journaldev.files = SEVERE
```

## 2. Method

The `java.util.logging.LogManager` manages the internal `Logger` hierarchy, and initiates the configuration of the `Logger`'s, either through the **configuration class** or **configuration file**.

### 2.1. create LogManager

There is only a single `LogManager` instantiated in the whole JVM. It is a singleton, in other words. Here is how to obtain the `LogManager` instance:

```java
LogManager manager = LogManager.getLogManager();
```

You will not often need to interact directly with the LogManager, except for a few borderline cases.

### 2.2. readConfiguration

For instance, if you want to **reload the configuration file**, you can do so using either of these methods:

```java
readConfiguration();
readConfiguration(inputStream);
```

The first method on the `LogManager` simply re-reads the configuration from file (or class), in case these has changed.

The second method on the `LogManager` simply reads the configuration from the given `InputStream`.

```java
InputStream stream = MyClass.class.getClassLoader().
                getResourceAsStream("logging.properties");
LogManager.getLogManager().readConfiguration(stream);
```

### 2.3. reset()

Reset the logging configuration. For all **named loggers**, the `reset` operation removes and closes all `Handler`s and (except for the **root logger**) sets the level to `null`. The **root logger**'s level is set to `Level.INFO`.

### 2.4. LoggingMXBean

You can also get access to an MXBean (Java Management Extensions) from the `LogManager` using the method `getLoggingMXBean()`. Here is an example:

```java
LoggingMXBean mxBean = logManager.getLoggingMXBean();
```
