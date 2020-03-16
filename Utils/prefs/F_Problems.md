# Problems

## node name

不能使用`_`（下划线），否则会出现乱码的现象。例如，下面写法是不正确的：

```text
my_email_node
```

而应该使用`-`（中划线）。正确写法如下：

```text
my-email-node
```

## storage

默认情况下，并不能保证Preferences什么时候会将新修改或添加的内容写回到磁盘上。为了让修改后的内容输出到磁盘上，可以调用`flush()`方法。

