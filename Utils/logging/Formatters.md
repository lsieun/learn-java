# Formatters

<!-- TOC -->

- [1. Customizing log format](#1-customizing-log-format)
  - [1.1. Using logging.properties](#11-using-loggingproperties)
  - [1.2. Using system property](#12-using-system-property)
  - [1.3. Programmatically setting format](#13-programmatically-setting-format)

<!-- /TOC -->

Java SE also includes two standard `Formatter` classes:

- `SimpleFormatter`: Writes brief "human-readable" summaries of log records.
- `XMLFormatter`: Writes detailed XML-structured information.

As with handlers, it is fairly straightforward to develop new formatters.

## 1. Customizing log format

By default Java Util logging (JUL) uses following configurations (JDK 8):

```txt
handlers= java.util.logging.ConsoleHandler
.level= INFO
java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter
```

The complete default configuration file can be found at the JDK/JRE installation directory: `JAVA_HOME/jre/lib/logging.properties`.

The default handler, `ConsoleHandler`, sends log records to `System.err` (the available command line console). This handler, by default uses `SimpleFormatter` to format logs. In this tutorial, we will learn **how to modify the default log format**.

The default formatter, `SimpleFormatter`, formats the output by using following method call:

```java
String.format(format, date, source, logger, level, message, thrown);
```

The first argument 'format' can be customized in the `logging.properties` or by a command line option or can be set programmatically. The good thing, we don't have to learn new formatting specifiers here, as `java.util.Formatter` specification is fully supported.

### 1.1. Using logging.properties

Let's add a new line to `logging.properties` with property key `java.util.logging.SimpleFormatter.format` to specify our desire log format.

```txt
handlers= java.util.logging.ConsoleHandler
.level= INFO

java.util.logging.ConsoleHandler.level = INFO
java.util.logging.ConsoleHandler.formatter = java.util.logging.SimpleFormatter

java.util.logging.SimpleFormatter.format=[%1$tF %1$tT] [%4$-7s] %5$s %n
```

```java
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class D_Config_ReadFile {
    private static Logger LOGGER = null;

    static {
        InputStream stream = D_Config_ReadFile.class.getClassLoader().
                getResourceAsStream("logging.properties");
        try {
            LogManager.getLogManager().readConfiguration(stream);
            LOGGER = Logger.getLogger(D_Config_ReadFile.class.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("-- main method starts --");
        LOGGER.info("This is an info message");
        LOGGER.warning("This is a warning message");
    }
}
```

### 1.2. Using system property

```java
import java.util.logging.Logger;

public class D_Config_SysProperty {
    private static Logger LOGGER = null;

    static {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        LOGGER = Logger.getLogger(D_Config_SysProperty.class.getName());
    }

    public static void main(String[] args) {
        System.out.println("-- main method starts --");
        LOGGER.info("This is an info message");
        LOGGER.warning("This is a warning message");
    }
}
```

### 1.3. Programmatically setting format















