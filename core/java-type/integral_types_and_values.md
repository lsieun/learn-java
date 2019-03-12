# Integral Types and Values

The values of the integral types are integers in the following ranges:

- For `byte` , from `-128` to `127`, inclusive
- For `short` , from `-32768` to `32767`, inclusive
- For `int` , from `-2147483648` to `2147483647`, inclusive
- For `long` , from `-9223372036854775808` to `9223372036854775807`, inclusive
- For `char` , from '`\u0000`' to '`\uffff`' inclusive, that is, from `0` to `65535`

> 作者有话说：我觉得，`short`和`char`两个类型占用的空间大小是一样的，都是2个字节，只是两种类型的取值范围不同而已。

New objects of the types `Boolean`, `Byte`, `Short`, `Character`, `Integer`, `Long`, `Float`, and `Double` may be implicitly created by boxing conversion.

