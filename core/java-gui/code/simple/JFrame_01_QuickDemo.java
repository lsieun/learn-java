import javax.swing.JFrame;

public class JFrame_01_QuickDemo {
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
