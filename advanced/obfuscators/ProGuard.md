# ProGuard

URL: https://www.guardsquare.com/en/products/proguard/manual

# 1. Introduction

**ProGuard** is a Java class file **shrinker**, **optimizer**, **obfuscator**, and **preverifier**. 

- (1) The **shrinking** step detects and removes unused classes, fields, methods and attributes. 
- (2) The **optimization** step analyzes and optimizes the bytecode of the methods. 
- (3) The **obfuscation** step renames the remaining classes, fields, and methods using short meaningless names. 

These first steps make the code base smaller, more efficient, and harder to reverse-engineer.

- (4) The final **preverification** step adds preverification information to the classes, which is required for Java Micro Edition and for Java 6 and higher.


**Each of these steps is optional**. For instance, ProGuard can also be used to just list dead code in an application, or to preverify class files for efficient use in Java 6.

![](images/ProGuard_build_process_b.png)


ProGuard first reads the **input jars** (or aars, wars, ears, zips, apks, or directories). It then subsequently shrinks, optimizes, obfuscates, and preverifies them. You can optionally let ProGuard perform multiple optimization passes. ProGuard writes the processed results to one or more **output jars** (or aars, wars, ears, zips, apks, or directories). The input may contain resource files, whose names and contents can optionally be updated to reflect the obfuscated class names.

ProGuard requires the **library jars** (or aars, wars, ears, zips, apks, or directories) of the input jars to be specified. These are essentially the libraries that you would need for compiling the code. ProGuard uses them to reconstruct the class dependencies that are necessary for proper processing. The library jars themselves always remain unchanged. You should still put them in the class path of your final application.

## 1.1 Entry points

In order to determine which code has to be preserved and which code can be discarded or obfuscated, you have to specify one or more **entry points** to your code. These entry points are typically classes with main methods, applets, midlets, activities, etc.

- In the **shrinking step**, ProGuard starts from these seeds and recursively determines which classes and class members are used. All other classes and class members are discarded.
- In the **optimization step**, ProGuard further optimizes the code. Among other optimizations, classes and methods that are not entry points can be made private, static, or final, unused parameters can be removed, and some methods may be inlined.
- In the **obfuscation step**, ProGuard renames classes and class members that are not entry points. In this entire process, keeping the entry points ensures that they can still be accessed by their original names.
- The **preverification step** is the only step that doesn't have to know the entry points.

## 1.2 Reflection




我现在的难点，在于如何起手Getting Started





