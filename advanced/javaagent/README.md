# javaagent

URL:

- [Getting Started with Javassist](https://www.javassist.org/tutorial/tutorial.html)
- [](https://github.com/jboss-javassist/javassist)

一个不错的教程[Link](https://udaniweeraratne.wordpress.com/category/javaagent/)：

- [Getting to know javaagents](https://udaniweeraratne.wordpress.com/2015/10/25/getting-to-know-javaagents/)
- [How to write a Simple Agent](https://udaniweeraratne.wordpress.com/2015/11/08/how-to-write-a-simple-agent-2/)
- [How to generate a MANIFEST & JAR using Maven](https://udaniweeraratne.wordpress.com/2016/01/21/how-to-generate-a-manifest-using-maven/)
- [How to copy maven dependencies](https://udaniweeraratne.wordpress.com/2016/02/07/how-to-copy-maven-dependencies/)
- [How to generate JNI based method Signature?](https://udaniweeraratne.wordpress.com/2016/07/10/how-to-generate-jni-based-method-signature/)

我可以做一个jar包，使用Java Agent来分析各种**排序算法**的运行效率

想一个问题，如何让一个“初学者”更容易听懂，且有逻辑的思路

Java Agent MindMap

- Java Agent
  - 如何写一个Java Agent
    - MANIFEST: Agent-Class, Premain-Class
    - Agent类
    - ClassFileTransformer
  - 如何使用（载入）一个Java Agent
    - 在进入程序的`main()`方法之**前**载入：通过JVM参数`-javaagent:xyz.jar`启动。
    - 在进入程序的`main()`方法之**后**载入

手工使用jar打包

使用IDEA进行打包

使用Maven进行打包，配置MANIFEST文件内容


## 如何载入Java Agent

前面说了，一个Java Agent既可以在「进入程序的`main()`方法之前」加载，也可以在「进入程序的`main()`方法之后」加载。

- JVM之内，时机不同(When，时间维度)：main方法之前、之后
- JVM之内，类加载情况（ClassLoader和Class）：大部分没有加载、大部分已经加载
- JVM之内，抛出异常：退出、继续执行
- JVM之外，JVM进程数量：1个、2个


### 在进入程序的main()方法之前加载

通过JVM参数`-javaagent:**.jar`启动。程序启动的时候，会优先载入Java Agent，并执行其`premain`方法。其实大部分的类都还没有被加载，这个时候可以实现对新载入的类的bytecode进行修改。

如果出现问题，会怎么处理？但是，如果`premain`方法执行失败或抛出异常，那么JVM会被终止，这是很致命的问题。

### 在进入程序的main()方法之后加载

程序启动之后，通过某种特定的手段载入Java Agent，这个特定手段就是`VirtualMachine`的`attach api`。这个api其实是JVM程序之间的沟通桥梁，底层通过socket进行通讯：JVM A可以传送一些指令给JVM B，JVM B收到指令之后，可以执行对应的逻辑。比如在命令列中经常使用的jstack、jcmd、jps等，很多都是基于这种机制实现的。

因为是程序间通讯，所以使用`attach api`的也是一个独立的Java程序。下面是一个简单的实现。

```java
// 15186表示目標程序的PID
VirtualMachine vm = VirtualMachine.attach("15186");
try {
    // 指定Java Agent的jar包路徑
    vm.loadAgent(".../agent.jar");
} finally {
    vm.detach();
}
```

首先，我們得知道目标程序的PID，这个可以通过jps指令方便得到，也可以通过`VirtualMachine`的`list`方法拿到本机所有Java程序的PID。通過`attach`連线上目标PID之後，可以获得表示目标程序的`vm`对象，執行`loadAgent`方法，对应的Java Agent会被载入，然后会找到指定的入口类，并执行`agentmain`方法，如果执行出現普通异常（除了oom和其它致命异常），目标JVM并不会受到影响。

通过这种方式，可以实现动态的载入Java Agent，而不需要修改JVM启动参数。

Java Agent 後續內容
attach api 的實現原理
agentmain 和 premain 方法中的Instrumentation引數是什麼？
如何自定義類載入器，避免汙染目前程序
如何實現位元組碼的修改
如何實現位元組碼的多次修改
如何恢復被修改過的位元組碼
如何解除安裝Java Agent的類
解除安裝自定義類載入器遇到的一些坑






