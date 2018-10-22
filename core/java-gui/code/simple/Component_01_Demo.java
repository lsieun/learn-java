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
