# java.util.zip.ZipFile

## 成员方法

第一批方法，关于自身的方法

- `ZipFile(String name)`: Opens a zip file for reading. 构造方法，其实有多个，这里只列了一个
- `getName()`: Returns the path name of the ZIP file.
- `close()`: Closes the ZIP file.

第二批方法，作为集合的方法：因为一个zip文件包含了很多文件

- `entries()`: Returns an enumeration of the ZIP file entries.
- `size()`： Returns the number of entries in the ZIP file.

第三批方法，获取单个entry的方法

- `getEntry(String name)`: Returns the zip file entry for the specified name, or null if not found.
- `getInputStream(ZipEntry entry)`: Returns an input stream for reading the contents of the specified zip file entry.
