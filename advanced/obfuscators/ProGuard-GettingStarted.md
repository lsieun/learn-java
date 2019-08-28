# ProGuard: Code Obfuscation tool

URL:
- https://self-learning-java-tutorial.blogspot.com/2016/09/proguard-code-obfuscation-tool.html
- http://www.alexeyshmalko.com/2014/proguard-real-world-example/
- https://www.guardsquare.com/en/products/proguard/manual/usage
- https://www.guardsquare.com/en/products/proguard/manual/examples

<!-- TOC -->

- [1. What is Obfuscation?](#1-what-is-obfuscation)
- [2. How ProGuard helps to my project?](#2-how-proguard-helps-to-my-project)
- [3. Where can I download Proguard?](#3-where-can-i-download-proguard)
- [4. How ProGuard perform Obfuscation?](#4-how-proguard-perform-obfuscation)
- [5. Example](#5-example)
  - [5.1. config proguard](#51-config-proguard)
  - [5.2. execute proguard](#52-execute-proguard)

<!-- /TOC -->

> Obfuscation 混淆  
> In software development, **obfuscation** is the deliberate act of creating source or machine code that is difficult for humans to understand.

ProGuard is a Java class file shrinker and code obfuscator.

## 1. What is Obfuscation?

Obfuscation is the process of converting programming code to some other form, so it is difficult to perform reverse engineering (Difficult to understand by humans). 

**Now a days**, sophisticated Java decompilers are available, which convert Java class files to source code. By using any good decompiler, one can reverse engineer the code, which is a security threat, by using obfuscation, we can make this reverse engineering process hard.

> 学习英语  
> `Nowadays` or `Now a Days`? `Nowadays` is the only **correct** spelling of this word. Spelling the word as three words—`now a days`—is **incorrect**. `Nowadays` means “**at the present time**.” It’s easy to use, but the spelling gives some people trouble. 

## 2. How ProGuard helps to my project?

- (1) It detect and remove unused classes, method and attributes.
- (2) List the dead code, so you can remove it from the source code.
- (3) Rename the classes, fields, methods with meaning less names, so it is difficult to reverse engineer.

## 3. Where can I download Proguard?

You can download ProGuard from following location.

https://sourceforge.net/projects/proguard/


## 4. How ProGuard perform Obfuscation?

The process is divided into 4 steps.

- (1) **Shrink**: Detects and removes unused classes, fields, methods, and attributes.
- (2) **Optimize**: Analyzes and optimizes the bytecode of the methods.
- (3) **Obfuscate**: Renames the remaining classes, fields, and methods using short meaningless names.
- (4) **Preverify**: Adds preverification information to Java classes, it is required for Java Micro Edition and for Java 6 and higher.

## 5. Example

### 5.1. config proguard

filename: `myconfig.pro`

```txt
-injars       agent.jar
-outjars      out.jar
-libraryjars  /usr/local/jdk1.8.0_181/jre/lib/rt.jar:./lib/javassist-3.24.0-GA.jar
-printmapping myapplication.map

-keep public class lsieun.javaagent.gui.GUIMain{ 
      public static void main(java.lang.String[]); 
}
```

注意：下面的配置文件更简洁，使用`<java.home>`代替`/usr/local/jdk1.8.0_181/jre`

```txt
-injars       agent.jar
-outjars      out.jar
-libraryjars  <java.home>/lib/rt.jar:./lib/javassist-3.24.0-GA.jar
-overloadaggressively
-repackageclasses In.God.We.Trust
-printmapping myapplication.map

-keep public class lsieun.javaagent.gui.GUIMain{
    public static void main(java.lang.String[]);
}
```

### 5.2. execute proguard

You can find the ProGuard jar(`proguard.jar`) in the `lib` directory of the ProGuard distribution.

```bash
java -jar proguard.jar @myconfig.pro -verbose
```




