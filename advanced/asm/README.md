# A Guide to Java Bytecode Manipulation with ASM #

## 1. Introduction ##

In this article, we’ll look at how to use the [ASM](http://asm.ow2.org/) library for **manipulating an existing Java class** by `adding fields`, `adding methods`, and `changing the behavior of existing methods`.

> 使用ASM可以进行的操作：manipulating an existing Java class by adding fields, adding methods, and changing the behavior of existing methods.

## 2. Dependencies ##

We need to add the ASM dependencies to our pom.xml:

	<dependency>
	    <groupId>org.ow2.asm</groupId>
	    <artifactId>asm</artifactId>
	    <version>6.0</version>
	</dependency>
	<dependency>
	    <groupId>org.ow2.asm</groupId>
	    <artifactId>asm-util</artifactId>
	    <version>6.0</version>
	</dependency>

We can get the latest versions of [asm](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.ow2.asm%22%20AND%20a%3A%22asm%22) and [asm-util](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.ow2.asm%22%20AND%20a%3A%22asm-util%22) from Maven Central.

## 3. ASM API Basics ##

The ASM API provides **two styles** of interacting with Java classes for transformation and generation: **event-based** and **tree-based**.

### 3.1. Event-based API ###

This API is heavily based on the **Visitor pattern** and is **similar in feel to the SAX parsing model** of processing XML documents. It is comprised, at its core, of the following components:

- ClassReader – helps to read class files and is the beginning of transforming a class
- ClassVisitor – provides the methods used to transform the class after reading the raw class files
- ClassWriter – is used to output the final product of the class transformation

> ClassReader  是开始，用于读取class files
> ClassVisitor 是进行中，用于tranform the class
> ClassWriter  是结束，用于输出结果

It’s in the **ClassVisitor** that we have all the visitor methods that we’ll use to touch the different components (fields, methods, etc.) of a given Java class. We do this by providing a subclass of ClassVisitor to implement any changes in a given class.

> 重点讲述了第二个类：ClassVisitor

Due to the need to preserve the integrity of the output class concerning Java conventions and the resulting bytecode, **this class requires a strict order** in which its methods should be called to generate correct output.

> 在使用ClassVisitor类的时候，要遵循严格的调用顺序；否则，就无法输出正确的结果。

	visit
	visitSource?
	visitOuterClass?
	( visitAnnotation | visitAttribute )*
	( visitInnerClass | visitField | visitMethod )*
	visitEnd

### 3.2. Tree-based API ###

This API is a **more object-oriented API** and is **analogous to the JAXB model** of processing XML documents.

It’s still based on the event-based API, but it introduces the ClassNode root class. This class serves as the entry point into the class structure.

## 4. Working With the Event-based ASM API ##

We’ll modify the `java.lang.Integer` class with ASM. And we need to grasp a fundamental concept at this point: the `ClassVisitor` class contains **all the necessary visitor methods** to create or modify all the parts of a class.

We only need to override the necessary visitor method to implement our changes. Let’s start by setting up the prerequisite components:

```java

```

We use this as a basis to add the `Cloneable` interface to the stock `Integer` class, and we also add a field and a method.

### 4.1. Working With Fields ###

Let’s create our `ClassVisitor` that we’ll use to add a field to the Integer class:


Next, let’s override the `visitField` method, where we first check if the field we plan to add already exists and set a flag(`isFieldPresent`) to indicate the status.

We still have to forward the method call to the parent class — this needs to happen as the `visitField` method is called for every field in the class. Failing to forward the call means no fields will be written to the class.

This method also allows us to modify the visibility or type of existing fields:






