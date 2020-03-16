# Log Levels

Each log message has an associated log `Level` object. The `Level` gives a rough guide to the importance and urgency of a log message. Log `Level` objects encapsulate **an integer value**, with **higher values indicating higher priorities**.

The `Level` class defines seven standard log levels, ranging from `FINEST` (the lowest priority, with the lowest value) to `SEVERE` (the highest priority, with the highest value).

```java
public static final Level OFF = new Level("OFF",Integer.MAX_VALUE, defaultBundle);

public static final Level SEVERE = new Level("SEVERE",1000, defaultBundle);
public static final Level WARNING = new Level("WARNING", 900, defaultBundle);
public static final Level INFO = new Level("INFO", 800, defaultBundle);
public static final Level CONFIG = new Level("CONFIG", 700, defaultBundle);
public static final Level FINE = new Level("FINE", 500, defaultBundle);
public static final Level FINER = new Level("FINER", 400, defaultBundle);
public static final Level FINEST = new Level("FINEST", 300, defaultBundle);

public static final Level ALL = new Level("ALL", Integer.MIN_VALUE, defaultBundle);
```

## Change Log Levels

In this example, we will learn how to change Java Util Logging **default level** to **a new value**.

To change a log level we must use `Logger.setLevel()` and `Handler.setLevel()`.

### Programmatically setting Log Level

We are going to set the log Level to `ALL` on the **root logger/handlers**, then we will find out all declared Levels in the `Level` class via reflection and then we will use the `log()` method on them. We will also set a custom easy to read log format.






