# Java Builtint Annotations

<!-- TOC -->

- [1. @Override](#1-override)
- [2. @Deprecated](#2-deprecated)
- [3. @SuppressWarnings](#3-suppresswarnings)
- [4. @SafeVarargs](#4-safevarargs)

<!-- /TOC -->

使用Annotation时要在其前面加`＠`符号。并把该Annotation当成一个修饰符使用，用于修饰它支持的程序元素。

Java提供了4个基本Annotation：

- `@Override`
- `@Deprecated`
- `@SuppressWarnings`
- `@SafeVarargs`

上面4个基本Annotation中的`@SafeVarargs`是Java 7新增的。这4个基本的Annotation都定义在`java.lang`包下。

## 1. `@Override`

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

## 2. `@Deprecated`

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

示例：应用在PACKAGE上

FileName: `package-info.java`

```java
@Deprecated
package com.sample;
```

## 3. `@SuppressWarnings`

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

示例一：应用在TYPE上

```java
@SuppressWarnings("rawtypes")
public class HelloWorld {
    //
}
```

示例二：应用在FIELD上

```java
import java.util.List;

public class HelloWorld {
    @SuppressWarnings("rawtypes")
    private List mylist;
}
```

示例三：应用在METHOD上

```java
public class HelloWorld {
    @SuppressWarnings("rawtypes")
    public void test() {
        //
    }
}
```

示例四：应用在PARAMETER上

```java
import java.util.List;

public class HelloWorld {
    public void test(@SuppressWarnings("rawtypes") List mylist) {
        //
    }
}
```

示例五：应用在CONSTRUCTOR上

```java
public class HelloWorld {
    @SuppressWarnings("rawtypes")
    public HelloWorld() {
        //
    }
}
```

示例六：应用在LOCAL_VARIABLE上

```java
import java.util.ArrayList;
import java.util.List;

public class HelloWorld {
    public void test() {
        @SuppressWarnings("rawtypes")
        List mylist = new ArrayList();
    }
}
```

## 4. `@SafeVarargs`

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
