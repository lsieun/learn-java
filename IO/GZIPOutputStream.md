# GZIPOutputStream

## Notice

The problem is that you are not closing the `GZIPOutputStream`. Until you `close` it the output will be incomplete.

> 有一次我用GZIPOutputStream，怎么都得不到正确的的压缩后的数据，即使调用`flush`方法也不行，最后调用了`close`方法才成功。
