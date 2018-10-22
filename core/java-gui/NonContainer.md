# 非窗口组件

继承关系图(AWT)

![](images/java-component-tree.png)

## 按钮

```java
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;

// 非窗口组件
public class Component_01_Demo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("注册");

        // 面板
        FrameUtil.initFrame(frame, 400, 300);
        JPanel panel = new JPanel();
        frame.add(panel);

        // 用户名
        JLabel nameLabel = new JLabel("用户名");
        JTextField nameField = new JTextField(12);
        panel.add(nameLabel);
        panel.add(nameField);

        // 密码
        JLabel passLabel = new JLabel("密码");
        JPasswordField passField = new JPasswordField(12);
        panel.add(passLabel);
        panel.add(passField);

        // 性别－单选框
        // 如果是单选框必须要进行分组，同一个组的单选框只能选择其中的一个
        JLabel sexLabel = new JLabel("性别");
        JRadioButton man = new JRadioButton("男", true);
        JRadioButton woman = new JRadioButton("女", true);
        ButtonGroup group = new ButtonGroup();
        group.add(man);
        group.add(woman);
        panel.add(sexLabel);
        panel.add(man);
        panel.add(woman);

        // 兴趣爱好－复选框
        JLabel hobbyLabel = new JLabel("兴趣爱好");
        JCheckBox javaCheck = new JCheckBox("Java", true);
        JCheckBox csharpCheck = new JCheckBox("C#", true);
        JCheckBox cplusCheck = new JCheckBox("C++");
        panel.add(hobbyLabel);
        panel.add(javaCheck);
        panel.add(csharpCheck);
        panel.add(cplusCheck);

        // 城市－下拉框
        JLabel cityLabel = new JLabel("城市");
        Object[] arr = {"北京", "上海", "广州", "深圳"};
        JComboBox cities = new JComboBox(arr);
        panel.add(cityLabel);
        panel.add(cities);

        // 个人简介
        JLabel profileLabel = new JLabel("个人简介");
        JTextArea profileArea = new JTextArea(10, 20);
        profileArea.setLineWrap(true); // 设置自动换行
        panel.add(profileLabel);
        panel.add(profileArea);

        FrameUtil.initFrame(frame, 400, 300);
    }
}

```

## 菜单

 菜单组件
    
    菜单条(MenuBar) 、  菜单（Menu） 、 菜单项(MenuItem)
    
    菜单条可以添加菜单
    
    菜单可以添加菜单项
    
    复选菜单：
        首先菜单添加菜单 ， 菜单添加菜单项。

```java
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;

public class Component_01_Menu {
    JFrame frame = new JFrame("记事本");

    // 菜单条
    JMenuBar bar = new JMenuBar();

    // 菜单
    JMenu fileMenu = new JMenu("文件");
    JMenu editMenu = new JMenu("编辑");

    JMenu swithMenu = new JMenu("切换工作目录");

    // 菜单项
    JMenuItem openItem = new JMenuItem("打开");
    JMenuItem saveItem = new JMenuItem("保存");
    JMenuItem copyItem = new JMenuItem("复制");
    JMenuItem pasteItem = new JMenuItem("粘贴");

    JMenuItem workItem1 = new JMenuItem("第1个项目");
    JMenuItem workItem2 = new JMenuItem("第2个项目");
    JMenuItem workItem3 = new JMenuItem("第3个项目");

    // 文本编辑区
    JTextArea textArea = new JTextArea(20, 30);

    public void initNotepad() {
        // 菜单 添加 菜单项
        fileMenu.add(openItem);
        fileMenu.add(saveItem);

        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // 复选菜单
        swithMenu.add(workItem1);
        swithMenu.add(workItem2);
        swithMenu.add(workItem3);
        fileMenu.add(swithMenu);

        // 菜单条添加菜单
        bar.add(fileMenu);
        bar.add(editMenu);

        // 添加菜单条 和 文本编辑区域
        frame.add(bar, BorderLayout.NORTH);
        frame.add(textArea, BorderLayout.CENTER);
        FrameUtil.initFrame(frame, 500, 600);
    }
    public static void main(String[] args) {
        new Component_01_Menu().initNotepad();
    }
}

```


