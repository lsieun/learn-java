import javax.swing.JFrame;

public class JFrame_01_Bounds {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("我的Frame");
        // 设置尺寸
        // frame.setSize(300, 400);
        // 设置位置
        // frame.setLocationRelativeTo(null);
        // 设置位置和大小
        frame.setBounds(200, 100, 300, 400);
        // JFrame关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示JFrame
        frame.setVisible(true);
    }
}
