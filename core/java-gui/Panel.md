# Panel

继承关系图(AWT)

![](images/java-component-tree.png)

A generic **Abstract Window Toolkit(AWT)** `Container` object is a component that can contain other AWT components.

A `Window` object is a top-level window with no borders and no menubar. The default layout for a window is `BorderLayout`.

A `Frame` is a top-level window with a title and a border.

`Panel` is the simplest container class. A panel provides space in which an application can attach any other component, including other panels.  The default layout manager for a panel is the `FlowLayout` layout manager.

## 修改背景色

```java
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_01_Background {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello");

        JPanel panel = new JPanel();
        panel.setBackground(Color.RED);

        frame.add(panel);
        FrameUtil.initFrame(frame, 300, 400);
    }
}

```

## 画图形和写文字

```java
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_01_Draw extends JPanel {

    @Override
    public void paint(Graphics g) {
        // Graphics g 画笔  使用该画笔可以画任何的东西。
        // 设置画笔的颜色
        g.setColor(Color.GRAY);
        // 画矩形
        g.fill3DRect(0, 0, 20, 20, true);
        g.fill3DRect(20, 0, 20, 20, true);
        g.setColor(Color.GREEN);
        g.fill3DRect(20, 20, 20, 20, true);

        // 写字
        g.setColor(Color.RED);
        // 设置画笔 的字体
        g.setFont(new Font("宋体", Font.BOLD, 30));
        g.drawString("GAME OVER", 300, 200);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");
        Panel_01_Draw panel = new Panel_01_Draw();
        frame.add(panel);
        FrameUtil.initFrame(frame, 700, 500);
    }
}

```


