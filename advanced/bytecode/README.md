
ByteCode

- [Mastering Java Bytecode at the Core of the JVM](https://zeroturnaround.com/rebellabs/rebel-labs-report-mastering-java-bytecode-at-the-core-of-the-jvm/)


Java bytecode instruction

- [Java bytecode instruction listings](https://en.wikipedia.org/wiki/Java_bytecode_instruction_listings)

load: byte -> stack

- `bipush`: push **a byte** onto **the stack** as **an integer value**
- `iconst_m1`: load the int value `-1` onto the stack
- `iconst_0`: load the int value `0` onto the stack
- `iconst_1`: load the int value `1` onto the stack
- `iconst_5`: load the int value `5` onto the stack

load: constant pool -> stack

- `ldc`: push a constant `#index` from **a constant pool** (`String`, `int`, `float`, `Class`, `java.lang.invoke.MethodType`, or `java.lang.invoke.MethodHandle`) onto the stack

load: local variable -> stack

- `aload`: load **a reference** onto **the stack** from a **local variable** `#index`
- `aload_1`: load **a reference** onto **the stack** from **local variable** `1`
- `aload_2`: load **a reference** onto **the stack** from **local variable** `2`
- `aload_3`: load **a reference** onto **the stack** from **local variable** `3`

store: stack -> variable

- `istore`: store **int** value into variable `#index`
- `lstore`: store a **long** value in a **local variable** `#index`

Arithmetic Operators

- `iadd`: add two ints (`+`)
- `isub`: int subtract (`-`)
- `imul`: multiply two integers (`*`)
- `idiv`: divide two integers (`/`)
- `irem`: logical int remainder (`%`)
- `iinc`: increment local variable `#index` by signed byte const (`++`, `--`)

Relational Operators

- `if_icmpeq`: `==`
- `if_icmpne`: `!=`
- `if_icmple`: `<=`
- `if_icmpge`: `>=`
- `if_icmplt`: `<`
- `if_icmpgt`: `>`

Bitwise Operators

- `iand`: perform a bitwise AND on two integers (`&`)
- `ior`: bitwise int OR (`|`)
- `ixor`: int xor (`^`)
- `ishl`: int shift left (`<<`)
- `ishr`: int arithmetic shift right (`>>`)
- `iushr`: int logical shift right (`>>>`)

Logical Operators

- `ifeq`: if value is `0`, branch to instruction at **branchoffset**
- `ifne`: if value is **not** `0`, branch to instruction at **branchoffset**

Miscellaneous Operators

- `instanceof`: determines if an object `objectref` is of **a given type**

