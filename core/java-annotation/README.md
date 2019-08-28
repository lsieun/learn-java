# Annotations

## Annotations as special interfaces

As we mentioned before, **annotations are the syntactic sugar used to associate the metadata with different elements of Java language**.

Annotations by themselves do not have any direct effect on the element they are annotating. However, depending on the annotations and the way they are defined, they may be used by Java compiler (the great example of that is the `@Override`), by **annotation processors** and by **the code at runtime using reflection and other introspection techniques**.

Let us take a look at the simplest annotation declaration possible:

```java
public @interface SimpleAnnotation {
}
```

The `@interface` keyword introduces new annotation type. That is why annotations could be treated as specialized interfaces. Annotations may declare the attributes with or without default values, for example:

```java
public @interface SimpleAnnotationWithAttributes {
    String name();
    int order() default 0;
}
```

If an annotation declares an attribute without a default value, it should be provided in all places the annotation is being applied. For example:

```java
@SimpleAnnotationWithAttributes(name = "new annotation")
```

By convention, if the annotation has an attribute with the name `value` and it is the only one which is required to be specified, the name of the attribute could be omitted, for example:

```java
public @interface SimpleAnnotationWithValue {
    String value();
}
```

It could be used like this:

```java
@SimpleAnnotationWithValue("new annotation")
```

There are a couple of limitations which in certain use cases make working with annotations not very convenient. Firstly, annotations do not support any kind of inheritance: one annotation cannot extend another annotation. Secondly, it is not possible to create an instance of annotation programmatically using the `new` operator. And thirdly, annotations can declare only attributes of **primitive types**, `String` or `Class<?>` types and **arrays of those**. No methods or constructors are allowed to be declared in the annotations.

这里说了annotation的三个限制：

- （1） 不能继承
- （2） 不能使用new创建实例
- （3） 只能定义attributes，而不能定义methods和构造constructors。

## Annotations and retention policy

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

## Annotations and element types

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

## Annotations and inheritance

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

## Repeatable annotations

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

## Annotation processors

The Java compiler supports a special kind of plugins called **annotation processors** (using the `-processor` command line argument) which could process the annotations during the compilation phase. **Annotation processors** can analyze the annotations usage (perform static code analysis), create additional Java source files or resources (which in turn could be compiled and processed) or mutate the annotated code.

The **retention policy** plays a key role by instructing the compiler which annotations should be available for processing by annotation processors.

Annotation processors are widely used, however to write one it requires some knowledge of how Java compiler works and the compilation process itself.

Annotation是一个接口，程序可以通过反射来获取指定程序元素的Annotation对象，然后通过Annotation对象来取得注释里的元数据。

值得指出的是，Annotation不影响程序代码的执行，无论增加、删除Annotation，代码都始终如一地执行。如果希望让程序中的Annotation在运行时起一定的作用，只有通过某种配套的工具对Annotation中的信息进行访问和处理，访问和处理Annotation的工具统称APT（Annotation Processing Tool）。


－ Annotation
    － 4
－ APT


- 4个基本Annotation
- 加入元数据
- 取出元数据 APT

如何定义：

- RetentionPolicy

## About

## 诞生

从JDK5开始，Java增加了元数据（MetaData）的支持，也就是Annotation（注释）。

## 基本Annotation

使用Annotation时要在其前面加`＠`符号。并把该Annotation当成一个修饰符使用，用于修饰它支持的程序元素。

Java提供了4个基本Annotation：

- `@Override`
- `@Deprecated`
- `@SuppressWarnings`
- `@SafeVarargs`

上面4个基本Annotation中的`@SafeVarargs`是Java 7新增的。这4个基本的Annotation都定义在`java.lang`包下。


### `@Override`

`@Override`的作用是告诉编译器检查这个方法，保证**父类**要包含一个被该方法重写的方法；否则，就会编译出错。

`@Override`只能用于方法，不能用于其他程序元素。

```java
package java.lang;

import java.lang.annotation.*;

/**
 * @since 1.5
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.SOURCE)
public @interface Override {
}
```

### `@Deprecated`

`@Deprecated`用于表示某个程序元素（类、方法）已经过时，当其他程序使用已过时的类、方法时，编译器将会给出警告。

```java
package java.lang;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * @since 1.5
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value={CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE})
public @interface Deprecated {
}
```

### `@SuppressWarnings`

`@SuppressWarnings`指示被该Annotation修饰的程序元素（以及该程序元素的所有子元素）取消显示指定的编译器警告。

```java
package java.lang;

import java.lang.annotation.*;
import static java.lang.annotation.ElementType.*;

/**
 * @since 1.5
 */
@Target({TYPE, FIELD, METHOD, PARAMETER, CONSTRUCTOR, LOCAL_VARIABLE})
@Retention(RetentionPolicy.SOURCE)
public @interface SuppressWarnings {
    String[] value();
}
```

### `@SafeVarargs`

`@SafeVarargs`是Java 7专门为抑制“堆污染”警告提供的。

```java
package java.lang;

import java.lang.annotation.*;

/**
 * @since 1.7
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD})
public @interface SafeVarargs {}
```

