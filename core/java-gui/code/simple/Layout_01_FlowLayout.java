import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;

public class Layout_01_FlowLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("测试Layout");

        // 创建面板
        JPanel panel = new JPanel();
        frame.add(panel);

        // 创建一个流式布局管理器
        FlowLayout layout = new FlowLayout(FlowLayout.LEFT, 0, 50);
        // 让流式布局管理器管理frame窗体
        panel.setLayout(layout);

        panel.add(new JButton("按钮1"));
        panel.add(new JButton("按钮2"));
        panel.add(new JButton("按钮3"));
        panel.add(new JButton("按钮4"));

        // 初始化窗体
        FrameUtil.initFrame(frame, 300, 400);
    }
}
