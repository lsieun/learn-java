# java.util.zip.ZipEntry

## 静态成员变量

- `public static final int STORED = 0;`: Compression method for uncompressed entries.
- `public static final int DEFLATED = 8;`: Compression method for compressed (deflated) entries.

## 方法

第一批方法，自身的信息

- `getName()`: Returns the name of the entry.
- `isDirectory()`: Returns true if this is a directory entry.

第二批方法，压缩信息

- `getMethod()`: Returns the compression method of the entry.
- `getSize()`: Returns the uncompressed size of the entry data.
- `getCompressedSize()`: Returns the size of the compressed entry data.
