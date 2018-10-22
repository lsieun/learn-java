import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JButton;

public class Layout_01_BorderLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试Layout");

        // 创建一个边框布局管理器
        BorderLayout layout = new BorderLayout();
        // 让borderlayout管理frame窗体。
        frame.setLayout(layout);

        frame.add(new JButton("北"), BorderLayout.NORTH);
        frame.add(new JButton("南"), BorderLayout.SOUTH);
        frame.add(new JButton("西"), BorderLayout.WEST);
        frame.add(new JButton("东"), BorderLayout.EAST);
        frame.add(new JButton("中"), BorderLayout.CENTER);

        FrameUtil.initFrame(frame, 400, 500);
    }
}
