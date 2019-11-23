# Lambda VS Method Reference

<!-- TOC -->

- [1. Behavior Parameterization = Functional Interface + Lambda/Method Reference/Annoymous Class](#1-behavior-parameterization--functional-interface--lambdamethod-referenceannoymous-class)
- [2. Behavior Parameterization VS Type Parameterization](#2-behavior-parameterization-vs-type-parameterization)
- [3. 本质相同](#3-%e6%9c%ac%e8%b4%a8%e7%9b%b8%e5%90%8c)
- [4. 使用场景](#4-%e4%bd%bf%e7%94%a8%e5%9c%ba%e6%99%af)

<!-- /TOC -->

我想通过“一个概念”来统治（统驭）这部分的知识，这个概念就是Behavior Parameterization。零散的知识，总是让我很难去把握，一会儿是Lambda Expression，一会儿是Method Reference，一会儿是Functional Interface，概念之间跳来跳去，混乱了我的思路。我需要的是一个干净的、清晰的思路，帮我走出这片“沼泽地”。

经历过战乱的纷争，才知道“和平”的可贵（想像你喜欢的电视剧、动漫作品，那些战争结束之后人们所表现的欢庆）。经历了混乱的概念，才知道一个清晰的、有逻辑的mind map的重要性。

## 1. Behavior Parameterization = Functional Interface + Lambda/Method Reference/Annoymous Class

我觉得，Lambda和Method Reference都是属于Behavior Parameterization的范围。

```java
int i = 100;
```

其中，`int`是Type，是装数据的“容器”，而`100`是Value，是真正的“数据”。这就表达了Type与Value之间的关系：Type是“容器”，Value是“数据”。

```txt
将Value装载到Type这个容器当中：
Type <-- Value
```

那么，把Type和Value的概念应用到Behavior Parameterization上来说，Lambda和Method Reference表示的只是Value，而Functional Interface表示的是Type。

## 2. Behavior Parameterization VS Type Parameterization

Behavior Parameterization是经Code（Behavior）进行参数化，而Type Parameterization（Generics，泛型）是将Type进行参数化。

## 3. 本质相同

有一种说法：

```txt
Lambda = anonymous functions
Method Reference = named functions
```

这个问题，从两个角度看：

- 从Java语言的角度来说，是对的。
- 从ClassFile中ByteCode的角度来说，是错误的。因为所有的lambda都会被javac编译成一个类的“方法”而存在着。相应的，还有什么“匿名类”，也是不存在的概念，都是被javac编译成一个具体的有名字的类了。现实生活中，还有什么“匿名投票”，只是不显示名字而已，其实都记录了。

## 4. 使用场景

Passing methods as values is clearly useful, but it’s a bit annoying having to write a definition for short methods such as `isHeavyApple` and `isGreenApple` when they’re used perhaps only once or twice.

But Java 8 has solved this too. It introduces a new notation (**anonymous functions**, or **lambdas**) that enables you to write just

```java
filterApples(inventory, (Apple a) -> "green".equals(a.getColor()));
```

or

```java
filterApples(inventory, (Apple a) -> a.getWeight() > 150 );
```

or even

```java
filterApples(inventory, (Apple a) -> a.getWeight() < 80 || "brown".equals(a.getColor()) );
```

So you don’t even need to write a method definition that’s used only once; the code is crisper and clearer because you don’t need to search to find the code you’re passing.

But if such a lambda exceeds a few lines in length (so that its behavior isn’t instantly clear), then you should instead use a **method reference** to a method with a descriptive name instead of using an anonymous lambda. **Code clarity should be your guide**.
