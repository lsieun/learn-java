# Archive

The path of the JAR entries must be relative. If the path being read from is absolute, the suggested code would create JAR entries with absolute paths.

The path of the JAR entries must have the **forward slash** ("`/`") as the path separator<sub>注：在jar文件中，只能使用`/`作为路径分隔符，而不能使用`\`作为路径分隔符</sub>. If the suggested code is executed on a Window machine, it would create JAR entries with backward slashes ("`\`") as the separator. Java class loaders only understand JAR entries with **forward slashes** in their path.

## Caution

### JarOutputStream

It turns out that `JarOutputStream` has three undocumented quirks:

- (1) Directory names must end with a '`/`' slash.
- (2) Paths must use '`/`' slashes, not '`\`'
- (3) All JarEntry's names should NOT begin with "`/`".


## Java Documentation

- [JAR File Specification](https://docs.oracle.com/javase/8/docs/technotes/guides/jar/jar.html)



