import javax.swing.JFrame;

public class JFrame_01_Location {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("我的Frame");
        // 设置尺寸
        frame.setSize(300, 400);
        // 设置位置
        frame.setLocation(200, 100);
        // 显示JFrame
        frame.setVisible(true);
    }
}
