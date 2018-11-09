

## Path

URL: https://www.baeldung.com/java-path

The `java.io.File` class has three methods — `getPath()`, `getAbsolutePath()` and `getCanonicalPath()` — to obtain the filesystem path.

Simply put, `getPath()` returns the String representation of the file’s abstract pathname. This is essentially **the pathname** passed to the `File` constructor.

So, if the File object was created using **a relative path**, the returned value from `getPath()` method would also be **a relative path**.

The `getAbsolutePath()` method returns **the pathname of the file after resolving the path for the current directory** — this is called an absolute pathname.

The `getCanonicalPath()` method goes a step further and resolves **the absolute pathname as well as the shorthands or redundant names like “`.`” and “`..`“** as per the directory structure. It also resolves **symbolic links** on Unix systems and converts **the drive letter** to a standard case on Windows systems.

It’s worth mentioning that a single file on the filesystem can have an infinite number of absolute paths since there’s an infinite number of ways shorthand representations can be used. However, **the canonical path will always be unique** since all such representations are resolved.

Unlike the last two methods, `getCanonicalPath()` may throw `IOException` because it requires filesystem queries.

### Use Case

Let’s say we’re writing a method that takes in a `File` object as a **parameter** and saves its **fully qualified name** into a database. We don’t know whether the path is **relative** or contains **shorthands**. In this case, we may want to use `getCanonicalPath()`.

However, since `getCanonicalPath()` reads the filesystem, it comes at **a performance cost**. If we are sure that there are no redundant names or symbolic links and drive letter case is standardized (if using a Windows OS), then we should prefer using `getAbsoultePath()`.




