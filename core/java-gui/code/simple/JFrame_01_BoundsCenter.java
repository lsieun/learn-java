import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;

public class JFrame_01_BoundsCenter {
    public static void main(String[] args) {
        // 创建JFrame
        JFrame frame = new JFrame("我的Frame");
        // 设置窗体大小并居中显示
        initFrame(frame, 300, 400);
        // JFrame关闭时的操作
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 显示JFrame
        frame.setVisible(true);
    }

    // 获取屏幕的分辨率 设置窗体在屏幕的居中位置
    public static void initFrame(JFrame frame, int width, int height) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取屏幕的分辨率
        Dimension d = toolkit.getScreenSize();

        int screenX = (int) d.getWidth();
        int screenY = (int) d.getHeight();

        int leftTopX = (screenX - width) / 2;
        int leftTopY = (screenY - height) / 2;

        frame.setBounds(leftTopX, leftTopY, width, height);
    }
}
