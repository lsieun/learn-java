# Overload Resolution

<!-- TOC -->

- [1. 具有父子关系的方法Overload](#1-%e5%85%b7%e6%9c%89%e7%88%b6%e5%ad%90%e5%85%b3%e7%b3%bb%e7%9a%84%e6%96%b9%e6%b3%95overload)
  - [1.1. 普通方法的Overload](#11-%e6%99%ae%e9%80%9a%e6%96%b9%e6%b3%95%e7%9a%84overload)
  - [1.2. lambda方法的Overload](#12-lambda%e6%96%b9%e6%b3%95%e7%9a%84overload)
- [1. 不具有父子关系的方法Overload](#1-%e4%b8%8d%e5%85%b7%e6%9c%89%e7%88%b6%e5%ad%90%e5%85%b3%e7%b3%bb%e7%9a%84%e6%96%b9%e6%b3%95overload)

<!-- /TOC -->

It’s possible in Java to overload methods, so you have multiple methods with the same name but different signatures. This approach poses a problem for **parameter-type inference** because it means that there are several types that could be inferred. In these situations `javac` will pick **the most specific type** for you.

讲解思路：

- （1） 可以先注释掉其中一个方法，然后运行程序，表明这两个方法都是能够通过编译的
- （2） 如果这两个方法同时存在，那么会使用哪一个呢？
- （3） javac处理这个问题的时候，会选择the most specific type；如果找不到，就会报错。

## 1. 具有父子关系的方法Overload

### 1.1. 普通方法的Overload

```java
/**
 * 问题：test()会调用哪一个overloadedMethod方法呢？
 * 答案：overloadedMethod(String s)
 */
public class Demo {

    private void overloadedMethod(Object o) {
        System.out.print("Object");
    }
    private void overloadedMethod(String s) {
        System.out.print("String");
    }

    public void test() {
        overloadedMethod("abc");
    }

    public static void main(String[] args) {
        Demo instance = new Demo();
        instance.test();
    }
}
```

验证方法一：运行main方法之后的输出结果

```txt
String
```

验证方法二：使用javap查看

```bash
$ javap -v Demo.class
...
  public void test();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: ldc           #6                  // String abc
         3: invokespecial #7                  // Method overloadedMethod:(Ljava/lang/String;)V
         6: return
...
```

### 1.2. lambda方法的Overload

A `BinaryOperator` is special type of `BiFunction` for which **the arguments** and **the return type** are all the same. For example, adding two integers would be a `BinaryOperator`.

```java
@FunctionalInterface
public interface BiFunction<T, U, R> {
    R apply(T t, U u);
}

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T,T,T> {
}
```

Because **lambda expressions** have the types of their functional interfaces, the same rules apply when passing them as arguments. We can overload a method with the `BinaryOperator` and `BiFunction`. When calling these methods, Java will infer the type of your lambda to be **the most specific functional interface**.

```java
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

/**
 * 问题：test()会调用哪一个overloadedMethod方法呢？
 * 答案：overloadedMethod(BinaryOperator<Integer> lambda)
 */
public class Demo {

    private void overloadedMethod(BiFunction<Integer, Integer, Integer> lambda) {
        System.out.print("BiFunction");
    }

    private void overloadedMethod(BinaryOperator<Integer> lambda) {
        System.out.print("BinaryOperator");
    }

    public void test() {
        overloadedMethod((x, y) -> x + y);
    }

    public static void main(String[] args) {
        Demo instance = new Demo();
        instance.test();
    }
}
```

验证方法一：运行main方法之后的输出结果

```txt
BinaryOperator
```

验证方法二：使用javap查看

```bash
$ javap -v Demo.class
...
  public void test();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=1, args_size=1
         0: aload_0
         1: invokedynamic #6,  0              // InvokeDynamic #0:apply:()Ljava/util/function/BinaryOperator;
         6: invokespecial #7                  // Method overloadedMethod:(Ljava/util/function/BinaryOperator;)V
         9: return
...
```

## 1. 不具有父子关系的方法Overload

Of course, when there are multiple method overloads, there isn’t always a clear “most specific type.”

```java
/**
 * 问题：test()会调用哪一个overloadedMethod方法呢？
 * 答案：会报错
 */
public class Demo {

    private interface IntPredicate {
        public boolean test(int value);
    }
    private void overloadedMethod(Predicate<Integer> predicate) {
        System.out.print("Predicate");
    }
    private void overloadedMethod(IntPredicate predicate) {
        System.out.print("IntPredicate");
    }

    public void test() {
        overloadedMethod((x) -> true);
    }

    public static void main(String[] args) {
        Demo instance = new Demo();
        instance.test();
    }
}
```

In this case, `javac` will fail to compile the code, complaining that the lambda expression is an ambiguous method call: `IntPredicate` doesn’t extend any `Predicate`, so the compiler isn’t able to infer that it’s more specific.

The way to fix these situations is to cast the lambda expression to either `IntPredicate` or `Predicate<Integer>`, depending upon which behavior you want to call.

```java
IntPredicate lambda = (x) -> true;
overloadedMethod(lambda);
// 或者
Predicate<Integer> lambda = (x) -> true;
overloadedMethod(lambda);
```

Of course, if you’ve designed the library yourself, you might conclude that this is a code smell and you should start renaming your overloaded methods.

In summary, **the parameter types of a lambda** are inferred from the **target type**, and **the inference** follows these rules:

- If there is a single possible target type, the lambda expression infers the type from the corresponding argument on the functional interface.
- If there are several possible target types, the most specific type is inferred.
- If there are several possible target types and there is no most specific type, you must manually provide a type.

这里要区分清楚两个概念：target type和parameter type

- target type是指具体的functional interface，是一个接口，例如`Predict`、`Consumer`和`Supplier`
- parameter type是指`(x,y) -> x + y`中`x`和`y`的具体类型是什么
