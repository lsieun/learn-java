# Findbugs - Static Code Analysis of Java

Findbugs is an open source tool for **static code analysis** of Java programs. It scans **byte code** for so called **bug pattern** to find defects and/or suspicious code. 

> FindBugs是一个static code analysis工具，它是根据bug pattern扫描byte code发现潜在的问题。

Although Findbugs needs **the compiled class files** it is **not necessary to execute the code** for the analysis. 

> FindBugs虽然是基于byte code，但并不需要让代码运行起来。

Findbugs scans for possible bugs in Java software. **Each finding** is reported as **a warning**, but not all of these warnings are necessarily defects, e.g. warnings referring to possible performance issues. 

All warnings are classified in **four ranks**: (i) **scariest**, (ii) **scary**, (iii) **troubling** and (iv) **of concern**. This is a hint to the developer about the possible impact/severity of the warnings. 

The current version reports 400 warnings in **the nine categories**:



| Category                     | Number | Samples                                                      |
| ---------------------------- | ------ | ------------------------------------------------------------ |
| Correctness                  | 142    | Illegal format string<br/> Null value is guaranteed to be dereferenced<br/> Call to `equals()` comparing unrelated class and interface |
| Bad practice                 | 84     | Confusing method names<br/> Method may fail to close stream<br/> Comparison of String parameter using `==` or `!=` |
| Dodgy code                   | 71     | Useless control flow<br/> Integer remainder modulo 1<br/> Redundant null check of value known to be null |
| Multithreaded Correctness    | 45     | A thread was created using the default empty run method<br/> Class's writeObject() method is synchronized but nothing else is |
| Performance                  | 27     | Method concatenates strings using `+` in a loop<br/> Method invokes inefficient Boolean constructor |
| Malicious Code Vulnerability | 15     | Finalizer should be protected, not public<br/> Field isn't final and can't be protected from malicious code |
| Security                     | 11     | Hardcoded constant database password<br/> A prepared statement is generated from a variable String |
| Experimental                 | 3      | Method may fail to clean up stream or resource               |
| Internationalization         | 2      | Consider using Locale parameterized version of invoked method |

## Rankings

As of version 2, FindBugs started ranking bugs with a scale from `1` to `20` to measure the severity of defects:

- **Scariest**: ranked between `1 - 4`.
- **Scary**: ranked between `5 - 9`.
- **Troubling**: ranked between `10 - 14`.
- **Of concern**: ranked between `15 - 20`.

While the bug rank describes severity, the confidence factor reflects the likelihood of these bugs to be flagged as real ones. **The confidence was originally called priority**, but it was renamed in the new version.

## Categories

FindBugs divide defects in many categories:

- **Correctness** – gathers general bugs, e.g. infinite loops, inappropriate use of equals(), etc
- **Bad practice**, e.g. exceptions handling, opened streams, Strings comparison, etc
- **Performance**, e.g. idle objects
- **Multithreaded correctness** – gathers synchronization inconsistencies and various problems in a multi-threaded environment
- **Internationalization** – gathers problems related to encoding and application’s internationalization
- **Malicious code vulnerability** – gathers vulnerabilities in code, e.g. code snippets that can be exploited by potential attackers
- **Security** – gathers security holes related to specific protocols or SQL injections
- **Dodgy** – gathers code smells, e.g. useless comparisons, null checks, unused variables, etc

