# Define Annotation



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

## 1. Limitations

There are a couple of limitations which in certain use cases make working with annotations not very convenient. Firstly, annotations do not support any kind of inheritance: one annotation cannot extend another annotation. Secondly, it is not possible to create an instance of annotation programmatically using the `new` operator. And thirdly, annotations can declare only attributes of **primitive types**, `String` or `Class<?>` types and **arrays of those**. No methods or constructors are allowed to be declared in the annotations.

这里说了annotation的三个限制：

- （1） 不能继承
- （2） 不能使用new创建实例
- （3） 只能定义attributes，而不能定义methods和构造constructors。

### 1.1. 定义Annotation

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

