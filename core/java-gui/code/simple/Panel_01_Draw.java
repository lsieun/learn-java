import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_01_Draw extends JPanel {

    @Override
    public void paint(Graphics g) {
        // Graphics g 画笔  使用该画笔可以画任何的东西。
        // 设置画笔的颜色
        g.setColor(Color.GRAY);
        // 画矩形
        g.fill3DRect(0, 0, 20, 20, true);
        g.fill3DRect(20, 0, 20, 20, true);
        g.setColor(Color.GREEN);
        g.fill3DRect(20, 20, 20, 20, true);

        // 写字
        g.setColor(Color.RED);
        // 设置画笔 的字体
        g.setFont(new Font("宋体", Font.BOLD, 30));
        g.drawString("GAME OVER", 300, 200);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("测试");
        Panel_01_Draw panel = new Panel_01_Draw();
        frame.add(panel);
        FrameUtil.initFrame(frame, 700, 500);
    }
}
