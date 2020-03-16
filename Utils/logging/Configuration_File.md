# Configuration File

<!-- TOC -->

- [1. Default Configuration](#1-default-configuration)
- [2. Dynamic Configuration Updates](#2-dynamic-configuration-updates)
- [3. Configuration, Setting up logging properties](#3-configuration-setting-up-logging-properties)
  - [3.1. Using JVM system property(class)](#31-using-jvm-system-propertyclass)
  - [3.2. Using JVM system property(config file)](#32-using-jvm-system-propertyconfig-file)
  - [3.3. Default configuration file](#33-default-configuration-file)
- [4. All Config](#4-all-config)
  - [4.1. root Logger](#41-root-logger)
  - [4.2. logger](#42-logger)
  - [4.3. FileHandler](#43-filehandler)
  - [4.4. ConsoleHandler](#44-consolehandler)
  - [4.5. StreamHandler](#45-streamhandler)
  - [4.6. SocketHandler](#46-sockethandler)
  - [4.7. MemoryHandler](#47-memoryhandler)

<!-- /TOC -->

The **logging configuration** can be initialized using **a logging configuration file** that will be read at startup. This logging configuration file is in standard `java.util.Properties` format.

> 笔记：logging configuration --> a logging configuration file

Alternatively, the **logging configuration** can be initialized by specifying **a class** that can be used for reading **initialization properties**. This mechanism allows configuration data to be read from arbitrary sources, such as LDAP and JDBC.

> 笔记：logging configuration --> a class --> initialization properties

There is a small set of **global configuration information**. This is specified in the description of the `LogManager` class and includes a list of **root-level handlers** to install during startup.

> logging configuration = global configuration information + other configuration

The **initial configuration** may specify **levels for particular loggers**. These levels are applied to the named logger and any loggers below it in the naming hierarchy. The levels are applied in the order they are defined in the **configuration file**.

> 笔记：initial configuration包含日志输出的Level信息

The **initial configuration** may contain **arbitrary properties for use by handlers** or by subsystems doing logging. By convention, these properties should use names starting with **the name of the handler class** or **the name of the main Logger** for the subsystem.

For example, the `MemoryHandler` uses a property `java.util.logging.MemoryHandler.size` to determine the default size for its ring buffer.

## 1. Default Configuration

If we don’t specify any configuration, it’s read from `JRE_HOME/lib/logging.properties` file.

The **default logging configuration** that ships with the JRE is only a default and can be overridden by ISVs, system administrators, and end users.

**The default configuration** makes only limited use of disk space. It doesn't flood the user with information, but does make sure to always capture key failure information.

**The default configuration** establishes a single handler on the root logger for sending output to the console.

## 2. Dynamic Configuration Updates

Programmers can update the **logging configuration** at **run time** in a variety of ways:

- `FileHandler`, `MemoryHandler`, and `ConsoleHandler` objects can all be created with various attributes.
- New `Handler` objects can be added and old ones removed.
- New `Logger` object can be created and can be supplied with specific `Handler`s.
- `Level` objects can be set on target `Handler` objects.

## 3. Configuration, Setting up logging properties

We can load logging properties in one of following ways (in the order of priority).

### 3.1. Using JVM system property(class)

```txt
java.util.logging.config.class=com.logicbig.example.MyConfigClass
```

Java will load `MyConfigClass` during startup. The constructor of this class can use following method to initialize logging properties:

```java
LogManager.getLogManager().readConfiguration(InputStream);
```

In this initializing class, we can also setup base/individual Loggers like this:

```java
Logger mainLogger = Logger.getLogger("com.logicbig.example");
mainLogger.setLevel(Level.FINEST);
mainLogger.addHandler(....);
// ...
```

### 3.2. Using JVM system property(config file)

```txt
java.util.logging.config.file=D:\myApp\logging.properties
```

### 3.3. Default configuration file

If neither of the above properties is defined then `LogManager` will use `lib/logging.properties` in the Java installation directory. For example `C:\Java\jdk1.8.0_111\jre\lib\logging.properties`:

```txt
############################################################
#   Default Logging Configuration File
#
# You can use a different file by specifying a filename
# with the java.util.logging.config.file system property.
# For example java -Djava.util.logging.config.file=myfile
############################################################

############################################################
#   Global properties
############################################################

# "handlers" specifies a comma separated list of log Handler
# classes.  These handlers will be installed during VM startup.
# Note that these classes must be on the system classpath.
# By default we only configure a ConsoleHandler, which will only
# show messages at the INFO and above levels.
handlers= java.util.logging.ConsoleHandler

# To also add the FileHandler, use the following line instead.
#handlers= java.util.logging.FileHandler, java.util.logging.ConsoleHandler

# Default global logging level.
# This specifies which kinds of events are logged across
# all loggers.  For any given facility this global level
# can be overriden by a facility specific level
# Note that the ConsoleHandler also has a separate level
# setting to limit messages printed to the console.
.level= INFO

############################################################
# Handler specific properties.
# Describes specific configuration info for Handlers.
############################################################

# default file output is in user's home directory.
java.util.logging.FileHandler.pattern = %h/java%u.log
java.util.logging.FileHandler.limit = 50000
java.util.logging.FileHandler.count = 1
java.util.logging.FileHandler.formatter = java.util.logging.XMLFormatter

# Limit the message that are printed on the console to INFO and above.
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter

# Example to customize the SimpleFormatter output format
# to print one-line log message like this:
#     <level>: <log message> [<date/time>]
#
# java.util.logging.SimpleFormatter.format=%4$s: %5$s [%1$tc]%n

############################################################
# Facility specific properties.
# Provides extra control for each logger.
############################################################

# For example, set the com.xyz.foo logger to only log SEVERE
# messages:
com.xyz.foo.level = SEVERE
```

## 4. All Config

### 4.1. root Logger

- `handlers`: A white space or comma separated list of handler class names to be added to the **root Logger**
- `config`: A white space or comma separated list of class names which will be instantiated when the `LogManager` is initialized. The constructors of these classes can execute arbitrary configuration code.

### 4.2. logger

- `"logger".handlers`: Sets the handler classes to use for a given Logger in the hierarchy. Replace the "logger" with a specific name of a Logger in your app (e.g. com.jenkov.web).
- `"logger".useParentHandlers`: Tells a given Logger whether it should log to its parents or not (true or false).
- `"logger".level`: Tells a given Logger what minimum log level it should log.

### 4.3. FileHandler

- `java.util.logging.FileHandler.level`: Sets the default log level for all FileHandler's.
- `java.util.logging.FileHandler.filter`: A class name of the Filter to use on all FileHandler's.
- `java.util.logging.FileHandler.formatter`: A class name of the Formatter to use on all FileHandler's.
- `java.util.logging.FileHandler.encoding`: The encoding to use by all FileHandler's (e.g. UTF-8, UTF-16 etc.).
- `java.util.logging.FileHandler.limit`: The approximate amount of bytes to write to a log file, before rotating to a new file.
- `java.util.logging.FileHandler.count`: The number of log files to use in the log file rotation.
- `java.util.logging.FileHandler.append`: Sets whether or not the FileHandler's should append to an existing file or not (true or false), if an existing log file is found.
- `java.util.logging.FileHandler.pattern`: The log file name pattern.

### 4.4. ConsoleHandler

`java.util.logging.ConsoleHandler.level`: Sets the default log level of all ConsoleHandler's.
`java.util.logging.ConsoleHandler.filter`: Sets the Filter to use by all ConsoleHandler's
`java.util.logging.ConsoleHandler.formatter`: Sets the Formatter to use by all ConsoleHandler's.
`java.util.logging.ConsoleHandler.encoding`: Sets the encoding to use by all ConsoleHandler's.

### 4.5. StreamHandler

`java.util.logging.StreamHandler.level`: Sets the default log level of all StreamHandler's.
`java.util.logging.StreamHandler.filter`: Sets the Filter to use by all StreamHandler's
`java.util.logging.StreamHandler.formatter`: Sets the Formatter to use by all StreamHandler's.
`java.util.logging.StreamHandler.encoding`: Sets the encoding to use by all StreamHandler's.

### 4.6. SocketHandler

`java.util.logging.SocketHandler.level`: Sets the default log level of all SocketHandler's.
`java.util.logging.SocketHandler.filter`: Sets the Filter to use by all SocketHandler's.
`java.util.logging.SocketHandler.formatter`: Sets the Formatter to use by all SocketHandler's.
`java.util.logging.SocketHandler.encoding`: Sets the encoding to use by all SocketHandler's.
`java.util.logging.SocketHandler.host`: Sets the host name of the host to send the log messages to (e.g. jenkov.com).
`java.util.logging.SocketHandler.port`: Sets the port number of of the host to send the log message to (e.g. 9999).

### 4.7. MemoryHandler

`java.util.logging.MemoryHandler.level`: Sets the default log level of all MemoryHandler's.
`java.util.logging.MemoryHandler.filter`: Sets the Filter to use by all MemoryHandler's.
`java.util.logging.MemoryHandler.size`: The size of the internal LogRecord buffer.
`java.util.logging.MemoryHandler.push`: The push level of messages causing the buffer to be pushed to the target Handler. Defaults to SEVERE.
`java.util.logging.MemoryHandler.target`: The class name of the target Handler.

