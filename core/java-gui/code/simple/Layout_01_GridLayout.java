import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JButton;

public class Layout_01_GridLayout {
    public static void main(String[] args) {
        JFrame frame = new JFrame("计算器");

        // 创建表格布局管理器
        GridLayout layout = new GridLayout(4, 4, 1, 2);
        // 让窗体交给表格布局管理器管理
        frame.setLayout(layout);

        for(int i = 0; i < 10; i++) {
            frame.add(new JButton("" + i));
        }

        frame.add(new JButton("+"));
        frame.add(new JButton("-"));
        frame.add(new JButton("*"));
        frame.add(new JButton("/"));
        frame.add(new JButton("="));
        frame.add(new JButton("."));
        // frame.add(new JButton("&"));

        FrameUtil.initFrame(frame, 300, 300);
    }
}

