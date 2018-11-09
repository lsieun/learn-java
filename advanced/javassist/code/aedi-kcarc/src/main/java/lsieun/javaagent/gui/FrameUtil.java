package lsieun.javaagent.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

// 初始化窗体的工具类
public class FrameUtil {

    public static void initFrame(JFrame frame, int width, int height) {
        // 获取一个与系统相关工具类对象
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // 获取屏幕的分辨率
        Dimension d = toolkit.getScreenSize();

        int screenX = (int) d.getWidth();
        int screenY = (int) d.getHeight();

        int leftTopX = (screenX - width) / 2;
        int leftTopY = (screenY - height) / 2;

        frame.setBounds(leftTopX, leftTopY, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void quit(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
    }
}
