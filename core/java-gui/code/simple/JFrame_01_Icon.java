import java.awt.Toolkit;
import javax.swing.JFrame;

public class JFrame_01_Icon {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("我的Frame");
        // 设置尺寸
        frame.setSize(300, 400);
        // 设置位置
        frame.setLocationRelativeTo(null);
        // JFrame关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 设置ICON
        frame.setIconImage(Toolkit.getDefaultToolkit().createImage("circle.png"));
        // 显示JFrame
        frame.setVisible(true);
    }
}
