# JVM Arguments

URL:

- [Guide to the Most Important JVM Parameters](https://www.baeldung.com/jvm-parameters)
- [jvm-options](http://jvm-options.tech.xebia.fr/)
- [JDK 9: XXtra Command Line Options](https://dzone.com/articles/jdk-9-xxtra-command-line-options)

## JVM OPTIONS

Several options and environment variables are available that can affect the performance characteristics of the HotSpot JVM. These options start with `-X` or `-XX`. Note that `-X` options are not standard options. So, they may not work on all VM implementations. The `-X` and `-XX` options are subject to change without notice in subsequent releases of the JDK.

The available `-X` options may be listed using the following command:

```bash
java -X
```

The partial output of this command is shown below:

```txt
...
-Xms<size>    set initial Java heap size
-Xmx<size>    set maximum Java heap size
-Xss<size>    set java thread stack size
...
```

The options related to **garbage collector** start with `-XX`.

> 这句短短的话，我觉得很重要，使用GC时，options都是以`-XX`开头的。

使用`java -XX:+PrintFlagsFinal -version`可以查看`-XX:`的默认值是什么。使用`-XX:+PrintFlagsFinal`参数可以输出所有参数的名称及默认值（默认不包括Diagnostic和Experimental的参数，如果需要，可以配合`-XX:+UnlockDiagnosticVMOptions/-XX:+UnlockExperimentalVMOptions`一起使用）。

```bash
java -XX:+PrintFlagsInitial -version
java -XX:+PrintFlagsInitial -XX:+UnlockExperimentalVMOptions -version
java -XX:+PrintFlagsFinal -version
java -XX:+PrintFlagsFinal -XX:+UnlockDiagnosticVMOptions -version
java -XX:+PrintFlagsFinal -XX:+UnlockExperimentalVMOptions -version
```

- **Options that do not require any value** (Boolean options) are turned on and off with `-XX:+<option>` and `-XX:-<option>` respectively (i.e., `+` for adding and `-` for removing option).
- **Options that require numeric value** are set with `-XX:<option>=<number>`. Numbers can include ‘`m`’ or ‘`M`’ for **megabytes**, ‘`k`’ or ‘`K`’ for **kilobytes**, and ‘`g`’ or ‘`G`’ for **gigabytes**. For example, 2k is the same as 2048. 
- **String options** are set with `-XX:<option>=<string>`.

The options below are loosely categorized into groups as follows:

- **Debugging options** - Usually used to enable tracing, printing, or output of VM information.
- **Behavioural options** - Used to change the basic behaviour of the VM.
- **Garbage First (G1)** Garbage Collection Options
- **Performance tuning options** - Behaves like knobs(旋纽；按钮) which can be used to tune VM performance.

## Explicit Heap Memory

One of the most common performance-related practices is to initialize the heap memory as per the application requirements.

That’s why we should specify minimal and maximal heap size. Below parameters can be used for achieving it:

```txt
-Xms<heap size>[unit] 
-Xmx<heap size>[unit]
```

Here, **unit** denotes the unit in which the memory (indicated by **heap size**) is to be initialized. Units can be marked as ‘**g**’ for **GB**, ‘**m**’ for **MB** and ‘**k**’ for **KB**.

For example, if we want to assign minimum 2 GB and maximum 5 GB to JVM, we need to write:

```txt
-Xms2G -Xmx5G
```

Starting with **Java 8**, the size of **Metaspace** is not defined. Once it reaches the global limit, JVM automatically increases it, However, to overcome any unnecessary instability, we can set `Metaspace` size with:

```txt
-XX:MaxMetaspaceSize=<metaspace size>[unit]
```

Here, **metaspace size** denotes the amount of memory we want to assign to Metaspace.

As per [Oracle guidelines](https://docs.oracle.com/javase/8/docs/technotes/guides/vm/gctuning/sizing.html), after total available memory, the second most influential factor is the proportion of the heap reserved for the **Young Generation**. By default, the minimum size of the YG is 1310 MB, and maximum size is unlimited.

We can assign them explicitly:

```txt
-XX:NewSize=<young size>[unit] 
-XX:MaxNewSize=<young size>[unit]
```

## Garbage Collection

For better stability of the application, choosing of right Garbage Collection algorithm is critical.

JVM has four types of GC implementations:

- Serial Garbage Collector
- Parallel Garbage Collector
- CMS Garbage Collector
- G1 Garbage Collector

These implementations can be declared with the below parameters:

```txt
-XX:+UseSerialGC
-XX:+UseParallelGC
-XX:+USeParNewGC
-XX:+UseG1GC
```

## GC Logging

To strictly monitor the application health, we should always check the JVM’s Garbage Collection performance. The easiest way to do this is to log the GC activity in human readable format.

Using the following parameters, we can log the GC activity:

```txt
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=<number of log files>
-XX:GCLogFileSize=<filesize>[unit]
-Xloggc:/path/to/gc.log
```

Use `java -XX:+PrintFlagsFinal` to print available `-XX` options. More **diagnostic** and **experimental** options can be added to list with following options: `-XX:+UnlockDiagnosticVMOptions`  `-XX:+UnlockExperimentalVMOptions` (for **Sun**) `-XX:+UnlockInternalVMOptions` (for **JRockit**)

`UseGCLogFileRotation` specifies the log file rolling policy, much like log4j, s4lj, etc. `NumberOfGCLogFiles` denotes the max number of log files that can be written for a single application life cycle. `GCLogFileSize` specifies the max size of the file. Finally, `loggc` denotes its location.

Point to note here is that, there are two more JVM parameters available (`-XX:+PrintGCTimeStamps` and `-XX:+PrintGCDateStamps`) which can be used to print date-wise timestamp in the GC log.

For example, if we want to assign a maximum of 100 GC log files, each having a maximum size of 50 MB and want to store them in ‘`/home/user/log/`’ location, we can use below syntax:

```txt
-XX:+UseGCLogFileRotation
-XX:NumberOfGCLogFiles=100
-XX:GCLogFileSize=50M
-Xloggc:/home/user/log/gc.log
```

However, the problem is that one additional daemon thread is always used for monitoring system time in the background. This behavior may create some performance bottleneck; that’s why it’s always better not to play with this parameter in production.

## Handling Out of Memory

It’s very common for a large application to face out of memory error which, in turn, results in the application crash. It’s a very critical scenario and very hard to replicate to troubleshoot the issue.

That’s why JVM comes with some parameters which dump heap memory into a physical file which can be used later for finding out leaks:

```txt
-XX:+HeapDumpOnOutOfMemoryError
-XX:HeapDumpPath=./java_pid<pid>.hprof
-XX:OnOutOfMemoryError="<cmd args>;<cmd args>"
-XX:+UseGCOverheadLimit
```

A couple of points to note here:

- **HeapDumpOnOutOfMemoryError** instructs the JVM to dump heap into physical file in case of `OutOfMemoryError`
- **HeapDumpPath** denotes the path where the file is to be written; any filename can be given; however, if JVM finds a `<pid>` tag in the name, the process id of the current process causing the out of memory error will be appended to the file name with `.hprof` format
- **OnOutOfMemoryError** is used to issue emergency commands to be executed in case of out of memory error; proper command should be used in the space of `cmd args`. For example, if we want to restart the server as soon as out of memory occur, we can set the parameter:

```txt
-XX:OnOutOfMemoryError="shutdown -r"
```

- **UseGCOverheadLimit** is a policy that limits the proportion of the VM’s time that is spent in GC before an OutOfMemory error is thrown

## 32/64 bit

In the OS environment where both 32 and 64-bit packages are installed, the JVM automatically chooses 32-bit environmental packages.

If we want to set the environment to 64 bit manually, we can do so using below parameter:

```txt
-d<OS bit>
```

**OS bit** can be either `32` or `64`. 

## Misc

- `-server`: enables “Server Hotspot VM”; this parameter is used by default in 64 bit JVM
- `-XX:+UseStringDeduplication`: Java 8u20 has introduced this JVM parameter for reducing the unnecessary use of memory by creating too many instances of the same String; this optimizes the heap memory by reducing duplicate String values to a single global `char[]` array
- `-XX:+UseLWPSynchronization`: sets LWP (Light Weight Process) – based synchronization policy instead of thread-based synchronization
- `-XX:LargePageSizeInBytes`: sets the large page size used for the Java heap; it takes the argument in GB/MB/KB; with larger page sizes we can make better use of virtual memory hardware resources; however, this may cause larger space sizes for the PermGen, which in turn can force to reduce the size of Java heap space
- `-XX:MaxHeapFreeRatio`: sets the maximum percentage of heap free after GC to avoid shrinking.
- `-XX:MinHeapFreeRatio`: sets the minimum percentage of heap free after GC to avoid expansion; to monitor the heap usage you can use [VisualVM](https://visualvm.github.io/) shipped with JDK.
- `-XX:SurvivorRatio`: Ratio of eden/survivor space size – for example, `-XX:SurvivorRatio=6` sets the ratio between each survivor space and eden space to be 1:6,
- `-XX:+UseLargePages`: use large page memory if it is supported by the system; please note that OpenJDK 7 tends to crash if using this JVM parameter
- `-XX:+UseStringCache`: enables caching of commonly allocated strings available in the String pool
- `-XX:+UseCompressedStrings`: use a byte[] type for String objects which can be represented in pure ASCII format
- `-XX:+OptimizeStringConcat`: it optimizes String concatenation operations where possible
