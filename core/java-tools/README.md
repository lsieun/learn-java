# Command-Line Tools

我从3个层次去理解这些工具：

- （1）sources 代码
- （2）classes 字节码文件（静态的class文件，没有被加载到JVM中、没有运行的class文件）
- （3）run 运行过程中的状态

## 1. sources

源代码（source code）工具分为两部分：

- sources 内容编写
- sources 文件使用


| 阶段             | 可使用的Tools          | 说明                        |
| ---------------- | ---------------------- | --------------------------- |
| sources 内容编写 | `Eclipse` <br/> `IDEA` | 开发人员来写                |
| sources 文件使用 | `javadoc`              | 由source code生成document   |
|                  | `javac`                | 由source code生成class file |


## 2. classes

字节码文件（class file）工具分为三个部分：

- classes 内容(byte code)修改
- classes 内容(byte code)分析
- classes 文件(class file)使用

| 阶段             | 可使用的工具                            | 说明                                                         |
| ---------------- | --------------------------------------- | ------------------------------------------------------------ |
| classes 内容修改 | `asm`, <br/>`javassist`, <br/>`aspectj` |                                                              |
| classes 内容分析 | `jdeps`                                 | a static analysis tool for analyzing the dependencies of packages or classes. |
|                  | `javap`                                 | Java class disassembler -- effectively a tool for peeking inside class files. |
| classes 文件使用 | `jar`                                   | class file -> jar                                            |
|                  | `java`<br/>`javaw`                      | class file or jar -> JVM Run                                 |


## 3. run

运行（Running）过程中的工具分成三个类别：

- 多个进程的列表
- 单个进程的信息
- 单个进程的统计信息


| 类别     | 子类别           | 可使用的工具 | 进程 | 阶段   | 说明                                                         |
| -------- | ---------------- | ------------ | ---- | ------ | ------------------------------------------------------------ |
| 列表     |                  | `jps`        | ALL  | 运行中 | 查看所有的JVM processes                                      |
| 单个信息 | 参数（静态）     | `jinfo`      | One  | 启动前 | a given process, displays the **system properties** and **JVM options** for a running Java process （启动前，使用的参数：system参数和JVM参数） |
|          | 算法（动态）     | `jstack`     | One  | 运行中 | produces **a stack trace** for **each Java thread** in **the process**. （方法栈调用信息，属于“算法”） |
|          | 内存空间（动态） | `jmap`       | One  | 运行中 | a view of memory allocation for a running Java process. （内存分配，属于“空间”） |
| 统计     | 客户端           | `jstat`      | One  | 运行中 | a given Java process, display some basic statistics          |
|          | 服务端           | `jstatd`     | ALL  | 运行中 | provides a way of making information about local JVMs available over the network. |


