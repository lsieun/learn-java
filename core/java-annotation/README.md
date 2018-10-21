

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

##诞生

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




