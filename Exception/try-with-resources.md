# The try-with-resources Statement

<!-- TOC -->

- [1. Java File](#1-java-file)
  - [1.1. try-with-resources: AutoCloseable](#11-try-with-resources-autocloseable)
  - [1.2. try-with-resources: multi resources](#12-try-with-resources-multi-resources)
  - [1.3. try-with-resources: Exception](#13-try-with-resources-exception)
  - [1.4. catch and finally](#14-catch-and-finally)
- [2. Class File](#2-class-file)
  - [2.1. Basic try-with-resources](#21-basic-try-with-resources)
  - [2.2. Talk is cheap. Show me the code.](#22-talk-is-cheap-show-me-the-code)
  - [2.3. Resource close Order](#23-resource-close-order)

<!-- /TOC -->

## 1. Java File

### 1.1. try-with-resources: AutoCloseable

The **try-with-resources statement** is a `try` statement that declares one or more resources. A resource is an object that must be closed after the program is finished with it. The **try-with-resources statement** ensures that each resource is closed at the end of the statement. Any object that implements `java.lang.AutoCloseable`, which includes all objects which implement `java.io.Closeable`, can be used as a resource.

```java
/**
 * A {@code Closeable} is a source or destination of data that can be closed.
 * The close method is invoked to release resources that the object is
 * holding (such as open files).
 *
 * @since 1.5
 */
public interface Closeable extends AutoCloseable {

    public void close() throws IOException;
}

/**
 * @author Josh Bloch
 * @since 1.7
 */
public interface AutoCloseable {

    void close() throws Exception;
}
```

In the following example, the resource declared in the try-with-resources statement is a `BufferedReader`. The declaration statement appears within parentheses immediately after the `try` keyword. The class `BufferedReader`, in Java SE 7 and later, implements the interface `java.lang.AutoCloseable`. Because the `BufferedReader` instance is declared in a try-with-resource statement, it will be closed regardless of whether the try statement completes normally or abruptly (as a result of the method `BufferedReader.readLine` throwing an `IOException`).

```java
static String readFirstLineFromFile(String path) throws IOException {
    try (BufferedReader br =
                   new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```

### 1.2. try-with-resources: multi resources

You may declare **one or more resources** in a try-with-resources statement. The following example retrieves the names of the files packaged in the zip file `zipFileName` and creates a text file that contains the names of these files:

```java
public static void writeToFileZipFileContents(String zipFileName,
                                           String outputFileName)
                                           throws java.io.IOException {

    java.nio.charset.Charset charset =
         java.nio.charset.StandardCharsets.US_ASCII;
    java.nio.file.Path outputFilePath =
         java.nio.file.Paths.get(outputFileName);

    // Open zip file and create output file with 
    // try-with-resources statement

    try (
        java.util.zip.ZipFile zf =
             new java.util.zip.ZipFile(zipFileName);
        java.io.BufferedWriter writer = 
            java.nio.file.Files.newBufferedWriter(outputFilePath, charset)
    ) {
        // Enumerate each entry
        for (java.util.Enumeration entries =
                                zf.entries(); entries.hasMoreElements();) {
            // Get the entry name and write it to the output file
            String newLine = System.getProperty("line.separator");
            String zipEntryName =
                 ((java.util.zip.ZipEntry)entries.nextElement()).getName() +
                 newLine;
            writer.write(zipEntryName, 0, zipEntryName.length());
        }
    }
}
```

In this example, the try-with-resources statement contains two declarations that are separated by a semicolon: `ZipFile` and `BufferedWriter`. When the block of code that directly follows it terminates, either normally or because of an exception, the `close` methods of the `BufferedWriter` and `ZipFile` objects are automatically called in this order. **Note that the `close` methods of resources are called in the opposite order of their creation**.

### 1.3. try-with-resources: Exception

Prior to Java SE 7, you can use a `finally` block to ensure that a resource is closed regardless of whether the `try` statement completes normally or abruptly. The following example uses a `finally` block instead of a try-with-resources statement:

```java
static String readFirstLineFromFileWithFinallyBlock(String path)
                                                     throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(path));
    try {
        return br.readLine();
    } finally {
        if (br != null) br.close();
    }
}
```

However, in this example, if the methods `readLine` and `close` both throw exceptions, then the method `readFirstLineFromFileWithFinallyBlock` throws the exception thrown from the `finally` block; the exception thrown from the `try` block is suppressed. In contrast, in the example `readFirstLineFromFile`, if exceptions are thrown from both the `try` block and the try-with-resources statement, then the method `readFirstLineFromFile` throws the exception thrown from the `try` block; the exception thrown from the try-with-resources block is suppressed. In Java SE 7 and later, you can retrieve suppressed exceptions.

```java
public class XxxException extends Exception {

}

public class YyyException extends Exception {

}

public class MyResource implements AutoCloseable {
    @Override
    public void close() throws Exception {
        throw new YyyException();
    }
}

public class Finally_Exception {
    public static void play_try_finally() throws Exception {
        MyResource resource = null;
        try {
            resource = new MyResource();
            throw new XxxException();
        }
        finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

    public static void play_try_with_resource() throws Exception {
        try (MyResource resource = new MyResource();) {
            throw new XxxException();
        }
    }

    public static void main(String[] args) {
        try {
            //play_try_finally();
            play_try_with_resource();
        }
        catch (Exception ex) {
            System.out.println("Current Exception: " + ex.getClass());
            System.out.println("Suppressed: " + Arrays.toString(ex.getSuppressed()));
        }
    }
}
```

执行`play_try_finally()`，输出结果如下：

```txt
Current Exception: class lsieun.except.bean.YyyException
Suppressed: []
```

执行`play_try_with_resource()`，输出结果如下：

```txt
Current Exception: class lsieun.except.bean.XxxException
Suppressed: [lsieun.except.bean.YyyException]
```

### 1.4. catch and finally

Note: A **try-with-resources** statement can have `catch` and `finally` blocks just like an ordinary `try` statement. In a **try-with-resources** statement, any `catch` or `finally` block is run after the resources declared have been closed.

```java
public void test() {
    try(InputStream in = new FileInputStream("/path/to/file")) {
        int value = in.read();
    }
    catch (IOException ex) {
        System.out.println("Hello Catch");
    }
    finally {
        System.out.println("Hello Finally");
    }
}
```

## 2. Class File

### 2.1. Basic try-with-resources

Reference: [Basic try-with-resources](https://docs.oracle.com/javase/specs/jls/se7/html/jls-14.html#jls-14.20.3)

A **try-with-resources** statement with no `catch` clauses or `finally` clause is called a **basic try-with-resources statement**.

The meaning of a **basic try-with-resources statement**:

```java
try (R Identifier = Expression ...)
    Block
```

is given by the following translation to a local variable declaration and a try-catch-finally statement:

```java
{
    final R Identifier = Expression;
    Throwable #primaryExc = null;

    try ResourceSpecification_tail
        Block
    catch (Throwable #t) {
        #primaryExc = #t;
        throw #t;
    } finally {
        if (Identifier != null) {
            if (#primaryExc != null) {
                try {
                    Identifier.close();
                } catch (Throwable #suppressedExc) {
                    #primaryExc.addSuppressed(#suppressedExc);
                }
            } else {
                Identifier.close();
            }
        }
    }
}
```

### 2.2. Talk is cheap. Show me the code.

使用try-with-resources的方式：

```java
public void test_try_with_resources() throws IOException{
    try (InputStream in = new FileInputStream("/path/to/file");) {
        int b = in.read();
    }
}
```

在不使用try-with-resources的情况下，与上面的代码等价的代码：

```java
public void test_no_try_with_resources() throws IOException {
    InputStream in = null; // 1
    Throwable primaryException = null; // 2

    try {
        in = new FileInputStream("/path/to/file");
        int b = in.read(); // 3
    }
    catch (Throwable ex) { // 3
        primaryException = ex;
        throw ex;
    }
    finally {
        if (in != null) {
            if (primaryException != null) {
                try {
                    in.close();
                }
                catch (Throwable suppressedException) {
                    primaryException.addSuppressed(suppressedException);
                }
            }
            else {
                in.close();
            }
        }
    }
}
```

### 2.3. Resource close Order

**Note that the `close` methods of resources are called in the opposite order of their creation**.

```java
public void test() throws IOException {
    try (InputStream in = new FileInputStream("/path/to/file");
            BufferedInputStream bufferedIn = new BufferedInputStream(in);) {
        int value = bufferedIn.read();
    }
}
```

知识点：

- try-with-resources，AutoClosable
- try-with-resources, Exception处理
