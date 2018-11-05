

## 空类

```java
package lsieun;

public class User {
}
```

```bash
$ javap -cp target/lsieun.jar -c -verbose lsieun.User
```

Output:

```txt
Classfile jar:file:/home/liusen/workdir/git-repo/learn-java/advanced/bytecode/code/learn-bytecode/target/lsieun.jar!/lsieun/User.class
  Last modified Nov 5, 2018; size 251 bytes
  MD5 checksum c845026d3c659a0e7fbfc25d43a2680e
  Compiled from "User.java"
public class lsieun.User
  minor version: 0
  major version: 49
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #3.#13         // java/lang/Object."<init>":()V
   #2 = Class              #14            // lsieun/User
   #3 = Class              #15            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               LocalVariableTable
   #9 = Utf8               this
  #10 = Utf8               Llsieun/User;
  #11 = Utf8               SourceFile
  #12 = Utf8               User.java
  #13 = NameAndType        #4:#5          // "<init>":()V
  #14 = Utf8               lsieun/User
  #15 = Utf8               java/lang/Object
{
  public lsieun.User();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 3: 0
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0       5     0  this   Llsieun/User;
}
SourceFile: "User.java"
```


