# GUI

<!-- TOC -->

- [1. GUI编程引言](#1-gui%e7%bc%96%e7%a8%8b%e5%bc%95%e8%a8%80)
- [2. GUI的分类](#2-gui%e7%9a%84%e5%88%86%e7%b1%bb)
  - [2.1. AWT](#21-awt)
  - [2.2. Swing](#22-swing)
  - [2.3. 两者之间的区别](#23-%e4%b8%a4%e8%80%85%e4%b9%8b%e9%97%b4%e7%9a%84%e5%8c%ba%e5%88%ab)
- [3. Java GUI API](#3-java-gui-api)
  - [3.1. 组件类](#31-%e7%bb%84%e4%bb%b6%e7%b1%bb)
  - [3.2. 容器类](#32-%e5%ae%b9%e5%99%a8%e7%b1%bb)
  - [3.3. GUI辅助类](#33-gui%e8%be%85%e5%8a%a9%e7%b1%bb)
  - [3.4. GUI运行原理](#34-gui%e8%bf%90%e8%a1%8c%e5%8e%9f%e7%90%86)
- [4. 体验GUI](#4-%e4%bd%93%e9%aa%8cgui)

<!-- /TOC -->

## 1. GUI编程引言

以前的学习当中，我们都使用的是**命令交互方式**。例如，在DOS命令行中通过`javac`、`java`命令启动程序。

软件的交互的方式：

- 命令交互方式
- 图形交互方式

Java提供了专业的API用于开发图形用户界面（GUI，Graphic User Interface）

将要了解GUI API的框架结构,以及GUI组件以及组件之间的关系,容器和布局管理器,颜色,字体等.

## 2. GUI的分类

### 2.1. AWT

Java 1.0版本的图形用户界面库，设计目标是帮助程序员编写在所有平台上都能良好表现的GUI程序。为了实现这个目标Java 1.0提供了**抽象窗口工具集（AWT，Abstract Window Toolkit）**，但是这个目标并没有达到。AWT在所有的系统上表现都不好。因为：最初版本的AWT是在一个月内构思，设计和实现的（Think in Java）。

Java将图形用户界面相关的类捆绑在了一起,放在了一个称之为抽象窗口工具集的库中。AWT适合开发简单的图形用户界面,并不适合开发复杂的GUI项目。

位于: `java.awt.*` 中,定义了很多的组件类，开发者可以直接创建对象加以使用  
缺点: 所有的图形界面都依赖于底层的操作系统,容易发生于特定平台相关的故障.  
AWT调用本地系统资源生成图形化界面, 依赖本地平台. 1.0  

### 2.2. Swing

SUN公司对AWT进行了升级，基于AWT，推出了一种更稳定,更通用和更灵活的库.称之为Swing组件库(Swing component)。

既然都是用于GUI设计的组件库,那么为了区分Swing组件类和对应的AWT组件类,Swing组件类都以字母`J`为前缀.

位于:`javax.swing.*` 中,提供了和AWT中等同的所有的组件类，但是类名的前面多加了一个`J`.

SWING可以跨平台.
我们主要学习Swing GUI组件.

### 2.3. 两者之间的区别

`java.awt`包与 `javax.swing`包的区别：

- `java.awt`中使用的图形类都是依赖于**系统**的图形库的。
- `javax.swing`包使用到的图形类都是sun自己实现，不需要依赖系统的图形库。

## 3. Java GUI API

GUI API包含的类分为三个部分：**组件类(component class)**、**容器类(container class)** 和 **辅助类(helper class)**。

- (1) **组件类**是用来创建用户图形界面的,例如`JButton`,`JLabel`,`JTextField`.
- (2) **容器类**是用来包含其他组件的,例如`JFrame`,`JPanel`
- (3) **辅助类**是用来支持GUI组件的,例如`Color`,`Font`

### 3.1. 组件类

在图形用户界面程序中，当我们想要创建按钮、复选框和滚动条等这些可以显示在屏幕上的对象时，该如何创建呢？其实这些都属于一类叫做**组件类**。

AWT中的组件根类`Component`类：

- `java.lang.Object`
  - `java.awt.Component`

```java
public abstract class Component
    implements ImageObserver, MenuContainer, Serializable
```

Swing中的组件根类`JComponent`类：

- `java.lang.Object`
  - `ava.awt.Component`
    - `java.awt.Container`
      - `javax.swing.JComponent`

```java
public abstract class JComponent
    extends Container
    implements Serializable, TransferHandler.HasGetTransferHandler
````

组件类的实例可以显示在屏幕上。`Component`类是包括容器类的所有用户界面类的根类是j`ava.awt`中的类，对应的`Swing`中的是`Jcomponent`。因为`Component`和`JComponent`都是抽象类，所以不能使用`new`关键字创建对象，需要使用它们的具体的实现类来创建对象。

在AWT中典型图形用户界面中的按钮(Button)、复选框(Checkbox)和滚动条(Scrollbar)都是组件类,都是Component类的子类。在Swing中的GUI组件,有对应的JButton,JCheckBox,JscrollBar。

继承关系图(AWT)

![](images/java-component-tree.png)

- A generic **Abstract Window Toolkit(AWT)** `Container` object is a component that can contain other AWT components.

- A `Window` object is a top-level window with no borders and no menubar. The default layout for a window is `BorderLayout`.

- A `Frame` is a top-level window with a title and a border.

- `Panel` is the simplest container class. A panel provides space in which an application can attach any other component, including other panels.  The default layout manager for a panel is the `FlowLayout` layout manager.

### 3.2. 容器类

容器(Container)，是一个特殊的组件，该组件可以通过`add()`添加其他组件。

- `java.lang.Object`
  - `ava.awt.Component`
    - `java.awt.Container`

```java
/**
 * A generic Abstract Window Toolkit(AWT) container object is a component
 * that can contain other AWT components.
 *
 * @since     JDK1.0
 */
public class Container extends Component {
    /**
     * Appends the specified component to the end of this container.
     * This is a convenience method for {@link #addImpl}.
     */
    public Component add(Component comp) {
        addImpl(comp, null, -1);
        return comp;
    }
}
```

容器类适用于盛装其他GUI组件的GUI组件。例如 `Panel`、`Frame`、`Dialog`都是AWT组件的容器类，对应的Swing组件的容器类是`JPanel`、`JFrame`、`JDialog`。

### 3.3. GUI辅助类

用来描述GUI组件的属性,例如图像的颜色,字体等。

> 注意：辅助类是在`java.awt`中的

### 3.4. GUI运行原理

在JDK的bin目录中有`javaw.exe`。`javaw.exe`是java在window中专门用于执行GUI程序.

## 4. 体验GUI

Code: [Link](code/simple/JFrame_01_QuickDemo.java)

```java
import javax.swing.JFrame;

public class JFrameQuickDemo {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("Hello World");
        // 设置尺寸
        frame.setSize(200, 100);
        // JFrame在屏幕中
        frame.setLocationRelativeTo(null);
        // JFrame关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示JFrame
        frame.setVisible(true);
    }
}
```

运行：

```bash
javac Demo.java
java Demo
```

![](images/20181021130813.png)

> 至此结束