## JDK Meta Annotation

JDK除了在`java.lang`下提供了4个基本的Annotation之外，还在`java.lang.annotation`包下提供了4个Meta Annoation，这4个Meta Annotation都用于修饰其他的Annotation。

4个Meta Annoation:

- `@Retention`
- `@Target`
- `@Documented`
- `@Inherited`

### `@Retention`

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

### `@Target`

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


### `@Documented`

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

### `@Inherited`

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

## 自定义Annotation

### 定义Annotation

定义新的Annotation类型时，使用`@interface`关键字：

```java
public @interface Testable {
    //
}
```

使用Annotation时的语法，非常类似于`public`、`final`这样的修饰符。通常我们会把Annotation放在所有修饰符之前，而且由于使用Annotation时可能还需要为成员变量指定值，因而Annotation的长度可能较长，所以通常把Annotation另放一行：

```java
@Testable
public class MyClass {
    //
}
```

在默认情况下，Annotation可用于修饰任何程序元素，包括类、接口、方法等。

Annotation还可以带**成员变量**，这些成员变量在定义中以“**无形参的方法**”形式来声明，其**方法名**和**返回值**定义了该**成员变量**的**名字**和**类型**。

```java
public @interface MyTag {
    String name();
    int age();
}
```

另外，也可以在定义Annotation的成员变量时为其指定初始值（默认值），指定成员变量的初始值可以使用`default`关键字。如果为Annotation的成员变量指定了默认值，使用该Annotation时则可以不为这些成员变量指定值，而是使用默认值。

```java
public @interface MyTag {
    String name() default "tomcat";
    int age() default 12;
}
```

根据Annotation是否可以包含成员变量，可以把Annotation分为如下两类：

- 标记Annotation：一个没有定义成员变量的Annotation的类型被称为标记。这种Annotation仅利用自身的存在与否来为我们提供信息，例如`@Override`。
- 元数据Annotation：包含成员变量的Annotation。因为它们可以接受更多的元数据，所以也被称为元数据Annotation。


### 提取Annotation信息

当开发者使用Annotation修饰了类、方法、Field等成员之后，这些Annotation不会自己生效，必须由开发者提供相应的工具来提供并处理Annotation信息。

Java使用`Annotation`接口来代表程序元素前面的注释，该接口是所有Annotation类型的父接口。

```java
package java.lang.annotation;

/**
 * @since   1.5
 */
public interface Annotation {

    boolean equals(Object obj);

    int hashCode();

    String toString();

    Class<? extends Annotation> annotationType();
}
```

Java 5在`java.lang.reflect`包下新增了`AnnotationElement`接口，该接口代表程序中可以接受注释的程序元素。

```java
package java.lang.reflect;

/**
 * @since 1.5
 */
public interface AnnotatedElement {
    /**
     * Returns true if an annotation for the specified type
     * is <em>present</em> on this element, else false. 
     * @since 1.5
     */
    default boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        return getAnnotation(annotationClass) != null;
    }

   /**
     * Returns this element's annotation for the specified type if
     * such an annotation is <em>present</em>, else null.
     * @since 1.5
     */
    <T extends Annotation> T getAnnotation(Class<T> annotationClass);

    /**
     * Returns annotations that are <em>present</em> on this element.
     * @since 1.5
     */
    Annotation[] getAnnotations();

    /**
     * Returns annotations that are <em>associated</em> with this element.
     * @since 1.8
     */
    default <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        //
     }

    /**
     * Returns this element's annotation for the specified type if
     * such an annotation is <em>directly present</em>, else null.
     * @since 1.8
     */
    default <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        //
    }

    /**
     * Returns this element's annotation(s) for the specified type if
     * such annotations are either <em>directly present</em> or
     * <em>indirectly present</em>. This method ignores inherited
     * annotations.
     * @since 1.8
     */
    default <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        //
    }

    /**
     * Returns annotations that are <em>directly present</em> on this element.
     * This method ignores inherited annotations.
     * @since 1.5
     */
    Annotation[] getDeclaredAnnotations();
}
```

`AnnotationElement`接口主要有如下几个实现类：

- `Class`：类定义
- `Constructor`：构造器定义
- `Field`：类的成员变量定义。
- `Method`：类的方法定义。
- `Package`：类的包定义。

`AnnotationElement`接口是所有程序元素（如Class、Method、Constructor等）的父接口，所以程序通过反射获取了某个类的`AnnotationElement`对象（如Class、Method、Constructor等）之后，程序就可以调用该对象的如下3个方法来访问Annotation信息：

- `getAnnotation(Class<T> annotationClass)`：返回该程序元素上存在的指定类型的注释，如果该类型的注释不存在，则返回null。
- `getAnnotations()`：返回该程序元素上存在的所有注释。
- `isAnnotationPresent(Class<? extends Annotation> annotationClass)`：判断该程序元素上是否存在指定类型的注释，如果存在则返回`true`，否则返回`false`。

- JDK 4个基本Annotation （普通Annoation）
- JDK Meta Annotation （元Annotation）
- 自定义Annotation
    - 定义Annotation信息
    - 提取Annotation信息




