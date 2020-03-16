# Handlers

## Builtin JDK Handler

Java SE provides the following Handler classes:

- `StreamHandler`: A simple handler for writing formatted records to an `OutputStream`.
- `ConsoleHandler`: A simple handler for writing formatted records to `System.err`
- `FileHandler`: A handler that writes formatted log records either to **a single file**, or to **a set of rotating log files**.
- `SocketHandler`: A handler that writes formatted log records to remote TCP ports.
- `MemoryHandler`: A handler that buffers log records in memory.

## FileHandler

The `FileHandler` writes all messages to file. This can either be **a single file**, or **a set of rotated files**. If **rotated files** are used, each file is filled to **a certain size limit**, after which a new file is created. Each file name is composed of **a base name** and **a sequence number**. For instance `mylog.0.txt`, `mylog.1.txt` etc.

By default the `FileHandler` uses the `XMLFormatter` to format all messages before writing them to a file.

Here are the various constructors you can use to create a `FileHandler`:

```java
FileHandler handler = new FileHandler();
FileHandler handler = new FileHandler(String pattern);
FileHandler handler = new FileHandler(String pattern, boolean append);
FileHandler handler = new FileHandler(String pattern, int limit, int count);
FileHandler handler = new FileHandler(String pattern, int limit, int count,
    boolean append);
```

The first constructor creates a default `FileHandler`. This `FileHandler` is fully configured via the configuration file.

The second constructor creates a `FileHandler` with **a predefined pattern for generating file names** for the log files.

The third constructor creates a `FileHandler` with **a file name pattern**, and **a boolean** telling whether the `FileHandler` should append to any existing files or not. There is **no file size limit**, and file count is set to `1`.

The fourth constructor creates a `FileHandler` with **a file name pattern**, **a file size limit**, and **a file count**. When the log files reach the given file size limit a new file is created, until the maximum of the file count is reached. Then the FileHandler starts over with the first file again, deleting it and logging to it from scratch.

The fifth constructor creates a `FileHandler` with **a file name pattern**, **a file size limit**, **a file count**, and **a boolean** telling whether the FileHandler should append to any existing files or not.

Here are a few examples:

```java
FileHandler handler = new FileHandler("myapp-log.%u.%g.txt");
FileHandler handler = new FileHandler("myapp-log.%u.%g.txt", true);
FileHandler handler = new FileHandler("myapp-log.%u.%g.txt", 1024 * 1024, 10);
FileHandler handler = new FileHandler("myapp-log.%u.%g.txt", 1024 * 1024, 10, true);
```

### File Name Pattern

The file name pattern is a string containing **a file name** plus **one or more special codes**, telling the `FileHandler` how to generate the file names. The special codes you can use are:

```txt
Code  Meaning
/     The file name separator of the system. Typically either \ or / .
%t    The temp directory of the system.
%h    The user home directory of the system.
%g    The generation number that distinguishes the rotated log files from each other.
%u    A unique number to avoid naming conflicts.
%%    A single percent sign, in case you want to use that in your file name.
```

If no `%g` code has been specified and the file count of the `FileHandler` is greater than `1`, then the generation number (file sequence number) will be appended to the end of the file name, after a dot(`.`).

The `%u` code is usually set to `0`. If a file already exists with that name which is in use by another process, the `%u` code is set to 1, 2 etc. until an unused base file name is found. If no `%u` code is used in the file name pattern, and a conflict over the file name is found with another process, the unique number is appended to the end of the file name, after any automatically added generation number. Note: The use of `%u` to generate unique numbers for base file names is only guaranteed to work on a local file system.

Here are a few examples:

- `logfile.txt`: The file is called logfile, and is located in the current directory of the application.
- `logfile%g.txt`: The file is called logfile, and is located in the current directory of the application. A generation number is inserted after the text "logfile" in the file name. For instance, logfile0.txt, logfile1.txt etc.
- `logfile%u.%g.txt`: The file is called logfile, and is located in the current directory of the application. A unique number and a generation number is inserted after the text "logfile" in the file name. For instance, logfile0.0.txt, logfile0.1.txt etc.

## Custom Handler

We can create our own custom handlers also to perform specific tasks. To create our own `Handler` class, we need to extend `java.util.logging.Handler` class or any of it’s subclasses like `StreamHandler`, `SocketHandler` etc.

Here is an example of a custom java logging handler:

```java
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class MyHandler extends StreamHandler {

    @Override
    public void publish(LogRecord record) {
        //add own logic to publish
        super.publish(record);
    }


    @Override
    public void flush() {
        super.flush();
    }


    @Override
    public void close() throws SecurityException {
        super.close();
    }

}
```
