import javax.swing.JFrame;
import javax.swing.JButton;

public class JFrame_02_AddComponent {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("Hello World");

        // 创建Button
        JButton btn = new JButton("OK");
        frame.add(btn);

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
