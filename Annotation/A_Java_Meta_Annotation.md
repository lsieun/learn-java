# Annotations

<!-- TOC -->

- [1. Intro](#1-intro)
  - [1.2. Annotations and retention policy](#12-annotations-and-retention-policy)
  - [1.3. Annotations and element types](#13-annotations-and-element-types)
  - [1.4. Annotations and inheritance](#14-annotations-and-inheritance)
  - [1.5. Repeatable annotations](#15-repeatable-annotations)
- [2. Source Code](#2-source-code)
  - [2.1. `@Retention`](#21-retention)
  - [2.2. `@Target`](#22-target)
  - [2.3. `@Documented`](#23-documented)
  - [2.4. `@Inherited`](#24-inherited)
  - [2.5. `@Repeatable`](#25-repeatable)

<!-- /TOC -->

## 1. Intro



### 1.2. Annotations and retention policy

Each annotation has the very important characteristic called **retention policy** which is an enumeration (of type `RetentionPolicy`) with the set of policies on how to retain annotations. It could be set to one of the following values.

| Policy    | Description                                                  |
| --------- | ------------------------------------------------------------ |
| `CLASS`   | Annotations are to be recorded in the class file by the compiler but need not be retained by the VM at run time |
| `RUNTIME` | Annotations are to be recorded in the class file by the compiler and retained by the VM at run time, so they may be read reflectively. |
| `SOURCE`  | Annotations are to be discarded by the compiler.             |

Retention policy has a crucial effect on when the annotation will be available for processing. The retention policy could be set using `@Retention` annotation. For example:

```java
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotationWithRetention {
}
```

Setting annotation retention policy to `RUNTIME` will guarantee its presence in the compilation process and in the running application.

### 1.3. Annotations and element types

Another characteristic which each annotation must have is the element types it could be applied to. Similarly to the **retention policy**, it is defined as **enumeration** (`ElementType`) with the set of possible element types.

| Element Type    | Description                                                  |
| --------------- | ------------------------------------------------------------ |
| ANNOTATION_TYPE | Annotation type declaration                                  |
| CONSTRUCTOR     | Constructor declaration                                      |
| FIELD           | Field declaration (includes enum constants)                  |
| LOCAL_VARIABLE  | Local variable declaration                                   |
| METHOD          | Method declaration                                           |
| PACKAGE         | Package declaration                                          |
| PARAMETER       | Parameter declaration                                        |
| TYPE            | Class, interface (including annotation type), or enum declaration |

Additionally to the ones described above, Java 8 introduces two new element types the annotations can be applied to.

| Element Type   | Description                |
| -------------- | -------------------------- |
| TYPE_PARAMETER | Type parameter declaration |
| TYPE_USE       | Use of a type              |



In contrast to the **retention policy**, an annotation may declare **multiple element types** it can be associated with, using the `@Target` annotation. For example:

```java
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
public @interface AnnotationWithTarget {
}
```

Mostly all annotations you are going to create should have both **retention policy** and **element types** specified in order to be useful.

### 1.4. Annotations and inheritance

The important relation exists between **declaring annotations** and **inheritance** in Java. By default, the subclasses do not inherit the annotation declared on the parent class. However, there is a way to propagate particular annotations throughout the class hierarchy using the `@Inherited` annotation. For example:

```java
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface InheritableAnnotation {
}

@InheritableAnnotation
public class Parent {
}

public class Child extends Parent {
}
```

In this example, the `@InheritableAnnotation` annotation declared on the `Parent` class will be inherited by the `Child` class as well.

### 1.5. Repeatable annotations

In pre-Java 8 era there was another limitation related to the annotations which was not discussed yet: the same annotation could appear only once at the same place, it cannot be repeated multiple times. Java 8 eased this restriction by providing support for repeatable annotations. For example:

```java
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RepeatableAnnotations {
    RepeatableAnnotation[] value();
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(RepeatableAnnotations.class)
public @interface RepeatableAnnotation {
    String value();
}

@RepeatableAnnotation("repeatition 1")
@RepeatableAnnotation("repeatition 2")
public void performAction() {
    // Some code here
}
```

Although in Java 8 the repeatable annotations feature requires a bit of work to be done in order to allow your annotation to be repeatable (using `@Repeatable`), the final result is worth it: more clean and compact annotated code.

## 2. Source Code

JDK除了在`java.lang`下提供了4个基本的Annotation之外，还在`java.lang.annotation`包下提供了4个Meta Annoation，这4个Meta Annotation都用于修饰其他的Annotation。

4个Meta Annoation:

- `@Retention`
- `@Target`
- `@Documented`
- `@Inherited`

### 2.1. `@Retention`

`@Retention`只能用于修饰一个**Annotation定义**，用于指定被修饰的Annotation可以保留多长时间。

```java
package java.lang.annotation;

/**
 * @since 1.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Retention {
    RetentionPolicy value();
}
```

`@Retention`包含一个`RetentionPolicy`类型的`value`成员变量。`value`成员变量的值只能是如下3个：

- `RetentionPolicy.CLASS`：编译器将把Annotation记录在class文件中。当运行Java程序时，JVM不再保留Annotation。这是默认值。
- `RetentionPolicy.RUNTIME`：编译器将把nnotation记录在class文件中。当运行Java程序时，JVM也会保留Annotation，程序可以通过反射获取该Annotation信息。
- `RetentionPolicy.SOURCE`：Annotation只保留在源代码中，编译器直接丢弃这种Annotation。

如果需要通过反射获取注释信息，就应当将`@Retention`的`value`属性值设置为`RetentionPolicy.RUNTIME`

```java
package java.lang.annotation;

/**
 * @since 1.5
 */
public enum RetentionPolicy {
    SOURCE,
    CLASS,
    RUNTIME
}
```

两个示例：

```java
@Retension(value=RetensionPolicy.RUNTIME) // 使用value=RetensionPolicy.RUNTIME
public @interface Testable{}
```

```java
@Retension(RetensionPolicy.SOURCE) // 直接使用RetensionPolicy.SOURCE
public @interface Testable{}
```

> 如果Annotation里只有一个`value`成员变量名，使用该Annotation时可以直接在Annotation后的括号里指定`value`的值，无须使用`name=value`的形式。

### 2.2. `@Target`

`@Target`也只能修饰一个Annotation定义，它用于指定被修饰的Annotation能用于修饰哪些程序单元。

```java
package java.lang.annotation;

/**
 * @since 1.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Target {
    ElementType[] value();
}
```

`@Target`也包含一个名为`value`的成员变量，该成员变量的值只能是如下几个：

- `ElementType.TYPE`：用于修饰类、接口（包括注释类型）、枚举
- `ElementType.FIELD`：用于修饰成员变量
- `ElementType.METHOD`：用于修饰方法定义
- `ElementType.PARAMETER`：用于修饰参数
- `ElementType.CONSTRUCTOR`：用于修饰构造器
- `ElementType.LOCAL_VARIABLE`：用于修饰局部变量
- `ElementType.ANNOTATION_TYPE`：用于用于修饰Annotation
- `ElementType.PACKAGE`：用于修饰包定义
- `ElementType.TYPE_PARAMETER`
- `ElementType.TYPE_USE`

```java
package java.lang.annotation;

/**
 * @since 1.5
 */
public enum ElementType {
    /** Class, interface (including annotation type), or enum declaration */
    TYPE,

    /** Field declaration (includes enum constants) */
    FIELD,

    /** Method declaration */
    METHOD,

    /** Formal parameter declaration */
    PARAMETER,

    /** Constructor declaration */
    CONSTRUCTOR,

    /** Local variable declaration */
    LOCAL_VARIABLE,

    /** Annotation type declaration */
    ANNOTATION_TYPE,

    /** Package declaration */
    PACKAGE,

    /**
     * Type parameter declaration
     *
     * @since 1.8
     */
    TYPE_PARAMETER,

    /**
     * Use of a type
     *
     * @since 1.8
     */
    TYPE_USE
}
```


### 2.3. `@Documented`

如果定义Annotation类时，使用了`@Documented`修饰，则所有使用该Annotation修饰的程序元素的API文档中将包含该Annotation说明。

```java
package java.lang.annotation;

/**
 * @author  Joshua Bloch
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Documented {
}
```

### 2.4. `@Inherited`

假如使用`@Inherited`定义一个名为`@Testable`的Annotation。
当父类被`@Testable`修饰时，那么子类将自动被`@Testable`修饰。

```java
package java.lang.annotation;

/**
 * @since 1.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Inherited {
}
```

### 2.5. `@Repeatable`

```java
/**
 * @since 1.8
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface Repeatable {
    /**
     * Indicates the <em>containing annotation type</em> for the
     * repeatable annotation type.
     * @return the containing annotation type
     */
    Class<? extends Annotation> value();
}
```
