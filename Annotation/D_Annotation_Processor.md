# Annotation processors

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


## 6. 自定义Annotation



### 6.2. 提取Annotation信息

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
