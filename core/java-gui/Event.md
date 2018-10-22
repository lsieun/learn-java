# Event

事件： 当发生了某个事件的时候，就会有相应处理方案。
 
 
    事件源         监听器            事件            处理方案

## 1. ActionListener

```java
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Event_01_ActionListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("事件");

        JButton button = new JButton("滚滚红尘终误我");
        frame.add(button);

        // 给按钮添加动作监听器
        // 动作事件监听器(ActionListener)对于 鼠标点击 以及 空格键 都会起作用的。
        button.addActionListener(new ActionListener() {
            // 当按钮被点击的时候，就会调用actionPerformed的方法。
            // 当前按钮被点击时，JVM就会把对应的事件封装为ActionEvent，并且调用actionPerformed方法。
            @Override
            public void actionPerformed(ActionEvent e) {
                // 获取事件源
                JButton btn = (JButton) e.getSource();
                if (btn.getText().equals("滚滚红尘终误我")) {
                    btn.setText("今宵月夜可流连");
                }
                else {
                    btn.setText("滚滚红尘终误我");
                }
            }
        });

        FrameUtil.initFrame(frame, 300, 400);
    }
}

```

## 2. MouseListener

```java
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Event_01_MouseListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("鼠标监听事件");

        JButton btn = new JButton("Hello");
        frame.add(btn);

        /*
        btn.addMouseListener(new MouseListener() {
            @Override
            public void mouseReleased(MouseEvent e) {
                System.out.println("鼠标松开...");
            }
            
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println("鼠标按下..");
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                System.out.println("鼠标移出...");
            }
            
            @Override
            public void mouseEntered(MouseEvent e) {
                System.out.println("鼠标进入...");
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标单击..");
            }
        });
        */

        // 如果添加鼠标监听器的时候只使用到单击事件，但是目前要实现所有的方法？
        // 解决方案： 适配器。
        // 适配器是实现了MouseListener方法的所有方法，但是实现的方法全部都是空实现。
        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击。。。");
                if (e.getClickCount() == 2) {
                    System.out.println("鼠标双击。。。");
                }
            }
        });

        FrameUtil.initFrame(frame, 300, 400);
    }
}

```


## 3. KeyListener

```java
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class Event_01_KeyListener {
    public static void main(String[] args) {
        JFrame frame = new JFrame("键盘事件");

        JButton btn = new JButton("Hello");
        frame.add(btn);

        /*
        // 给按钮添加键盘事件监听器
        btn.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                System.out.println("键入某个键");
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                System.out.println("释放某个键");
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按下某个键..");
            }
        });
        */

        btn.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("按下的字符："+e.getKeyChar());
                System.out.println("获取键对应的数值："+ e.getKeyCode());
                int code = e.getKeyCode();
                switch (code) {
                case 38:
                    System.out.println("上");
                    break;
                case 40:
                    System.out.println("下");
                    break;
                case 37:
                    System.out.println("左");
                    break;
                case 39:
                    System.out.println("右");
                    break;
                default:
                    break;
                
                }
            
            }
        });

        FrameUtil.initFrame(frame, 300, 400);
    }
}

```


